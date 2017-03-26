package com.cjteam.mrile.service.duiba;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.com.duiba.credits.sdk.CreditConsumeParams;
import cn.com.duiba.credits.sdk.CreditConsumeResult;
import cn.com.duiba.credits.sdk.CreditNotifyParams;
import cn.com.duiba.credits.sdk.CreditQueryParams;
import cn.com.duiba.credits.sdk.CreditQueryResult;
import cn.com.duiba.credits.sdk.CreditTool;

import com.cjteam.mrile.dao.IDuibaCreditConsumeDao;
import com.cjteam.mrile.dao.IExchangeRecordDao;
import com.cjteam.mrile.model.DuibaCreditConsume;
import com.cjteam.mrile.model.ExchangeRecord;
import com.cjteam.mrile.model.User;
import com.cjteam.mrile.service.BaseService;
import com.cjteam.mrile.service.UserService;

@Service
public class DuibaCreditService extends BaseService {

	@Autowired
	private UserService userService;
	@Autowired
	private IExchangeRecordDao exchangeRecordDao;
	@Autowired
	private IDuibaCreditConsumeDao duibaCreditConsumeDao;

	public DuibaCreditConsume getOne(long id) {
		return duibaCreditConsumeDao.selectOneById(id);
	}

	public CreditTool getCreditTool(String appKey,String appSecret) {
		return new CreditTool(appKey, appSecret);
	}

	public String buildCreditAutoLoginRequest(String appKey,String appSecret, String userId) {
		CreditTool tool = getCreditTool(appKey, appSecret);
		User user = userService.getOneByUserId(userId);
		Assert.notNull(user, "用户不存在");
		return tool.buildCreditAutoLoginRequest(userId, user.getSurplus()
				.intValue());
	}

	public void parseCreditResult(CreditNotifyParams notifyParams) {
		DuibaCreditConsume creditConsume = duibaCreditConsumeDao
				.selectOneById(Long.valueOf(notifyParams.getBizId()));
		creditConsume.setNotifyResult(notifyParams.isSuccess());
		creditConsume.setNotifyDate(notifyParams.getTimestamp());
		if (!notifyParams.isSuccess()) {
			userService.consumeScore(notifyParams.getUid(), 0 - creditConsume.getCredits());
			creditConsume.setNotifyMessage(notifyParams.getErrorMessage());
		}
		saveOrUpdateConsume(creditConsume);
	}

	public CreditConsumeResult creditConsume(CreditConsumeParams consumeParams) {
		CreditConsumeResult result;
		
		DuibaCreditConsume creditConsume = new DuibaCreditConsume();
		creditConsume.setActualPrice(consumeParams.getActualPrice());
		creditConsume.setAlipay(consumeParams.getAlipay());
		creditConsume.setAppKey(consumeParams.getAppKey());
		creditConsume.setDescription(consumeParams.getDescription());
		creditConsume.setType(consumeParams.getType());
		creditConsume.setOrderNum(consumeParams.getOrderNum());
		creditConsume.setPhone(consumeParams.getPhone());
		creditConsume.setQq(consumeParams.getQq());
		creditConsume.setUid(consumeParams.getUid());
		creditConsume.setCredits(consumeParams.getCredits());
		creditConsume.setTimestamp(consumeParams.getTimestamp());
		creditConsume.setFacePrice(consumeParams.getFacePrice());
		
		try {
			saveOrUpdateConsume(creditConsume);
			User user = userService.getOneByUserId(consumeParams.getUid());
			Assert.notNull(user, "用户不存在");
			Assert.isTrue(user.getSurplus() > consumeParams.getCredits(),
					"用户积分不够");
			Assert.isTrue(
					userService.consumeScore(user.getId()+"", consumeParams.getCredits()), "扣减用户积分失败");
			ExchangeRecord exchangeRecord = new ExchangeRecord();
			exchangeRecord.setContent(consumeParams.getDescription());
			exchangeRecord.setUserId(user.getUserId());
			exchangeRecord.setSurplus(consumeParams.getCredits());
			exchangeRecord.setType(consumeParams.getType());
			exchangeRecordDao.insert(exchangeRecord);
			creditConsume.setConsumeResult(Boolean.TRUE);

			result = new CreditConsumeResult(true);
			result.setBizId(String.valueOf(creditConsume.getId()));

		} catch (Throwable t) {
			logger.error(t.getLocalizedMessage(), t);
			result = new CreditConsumeResult(false);
			result.setErrorMessage(t.getMessage());
			creditConsume.setConsumeResult(Boolean.FALSE);
			creditConsume.setConsumeMessage(t.getLocalizedMessage());
		}
		saveOrUpdateConsume(creditConsume);
		return result;
	}

	public CreditQueryResult credit(CreditQueryParams queryParams) {
		User user = userService.getOneByUserId(queryParams.getUid());
		Assert.notNull(user, "用户不存在");
		CreditQueryResult result = new CreditQueryResult(true, user
				.getSurplus().intValue());
		if (StringUtils.isNotBlank(user.getAlipayNo()))
			result.setAlipay(user.getAlipayNo());
		if (StringUtils.isNotBlank(user.getMobilePhone()))
			result.setPhone(user.getMobilePhone());
		if (StringUtils.isNotBlank(user.getQq()))
			result.setQq(user.getQq());
		return result;
	}

	public void saveOrUpdateConsume(DuibaCreditConsume creditConsume) {
		DuibaCreditConsume dc = duibaCreditConsumeDao
				.selectOneById(creditConsume.getId());
		if (dc == null) {
			duibaCreditConsumeDao.insert(creditConsume);
		} else {
			duibaCreditConsumeDao.updateByPrimaryKeySelective(creditConsume);
		}
	}
}

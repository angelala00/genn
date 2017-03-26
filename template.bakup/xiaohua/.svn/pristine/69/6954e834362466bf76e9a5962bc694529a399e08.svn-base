package com.cjteam.xiao.jdbc;

import com.cjteam.xiao.model.DailyChannelIntegralStat;
import com.cjteam.xiao.model.Integral;
import com.cjteam.xiao.model.Product;
import com.cjteam.xiao.model.User;
import com.cjteam.xiao.repository.DailyChannelIntegralStatRepository;
import com.cjteam.xiao.util.ToolKits;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ChenLong on 14-3-3.
 */

public class ExternalTools extends JdbcTemplate {
    private final String insertIntegralSQL = "INSERT INTO INTEGRAL (" +
            "APP_ID,USER_ID,SCORE,CHANNEL,CREATE_TIME,STATUS,ORDER_ID)  VALUES (" +
            "?,?,?,?,?,?,?)";
    private final String insertDailyStatSQL = "INSERT INTO DAILY_CHANNEL_INTEGRAL_STAT (" +
            "APP_ID,USER_ID,SCORE,CHANNEL,DATE)  VALUES (" +
            "?,?,?,?,?)";
    private final String UPDATEDailyStatSQL = "UPDATE DAILY_CHANNEL_INTEGRAL_STAT  S SET S.SCORE=S.SCORE+? WHERE S.ID=?";

    public Date addIntegral(Integral integral) throws SQLException {
        Calendar createCalendar = Calendar.getInstance();
        List<Date> latestIntegralsTime = query("select max(i.create_time) from integral i where i.user_id=? limit 1 ", new Object[]{integral.getUser().getUserId()}, new RowMapper<Date>() {
            @Override
            public Date mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getDate(1);
            }
        });
        Date tmp = null;
        if (CollectionUtils.isNotEmpty(latestIntegralsTime)) {
            for (Date date : latestIntegralsTime) {
                if (date != null) {
                    tmp = date;
                    break;
                }
            }
        }
        if (null == tmp) {
            tmp = integral.getUser().getCreateTime();
        }
        createCalendar.setTime(tmp);
        createCalendar.set(Calendar.HOUR_OF_DAY, 8);
        createCalendar.add(Calendar.HOUR_OF_DAY, ((Integer) ToolKits.ranDomSelect(ToolKits.hours, ToolKits.hoursRandom)) % 3 + 1);
        createCalendar.add(Calendar.MINUTE, (Integer) ToolKits.ranDomSelect(ToolKits.minutes, ToolKits.minutesRandom));
        createCalendar.add(Calendar.SECOND, (Integer) ToolKits.ranDomSelect(ToolKits.seconds, ToolKits.secondsRandom));
        integral.setCreateTime(createCalendar.getTime());
        integral.setStatus(Boolean.TRUE);
        this.update(insertIntegralSQL, integral.getAppId(), integral.getUser().getUserId(), integral.getScore(), integral.getChannel().getCode(),
                integral.getCreateTime(), integral.getStatus(), integral.getOrderId());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        List<DailyChannelIntegralStat> stats = query("select s.id from DAILY_CHANNEL_INTEGRAL_STAT s where s.user_id=? and s.channel=? and s.date=? limit 1 ", new Object[]{integral.getUser().getUserId(), integral.getChannel().getCode(), df.format(integral.getCreateTime())},
                new RowMapper<DailyChannelIntegralStat>() {
                    @Override
                    public DailyChannelIntegralStat mapRow(ResultSet rs, int rowNum) throws SQLException {
                        DailyChannelIntegralStat stat = new DailyChannelIntegralStat();
                        stat.setId(rs.getInt(1));
                        return stat;
                    }
                }
        );
        DailyChannelIntegralStat stat = null;
        if (CollectionUtils.isNotEmpty(stats)) {
            stat = stats.get(0);
        }

        if (null == stat) {
            try {
                update(insertDailyStatSQL, integral.getAppId(), integral.getUser().getUserId(), integral.getScore(), integral.getChannel().getCode(), integral.getCreateTime());
            } catch (Throwable t) {
                t.printStackTrace();
            }
        } else {
            update(UPDATEDailyStatSQL, integral.getScore(), stat.getId());
        }
        return integral.getCreateTime();
    }

    private final String insertExchagneSQL = "INSERT INTO EXCHANGE (" +
            "APP_ID,USER_ID,PRODUCT_CODE,CONSUMER_ACCOUNT,MONEY_EXCHANGE,SCORE_CONSUMPTION,CREATE_TIME,STATE,STATUS)  VALUES (" +
            "?,?,?,?,?,?,?,?,?)";

    private final String insertWithdrawSQL = "INSERT INTO WITHDRAW (" +
            "APP_ID,USER_ID,ALIPAY_NO,AMOUNT_MONEY,SCORE_COST,CREATE_TIME,STATE)  VALUES (" +
            "?,?,?,?,?,?,?)";

    public Date addExchangeForAlipay(User user, Product product, String alipayNo) throws SQLException {
        Calendar createCalendar = Calendar.getInstance();
        List<Date> latestDrawsTime = query("select max(i.create_time) from withdraw i where i.user_id=? limit 1 ", new Object[]{user.getUserId()}, new RowMapper<Date>() {
            @Override
            public Date mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getDate(1);
            }
        });

        Date tmp = null;
        if (CollectionUtils.isNotEmpty(latestDrawsTime)) {
            for (Date date : latestDrawsTime) {
                if (date != null) {
                    tmp = date;
                    break;
                }
            }
        }
        if (null == tmp) {
            List<Date> latestIntegralsTime = query("select max(i.create_time) from integral i where i.user_id=? limit 1 ", new Object[]{user.getUserId()}, new RowMapper<Date>() {
                @Override
                public Date mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getDate(1);
                }
            });

            if (CollectionUtils.isNotEmpty(latestIntegralsTime))
                tmp = latestIntegralsTime.get(0);
        }

        if (null == tmp)
            return null;

        createCalendar.setTime(tmp);

        Date createTime = null;
        createCalendar.set(Calendar.HOUR_OF_DAY, 8);
        createCalendar.add(Calendar.HOUR_OF_DAY, Math.abs((Integer) ToolKits.ranDomSelect(ToolKits.hours, ToolKits.hoursRandom) % 5) + 1);
        createCalendar.add(Calendar.MINUTE, (Integer) ToolKits.ranDomSelect(ToolKits.minutes, ToolKits.minutesRandom));
        createCalendar.add(Calendar.SECOND, (Integer) ToolKits.ranDomSelect(ToolKits.seconds, ToolKits.secondsRandom));

        createTime = createCalendar.getTime();

        update(insertExchagneSQL, user.getAppId(), user.getUserId(),
                product.getCode(), alipayNo, product.getPrice(), product.getScore(), createTime, "doing", 1);
        update(insertWithdrawSQL, user.getAppId(), user.getUserId(), alipayNo, product.getPrice() / 100, product.getScore(), createTime, "doing");

        return createTime;
    }

    public void updateCreateTimeToBefore(User user, Date newCreateTime) {
        update("update user u set u.create_time=? where u.user_id=?", new Object[]{newCreateTime, user.getUserId()});
    }

    @Autowired
    private DailyChannelIntegralStatRepository statRepository;

    public DailyChannelIntegralStatRepository getStatRepository() {
        return statRepository;
    }

    public void setStatRepository(DailyChannelIntegralStatRepository statRepository) {
        this.statRepository = statRepository;
    }
}

package com.cjteam.xiao.web.vo;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Created by ChenLong Date: 13-10-8
 */
public class ExchangeRecordsResponse extends ResponseVo {
	private List<ExchangeVo> records;

	public List<ExchangeVo> getRecords() {
		return records;
	}

	public void setRecords(List<ExchangeVo> records) {
		this.records = records;
	}

    @Override
    public int getCount() {
        return CollectionUtils.isEmpty(records) ? 0 : records.size();
    }
}

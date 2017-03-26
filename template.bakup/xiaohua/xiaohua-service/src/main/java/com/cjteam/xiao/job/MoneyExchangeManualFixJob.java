package com.cjteam.xiao.job;

import com.cjteam.xiao.context.ApplicationContextUtils;
import com.cjteam.xiao.manager.DataFixManager;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ChenLong on 14-3-3.
 */
public class MoneyExchangeManualFixJob implements Job {
    //作为GroupName
    public static final String GROUP_NAME = "exchange.fix.manual";

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        DataFixManager exchangeFix = (DataFixManager) ApplicationContextUtils.getBean("dataFixManager");
        try {
            exchangeFix.doFix("3njsp");
            LOG.info("task exchange money  manual fix  job executed");
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage());
        }
    }
}

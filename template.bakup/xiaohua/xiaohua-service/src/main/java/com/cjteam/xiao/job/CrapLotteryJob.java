package com.cjteam.xiao.job;

import com.cjteam.xiao.context.ApplicationContextUtils;
import com.cjteam.xiao.service.CrapService;
import org.joda.time.DateTime;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ChenLong on 14-3-7.
 */
public class CrapLotteryJob extends CrapJob {
    private static final Logger LOG = LoggerFactory.getLogger(CrapLotteryJob.class);
    public static final String JOB_NAME = "crap_issue_lottery";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOG.info("start execute {} at {}", CrapLotteryJob.class.getName(), DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        CrapService crapService = (CrapService) ApplicationContextUtils.getBean("crapService");
        String appId = "3njsp";
        crapService.lottery(appId);
        LOG.info("lottery job starting.  completely done.");
    }
}

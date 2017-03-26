package com.cjteam.xiao.job;

import com.cjteam.xiao.context.ApplicationContextUtils;
import com.cjteam.xiao.service.ScoreCollector;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ChenLong on 14-3-3.
 */
public class MoneyExchangeLostScoreFixJob implements Job {
    //作为GroupName
    public static final String GROUP_NAME = "exchange.fix.lost";

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        ScoreCollector scoreCollector = (ScoreCollector) ApplicationContextUtils.getBean("scoreCollector");
        scoreCollector.doMoneyExchange();
        LOG.info("task exchange money lost fix  job Started");
    }
}

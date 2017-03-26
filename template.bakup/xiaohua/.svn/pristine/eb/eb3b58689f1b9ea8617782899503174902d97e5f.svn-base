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
public class CrapIssueManagerJob extends CrapJob {
    private static final Logger LOG = LoggerFactory.getLogger(CrapIssueManagerJob.class);
    public static final String JOB_NAME = "crap_issue_manage";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOG.info("start execute {} at {}", CrapIssueManagerJob.class.getName(), DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        CrapService crapService = (CrapService) ApplicationContextUtils.getBean("crapService");
        crapService.issueStatusManage("3njsp");//todo
    }
}
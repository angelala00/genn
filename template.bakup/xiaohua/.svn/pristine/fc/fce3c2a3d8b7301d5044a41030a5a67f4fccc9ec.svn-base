package com.cjteam.xiao.web.controller.platform.scoreinterface;

import com.cjteam.xiao.model.Channel;
import com.cjteam.xiao.model.Integral;
import com.cjteam.xiao.service.ChannelService;
import com.cjteam.xiao.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ChenLong on 2014/4/3.
 */
public class ScoreInterfaceBaseController extends BaseController {
    protected final String REQUEST_PARAM_NAME_CHANNEL_CODE="chcode";

    @Override
    protected String getPrefixPath() {
        //un-useable
        return null;
    }

    protected void channelCodeParamMustNotEmpty(Integral integral, String channelCode) throws ScoreInterfaceException {
        if (StringUtils.isBlank(channelCode)) {
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.CHANNEL_CODE_PARAM_NOT_EXIST);
        }
        if (StringUtils.isBlank(integral.getAppId())) {
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.APP_CODE_PARAM_NOT_EXIST);
        }
        Channel channel = channelService.findOneByCode(integral.getAppId(),channelCode);

        if (null == channel) {
            throw new ScoreInterfaceException(integral, ScoreInterfaceEnum.CHANNEL_NOT_EXIST);
        }
        integral.setChannel(channel);
    }

    @Autowired
    protected ChannelService channelService;
}

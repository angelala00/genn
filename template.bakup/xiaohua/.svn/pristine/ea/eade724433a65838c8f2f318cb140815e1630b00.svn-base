package com.cjteam.xiao.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjteam.xiao.model.User;
import com.cjteam.xiao.service.UserService;
import com.cjteam.xiao.web.vo.UserTop10Response;
import com.cjteam.xiao.web.vo.UserVo;

/**
 * Created by ChenLong Date: 13-10-8
 */
@Controller
@RequestMapping("/top")
public class TopController {

    @RequestMapping("score")
    public    @ResponseBody    UserTop10Response topScore(String appId , @RequestParam(required = false, defaultValue = "15") Integer number) {
        UserTop10Response response = new UserTop10Response();
        Page<User> users = userService.getTopTotallyScoredUserByPage(appId, number, 0);
        if (users != null && CollectionUtils.isNotEmpty(users.getContent())) {
            response.setUsers(change(users.getContent()));
            response.setSuccess(Boolean.TRUE);
        } else {
            response.setSuccess(Boolean.FALSE);
            response.setMessage("no user data");
        }
        return response;
    }

    private List<UserVo> change(List<User> content) {
        List<UserVo> s = new ArrayList<UserVo>();
        for (User c : content) {
            c.setSurplus(c.getTotalPoints());//由于 ios 前端没法修改，所以在这里做
            s.add(new UserVo(c));
        }
        return s;
    }

    @Autowired
    private UserService userService;
}

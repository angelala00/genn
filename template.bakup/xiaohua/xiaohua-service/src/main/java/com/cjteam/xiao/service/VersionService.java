package com.cjteam.xiao.service;

import java.util.Map;


/**
 * Created by ChenLong Date: 13-9-26
 */
public interface VersionService {

	Map<String, String> getLatestVersion(String appId);
}

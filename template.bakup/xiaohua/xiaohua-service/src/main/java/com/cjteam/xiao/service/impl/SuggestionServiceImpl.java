package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.model.Suggestion;
import com.cjteam.xiao.repository.SuggestionRepository;
import com.cjteam.xiao.service.SuggestionService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ChenLong on 14-3-2.
 */
@Service
@Transactional
public class SuggestionServiceImpl implements SuggestionService {
    private static final Logger LOG = LoggerFactory.getLogger(SuggestionServiceImpl.class);

    @Override
    public void addNew(String appId, String userId, String suggestion) {
        if (StringUtils.isBlank(suggestion))
            return;//ignore blank content
        suggestionRepository.save(new Suggestion(suggestion, userId,appId));
    }

    @Autowired
    SuggestionRepository suggestionRepository;
}

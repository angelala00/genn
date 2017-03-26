package com.cjteam.xiao.xiaohua.repository;

import com.cjteam.xiao.model.About;
import com.cjteam.xiao.xiaohua.model.XiaohuaContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by chenlong on 2014/7/23.
 */
public interface XiaohuaContentRepository  extends JpaRepository<XiaohuaContent,Integer> {

    XiaohuaContent findByTitle(String title);
    
    XiaohuaContent findByTargetIdAndXiaohuaSpiderConfigId(String targetId ,int id  ) ; 
    Page<XiaohuaContent> findByType(String type, Pageable pageable);
    @Modifying
    @Query("update XiaohuaContent set good=good+1 where id=:id")
    void updateGood(@Param("id") int id) ;
    @Modifying
    @Query("update XiaohuaContent set bad=bad+1 where id=:id")
    void updateBad(@Param("id") int id) ;
}

package com.cjteam.xiao.repository;
import com.cjteam.xiao.model.Announcement;
import com.cjteam.xiao.model.Withdraw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
    Announcement findByRecommend(String recommend);
}

package com.comm.recruit.dao;

import com.comm.model.recruit.Recruit;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/6/4
 */

public interface RecruitDao extends JpaRepository<Recruit,String> {
    List findByRecommend(Boolean recommend);

    List findTop5ByOrderByCreateTimeDesc();
}

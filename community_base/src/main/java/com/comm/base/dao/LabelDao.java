package com.comm.base.dao;

import com.comm.model.base.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/6/4
 */

public interface LabelDao extends JpaRepository<Label,String> {
    List findByRecommend(boolean recommend);

    List findByState(boolean state);
}

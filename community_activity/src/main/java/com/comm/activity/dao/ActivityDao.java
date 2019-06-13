package com.comm.activity.dao;

import com.comm.model.activity.Activity;
import com.comm.model.base.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hcq
 * @date : 2019/6/4
 */

public interface ActivityDao extends JpaRepository<Activity,String> {
}

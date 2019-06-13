package com.comm.recruit.dao;

import com.comm.model.recruit.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/6/4
 */

public interface EnterpriseDao extends JpaRepository<Enterprise,String> {
    List findByHot(Boolean b);
}

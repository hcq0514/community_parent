package com.comm.qa.dao;

import com.comm.model.qa.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hcq
 * @date : 2019/6/4
 */

public interface ProblemDao extends JpaRepository<Problem,String> {
}

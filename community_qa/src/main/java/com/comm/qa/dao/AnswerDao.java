package com.comm.qa.dao;

import com.comm.model.qa.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hcq
 * @date : 2019/6/4
 */

public interface AnswerDao extends JpaRepository<Answer, String> {
}

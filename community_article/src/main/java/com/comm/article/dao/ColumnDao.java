package com.comm.article.dao;

import com.comm.model.article.Column;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/6/4
 */

public interface ColumnDao extends JpaRepository<Column,String> {
    List findByUserId(String userId);
}

package com.comm.user.dao;

import com.comm.model.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hcq
 * @date : 2019/6/4
 */

public interface AdminDao extends JpaRepository<Admin,String> {
}

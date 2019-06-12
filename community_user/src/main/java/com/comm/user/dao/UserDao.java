package com.comm.user.dao;

import com.comm.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hcq
 * @date : 2019/6/4
 */

public interface UserDao extends JpaRepository<User,String> {
}

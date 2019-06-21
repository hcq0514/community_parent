package com.comm.sms.dao;

import com.comm.model.sms.App;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hcq
 * @date : 2019/6/4
 */

public interface AppDao extends JpaRepository<App, Long> {
}

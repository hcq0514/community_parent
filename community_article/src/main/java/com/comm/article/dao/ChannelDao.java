package com.comm.article.dao;

import com.comm.model.article.Channel;
import com.comm.model.base.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hcq
 * @date : 2019/6/4
 */

public interface ChannelDao extends JpaRepository<Channel,String> {
}

package com.comm.base.dao;

import com.comm.model.base.City;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hcq
 * @date : 2019/6/4
 */

public interface CityDao extends JpaRepository<City,String> {
}

package com.wonders.xlab.qudongdong.repository;

import com.wonders.xlab.framework.repository.MyRepository;
import com.wonders.xlab.qudongdong.entity.Sport;

import java.util.List;

/**
 * Created by mars on 15/8/18.
 */
public interface SportRepository extends MyRepository<Sport, Long> {

    List<Sport> findByEnabledTrue();
}

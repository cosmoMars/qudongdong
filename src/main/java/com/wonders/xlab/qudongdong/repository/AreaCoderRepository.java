package com.wonders.xlab.qudongdong.repository;

import com.wonders.xlab.framework.repository.MyRepository;
import com.wonders.xlab.qudongdong.entity.AreaCode;

import java.util.List;

/**
 * Created by mars on 15/9/7.
 */
public interface AreaCoderRepository extends MyRepository<AreaCode, Long> {

    List<AreaCode> findByParentIsNull();

    List<AreaCode> findByParentId(long parentId);
}

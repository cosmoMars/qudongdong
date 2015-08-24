package com.wonders.xlab.qudongdong.repository;

import com.wonders.xlab.framework.repository.MyRepository;
import com.wonders.xlab.qudongdong.entity.SportOrder;
import com.wonders.xlab.qudongdong.entity.User;

import java.util.List;

/**
 * Created by mars on 15/8/18.
 */
public interface SportOrderRepository extends MyRepository<SportOrder, Long> {

    List<SportOrder> findByUser(User user);

    SportOrder findTopByUserIdOrderByCreatedDateDesc(long userId);

//    @Query("from SportOrder sp where date_format(sp.createdDate, '%Y-%m-%d') = date_format(?1, '%Y-%m-%d')")
//    SportOrder findTopByToday(Date today);
}

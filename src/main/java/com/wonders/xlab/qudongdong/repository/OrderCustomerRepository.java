package com.wonders.xlab.qudongdong.repository;

import com.wonders.xlab.framework.repository.MyRepository;
import com.wonders.xlab.qudongdong.entity.OrderCustomer;

import java.util.List;

/**
 * Created by mars on 15/8/18.
 */
public interface OrderCustomerRepository extends MyRepository<OrderCustomer, Long> {

    List<OrderCustomer> findBySportOrderIdOrderByUserAgreeAsc(long orderId);

    OrderCustomer findBySportOrderIdAndCustomerId(long sportOrderId, long customerId);

    List<OrderCustomer> findBySportOrderIdAndCustomerIdNot(long sportOrderId, long customerId);

}

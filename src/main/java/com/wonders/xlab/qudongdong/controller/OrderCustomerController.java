package com.wonders.xlab.qudongdong.controller;

import com.wonders.xlab.framework.controller.AbstractBaseController;
import com.wonders.xlab.framework.repository.MyRepository;
import com.wonders.xlab.qudongdong.dto.result.ControllerResult;
import com.wonders.xlab.qudongdong.entity.OrderCustomer;
import com.wonders.xlab.qudongdong.entity.SportOrder;
import com.wonders.xlab.qudongdong.entity.User;
import com.wonders.xlab.qudongdong.repository.OrderCustomerRepository;
import com.wonders.xlab.qudongdong.repository.SportOrderRepository;
import com.wonders.xlab.qudongdong.repository.UserRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by mars on 15/8/18.
 */
@RestController
@RequestMapping("orderCustomer")
public class OrderCustomerController extends AbstractBaseController<OrderCustomer, Long> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SportOrderRepository sportOrderRepository;

    @Autowired
    private OrderCustomerRepository orderCustomerRepository;

    @Override
    protected MyRepository<OrderCustomer, Long> getRepository() {
        return orderCustomerRepository;
    }

    /**
     * 用户查看客户列表
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "listOrderCustomer/{userId}", method = RequestMethod.GET)
    public Object listOrderCustomer(@PathVariable long userId) {

        SportOrder sportOrder = sportOrderRepository.findTopByUserIdOrderByCreatedDateDesc(userId);

        if (!DateUtils.isSameDay(new Date(), sportOrder.getCreatedDate())) {
            return new ControllerResult<>()
                    .setRet_code(-1)
                    .setRet_values("亲，今天还没有发起订单哦")
                    .setMessage("失败");
        }

        List<OrderCustomer> orderCustomers = orderCustomerRepository.findBySportOrderId(sportOrder.getId());


        List<Map<String, Object>> list = new ArrayList<>();
        for (OrderCustomer customer : orderCustomers) {
            Map<String, Object> map = new HashMap<>();
            map.put("customerId", customer.getCustomer().getId());
            map.put("nickName", customer.getCustomer().getNickName());
            map.put("avatar",customer.getCustomer().getAvatarUrl());
            map.put("age", customer.getCustomer().getSex().ordinal());
            map.put("city", customer.getCustomer().getCity());

            list.add(map);
        }

        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values(list)
                .setMessage("成功");
    }

    /**
     * 生成用户约练请求
     * @param orderId
     * @param cId
     * @return
     */
    @RequestMapping(value = "generateOrderCustomer/{orderId}/{cId}", method = RequestMethod.GET)
    public Object generateOrderCustomer(@PathVariable long orderId,
                                        @PathVariable long cId) {

        SportOrder sportOrder = sportOrderRepository.findOne(orderId);

        if (sportOrder.getUser().getId() == cId) {
            return new ControllerResult<>()
                    .setRet_code(-1)
                    .setRet_values("不能对自己创建约练订单")
                    .setMessage("失败");
        }
        User customer = userRepository.findOne(cId);
        OrderCustomer orderCustomer = new OrderCustomer();
        orderCustomer.setSportOrder(sportOrder);
        orderCustomer.setCustomer(customer);
        orderCustomerRepository.save(orderCustomer);
        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values("添加成功")
                .setMessage("成功");
    }


    /**
     * 用户同意约炼
     * @param ocId
     * @param agree
     * @return
     */
    @RequestMapping(value = "responseCustomer/{ocId}/{agree}", method = RequestMethod.POST)
    public Object responseCustomer(@PathVariable long ocId,
                                   @PathVariable boolean agree) {

        OrderCustomer orderCustomer = orderCustomerRepository.findOne(ocId);
        orderCustomer.setUserAgree(agree);
        orderCustomerRepository.save(orderCustomer);

        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values("修改成功")
                .setMessage("成功");

    }

}

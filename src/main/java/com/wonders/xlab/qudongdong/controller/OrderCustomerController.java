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
import com.wonders.xlab.qudongdong.utils.SmsUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
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

        if (sportOrder == null || !DateUtils.isSameDay(new Date(), sportOrder.getCreatedDate())) {
            return new ControllerResult<>()
                    .setRet_code(-1)
                    .setRet_values("骚年，今天还没有发起订单哦")
                    .setMessage("失败");
        }

        List<OrderCustomer> orderCustomers = orderCustomerRepository.findBySportOrderIdOrderByUserAgreeAsc(sportOrder.getId());

        List<Map<String, Object>> list = new ArrayList<>();
        for (OrderCustomer oCustomer : orderCustomers) {
            Map<String, Object> map = new HashMap<>();
            map.put("orderCId", oCustomer.getId());
            map.put("customerId", oCustomer.getCustomer().getId());
            map.put("nickName", oCustomer.getCustomer().getNickName());
            map.put("avatar", oCustomer.getCustomer().getAvatarUrl());
            map.put("age", oCustomer.getCustomer().getSex().ordinal());
            map.put("city", oCustomer.getCustomer().getCity());
            map.put("sex", oCustomer.getCustomer().getSex().ordinal());
            map.put("agree", oCustomer.getUserAgree());

            list.add(map);
        }

        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values(list)
                .setMessage("成功");
    }

    /**
     * 生成用户约练请求
     *
     * @param orderId 订单id
     * @param cId     请求的用户id
     * @return
     */
    @RequestMapping(value = "generateOrderCustomer/{orderId}/{cId}", method = RequestMethod.GET)
    public Object generateOrderCustomer(@PathVariable long orderId,
                                        @PathVariable long cId) {

        SportOrder sportOrder = sportOrderRepository.findOne(orderId);

        if (sportOrder.getUser().getId() == cId) {
            return new ControllerResult<>()
                    .setRet_code(-1)
                    .setRet_values("约自己？你又傲娇了！")
                    .setMessage("失败");
        }

        OrderCustomer existOrder = orderCustomerRepository.findBySportOrderIdAndCustomerId(orderId, cId);

        if (existOrder != null) {
            return new ControllerResult<>()
                    .setRet_code(-1)
                    .setRet_values("骚年，你已经对TA下约咯～")
                    .setMessage("失败");
        }

        User customer = userRepository.findOne(cId);
        if (StringUtils.isEmpty(customer.getTel()) || StringUtils.isEmpty(customer.getWeChat())) {
            return new ControllerResult<>()
                    .setRet_code(-1)
                    .setRet_values("骚年，请先去完善个人信息")
                    .setMessage("失败");
        }

        OrderCustomer orderCustomer = new OrderCustomer();
        orderCustomer.setSportOrder(sportOrder);
        orderCustomer.setCustomer(customer);
        if (sportOrder.getUser().getTel() == null) {
            return new ControllerResult<>()
                    .setRet_code(-1)
                    .setRet_values("对方手机还未填写哦")
                    .setMessage("失败");
        }
        SmsUtils.sendInviteMessage(sportOrder.getUser().getTel(), customer.getNickName(), DateFormatUtils.format(sportOrder.getStartTime(), "yyyy.MM.dd HH:mm"), sportOrder.getLocation());

        orderCustomerRepository.save(orderCustomer);
        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values("骚等，请求已发出咯～")
                .setMessage("成功");
    }


    /**
     * 用户同意约炼
     *
     * @param ocId
     * @param agree
     * @return
     */
    @RequestMapping(value = "responseCustomer/{ocId}/{agree}", method = RequestMethod.GET)
    public Object responseCustomer(@PathVariable long ocId,
                                   @PathVariable boolean agree) {

        OrderCustomer orderCustomer = orderCustomerRepository.findOne(ocId);

        SportOrder sportOrder = orderCustomer.getSportOrder();

        List<OrderCustomer>  orderCustomers = new ArrayList<>(sportOrder.getOrderCustomers());

        for (OrderCustomer customer : orderCustomers) {
            if (customer.getId() == ocId) {
                customer.setUserAgree(true);
            } else {
                customer.setUserAgree(false);
            }
        }
//
//        if (sportOrder.getCurrentCount() >= sportOrder.getPeopleCount()) {
//            return new ControllerResult<>()
//                    .setRet_code(-1)
//                    .setRet_values("亲，你已经有骚年和你一起躁动咯～")
//                    .setMessage("失败");
//        }
//        orderCustomer.setUserAgree(agree);
        orderCustomerRepository.save(orderCustomers);
        if (agree) {
            // 发送给申请人
            SmsUtils.sendInviteSucceedMessage(orderCustomer.getCustomer().getTel(), sportOrder.getUser().getNickName(), DateFormatUtils.format(sportOrder.getStartTime(), "yyyy.MM.dd HH:mm"), sportOrder.getLocation());

            // 发送给发起人
            SmsUtils.sendInviteSucceedMessage(sportOrder.getUser().getTel(), orderCustomer.getCustomer().getNickName(), DateFormatUtils.format(sportOrder.getStartTime(), "yyyy.MM.dd HH:mm"), sportOrder.getLocation());

            sportOrder.setCurrentCount(sportOrder.getCurrentCount() + 1);

            sportOrderRepository.save(sportOrder);

        } else {
            SmsUtils.sendRefusesMessage(orderCustomer.getCustomer().getTel(), sportOrder.getUser().getNickName());
        }

        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values("骚年，赶快和小伙伴躁动起来吧～")
                .setMessage("成功");

    }

}

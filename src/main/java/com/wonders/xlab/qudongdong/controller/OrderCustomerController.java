package com.wonders.xlab.qudongdong.controller;

import com.wonders.xlab.framework.controller.AbstractBaseController;
import com.wonders.xlab.framework.repository.MyRepository;
import com.wonders.xlab.qudongdong.dto.result.ControllerResult;
import com.wonders.xlab.qudongdong.entity.OrderCustomer;
import com.wonders.xlab.qudongdong.entity.SportOrder;
import com.wonders.xlab.qudongdong.entity.SportOrderOfficial;
import com.wonders.xlab.qudongdong.entity.User;
import com.wonders.xlab.qudongdong.repository.OrderCustomerRepository;
import com.wonders.xlab.qudongdong.repository.SportOrderOfficialRepository;
import com.wonders.xlab.qudongdong.repository.SportOrderRepository;
import com.wonders.xlab.qudongdong.repository.UserRepository;
import com.wonders.xlab.qudongdong.utils.SmsUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private SportOrderOfficialRepository sportOrderOfficialRepository;

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

        List<OrderCustomer> orderCustomers = orderCustomerRepository.findBySportOrderIdOrderByUserAgreeDesc(sportOrder.getId());

        List<Map<String, Object>> list = new ArrayList<>();
        for (OrderCustomer oCustomer : orderCustomers) {
            Map<String, Object> map = new HashMap<>();
            map.put("orderCId", oCustomer.getId());
            map.put("customerId", oCustomer.getCustomer().getId());
            map.put("nickName", oCustomer.getCustomer().getNickName());
            map.put("avatar", oCustomer.getCustomer().getAvatarUrl());
            map.put("age", oCustomer.getCustomer().getAge());
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
     * 查看用户回应信息
     *
     * @param userId
     * @param past
     * @param pageable
     * @return
     */
    @RequestMapping(value = "listOrderCustomerByStatus/{userId}/{past}", method = RequestMethod.GET)
    public Object listOrderCustomerByStatus(@PathVariable long userId,
                                            @PathVariable boolean past,
                                            Pageable pageable) {

        SportOrder sportOrder = sportOrderRepository.findTopByUserIdOrderByCreatedDateDesc(userId);

        if (sportOrder == null || !DateUtils.isSameDay(new Date(), sportOrder.getCreatedDate())) {
            return new ControllerResult<>()
                    .setRet_code(-1)
                    .setRet_values("骚年，今天还没有发起订单哦")
                    .setMessage("失败");
        }
        List<OrderCustomer> orderCustomers;
        Page<OrderCustomer> orderCustomerPage;
        if (!past) {
            orderCustomerPage = orderCustomerRepository.findBySportOrderIdAndUserAgreeIsNull(sportOrder.getId(), pageable);
        } else {
            orderCustomerPage = orderCustomerRepository.findBySportOrderIdAndUserAgreeIsTrueOrderByCreatedDateDesc(sportOrder.getId(), pageable);
        }
        if (orderCustomerPage.hasContent()) {
            orderCustomers = orderCustomerPage.getContent();
        } else {
            orderCustomers = new ArrayList<>();
        }
        List<Map<String, Object>> list = new ArrayList<>();
        for (OrderCustomer oCustomer : orderCustomers) {
            Map<String, Object> map = new HashMap<>();
            map.put("orderCId", oCustomer.getId());
            map.put("customerId", oCustomer.getCustomer().getId());
            map.put("nickName", oCustomer.getCustomer().getNickName());
            map.put("avatar", oCustomer.getCustomer().getAvatarUrl());
            map.put("age", oCustomer.getCustomer().getAge());
            map.put("city", oCustomer.getCustomer().getCity());
            map.put("sex", oCustomer.getCustomer().getSex().ordinal());
            map.put("agree", oCustomer.getUserAgree());

            list.add(map);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", list);
        resultMap.put("more", orderCustomerPage.hasNext());
        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values(resultMap)
                .setMessage("成功");
    }

    /**
     * 生成用户约练请求
     *
     * @param orderId 订单id
     * @param cId     请求的用户id
     * @return
     */
//    @RequestMapping(value = "generateOrderCustomer/{orderId}/{cId}", method = RequestMethod.GET)
//    public Object generateOrderCustomer(@PathVariable long orderId,
//                                        @PathVariable long cId) {
//
//        SportOrder sportOrder = sportOrderRepository.findOne(orderId);
//
//        if (sportOrder.getUser().getId() == cId) {
//            return new ControllerResult<>()
//                    .setRet_code(-1)
//                    .setRet_values("约自己？你又傲娇了！")
//                    .setMessage("失败");
//        }
//     //   OrderCustomer existOrder = orderCustomerRepository.findBySportOrderIdAndCustomerId(orderId, cId);
//
//        if (existOrder != null) {
//            return new ControllerResult<>()
//                    .setRet_code(-1)
//                    .setRet_values("骚年，你已经对TA下约咯～")
//                    .setMessage("失败");
//        }
//
//        User customer = userRepository.findOne(cId);
//        if (StringUtils.isEmpty(customer.getTel()) || StringUtils.isEmpty(customer.getWeChat())) {
//            return new ControllerResult<>()
//                    .setRet_code(-1)
//                    .setRet_values("骚年，请先去完善个人信息")
//                    .setMessage("失败");
//        }
//        OrderCustomer orderCustomer = new OrderCustomer();
//        orderCustomer.setSportOrder(sportOrder);
//        orderCustomer.setCustomer(customer);
//
//        if (sportOrder.getUser().getTel() == null) {
//            return new ControllerResult<>()
//                    .setRet_code(-1)
//                    .setRet_values("对方手机还未填写哦")
//                    .setMessage("失败");
//        }
//        if (sportOrder.isOfficial()) {
//            orderCustomer.setUserAgree(true);
//            sportOrder.setCurrentCount(sportOrder.getCurrentCount() + 1);
//        }
//        if (!sportOrder.isOfficial()) {
//            SmsUtils.sendInviteMessage(sportOrder.getUser().getTel(), customer.getNickName(), DateFormatUtils.format(sportOrder.getStartTime(), "yyyy.MM.dd HH:mm"), sportOrder.getLocation());
//        }
//
//        orderCustomerRepository.save(orderCustomer);
//        sportOrderRepository.save(sportOrder);
//        return new ControllerResult<>()
//                .setRet_code(0)
//                .setRet_values("骚等，请求已发出咯～")
//                .setMessage("成功");
//    }


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
        if (new Date().getTime() > sportOrder.getStartTime().getTime()) {
            return new ControllerResult<>()
                    .setRet_code(-1)
                    .setRet_values("该信息已过期～")
                    .setMessage("失败");
        }

        List<OrderCustomer> orderCustomers = new ArrayList<>(sportOrder.getOrderCustomers());

        if (agree) {
            for (OrderCustomer customer : orderCustomers) {
                if (customer.getId() == ocId) {
                    customer.setUserAgree(true);
                } else {
                    customer.setUserAgree(false);
                }
            }
            orderCustomerRepository.save(orderCustomers);
            // 发送给申请人
            SmsUtils.sendInviteSucceedMessage(orderCustomer.getCustomer().getTel(), sportOrder.getUser().getNickName(), sportOrder.getUser().getWeChat());

            // 发送给发起人
            SmsUtils.sendInviteSucceedMessage(sportOrder.getUser().getTel(), orderCustomer.getCustomer().getNickName(), orderCustomer.getCustomer().getWeChat());

            sportOrder.setCurrentCount(sportOrder.getCurrentCount() + 1);

            sportOrderRepository.save(sportOrder);

            return new ControllerResult<>()
                    .setRet_code(0)
                    .setRet_values("骚年，赶快和小伙伴躁动起来吧～")
                    .setMessage("成功");
        } else {

            orderCustomer.setUserAgree(false);

            orderCustomerRepository.save(orderCustomers);

            SmsUtils.sendRefusesMessage(orderCustomer.getCustomer().getTel(), sportOrder.getUser().getNickName());
            return new ControllerResult<>()
                    .setRet_code(0)
                    .setRet_values("骚年，你简直了～")
                    .setMessage("成功");
        }
    }


    /**
     * 参加活动
     * @param orderId
     * @param cId
     * @return
     */
    @RequestMapping(value = "joinActivity/{orderId}/{cId}/{official}", method = RequestMethod.GET)
    public Object joinActivity(@PathVariable long orderId,
                               @PathVariable long cId,
                               @PathVariable long official) {
        if (official == 0) {//非官方
            SportOrder sportOrder = sportOrderRepository.findOne(orderId);

            if (sportOrder.getUser().getId() == cId) {
                return new ControllerResult<>()
                        .setRet_code(-1)
                        .setRet_values("约自己？你又傲娇了！")
                        .setMessage("失败");
            }
            OrderCustomer existOrder = orderCustomerRepository.findBySportOrderIdAndCustomerIdAndOfficial(orderId, cId, false);

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

            if (sportOrder.getCurrentCount() == sportOrder.getPeopleCount()) {
                return new ControllerResult<>()
                        .setRet_code(-1)
                        .setRet_values("预约人数已满")
                        .setMessage("失败");
            }
            sportOrder.setCurrentCount(sportOrder.getCurrentCount() + 1);
            orderCustomer.setOfficial(false);
            orderCustomerRepository.save(orderCustomer);
            sportOrderRepository.save(sportOrder);
        } else { //官方
            SportOrderOfficial sportOrderOfficial = sportOrderOfficialRepository.findOne(orderId);
            if (sportOrderOfficial.getUser().getId() == cId) {
                return new ControllerResult<>()
                        .setRet_code(-1)
                        .setRet_values("约自己？你又傲娇了！")
                        .setMessage("失败");
            }
            OrderCustomer existOrder = orderCustomerRepository.findBySportOrderIdAndCustomerIdAndOfficial(orderId, cId,false);

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
            sportOrderOfficial.setCurrentCount(sportOrderOfficial.getCurrentCount() + 1);

            OrderCustomer orderCustomer = new OrderCustomer();

            orderCustomer.setSportOrderOfficial(sportOrderOfficial);

            orderCustomer.setCustomer(customer);
            orderCustomer.setOfficial(true);
            orderCustomerRepository.save(orderCustomer);
            sportOrderOfficialRepository.save(sportOrderOfficial);

        }
        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values("骚等，请求已发出咯～")
                .setMessage("成功");
    }
}

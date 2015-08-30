package com.wonders.xlab.qudongdong.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wonders.xlab.framework.controller.AbstractBaseController;
import com.wonders.xlab.framework.repository.MyRepository;
import com.wonders.xlab.qudongdong.dto.OrderDto;
import com.wonders.xlab.qudongdong.dto.SportOrderDto;
import com.wonders.xlab.qudongdong.dto.result.ControllerResult;
import com.wonders.xlab.qudongdong.entity.Sport;
import com.wonders.xlab.qudongdong.entity.SportOrder;
import com.wonders.xlab.qudongdong.entity.User;
import com.wonders.xlab.qudongdong.repository.SportOrderRepository;
import com.wonders.xlab.qudongdong.repository.SportRepository;
import com.wonders.xlab.qudongdong.repository.UserRepository;
import com.wonders.xlab.qudongdong.utils.WdDateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

/**
 * Created by mars on 15/8/18.
 */
@RestController
@RequestMapping("order")
public class SportOrderController extends AbstractBaseController<SportOrder, Long> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SportRepository sportRepository;

    @Autowired
    private SportOrderRepository sportOrderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected MyRepository<SportOrder, Long> getRepository() {
        return sportOrderRepository;
    }

    /**
     * 添加订单
     *
     * @param userId
     * @param sportId
     * @param sportOrderDto
     * @return
     */
    @RequestMapping(value = "generateOrder/{userId}/{sportId}", method = RequestMethod.POST)
    public Object generateOrder(@PathVariable long userId,
                                @PathVariable long sportId,
                                @RequestBody SportOrderDto sportOrderDto) {


        Date now = new Date();
        try {
            Date startTime = DateUtils.parseDate(sportOrderDto.getStartTime(), "yyyy-MM-dd HH:mm");
            Date endTime = DateUtils.parseDate(sportOrderDto.getEndTime(), "yyyy-MM-dd HH:mm");
            if (now.getTime() > startTime.getTime()) {
                return new ControllerResult<>()
                        .setRet_code(-1)
                        .setRet_values("骚年，躁动时间过早哦")
                        .setMessage("失败");
            }
            if (endTime.getTime() - startTime.getTime() < 30 * 60 * 1000) {
                return new ControllerResult<>()
                        .setRet_code(-1)
                        .setRet_values("骚年，躁动时间要30分钟以上哦")
                        .setMessage("失败");
            }
//            if(startTime.getTime() > endTime.getTime()) {
//                return new ControllerResult<>()
//                        .setRet_code(-1)
//                        .setRet_values("请填写正确的躁动时间哦～")
//                        .setMessage("失败");
//            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        User user = userRepository.findOne(userId);
        if (StringUtils.isEmpty(user.getTel())) {
            return new ControllerResult<>()
                    .setRet_code(-1)
                    .setRet_values("请完善你的信息")
                    .setMessage("失败");
        }
        Sport sport = sportRepository.findOne(sportId);

//        SportOrder existOrder = sportOrderRepository.findTopByUserIdOrderByCreatedDateDesc(userId);
//        if (existOrder != null && org.apache.commons.lang3.time.DateUtils.isSameDay(new Date(), existOrder.getCreatedDate())) {
//            return new ControllerResult<>()
//                    .setRet_code(-1)
//                    .setRet_values("亲，你今天已经躁动过咯～")
//                    .setMessage("失败");
//        }

        SportOrder sportOrder = sportOrderDto.toNewOrder();

        sportOrder.setUser(user);
        sportOrder.setSport(sport);
        if (sportOrderDto.getOfficial()) {
            sportOrder.setOfficial(true);
            sportOrder.setPeopleCount(sportOrderDto.getPeopleCount());
        } else {
            sportOrder.setOfficial(false);
            sportOrder.setPeopleCount(1);

        }
        sportOrderRepository.save(sportOrder);

        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values("发布成功")
                .setMessage("成功");
    }

    /**
     * 订单列表
     * @param pageable
     * @return
     */
    @RequestMapping(value = "listSportOrder/{userId}", method = RequestMethod.GET)
    private Object listSportOrder(
            @PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC)
            Pageable pageable) {
        List<SportOrder> orderPages = sportOrderRepository.findTopByToday(new Date(), pageable);

        List<OrderDto> orderDtos = new ArrayList<>();

        Date now = new Date();
        for (SportOrder order : orderPages) {
            OrderDto dto = new OrderDto();
            dto.setOrderId(order.getId());
            dto.setUserId(order.getUser().getId());
            dto.setLocation(order.getLocation());

            dto.setPeopleCount(order.getPeopleCount());
            dto.setCurrentPeople(order.getCurrentCount());

            dto.setAvatarUrl(order.getUser().getAvatarUrl());
            dto.setSex(order.getUser().getSex().ordinal());
            dto.setNickName(order.getUser().getNickName());
            dto.setSportName(order.getSport().getName());
            dto.setSports(new ArrayList<>(order.getUser().getSports()));
            dto.setContent(order.getContent());
            dto.setOfficial(order.isOfficial());

            int diffTime = WdDateUtils.calculatePeiorMiniutesOfTwoDate(order.getCreatedDate(), now);
            if (diffTime < 60) {
                dto.setDiffTime(diffTime + "分钟");
            } else {
                dto.setDiffTime((diffTime / 60) + "小时");
            }
            dto.setStartTime(DateFormatUtils.format(order.getStartTime(), "HH:mm"));
            dto.setEndTime(DateFormatUtils.format(order.getEndTime(), "HH:mm"));

            dto.setPercent(dto.getCurrentPeople() / dto.getPeopleCount());

            if (now.getTime() < order.getStartTime().getTime() && dto.getCurrentPeople() < dto.getPeopleCount()) {
                dto.setEnabled(true);
            } else {
                dto.setEnabled(false);
            }
            dto.setAge(order.getUser().getAge());
            orderDtos.add(dto);
        }

        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values(orderDtos)
                .setMessage("成功");
    }

    /**
     * 时光轴
     * @param userId
     * @return
     */
    @RequestMapping(value = "listUserOrder/{userId}", method = RequestMethod.GET)
    public Object listUserOrder(@PathVariable long userId) {

        User user = userRepository.findOne(userId);

        List<SportOrder> sportOrders = sportOrderRepository.findByUserOrderByCreatedDateDesc(user);

        JsonNodeFactory jsonNodeFactory = objectMapper.getNodeFactory();

        ObjectNode node = jsonNodeFactory.objectNode();

        node.put("userId", user.getId());
        node.put("nickName", user.getNickName());
        node.put("avatar", user.getAvatarUrl());
        node.put("age", user.getAge());
        node.put("sex", user.getSex().ordinal());
        node.put("city", user.getCity());

        List<Map<String, Object>> list = new ArrayList<>();
        for (SportOrder sportOrder : sportOrders) {
            Map<String, Object> map = new HashMap<>();
            map.put("startTime", DateFormatUtils.format(sportOrder.getStartTime(), "HH:mm"));
            map.put("endTime", DateFormatUtils.format(sportOrder.getEndTime(), "HH:mm"));

            map.put("month", DateFormatUtils.format(sportOrder.getCreatedDate(), "M"));
            map.put("day", DateFormatUtils.format(sportOrder.getCreatedDate(), "d"));

            map.put("sportName", sportOrder.getSport().getName());
            map.put("sportIcon",sportOrder.getSport().getIconUrl());
            map.put("location", sportOrder.getLocation());
            map.put("currentPeople", sportOrder.getCurrentCount());
            map.put("peopleCount", sportOrder.getPeopleCount());

            list.add(map);
        }
        node.putPOJO("orders", list);

        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values(node)
                .setMessage("成功");
    }
}

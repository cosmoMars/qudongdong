package com.wonders.xlab.qudongdong.controller;

import com.wonders.xlab.framework.controller.AbstractBaseController;
import com.wonders.xlab.framework.repository.MyRepository;
import com.wonders.xlab.qudongdong.dto.result.ControllerResult;
import com.wonders.xlab.qudongdong.entity.AreaCode;
import com.wonders.xlab.qudongdong.repository.AreaCoderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mars on 15/9/7.
 */
@RestController
@RequestMapping("areaCode")
public class AreaCodeController extends AbstractBaseController<AreaCode, Long> {

    @Autowired
    private AreaCoderRepository areaCoderRepository;

    @Override
    protected MyRepository<AreaCode, Long> getRepository() {
        return areaCoderRepository;
    }

    /**
     * 获取父节点
     * @return
     */
    @RequestMapping(value = "retrieveParentArea", method = RequestMethod.GET)
    public Object retrieveParentArea() {
        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values(areaCoderRepository.findByParentIsNull())
                .setMessage("成功");
    }

    /**
     * 获取子节点
     * @param parentId
     * @return
     */
    @RequestMapping(value = "retrieveAreaCodeByParent/{parentId}", method = RequestMethod.GET)
    public Object retrieveAreaCodeByParent(@PathVariable long parentId) {

        return new ControllerResult<>()
                .setRet_code(0)
                .setRet_values(areaCoderRepository.findByParentId(parentId))
                .setMessage("成功");
    }
}

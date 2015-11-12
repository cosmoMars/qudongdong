package com.wonders.xlab.qudongdong.controller;

import com.wonders.xlab.framework.controller.AbstractBaseController;
import com.wonders.xlab.framework.repository.MyRepository;
import com.wonders.xlab.qudongdong.entity.Venue;
import com.wonders.xlab.qudongdong.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yk on 15/11/12.
 */
@RestController
@RequestMapping(value = "venue")
public class VenueController extends AbstractBaseController<Venue,Long>{

    @Autowired
    private VenueRepository venueRepository;

    @Override
    protected MyRepository<Venue, Long> getRepository() {
        return venueRepository;
    }
}

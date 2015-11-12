package com.wonders.xlab.qudongdong.repository;

import com.wonders.xlab.framework.repository.MyRepository;
import com.wonders.xlab.qudongdong.entity.Venue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * Created by yk on 15/11/12.
 */
public interface VenueRepository extends MyRepository<Venue,Long> {

}

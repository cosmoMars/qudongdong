package com.wonders.xlab.qudongdong.repository.v1;

import com.wonders.xlab.framework.repository.MyRepository;
import com.wonders.xlab.qudongdong.entity.v1.BannerPic;

import java.util.List;

/**
 * Created by wanda2 on 16/3/28.
 */
public interface BannerPicRepository extends MyRepository<BannerPic, Long> {

    List<BannerPic> findByEnableTrueOrderByIdAsc();

}

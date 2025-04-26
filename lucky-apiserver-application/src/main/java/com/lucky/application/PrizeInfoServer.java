package com.lucky.application;

import com.lucky.domain.PrizeInfoService;
import com.lucky.domain.entity.PrizeInfoEntity;
import com.lucky.domain.repository.PrizeInfoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 奖品
 */
@Component
public class PrizeInfoServer {
    private final PrizeInfoService prizeInfoService;

    public PrizeInfoServer(PrizeInfoService prizeInfoService) {
        this.prizeInfoService = prizeInfoService;
    }


    /**
     * 添加/修改
     */
    public Long saveOrUpdate(PrizeInfoEntity entity) {
        return prizeInfoService.saveOrUpdate(entity);
    }
    /**
     * 批量添加/修改
     */
    public  Boolean saveOrUpdateList(List<PrizeInfoEntity> entity) {
        return prizeInfoService.saveOrUpdateList(entity);
    }

    /**
     * 删除
     */
    public Boolean deleteById(Long id) {
        return prizeInfoService.deleteById(id);
    }

    public List<PrizeInfoEntity> findByTopicId(Long topicId) {
        return prizeInfoService.findByTopicId(topicId);
    }
}

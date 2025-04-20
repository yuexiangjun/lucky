package com.lucky.domain.repository;

import com.lucky.domain.entity.PrizeInfoEntity;

import java.util.List;

public interface PrizeInfoRepository {

    Long saveOrUpdate(PrizeInfoEntity entity);

    Boolean deleteById(Long id);

    List<PrizeInfoEntity> findByTopicId(Long topicId);

    List<PrizeInfoEntity> findByIds(List<Long> productIds);
}

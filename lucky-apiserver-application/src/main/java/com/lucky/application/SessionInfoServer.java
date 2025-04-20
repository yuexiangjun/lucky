package com.lucky.application;

import com.lucky.domain.GradeService;
import com.lucky.domain.PrizeInfoService;
import com.lucky.domain.SessionInfoService;
import com.lucky.domain.entity.GradeEntity;
import com.lucky.domain.entity.PrizeInfoEntity;
import com.lucky.domain.entity.SessionInfoEntity;
import com.lucky.domain.valueobject.BaseDataPage;
import com.lucky.domain.valueobject.InventoryInfo;
import com.lucky.domain.valueobject.SessionInfo;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import java.util.Comparator;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SessionInfoServer {
	private final SessionInfoService sessionInfoService;
	private final PrizeInfoService prizeInfoService;
	private final GradeService gradeService;

	public SessionInfoServer(SessionInfoService sessionInfoService,
	                         PrizeInfoService prizeInfoService,
	                         GradeService gradeService) {
		this.sessionInfoService = sessionInfoService;
		this.prizeInfoService = prizeInfoService;
		this.gradeService = gradeService;
	}

	/**
	 * 商品详情
	 */
	public BaseDataPage<SessionInfo> findByTopicIdPageNO(Long topicId, Integer page, Integer size) {

		var sessionInfoEntityPage = sessionInfoService.findByTopicIdPageNO(topicId, page, size);





		return this.getSessionInfoBaseDataPage(topicId, sessionInfoEntityPage);

	}

	/**
	 * 全部场次
	 */
	public BaseDataPage<SessionInfo> findByTopicIdPageStatus(Long topicId, Integer page, Integer size) {
		var sessionInfoEntityPage = sessionInfoService.findByTopicIdPageStatus(topicId, page, size);

		return this.getSessionInfoBaseDataPage(topicId, sessionInfoEntityPage);
	}

	private BaseDataPage<SessionInfo> getSessionInfoBaseDataPage(Long topicId, BaseDataPage<SessionInfoEntity> sessionInfoEntityPage) {
		var dataList = sessionInfoEntityPage.getDataList();

		if (CollectionUtils.isEmpty(dataList))
			return new BaseDataPage<>(0l);
		//更据系列获取奖品
		var prizeInfoEntities = prizeInfoService.findByTopicId(topicId);

		var gradeIds = prizeInfoEntities.stream()
				.map(PrizeInfoEntity::getGradeId)
				.collect(Collectors.toList());

		var gradeEntityMap = this.getGradeEntityMap(gradeIds);

		var prizeInfoEntityMap = this.getPrizeInfoEntityMap(prizeInfoEntities);
		//总库存
		var totalInventory = prizeInfoEntities.stream()
				.filter(p -> p.getType() == 2)
				.map(PrizeInfoEntity::getInventory)
				.reduce(0, Integer::sum);

		var totalInventoryMap = prizeInfoEntities.stream()
				.collect(Collectors.toMap(PrizeInfoEntity::getId, PrizeInfoEntity::getInventory));

		var sessionInfoList = dataList.stream()
				.map(s -> {
					var inventoryInfos = s.getPrizeInventory()
							.stream()
							.map(p -> {

								PrizeInfoEntity prizeInfoEntity = prizeInfoEntityMap.get(p.getPrizeId());
								GradeEntity gradeEntity = gradeEntityMap.get(prizeInfoEntity.getGradeId());
								return InventoryInfo.builder()
										.totalInventory(totalInventoryMap.get(p.getPrizeId()))
										.remainInventory(p.getInventory())
										.prizeUrl(prizeInfoEntity.getPrizeUrl())
										.prizeName(prizeInfoEntity.getPrizeName())
										.gradeName(gradeEntity.getName())
										.sort(gradeEntity.getSort())
										.build();
							}).sorted(Comparator.comparingInt(InventoryInfo::getSort))
							.collect(Collectors.toList());

					return SessionInfo.builder()
							.id(s.getId())
							.totalInventory(totalInventory)
							.remainInventory(s.totalInventory())
							.inventoryInfos(inventoryInfos)
							.build();


				})
				.collect(Collectors.toList());

		return BaseDataPage.newInstance(
				sessionInfoEntityPage.getTotal(),
				sessionInfoEntityPage.getPages(),
				sessionInfoList);
	}

	/**
	 * 奖项详情
	 *
	 * @param gradeIds
	 * @return
	 */
	private Map<Long, GradeEntity> getGradeEntityMap(List<Long> gradeIds) {
		return gradeService.findByIds(gradeIds)
				.stream()
				.collect(Collectors.toMap(GradeEntity::getId, Function.identity(), (v1, v2) -> v1));
	}

	/**
	 * 奖品详情
	 *
	 * @param prizeInfoEntities
	 * @return
	 */

	private Map<Long, PrizeInfoEntity> getPrizeInfoEntityMap(List<PrizeInfoEntity> prizeInfoEntities) {
		return prizeInfoEntities.stream()
				.collect(Collectors.toMap(PrizeInfoEntity::getTopicId, Function.identity(), (v1, v2) -> v1));
	}


}

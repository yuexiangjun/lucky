package com.lucky.application;

import com.lucky.domain.DeliveryAddressService;
import com.lucky.domain.entity.DeliveryAddressEntity;
import com.lucky.domain.repository.DeliveryAddressRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 收货地址信息
 */
@Component
public class DeliveryAddressServer {
    private final DeliveryAddressService deliveryAddressService;

    public DeliveryAddressServer(DeliveryAddressService deliveryAddressService) {
        this.deliveryAddressService = deliveryAddressService;
    }


    /**
     * 添加修改
     *
     * @param entity
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public  void  saveOrUpdate(DeliveryAddressEntity entity) {
        var id = deliveryAddressService.saveOrUpdate(entity);
        if (Objects.isNull(id))
            throw new RuntimeException("添加/修改失败");
    }

    ;

    /**
     * 根据id查询
     */
    public DeliveryAddressEntity getById(Long id) {
        return deliveryAddressService.getById(id);
    }

    ;

    /**
     * 更据id删除
     */
    public void deleteById(Long id) {
         var b = deliveryAddressService.deleteById(id);
        if (!b)
            throw new RuntimeException("删除失败");

    }

    /**
     * 更据用户查询
     */
    public List<DeliveryAddressEntity> getByWechatUserId(Long wechatUserId) {
        return deliveryAddressService.getByWechatUserId(wechatUserId);
    }

    /**
     * 修改默认地址
     */
    public  void  updateDefault(Long id, Long wechatUserId) {
        var  b= deliveryAddressService.updateDefault(id,wechatUserId);


        if (!b)
            throw new RuntimeException("修改默认地址失败");

     }

}

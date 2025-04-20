package com.lucky.domain.valueobject;

import com.lucky.domain.entity.PrizeInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    /**
     * 奖品id
     */
    private Long prizeId;
    /**
     * 库存
     */
    private Integer inventory;

    public static Inventory getInstance(PrizeInfoEntity prizeInfoEntity) {
        if (Objects.isNull(prizeInfoEntity))
            return null;
        return Inventory.builder()
                .inventory(prizeInfoEntity.getInventory())
                .prizeId(prizeInfoEntity.getId())
                .build();

    }

}

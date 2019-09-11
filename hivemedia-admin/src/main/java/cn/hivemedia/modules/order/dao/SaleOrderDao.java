package cn.hivemedia.modules.order.dao;

import cn.hivemedia.modules.order.entity.SaleOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 出售订单信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
public interface SaleOrderDao extends BaseMapper<SaleOrderEntity> {


    List<SaleOrderEntity> selectOutOfTime(@Param("time") String time,@Param("orderStatus") int orderStatus,
                                          @Param("index") int index,@Param("size") int size);

    /**
     *
     * @param amountTotal
     * @param goodsId
     * @param sizeId
     * @return
     */
    List<SaleOrderEntity> selectPriceOrdersBiMinPrice(@Param("amountTotal") BigDecimal amountTotal, @Param("goodsId") Integer goodsId, @Param("sizeId") Integer sizeId);

    List<SaleOrderEntity> selectByPurchasePrice(@Param("amountTotal") BigDecimal amountTotal, @Param("goodsId") Integer goodsId, @Param("sizeId") Integer sizeId);

    List<SaleOrderEntity> selectSaleOrderList(@Param("goodsId") Integer goodsId, @Param("sizeId") Integer sizeId);

}

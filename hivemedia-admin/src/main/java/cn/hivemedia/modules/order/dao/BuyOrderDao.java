package cn.hivemedia.modules.order.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import cn.hivemedia.modules.order.entity.BuyOrderEntity;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购买订单信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
public interface BuyOrderDao extends BaseMapper<BuyOrderEntity> {

    List<BuyOrderEntity> selectOutOfTime(@Param("time")String  time, @Param("orderStatus") int orderStatus,
                                         @Param("index") int index, @Param("size") int size);

    List<BuyOrderEntity> selectOutOfUpdateTime(@Param("time")String  time, @Param("orderStatus") int orderStatus,
                                               @Param("index") int index, @Param("size") int size);

    BuyOrderEntity selectLatestBuyOrder(@Param("goodsId") Integer goodsId);

    /**
     * 查看商品尺码对应的最高求购价
     * @param goodsId
     * @param sizeId
     * @return
     */
    BigDecimal selectMaxPurchasePrice(@Param("goodsId") Integer goodsId, @Param("sizeId") Integer sizeId);
}

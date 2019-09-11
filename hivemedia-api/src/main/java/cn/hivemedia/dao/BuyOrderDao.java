package cn.hivemedia.dao;

import cn.hivemedia.entity.BuyOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import cn.hivemedia.entity.model.*;
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

    List<OrderGoods> queryListByGoodsId(Page<OrderGoods> page, @Param("goodsId") Integer goodsId, @Param("buyType") Integer buyType, @Param("sizeId") Integer sizeId, @Param("orderStatus") Integer orderStatus);

    List<OrderGoods> queryLastFiveOrderByGoodsId(@Param("goodsId") Integer goodsId, @Param("buyType") Integer buyType,@Param("sizeId") Integer sizeId);
    /**
     * 商品最高求购价格  按规格分
     *
     * @param goodsId goodsId
     * @return list
     */
    List<SaleGoods> querySaleGoodsAmountOrderBySize(Integer goodsId);

    /**
     * 商品最最低出售  按规格分
     *
     * @param goodsId goodsId
     * @return list
     */
    List<SaleGoods> queryBuyGoodsAmountOrderBySize(@Param("goodsId") Integer goodsId);

    /**
     * 商品最低已成交价格
     *
     * @param goodsId goodsId
     * @param sizeId  sizeId
     * @return list
     */
    SaleGoods queryBuyOrderAmountByGoodsSize(@Param("goodsId") Integer goodsId, @Param("sizeId") Integer sizeId);

    /**
     * 查询出价求购尺码列表(显示该尺码对应的最高求购价)
     *
     * @param goodsId
     * @return
     */
    List<OfferPurchaseDto> offerPurchaseList(@Param("goodsId") Integer goodsId);

    BuyOrderEntity selectLatestBuyOrder(@Param("goodsId") Integer goodsId);

    GoodsOfferPurchase getGoodsOfferPurchase(@Param("goodsId") Integer goodsId,@Param("sizeId") Integer sizeId);

    /**
     * 查看商品尺码对应的最高求购价
     * @param goodsId
     * @param sizeId
     * @return
     */
    BigDecimal selectMaxPurchasePrice(@Param("goodsId") Integer goodsId, @Param("sizeId") Integer sizeId);
}

package cn.hivemedia.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.entity.BuyOrderEntity;
import cn.hivemedia.entity.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 购买订单信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
public interface BuyOrderService extends IService<BuyOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Page<OrderGoods> queryListByGoodsId(Integer goodsId, Integer buyType, Integer sizeId, Integer pageNo, Integer pageSize,Integer orderStatus);

    List<OrderGoods> queryLastFiveOrderByGoodsId(Integer goodsId, Integer buyType, Integer sizeId);

    /**
     * 查询商品最高求购价格按规格分
     * @param goodsId id
     * @return list
     */
    List<SaleGoods> querySaleGoodsAmountOrderBySize(Integer goodsId);

    /**
     * 商品最最低售价  按规格分
     * @param goodsId goodsId
     * @return list
     */
    List<SaleGoods> queryBuyGoodsAmountOrderBySize(Integer goodsId);

    /**
     * 商品最低已成交价格
     * @param goodsId goodsId
     * @param sizeId sizeId
     * @return list
     */
    SaleGoods queryBuyOrderAmountByGoodsSize(Integer goodsId, Integer sizeId);

    List<OfferPurchaseDto> offerPurchaseList(@Param("goodsId")Integer goodsId);

    BuyOrderEntity buyerCreateOrder(BuyOrder buyOrder);

    BuyOrderEntity offerPurchaseCreateOrder(BuyOrder buyOrder);

    BuyOrderEntity selectLatestBuyOrder(Integer goodsId);

    /**
     * 购买订单支付回调(返回true代表成功，false代表失败)
     * @param orderId 订单ID
     * @param payType 支付类型：0：保证金支付；1：订单金额支付/保证金以外的金额支付
     * @param payChannel 支付渠道 1：支付宝 2：微信
     * @return
     */
    Boolean buyOrderPayRedirect(Integer orderId,Integer payType, Integer payChannel);

    /**
     * 不买了（取消订单）
     * @param orderId 订单ID
     * @return
     */
    Boolean cancelBuyOrder(Integer orderId);

    /**
     * 删除订单
     * @param orderId 订单ID
     * @return
     */
    Boolean delBuyOrder(Integer orderId);

    /**
     * 发货
     * @param orderId 订单ID
     * @param expressNo 快递单号
     * @return
     */
    Boolean deliverGoods(Integer orderId,String expressNo);

    Page<BuyOrderEntity> queryMyBuyOrder(String pageNo, String pageSize, Long userId, List orderStatusList);

    BuyOrderEntity detail(Integer buyOrderId);

    GoodsOfferPurchase getGoodsOfferPurchase(Integer goodsId, Integer sizeId);

    /**
     * 签收订单
     * @param orderId 订单ID
     * @return
     */
    Boolean signOrder(Integer orderId);
}


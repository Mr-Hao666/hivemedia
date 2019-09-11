package cn.hivemedia.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.entity.SaleOrderEntity;
import cn.hivemedia.entity.model.OrderGoods;
import cn.hivemedia.entity.model.SaleOrderQueryDto;
import cn.hivemedia.entity.model.SellOrder;

import java.util.List;
import java.util.Map;

/**
 * 出售订单信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
public interface SaleOrderService extends IService<SaleOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    SaleOrderEntity createSaleOrder(SellOrder sellOrder);

    /**
     * 我的售出
     *
     * @param userId
     * @return
     */
    Page<SaleOrderEntity> queryMySaleOrder(String pageNo, String pageSize, Long userId, List orderStatusList);

    /**
     * 购买-立即购买列表（现货上架和预售两种类型）
     *
     * @param goodsId
     * @return
     */
    List<SaleOrderQueryDto> purchaseList(Integer goodsId);

    SaleOrderEntity detail(Integer saleOrderId);

    /**
     * @param goodsId
     * @param sizeId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<OrderGoods> queryListByGoodsId(Integer goodsId, Integer sizeId, Integer pageNo, Integer pageSize, Integer orderStatus);

    /**
     * 全部交易
     *
     * @param goodsId
     * @param sizeId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<OrderGoods> queryListAllByGoodsId(Integer goodsId, Integer sizeId, Integer pageNo, Integer pageSize);

    /**
     * 出售订单保证金支付回调(返回true代表成功，false代表失败)
     *
     * @param orderId 订单ID
     * @param payChannel 支付渠道
     * @return
     */
    Boolean saleOrderPayRedirect(Integer orderId, Integer payChannel);

    /**
     * 不卖了（取消订单）
     *
     * @param orderId 订单ID
     * @return
     */
    Boolean cancelSaleOrder(Integer orderId);

    /**
     * * 发货
     * @param orderId 订单ID
     * @param returnConsigneeAddress
     * @param returnConsigneePhone
     * @param returnConsigneeName
     * @return
     */
    Boolean deliverGoods(Integer orderId, String returnConsigneeAddress, String returnConsigneePhone, String returnConsigneeName,String expressNo);

    /**
     * 删除订单
     *
     * @param orderId 订单ID
     * @return
     */
    Boolean delSaleOrder(Integer orderId);


}
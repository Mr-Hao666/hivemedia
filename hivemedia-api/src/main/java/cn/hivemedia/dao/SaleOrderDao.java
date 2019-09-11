package cn.hivemedia.dao;

import cn.hivemedia.entity.SaleOrderEntity;
import cn.hivemedia.entity.model.OrderGoods;
import cn.hivemedia.entity.model.SaleOrderQueryDto;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.models.auth.In;
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

    /**
     * 购买-立即购买列表（现货上架和预售两种类型）
     * @param goodsId
     * @return
     */
    List<SaleOrderQueryDto> purchaseList(@Param("goodsId")Integer goodsId);


    List<OrderGoods> queryListByGoodsId(Page<OrderGoods> page, @Param("goodsId")Integer goodsId, @Param("sizeId")Integer sizeId, @Param("orderStatus")Integer orderStatus);

    List<OrderGoods> queryListAllByGoodsId(Page<OrderGoods> page, @Param("goodsId")Integer goodsId , @Param("sizeId")Integer sizeId);

    /**
     *
     * @param amountTotal
     * @param goodsId
     * @param sizeId
     * @return
     */
    List<SaleOrderEntity> selectPriceOrdersBiMinPrice(@Param("amountTotal")BigDecimal amountTotal, @Param("goodsId")Integer goodsId , @Param("sizeId")Integer sizeId);

    List<SaleOrderEntity> selectByPurchasePrice(@Param("amountTotal")BigDecimal amountTotal, @Param("goodsId")Integer goodsId , @Param("sizeId")Integer sizeId);

    List<SaleOrderEntity> selectSaleOrderList(@Param("goodsId")Integer goodsId , @Param("sizeId")Integer sizeId);

}

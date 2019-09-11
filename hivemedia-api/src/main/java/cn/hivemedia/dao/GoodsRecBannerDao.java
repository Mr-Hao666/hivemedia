package cn.hivemedia.dao;

import cn.hivemedia.entity.GoodsRecBannerEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品推荐banner表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:16
 */
public interface GoodsRecBannerDao extends BaseMapper<GoodsRecBannerEntity> {

    List<GoodsRecBannerEntity> getList(@Param("size") Integer size);

}

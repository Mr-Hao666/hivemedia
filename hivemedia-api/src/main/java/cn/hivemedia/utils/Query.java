package cn.hivemedia.utils;

import com.baomidou.mybatisplus.plugins.Page;
import cn.hivemedia.common.xss.SQLFilter;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ZengXiong
 * @Description mybatis分页
 * @Date 2018/11/22 18:42
 */
public class Query<T> extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    /**
     * mybatis-plus分页参数
     */
    private Page<T> page;
    /**
     * 当前页码
     */
    private int currPage = 1;
    /**
     * 每页条数
     */
    private int pageSize = 10;

    public Query(Map<String, Object> params) {
        this.putAll(params);

        //分页参数
        if (params.get("pageNo") != null) {
            currPage = Integer.parseInt(params.get("pageNo").toString());
        }
        if (params.get("pageSize") != null) {
            pageSize = Integer.parseInt(params.get("pageSize").toString());
        }
        int offset = (currPage - 1) * pageSize;
        this.put("offset", offset);
        this.put("pageNo", currPage);
        this.put("pageSize", pageSize);

        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String sidx = SQLFilter.sqlInject((String) params.get("sidx"));
        String order = SQLFilter.sqlInject((String) params.get("order"));
        this.put("sidx", sidx);
        this.put("order", order);

        //mybatis-plus分页
        this.page = new Page<>(currPage, pageSize);

        //排序
        if (StringUtils.isNotBlank(sidx) && StringUtils.isNotBlank(order)) {
            this.page.setOrderByField(sidx);
            this.page.setAsc("ASC".equalsIgnoreCase(order));

            params.put("sidx", sidx);
            params.put("order", "ASC".equalsIgnoreCase(order) ? "ASC" : order);
        }
        params.put("offset", offset);
        params.put("pageSize", pageSize);
    }

    public Page<T> getPage() {
        return page;
    }

    public int getCurrPage() {
        return currPage;
    }

    public int getPageSize() {
        return pageSize;
    }
}

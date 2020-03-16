package cn.honghuroad.common.utils.excel;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author 杨浩
 * @create 2019-09-12 18:15
 **/
@Data
public class SimpleExportParameter {

    private String fileName = "";
    private String titleEn = "";
    private String title = "";
    private String[] fieldsId;
    private String[] fieldsName;
    private String[] widths;
    private int startIndex = 2;

    private List<Map<String, Object>> dataList;


}

package cn.hivemedia.common.utils.excel;

import cn.hivemedia.common.utils.DateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 杨浩
 * @create 2019-09-12 17:54
 **/
public class ExcelUtil {
    /**
     * excel文件得后缀 = .xls
     */
    public static final String EXCEL_FILE_SUFFIX = ".xls";

    /**
     * 默认的excel列宽=250
     */
    public static final int DEFAULT_COLUMN_WIDTH = 250;

    public List<Map<String, Object>> readSimple(Sheet wsheet, int startIndex, String[] fields) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Row row : wsheet) {
            // 从数据行开始读
            if (row.getRowNum() >= startIndex) {
                Map<String, Object> rowMap = new HashMap<>();
                for (Cell cell : row) {
                    // fields代表的列以后的数据忽略
                    if (cell.getColumnIndex() < fields.length) {
                        String key = fields[cell.getColumnIndex()];
                        Object value = getValue(cell);
                        rowMap.put(key, value);
                    }
                }
                list.add(rowMap);
            }
        }
        return list;
    }

    public static Object getValue(Cell cell) {
        Object value = null;
        DecimalFormat decimalFormat = new DecimalFormat("#.####");
        switch (cell.getCellType()) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    value = DateUtils.format(cell.getDateCellValue(), DateUtils.DATE_PATTERN);
                } else {
                    value = decimalFormat.format(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            default:
                value = cell.getStringCellValue();
        }
        return value;
    }
}

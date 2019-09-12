package cn.hivemedia.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 杨浩
 * @create 2019-09-12 14:09
 **/
@Slf4j
public class ExportExcelUtil {
    // 2007 版本以上 最大支持1048576行
    public final static String EXCEL_FILE_2007 = "2007";
    // 2003 版本 最大支持65536 行
    public final static String EXCEL_FILE_2003 = "2003";

    // 声明通用变量
    private XSSFWorkbook workbook;

    // 统一写出的开头样式
    private XSSFCellStyle titleStyle;

    private XSSFCellStyle contextStyle;

    private static String timePattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * <p>
     * 导出带有头部标题行的Excel<br>
     * 时间格式默认：yyyy-MM-dd HH:mm:ss<br>
     * <p/>
     */
    public static <T> void exportExcelFile(String fileName, String title, String[] headers, String[] fileds, Collection<T> dataset, HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Dispostion", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xlsx");
            exportExcel2007(title, headers, fileds, dataset, response.getOutputStream(), timePattern);
        } catch (Exception e) {
            log.error("导出异常", e);
            throw new RuntimeException("导出异常");
        }
    }

    /**
     * 通过excel导出方法，利用反射机制遍历对象的所有字段，将数据写入excel文件中
     * 此版本生成2007以上版本的文件（文件后缀：xlsx）
     */
    private static <T> void exportExcel2007(String title, String[] headers, String[] fields, Collection<T> dataset, OutputStream out, String pattern) {

        // 声明一个工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认类宽度为15个字节
        sheet.setDefaultColumnWidth(20);
        // 生成设置一个标题样式
        XSSFCellStyle titleStyle = workbook.createCellStyle();
        // 设置这些样式
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        // 生成一个标题字体
        XSSFFont titleFont = workbook.createFont();
        titleFont.setFontName("宋体");
        titleFont.setFontHeightInPoints((short) 15);
        titleFont.setBold(true);//加粗
        // 把字体应用到当前的样式
        titleStyle.setFont(titleFont);
        // 生成并设置内容样式
        XSSFCellStyle contentStyle = workbook.createCellStyle();
        contentStyle.setAlignment(HorizontalAlignment.CENTER);

        // 生成另一个内容字体
        XSSFFont contentFont = workbook.createFont();
        // 把字体应用到当前的样式
        contentFont.setFontName("宋体");
        contentFont.setFontHeightInPoints((short) 12);
        contentStyle.setFont(contentFont);
        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        XSSFCell cellHeader;
        for (int i = 0; i < headers.length; i++) {
            cellHeader = row.createCell(i);
            cellHeader.setCellStyle(titleStyle);
            cellHeader.setCellValue(new XSSFRichTextString(headers[i]));
        }
        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        T t;
        XSSFRichTextString richTextString;
        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
        Matcher matcher;
        String fieldName;
        String getMethodName;
        XSSFCell cell;
        Class tCls;
        Method getMethod;
        Object value;
        String textValue;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            t = it.next();
            for (int i = 0; i < fields.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(contentStyle);
                fieldName = fields[i];
                getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                try {
                    tCls = t.getClass();
                    getMethod = tCls.getMethod(getMethodName, new Class[]{});
                    value = getMethod.invoke(t, new Object[]{});
                    // 判断值得类型后进行强制类型转换
                    textValue = null;
                    if (value instanceof Integer) {
                        cell.setCellValue((Integer) value);
                    } else if (value instanceof Float) {
                        textValue = String.valueOf((Float) value);
                        cell.setCellValue(textValue);
                    } else if (value instanceof Double) {
                        textValue = String.valueOf((Double) value);
                        cell.setCellValue(textValue);
                    } else if (value instanceof Boolean) {
                        textValue = "是";
                        if (!(Boolean) value) {
                            textValue = "否";
                        }
                    } else if (value instanceof Long) {
                        cell.setCellValue((Long) value);
                    } else if (value instanceof Date) {
                        textValue = sdf.format((Date) value);
                    } else {
                        // 其他数据类型都当作字符串简单处理
                        if (value != null) {
                            textValue = value.toString();
                        }
                    }
                    if (textValue != null) {
                        matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            // 是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            richTextString = new XSSFRichTextString(textValue);
                            cell.setCellValue(richTextString);
                        }
                    }
                } catch (Exception e) {
                    log.error("导出异常：", e);
                    throw new RuntimeException("导出异常");
                }
            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
        } finally {
            // 清理资源
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
    }
}

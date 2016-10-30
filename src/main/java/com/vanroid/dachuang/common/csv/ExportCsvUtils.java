package com.vanroid.dachuang.common.csv;


import au.com.bytecode.opencsv.CSVWriter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * 通用csv导出类
 *
 * @author arthur.dy.lee
 * @since 2016.08.28
 */
public class ExportCsvUtils {
    private static final Logger logger = LoggerFactory.getLogger(ExportCsvUtils.class);

    /**
     * 导出Csv
     *
     * @param csvName  要导出的excel名称
     * @param list     要导出的数据集合
     * @param fieldMap 中英文字段对应Map,即要导出的excel表头
     * @param response 使用response可以导出到浏览器
     * @return
     */
    public static <T> void export(String csvName, List<T> list, LinkedHashMap<String, String> fieldMap,
                                  HttpServletResponse response) {

        // 设置默认文件名为当前时间：年月日时分秒
        if (StringUtils.isBlank(csvName)) {
            csvName = new SimpleDateFormat("yyyy-MM-dd hhmmss").format(new Date()).toString();
        } else {
            csvName = csvName + new SimpleDateFormat("_yyyy-MM-dd hhmmss").format(new Date()).toString();
        }

        try {
            // 设置response头信息
            response.reset(); // 清空输出流
            response.setContentType("APPLICATION/OCTET-STREAM"); // 定义输出类型
            csvName = new String(csvName.getBytes("gbk"), "ISO-8859-1");
            response.setHeader("Content-disposition", "attachment;  filename=" + csvName + ".csv");
            response.setCharacterEncoding("gbk");

        } catch (UnsupportedEncodingException e1) {
            logger.info(e1.getMessage());
        }

        try {
            CSVWriter writer = new CSVWriter(response.getWriter());
            // 组装文件并输出
            fillCsv(writer, list, fieldMap);

            writer.flush();
            writer.close();
        } catch (Exception e) {
            logger.info("导出Csv失败！");
            logger.error(e.getMessage());
        }
    }

    /**
     * 根据字段名获取字段对象
     *
     * @param fieldName 字段名
     * @param clazz     包含该字段的类
     * @return 字段
     */
    private static Field getFieldByName(String fieldName, Class<?> clazz) {
        //logger.info("根据字段名获取字段对象:getFieldByName(), fieldName: "+fieldName);
        // 拿到本类的所有字段
        Field[] selfFields = clazz.getDeclaredFields();

        // 如果本类中存在该字段，则返回
        for (Field field : selfFields) {
            // 如果本类中存在该字段，则返回
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        // 否则，查看父类中是否存在此字段，如果有则返回
        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz != null && superClazz != Object.class) {
            // 递归
            return getFieldByName(fieldName, superClazz);
        }

        // 如果本类和父类都没有，则返回空
        return null;
    }

    /**
     * 根据字段名获取字段值
     *
     * @param fieldName 字段名
     * @param o         对象
     * @return 字段值
     * @throws Exception 异常
     */
    private static Object getFieldValueByName(String fieldName, Object o) throws Exception {

        //logger.info("根据字段名获取字段值:getFieldValueByName()");
        Object value = null;
        // 根据字段名得到字段对象
        Field field = getFieldByName(fieldName, o.getClass());

        // 如果该字段存在，则取出该字段的值
        if (field != null) {
            field.setAccessible(true);// 类中的成员变量为private,在类外边使用属性值，故必须进行此操作
            value = field.get(o);// 获取当前对象中当前Field的value
        } else {
            throw new Exception(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
        }
        logger.info("value: " + value);
        return value;
    }

    /**
     * 根据带路径或不带路径的属性名获取属性值,即接受简单属性名，
     * 如userName等，又接受带路径的属性名，如student.department.name等
     *
     * @param fieldNameSequence 带路径的属性名或简单属性名
     * @param o                 对象
     * @return 属性值
     * @throws Exception 异常
     */
    private static Object getFieldValueByNameSequence(String fieldNameSequence, Object o) throws Exception {
        //logger.info("根据带路径或不带路径的属性名获取属性值,即接受简单属性名:getFieldValueByNameSequence(): "+fieldNameSequence);
        Object value = null;

        // 将fieldNameSequence进行拆分
        String[] attributes = fieldNameSequence.split("\\.");
        if (attributes.length == 1) {
            value = getFieldValueByName(fieldNameSequence, o);
        } else {
            // 根据数组中第一个连接属性名获取连接属性对象，如student.department.name
            Object fieldObj = getFieldValueByName(attributes[0], o);
            // 截取除第一个属性名之后的路径
            String subFieldNameSequence = fieldNameSequence.substring(fieldNameSequence.indexOf(".") + 1);
            // 递归得到最终的属性对象的值
            value = getFieldValueByNameSequence(subFieldNameSequence, fieldObj);
        }
        return value;

    }

    /**
     * 向csv中填充数据
     *
     * @param writer   csv文件的名称
     * @param list     数据源
     * @param fieldMap 中英文字段对应关系的Map
     * @param style    表格中的格式
     * @throws Exception 异常
     */
    private static <T> void fillCsv(CSVWriter writer, List<T> list, LinkedHashMap<String, String> fieldMap) throws Exception {
        //logger.info("向csv file中填充数据");
        // 定义存放英文字段名和中文字段名的数组
        String[] enFields = new String[fieldMap.size()];
        String[] cnFields = new String[fieldMap.size()];

        // 填充数组
        int count = 0;
        for (Entry<String, String> entry : fieldMap.entrySet()) {
            enFields[count] = entry.getKey();
            cnFields[count] = entry.getValue();
            count++;
        }

        // 填充表头
        String[] includeHeaders = new String[count];
        for (int i = 0; i < cnFields.length; i++) {
            includeHeaders[i] = cnFields[i];
        }
        writer.writeNext(includeHeaders);


        // 填充内容
        String[] data = new String[count];
        for (int index = 0; index < list.size(); index++) {
            // 获取单个对象
            T item = list.get(index);
            for (int i = 0; i < enFields.length; i++) {
                Object objValue = getFieldValueByNameSequence(enFields[i], item);
                String fieldValue = objValue == null ? "" : objValue.toString();
                data[i] = fieldValue;
            }
            writer.writeNext(data);
        }

    }

}

package com.vanroid.dachuang.common.csv;

import au.com.bytecode.opencsv.CSVReader;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.Reflections;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by cgz on 16-10-30.
 */
public class ImportCSV {
    public static final Logger logger = LoggerFactory.getLogger(ImportCSV.class);

    /**
     * 头行数
     */
    private int headNum;


    /**
     * 文件中的数据
     */
    private List<String[]> csvDatas;


    public ImportCSV(Reader reader, int headerNum) {

        this.headNum = headerNum;
        try {
            CSVReader csvReader = new CSVReader(reader);

            csvDatas = csvReader.readAll();

            /*logger.debug("\n cell values:");
            for (String[] row : csvDatas) {
                for (String cell : row) {
                    logger.debug(cell);
                }
                logger.debug("\n");
            }*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public <E> List<E> getDataList(Class<E> cls) throws IllegalAccessException, InstantiationException {

        Field[] fields = cls.getDeclaredFields();
        Method[] methods = cls.getDeclaredMethods();

        List<Object[]> annotationList = Lists.newArrayList();

        for (Field field : fields) {
            ExcelField ef = field.getAnnotation(ExcelField.class);
            if (ef != null && (ef.type() == 0 || ef.type() == 2)) {
                annotationList.add(new Object[]{ef, field});
            }
        }

        for (Method method : methods) {
            ExcelField ef = method.getAnnotation(ExcelField.class);
            if (ef != null && (ef.type() == 0 || ef.type() == 2)) {
                annotationList.add(new Object[]{ef, method});
            }
        }
        Collections.sort(annotationList, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                return new Integer(((ExcelField) o1[0]).sort()).
                        compareTo(new Integer(((ExcelField) o2[0]).sort()));
            }
        });

        List<E> dataList = Lists.newArrayList();

        for (int i = headNum; i < csvDatas.size(); i++) {
            E e = cls.newInstance();

            int col = 0;
            String[] row = csvDatas.get(i);

            for (Object[] os : annotationList) {

                String cellValueStr = row[col++];
                Object cellValue = null;
                if (cellValueStr != null) {
                    ExcelField ef = (ExcelField) os[0];

                    // 对象字段的类型
                    Class<?> valueType = Class.class;
                    if (os[1] instanceof Field) {
                        valueType = ((Field) os[1]).getType();
                    } else if (os[1] instanceof Method) {
                        valueType = ((Method) os[1]).getReturnType();
                    }
                    try {
                        // 去除等号
                        if (cellValueStr.startsWith("=\"")) {
                            cellValueStr = StringUtils.substring(cellValueStr.toString(), 2);
                        } else if (cellValueStr.startsWith("=")) {
                            cellValueStr = StringUtils.substring(cellValueStr.toString(), 1);
                        }
                        if (ef.fieldType() != Class.class) {  // 优先使用客户自定义的类型
                            cellValue = ef.fieldType().getMethod("getValue", String.class).invoke(null, cellValueStr);
                        } else if (valueType == String.class) {
                            String s = String.valueOf(cellValueStr);
                            if (StringUtils.endsWith(s, ".0")) {
                                cellValue = StringUtils.substringBefore(s, ".0");
                            } else if (StringUtils.startsWith(s, "=\"")) {
                                cellValue = StringUtils.substring(s, 2);
                            } else if (StringUtils.startsWith(s, "=")) {
                                cellValue = StringUtils.substring(s, 1);
                            } else {
                                cellValue = String.valueOf(cellValueStr);
                            }
                        } else if (valueType == Integer.class) {
                            cellValue = Double.valueOf(cellValueStr).intValue();
                        } else if (valueType == Long.class) {
                            cellValue = Double.valueOf(cellValueStr).longValue();
                        } else if (valueType == Double.class) {
                            cellValue = Double.valueOf(cellValueStr);
                        } else if (valueType == Float.class) {
                            cellValue = Float.valueOf(cellValueStr);
                        } else if (valueType == Date.class) {
                            cellValue = DateUtils.parseDate(cellValueStr);
                        } else {
                            throw new RuntimeException("找不到类型返回值");
                        }
                    } catch (Exception ex) {
                        logger.info("Get cell value [" + i + "," + col + "] error: " + ex.toString());
                        cellValue = null;
                    }
                    logger.debug("cellValue:{}", cellValue);
                    // set value
                    if (os[1] instanceof Field) {
                        Reflections.invokeSetter(e, ((Field) os[1]).getName(), cellValue);
                    } else {
                        String methodName = ((Method) os[1]).getName();
                        if ("get".equals(methodName.substring(0, 3))) {
                            methodName = "set" + StringUtils.substringAfter(methodName, "get");
                        }
                        Reflections.invokeMethod(e, methodName, new Class[]{valueType}, new Object[]{cellValue});
                    }
                }
            }
            dataList.add(e);
        }

        return dataList;
    }

}

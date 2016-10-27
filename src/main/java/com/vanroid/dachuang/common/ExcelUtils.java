package com.vanroid.dachuang.common;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.Date;

/**
 * Created by cgz on 16-10-26.
 * Excel处理工具类
 */
public class ExcelUtils {


    public static String getStringCellValue(Row row, int colNum) {
        Cell cell = row.getCell(colNum);
        if (cell != null) {
            int cellType = cell.getCellType();
            if (cellType == Cell.CELL_TYPE_ERROR)
                return null;
            if (cellType == Cell.CELL_TYPE_NUMERIC) {
                return String.valueOf(cell.getNumericCellValue());
            }
            return cell.getStringCellValue();
        }
        return null;
    }

    public static Date getDateCellValue(Row row, int colNum) {
        Cell cell = row.getCell(colNum);
        if (cell != null) {
            return cell.getDateCellValue();
        }
        return null;
    }

    /**
     * 判断单元格是否为空
     *
     * @param cell
     * @return
     */
    public static boolean cellIsBank(Cell cell) {
        if (cell != null) {
            return "".equals(cell.getStringCellValue());
        }
        return true;
    }


}

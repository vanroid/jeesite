package com.vanroid.dachuang.common.csv;

import au.com.bytecode.opencsv.CSVReader;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by cgz on 16-10-30.
 */
public class ImportCsvUtils {

    public static List<String[]> getDatas(String filePath) {
        Reader reader = null;
        List<String[]> datas = null;
        try {
            reader = Files.newReader(new File("/home/cgz/win7vm/终端交易统计_143_大创电子_201609.csv"), Charset.forName("GBK"));

            CSVReader csvReader = new CSVReader(reader);

            datas = csvReader.readAll();
            for (String[] row : datas) {
                for (String cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return datas;
    }
}

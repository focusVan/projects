package com.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * focus Create in 18:34 2019/4/18
 */
public class Excel2SQL {
    public static void main(String[] args) {
        String path = "C:\\\\Users\\\\madhouse\\\\Desktop\\\\brand.xlsx";
        InputStream inputStream = null;
        try {
            Map<String, String> map = new ConcurrentHashMap<>();
            inputStream = new FileInputStream(path);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            StringBuffer buffer = new StringBuffer();
            Set<String> set = new HashSet<>();
            for(Row row : sheet) {
                String brand = "";
                String model = "";
                String ua = "";
                int i = 0;
                for(Cell cell : row) {
                    if(i == 0) {
                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
                            ua = String.valueOf((int)cell.getNumericCellValue()).toUpperCase().trim();
                        }
                        if (cell.getCellTypeEnum().equals(CellType.STRING)) {
                            ua = cell.getStringCellValue().toUpperCase().trim();
                        }
                        if (set.contains(ua)) {
                            brand = "";
                            model = "";
                            continue;
                        } else {
                            set.add(ua);
                        }
                        i++;
                    } else if(i == 1) {
                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
                            brand = String.valueOf((int)cell.getNumericCellValue());
                        }
                        if (cell.getCellTypeEnum().equals(CellType.STRING)) {
                            brand = cell.getStringCellValue();
                        }
                        i++;
                    } else if(i == 2){
                        if (cell.getCellTypeEnum().equals(CellType.NUMERIC)) {
                            model = String.valueOf((int)cell.getNumericCellValue());
                        }
                        if (cell.getCellTypeEnum().equals(CellType.STRING)) {
                            model = cell.getStringCellValue();
                        }
                        i++;
                        continue;
                    }
                }
                if("".equals(brand) && "".equals(model)) {
                    continue;
                }
                buffer.append("INSERT INTO `mahad`.`md_phone_fin`(`model_name`,`brand_micro`,`brand_name_model`,`brand_name`,`pb_id`) VALUES(").append("\'").append(model).append("\',\'").append(brand).append("\',\'").append(brand).append(" ").append(model).append("\',\'").append(brand).append("\',\'").append(ua).append("\'").append(")").append(";").append("\n");
            }
            System.out.println(set.size());
            System.out.println(buffer.toString());
        } catch (Exception e) {
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
    }
}

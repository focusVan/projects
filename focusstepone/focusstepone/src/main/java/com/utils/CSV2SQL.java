package com.utils;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * focus Create in 17:27 2019/4/18
 */
public class CSV2SQL {
    public static void main(String[] args) {
        String filename = "C:\\\\Users\\\\madhouse\\\\Desktop\\\\brand-0417.csv";
        File file = new File(filename);
        file.setReadable(true);
        BufferedReader br = null;
        try {
            List list = Arrays.asList(new String[]{"2","1","3"});
            Collections.sort(list);
            FileReader fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] array = line.split(",");
                if (array.length < 3) {
                    System.out.println(line);
                    continue;
                }
                String brand = array[0] == null ? array[0] : "";
                String model = array[1] == null ? array[1] : "";
                stringBuilder.append("INSERT INTO `mahad`.`md_phone_in`(`model_name`,`brand_micro`,`brand_name_model`,`brand_name`) VALUES(").append("\'").append(model).append("\',\'").append(brand).append("\',\'").append(brand).append(" ").append(model).append("\',\'").append(brand).append("\';").append("\n");
            }
            System.out.println(stringBuilder.toString());
        } catch (Exception e){
            System.out.println(ExceptionUtils.getStackTrace(e));
        }
    }
}

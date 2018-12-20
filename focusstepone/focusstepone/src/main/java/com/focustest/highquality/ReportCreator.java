package com.focustest.highquality;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * focus Create in 17:39 2018/12/10
 */
public class ReportCreator {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = null;
        BufferedReader end = null;
        BufferedReader start = null;
        try {
//            String fileName = ReportCreator.class.getClassLoader().getResource("project.txt").getPath();
//            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
//            String line = null;
//            while ((line = bufferedReader.readLine()) != null) {
//                    String[] array = line.split("::");
//                    String sql = "UPDATE md_project SET startDate = DATE_FORMAT('" + array[1] + "','%Y-%m-%d'), endDate = DATE_FORMAT('" + array[2] + "','%Y-%m-%d') WHERE id = " + array[0] + ";";
//                    System.out.println(sql);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
            List list = new ArrayList();
            list = new LinkedList();
            list = new Vector();

            String fileNameStart = ReportCreator.class.getClassLoader().getResource("campaign-start.txt").getPath();
            start = new BufferedReader(new InputStreamReader(new FileInputStream(fileNameStart)));

            String startLine = null;
            Map<String, String> startMap = new HashMap<>();
            startMap = new LinkedHashMap<>();
            startMap = new TreeMap<>();

            Set set = new HashSet();
            set = new LinkedHashSet();
            set = new TreeSet();
            while ((startLine = start.readLine()) != null) {
                String[] array = startLine.split("::");
                startMap.put(array[0], array[1]);
            }

            String fileNameEnd = ReportCreator.class.getClassLoader().getResource("campaign-end.txt").getPath();
            String endLine = null;
            Map<String, String> endMap = new HashMap<>();
            end = new BufferedReader(new InputStreamReader(new FileInputStream(fileNameEnd)));
            while ((endLine = end.readLine()) != null) {
                String[] array = endLine.split("::");
                endMap.put(array[0], array[1]);
            }

            for (Map.Entry<String, String> entry : startMap.entrySet()) {
                String sql = "UPDATE md_campaign SET beginDate = DATE_FORMAT('" + entry.getValue() + "','%Y-%m-%d'), endDate = DATE_FORMAT('" + endMap.get(entry.getKey()) + "','%Y-%m-%d') WHERE id = " + entry.getKey() + ";";
                System.out.println(sql);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


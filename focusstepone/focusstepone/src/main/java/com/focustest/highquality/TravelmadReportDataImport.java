package com.focustest.highquality;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * focus Create in 11:11 2018/12/17
 */
public class TravelmadReportDataImport {
    public static void main(String[] args) throws Exception {
        String path = TravelmadReportDataImport.class.getClassLoader().getResource("datas.txt").getPath();
        File file = new File(path);
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String line = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Set<Integer> projectIDSet = new HashSet();
        Set<Integer> campaignIDSet = new HashSet<>();
        Set<Integer> adsapceIDSet = new HashSet<>();
        Set<Integer> materialIDSet = new HashSet<>();
        Map<String, Integer> impMap = new HashMap();
        Map<String, Integer> clkMap = new HashMap<>();
        Map<String, Double> costMap = new HashMap<>();
        Map<String, Integer> mediaImpMap = new HashMap<>();
        Map<String, Integer> mediaClkMap = new HashMap<>();
        Set<String> repeatedDatas = new HashSet<>();
        while( (line = bufferedReader.readLine()) != null) {
            String[] datas = line.split(";");
            String key = datas[0] + ";" + datas[1] + ";" + datas[2] + ";" + datas[3] + ";" + datas[4] + ";" + datas[5];
            String mediaKey = datas[0] + ";" + datas[3] + ";" + datas[4];

            adsapceIDSet.add(Integer.parseInt(datas[4].trim()));
            materialIDSet.add(Integer.parseInt(datas[5].trim()));
            if (impMap.get(key) == null) {
                impMap.put(key, Integer.parseInt(datas[6].trim()));
            } else {
                repeatedDatas.add(key);
            }

            if (clkMap.get(key) == null) {
                clkMap.put(key, Integer.parseInt(datas[7].trim()));
            }
            if (costMap.get(key) == null) {
                costMap.put(key, Double.parseDouble(datas[8].trim()));
            }


            if (mediaImpMap.get(mediaKey) == null) {
                mediaImpMap.put(mediaKey, Integer.parseInt(datas[6].trim()));
            }

            if (mediaClkMap.get(mediaKey) == null) {
                mediaClkMap.put(mediaKey, Integer.parseInt(datas[7].trim()));
            }
        }

        for (Map.Entry<String, Integer> entry : impMap.entrySet()) {
            StringBuffer sql = new StringBuffer();
            String[] datas = entry.getKey().split(";");
            String key = entry.getKey();
            Date date = sdf.parse(datas[0]);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            long timestamp = cal.getTimeInMillis()/1000 + 9 * 3600;
            String projectID = datas[1].trim();
            projectIDSet.add(Integer.parseInt(projectID));
            String campaignID = datas[2].trim();
            campaignIDSet.add(Integer.parseInt(campaignID));
            String sspMediaID = datas[3].trim();
            String sspAdspaceID = datas[4].trim();
            String materialID = datas[5].trim();
            Integer imp = impMap.get(key);
            Integer clk = clkMap.get(key);
            Double cost = costMap.get(key);
            sql.append("INSERT INTO tvl_report_campaign(`timestamp`,`project_id`,`campaign_id`,`material_id`,`media_id`,`adspace_id`,`bids`,`wins`,`imps`,`clks`,`vimps`,`vclks`,`cost`,`create_user`,`create_timestamp`,`update_user`,`update_timestamp`) VALUES (")
                    .append(timestamp).append(",")
                    .append(projectID).append(",")
                    .append(campaignID).append(",")
                    .append(materialID).append(",")
                    .append(sspMediaID).append(",")
                    .append(sspAdspaceID).append(",")
                    .append(Math.round(imp * 1.2)).append(",")
                    .append(Math.round(imp * 1.2)).append(",")
                    .append(imp).append(",")
                    .append(clk).append(",")
                    .append(imp).append(",")
                    .append(clk).append(",")
                    .append(Math.round(cost * 100000)).append(",")
                    .append(0).append(",")
                    .append(System.currentTimeMillis()/1000).append(",")
                    .append(0).append(",")
                    .append(System.currentTimeMillis()/1000).append(");");
            System.out.println(sql.toString());
        }

        for (Map.Entry<String, Integer> entry : mediaImpMap.entrySet()) {
/*            String key = entry.getKey();
            String[] datas = key.split(";");
            Date date = sdf.parse(datas[0]);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            long timestamp = cal.getTimeInMillis()/1000 + 9 * 3600;
            StringBuffer sql = new StringBuffer();
            String meidaId = datas[1];
            String adspaceId = datas[2];
            Integer imp = mediaImpMap.get(key);
            Integer clk = mediaClkMap.get(key);

            sql.append("INSERT INTO tvl_report_media(`timestamp`,`media_id`,`adspace_id`,`reqs`,`errs`,`bids`,`wins`,`imps`,`clks`,`vimps`,`vclks`,`income`,`create_user`,`create_timestamp`,`update_user`,`update_timestamp`) VALUES (")
                    .append(timestamp).append(",")
                    .append(meidaId).append(",")
                    .append(adspaceId).append(",")
                    .append(Math.round(imp * 1.5)).append(",")
                    .append(0).append(",")
                    .append(Math.round(imp * 1.2)).append(",")
                    .append(Math.round(imp * 1.2)).append(",")
                    .append(imp).append(",")
                    .append(imp).append(",")
                    .append(clk).append(",")
                    .append(clk).append(",")
                    .append(0).append(",")
                    .append(0).append(",")
                    .append(System.currentTimeMillis()/1000).append(",")
                    .append(0).append(",")
                    .append(System.currentTimeMillis()/1000).append(");");
            System.out.println(sql.toString());*/
        }

        StringBuffer adspaceBuf = new StringBuffer();
        for (Integer i : adsapceIDSet) {
            adspaceBuf.append(i).append(",");
        }
        System.out.println("adspace: " + adspaceBuf.toString());

        StringBuffer materialBuf = new StringBuffer();
        for (Integer i : materialIDSet) {
            materialBuf.append(i).append(",");
        }
        System.out.println("material: " + materialBuf.toString());

        StringBuffer projectBuf = new StringBuffer();
        for (Integer i : projectIDSet) {
            projectBuf.append(i).append(",");
        }
        System.out.println("project: " + projectBuf.toString());

        StringBuffer campaignBuf = new StringBuffer();
        for (Integer i : campaignIDSet) {
            campaignBuf.append(i).append(",");
        }
        System.out.println("campaign: " + campaignBuf.toString());

        for (String key : repeatedDatas) {
            System.out.println(key);
        }
    }
}

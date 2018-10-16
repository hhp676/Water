package com.hongguaninfo.hgdf.wa.web;

/**
 * Created by HHP on 2018/9/27.
 */

import com.hongguaninfo.hgdf.wa.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelService {

    private static HSSFWorkbook workbook = null;

    public static void main(String[] args) {
        String path = "D:\\test\\";
        File file = new File(path);
        // 获得该文件夹内的所有文件
        File[] array = file.listFiles();
        doExcel(array, path);
    }

    public static void getSumExcel(String path){
        File file = new File(path);
        // 获得该文件夹内的所有文件
        File[] array = file.listFiles();
        doExcel(array, path);
    }

    /**
     * @Title: doExcel
     * @Description:   执行合并
     * @param @param array
     * @return void
     */
    private static void doExcel(File[] array, String path){
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for(int i=0;i<array.length;i++){
            System.out.println(path + array[i].getName());
            List<Map<String, String>> listRow = parseExcel(path + array[i].getName());
            list.addAll(listRow);
        }
        try {
            if(!fileExist("D:\\汇总.xls")){
                createExcel("D:\\汇总.xls", "汇总结果", null);
            }
            writeToExcel("D:\\汇总.xls", "汇总结果", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断文件是否存在.
     * @param fileDir  文件路径
     * @return
     */
    public static boolean fileExist(String fileDir){
        boolean flag = false;
        File file = new File(fileDir);
        flag = file.exists();
        return flag;
    }

    /**
     * 解析文档
     * @param filePath 文档路径
     */
    public static List<Map<String, String>> parseExcel(String filePath) {
        Map<String, String> mapRow = new HashMap<String, String>();
        List<Map<String, String>> listRow = new ArrayList<Map<String, String>>();
        InputStream ins = null;
        try {
            ins = new FileInputStream(new File(filePath));
            HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(ins);
            Map<Integer, List<String>> map = ExcelUtil.getSheetRowDatas(wb, 0);
            for(List<String> row : map.values()) {
                for(int i=0;i<row.size();i++){
                    mapRow.put(String.valueOf(i), String.valueOf(row.get(i)));
                }
                listRow.add(mapRow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(ins != null) {
                    ins.close();
                }
            } catch (IOException e) {
            }
        }
        return listRow;
    }

    /**
     * 创建新excel.
     * @param fileDir  excel的路径
     * @param sheetName 要创建的表格索引
     * @param titleRow excel的第一行即表格头
     */
    public static void createExcel(String fileDir,String sheetName,String titleRow[]){
        //创建workbook
        workbook = new HSSFWorkbook();
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        workbook.createSheet(sheetName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileDir);
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 往excel中写入
     * @param fileDir    文件路径
     * @param sheetName  表格索引
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public static void writeToExcel(String fileDir,String sheetName,List<Map<String, String>> mapList) throws Exception{
        //创建workbook
        File file = new File(fileDir);
        try {
            workbook = new HSSFWorkbook(new FileInputStream(file));
//            workbook.createSheet(sheetName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //流
        FileOutputStream out = null;
        HSSFSheet sheet = workbook.getSheet(sheetName);
        // 获取表格的总行数
        // int rowCount = sheet.getLastRowNum() + 1; // 需要加一
        // 获取表头的列数
        int columnCount = sheet.getRow(0).getLastCellNum();
        try {
            // 获得表头行对象
            HSSFRow titleRow = sheet.getRow(0);
            if(titleRow!=null){
                for(int rowId=0;rowId<mapList.size();rowId++){
                    Map<String, String> map = mapList.get(rowId);
                    HSSFRow newRow=sheet.createRow(rowId+1);
                    for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {  //遍历表头
                        String mapKey = titleRow.getCell(columnIndex).toString().trim().toString().trim();
                        HSSFCell cell = newRow.createCell(columnIndex);
                        cell.setCellValue(map.get(String.valueOf(columnIndex))==null ? null : map.get(String.valueOf(columnIndex)).toString());
//                        cell.setCellValue(map.get("+columnIndex+")==null ? null : map.get(mapKey).toString());
                    }
                }
            }

            out = new FileOutputStream(fileDir);
            workbook.write(out);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

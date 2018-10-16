package com.hongguaninfo.hgdf.wa.utils;

import com.alibaba.fastjson.JSON;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HHP on 2018/9/14.
 */
public class ExcelUtil {
    private static final Log LOG = LogFactory.getLog(ExcelUtil.class);

    public static String getCellValue(HSSFCell cell) throws Exception {
        String strCell = "";
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf((long)cell.getNumericCellValue());
                break;
//		case HSSFCell.CELL_TYPE_BOOLEAN:
//			strCell = String.valueOf(cell.getBooleanCellValue());
//			break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
//			int column = cell.getColumnIndex()+1;
//			int row = cell.getRowIndex();
//			throw new Exception("单元格文本必须是文本格式：第"+ row +"行，第"+ column +"列。");
                strCell = "";
                break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }

        return strCell;
    }

    /**
     * 获取一个工作簿
     * @param in
     * @return
     * @throws IOException
     */
    public static HSSFWorkbook getHSSFWorkbook(InputStream in) throws IOException {
        return new HSSFWorkbook(new POIFSFileSystem(in), true);
    }

    /**
     * @param wb ： 工作簿
     * @param sheetAt ： 第一个sheet页,从0开始
     * @return 将数据封装到Map中 ：数据从rowNum=1开始到lastRowNum(rowNum=0为标题不解析)
     */
    public static Map<Integer, List<String>> getSheetRowDatas(HSSFWorkbook wb, int sheetAt) {
        Map<Integer, List<String>> map = new HashMap<>();
        HSSFSheet sheet = wb.getSheetAt(sheetAt);
        Row row = null;
        // 从第二行开始：rowNum为1
        try {
            int lastCellNum = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                List<String> list = new ArrayList<>();
                row = sheet.getRow(i);
                if (row == null || row.getLastCellNum() != lastCellNum) {
                    LOG.warn("获取第" + (sheetAt + 1) + "页第" + (i + 1) + "行的数据内容为空！");
                    continue;
                }
                for(int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (isMergedRegion(sheet, i, cell.getColumnIndex())) {
                        list.add(getMergedRegionValue(sheet, i, cell.getColumnIndex()));
                    } else {
                        list.add(getCellValue((HSSFCell) cell));
                    }
                }
                // 从0计数
                map.put(i - 1, list);
                LOG.info("获取第" + (sheetAt + 1) + "页第" + (i + 1) + "行的数据个数为 " + list.size() + ",内容为：" + JSON.toJSONString(list));
            }
        } catch (Exception e) {
            LOG.error("ExcelUtil -- > getSheetData -- > 获取第" + (sheetAt + 1) + "页数据异常！", e);
        }
        return map;
    }

    /**
     * 判断是否为合并项
     *
     * @param sheet
     * @return
     */
    public static boolean isMergedRegion(Sheet sheet, int rowNum, int columnIndex) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (rowNum >= firstRow && rowNum <= lastRow) {
                if (columnIndex >= firstColumn && columnIndex <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取合并项内容
     *
     * @param sheet
     * @return
     */
    public static String getMergedRegionValue(Sheet sheet, int rowNum, int columnIndex) throws Exception {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if (rowNum >= firstRow && rowNum <= lastRow) {
                if (columnIndex >= firstColumn && columnIndex <= lastColumn) {
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue((HSSFCell) fCell);
                }
            }
        }
        return null;
    }
}

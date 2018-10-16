package com.hongguaninfo.hgdf.adp.core.utils.view;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.hongguaninfo.hgdf.adp.core.utils.ResponseUtils;
import com.hongguaninfo.hgdf.core.utils.StringUtil;

/**
 * xls导出view
 * @author shichengqun
 * @version 创建时间：2017-11-22 下午1:30:29
 */
public abstract class ExcelXlsBaseView extends AbstractXlsView {

    /**
     * 新建row
     * @param sheet
     * @param row
     * @return
     */
    protected Row createRow(HSSFSheet sheet, int row){
        HSSFRow sheetRow = sheet.getRow(row);
        if (sheetRow == null) {
          sheetRow = sheet.createRow(row);
        }
        return sheetRow;
    }

    /**
     * 获得helper
     * @param workbook
     * @return
     */
    protected CreationHelper getHelper(Workbook workbook){
        return workbook.getCreationHelper();
    }


    /**
     * 获得日期style
     * @param workbook
     * @param dataFormatString  默认为 yy/mm/dd
     * @return
     */
    protected CellStyle getDateStyle(Workbook workbook, String dataFormatString){
        String dataFomStr = "";
        dataFomStr = (dataFormatString == null || dataFormatString.length() < 0) ? "yy/mm/dd" : dataFormatString;

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(getHelper(workbook).createDataFormat().getFormat(dataFomStr));

        return cellStyle;
    }

    /**
     * 设置style对齐方式
     * @param cellStyle
     * @param cell
     * @param halign
     * @param valign
     * @return
     */
    protected void setStyleAlign(CellStyle cellStyle, short halign, short valign){
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
    }

    /**
     * cellstyle边框设置
     * @param cellStyle
     * @param borderStyle
     * @param borderColor
     * @return
     */
    protected void setStyleBorder(CellStyle cellStyle, short borderStyle, short borderColor){
        //设置边框
        cellStyle.setBorderTop(borderStyle);
        cellStyle.setBorderBottom(borderStyle);
        cellStyle.setBorderLeft(borderStyle);
        cellStyle.setBorderRight(borderStyle);

        //设置边框颜色
        cellStyle.setTopBorderColor(borderColor);
        cellStyle.setBottomBorderColor(borderColor);
        cellStyle.setLeftBorderColor(borderColor);
        cellStyle.setRightBorderColor(borderColor);

    }

    /**
     * 设置字体及背景色
     * @param cellStyle
     * @param font
     * @param bgColr
     * @return
     */
    protected void setStyleColor(CellStyle cellStyle, Font font, short bgColr){
        cellStyle.setFont(font);
        cellStyle.setFillBackgroundColor(bgColr);
    }

    /**
     * 合并单元格
     * @param sheet
     * @param firstRow
     * @param lastRow
     * @param firstCol
     * @param lastCol
     */
    protected void mergeRegion(HSSFSheet sheet, int firstRow, int lastRow, int firstCol, int lastCol){
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }

    /**
     * 新建字体
     * @param workbook
     * @param height
     * @param name
     * @param bold
     * @return
     */
    protected Font createFont(Workbook workbook, short height, String name, short bold){
        Font font = workbook.createFont();
        font.setFontHeightInPoints(height);
        font.setFontName(name);
        font.setBoldweight(bold);
        return font;
    }
    
    
    
    
    /**
     * 设置默认的标题样式
     * @param workbook
     * @param row
     */
    protected void setDefalutTitleStyle(Workbook workbook,HSSFSheet sheet,int rowIndex,int colNum) {
        Font font = workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setFont(font);
        for (int i=0; i<colNum;i++) {
            getCell(sheet, rowIndex, i).setCellStyle(cellStyle);
        }
        
    }
    
    /**
     * 
     * @param cell
     * @param text
     * @param align CellStyle.ALIGN_RIGHT
     */
    protected void setText(HSSFCell cell, String text,CellStyle style) {
        cell.setCellStyle(style);
        setText(cell,text);
    }
    
    
    /**
     * 设置数值型单元格内容
     */
    protected void setNumber(HSSFCell cell, String text,CellStyle style) {
        cell.setCellStyle(style);
        setNumber(cell,text);
    }
    
    
    /**
     * 设置数值型单元格内容
     */
    protected void setNumber(HSSFCell cell, String text) {
        if ("null".equals(text)) {
            text = "";
        }
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellValue(text);
    }
    
  protected void setNumber2Decimal(HSSFCell cell, String text, CellStyle style) {
    cell.setCellStyle(style);
    BigDecimal value = new BigDecimal(text);
    value = value.setScale(2, RoundingMode.HALF_UP);
    cell.setCellValue(value.toString());
  }
    
    
    /**
     * 设置数值型单元格内容
     */
    protected void setInt(HSSFCell cell, Integer value,CellStyle style) {
        cell.setCellStyle(style);
        setInt(cell,value);
    }
    
    
    /**
     * 设置数值型单元格内容
     */
    protected void setInt(HSSFCell cell, Integer value) {
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellValue(value == null ? 0 : value.intValue());
    }
    /**
     * 设置金额型单元格内容
     * @param row
     * @param columnIndex
     * @param cellType
     * @param style
     * @param value
     */
    public void setMoney(HSSFCell cell, String value, CellStyle style){
        if ("null".equals(value)) {
            value = "";
        }
        double money = 0;
        try{
            money = new BigDecimal(StringUtil.defaultIfEmpty(value, "0")).doubleValue();
        }catch (Exception e) {
            e.printStackTrace();
        }
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00")); // 两位小数，只加了一个格式的自定义，反应到Excle上面为自定义的金额，其他格式类似
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        cell.setCellStyle(style);
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cell.setCellValue(money);
    }
    
    /**
     * 设置金额型单元格内容（格式化单位为万）
     * @param cell
     * @param value
     * @param style
     */
    public void setMoneyMillionUnits(HSSFCell cell, String value, CellStyle style){
      if ("null".equals(value)) {
        value = "";
      }
      value = (new BigDecimal(StringUtil.defaultIfEmpty(value, "0")).divide(new BigDecimal(10000))).toString();
      setMoney(cell, value, style);
    }
    
    /**
     * 合并单元格时，只留其中一个有内容，其他清空
     * firstRow <= lastRow ; firstCol <= firstCol
     * @param sheet
     * @param firstRow
     * @param lastRow
     * @param firstCol
     * @param lastCol
     */
    public void mergeRegionAndClear(HSSFSheet sheet, int firstRow, int lastRow, int firstCol, int lastCol){
        if(firstRow == lastRow){
            if(firstCol == lastCol){
                //就一个单元格不处理
            }else if(firstCol < lastCol){
                //第一行第一个单元格后面的处理
                for(int j = firstCol + 1; j <= lastCol; j++){
                    setText(getCell(sheet, firstRow, j), "");
                }
            }
        }else if(firstRow < lastRow){
            if(firstCol == lastCol){
                for(int i = firstRow + 1; i <= lastRow; i++){
                    setText(getCell(sheet, i, firstCol), "");
                }
            }else if(firstCol < lastCol){
                //第一行第一个单元格后面的处理
                for(int j = firstCol + 1; j <= lastCol; j++){
                    setText(getCell(sheet, firstRow, j), "");
                }
                //第二行以后的处理
                for(int i = firstRow + 1; i <= lastRow; i++){
                    for(int j = firstCol; j <= lastCol; j++){
                        setText(getCell(sheet, i, j), "");
                    }
                }
            }
        }
        
        
        mergeRegion(sheet, firstRow, lastRow, firstCol, lastCol);
    }
    
    /**
     * 从原AbstractExcelView复制getCell方法。
     * @param sheet sheet
     * @param row row
     * @param col col
     * @return cell
     */
    protected HSSFCell getCell(HSSFSheet sheet, int row, int col) {
        HSSFRow sheetRow = sheet.getRow(row);
        if (sheetRow == null) {
            sheetRow = sheet.createRow(row);
        }
        HSSFCell cell = sheetRow.getCell(col);
        if (cell == null) {
            cell = sheetRow.createCell(col);
        }
        return cell;
    }
    
    /**
     * 从原AbstractExcelView复制setText方法。
     * @param cell cell
     * @param text text
     */
    protected void setText(HSSFCell cell, String text) {
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(text);
    }
    
    /**
     * 修改导出的文件名为fileName
     * @param fileName
     * @param request
     * @param response
     * @return fileName
     */
    public void setExportFileName(String fileName,HttpServletRequest request,HttpServletResponse response){
        if(!StringUtil.isEmpty(fileName)){
            fileName = ResponseUtils.encodeFilename(fileName,request) ;
            response.setHeader("Content-disposition", "filename=" + fileName + ".xls");
        }
    }
    
    

}
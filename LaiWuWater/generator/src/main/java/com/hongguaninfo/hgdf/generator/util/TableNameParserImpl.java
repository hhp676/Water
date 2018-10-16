package com.hongguaninfo.hgdf.generator.util;

/**
 * 根据数据库表名称 组装包路径 等待扩展
 * 
 * @author henry
 * 
 */
public class TableNameParserImpl implements ITableNameParser {

    public String[] parseTable(String tableName, String packageName) {
        String[] returns = new String[2];
        if (packageName.equals("") || null == packageName) {
            returns[0] = "com.hongguaninfo";
        }
        returns[0] = packageName;
        returns[1] = "";
        return returns;
    }

}

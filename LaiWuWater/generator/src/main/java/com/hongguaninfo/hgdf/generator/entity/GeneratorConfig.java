package com.hongguaninfo.hgdf.generator.entity;

import com.hongguaninfo.hgdf.generator.core.base.BaseEntity;
import com.hongguaninfo.hgdf.generator.util.ITableNameParser;

public class GeneratorConfig extends BaseEntity {

    private String jdbcDriverName;
    private String jdbcDriverUrl;
    private String jdbcUserName;
    private String jdbcPassword;
    private String jdbcSchema;
    private String packageName;

    private ITableNameParser tableNameParser;
    private String outPath;
    
    private String author ;

    public String getJdbcDriverName() {
        return jdbcDriverName;
    }

    public void setJdbcDriverName(String jdbcDriverName) {
        this.jdbcDriverName = jdbcDriverName;
    }

    public String getJdbcDriverUrl() {
        return jdbcDriverUrl;
    }

    public void setJdbcDriverUrl(String jdbcDriverUrl) {
        this.jdbcDriverUrl = jdbcDriverUrl;
    }

    public String getJdbcUserName() {
        return jdbcUserName;
    }

    public void setJdbcUserName(String jdbcUserName) {
        this.jdbcUserName = jdbcUserName;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getJdbcSchema() {
        return jdbcSchema;
    }

    public void setJdbcSchema(String jdbcSchema) {
        this.jdbcSchema = jdbcSchema;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public ITableNameParser getTableNameParser() {
        return tableNameParser;
    }

    public void setTableNameParser(String tnParser) {
        String className = tnParser;
        Object obj;
        try {
            obj = Class.forName(className).newInstance();
            tableNameParser = (ITableNameParser) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTableNameParser(ITableNameParser tnParser) {
        tableNameParser = tnParser;
    }

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}

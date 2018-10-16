package com.hongguaninfo.hgdf.core.utils.props;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import com.hongguaninfo.hgdf.core.utils.ZipUtil;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * Property util
 * 
 */
public class PropertyUtils {

    public static final Log LOG = LogFactory.getLog(PropertyUtils.class);

    /**
     * get proerties of file
     * 
     * @param resName
     * 
     * @return
     */
    private static PropertyResourceBundle getProperties(String resName) {
        PropertyResourceBundle bundle = (PropertyResourceBundle) ResourceBundle.getBundle(resName);
        return bundle;
    }

    /**
     * get proerty value
     * 
     * @param resName
     * 
     * @return
     */
    public static String getPropertyValue(String resName, String proertyName) {
        PropertyResourceBundle bundle = getProperties(resName);
        String value = "";
        if (bundle != null) {
            value = String.valueOf(bundle.handleGetObject(proertyName));
        }
        return value;
    }

    /**
     * get proerty values
     * 
     * @param resName
     * 
     * @return
     */
    public static Properties getPropertyValues(String resName) {
        PropertyResourceBundle bundle = getProperties(resName);
        Properties props = new Properties();
        if (bundle != null) {
            Enumeration<String> en = bundle.getKeys();
            while (en.hasMoreElements()) {
                String parmString = (String) en.nextElement();
                props.setProperty(parmString, String.valueOf(bundle.handleGetObject(parmString)));
            }
        }
        return props;
    }

    /**
     * get proerty values
     * 
     * @param fileName
     * 
     * @return HashMap<String,String>
     */
    public static HashMap<String, String> getPropertyMap(String resName) {
        PropertyResourceBundle bundle = getProperties(resName);
        HashMap<String, String> maps = new HashMap<String, String>();
        if (bundle != null) {
            Enumeration<String> en = bundle.getKeys();
            while (en.hasMoreElements()) {
                String parmString = (String) en.nextElement();
                maps.put(parmString, String.valueOf(bundle.handleGetObject(parmString)));
            }
        }
        return maps;
    }

    /**
     * get proerties of file
     * 
     * @param fileName
     * @return
     */
    public static Properties getPropsByPathAndName(String pathName) {
        Properties props = new Properties();
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        try {
            props.load(new FileInputStream(path + pathName));
        } catch (FileNotFoundException e) {
            LOG.error("getPropsByPathAndName fail !", e);
        } catch (IOException e) {
           LOG.error("getPropsByPathAndName fail !", e);
        }
        return props;
    }

    /**
     * get proerty value
     * 
     * @return
     */
    public static String getPropValueByPathAndName(String key, String pathName) {
        Properties props = getPropsByPathAndName(pathName);
        return props.getProperty(key);
    }

    public static void main(String[] args) {
        Properties props = getPropertyValues("test.log4j");
        LOG.info(String.valueOf(props));
    }
}
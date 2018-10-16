package com.hongguaninfo.hgdf.wadp.core.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * <p>
 * 防止重复提交工具
 * </p>
 */
public class RepeatSubmitUtil {
	private final Log log = LogFactory.getLog(getClass());

    public static final String TOKEN_TAG = "submitToken";
    
	/**
	 * 增加token
	 * 
	 * @return
	 */
	protected static void putRepeatToken(HttpServletRequest request, String tokenValue) {
		ArrayList<String> tokenList = getTokens(request);
		tokenList.add(tokenValue);
		saveTokens(request, tokenList);
	}

	/**
	 * 获取最后一个token
	 * 
	 * @return
	 */
	public static String getLastRepeatToken(HttpServletRequest request) {
		ArrayList<String> tokenList = getTokens(request);
		if (tokenList != null) {
			return tokenList.get(tokenList.size() - 1);
		}
		return null;
	}

	/**
	 * 删除token
	 * 
	 * @return
	 */
	public static void removeRepeatToken(HttpServletRequest request) {
		String tokenValue = getRequestToken(request);
		ArrayList<String> tokenList = getTokens(request);
		for (String tmp : tokenList) {
			if (tmp.equals(tokenValue)) {
				tokenList.remove(tokenValue);
				break;
			}
		}
		saveTokens(request, tokenList);
	}

	/**
	 * 获取所有tokens
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected static ArrayList<String> getTokens(HttpServletRequest request) {
		ArrayList<String> tokens = (ArrayList<String>) request.getSession(true).getAttribute(TOKEN_TAG);
		if(tokens == null){
			return new ArrayList<String>();
		}
		return tokens;
	}

	/**
	 * 保存所有tokens
	 * 
	 * @param request
	 * @param tokenList
	 */
	private static void saveTokens(HttpServletRequest request, ArrayList<String> tokenList) {
		request.getSession(false).setAttribute(TOKEN_TAG, tokenList);
	}

	/**
     * 获取提交的参数token
     * @param request
     * @return
     */
    @SuppressWarnings("unused")
    protected static String getRequestToken(HttpServletRequest request){
    	return request.getParameter(TOKEN_TAG);
    }
    
    /**
     * 是否是重复提交的请求
     * @param request 
     * @return
     * @since V1.0.0
     */
    public static boolean isRepeatSubmit(HttpServletRequest request) {
        ArrayList<String> tokens = getTokens(request);
        if (tokens.size() == 0) {
            return true;
        }
        if (!tokens.contains(getRequestToken(request))) {
            return true;
        }
        return false;
    }
    
}
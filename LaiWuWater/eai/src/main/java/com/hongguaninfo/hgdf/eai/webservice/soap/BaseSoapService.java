package com.hongguaninfo.hgdf.eai.webservice.soap;

import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;
import com.hongguaninfo.hgdf.eai.webservice.soap.result.WSResult;

public class BaseSoapService {
	private static Log LOG = LogFactory.getLog(BaseSoapService.class);

	public static <T extends WSResult> T handleParameterError(T result,
			Exception e, String message) {
		LOG.error(message, e);
		result.setError(WSResult.PARAMETER_ERROR, message);
		return result;
	}

	public static <T extends WSResult> T handleParameterError(T result,
			Exception e) {
		LOG.error(e.getMessage());
		result.setError(WSResult.PARAMETER_ERROR, e.getMessage());
		return result;
	}

	public static <T extends WSResult> T handleGeneralError(T result,
			Exception e) {
		LOG.error(e.getMessage());
		result.setDefaultError();
		return result;
	}

}

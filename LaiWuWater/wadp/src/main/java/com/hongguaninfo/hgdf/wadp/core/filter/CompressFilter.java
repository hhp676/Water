package com.hongguaninfo.hgdf.wadp.core.filter;

import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.io.IOUtils;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import com.hongguaninfo.hgdf.core.utils.logging.Log;
import com.hongguaninfo.hgdf.core.utils.logging.LogFactory;

/**
 * @ClassName: CompressFilter
 * @Description: 压缩filter
 * @author henry
 * @date 2014-5-23 下午5:40:39
 * 
 */
public class CompressFilter implements Filter {

    public final Log log = LogFactory.getLog(getClass());

    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        CharResponseWrapper charResponseWrapper = new CharResponseWrapper(
                (HttpServletResponse) response);
        String requestURL = ((HttpServletRequest) request).getRequestURL()
                .toString();
        if (requestURL.contains("/druid/")) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(request, charResponseWrapper);
            String html = charResponseWrapper.toString();
            // TODO ...处理html
            HtmlCompressor compressor = new HtmlCompressor();
            compressor.setEnabled(true); 
            compressor.setRemoveComments(true); 
            compressor.setRemoveMultiSpaces(true);
            compressor.setRemoveIntertagSpaces(true);
            compressor.setRemoveQuotes(false);
            compressor.setCompressCss(true);
            compressor.setCompressJavaScript(false);

            try {
                html = compressor.compress(html);
                InputStream inputStream = new ByteArrayInputStream(
                        html.getBytes());
                IOUtils.copy(inputStream, response.getOutputStream());
                response.flushBuffer();
                /*response.getWriter().write(html);*/
            } catch (Exception e) {
                log.info("过滤失败或response已经完毕:"
                        + ((HttpServletRequest) request).getRequestURL());
            }
        }
    }

    public void destroy() {
    }

    class CharResponseWrapper extends HttpServletResponseWrapper {
        private CharArrayWriter output;

        public String toString() {
            return output.toString();
        }

        public CharResponseWrapper(HttpServletResponse response) {
            super(response);
            output = new CharArrayWriter();
        }

        public PrintWriter getWriter() {
            return new PrintWriter(output);
        }
    }
}

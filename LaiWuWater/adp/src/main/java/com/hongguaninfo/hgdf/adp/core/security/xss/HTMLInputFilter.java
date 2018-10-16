package com.hongguaninfo.hgdf.adp.core.security.xss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HTMLInputFilter {
    /**
     * flag determining whether to try to make tags when presented with
     * "unbalanced" angle brackets (e.g. "<b text </b>" becomes
     * "<b> text </b>"). If set to false, unbalanced angle brackets will be html
     * escaped.
     */
    private static final boolean ALWAYS_MAKE_TAGS = true;

    /**
     * flag determing whether comments are allowed in input String.
     */
    private static final boolean STRIP_COMMENTS = true;

    /** regex flag union representing /si modifiers in php **/
    private static final int REGEX_FLAGS_SI = Pattern.CASE_INSENSITIVE
            | Pattern.DOTALL;

    /**
     * set of allowed html elements, along with allowed attributes for each
     * element
     **/
    private Map<String, List<String>> vAllowed;

    /** counts of open tags for each (allowable) html element **/
    private Map<String, Integer> vTagCounts;

    /** html elements which must always be self-closing (e.g. "<img />") **/
    private String[] vSelfClosingTags;

    /**
     * html elements which must always have separate opening and closing tags
     * (e.g. "<b></b>")
     **/
    private String[] vNeedClosingTags;

    /** attributes which should be checked for valid protocols **/
    private String[] vProtocolAtts;

    /** allowed protocols **/
    private String[] vAllowedProtocols;

    /**
     * tags which should be removed if they contain no content (e.g. "<b></b>"
     * or "<b />")
     **/
    private String[] vRemoveBlanks;

    /** entities allowed within html markup **/
    private String[] vAllowedEntities;

    public HTMLInputFilter() {
        this(false);
    }

    public HTMLInputFilter(boolean debug) {
        vAllowed = new HashMap<String, List<String>>();
        vTagCounts = new HashMap<String, Integer>();

        ArrayList<String> aAtts = new ArrayList<String>();
        aAtts.add("href");
        aAtts.add("target");
        vAllowed.put("a", aAtts);

        ArrayList<String> imgatts = new ArrayList<String>();
        imgatts.add("src");
        imgatts.add("width");
        imgatts.add("height");
        imgatts.add("alt");
        vAllowed.put("img", imgatts);

        ArrayList<String> noAtts = new ArrayList<String>();
        vAllowed.put("b", noAtts);
        vAllowed.put("strong", noAtts);
        vAllowed.put("i", noAtts);
        vAllowed.put("em", noAtts);

        vSelfClosingTags = new String[] { "img" };
        vNeedClosingTags = new String[] { "a", "b", "strong", "i", "em" };
        vAllowedProtocols = new String[] { "http", "mailto" }; // no ftp.
        vProtocolAtts = new String[] { "src", "href" };
        vRemoveBlanks = new String[] { "a", "b", "strong", "i", "em" };
        vAllowedEntities = new String[] { "amp", "gt", "lt", "quot" };
    }

    protected void reset() {
        vTagCounts = new HashMap<String, Integer>();
    }

    public static String chr(int decimal) {
        return String.valueOf((char) decimal);
    }

    public static String htmlSpecialChars(String str) {
        return str.replaceAll("&", "&amp;").replaceAll("\"", "&quot;")
                .replaceAll("<", "&lt;").replaceAll(">", "&gt;");
    }

    // ---------------------------------------------------------------

    /**
     * given a user submitted input String, filter out any invalid or restricted
     * html.
     * 
     * @param input
     *            text (i.e. submitted by a user) than may contain html
     * @return "clean" version of input, with only valid, whitelisted html
     *         elements allowed
     */
    public synchronized String filter(String input) {
        reset();
        String str = input;
        str = escapeComments(str);
        str = balanceHTML(str);
        str = checkTags(str);
        str = processRemoveBlanks(str);
        return str;
    }

    protected String escapeComments(String str) {
        Pattern p = Pattern.compile("<!--(.*?)-->", Pattern.DOTALL);
        Matcher m = p.matcher(str);
        StringBuffer buf = new StringBuffer();
        if (m.find()) {
            // (.*?)
            String match = m.group(1);
            m.appendReplacement(buf, "<!--" + htmlSpecialChars(match) + "-->");
        }
        m.appendTail(buf);

        return buf.toString();
    }

    protected String balanceHTML(String strP) {
        String str = "";
        if (ALWAYS_MAKE_TAGS) {
            // try and form html
            str = regexReplace("^>", "", strP);
            str = regexReplace("<([^>]*?)(?=<|$)", "<$1>", str);
            str = regexReplace("(^|>)([^<]*?)(?=>)", "$1<$2", str);

        } else {
            // escape stray brackets
            str = regexReplace("<([^>]*?)(?=<|$)", "&lt;$1", strP);
            str = regexReplace("(^|>)([^<]*?)(?=>)", "$1$2&gt;<", str);

            str = str.replaceAll("<>", "");
        }

        return str;
    }

    protected String checkTags(String strP) {
        Pattern p = Pattern.compile("<(.*?)>", Pattern.DOTALL);
        Matcher m = p.matcher(strP);

        StringBuffer buf = new StringBuffer();
        while (m.find()) {
            String replaceStr = m.group(1);
            replaceStr = processTag(replaceStr);
            m.appendReplacement(buf, replaceStr);
        }
        m.appendTail(buf);

        String str = buf.toString();

        // these get tallied in processTag
        // (remember to reset before subsequent calls to filter method)
        for (String key : vTagCounts.keySet()) {
            for (int ii = 0; ii < vTagCounts.get(key); ii++) {
                str += "</" + key + ">";
            }
        }

        return str;
    }

    protected String processRemoveBlanks(String strP) {
        String str = "";
        for (String tag : vRemoveBlanks) {
            str = regexReplace("<" + tag + "(\\str[^>]*)?></" + tag + ">", "",
                    strP);
            str = regexReplace("<" + tag + "(\\str[^>]*)?/>", "", str);
        }
        return str;
    }

    protected String regexReplace(String regexPattern, String replacement,
            String str) {
        Pattern p = Pattern.compile(regexPattern);
        Matcher m = p.matcher(str);
        return m.replaceAll(replacement);
    }

    protected String processTag(String str) {
        // ending tags
        Pattern p = Pattern.compile("^/([a-z0-9]+)", REGEX_FLAGS_SI);
        Matcher m = p.matcher(str);
        if (m.find()) {
            String name = m.group(1).toLowerCase();
            if (vAllowed.containsKey(name)) {
                if (!inArray(name, vSelfClosingTags)) {
                    if (vTagCounts.containsKey(name)) {
                        vTagCounts.put(name, vTagCounts.get(name) - 1);
                        return "</" + name + ">";
                    }
                }
            }
        }

        // starting tags
        p = Pattern.compile("^([a-z0-9]+)(.*?)(/?)$", REGEX_FLAGS_SI);
        m = p.matcher(str);
        if (m.find()) {
            String name = m.group(1).toLowerCase();
            String body = m.group(2);
            String ending = m.group(3);

            if (vAllowed.containsKey(name)) {
                StringBuilder params = new StringBuilder();

                Pattern p2 = Pattern.compile("([a-z0-9]+)=([\"'])(.*?)\\2",
                        REGEX_FLAGS_SI);
                Pattern p3 = Pattern.compile("([a-z0-9]+)(=)([^\"\\s']+)",
                        REGEX_FLAGS_SI);
                Matcher m2 = p2.matcher(body);
                Matcher m3 = p3.matcher(body);
                List<String> paramNames = new ArrayList<String>();
                List<String> paramValues = new ArrayList<String>();
                while (m2.find()) {
                    // ([a-z0-9]+)
                    paramNames.add(m2.group(1));
                    // (.*?)
                    paramValues.add(m2.group(3));
                }
                while (m3.find()) {
                    // ([a-z0-9]+)
                    paramNames.add(m3.group(1));
                    // ([^\"\\s']+)
                    paramValues.add(m3.group(3));
                }
                String paramName, paramValue;
                for (int ii = 0; ii < paramNames.size(); ii++) {
                    paramName = paramNames.get(ii).toLowerCase();
                    paramValue = paramValues.get(ii);
                    if (vAllowed.get(name).contains(paramName)) {
                        if (inArray(paramName, vProtocolAtts)) {
                            paramValue = processParamProtocol(paramValue);
                        }
                        params.append(paramName).append("=\"")
                                .append(paramValue).append("\"");
                    }
                }

                if (inArray(name, vSelfClosingTags)) {
                    ending = " /";
                }

                if (inArray(name, vNeedClosingTags)) {
                    ending = "";
                }

                if (ending == null || ending.length() < 1) {
                    if (vTagCounts.containsKey(name)) {
                        vTagCounts.put(name, vTagCounts.get(name) + 1);
                    } else {
                        vTagCounts.put(name, 1);
                    }
                } else {
                    ending = " /";
                }
                return "<" + name + params + ending + ">";
            } else {
                return "";
            }
        }

        // comments
        p = Pattern.compile("^!--(.*)--$", REGEX_FLAGS_SI);
        m = p.matcher(str);
        if (m.find()) {
            String comment = m.group();
            if (STRIP_COMMENTS) {
                return "";
            } else {
                return "<" + comment + ">";
            }
        }

        return "";
    }

    protected String processParamProtocol(String strP) {
        String str = decodeEntities(strP);
        Pattern p = Pattern.compile("^([^:]+):", REGEX_FLAGS_SI);
        Matcher m = p.matcher(str);
        if (m.find()) {
            String protocol = m.group(1);
            if (!inArray(protocol, vAllowedProtocols)) {
                // bad protocol, turn into local anchor link instead
                str = "#" + str.substring(protocol.length() + 1, str.length());
                if (str.startsWith("#//")) {
                    str = "#" + str.substring(3, str.length());
                }
            }
        }

        return str;
    }

    protected String decodeEntities(String strP) {
        StringBuffer buf = new StringBuffer();

        Pattern p = Pattern.compile("&#(\\d+);?");
        Matcher m = p.matcher(strP);
        while (m.find()) {
            String match = m.group(1);
            int decimal = Integer.decode(match).intValue();
            m.appendReplacement(buf, chr(decimal));
        }
        m.appendTail(buf);
        String str = buf.toString();

        buf = new StringBuffer();
        p = Pattern.compile("&#x([0-9a-f]+);?");
        m = p.matcher(str);
        while (m.find()) {
            String match = m.group(1);
            int decimal = Integer.decode(match).intValue();
            m.appendReplacement(buf, chr(decimal));
        }
        m.appendTail(buf);
        str = buf.toString();

        buf = new StringBuffer();
        p = Pattern.compile("%([0-9a-f]{2});?");
        m = p.matcher(str);
        while (m.find()) {
            String match = m.group(1);
            int decimal = Integer.decode(match).intValue();
            m.appendReplacement(buf, chr(decimal));
        }
        m.appendTail(buf);
        str = buf.toString();

        str = validateEntities(str);
        return str;
    }

    protected String validateEntities(String str) {
        // validate quotes outside of tags
        Pattern p = Pattern.compile("(>|^)([^<]+?)(<|$)", Pattern.DOTALL);
        Matcher m = p.matcher(str);
        StringBuffer buf = new StringBuffer();
        if (m.find()) {
            // (>|^)
            String one = m.group(1);
            // ([^<]+?)
            String two = m.group(2);
            // (<|$)
            String three = m.group(3);
            m.appendReplacement(buf, one + two.replaceAll("\"", "&quot;")
                    + three);
        }
        m.appendTail(buf);

        return str;
    }

    protected String checkEntity(String preamble, String term) {
        if (!term.equals(";")) {
            return "&amp;" + preamble;
        }

        if (isValidEntity(preamble)) {
            return "&" + preamble;
        }

        return "&amp;" + preamble;
    }

    protected boolean isValidEntity(String entity) {
        return inArray(entity, vAllowedEntities);
    }

    private boolean inArray(String str, String[] array) {
        for (String item : array) {
            if (item != null && item.equals(str)) {
                return true;
            }
        }

        return false;
    }

}

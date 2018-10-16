package com.hongguaninfo.hgdf.core.utils.password;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 口令检验算法实现及规则配置类。 <br />
 * Date: 2017年8月26日 <br/>
 * @author qiujingde
 * @since V3.0.4-rc1
 */
public class PasswordCheck {

    private static int minLength = 8;
    private static final String[] charTypes = {
            ".*[a-z].*", ".*[A-Z].*", ".*\\d.*",
            ".*[`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:'\",<.>/?].*" };
    private static int minTypeNums = 2;
    private static final Map<Character, Character> shapeMap = new HashMap<>();
    private static final Set<String> forbiddenSet = new HashSet<>();

    private static final Map<Character, Character> shiftMap = new HashMap<>();
    private static final Set<String> consecutiveSet = new HashSet<>();
    static {
        addShapeMaps("i1 l1 0o 8b");
        addForbidden("admin, root");
        initConsecutiveSet();
    }


    private static final CheckResult valid = new CheckResult(true, "");

    private static final Map<String, Checker> nameMap = new HashMap<>();

    /**
     * 检查规则。<br />
     * 算法运行时检查的顺序和各项的定义顺序相同，因此定义时应该按照从低到高的顺序。
     */
    @SuppressWarnings("unused")
    public enum Checker {

        LENGTH("length", "长度应大于%d！") {
            @Override
            public CheckResult check(String password, String username) {
                Checker checker = this;
                if (password == null || password.length() < minLength) {
                    return new CheckResult(false, checker.getMsg(minLength));
                }
                return valid;
            }
        },

        CHAR_TYPE_NUMS("charTypeNums", "应包含数字，大写字母，小写字母，特殊符号中的至少%d类！") {
            private int getTypeNums(String password) {
                int num = 0;
                for (String rule : charTypes) {
                    if (password.matches(rule)) {
                        num++;
                    }
                }
                return num;
            }

            @Override
            public CheckResult check(String password, String username) {
                Checker checker = this;
                if (getTypeNums(password) < minTypeNums) {
                    return new CheckResult(false, checker.getMsg(minTypeNums));
                }
                return valid;
            }
        },

        REPEAT("repeat", "不能包含3个或3个以上重复字符！非法字符序列：%s") {

            // 获取最长的重复字符
            private String getRepeat(String password) {
                int maxStart = 0;
                int maxLen = 1;
                int crtStart = 0;
                int crtLen = 1;
                char a = password.charAt(0);
                for (int i = 1; i < password.length(); i++) {
                    char b = password.charAt(i);
                    if (a == b) {
                        crtLen++;
                    } else {
                        if (crtLen > maxLen) {
                            maxStart = crtStart;
                            maxLen = crtLen;
                        }
                        crtStart = i;
                        crtLen = 1;
                    }
                }
                return password.substring(maxStart, maxStart + maxLen);
            }

            @Override
            public CheckResult check(String password, String username) {
                Checker checker = this;

                String repeat = getRepeat(password);
                if (repeat.length() > 2) {
                    return new CheckResult(false, checker.getMsg(repeat));
                }
                return valid;
            }
        },

        USER_NAME("userName", "不能包含用户名，倒序用户名或其形似变换体！") {
            @Override
            public CheckResult check(String password, String username) {
                Checker checker = this;

                String passwordTrim = map(password, shapeMap);
                String usernameTrim = map(username, shapeMap);
                String reverseUserName = new StringBuilder(usernameTrim).reverse().toString();

                if (passwordTrim.contains(usernameTrim) || passwordTrim.contains(reverseUserName)) {
                    return new CheckResult(false, checker.getMsg());
                }
                return valid;
            }
        },

        FORBIDDEN("forbidden", "不能包含禁止字符串，或其形似变换体！非法字符串：%s") {

            private String getForbidden(String password) {
                String passwordTrim = map(password, shapeMap);
                for(String forbidden : forbiddenSet) {
                    String reverse = new StringBuilder(forbidden).reverse().toString();

                    int index = passwordTrim.contains(forbidden) ?
                            passwordTrim.indexOf(forbidden) : passwordTrim.indexOf(reverse);

                    if (index != -1) {
                        return password.substring(index, index + forbidden.length());
                    }
                }
                return null;
            }

            @Override
            public CheckResult check(String password, String username) {
                Checker checker = this;

                // 获取其中的禁止串
                String forbidden = getForbidden(password);
                if (forbidden != null) {
                    return new CheckResult(false, checker.getMsg(forbidden));
                }

                return valid;
            }
        },

        CONSECUTIVE("consecutive", "不能包含3个或3个以上字典或键盘连续字符串，或其倒序！非法字符串：%s") {
            private String getConsecutive(String password) {
                String passwordTrim = map(password.toLowerCase(), shiftMap);
                for (int i = 0; i < password.length() - 2; i++) {
                    String sub = passwordTrim.substring(i, i + 3);
                    String reverse = new StringBuilder(sub).reverse().toString();

                    if (consecutiveSet.contains(sub) || consecutiveSet.contains(reverse)) {
                        return password.substring(i, i + 3);
                    }
                }

                return null;
            }

            @Override
            public CheckResult check(String password, String username) {
                Checker checker = this;

                String forbidden = getConsecutive(password);
                if (forbidden != null) {
                    return new CheckResult(false, checker.getMsg(forbidden));
                }

                return valid;
            }
        };

        private String msgFormatter;

        Checker(String name, String msgFormatter) {
            this.msgFormatter = msgFormatter;
            nameMap.put(name, this);
        }

        public abstract CheckResult check(String password, String username);

        public static Checker parse(String name) {
            return nameMap.get(name);
        }

        private String getMsg(Object... obj) {
            return String.format(msgFormatter, obj);
        }
    }


    @SuppressWarnings("unused")
    public static void setPasswordMinLength(int len) {
        minLength = len;
    }

    @SuppressWarnings("unused")
    public static void setMinCharTypeNums(int nums) {
        minTypeNums = nums;
    }

    /**
     * 重置映射规则。<br />
     * 1."ab,cd,ef"对应映射a->b,c->d,e->f三条规则。 <br />
     * 2.方向相反的映射不能同时出现。即a->b和b->a只能出现一个。 <br />
     * 3.不能有传递性的规则。即a->b, b->c应该改成a->c,b->c
     * @param maps 映射规则。
     */
    public static void addShapeMaps(String maps) {
        addMapMap(maps, shapeMap);
    }

    public static void addForbidden(String codes) {
        for (String code : codes.replaceAll("\\s", "").split(",")) {
            addOneForbidden(code);
        }
    }

    private static void addOneForbidden(String code) {
        forbiddenSet.add(map(code, shapeMap));
    }

    private static void addMapMap(String mapStr, Map<Character, Character> maps) {
        maps.clear();
        for (String map : mapStr.split("\\s")) {
            maps.put(map.charAt(0), map.charAt(1));
        }
    }

    /**
     * 1.应全部转换为小写，再变换。
     * 2.映射应该是有方向的，保证转换后形式一致。
     * @param raw 原字符串
     * @return 处理后的字符串
     */
    private static String map(String raw, Map<Character, Character> maps) {
        String lower = raw.toLowerCase();
        for (Map.Entry<Character, Character> map : maps.entrySet()) {
            lower = lower.replace(map.getKey(), map.getValue());
        }
        return raw;
    }

    private static void initConsecutiveSet() {
        // 1.获取连续三个字母表顺序的字符。
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < alphabet.length() - 2; i++) {
            consecutiveSet.add(alphabet.substring(i, i + 3));
        }

        // 初始化特殊字符的shift转换
        String maps = "~` !1 @2 #3 $4 %5 ^6 &7 *8 (9 )0 _- += {[ }] |\\ :; \"' <, >. ?/";
        addMapMap(maps, shiftMap);

        String[] keyboard = {
                "` 1 2 3 4 5 6 7 8 9 0 - =",
                " q w e r t y u i o p [ ] \\",
                " a s d f g h j k l ; '",
                " z x c v b n m , . /"};

        Map<Integer, Map<Integer, Character>> keyboardMap = new HashMap<>();
        for (int i = 0; i < keyboard.length; i++) {
            Map<Integer, Character> map = new HashMap<>();
            keyboardMap.put(i, map);

            String[] line = keyboard[i].split(" ");
            for (int j = 0; j < line.length; j++) {
                map.put(j, line[j] != null && line[j].length() != 0 ? line[j].charAt(0) : null);
            }
        }

        int[] xStep = {0, 1, 2};
        int[] yStep = {0, 1};

        // 遍历获取所有键盘组合
        for (int c1x = 0; c1x < 14; c1x++) {
            for (int c1y = 0; c1y < 4; c1y++) {
                for (int xs : xStep) {
                    int c2x = c1x + xs;
                    int c3x = c2x + xs;
                    if (c2x > 13 || c3x > 13) {
                        continue;
                    }
                    for (int ys : yStep) {
                        // 去掉重复字符组合
                        if (xs == 0 && ys == 0) {
                            continue;
                        }
                        int c2y = c1y + ys;
                        int c3y = c2y + ys;
                        // 超出范围
                        if (c2y > 3 || c3y > 3) {
                            continue;
                        }
                        Character c1 = keyboardMap.get(c1y).get(c1x);
                        Character c2 = keyboardMap.get(c2y).get(c2x);
                        Character c3 = keyboardMap.get(c3y).get(c3x);

                        if (c1 != null && c2 != null && c3 != null) {
                            consecutiveSet.add("" + c1 + c2 + c3);
                        }
                    }
                }
            }
        }
    }

}

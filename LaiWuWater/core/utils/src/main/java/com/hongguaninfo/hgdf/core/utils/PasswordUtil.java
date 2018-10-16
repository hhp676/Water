/*
 * Project Name:hutils
 * File Name:FtpUtil.java
 * Package Name:com.hginfo.hutils
 * Date:2016年9月2日上午11:07:53
 * Copyright (c) 2016, hongguaninfo.com All Rights Reserved.
 */
package com.hongguaninfo.hgdf.core.utils;


import com.hongguaninfo.hgdf.core.utils.password.CheckResult;
import com.hongguaninfo.hgdf.core.utils.password.PasswordCheck;
import com.hongguaninfo.hgdf.core.utils.password.PasswordCheck.Checker;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


/**
 * 口令校验工具类。
 * ClassName:PasswordUtil <br/>
 * Date: 2017年8月26日 <br/>
 * @author qiujingde
 * @since V3.0.4-rc1
 */
public class PasswordUtil {

    private static final String checkerSplit = ",";
    private static final String lowCheckers = "length,charTypeNums";
    private static final String middleCheckers = "repeat,username";
    private static final String highCheckers = "forbidden,consecutive";

    private static final String shapeMaps = "i1 l1 0o 8b";
    private static final String forbidden = "admin, root";

    static {
        PasswordCheck.addShapeMaps(shapeMaps);
        PasswordCheck.addForbidden(forbidden);
    }

    public static CheckResult check(String password, String username, int validLevel) {
        return check(password, username, ValidLevel.parse(validLevel));
    }

    private static CheckResult check(String password, String username, ValidLevel validLevel){
        EnumSet<Checker> checkers = validLevel.getCheckers();
        // 使用EnumSet，遍历顺序和enum中的定义顺序相同。
        CheckResult result = null;
        for(Checker checker : checkers) {
            result = checker.check(password, username);
            if (!result.isValid()) {
                return result;
            }
        }
        assert result != null;
        return result;
    }

    private static final Map<Integer, ValidLevel> validLevelCodeMap = new HashMap<>();
    private static final Map<ValidLevel, String[]> levelToCheckNames =
            new EnumMap<>(ValidLevel.class);

    static {
        levelToCheckNames.put(ValidLevel.LOW, lowCheckers.split(checkerSplit));
        levelToCheckNames.put(ValidLevel.MIDDLE, middleCheckers.split(checkerSplit));
        levelToCheckNames.put(ValidLevel.HIGH, highCheckers.split(checkerSplit));
    }

    private enum ValidLevel {
        LOW(1),
        MIDDLE(2),
        HIGH(3);

        ValidLevel(int code) {
            validLevelCodeMap.put(code, this);
        }

        private static ValidLevel parse(int code) {
            return validLevelCodeMap.get(code);
        }

        private EnumSet<Checker> getCheckers() {
            EnumSet<Checker> checkers = EnumSet.noneOf(Checker.class);
            switch (this) {
                case HIGH:
                    addToCheckers(checkers, HIGH);
                case MIDDLE:
                    addToCheckers(checkers, MIDDLE);
                case LOW:
                default:
                    addToCheckers(checkers, LOW);
            }
            return checkers;
        }

        private void addToCheckers(EnumSet<Checker> checkers, ValidLevel level) {
            String[] names = levelToCheckNames.get(level);
            for (String name : names) {
                try {
                    checkers.add(Checker.parse(name));
                } catch (NullPointerException e) {
                    // ignore
                }
            }
        }
    }
}

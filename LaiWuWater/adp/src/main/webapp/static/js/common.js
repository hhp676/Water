/**
 * 公共函数库，主要是一些JS工具函数，各种插件的公共设置
 */
(function($) {

    $.common = {};

    //-- 初始化方法 --//
    _initFunction();

    /*******************************************/
    /**			$.common--开始	  			  **/
    /*******************************************/
    //-- frame工具 --//
    $.common.frame = {
        /**
         * 让iframe自适应高度
         */
        autoSizeIframe: function(iframeId) {
            var parentHeight = $('#' + iframeId).parent();
            $('#' + iframeId).height(parentHeight);
        }
    };
    
    //-- 浏览器工具 --//
    $.common.browser = {
        // 检测是否是IE浏览器
        isIE: function() {
            var _uaMatch = $.uaMatch(navigator.userAgent);
            var _browser = _uaMatch.browser;
            if (_browser == 'msie') {
                return true;
            } else {
                return false;
            }
        },
        // 检测是否是chrome浏览器
        isChrome: function() {
            var _uaMatch = $.uaMatch(navigator.userAgent);
            var _browser = _uaMatch.browser;
            if (_browser == 'webkit') {
                return true;
            } else {
                return false;
            }
        },
        // 检测是否是Firefox浏览器
        isMozila: function() {
            var _uaMatch = $.uaMatch(navigator.userAgent);
            var _browser = _uaMatch.browser;
            if (_browser == 'mozilla') {
                return true;
            } else {
                return false;
            }
        },
        // 检测是否是Firefox浏览器
        isOpera: function() {
            var _uaMatch = $.uaMatch(navigator.userAgent);
            var _browser = _uaMatch.browser;
            if (_browser == 'opera') {
                return true;
            } else {
                return false;
            }
        }
    };

    //-- 编码相关 --//
    $.common.code = {
        /**
         * 把文本转换为HTML代码
         * @param {Object} text	原始文本
         */
        htmlEncode: function(text) {
            var textold;
            do {
                textold = text;
                text = text.replace("\n", "<br>");
                text = text.replace("\n", "<br/>");
                text = text.replace("\n", "<BR/>");
                text = text.replace("\n", "<BR>");
                text = text.replace(" ", "&nbsp;");
            } while (textold != text);

            return text;
        },

        /**
         * 把HTML代码转换为文本
         * @param {Object} text	原始HTML代码
         */
        htmlDecode: function(text) {
            var textold;
            do {
                textold = text;
                text = text.replace("<br>", "\n");
                text = text.replace("<br/>", "\n");
                text = text.replace("<BR>", "\n");
                text = text.replace("<BR/>", "\n");
                text = text.replace("&nbsp;", " ");
            } while (textold != text);
            return text;
        }

    };

    //-- 数学工具 --//
    $.common.math = {
        /*
         * 四舍五入
         */
        round: function(dight, how) {
            return dight.toFixed(how);
        }
    };

    //-- 文件相关 --//
    $.common.file = {
        /**
         * 下载文件
         * @fileName	相对于Web根路径
         */
        download: function(fileName) {
            var downUrl = ctx + '/file/download.action?fileName=' + fileName;
            location.href = encodeURI(encodeURI(downUrl));
        },

        /**
         * 友好文件大小：KB、MB
         */
        friendlySize: function(size) {
            if (size < 1024 * 1024) {
                return $.common.math.round(size / 1024, 2) + "KB";
            } else {
                return $.common.math.round(size / (1024 * 1024), 2) + "MB";
            }
        }
    };

    //-- 字符工具 --//
    $.common.string = {
        /*
         * 如果对象为空返回空字符串
         */
        emptyIfNull: function(str) {
            return str ? str : '';
        }
    };

    //-- 未分类 --//
    $.common.custom = {
        // 得到应用名
        getCtx: function() {
            try {
                return ctx || '';
            } catch (e) {
                //alert('没有设置ctx变量');
            }
        },
        getLoadingImg: function() {
            return '<img src="' + $.common.custom.getCtx() + '/images/ajax/loading.gif" align="absmiddle"/>&nbsp;';
        },
        /**
         * 创建小时下拉框
         */
        createHourSelect: function(selectId, defaultValue) {
            var hours = new StringBuffer();
            var tempValue = "";
            for (var i = 0; i < 24; i++) {
                if (i < 10) {
                    tempValue = "0" + i;
                } else {
                    tempValue = i;
                }
                hours.append("<option value='" + tempValue + "'" + (defaultValue == tempValue ? " selected" : "") + ">" + tempValue + "</option>");
            }
            $(selectId).append(hours.toString());
        },
        /**
         * 创建分钟下拉框
         */
        createMinuteSelect: function(selectId, defaultValue) {
            var hours = new StringBuffer();
            var tempValue = "";
            for (var i = 0; i < 60; i++) {
                if (i < 10) {
                    tempValue = "0" + i;
                } else {
                    tempValue = i;
                }
                hours.append("<option value='" + tempValue + "'" + (defaultValue == tempValue ? " selected" : "") + ">" + tempValue + "</option>");
            }
            $(selectId).append(hours.toString());
        },

        /**
         * 日期增加年数或月数或天数
         * @param {String} BaseDate	要增加的日期
         * @param {Object} interval	增加数量
         * @param {Object} DatePart	增加哪一部分
         * @param {String} ReturnType 返回类型strunt|date
         */
        dateAdd: function(BaseDate, interval, DatePart, ReturnType) {
            var dateObj;
            if (typeof BaseDate == 'object') {
                dateObj = BaseDate;
            } else {
                var strDs = BaseDate.split('-');
                var year = parseInt(strDs[0]);
                var month = parseInt(strDs[1]);
                var date = parseInt(strDs[2]);
                dateObj = new Date(year, month, date);
            }
            ReturnType = ReturnType || 'string';
            var millisecond = 1;
            var second = millisecond * 1000;
            var minute = second * 60;
            var hour = minute * 60;
            var day = hour * 24;
            var year = day * 365;

            var newDate;
            var dVal = new Date(dateObj);
            var dVal = dVal.valueOf();
            switch (DatePart) {
                case "ms":
                    newDate = new Date(dVal + millisecond * interval);
                    break;
                case "s":
                    newDate = new Date(dVal + second * interval);
                    break;
                case "mi":
                    newDate = new Date(dVal + minute * interval);
                    break;
                case "h":
                    newDate = new Date(dVal + hour * interval);
                    break;
                case "d":
                    newDate = new Date(dVal + day * interval);
                    break;
                case "y":
                    newDate = new Date(dVal + year * interval);
                    break;
                default:
                    return escape("日期格式不对");
            }
            newDate = new Date(newDate);
            if (ReturnType == 'string') {
                return newDate.getFullYear() + "-" + newDate.getMonth() + "-" + newDate.getDate();
            } else if (ReturnType == 'date') {
                return newDate;
            }
        },

        /**
         * 把毫秒转换为自然语言
         * @param {Object} millis
         */
        timeRange: function(millis) {
            // 计算出相差天数
            var days = Math.floor(millis / (24 * 3600 * 1000));

            // 计算相差小时
            var leave1 = millis % (24 * 3600 * 1000); //计算天数后剩余的毫秒数
            var hours = Math.floor(leave1 / (3600 * 1000));

            //计算相差分钟数
            var leave2 = leave1 % (3600 * 1000); //计算小时数后剩余的毫秒数
            var minutes = Math.floor(leave2 / (60 * 1000));

            //计算相差秒数
            var leave3 = leave2 % (60 * 1000); //计算分钟数后剩余的毫秒数
            var seconds = Math.round(leave3 / 1000);

            var result = "";
            if (days > 0) {
                result += days + "天";
            }
            if (hours > 0) {
                result = result == "" ? "" : result + " ";
                result += hours + "小时";
            }
            if (minutes > 0) {
                result = result == "" ? "" : result + " ";
                result += minutes + "分钟";
            }
            if (seconds > 0) {
                result = result == "" ? "" : result + " ";
                result += seconds + "秒";
            }
            return result;
            //return "相差 " + days + "天 " + hours + "小时 " + minutes + " 分钟" + seconds + " 秒";

        }
    };

})(jQuery);

//-- 自定义函数 --//
function _initFunction() {
    $.extend({
        jsonToString: function(object) {
            if (object == null) {
                return 'null';
            }
            var type = typeof object;
            if ('object' == type) {
                if (Array == object.constructor) {
                    type = 'array';
                } else if (RegExp == object.constructor) {
                    type = 'regexp';
                } else {
                    type = 'object';
                }
            }
            switch (type) {
                case 'undefined':
                case 'unknown':{
                    return;
                    break;
                }
                case 'function':{
                    return '"' + object() + '"';
                    break;
                }
                case 'boolean':
                case 'regexp':{
                    return object.toString();
                    break;
                }
                case 'number':{
                    return isFinite(object) ? object.toString() : 'null';
                    break;
                }
                case 'string':{
                    return '"' +
                    object.replace(/(\\|\")/g, "\\$1").replace(/\n|\r|\t/g, function() {
                        var a = arguments[0];
                        return (a == '\n') ? '\\n' : (a == '\r') ? '\\r' : (a == '\t') ? '\\t' : ""
                    }) +
                    '"';
                    break;
                }
                case 'object':{
                    if (object === null)
                        return 'null';
                    var results = [];
                    for (var property in object) {
                        var value = jquery.jsonToString(object[property]);
                        if (value !== undefined)
                            results.push(jquery.jsonToString(property) + ':' + value);
                    }
                    return '{' + results.join(',') + '}';
                    break;

                }
                case 'array':{
                    var results = [];
                    for (var i = 0; i < object.length; i++) {
                        var value = jquery.jsonToString(object[i]);
                        if (value !== undefined)
                            results.push(value);
                    }
                    return '[' + results.join(',') + ']';
                    break;

                }
            }
        }
    });

    // jquery validator
    if ($.validator) {
    	// 银行卡号
        $.validator.addMethod("bankcard", function(value, element, params) {
            var regPex = /(^\d{16}|^\d{17}|^\d{18}|^\d{19}|^\d{20})$/;
            return this.optional(element) || regPex.exec(value);
        }, jQuery.format("请输入合法的银行卡号(长度：16~20)"));

        // 不能大于当天
        $.validator.addMethod("canNotMoreThanToday", function(value, element, params) {
        	var values = value.split('-');
            return this.optional(element) || new Date(values[0], values[1], values[2]).getMilliseconds() <= new Date().getMilliseconds();
        }, jQuery.format("日期不能大于今天！"));
        
        // 密码格式要求
        $.validator.addMethod("isGoodPassword", function(value, element, params) {
            return this.optional(element) || (  !/^[0-9]+$/.test(value) && !/^[a-zA-Z]+$/.test(value)  ) ;
        }, jQuery.format("密码不能是纯数字或纯字母！"));
        
        // 密码格式要求
        $.validator.addMethod("notSamePassword", function(value, element, params) {
            return this.optional(element) || ( value!=$(params).val()  ) ;
        }, jQuery.format("新密码不能和旧密码相同！"));
    }
};

//-- Javascript对象扩展--开始-//
/**
 * 去掉开头、结尾的空格
 *
 * @return {}
 */
String.prototype.trim = function() {
    return this.replace(/(^\s+)|\s+$/g, "");
};

/**
 * 转换字符串为json对象
 */
String.prototype.toJson = function() {
    return eval('(' + this + ')');
};

String.prototype.endsWithIgnoreCase = function(str) {
    return (this.toUpperCase().match(str.toUpperCase() + "$") == str.toUpperCase()) ||
    (this.toLowerCase().match(str.toLowerCase() + "$") == str.toLowerCase());
}

/**
 * 输出2010-02-05格式的日期字符串
 *
 * @return {}
 */
Date.prototype.toDateStr = function() {
    return ($.common.browser.isMozila() || $.common.browser.isChrome() ? (this.getYear() + 1900) : this.getYear()) + "-" +
    (this.getMonth() < 10 ? "0" + this.getMonth() : this.getMonth()) +
    "-" +
    (this.getDate() < 10 ? "0" + this.getDate() : this.getDate());
};

/**
 * 日期格式化
 * @param {Object} format
 */
Date.prototype.format = function(format) {
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(), //day
        "h+": this.getHours(), //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds() //millisecond
    }
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}


/**
 * 将字符串格式的日期转换为日期类型对象
 * @param {Object} strDate
 */
Date.toDate = function(strDate) {
    var strDs = strDate.split('-');
    var year = parseInt(strDs[0]);
    var month = parseInt(strDs[1]);
    var date = parseInt(strDs[2]);
    return new Date(year, month, date);
};

/**
 * 通过当前时间计算当前周数
 */
Date.prototype.getWeekNumber = function() {
    var d = new Date(this.getFullYear(), this.getMonth(), this.getDate(), 0, 0, 0);
    var DoW = d.getDay();
    d.setDate(d.getDate() - (DoW + 6) % 7 + 3); // Nearest Thu
    var ms = d.valueOf(); // GMT
    d.setMonth(0);
    d.setDate(4); // Thu in Week 1
    return Math.round((ms - d.valueOf()) / (7 * 864e5)) + 1;
}


//+---------------------------------------------------
//| 日期计算
//+---------------------------------------------------
Date.prototype.DateAdd = function(strInterval, Number) {
    var dtTmp = this;
    switch (strInterval) {
        case 's':
            return new Date(Date.parse(dtTmp) + (1000 * Number));
        case 'n':
            return new Date(Date.parse(dtTmp) + (60000 * Number));
        case 'h':
            return new Date(Date.parse(dtTmp) + (3600000 * Number));
        case 'd':
            return new Date(Date.parse(dtTmp) + (86400000 * Number));
        case 'w':
            return new Date(Date.parse(dtTmp) + ((86400000 * 7) * Number));
        case 'q':
            return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number * 3, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        case 'm':
            return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        case 'y':
            return new Date((dtTmp.getFullYear() + Number), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
    }
};

//-- Javascript对象扩展--结束 -//

//-- 自定义类-开始 --/
function StringBuffer() {
    this._strings_ = new Array();
}

StringBuffer.prototype.append = function(str) {
    this._strings_.push(str);
    return this;
};

StringBuffer.prototype.toString = function() {
    return this._strings_.join("").trim();
};

/**
 * 以键值对存储
 */
function Map() {
    var struct = function(key, value) {
        this.key = key;
        this.value = value;
    };

    var put = function(key, value) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                this.arr[i].value = value;
                return;
            }
        }
        this.arr[this.arr.length] = new struct(key, value);
        this._keys[this._keys.length] = key;
    };

    var get = function(key) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                return this.arr[i].value;
            }
        }
        return null;
    };

    var remove = function(key) {
        var v;
        for (var i = 0; i < this.arr.length; i++) {
            v = this.arr.pop();
            if (v.key === key) {
                continue;
            }
            this.arr.unshift(v);
            this._keys.unshift(v);
        }
    };

    var size = function() {
        return this.arr.length;
    };

    var keys = function() {
        return this._keys;
    };

    var isEmpty = function() {
        return this.arr.length <= 0;
    };

    this.arr = new Array();
    this._keys = new Array();
    this.keys = keys;
    this.get = get;
    this.put = put;
    this.remove = remove;
    this.size = size;
    this.isEmpty = isEmpty;
}

/**
 * 引入css、script文件
 * @param {Object} file
 */
function include(file) {
    var files = typeof file == "string" ? [file] : file;
    for (var i = 0; i < files.length; i++) {
        var name = files[i].replace(/^\s|\s$/g, "");
        var att = name.split('.');
        var ext = att[att.length - 1].toLowerCase();
        var isCSS = ext == "css";
        var tag = isCSS ? "link" : "script";
        var attr = isCSS ? " type='text/css' rel='stylesheet' " : " language='javascript' type='text/javascript' ";
        var link = (isCSS ? "href" : "src") + "='" + '' + name + "'";
        if ($(tag + "[" + link + "]").length == 0) {
            $("<" + tag + attr + link + "></" + tag + ">").appendTo('head');
        }
    }
}

//-- 自定义类-结束 --/

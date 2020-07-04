/**
 * Created by fei on 2015/12/3.
 */
(function(window) {
    "use strict";

    /**
     * 版本信息
     * @type {{name: string, date: string, svn: string}}
     */
    var version = {
        name: "WebAgent 3.3 @ Channelsoft CCOD 4.5",
        date: "2018-03-29",
        git: "0e52457",
        tag: "WA-V2.3-rc3-standard",
        publish: "4.5.5.3"
    };

    /**
     * 是否是开发模式（上线时须修改为false）
     */
    var devMode = false;

    /**
     * 服务器地址（支持http/https）
     * http默认端口80，https默认端口443
     */
    var config = {
        host: {
            //"http:" : "http://202.108.28.30:8080",//公网
            //"http:" : "http://182.92.167.237",
            //"http:" : "http://10.130.29.12:20000",  //内网
            //"http:" : "http://10.130.41.180:80",  //火狐测试
            //"http:" : "http://10.130.29.42:20000",    //vgsim
            //"http:" : "http://10.130.29.10:80", // 开发环境
            //"http:" : "http://10.130.29.42:80",
            "http:" : "http://open.ccod.com:20000",
            "https:": "https://202.108.28.30:443"
        }
    };

    /**
     * 应用名称（默认为WA）
     */
    var appName = "WATEXT";

    var WA = {};

    WA.version = version;

    WA.devMode = devMode;

    WA.host = "http://open.ccod.com:20000";

    var baseUrl = WA.host + "/" + appName;

    WA.getBaseUrl = function() {
        return baseUrl;
    };

    /**
     * 加载模块附加的参数，开发时增加时间戳用于清除缓存，上线后去掉或设置固定值
     */
    WA.urlArgs = "v=" + (devMode ? new Date().getTime() : version.date + "_" + version.git);

    var initParams;

    WA.getInitParams = function() {
        return initParams;
    };

    /**
     * 判断当前浏览器是否支持
     * @returns {boolean}
     */
    function browserIsNotSupported() {
        return !(window.JSON && window.localStorage);
    }

    /**
     * 初始化入口函数
     * @param params
     */
    WA.init = function(params) {
        if (browserIsNotSupported()) {
            log("您的浏览器版本过低，不支持Web坐席端，建议使用现代浏览器(谷歌/火狐/Internet Explorer 9+)访问");
            return { code: -1, msg: "browser not supported."};
        }

        initParams = params || {};

        if (initParams.useLocal) {
            baseUrl = ".";
        }

        function loadMain() {
            loadScript((devMode ? "/main.js" : "/main.min.js") + "?" + WA.urlArgs);
        }

        if (isRequireJsExist()) {
            log("find requireJs exist. version: " + requirejs.version);
            loadMain();
        } else {
            loadScript("/lib/require-2.1.22.min.js", loadMain);
        }
    };

    /**
     * 判断页面是否已加载了requireJs库
     * @returns {requirejs|require|*|boolean}
     */
    function isRequireJsExist() {
        return window.requirejs && window.require && typeof window.requirejs.version === "string";
    }

    function log(msg) {
        if (window.console && window.console.log) {
            window.console.log(msg);
        }
    }

    function loadScript(url, callback) {
        var script = document.createElement("script");
        script.type = "text/javascript";
        script.async = true;
        script.src = baseUrl + url;
        script.onload = script.onreadystatechange = function() {
            if (!this.readyState || /complete|loaded/.test(this.readyState)) {
                log("script [" + url + "] load success.");
                if (typeof callback === "function") {
                    callback();
                }
                script.onload = null;
                script.onreadystatechange = null;
            }
        };
        (document.head || document.getElementsByTagName("head")[0]).appendChild(script);
    }

    /**
     * 附加模块
     * @param moduleName
     * @param callback
     */
    WA.attachModule = function(moduleName, callback) {
        require([moduleName], function() {
            log("module [" + moduleName + "] attached success.");
            if (typeof callback === "function") {
                callback();
            }
        });
    };

    if (!window.WA) {
        window.WA = WA;
    }

})(window);


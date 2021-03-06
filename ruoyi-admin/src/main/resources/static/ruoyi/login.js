
$(function() {
	validateKickout();
    validateRule();
	$('.imgcode').click(function() {
		var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
		$(".imgcode").attr("src", url);
	});
});

$.validator.setDefaults({
    submitHandler: function() {
		login();
    }
});

function login() {
	var username = $.common.trim($("input[name='username']").val());
    var password = $.common.trim($("input[name='password']").val());
    var validateCode = $("input[name='validateCode']").val();
    var rememberMe = $("input[name='rememberme']").is(':checked');
    var orgId = $("#orgId").val();
    var platform = $("#platform").val();
    if (orgId == null || orgId == "") {
        $.modal.msg("请选择部门");
        return;
    }
    $.modal.loading($("#btnSubmit").data("loading"));
    $.ajax({
        type: "post",
        url: ctx + "login",
        data: {
            "username": username,
            "password": password,
            "validateCode" : validateCode,
            "rememberMe": rememberMe,
            "orgId": orgId,
            "platform": platform
        },
        success: function(r) {
            if (r.code == 0) {
                location.href = ctx + 'index';
            } else {
            	$.modal.closeLoading();
            	$('.imgcode').click();
            	$(".code").val("");
            	$.modal.msg(r.msg);
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: icon + "请输入您的用户名",
            },
            password: {
                required: icon + "请输入您的密码",
            }
        }
    })
}

function validateKickout() {
	if (getParam("kickout") == 1) {
	    layer.alert("<font color='red'>您已在别处登录，请您修改密码或重新登录</font>", {
	        icon: 0,
	        title: "系统提示"
	    },
	    function(index) {
	        //关闭弹窗
	        layer.close(index);
	        if (top != self) {
	            top.location = self.location;
	        } else {
	            var url  =  location.search;
	            if (url) {
	                var oldUrl  = window.location.href;
	                var newUrl  = oldUrl.substring(0,  oldUrl.indexOf('?'));
	                self.location  = newUrl;
	            }
	        }
	    });
	}
}

function getParam(paramName) {
    var reg = new RegExp("(^|&)" + paramName + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}


function findOrg() {
    var username = $("#username").val();
    $.ajax({
        type: "post",
        url: ctx + "findPermission",
        data: {
            "username": username
        },
        success: function(result) {
            var msg = result.msg;
            if(msg=="成功"){
                $("#orgId").removeAttrs("style");
                $("#orgId").attr("style","width:360px");
                $("#orgId").empty();
                var data = eval(result.data);
                $.each(data,function (i,n) {
                    $("#orgId").append("<option value='"+n.deptId+"'>"+n.deptName+"</option>");
                });
            }
        }
    });
}

// 查询用户配置的话务平台
function findPlatform() {
    var username = $("#username").val();
    $.ajax({
        type: "post",
        url: ctx + "findPlatform",
        data: {
            "username": username
        },
        success: function(result) {
            var msg = result.msg;
            console.log(result.msg);
            if(msg=="成功"){
                $("#platform").removeAttrs("style");
                $("#platform").attr("style","width:360px");
                $("#platform").empty();
                var data = eval(result.data);
                $.each(data,function (i,n) {
                    $("#platform").append("<option value='"+n.platform+"'>"+n.platformName+"</option>");
                });
            }
        }
    });
}


$(function(){var evt=window.event||arguments[0];if(evt.srcElement){watch();}});var sign;function add(){sign = sign + 1;}function logout(){window.location.href=ctx+"logout";}var watch=function(){var timer1;var timer2;document.addEventListener("click", function(){sign=0;if(timer1){clearInterval(timer1);}if(timer2){clearInterval(timer2);}timer1 = setInterval("add()", 60000);timer2 = setInterval("monitor()", 1000);});document.addEventListener("mouseover", function(){sign=0;if(timer1){clearInterval(timer1);}if(timer2){clearInterval(timer2);}timer1 = setInterval("add()", 60000);timer2 = setInterval("monitor()", 1000);});document.addEventListener("keydown", function(){sign=0;if(timer1){clearInterval(timer1);}if(timer2){clearInterval(timer2);}timer1 = setInterval("add()", 60000);timer2 = setInterval("monitor()", 1000);});};function monitor(){if(sign == 15){sign = 0;var timer = setTimeout("logout()",15000);layer.confirm("亲，您确定还在吗？",{icon: 3, title: "系统提示", btn: ['确定']}, function (index) {clearTimeout(timer);layer.close(index);}, function(){clearTimeout(timer);logout();});}}







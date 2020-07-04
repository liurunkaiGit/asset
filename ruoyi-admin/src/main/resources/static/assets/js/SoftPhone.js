var ws_address  , ws_port , keep_alive_t1,is_keep_alive="", keep_alive_num , fs_ip="",trans_num="" ,tpe="", trans_type="" ,trans_phone="",cdrId="",isShow="",mm,trans_numA="",trans_numB="",is_call_succ;	
$(document).ready(function(){
	$(document).on("click" , '[data-toggle="soft-function"]' , function(e){
		if($(this).closest(".disabled").length == 0){
			var name = $(this).data("action");
			if(name == "login"){
				uKeFuSoftPhone.input();
			}else if(name == "logout"){
				uKeFuSoftPhone.logout();
			}else if(name == "ready"){
				uKeFuSoftPhone.ready();
			}else if(name == "notready"){
				uKeFuSoftPhone.notready();
			}else if(name == "answer"){
				uKeFuSoftPhone.answer();
			}else if(name == "hungup"){
				uKeFuSoftPhone.hungup();
			}else if(name == "hold"){
				uKeFuSoftPhone.hold();
			}else if(name == "reset"){
				uKeFuSoftPhone.reset();
			}else if(name == "unhold"){
				uKeFuSoftPhone.unhold();
			}else if (name == "trans") {
				uKeFuSoftPhone.trans();
				var obj=document.getElementById('select_ivr');
				$.ajax({
					type : "POST", // 请求方式
					url : 'http://39.105.99.84:12000/callcenter' + '/callIvr/findName', // 地址，就是json文件的请求路径
					dataType : "json", // 数据类型可以为 text xml json script jsonp
					success : function(result) { // 返回的参数就是
													// action里面所有的有get和set方法的参数
						for(var i=0;i<result.length;i++){ 
							 obj.options[i]=new Option(result[i].ivrMenuName,result[i].ivrMenuExtension); 
							 }
						}
					});
				 
				$(document).ready(function(){
					   $("#select_id").change(function(){
					       var selected=$(this).children('option:selected').val()
					       if(selected=="saab"){
					    	  // console.log( $('#trans_num'))
					    	   $('#trans_num').css('display','block');
					    	   $('#select_ivr').css('display','none');
					       }else{
					    	   $('#trans_num').css('display','none');
					    	   $("#select_ivr")
								.attr("style",
										"display:block;margin-left:80px;margin-top:-31.5px;height: 31.5px;width:100px");

					       }
					   });
					}); 
			}else if (name == "listen") {
				uKeFuSoftPhone.listen();
			}else if (name == "monitor") {
				uKeFuSoftPhone.monitor();
			}
		}		
	});
	var ondial = false ;
	$('#softphone-makecall').click(function(){
		if($(this).closest(".disabled").length == 0){
			$('#dialpad').show();
		}
	}) ;
	$('#dialpad .number').on("mousedown" , function(e){
		$(this).css("background-color" , "#1E90FF") ;
	}).on("mouseup" , function(e){
		$(this).css("background-color" , "#FFFFFF") ;
	}).on("click" , function(e){
		$("#dialpad-input").val($("#dialpad-input").val() + $(this).attr("id"));
	});
	$("#dialpad-del").on("click" , function(){
		$("#dialpad-input").val( $("#dialpad-input").val().substr(0,$("#dialpad-input").val().length - 1))
	});
	$("#makecall").on("click" , function(){
		if(new RegExp('^[0-9]*$').test($('#dialpad-input').val())){
			uKeFuSoftPhone.CallOut($('#dialpad-input').val());
			$('#dialpad-input').val("") ;
			$('#dialpad').hide();
		}else{
			//layer.msg("无效的号码，请重新输入");
		}
	});
	$('#dialpad').hover(function(){
		ondial = true ;
	}, function(){
		ondial = false ;
		setTimeout(function(){
			if(ondial == false){
				$('#dialpad').hide();
			}
		} , 1000);
	});
});

var observer = {
    addSubscriber: function (callback) {
        this.subscribers[this.subscribers.length] = callback;
    },
    removeSubscriber: function (callback) {
		
        for (var i = 0; i < this.subscribers.length; i++) {
           console.log("remove sub ###",this.subscribers.length);
                 //this.subscribers[i] = null;
				 delete this.subscribers[i];
        }
		//this.subscribers.length = 0;
    },
    publish: function (type,data) {
		//console.log("sub length:",this.subscribers.length,"#",typeof this.subscribers[i]);
        for (var i = 0; i < this.subscribers.length; i++) {
			
            if (typeof this.subscribers[i] === 'function') {
                this.subscribers[i](type,data);
				
            }
        }
    },
    make: function (o) { 
        for (var i in this) {
            o[i] = this[i];
            o.subscribers = [];
        }
    }
};

var blogger = {
    recommend: function (type,data) {
        this.publish(type,data);
    },
	remove: function (callback)
	{
		this.removeSubscriber(callback);
	}
};


var softPhoneUA , currentSession , mediaStream;
var agent,domain,show;
var uKeFuSoftPhone = {
	input : function(){
	  AgentLogin();
		/*//layer.msg($('#ukefu-login-html').html(), {
		 time: 0 // 不自动关闭
		  ,btn: ['登陆', '关闭']
		  ,yes: function(index){
		  	 $(':focus').blur();
		  		agent = $("#agent").val();
			var extno = $("#extno").val();
			var extpass = $("#extpass").val();
               domain = $("#domain").val();				
			layer.close(index);
			if(agent==''){
				//layer.msg("工号不能为空");
			}else if(extno==''){
				//layer.msg("分机号不能为空");
			}else if(extpass == ''){
				//layer.msg("密码不能为空");
			}else if(domain == ''){
				//layer.msg("域名不能为空");
			}
			else{
				uKeFuSoftPhone.login(agent , extno , extpass,domain);
			}
		  } 
     });   */
		  $("#agent").focus();
	},
	Initial:function(v_ws_address,v_ws_port,v_is_show,v_is_auto_ready,v_is_keep_alive,Event,v_domain){
		ws_address = v_ws_address;
		ws_port = v_ws_port;
		isShow = v_is_show;
		is_auto_ready = v_is_auto_ready;
		is_keep_alive = v_is_keep_alive;
		domain = v_domain;
		console.log("初始化domain为："+domain);
		observer.make(blogger);
		blogger.addSubscriber(Event.listen);
		 var initial_url =  'http://'+ws_address+':'+ws_port+'/v1/VsccApiInit/?query=ip:'+ws_address+',show:'+isShow+',ready:'+is_auto_ready +',keepAlive:'+is_keep_alive;
   			$.ajax({                                                                              
           			type: "GET",   
   			        async: true,   
                    timeout : 5000, //超时时间设置，单位毫秒
   			        contentType:'application/x-javascript;charset=utf-8',     
   			        url: initial_url,
   			        dataType:"jsonp", 
   			        success: function(data){
   			        		if(data == 'OK'){
									var message = new Object();
                                    message.success = "OK";
		                            blogger.recommend("EVNET_INITIAL_SUCCESS",message);
								}else{
								 var message = new Object();
								 message.erro = data;
								blogger.recommend("EVNET_INITIAL_FAIL",message );
   			        		}
   			      		},
           			error:function(e) {
					    var message = new Object();
						message.erro = e;
						blogger.recommend("EVNET_INITIAL_FAIL",message );
                       }		   
               });
	},
	AgentLogin:function(agent , extno , extpass , domain){
		console.log("登录时ws_address:" + ws_address +"ws_port:" + ws_port + "agent:"+agent + "domain:" + domain + "extno:" + extno + "extpass:" + extpass);
			   	var login_url =  'http://'+ws_address+':'+ws_port+'/v1/login/?query=agent:'+agent+',extension:'+extno+',password:'+extpass +',domain:'+domain;
   			$.ajax({                                                                              
           			type: "GET",   
   			        async: false,       
   			        contentType:'application/x-javascript;charset=utf-8',     
   			        url: login_url,                                                                             
   			        dataType:"jsonp", 
					
   			        success: function(data){
   			        		if(data.Msg == 'OK'){
   			        			//layer.msg('登录成功');
								console.log(agent + "登录成功");
								 var message = new Object();
                                    message.agent = agent;
                                    message.extno = extno;
                                    message.disNumbers =data.DisNumbers;
									is_call_succ = false;
									
								blogger.recommend("EVNET_LONGIN_SUCCESS",message);
   			        			uKeFuSoftPhone.status.ready();
								uKeFuSoftPhone.KeepAlive();
   			        			setExt(extno,domain,ws_address);
								if(is_keep_alive == "1"){
									keep_alive_num = 0;
									keep_alive_t1 = window.setInterval(keepAlive,3000); 
								}
								
   			        			doSocket();
								
   			        		}else{
   			        			//layer.msg('登陆失败:'+data);
								console.log(agent + "登录失败");
								 var message = new Object();
								 message.erro = data;
								blogger.recommend("EVNET_LONGIN_FAIL",message );
   			        			uKeFuSoftPhone.status.logout();
   			        		}
   			      		},
           			error:function(e) {
                       //layer.msg("注册异常:"+e);
						console.log(agent + "登录失败");
					    var message = new Object();
						message.erro = JSON.stringify(e);
						blogger.recommend("EVNET_LONGIN_FAIL",message );
                       }  
               });
	},
	KeepAlive:function(){
			var keep_url =  'http://'+ws_address+':'+ws_port+'/v1/SetKeepAlive/?query=is_auto_ready:'+is_auto_ready+',is_keep_alive:'+is_keep_alive+',agent:'+agent +',domain:'+domain+',extension:'+extno;
			$.ajax({                                                                              
           			type: "GET",   
   			        async: false,       
   			        contentType:'application/x-javascript;charset=utf-8',     
   			        url: keep_url,                                                                             
   			        dataType:"jsonp", 	
   			        success: function(data){
   			        		if(data == 'OK'){
								 var message = new Object();                            
                                    message.success = "OK";
								blogger.recommend("EVNET_KEEP_ALIVE_SUCCESS",message);
   			        		}else{
								 var message = new Object();
								 message.erro = data;
								blogger.recommend("EVNET_KEEP_ALIVE_FAIL",message );
   			        		
   			        		}
   			      		},
           			error:function(e) {
					    var message = new Object();
						message.erro = JSON.stringify(e);
						blogger.recommend("EVNET_KEEP_ALIVE_FAIL",message );
                       }  
               }); 
	},
	SetFree:function(){
			$.ajax({                                                                              
        			type: "GET",        
			        async: false,        
			        contentType:'application/x-javascript;charset=utf-8',     
			        url: 'http://'+ws_address+':'+ws_port+'/v1/ready/?query=extension:'+extno+',domain:'+domain+',type:success',                                                                             
			        dataType:"jsonp",                              
			        success: function(data){
			        // console.log(data);
			        		if(data == 'ok'){
			        			//layer.msg('就绪成功');
								var message = new Object();
								 message.success = "OK";
								blogger.recommend("EVENT_AGENT_READY_SUCCESS",message ); 
			        			uKeFuSoftPhone.status.ready();
			        		}else{
			        			//layer.msg('就绪失败:'+data);	
								var message = new Object();
								 message.erro = data;
								blogger.recommend("EVENT_AGENT_READY_FAIL",message ); 
			        			// uKeFuSoftPhone.status.notready();
			        		}
			      		},
        			error:function(e) {
						 var message = new Object();
						message.erro = JSON.stringify(e);
							blogger.recommend("EVENT_AGENT_READY_FAIL",message ); 
                    //layer.msg("就绪异常:"+e);
                    }  
            });
		
		 // uKeFuSoftPhone.status.ready();
		// softPhoneUA.register({register:true});
	},
	CallOut:function(pnumber,disNumber){
		if (is_call_succ)
		{
			var message = new Object();
			message.erro = "004"; //extno state fail
			blogger.recommend("EVENT_CALLOUT_FAIL",message );
			sendmes("extno state fail---EVENT_CALLOUT_FAIL");
		}
		else
		{
			var number;
			if(disNumber==""||disNumber==undefined){
				number="0"
			}else{
				number=disNumber;
			}
			console.log("打电话时domain为："+domain);
			$.ajax({                                                                              
	        type: "GET",         
	        async: false,        
	        contentType:'application/x-javascript;charset=utf-8',
	        url: 'http://'+ws_address+':'+ws_port+'/v1/originate/?query=aleg:'+extno+',bleg:'+pnumber+',domain:'+domain+',isShow:'+isShow+',disNumber:'+number,      
			dataType:"jsonp",           
	        success: function(data){
	        		if("OK"==data){
	        			call_Event("callOut",pnumber);
						console.log(agent + "外呼成功");
						var message = new Object();
						message.success = "OK";
						blogger.recommend("EVENT_CALLOUT_SUCCESS",message );
						
						is_call_succ = true;
	        		}else{
	        			//layer.msg("外呼失败:"+data);
						console.log(agent + "外呼失败：" + data);
						var message = new Object();
						message.erro = data;
						blogger.recommend("EVENT_CALLOUT_FAIL",message );
						sendmes("EVENT_CALLOUT_FAIL");
	        		}
	        	},
	        error:function(e) {
				       var message = new Object();
						console.log(agent + "外呼失败");
				       message.erro = JSON.stringify(e);
					   blogger.recommend("EVENT_CALLOUT_FAIL",message );
	                    //layer.msg("外呼失败:"+JSON.stringify(e));
	                    }                                                         
	 		});	
		}
	},
	AgentLogout:function(v_agent,v_domain, v_extno , v_extpass){
		agent = v_agent;
		domain = v_domain;
		console.log("退出登录时ws_address:" + ws_address +"ws_port:" + ws_port + "agent:"+agent + "domain:" + domain);
		$.ajax({                                                                              
        type: "GET",         
        async: false,        
        contentType:'application/x-javascript;charset=utf-8',     
        url: 'http://'+ws_address+':'+ws_port+'/v1/logout/?query=agent:'+agent+',domain:'+domain,                                                                             
        dataType:"jsonp",                              
        success: function(data){
        		if (data == 'OK'){
        			//layer.msg('退出');
        			// socket.close();
        		    disSocket();
					console.log(agent + "退出成功");
        			window.clearInterval(keep_alive_t1);
        			uKeFuSoftPhone.status.logout();
					var message = new Object();
					message.success = "OK";
					blogger.recommend("EVENT_LOGOUT_SUCCESS",message ); 
					// blogger.removeSubscriber(Event.listen);
					// 退出成功后执行登录请求
					uKeFuSoftPhone.AgentLogin(agent, v_extno, v_extpass, domain);
        		}else{
        			//layer.msg("退出异常:"+data);
					console.log(agent + "退出失败");
					var message = new Object();
					message.erro = data;
					blogger.recommend("EVENT_LOGOUT_FAIL",message );
        		}
        	},
        error:function(e) {
                    //layer.msg("退出异常:"+JSON.stringify(e));
					console.log(agent + "退出失败");
					var message = new Object();
					message.erro = JSON.stringify(e);
					blogger.recommend("EVENT_LOGOUT_FAIL",message );
                    }                                                         
 		});
		
	},
	answer:function(){
		if(currentSession){			
			currentSession.accept(uKeFuSoftPhone.getOptions());
		}
	},
	HangupCall:function(){
		is_call_succ = false;
				$.ajax({                                                                              
        type: "GET",         
        async: false,        
        contentType:'application/x-javascript;charset=utf-8',     
        url: 'http://'+ws_address+':'+ws_port+'/v1/kill/?query=leg_uuid:'+trans_numA,                                                                             
        dataType:"jsonp",                              
        success: function(data){
        		if (data == 'OK'){
        			//layer.msg('挂机');
        			uKeFuSoftPhone.status.hungup();
					var message = new Object();
					message.success = "OK";
					blogger.recommend("EVENT_HUNGUP_SUCCESS",message);
				

        		}else{
        			//layer.msg("挂机异常:"+data);
					var message = new Object();
				    message.erro = data;
					blogger.recommend("EVENT_HUNGUP_FAIL",message ); 
        			uKeFuSoftPhone.status.hungup();
					sendmes("EVENT_HUNGUP_FAIL"+trans_numA);
        		}
        	},
        error:function(e) {
                //layer.msg("挂机异常:"+e);
					var message = new Object();
					message.erro = JSON.stringify(e);
					blogger.recommend("EVENT_HUNGUP_FAIL",message );
                uKeFuSoftPhone.status.hungup();
                 }  
        });
		
	
	},

	Hold:function(){
	
			$('#softphone-status .hold').addClass("disabled")
		setTimeout(holdd,1000); 
	
	},
	UnHold:function(){

			$('#softphone-status .unhold').addClass("disabled")
		setTimeout(unholdd,1000); 
		
	},
	
	SetAfter:function(){

			$('#softphone-status .unhold').addClass("disabled")
		after_call();
		
	},
	SetSleep:function(){
		is_call_succ = false;
			$.ajax({                                                                              
        			type: "GET",         
			        async: false,        
			        contentType:'application/x-javascript;charset=utf-8',     
			        url: 'http://'+ws_address+':'+ws_port+'/v1/sleep/?query=agent:'+agent+',domain:'+domain,                                                                             
			        dataType:"jsonp",                              
			        success: function(data){
			        // console.log(data);
			        		if(data == 'OK'){
			        			//layer.msg('小休成功');
			        			uKeFuSoftPhone.status.notready();
								var message = new Object();
					            message.success = "OK";
								blogger.recommend("EVENT_AGENT_NOTREADY_SUCCESS",message );
			        		}else{
			        			//layer.msg('小休失败:'+data);	
			        			var message = new Object();
				                message.erro = data;
					            blogger.recommend("EVENT_AGENT_NOTREADY_FAIL",message ); 
			        		}
			      		},
        			error:function(e) {
						var message = new Object();
					    message.erro = JSON.stringify(e);
					    blogger.recommend("EVENT_AGENT_NOTREADY_FAIL",message );
                        //layer.msg("就绪异常:"+e);
                    }  
            });
	},
		AgentReset:function(){
			$.ajax({                                                                              
        			type: "GET",         
			        async: false,        
			        contentType:'application/x-javascript;charset=utf-8',     
			        url: 'http://'+ws_address+':'+ws_port+'/v1/AgentReset/?query=agent:'+agent+',domain:'+domain,                                                                             
			        dataType:"jsonp",                              
			        success: function(data){
			        		if(data == 'OK'){
			        			uKeFuSoftPhone.status.notready();
								var message = new Object();
					            message.success = "OK";
								blogger.recommend("EVENT_AGENT_RESET_SUCCESS",message );
							    // blogger.removeSubscriber(Event.listen);
								disSocket();
			        		}else{
			        			var message = new Object();
				                message.erro = data;
					            blogger.recommend("EVENT_AGENT_RESET_FAIL",message ); 
			        		}
			      		},
        			error:function(e) {
						var message = new Object();
					    message.erro = JSON.stringify(e);
					    blogger.recommend("EVENT_AGENT_RESET_FAIL",message );
                    }  
            });
	},
	
	trans:function(){
	
		layer.msg($('#ukefu-trans-html').html(),{
		  time: 0 // 不自动关闭
		  ,btn: ['转接', '取消']
		  ,yes: function(index){					  
					       var selected=$("#select_id").children('option:selected').val()
					       if(selected=="saab"){
					    	 // 查询输入号码
								   trans_num = $("#trans_num").val();
					       }else{
					    	   // 查询ivr流程
								   trans_num = $("#select_ivr").val();
								   trans_type = 'IVR'
					       }
					      if(type=="in"){
					    	  uuid = $('#trans-numA').val();
					      }else{
					    	  uuid = $('#trans-numB').val();
					      }
					       
			  layer.close(index);
			  uKeFuSoftPhone.transnum();
		  }
		});
		$("#agent").focus();
	},


    transnum:function(){
		$.ajax({                                                                              
        type: "GET",         
        async: false,        
        contentType:'application/x-javascript;charset=utf-8',
        url:'http://'+ws_address+':'+ws_port+'/v1/Transfer/?query=uuid:'+uuid+',domain:'+domain+',dest-exten:'+trans_num+',transfer-exten:'+trans_phone+',type:'+trans_type,
        dataType:"jsonp",                              
        success: function(data){
        			//layer.msg("转接:"+data);
        	},
        error:function(e) {
                    //layer.msg("转接失败:"+JSON.stringify(e));
                    }                                                         
 		});

	},
	monitor:function(){
		var Domain = $("#domain").val();
		layer.open({
			type: 2 ,
			skin: 'layui-layer-rim', 
	        area: ['1015px', '520px'], 
	  		title: '监控',
	  		content: 'http://'+ws_address+':12000/callcenter/static/softphone/monitor.html?extno='+extno+ '&domain='+ domain,
	  	});

	

	},
	TransferNum:function(number,args1){
			$.ajax({                                                                              
        			type: "GET",         
			        async: false,        
			        contentType:'application/x-javascript;charset=utf-8',     
			        url:'http://'+ws_address+':'+ws_port+'/v1/Transfer/?query=uuid:'+trans_numB+',domain:'+domain+',dest-exten:'+number+',transfer-exten:'+trans_phone+',type:'+trans_type+',args1:'+args1+',extno:'+extno+',fs_ip:'+fs_ip,
					dataType:"jsonp",                              
			        success: function(data){
								blogger.recommend("EVENT_TRANSNUM_STATE_SUCCESS",data );
					},
					error:function(e) {
						var message = new Object();
					    message.erro = JSON.stringify(e);
					    blogger.recommend("EVENT_TRANSNUM_STATE_FAIL",message );
                    }  
            });
	},
	GetAgentState:function(){
			$.ajax({                                                                              
        			type: "GET",         
			        async: false,        
			        contentType:'application/x-javascript;charset=utf-8',     
			        url: 'http://'+ws_address+':'+ws_port+'/v1/GetAgentState/?query=agent:'+agent+',domain:'+domain,                                                                             
			        dataType:"jsonp",                              
			        success: function(data){
								blogger.recommend("EVENT_GET_AGENT_STATE_SUCCESS",data );
					},
					error:function(e) {
						var message = new Object();
					    message.erro = JSON.stringify(e);
					    blogger.recommend("EVENT_GET_AGENT_STATE_FAIL",message );
                    }  
            });
	},
	GetAgentList:function(){
			$.ajax({
			type : "GET", //jsonp跨域请求只能是get请求                                                
			async : false,                                                                 
			contentType : 'application/x-javascript;charset=utf-8',
			url : 'http://' + ws_address+':'+ws_port+'/v1/AgentMonitor/?query=domain:' + domain,
			dataType : "jsonp",
			success : function(json) {
		        blogger.recommend("EVENT_GET_AGENT_LIST_SUCCESS",json );
			
			},
			error : function(e) {
				var message = new Object();
			    message.erro = JSON.stringify(e);
				blogger.recommend("EVENT_GET_AGENT_LIST_FAIL",message );
			} 
            });
	},
	sessionEvent:function(session){
		session.on("rejected" , function (response, cause){
			uKeFuSoftPhone.status.hungup();
		});
		session.on("bye" , function (response, cause){
			uKeFuSoftPhone.status.hungup();
		});
		session.on("hold" , function (response, cause){
			uKeFuSoftPhone.status.hold();
		});
		session.on("unhold" , function (response, cause){
			uKeFuSoftPhone.status.unhold();
		});
		session.on("accepted" , function (response, cause){
			uKeFuSoftPhone.status.accepted();
		});
		session.on("cancel" , function (response, cause){
			uKeFuSoftPhone.status.hungup();
		});
		uKeFuSoftPhone.status.initCallStatus(session) ;
	},
	status : {
		login:function(){
			$('.soft-function,.status').removeClass("disabled");	
			$('#softphone-answer').addClass("disabled");
			$('#softphone-hungup').addClass("disabled");
			$('#softphone-status .hold').addClass("disabled").show();
			$('#softphone-status .unhold').addClass("disabled").hide();
			$('#softphone-trans').addClass("disabled");
			$('#softphone-makecall').addClass("disabled");
			$('#softphone-maketrans').addClass("disabled");
			$('#softphone-reset').addClass("disabled");
			$('#ukefu_account .login').hide();
			$('#ukefu_account .logout').removeClass("disabled").show();
			$('#softphone-status .ready').removeClass("disabled").show();
			$('#softphone-status .notready').addClass("disabled").hide();
		},
		ready : function(){
			$('.soft-function,.status').removeClass("disabled");	
			$('#softphone-answer').addClass("disabled");
			$('#softphone-hungup').addClass("disabled");		
			$('#softphone-status .hold').addClass("disabled").show();
			$('#softphone-status .unhold').addClass("disabled").hide();
			$('#softphone-trans').addClass("disabled");
			$('#softphone-makecall').removeClass("disabled");
			$('#softphone-maketrans').addClass("disabled");
			$('#ukefu_account .login').hide();
			$('#ukefu_account .logout').removeClass("disabled").show();
			$('#softphone-status .ready').addClass("disabled").hide();
			$('#softphone-status .notready').removeClass("disabled").show();
		},
		notready : function(){
			$('.soft-function,.status').removeClass("disabled");	
			$('#softphone-answer').addClass("disabled");
			$('#softphone-hungup').addClass("disabled");
			$('#softphone-status .hold').addClass("disabled").show();
			$('#softphone-status .unhold').addClass("disabled").hide();
			$('#softphone-trans').addClass("disabled");
			$('#softphone-makecall').addClass("disabled");
			$('#softphone-maketrans').addClass("disabled");
			$('#ukefu_account .login').hide();
			$('#ukefu_account .logout').removeClass("disabled").show();
			$('#softphone-status .ready').removeClass("disabled").show();
			$('#softphone-status .notready').addClass("disabled").hide();
		},
		callIn : function(){
			$('.soft-function,.status').removeClass("disabled");	
			$('#softphone-answer').removeClass("disabled");
			$('#softphone-hungup').removeClass("disabled");
			$('#softphone-status .hold').addClass("disabled").show();;
			$('#softphone-status .unhold').addClass("disabled").hide();
			$('#softphone-trans').addClass("disabled");
			$('#softphone-makecall').addClass("disabled");
			$('#softphone-maketrans').addClass("disabled");
			$('#ukefu_account .login').hide();
			$('#ukefu_account .logout').addClass("disabled").show();
			$('#softphone-status .ready').addClass("disabled").hide();
			$('#softphone-status .notready').addClass("disabled").show();
		},
		callOut : function(){
			$('.soft-function,.status').removeClass("disabled");	
			$('#softphone-answer').addClass("disabled");
			$('#softphone-hungup').removeClass("disabled");
			$('#softphone-status .hold').addClass("disabled").show();;
			$('#softphone-status .unhold').addClass("disabled").hide();
			$('#softphone-trans').addClass("disabled");
			$('#softphone-makecall').addClass("disabled");
			$('#softphone-maketrans').addClass("disabled");
			$('#ukefu_account .login').hide();
			$('#ukefu_account .logout').addClass("disabled").show();
			$('#softphone-status .ready').addClass("disabled").hide();
			$('#softphone-status .notready').addClass("disabled").show();
		},
		hungup : function(){
			$('.soft-function,.status').removeClass("disabled");	
			$('#softphone-answer').addClass("disabled");
			$('#softphone-hungup').addClass("disabled");
			$('#softphone-status .hold').addClass("disabled").show();;
			$('#softphone-status .unhold').addClass("disabled").hide();
			$('#softphone-trans').addClass("disabled");
			$('#softphone-makecall').removeClass("disabled");
			$('#softphone-maketrans').addClass("disabled");
			$('#ukefu_account .login').hide();
			$('#ukefu_account .logout').removeClass("disabled");
			$('#softphone-status .ready').removeClass("disabled").show();
			$('#softphone-status .notready').addClass("disabled").hide();
		},
		accepted : function (){
			$('.soft-function,.status').removeClass("disabled");	
			$('#softphone-answer').addClass("disabled");
			$('#softphone-hungup').removeClass("disabled");
			$('#softphone-status .hold').removeClass("disabled").show();
			$('#softphone-status .unhold').addClass("disabled").hide();
			$('#softphone-trans').removeClass("disabled");
			$('#softphone-makecall').addClass("disabled");
			$('#softphone-maketrans').addClass("disabled");
			$('#ukefu_account .login').hide();
			$('#ukefu_account .logout').addClass("disabled").show();
			$('#softphone-status .ready').addClass("disabled").hide();
			$('#softphone-status .notready').addClass("disabled").show();
		},
		hold : function (){
			$('.soft-function,.status').removeClass("disabled");	
			$('#softphone-answer').addClass("disabled");
			$('#softphone-hungup').addClass("disabled");
			$('#softphone-status .hold').addClass("disabled").hide();
			$('#softphone-status .unhold').removeClass("disabled").show();
			$('#softphone-trans').addClass("disabled");
			$('#softphone-makecall').removeClass("disabled");
			$('#softphone-maketrans').addClass("disabled");
			$('#ukefu_account .login').hide();
			$('#ukefu_account .logout').addClass("disabled").show();
			$('#softphone-status .ready').addClass("disabled").hide();
			$('#softphone-status .notready').addClass("disabled").show();
		},
		unhold : function (){
			$('.soft-function,.status').removeClass("disabled");	
			$('#softphone-answer').addClass("disabled");
			$('#softphone-hungup').removeClass("disabled");
			$('#softphone-status .hold').removeClass("disabled").show();
			$('#softphone-status .unhold').addClass("disabled").hide();
			$('#softphone-trans').removeClass("disabled");
			$('#softphone-makecall').addClass("disabled");
			$('#softphone-maketrans').addClass("disabled");
			$('#ukefu_account .login').hide();
			$('#ukefu_account .logout').addClass("disabled").show();
			$('#softphone-status .ready').addClass("disabled").hide();
			$('#softphone-status .notready').addClass("disabled").show();
		},
		logout : function (){	
			$('.status').addClass("disabled");	
			$('#softphone-answer').addClass("disabled");
			$('#softphone-hungup').addClass("disabled");
			$('#softphone-reset').addClass("disabled");
			$('#softphone-status .hold').addClass("disabled").show();
			$('#softphone-status .unhold').addClass("disabled").hide();
			$('#softphone-trans').addClass("disabled");
			$('#softphone-makecall').addClass("disabled");
			$('#softphone-maketrans').addClass("disabled");
			$('#softphone-listen').addClass("disabled");
			$('#softphone-status .ready').addClass("disabled").show();
			$('#softphone-status .notready').addClass("disabled").hide();
			$('#ukefu_account .login').removeClass("disabled").show();
			$('#ukefu_account .logout').addClass("disabled").hide();
		},
		initCallStatus:function(session , called){
			$('#caller .number').text(session.request.from.uri.user);
			if(called){
				$('#called .number').text(called);
			}
		}
	}
	
}

function holdd(){
	$.ajax({                                                                              
		type: "GET",         
        async: false,        
        contentType:'application/x-javascript;charset=utf-8',     
        url: 'http://'+ws_address+':'+ws_port+'/v1/hold/?query=leg_uuid:'+trans_numA+',op:on',                                                                             
        dataType:"jsonp",                              
        success: function(data){
        		if(data == 'OK'){
        			//layer.msg('保持成功');
        			uKeFuSoftPhone.status.hold();
					var message = new Object();
					message.success = "OK";
					blogger.recommend("EVENT_HOLD_SUCCESS",message );
					
        		}else{
        			//layer.msg('保持失败:'+data);	
					var message = new Object();
				    message.erro = data;
					blogger.recommend("EVENT_HOLD_FAIL",message );
        		}
      		},
		error:function(e) {
				var message = new Object();
			    message.erro = JSON.stringify(e);
			    blogger.recommend("EVENT_HOLD_FAIL",message );
        //layer.msg("保持异常:"+e);
        }  
});
}

function unholdd(){
	$.ajax({                                                                              
		type: "GET",         
        async: false,        
        contentType:'application/x-javascript;charset=utf-8',     
        url: 'http://'+ws_address+':'+ws_port+'/v1/hold/?query=leg_uuid:'+trans_numA+',op:off',                                                                             
        dataType:"jsonp",                              
        success: function(data){
        		if(data == 'OK'){
        			//layer.msg('取消保持成功');
        			uKeFuSoftPhone.status.unhold();
					var message = new Object();
					message.success = "OK";
					blogger.recommend("EVENT_RETRIEVE_SUCCESS",message );
        		}else{
        			//layer.msg('取消保持失败:'+data);	
					var message = new Object();
				    message.erro = data;
					blogger.recommend("EVENT_RETRIEVE_FAIL",message );
        		}
      		},
		error:function(e) {
			    var message = new Object();
			    message.erro = JSON.stringify(e);
			    blogger.recommend("EVENT_RETRIEVE_FAIL",message );
        //layer.msg("取消保持异常:"+e);
        }  
});
}

function keepAlive(){
	$.ajax({                                                                              
        			type: "GET",         
			        async: true,        
			        contentType:'application/x-javascript;charset=utf-8',     
			        url: 'http://'+ws_address+':'+ws_port+'/v1/Hearbeat/?query=agent:'+agent+',extension:'+extno+',domain:'+domain,                                                                             
			        dataType:"jsonp",
			        timeout: 3000,
			        success: function(data){
			        		if(data == 'OK'){
			        			keep_alive_num = 0;
			        		}else{
			        			keep_alive_num++;
			        			if(keep_alive_num>3){
			            			window.clearInterval(keep_alive_t1);
			            			call_Event('connectionError');
			        			}
			        			console.log('keep_alive出现异常');	
			        		}
			      		},
			      	error    : function(XMLHttpRequest,textStatus ,errorThrown){
			      	        	console.log('keep_alive error:'+keep_alive_num);
			      	        	keep_alive_num++;
			        			if(keep_alive_num>3){
			            			window.clearInterval(keep_alive_t1);
			            			call_Event('connectionError');
			        			}
                    }
        });
}


function after_call(){
	$.ajax({                                                                              
		type: "GET",         
        async: true,        
        contentType:'application/x-javascript;charset=utf-8',     
        url: 'http://'+ws_address+':'+ws_port+'/v1/After/?query=agent:'+agent+',extension:'+extno+',domain:'+domain,                                                                             
        dataType:"jsonp",
        success: function(data){
        		if(data == 'OK'){
        			//layer.msg('话后成功');
        			uKeFuSoftPhone.status.notready();
					var message = new Object();
					message.success = "OK";
					blogger.recommend("EVENT_AFTER_SUCCESS",message );
        		}else{
        			//layer.msg('话后失败：'+data);
					var message = new Object();
				    message.erro = data;
					blogger.recommend("EVENT_AFTER_FAIL",message );
        		}
      		},
      	error: function(data){
				var message = new Object();
			    message.erro = data;
			    blogger.recommend("EVENT_AFTER_FAIL",message );
        }
});
}




function call_Event(event,arg1,casid,arg2,cdrid,bb){
	cdrId = cdrid;
		switch (event){
		case 'CALLIN':
			//layer.msg(arg1.substring(0,0)+"*******"+arg1.substring(7,11)+'来电');
			type="in";
			trans_phone = arg1;
			var message = new Object();
            message.phone = arg1;
		    blogger.recommend("EVENT_CALLIN", message);
			/*$('#caller').children('span').html(arg1.substring(0,0)+"*******"+arg1.substring(7,11));
			$('#called').children('span').html(extno);
			var url = window.location.href; 
			var url1 = url.split("/"); 
			addTab({"url" : url1[0]+"//"+url1[2]+"/callcenter/workOrder/manager?queue=154&AGENTID=agentid&caller="+arg1+"&cdrid="+arg2+"","title" : "工单管理","iconCls" : "icon-folder"});*/
			//uKeFuSoftPhone.status.callIn() //界面控制菜单选项
			break;
		case 'FORECAST_CALLIN':
		    var message = new Object();
            message.phone = arg1;
            message.casid = casid;
		    blogger.recommend("EVENT_FORECAST_CALLIN", message);
			//layer.msg(arg1.substring(0,0)+"*******"+arg1.substring(7,11)+'来电了'+'  案件ID:'+casid);
			type="in";
			trans_phone = arg1;
			/*$('#caller').children('span').html(arg1.substring(0,0)+"*******"+arg1.substring(7,11));
			$('#called').children('span').html(extno);
			var url = window.location.href; 
			var url1 = url.split("/"); 
			addTab({"url" : url1[0]+"//"+url1[2]+"/callcenter/workOrder/manager?queue=154&AGENTID=agentid&caller="+arg1+"&cdrid="+arg2+"&casid="+casid+"","title" : "工单管理","iconCls" : "icon-folder"});*/
			//uKeFuSoftPhone.status.callIn()
			break;
		case 'TARNSFER_CALLIN':
			var message = new Object();
            message.phone = arg1;
            message.casid = casid;
			message.exton = arg2;
			console.log(message);
		    blogger.recommend("EVENT_TARNSFER_CALLIN", message);
			
			type="in";
			trans_phone = arg1;
			break;
		
		case 'OUT_CALL_TARNSFER_AGNET_ANSWERED':
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_OUTBOUND_TARNSFER_AGNET_ANSWERED",message );
			break;
		case 'OUT_CALL_TARNSFER_AGNET_RINGING':
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_OUTBOUND_TARNSFER_AGNET_RINGING",message );
			break;
		case 'OUT_CALL_TARNSFER_AGNET_HANGUP':
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_OUTBOUND_TARNSFER_AGNET_HANGUP",message );
			break;
		case 'OUT_CALL_TARNSFER_CUSTOMER_HANGUP':
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_OUTBOUND_TARNSFER_CUSTOMER_HANGUP",message );
			break;
		
		case 'CALLOUT':
			//layer.msg(arg1.substring(0,0)+"*******"+arg1.substring(7,11)+'外呼');
			type="out";
			trans_phone = arg1;
			/*$('#caller').children('span').html(extno);
			$('#called').children('span').html(arg1.substring(0,0)+"*******"+arg1.substring(7,11));*/
			//uKeFuSoftPhone.status.callOut()
			break;
		case 'OUT_CALL_AGNET_ANSWERED':
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_OUTBOUND_AGNET_ANSWERED",message );
			break;
		case 'OUT_CALL_CUSTOMER_ANSWERED':
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_OUTBOUND_CUSTOMER_ANSWERED",message);
			console.log("OUT_CALL_CUSTOMER_ANSWERED#####################");
			break;
		case 'OUT_CALL_FORECAST_AGNET_ANSWERED':
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_OUTBOUND_FORECAST_AGNET_ANSWERED",message );
			break;
		case 'OUT_CALL_FORECAST_AGNET_RINGING':
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_OUTBOUND_FORECAST_AGNET_RINGING",message );
			break;
		case 'IN_CALL_AGNET_ANSWERED':
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_INBOUND_AGNET_ANSWERED",message );
			break;
		case 'IN_CALL_AGNET_RINGING':
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_INBOUND_AGNET_RINGING",message );
			break;

		case 'OUT_CALL_AGENT_RINGING':
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_OUTBOUND_AGENT_RINGING",message);
			break;
		case 'OUT_CALL_CUSTOMER_RINGING':
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_OUTBOUND_CUSTOMER_RINGING",message );
			break;

		case 'OUT_CALL_AGNET_HANGUP':
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_OUTBOUND_AGNET_HANGUP",message );
			is_call_succ = false;
			break;
		case 'OUT_CALL_CUSTOMER_HANGUP':
			is_call_succ = false;
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_OUTBOUND_CUSTOMER_HANGUP",message);
			break;
		case 'OUT_CALL_FORECAST_AGNET_HANGUP':
			is_call_succ = false;
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_OUTBOUND_FORECAST_AGNET_HANGUP",message );
			break;
		case 'OUT_CALL_FORECAST_CUSTOMER_HANGUP':
			is_call_succ = false;
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_OUTBOUND_FORECAST_CUSTOMER_HANGUP",message );
			break;

		case 'IN_CALL_AGNET_HANGUP':
			is_call_succ = false;
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_INBOUND_AGENT_HANGUP",message);
			break;
		case 'IN_CALL_CUSTOMER_HANGUP':
			is_call_succ = false;
			var message = new Object();
            message.phone = arg1;
			blogger.recommend("EVENT_INBOUND_CUSTOMER_HANGUP",message );
			break;
			

		case 'HANGUP':
			is_call_succ = false;
			$.ajax({
			type : "GET",                                               
			async : false,                                                                 
			contentType : 'application/x-javascript;charset=utf-8',
			url : 'http://' + ws_address+':'+ws_port+'/v1/GetRecordData/?query=leg_uuid:'+arg1+',CdrType:'+arg2+',cdrid:'+cdrid,
			dataType : "jsonp",
			success : function(json) {
		        blogger.recommend("EVENT_HUNGUP",json );
			
			},
			error : function(e) {
				var message = new Object();
			    message.erro = JSON.stringify(e);
				blogger.recommend("EVENT_HUNGUP_GET_RECORDDATA_FAIL",message );
				sendmes('EVENT_HUNGUP_GET_RECORDDATA_FAIL');
			} 
            });

			if(is_auto_ready == "1"){
				uKeFuSoftPhone.SetFree();
			}else{
				uKeFuSoftPhone.status.hungup();
			}
			break;
		case 'listen':
			break;
		case 'SET_UUID':
		    trans_numA=arg1;
			trans_numB=casid;
			fs_ip = arg2;
			console.log("---------",trans_numA,trans_numB,fs_ip);
			break;
		case 'SET_CDRID':
			CDRID = cdrid;
			break;
		case 'getDTMF':
			//
			break;
		case 'SOCKET_REOPENT':
			//layer.msg('与服务器连接异常,请检查');
			//uKeFuSoftPhone.status.logout();
			is_call_succ = false;
			break;	
	}
	
	}


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

/*var observer = {
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
};*/

//var blogger = {
    //recommend: function (type,data) {
       // this.publish(type,data);
   // },
	//remove: function (callback)
	//{
	//	this.removeSubscriber(callback);
	//}
//};


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
	Initial:function(v_ws_address,v_ws_port,v_is_show,v_is_auto_ready,v_is_keep_alive,Event){
		ws_address = v_ws_address;
		ws_port = v_ws_port;
		isShow = v_is_show;
		is_auto_ready = v_is_auto_ready;
		is_keep_alive = v_is_keep_alive;
		//observer.make(blogger);
		//blogger.addSubscriber(Event.listen);
		 var initial_url =  'http://'+ws_address+':'+ws_port+'/v1/VsccApiInit/?query=ip:'+ws_address+',show:'+isShow+',ready:'+is_auto_ready +',keepAlive:'+is_keep_alive;
   			$.ajax({                                                                              
           			type: "GET",   
   			        async: true,   
                    timeout : 2000, //超时时间设置，单位毫秒					
   			        contentType:'application/x-javascript;charset=utf-8',     
   			        url: initial_url,                                                                             
   			        dataType:"jsonp", 
   			        success: function(data){
   			        		if(data == 'OK'){
								//	var message = new Object();
                                //    message.success = "OK";
		                          //  blogger.recommend("EVNET_INITIAL_SUCCESS",message);
								}else{
								// var message = new Object();
								// message.erro = data;
								//blogger.recommend("EVNET_INITIAL_FAIL",message );
   			        		}
   			      		},
           			error:function(e) {
					    //var message = new Object();
						//message.erro = e;
						//blogger.recommend("EVNET_INITIAL_FAIL",message );
                       }		   
               });
	},
	AgentLogin:function(agent , extno , extpass , domain){
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
									is_call_succ = false;
   			        			uKeFuSoftPhone.status.ready();
								uKeFuSoftPhone.KeepAlive();
   			        			setExt(extno,domain,ws_address);
								if(is_keep_alive == "1"){
									keep_alive_num = 0;
									keep_alive_t1 = window.setInterval(keepAlive,3000); 
								}
								
   			        			doSocket();
   			        			/***********登录成功调用业务处理开始***************/
   			        			ocxIsLogin = true;
   			        			isReadyFlag  = true;
	   			     			ocxLoginSuccess();
	   			     			uKeFuSoftPhone.SetSleep(0);//置忙
	   			     			document.getElementById('phone_status').innerHTML = '语音状态：已签入';
	   			     			//获取呼显，展示到页面呼显列表
	   			     					var disNumbers_ = data.disNumbers;
	   			     			if(disNumbers_!=null&&disNumbers_!=''){
	   			     				$("#DNIS_number_id").html('');
	   			     				var Numbers = disNumbers_.split(";");
	   			     				for ( var i = 0; i < Numbers.length; i++) {
	   			     					$("#DNIS_number_id").append("<option value='"+ Numbers[i] + "'>" + Numbers[i] + "</option>");
	   			     					}
	   			     									
	   			     			}
	   			     		/***********登录成功调用业务处理结束***************/
   			        		}else{
   			        			//layer.msg('登陆失败:'+data);	
								 var message = new Object();
								 message.erro = data;
   			        			/***********登录失败调用业务处理开始***************/
		   			     		var msg = message.erro.Msg;
		   			 			
		   			 			if("maximum register date"==msg){
		   			 				alert("注册已到上限date");
		   			 			}else if("maximum register"==msg){
		   			 				alert("注册已到上限");
		   			 			}else if("agent not found"==msg){
		   			 				alert("坐席不存在");
		   			 			}else if("extension not found"==msg){
		   			 				alert("坐席不存在");
		   			 			}else if("extension not registrations"==msg){
		   			 				alert("分机未注册");
		   			 			}else if("agent registered"==msg){
		   			 				if(confirm("分机已签入,是否强制登录?")){
		   			 					uKeFuSoftPhone.AgentReset();//重置
		   			 					setTimeout(function(){
		   			 						uKeFuSoftPhone.Initial("172.18.100.1","8088","1","0","0",Event);
		   			 						uKeFuSoftPhone.AgentLogin(agent, extno, password, domain);
		   			 						$('#ocxLogindialog').dialog('close');  
		   			 					},500);
		   			 				}
		   			 			}else{
		   			 				alert("未知错误："+msg);
		   			 			}
		   			 		/***********登录失败调用业务处理结束***************/
   			        			
   			        		}
   			      		},
           			error:function(e) {
                       //layer.msg("注册异常:"+e);
					    var message = new Object();
						message.erro = JSON.stringify(e);
						alert("注册异常："+message.erro);
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
								// var message = new Object();                            
                                //    message.success = "OK";
								//blogger.recommend("EVNET_KEEP_ALIVE_SUCCESS",message);
   			        		}else{
								// var message = new Object();
								// message.erro = data;
								//blogger.recommend("EVNET_KEEP_ALIVE_FAIL",message );
   			        		
   			        		}
   			      		},
           			error:function(e) {
					    //var message = new Object();
						//message.erro = JSON.stringify(e);
						//blogger.recommend("EVNET_KEEP_ALIVE_FAIL",message );
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
			        			/********置闲成功业务处理开始******/
			        			isReadyFlag = false;
			        			ocxNotReadyAbled();
			        			ocxReadyDisabled();
			        			document.getElementById('phone_status').innerHTML = '语音状态：置闲';
			        			/********置闲成功业务处理结束******/
			        		}else{
			        			//layer.msg('就绪失败:'+data);	
								var message = new Object();
								 message.erro = data;
								alert("置闲失败："+message.erro);
			        		}
			      		},
        			error:function(e) {
						 var message = new Object();
						message.erro = JSON.stringify(e);
							alert("置闲失败："+message.erro);
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
			$.ajax({                                                                              
	        type: "GET",         
	        async: false,        
	        contentType:'application/x-javascript;charset=utf-8',
	        url: 'http://'+ws_address+':'+ws_port+'/v1/originate/?query=aleg:'+extno+',bleg:'+pnumber+',domain:'+domain+',isShow:'+isShow+',disNumber:'+number,      
			dataType:"jsonp",           
	        success: function(data){
	        		if("OK"==data){
	        			call_Event("callOut",pnumber);
						is_call_succ = true;
						
						/*****外呼成功按钮变化*****/
						ocxHangupAbled();
	        		}else{
	        			//layer.msg("外呼失败:"+data);
						var message = new Object();
						message.erro = data;
						sendmes("EVENT_CALLOUT_FAIL");
						
						
						/*****外呼失败页面提示*****/
						if(message.erro=='001'){
							alert('外呼失败，超出最大呼叫数');
						}else if(message.erro=='002'){
							alert('外呼失败，分机没有注册');
						}else if(message.erro=='003'){
							alert('外呼失败，拨号小于6位数，无法叫呼，小于6位数是分机号码');
						}else if(message.erro=='004'){
							alert('外呼失败，正在拨号中。。。');
						}else{
							alert('外呼失败，其他错误');
						}
	        		}
	        	},
	        error:function(e) {
				       var message = new Object();
				       message.erro = JSON.stringify(e);
	                    //layer.msg("外呼失败:"+JSON.stringify(e));
					   alert('外呼失败'+message.erro);
	                    }                                                         
	 		});	
		}
	},
	AgentLogout:function(){
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
        			window.clearInterval(keep_alive_t1);
        		
					/**********登出成功业务处理开始**********/
					ocxIsLogin = false;
					ocxLoginAbled();
					ocxDisabledAll();
					ocxResetDisabled();
					document.getElementById('phone_status').innerHTML = '语音状态：未签入';
					/**********登出成功业务处理结束**********/
        		}else{
        			//layer.msg("退出异常:"+data);
					var message = new Object();
					message.erro = data;
					alert("登出失败："+message.erro);
        		}
        	},
        error:function(e) {
                    //layer.msg("退出异常:"+JSON.stringify(e));
					var message = new Object();
					message.erro = JSON.stringify(e);
					alert("登出失败："+message.erro);
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
					// var message = new Object();
					// message.success = "OK";
					// blogger.recommend("EVENT_HUNGUP_SUCCESS",message);
				

        		}else{
        			//layer.msg("挂机异常:"+data);
					//var message = new Object();
				 //   message.erro = data;
					//blogger.recommend("EVENT_HUNGUP_FAIL",message ); 
        			//uKeFuSoftPhone.status.hungup();
					sendmes("EVENT_HUNGUP_FAIL"+trans_numA);
        		}
        	},
        error:function(e) {
                //layer.msg("挂机异常:"+e);
					//var message = new Object();
					//message.erro = JSON.stringify(e);
					//blogger.recommend("EVENT_HUNGUP_FAIL",message );
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
	SetSleep:function(is_reset_ui){
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
								
								/*****置忙成功业务处理开始******/
								if(is_reset_ui == 0)
								{
								isReadyFlag = true;
								ocxNotReadyDisabled();
								ocxReadyAbled();
								document.getElementById('phone_status').innerHTML = '语音状态：置忙';
								}else 
								{
									ocxCallRest();       //hefw   这是我给你加地。 不一定对。你看就修改一下。 如果传值 不为0，就是坐席在界面上点击重置按钮，这种情况下要重新初始化UI
								}
								/*****置忙成功业务处理结束******/
			        		}else{
			        			//layer.msg('小休失败:'+data);	
			        			var message = new Object();
				                message.erro = data;
					            alert("置忙失败："+message.erro);
			        		}
			      		},
        			error:function(e) {
						var message = new Object();
					    message.erro = JSON.stringify(e);
					    alert("置忙失败："+message.erro);
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
							
								disSocket();
								
								/****重置成功业务处理****/
								ocxCallRest();
			        		}else{
			        			var message = new Object();
				                message.erro = data;
					            alert("重置失败："+message.erro);
			        		}
			      		},
        			error:function(e) {
						var message = new Object();
					    message.erro = JSON.stringify(e);
					    alert("重置失败："+message.erro);
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
								
								/********转接状态*********/
								if(data=="agent status fail"){//转接失败
						    		alert("转接失败，对方分机未置闲！"); 
						    	}else if(data=="parameters error"){//参数错误
						    		
						    	}else if(data=="OK"){//转接成功
						    		$('#transDialog').dialog('close');  
						    	}
					},
					error:function(e) {
						var message = new Object();
					    message.erro = JSON.stringify(e);
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
								//blogger.recommend("EVENT_GET_AGENT_STATE_SUCCESS",data );
					},
					error:function(e) {
						//var message = new Object();
					    //message.erro = JSON.stringify(e);
					   // blogger.recommend("EVENT_GET_AGENT_STATE_FAIL",message );
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
		       // blogger.recommend("EVENT_GET_AGENT_LIST_SUCCESS",json );
			
			},
			error : function(e) {
				//var message = new Object();
			   // message.erro = JSON.stringify(e);
				//blogger.recommend("EVENT_GET_AGENT_LIST_FAIL",message );
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
					//blogger.recommend("EVENT_HOLD_SUCCESS",message );
					
        		}else{
        			//layer.msg('保持失败:'+data);	
					var message = new Object();
				    message.erro = data;
					//blogger.recommend("EVENT_HOLD_FAIL",message );
        		}
      		},
		error:function(e) {
				var message = new Object();
			    message.erro = JSON.stringify(e);
			    //blogger.recommend("EVENT_HOLD_FAIL",message );
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
					//blogger.recommend("EVENT_RETRIEVE_SUCCESS",message );
        		}else{
        			//layer.msg('取消保持失败:'+data);	
					var message = new Object();
				    message.erro = data;
					//blogger.recommend("EVENT_RETRIEVE_FAIL",message );
        		}
      		},
		error:function(e) {
			    var message = new Object();
			    message.erro = JSON.stringify(e);
			   // blogger.recommend("EVENT_RETRIEVE_FAIL",message );
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
					//blogger.recommend("EVENT_AFTER_SUCCESS",message );
        		}else{
        			//layer.msg('话后失败：'+data);
					var message = new Object();
				    message.erro = data;
					//blogger.recommend("EVENT_AFTER_FAIL",message );
        		}
      		},
      	error: function(data){
				var message = new Object();
			    message.erro = data;
			  //  blogger.recommend("EVENT_AFTER_FAIL",message );
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
		   // blogger.recommend("EVENT_CALLIN", message);
		    
		    /********呼入业务处理**********/
		    callInDialog(message.phone);
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
		   // blogger.recommend("EVENT_FORECAST_CALLIN", message);
			//layer.msg(arg1.substring(0,0)+"*******"+arg1.substring(7,11)+'来电了'+'  案件ID:'+casid);
			type="in";
			trans_phone = arg1;
			
			/*******预测外呼业务处理*******/
			sys_start_time = getNowTime() ;
		    onLoadForecast(message.casid);
			ocxDisabledAll();
			ocxHangupAbled();
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
		 //   blogger.recommend("EVENT_TARNSFER_CALLIN", message);
			
			type="in";
			trans_phone = arg1;
			
			/**********转接呼入业务处理**************/
			inCallFlag = true;
			answerFlag = 1;		
			ocxDisabledAll();
			ocxHangupAbled();
			alert("客户电话："+message.phone+",来源分机:"+message.exton+",随路参数："+message.casid);
			break;
		
		case 'OUT_CALL_TARNSFER_AGNET_ANSWERED':
			var message = new Object();
            message.phone = arg1;
			//blogger.recommend("EVENT_OUTBOUND_TARNSFER_AGNET_ANSWERED",message );
			break;
		case 'OUT_CALL_TARNSFER_AGNET_RINGING':
			var message = new Object();
            message.phone = arg1;
			//blogger.recommend("EVENT_OUTBOUND_TARNSFER_AGNET_RINGING",message );
			break;
		case 'OUT_CALL_TARNSFER_AGNET_HANGUP':
			var message = new Object();
            message.phone = arg1;
			//blogger.recommend("EVENT_OUTBOUND_TARNSFER_AGNET_HANGUP",message );
			break;
		case 'OUT_CALL_TARNSFER_CUSTOMER_HANGUP':
			var message = new Object();
            message.phone = arg1;
		//	blogger.recommend("EVENT_OUTBOUND_TARNSFER_CUSTOMER_HANGUP",message );
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
			//blogger.recommend("EVENT_OUTBOUND_AGNET_ANSWERED",message );
			
			/*****外呼坐席应答业务处理*****/
			sys_start_time = getNowTime() ;
			break;
		case 'OUT_CALL_CUSTOMER_ANSWERED':
			var message = new Object();
            message.phone = arg1;
			//blogger.recommend("EVENT_OUTBOUND_CUSTOMER_ANSWERED",message);
			console.log("OUT_CALL_CUSTOMER_ANSWERED#####################");
			
			/*******外呼客户应答业务处理**********/
			answer_time =getNowTime() ;
			document.getElementById('phone_status').innerHTML = '通话中...';
			inCallFlag = true;
			answerFlag = 1;		 
			ocxHoldAbled();

			//----连续外呼控制---
			callBatchCustAnswered();
			//----连续外呼控制---
			
			break;
		case 'OUT_CALL_FORECAST_AGNET_ANSWERED':
			var message = new Object();
            message.phone = arg1;
			//blogger.recommend("EVENT_OUTBOUND_FORECAST_AGNET_ANSWERED",message );
			
			/**********预测外呼坐席应答业务处理************/
			document.getElementById('phone_status').innerHTML = '通话中...';
			answer_time =getNowTime() ;
			inCallFlag = true;
			answerFlag = 1;		 
			ocxHoldAbled();
			break;
		case 'OUT_CALL_FORECAST_AGNET_RINGING':
			var message = new Object();
            message.phone = arg1;
			//blogger.recommend("EVENT_OUTBOUND_FORECAST_AGNET_RINGING",message );
			break;
		case 'IN_CALL_AGNET_ANSWERED':
			var message = new Object();
            message.phone = arg1;
			//blogger.recommend("EVENT_INBOUND_AGNET_ANSWERED",message );
			/******呼入坐席应答业务处理*******/
			ocxDisabledAll();
		    ocxHangupAbled();
		    inCallFlag=true;
			break;
		case 'IN_CALL_AGNET_RINGING':
			var message = new Object();
            message.phone = arg1;
			//blogger.recommend("EVENT_INBOUND_AGNET_RINGING",message );
			break;

		case 'OUT_CALL_AGENT_RINGING':
			var message = new Object();
            message.phone = arg1;
			//blogger.recommend("EVENT_OUTBOUND_AGENT_RINGING",message);
			/*********外呼坐席振铃业务处理********/
			document.getElementById('phone_status').innerHTML = '振铃中...';
			ocxDisabledAll();
			ocxHangupAbled();
			break;
		case 'OUT_CALL_CUSTOMER_RINGING':
			var message = new Object();
            message.phone = arg1;
			//blogger.recommend("EVENT_OUTBOUND_CUSTOMER_RINGING",message );
			/*********外呼客户振铃业务处理**********/
			document.getElementById('phone_status').innerHTML = '振铃中...';
			_callTimer();//通话计时
			ocxDisabledAll();
			ocxHangupAbled();
			break;

		case 'OUT_CALL_AGNET_HANGUP':
			var message = new Object();
            message.phone = arg1;
			//blogger.recommend("EVENT_OUTBOUND_AGNET_HANGUP",message );
			is_call_succ = false;
			break;
		case 'OUT_CALL_CUSTOMER_HANGUP':
			is_call_succ = false;
			var message = new Object();
            message.phone = arg1;
			//blogger.recommend("EVENT_OUTBOUND_CUSTOMER_HANGUP",message);
			break;
		case 'OUT_CALL_FORECAST_AGNET_HANGUP':
			is_call_succ = false;
			var message = new Object();
            message.phone = arg1;
			//blogger.recommend("EVENT_OUTBOUND_FORECAST_AGNET_HANGUP",message );
			break;
		case 'OUT_CALL_FORECAST_CUSTOMER_HANGUP':
			is_call_succ = false;
			var message = new Object();
            message.phone = arg1;
			//blogger.recommend("EVENT_OUTBOUND_FORECAST_CUSTOMER_HANGUP",message );
			break;

		case 'IN_CALL_AGNET_HANGUP':
			is_call_succ = false;
			var message = new Object();
            message.phone = arg1;
			//blogger.recommend("EVENT_INBOUND_AGENT_HANGUP",message);
			break;
		case 'IN_CALL_CUSTOMER_HANGUP':
			is_call_succ = false;
			var message = new Object();
            message.phone = arg1;
			//blogger.recommend("EVENT_INBOUND_CUSTOMER_HANGUP",message );
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
		       // blogger.recommend("EVENT_HUNGUP",json );
		        
		        /*********外呼挂断成功业务处理**********/
		        
		        sendmessage("EVENT_HUNGUP");
	        	setTelPhoneCallFlag(false); // 预览外呼false;
				changTelPhoneTrStyle();
				inCallFlag = false;
				isReadyFlag = true;
				document.getElementById('phone_status').innerHTML = '呼叫结束';
				document.getElementById('phone_status').innerHTML = '语音状态：置忙';
				ocxHoldDisabled();
				ocxReadyAbled();
				ocxLogoutAbled();
				ocxHangupDisabled();
				ocxNotReadyDisabled();
				_restart();		 
				sys_end_time = getNowTime() ;
				var myData =  new Object();
		        myData = json[0];
		        var record_id =myData.ID;
		        var CallerIdNumber =myData.CallerIdNumber;
		        var DestinationNumber = myData.DestinationNumber;
		        var StartStamp = myData.StartStamp;
		        var BEndStamp = myData.BEndStamp;
		        var BAnswerStamp = myData.BAnswerStamp;
		        var RecordingFile = myData.RecordingFile;
		        var Mduration = myData.Mduration;
		        var QCType = myData.QCType;
		        var QCSum = myData.QCSum;
		        var HangupCase = myData.HangupCase;
		        insertNewOb(record_id,CallerIdNumber,DestinationNumber,StartStamp
		        		,BEndStamp,BAnswerStamp,RecordingFile,Mduration,HangupCase,answerFlag);
		        answerFlag=0;
		        clearAgentCallInfo(); // 清空参数
		        
		        //----批量外呼控制开始
	    		callBatchHungup();
	        	//----批量外呼控制结束
	    		sendmessage("EVENT_HUNGUP_end");
			
			},
			error : function(e) {
				var message = new Object();
			    message.erro = JSON.stringify(e);
				//blogger.recommend("EVENT_HUNGUP_GET_RECORDDATA_FAIL",message );
				sendmes('EVENT_HUNGUP_GET_RECORDDATA_FAIL');
				answerFlag=0;
				clearAgentCallInfo(); // 清空参数
				alert("挂断失败："+message.erro); //修改为挂断后获取录音失败
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
		case 'SOCKET_OK':
			alert("SOCKET:"+arg1); //SOCKET正常
			is_call_succ = false;
			break;	
		case 'SOCKET_CLOSE':
			alert("SOCKET:"+arg1); //SOCKET异常+异常码（agr1）
			is_call_succ = false;
			break;	
	}
	
	}


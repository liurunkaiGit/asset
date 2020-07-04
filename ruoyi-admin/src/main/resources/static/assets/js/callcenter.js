
var extno = undefined;
var domain = undefined;
var socket_url = undefined;
var socket_port = undefined;
var cccId = "";
var is_exit_socket= false;
var socket ;
var sock;


var uuid = undefined;
var is_aleg = false;
var bleg_uuid = undefined;
var is_listen = false;


var uuid = undefined;
var is_aleg = false;
var bleg_uuid = undefined;
var is_listen = false;

//hewf

var this_uuid = undefined;
var other_uuid = undefined;
var otherno =undefined;

var is_outcall = false;
var is_incall = false;
var is_out_forecast =false;
var is_tarnsfer=false;
var cdrid = undefined;
var media_ip = undefined;
var is_send_hangup = false;
var is_first = 0;

function getNowFormatDate() {//获取当前时间
	var date = new Date();
	var seperator1 = "-";
	var seperator2 = ":";
	var month = date.getMonth() + 1<10? "0"+(date.getMonth() + 1):date.getMonth() + 1;
	var strDate = date.getDate()<10? "0" + date.getDate():date.getDate();
	var currentdate = date.getFullYear() + seperator1  + month  + seperator1  + strDate
			+ " "  + date.getHours()  + seperator2  + date.getMinutes()
			+ seperator2 + date.getSeconds();
	return currentdate;
}



function setExt(ext, domain, url) {
	extno = ext;
	domain = domain;
	socket_url = url;
}
function reConnectSocket(){
	if(sock!=null && (sock.readyState == 2|| sock.readyState==3) && !is_exit_socket){
		console.info(">>>>>>>>>>>>",socket);
		sock.close();
		doSocket('reconnect....');
	}
}
setInterval(reConnectSocket,1000); 


function sendmes(str_msg)
{
	// var date = new Date();
	// var msg = extno+str_msg+"-"+getNowFormatDate();
	// sock.send(msg);
}
function sendmessage(str_msg)
{
	//var date = new Date();
	//var msg = "web-"+extno+str_msg+"-"+getNowFormatDate();
	 //sock.send(msg);
}

function doSocket(reson1) {

  sock = new SockJS('http://172.18.100.1:3000/vscc');
 sock.onopen = function() {
     console.log('sock open');
     sock.send(reson1);
	 is_exit_socket = false;
 };

 sock.onmessage = function(e) {
    var jsonData = JSON.parse(e.data)
		eventCallbackEx(jsonData);
 };
  sock.onclose = function(code,reson) {
     console.log('close',code,reson);
	 if (code.code !=1000 && !is_exit_socket)
	 {
		 var err = code.code.toString()+code.reson;
		 console.log('close',err);
		 doSocket(code.code);
		 call_Event('SOCKET_REOPENT', '');
	 }
 };

}

function disSocket(){
is_exit_socket = true;
call_Event('SET_UUID', undefined, undefined,undefined);
var data;
if (sock !=null || sock != undefined)
sock.close();
console.log("&&&&&&&&dis--socket");
sendmes("&&&&&&&&dis--socket");
}
//Caller-Destination-Number => CDN
				//Caller-Unique-ID  => UUID
				//Caller-Caller-ID-Number => CCIN
				//variable_local_media_ip => FSIP
				//Caller-Orig-Caller-ID-Number => COCIN
				//variable_cdrid => CID
				//variable_is_tarnsfer => IST
				//variable_is_forecast => ISF
				//variable_a_leg_uuid => BUUID
				//variable_transfer_uuid => TUUID
				//variable_huadao_agrs1 => AGRS
				//variable_huadao_extno => EXTNO
				//variable_this_extno => TNO
				//variable_tcql_arg1 => TARG1
				//Answer-State => AST
function eventCallbackEx(data){

	switch (data["EN"])
	{
	case "CREATE":
		{
			//分两种情况ABLeg 都会创建channel
			//console.log(data);
			if (domain == data["domain"]) 
			{
				if(extno == data["CDN"] && is_send_hangup == false && is_first >0)
				{
					call_Event('HANGUP', this_uuid,'a','4',cdrid);
					sendmes('HANGUP #### last time no hungup');
					//is_send_hangup = true;
					this_uuid = undefined;
					other_uuid = undefined;
					
					is_outcall = false;
					is_incall =false;
					is_listen = false;
					is_out_forecast = false;
					is_tarnsfer = false;
					cdrid = undefined;
					media_ip = undefined;
					call_Event('SET_UUID', this_uuid, other_uuid,media_ip);
				}
				if ( extno == data["CDN"]	&& data["BUUID"] == undefined && this_uuid == undefined && data["IST"] == undefined ){

						is_outcall = true;
						is_send_hangup = false
						this_uuid = data["UUID"];
						current_status = "CREATE";
						is_first=is_first+1;
						media_ip = data["FSIP"];
						call_Event('SET_UUID', this_uuid, other_uuid,media_ip);
						call_Event('OUT_CALL_AGENT_RINGING', extno, other_uuid);
					
					}else if( this_uuid == data["BUUID"] && this_uuid !=undefined ){//外呼B创建 设置BLeg => ringing

								otherno = data["CDN"];   //外呼时客户电话号码
								other_uuid = data["UUID"]; 			//赋值Aleg UUID 外
								cdrid = data["CID"];
								media_ip = data["FSIP"];
								call_Event('SET_CDRID', "S", "S", cdrid);
								call_Event('CALLOUT', otherno, other_uuid);  //外呼客户电话振铃事件
								call_Event('SET_UUID', this_uuid, other_uuid,media_ip); 

							}else if (data["BUUID"] != undefined) {    //呼入与自动外呼处理

								if ("1" == data["ISF"]) {

									if (this_uuid == undefined && extno == data["CDN"]) {
										other_uuid== data["BUUID"];
										this_uuid = data["UUID"]; 
										cdrid = data["CID"];
										is_out_forecast = true;
										media_ip = data["FSIP"];
										call_Event('SET_UUID', this_uuid, other_uuid,media_ip); 
										call_Event('SET_CDRID', "S", "S", cdrid);
										call_Event('FORECAST_CALLIN', data['COCIN'],data['TARG1'])//自动外呼弹屏

									} else
									{
										console.log("auto call out error CHANNEL_CREATE !!!!")
									}
								}else if("1" == data["IST"]){
										if (this_uuid == undefined && extno == data["CDN"]) {
										other_uuid= data["TUUID"];
										this_uuid = data["UUID"]; 
										//cdrid = data["CID"];
										is_tarnsfer = true;
										media_ip = data["FSIP"];
										call_Event('SET_UUID', this_uuid, other_uuid,media_ip); 
										//call_Event('SET_CDRID', "S", "S", cdrid);
										call_Event('TARNSFER_CALLIN', data['COCIN'],data['AGRS'],data['EXTNO'])//自动外呼弹屏
									} else	
									{
										//console.log("auto call out error CHANNEL_CREATE !!!!")
									}
									
									
									
								}else{  //正常叫入
										if (this_uuid == undefined && extno == data["CDN"]) {
											
											other_uuid = data["BUUID"];
											this_uuid = data["UUID"]; 
											cdrid = data["CID"];
											is_incall = true;
											media_ip = data["FSIP"];
											call_Event('SET_UUID', this_uuid, other_uuid,media_ip); 
											call_Event('SET_CDRID', "S", "S", cdrid);
											call_Event('CALLIN', data['CCIN'], cdrid);//呼入弹屏
											
										} else
										{
											//console.log("call in error CHANNEL_CREATE !!!!")
										}
								}

							}else
							{
								if("1" == data["IST"]){
										if (extno == data["CDN"]) {
										other_uuid = data["TUUID"];
										this_uuid = data["UUID"]; 
										//cdrid = data["CID"];
										is_tarnsfer = true;
										media_ip = data["FSIP"];
										call_Event('SET_UUID', this_uuid, other_uuid,media_ip); 
										call_Event('TARNSFER_CALLIN', data['COCIN'],data['AGRS'],data['TNO'])//自动外呼弹屏

									} else	
									{
										//console.log("auto call out error CHANNEL_CREATE !!!!")
									}
								}
								//console.log("unknown CHANNEL_CREATE !!!!")
							}
			}																	
		 	break;
		}

	case "CHANNEL_BRIDGE":
		{
		  	//x="Today it's Monday";
		  	break;
		}

	case "STATE":
		{
			//console.log(JSON.stringify(data));
		//	console.log(is_outcall,is_out_forecast,is_incall,data["BUUID"],data["UUID"],this_uuid ,other_uuid,data["AST"],extno);
			//if (domain == data["variable_tcql_domain"]) 
			
				switch (data["AST"]) {
					case "answered":
					{
						if(is_outcall == true){
							if (this_uuid == data["UUID"]) {

								call_Event('OUT_CALL_AGNET_ANSWERED', extno, other_uuid);  //
								

							}else if (other_uuid == data["UUID"]){

								call_Event('OUT_CALL_CUSTOMER_ANSWERED', otherno, other_uuid);  //
								
							}
						}else if (is_out_forecast == true){
						if (this_uuid == data["UUID"]) {
							call_Event('OUT_CALL_FORECAST_AGNET_ANSWERED', extno, other_uuid);  //
						}

						}else if (is_incall == true){
							if (this_uuid == data["UUID"]) 	{	
							call_Event('IN_CALL_AGNET_ANSWERED', extno, other_uuid);  }//
						}if (is_tarnsfer == true){
							if (this_uuid == data["UUID"]){
							call_Event('OUT_CALL_TRANSFER_AGNET_ANSWERED', extno, other_uuid); } //

						}
						break;			
					}
					case "early":
					{
						if(is_outcall == true){
							if (this_uuid == data["UUID"]) {

								call_Event('OUT_CALL_AGNET_RINGING', extno, other_uuid);  //
								
							}else if (other_uuid == data["UUID"]){

								call_Event('OUT_CALL_CUSTOMER_RINGING', otherno, other_uuid);  //
								
							}
						}else if (is_out_forecast == true){

							if (this_uuid == data["UUID"]){
							call_Event('OUT_CALL_FORECAST_AGNET_RINGING', extno, other_uuid);  //
						}

						}else if (is_incall == true){
							if (this_uuid == data["UUID"]){
							call_Event('IN_CALL_AGNET_RINGING', extno, other_uuid);  //
							}
						} else if (is_tarnsfer == true){

							if (this_uuid == data["UUID"]){
							call_Event('OUT_CALL_TRANSFER_AGNET_RINGING', extno, other_uuid);  //
						}
						}
						break;
					}
					case "hangup":
					{
						if(is_outcall == true){
							if (this_uuid == data["UUID"]) {

								call_Event('OUT_CALL_AGNET_HANGUP', extno, other_uuid);  //
								
								if(is_send_hangup == false)
								{
									call_Event('HANGUP', data["UUID"],'a','4',cdrid);
								
									is_send_hangup = true;
									this_uuid = undefined;
									other_uuid = undefined;
									
									is_outcall = false;
									is_incall =false;
									is_listen = false;
									is_out_forecast = false;
									is_tarnsfer = false;
									cdrid = undefined;
									media_ip = undefined;
									call_Event('SET_UUID', this_uuid, other_uuid,media_ip);
								}
							}else if (other_uuid == data["UUID"]){

								call_Event('OUT_CALL_CUSTOMER_HANGUP', otherno, other_uuid);  //
							
								if(is_send_hangup == false)
								{
									call_Event('HANGUP', data["UUID"],'a','4',cdrid);
									
									
									$.ajax({                                                                              
									type: "GET",         
									async: false,        
									contentType:'application/x-javascript;charset=utf-8',     
									url: 'http://'+ws_address+':'+ws_port+'/v1/kill/?query=leg_uuid:'+this_uuid,                                                                             
									dataType:"jsonp",                              
									success: function(data){
											if (data == 'OK'){
												console.log("HANGUP #### ########################")
											}else{
											
												sendmes("EVENT_HUNGUP_FAIL111"+this_uuid);
											}
										},
									error:function(e) {
											
											 }  
									});
									
									
									
									is_send_hangup = true;
									
									this_uuid = undefined;
									other_uuid = undefined;
									
									is_outcall = false;
									is_incall =false;
									is_listen = false;
									is_out_forecast = false;
									is_tarnsfer = false;
									cdrid = undefined;
									media_ip = undefined;
									call_Event('SET_UUID', this_uuid, other_uuid,media_ip);
								}
							}
						}else if (is_out_forecast == true){

							if (this_uuid == data["UUID"]) {

								call_Event('OUT_CALL_FORECAST_AGNET_HANGUP', extno, other_uuid);  //

							}else if (other_uuid == data["UUID"]){

								call_Event('OUT_CALL_FORECAST_CUSTOMER_HANGUP', otherno, other_uuid);  //
							}

						}else if (is_incall == true){

							if (this_uuid == data["UUID"]) {

								call_Event('IN_CALL_AGNET_HANGUP', extno, other_uuid);  //

							}else if (other_uuid == data["UUID"]){

								call_Event('IN_CALL_CUSTOMER_HANGUP', otherno, other_uuid);  //
							}
						}else if (is_tarnsfer == true){

							if (this_uuid == data["UUID"]) {

								call_Event('OUT_CALL_TRANSFER_AGNET_HANGUP', extno, other_uuid);  //

							}else if (other_uuid == data["UUID"]){

								call_Event('OUT_CALL_TRANSFER_CUSTOMER_HANGUP', otherno, other_uuid);  //
							}
						}
						break;
					}

					case "ringing":
					{
						if(is_outcall == true){
								if (this_uuid == data["UUID"]) {

									call_Event('OUT_CALL_AGNET_RINGING', extno, other_uuid);  //
									

								}else if (other_uuid == data["UUID"]){

									call_Event('OUT_CALL_CUSTOMER_RINGING', otherno, other_uuid);  //
									
								}
							}else if (is_out_forecast == true){

								if (this_uuid == data["UUID"]){
								call_Event('OUT_CALL_FORECAST_AGNET_RINGING', extno, other_uuid);  //
							}

							}else if (is_incall == true){
								if (this_uuid == data["UUID"]){
								call_Event('IN_CALL_AGNET_RINGING', extno, other_uuid);  //
								}
							}else if (is_tarnsfer == true){

								if (this_uuid == data["UUID"]){
								call_Event('OUT_CALL_TRANSFER_AGNET_RINGING', extno, other_uuid);  //
							}
						}
							break;
					}
			}
		  
		  	break;
		}

	case "HANGUP":
	 	{
	 		if (domain == data["domain"]) 
			{
				if (this_uuid == data["UUID"] || other_uuid == data["UUID"]) {

					if(is_outcall == true){
						
								if(is_send_hangup == false)
								{
									call_Event('HANGUP', data["UUID"],'a','4',cdrid);
								
									is_send_hangup = true;
								}
							}else if (is_out_forecast == true){
								call_Event('HANGUP', data["UUID"],'a','7',cdrid);
							}else if (is_incall == true){
								call_Event('HANGUP', data["UUID"],'a','6',cdrid);
							}else if (is_tarnsfer == true){
								call_Event('HANGUP', data["UUID"],'a','4',cdrid);
							}

					this_uuid = undefined;
					other_uuid = undefined;
					
					is_outcall = false;
					is_incall =false;
					is_listen = false;
					is_out_forecast = false;
					is_tarnsfer = false;
					cdrid = undefined;
					media_ip = undefined;
					//if(extno == data["CDN"] || otherno == data["CDN"] )
					{
						call_Event('SET_UUID', this_uuid, other_uuid,media_ip);
						
					}
					otherno = undefined;
				}
			}
		  	break;
		}
	}


}

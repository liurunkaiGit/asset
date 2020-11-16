//电话号码脱敏
function tellToMd5(tell){
    if(tell != null && tell != ""){
        tell = tell.substring(0,3) + "****" + tell.substring(7);
    }else{
        tell = "";
    }
    return tell;
}

//身份证脱敏
function credentialToMd5(credNo){
    if(credNo != null && credNo != ""){
        credNo = credNo.substring(0,5) + "********" + credNo.substring(13);
    }else {
        credNo ="";
    }
    return credNo;
}

//银行卡脱敏
function cardToMd5(cardno){
    if(cardno != null && cardno != ""){
        var len = cardno.length;
        var endIndex = len-4;
        cardno = cardno.substring(0,4) + "********" + cardno.substring(endIndex);
    }else {
        cardno ="";
    }
    return cardno;
}

//地址脱敏
function addrToMd5(addr){
    if(addr != null && addr != ""){
        if(addr.indexOf("市") >= 0){
            addr = addr.substring(0,addr.indexOf("市")+1) + "********";
        }else if(addr.indexOf("省") >= 0){
            addr = addr.substring(0,addr.indexOf("省")+1) + "********";
        }else{
            addr = "********";
        }
    }else {
        addr ="";
    }
    return addr;
}
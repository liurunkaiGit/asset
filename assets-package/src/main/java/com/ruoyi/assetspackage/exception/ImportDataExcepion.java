package com.ruoyi.assetspackage.exception;

/**
 * @author guozeqi
 * @create 2019-12-30
 */

public class ImportDataExcepion extends RuntimeException{
    private static final long serialVersionUID = -7864604160297181941L;
    public ImportDataExcepion(){

    }
    public ImportDataExcepion(String str){
        //此处传入的是抛出异常后显示的信息提示
        super(str);
    }


}

package com.erp.sale.utils;

/**
 * Created by lenovo12 on 2018/8/18.
 */
public final  class FileUtil {
    private FileUtil(){}

    /**
     * 获取后缀
     * @param fileName 文件名
     * @return 后缀名
     */
    public static String getFileSuffix(String fileName){
        if(com.erp.sale.utils.StringUtil.isEmpty(fileName) || fileName.lastIndexOf(".")<0 ){
            return "error";
        }
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }
}

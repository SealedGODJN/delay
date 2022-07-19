package edu.nwpu.edap.edapplugin.exception;

public class XMLPropertyLossException extends Exception{
    public XMLPropertyLossException(String label,String prop){
        System.err.println("数据传输场景配置文件"+label+"标签缺少属性："+prop);
    }
}

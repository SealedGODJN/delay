package edu.nwpu.edap.edapplugin.exception;

public class XMLDocumentException extends Exception{

    public XMLDocumentException(String reason){
        System.err.println("数据传输场景配置文件出现错误，"+reason);
    }
}

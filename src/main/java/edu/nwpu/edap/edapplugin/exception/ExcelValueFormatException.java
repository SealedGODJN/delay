package edu.nwpu.edap.edapplugin.exception;


public class ExcelValueFormatException extends Exception{
	
	public ExcelValueFormatException(String info,int line){
        System.err.println("区段接口文件第"+line+"行格式错误，"+info);
    }

}

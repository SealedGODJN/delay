package edu.nwpu.edap.edapplugin.exception;

public class ExcelPropertyLossException extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcelPropertyLossException(String name, int col){
        System.err.println("区段接口文件错误，"+name+"第"+col+"列缺少属性值");
    }

}

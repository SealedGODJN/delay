package edu.nwpu.edap.edapplugin.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import edu.nwpu.edap.edapplugin.Service.Impl.ProgressServiceImpl;
import edu.nwpu.edap.edapplugin.Service.ProgressService;
import edu.nwpu.edap.edapplugin.bean.external.ExternalRDIU;
import edu.nwpu.edap.edapplugin.exception.ExcelPropertyLossException;
import edu.nwpu.edap.edapplugin.exception.ExcelValueFormatException;
import edu.nwpu.edap.edapplugin.library.ExternalRDIULibrary;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ImportExcelUtil {
	
	// ---------------------------------start----------------------------------
	
	private XSSFWorkbook xssfWorkbook = null;
	private List<JSONObject> jObjects = null;
	
	
	public ImportExcelUtil() {
		init();
	}
	
	private  List<JSONObject> init(){
		try {
			ProgressService progressService = new ProgressServiceImpl();
			progressService.closeProgress();
			progressService.showProgress();
			MyService myService = new MyService();
			
			
			myService.start();
			myService.progressProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
					progressService.setProgress(arg2.doubleValue());
				}
			});
			
			myService.messageProperty().addListener(new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
					progressService.setMessage(arg2);	
				}
			});
				
		}catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return jObjects;
	}
	
	class MyService extends Service<Number>{
		
		

		@Override
		protected Task<Number> createTask() {
			
			Task<Number> task = new Task<Number>() {

				@Override
				protected Number call() throws Exception {
					List<JSONObject> jsonObjects = new ArrayList<>();
			        XSSFSheet xssfSheet = xssfWorkbook.getSheet("Sheet1");//获取Sheet
			        if (xssfSheet == null) {
			            System.err.println("未获取到Sheet1内容");
			            return null;
			        }
			        List<String> colName = new ArrayList<>();
			        int total = xssfSheet.getLastRowNum();
			        
			        int MAX_SIZE =xssfSheet.getLastRowNum();
			        this.updateMessage("正在加载RDIU区段接口文件...");
			        int j = 0;
			        
			        for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
			            XSSFRow xssfRow = xssfSheet.getRow(rowNum);//获取Row
			            int minColIx = xssfRow.getFirstCellNum();
			            int maxColIx = xssfRow.getLastCellNum();
			            if (total >= 100 && rowNum % (total / 100) == 0) {
			                System.out.println("程序已导入：" + rowNum / (total / 100));
			            }
			            if (rowNum == 0) {
			                for (int colIx = minColIx; colIx < maxColIx; colIx++) {
			                    XSSFCell cell = xssfRow.getCell(colIx);
			                    //在这里可能出现第一行属性值缺少的情况，需要对异常进行处理
			                    if (cell == null||cell.toString() == "") {	
			                        throw new ExcelPropertyLossException("RDIU", colIx);
			                    } else {
			                        colName.add(cell.toString());
			                    }
			                }
			            } else {
			                JSONObject jsonObject = new JSONObject();
			                for (int colIx = minColIx; colIx < maxColIx; colIx++) {
			                    XSSFCell cell = xssfRow.getCell(colIx);
			                    //如果非第一行以外的部分Cell中出现null，则自动填充空字符串
			                    if (cell == null) {
			                        jsonObject.put(colName.get(colIx), "");
			                        continue;
			                    }
			                    jsonObject.put(colName.get(colIx), cell.toString());
			                }
			                jsonObjects.add(jsonObject);
			            }
			            
			            this.updateProgress(j++, MAX_SIZE);
			        }
			        
			        jObjects = jsonObjects;
					this.updateMessage("导入完成！");
					
					return null;
				}
				
			};
			
			return task;
		}
		
		
	}
	
	
	
	// -----------------------------------end------------------------------

    public void setExternalRDIUList(String path) throws ExcelPropertyLossException, ExcelValueFormatException{
    	List<ExternalRDIU> RDIUList = new ArrayList<>();
    	
    	getDataList(path);
    	
        List<JSONObject> dataList = init();
        int line = 0;
        try {
        	for(line=0;line<dataList.size();line++) {
            	RDIUList.add(jsonToRDIU(dataList.get(line)));
            }
        }catch (JSONException e) {
			throw new ExcelValueFormatException(e.getMessage(),line+1);
		}
        ExternalRDIULibrary.setExternalRDIUs(RDIUList);
        System.out.println(RDIUList.size());
    }

    private void getDataList(String path) throws ExcelPropertyLossException {
        XSSFWorkbook xssfWorkbook = getXSSFWorkbook(path);
        if (xssfWorkbook == null) {
            System.err.println("未输入XLSX文件或输入文件类型错误");
            return;
        }
        
        this.xssfWorkbook = xssfWorkbook;
    }

    //获取EXCEL文件，后缀名必须为xlsx
    private XSSFWorkbook getXSSFWorkbook(String path) {
        try {
            InputStream is = new FileInputStream(path);
            System.out.println(path);
            return new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private ExternalRDIU jsonToRDIU(JSONObject jsonObject) {
        return JSONObject.parseObject(jsonObject.toString(), ExternalRDIU.class);
    }
}

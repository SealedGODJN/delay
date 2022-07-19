package edu.nwpu.edap.edapplugin.report;

import com.careri.as.businessmodel.model.RPType;
import com.careri.as.workbench.api.misc.LogService;
import com.careri.as.workbench.api.misc.impl.LogServiceImpl;
import edu.nwpu.edap.edapplugin.Service.ConstructSceneService;
import edu.nwpu.edap.edapplugin.Service.Impl.ConstructSceneServiceImpl;
import edu.nwpu.edap.edapplugin.Service.Impl.LoadICDDataServiceImpl;
import edu.nwpu.edap.edapplugin.Service.Impl.ProgressServiceImpl;
import edu.nwpu.edap.edapplugin.Service.LoadICDDataService;
import edu.nwpu.edap.edapplugin.Service.ProgressService;
import edu.nwpu.edap.edapplugin.bean.RPMessage;
import edu.nwpu.edap.edapplugin.library.RPMessageLibrary;
import edu.nwpu.edap.edapplugin.test.LoadICDDataTest;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.util.List;

public class GenerateReport {
	
	LoadICDDataTest loadICDDataTest = LoadICDDataTest.getInstance();
	LogService logService = new LogServiceImpl("EDAP");
	int state0 = 0, state3=0;

	// ------------------------------------------------------------------------------
	public GenerateReport() {
		init();
	}
	
	private void init(){
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
	}
	
	class MyService extends Service<Number>{

		@Override
		protected Task<Number> createTask() {
			
			Task<Number> task = new Task<Number>() {

				@Override
				protected Number call() throws Exception {
					LoadICDDataTest loadICDDataTest = LoadICDDataTest.getInstance();
					List<RPType> rpList = loadICDDataTest.getRPList();
					System.out.println(rpList.size());
					long startTime = System.currentTimeMillis();
					
					int MAX_SIZE =rpList.size();
					
					this.updateMessage("正在分析ICD数据...");
					int j =0;
					
					for(int i=0;i<rpList.size();i++) {
						RPType rp = rpList.get(i);
						String rpPubRef = null;
						if(rp.getPubRef().size()==1) {
							rpPubRef = rp.getPubRef().get(0).getSrcGuid();
						}
						else if(rp.getPubRef().size()==0||rp.getPubRef().size()>1){
							rpPubRef = "N/A";
						}
						String rpGuid = rp.getGuid();
						String pubPortType = loadICDDataTest.getLocalPortTypeByGuid(rpPubRef);
						String faGuid = loadICDDataTest.getLocalPortGuidyRPGuid(rpGuid);
						String subPortType = loadICDDataTest.getLocalPortTypeByRPGuid(rpGuid);
						String rpDataflowRef = loadICDDataTest.getLocalPortDataflowByGuid(faGuid);
						String rpGuidDef = rp.getGuidDef();
//						System.out.println(rpPubRef+" "+rpDataflowRef+" "+ subPortType+" "+pubPortType+" "+ rpGuid+" "+faGuid);
						seekPath(rpPubRef, rpDataflowRef, subPortType, pubPortType, rpGuid, faGuid,rpGuidDef);
						this.updateProgress(j++, MAX_SIZE);
					}
					this.updateMessage("ICD数据分析完毕！");
					
					long endTime = System.currentTimeMillis();
					System.err.println("错误："+state0+" "+" "+"正确："+state3);
					System.out.println("遍历所有RP消息所需的时间为："+(endTime-startTime));
					
					generateReport();
					System.out.println("导出日志成功");
					
					
					return null;
				}
				
			};
			
			return task;
		}
		
		
	}
	
	// ------------------------------------------------------------------------------
	
	private void seekPath(String PubRef, String DataflowRef,String SubPortType,String PubPortType,String RPGuid,String faGuid,String rpGuidDef) {
		ConstructSceneService service = new ConstructSceneServiceImpl();
		LoadICDDataService loadICDDataService = new LoadICDDataServiceImpl();
		
		
		RPMessage rpMessage = null;
		rpMessage = service.seekPathByPort(PubPortType, SubPortType, RPGuid, faGuid, PubRef, DataflowRef,rpGuidDef);
		rpMessage = service.getHardwareInfo(rpMessage);
		service.getSwitchesInfo(rpMessage);
		loadICDDataService.matchExternalRDIUSectionInterface(rpMessage);
		loadICDDataService.matchExternalSwitchConfig(rpMessage);
		loadICDDataService.matchExternalGPMAnalysis(rpMessage);
		loadICDDataService.matchExternalSceneConfig(rpMessage);
		if(!rpMessage.isError()) {
			state3++;
//			System.out.println(rpMessage.toString());
			RPMessageLibrary.addRPMessage(rpMessage);
//			System.out.println(rpMessage.getRpGuid()+" " +rpMessage.getAllDeviceNodes());
//			if(rpMessage.isLocal()) {
//				System.out.println(rpMessage.toString());
//			}
		}else {
			state0++;
//			System.out.println(rpMessage.getErrorMsg());
			RPMessageLibrary.addRPMessage(rpMessage);
		}
		
	}
	
	private void generateReport() {
//			List<RPMessage> rpMessages = RPMessageLibrary.getRpMessages();
//			List<Report> reports = new ArrayList<Report>();
//			int num=1;
//			for(RPMessage rpMessage:rpMessages) {
//				if(!rpMessage.isError()) {
//					Report report = new Report();
//					report.setKey(num++);
//					report.setSubscribingHardwareName(rpMessage.getSubEndApplication().getHardware().getName());
//					report.setSubscribingAppName(rpMessage.getSubEndApplication().getName());
//					report.setSubscribingAppATA(rpMessage.getSubEndApplication().getAta());
//					report.setSubscribingAppGuid(rpMessage.getSubEndApplication().getGuid());
//					report.setSubscribingPortGuid(rpMessage.getSubEndApplication().getPort().getGuid());
//					report.setSubscribingPortName(rpMessage.getSubEndApplication().getPort().getName());
////					report.setSubscribingPortPhysSpeed(rpMessage.getSubEndApplication().getBiteRate());
//					report.setSubscribingPortType(rpMessage.getSubEndApplication().getPort().getType());
//					
//					reports.add(report);
//				}
//			}
//			ExportExcelUtil.exportExcel(Report.class, reports,"E:\\","test_output");
	}
	
}

package edu.nwpu.edap.edapplugin.ui;

import java.io.File;
import java.util.List;

import com.careri.as.workbench.api.icd.impl.LogServiceExample;
import com.careri.as.workbench.api.misc.LogService;

import edu.nwpu.edap.edapplugin.Service.UploadFileService;
import edu.nwpu.edap.edapplugin.Service.Impl.UploadFileServiceImpl;
import edu.nwpu.edap.edapplugin.handlers.UIControlHandler;
import edu.nwpu.edap.edapplugin.log.OutputLog;
import edu.nwpu.edap.edapplugin.utils.ImportExcelUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import sun.reflect.generics.tree.Tree;

public class UploadFileView extends VBox {

	/**
	 * 使用说明
	 */
	private Text title;
	private Text details;
	private Rectangle divider;

	/**
	 * 文件上传区域
	 */
	private GridPane fileUploadArea;
	private VBox rdiuFile;
	private VBox a664File;
	private VBox gpmFile;
	private VBox dataSenceFile;

	private TextField fPath1;
	private TextField fPath2;
	private TextField fPath3;
	private TextField fPath4;
	

	private Button submit1;
	private Button submit2;
	private Button submit3;
	private Button submit4;

	private TextArea doneMsg1;
	private TextArea doneMsg2;
	private TextArea doneMsg3;
	private TextArea doneMsg4;

	/**
	 * 工具变量
	 */
	private UIControlHandler mainView;
	private String initFilePath = "D:\\WXR\\InterfaceFile";

	private Boolean flag1 = false;
	private Boolean flag2 = false;
	private Boolean flag3 = false;
	private Boolean flag4 = false;

	/**
	 * 构造函数
	 */
	public UploadFileView(UIControlHandler mainView) {
		
		this.mainView = mainView;
		this.mainView.getTopMenuView().getStartBtn().setDisable(true);
		
		this.title = new Text("使用说明");
		this.title.setStyle("-fx-font-weight: bold;-fx-font-size:28px");

		this.details = new Text("●  使用需导入“RDIU区段接口文件”（Excel文件）、“A664区段接口文件”（Excel文件）和“数据传输场景配置文件”（XML文件）；\n\n"
				+ "●  所有文件均导入完毕后，点击【导入ICD数据】按钮，插件开始导入ICD数据；\n\n" 
				+ "●  当插件下方进度条显示导入完毕后，点击【分析ICD数据】按钮，插件开始分析ICD数据；\n\n" 
				+ "●  当插件下方进度条显示分析完毕后，选中左侧ICD目录中的消息，即可查看其网络拓扑图及延迟信息；\n\n"
				+ "●  点击【终止延迟分析】按钮即可终止分析，点击【导出分析报告】按钮即可导出规定格式的延迟分析报告。\n\n");
		this.details.setStyle("-fx-font-size:13px");
		
		LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
			new Stop(100, Color.web("#f4f4f4")),//0,cdcdcd
			new Stop(1, Color.web("#f4f4f4"))
		});
		
		this.divider = new Rectangle();
		divider.setLayoutX(title.getLayoutX());
		divider.setLayoutY(title.getLayoutY()+5);
		divider.setWidth(1100);
		divider.setHeight(1.5);
		divider.setFill(linearGradient);
		
		VBox titleAreaBox = new VBox(5);
		titleAreaBox.getChildren().addAll(title,divider);

		this.fileUploadArea = new GridPane();
		this.rdiuFile = new VBox(10);
		this.a664File = new VBox(10);
		this.gpmFile = new VBox(10);
		this.dataSenceFile = new VBox(10);

		/**
		 * 填充rdiuFile
		 */
		Text fName1 = new Text("RDIU区段接口文件");
		fName1.setStyle("-fx-font-weight: bold;-fx-font-size: 14px;");
		HBox hb1 = new HBox(10);
		this.fPath1 = new TextField();
		
		this.submit1 = new Button("浏览");
		hb1.getChildren().addAll(this.fPath1, this.submit1);
		this.doneMsg1 = new TextArea("点击按钮上传");

		this.rdiuFile.getChildren().addAll(fName1, hb1, this.doneMsg1);

		/**
		 * 填充a664File
		 */
		Text fName2 = new Text("A664区段接口文件");
		fName2.setStyle("-fx-font-weight: bold;-fx-font-size: 14px;");
		HBox hb2 = new HBox(10);
		this.fPath2 = new TextField();
		this.submit2 = new Button("浏览");
		hb2.getChildren().addAll(this.fPath2, this.submit2);
		this.doneMsg2 = new TextArea("点击按钮上传");

		this.a664File.getChildren().addAll(fName2, hb2, this.doneMsg2);
		
		/**
		 * 填充gpm文件
		 */
		Text fName3 = new Text("GPM接口文件");
		fName3.setStyle("-fx-font-weight: bold;-fx-font-size: 14px;");
		HBox hb3 = new HBox(10);
		this.fPath3 = new TextField();
		this.submit3 = new Button("浏览");
		hb3.getChildren().addAll(this.fPath3, this.submit3);
		this.doneMsg3 = new TextArea("点击按钮上传");

		this.gpmFile.getChildren().addAll(fName3, hb3, this.doneMsg3);

		/**
		 * 填充dataSenceFile
		 */
		Text fName4 = new Text("数据传输场景配置文件");
		fName4.setStyle("-fx-font-weight: bold;-fx-font-size: 14px;");
		HBox hb4 = new HBox(10);
		this.fPath4 = new TextField();
		this.submit4 = new Button("浏览");
		hb4.getChildren().addAll(this.fPath4, this.submit4);
		this.doneMsg4 = new TextArea("点击按钮上传");

		this.dataSenceFile.getChildren().addAll(fName4, hb4, this.doneMsg4);

		/**
		 * 填充根节点
		 */
		this.setSpacing(20);
		this.setPadding(new Insets(50));

		this.getChildren().addAll(titleAreaBox,this.details, this.fileUploadArea, this.gpmFile);

		/**
		 * 设置样式
		 */
		this.fileUploadArea.setGridLinesVisible(false);
		this.fileUploadArea.setAlignment(Pos.CENTER);
		this.fileUploadArea.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 1;"
				+ "-fx-border-radius: 5;" + "-fx-border-color: #cdcdcd;");

		this.fileUploadArea.add(this.rdiuFile, 0, 0);
		this.fileUploadArea.add(this.a664File, 1, 0);
		this.fileUploadArea.add(this.gpmFile, 2, 0);
		this.fileUploadArea.add(this.dataSenceFile, 3, 0);
		this.fileUploadArea.setHgap(60);
		this.fileUploadArea.setMaxHeight(200);
		this.fileUploadArea.setMaxWidth(1100);
		
		HBox.setHgrow(fPath1, Priority.ALWAYS);
		HBox.setHgrow(fPath2, Priority.ALWAYS);
		HBox.setHgrow(fPath3, Priority.ALWAYS);
		HBox.setHgrow(fPath4, Priority.ALWAYS);

		GridPane.setVgrow(rdiuFile, Priority.ALWAYS);
		GridPane.setHgrow(rdiuFile, Priority.ALWAYS);

		GridPane.setVgrow(a664File, Priority.ALWAYS);
		GridPane.setHgrow(a664File, Priority.ALWAYS);
		
		GridPane.setVgrow(gpmFile, Priority.ALWAYS);
		GridPane.setHgrow(gpmFile, Priority.ALWAYS);

		GridPane.setVgrow(dataSenceFile, Priority.ALWAYS);
		GridPane.setHgrow(dataSenceFile, Priority.ALWAYS);
		

		addListeners();
	}

	private void addListeners() {
		
		this.submit1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent event) {
            	
            	
            	Stage stage = new Stage();
            	FileChooser fileChooser = new FileChooser();
            	fileChooser.setTitle("导入RDIU区段接口文件");
            	fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("xlsx", "*.xlsx"));
            	
            	fileChooser.setInitialDirectory(new File(initFilePath));
                
                File RDIUFile = fileChooser.showOpenDialog(stage);
                
		        
				if(RDIUFile==null) {
					System.out.println("未选择文件");
				}else {
					
					fPath1.setText(RDIUFile.getParent());
	            	doneMsg1.setText("√ " + RDIUFile.getName());
	            	
	                flag1 = true;
	                enableStartBtn(flag1, flag2, flag3,flag4);
	                
					String RDIUPath = RDIUFile.getAbsolutePath();//这里可能会存在空指针异常

					try {
						
						ImportExcelUtil importExcelUtil = new ImportExcelUtil();
						importExcelUtil.setExternalRDIUList(RDIUPath);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
            		
            	
            }
        });
		
		this.submit2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	
            	Stage stage = new Stage();
            	FileChooser fileChooser = new FileChooser();
            	fileChooser.setTitle("导入A664区段接口文件");
            	fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("xml", "*.xml"));
            	
            	fileChooser.setInitialDirectory(new File(initFilePath));
            	
                File A664File = fileChooser.showOpenDialog(stage);
				if(A664File==null) {
					System.out.println("未选择文件");
				}else {
					String A664Path = A664File.getAbsolutePath();//这里可能会存在空指针异常
					UploadFileService service = new UploadFileServiceImpl();
					Pair<Boolean, String> res = service.uploadSwitchConfig(A664Path);
					System.out.println(res.getKey()+" "+res.getValue());
				}
                
            	fPath2.setText(A664File.getParent());
            	doneMsg2.setText("√ " +A664File.getName());
            	
                flag2 = true;
                enableStartBtn(flag1, flag2, flag3,flag4);
            }
        });
		
		this.submit3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            	Stage stage = new Stage();
            	FileChooser fileChooser = new FileChooser();
            	fileChooser.setTitle("导入GPM接口文件");
            	fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("xml", "*.xml"));
            	
            	fileChooser.setInitialDirectory(new File(initFilePath));
				
				List<File> gpmFile = fileChooser.showOpenMultipleDialog(stage);
				if(gpmFile==null) {
					System.out.println("未选择文件");
				}else {
					for(File file:gpmFile) {
						String gpmPath = file.getAbsolutePath();//这里可能会存在空指针异常
						UploadFileService service = new UploadFileServiceImpl();
						Pair<Boolean, String> res = service.uploadGPMReport(gpmPath);
						System.out.println(res.getKey()+" "+res.getValue());
					}
				}
                
            	fPath3.setText(gpmFile.get(0).getParent());
            	String s = "";
            	for(int i=0;i<gpmFile.size();i++) {
            		s += "√ " + gpmFile.get(i).getName() + "\n";
            	}
            	doneMsg3.setText(s);
            	
                flag3 = true;
                enableStartBtn(flag1, flag2, flag3,flag4);
            }
        });
		
		this.submit4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            	Stage stage = new Stage();
            	FileChooser fileChooser = new FileChooser();
            	fileChooser.setTitle("导入数据传输场景配置文件");
            	fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("xml", "*.xml"));
            	
            	fileChooser.setInitialDirectory(new File(initFilePath));
            	
                
                File sceneFile = fileChooser.showOpenDialog(stage);
				if(sceneFile==null) {
					System.out.println("未选择文件");
				}else {
					String scenePath = sceneFile.getAbsolutePath();//这里可能会存在空指针异常
					UploadFileService service = new UploadFileServiceImpl();
					Pair<Boolean,String> res = service.uploadSceneConfig(scenePath);
					System.out.println(res.getKey()+" "+res.getValue());
				}
                
            	fPath4.setText(sceneFile.getParent());
            	doneMsg4.setText("√ " + sceneFile.getName());
            	
                flag4 = true;
                enableStartBtn(flag1, flag2, flag3,flag4);
            }
        });
		
	}

	/**
	 * 判断所有文件是否上传完毕
	 * 
	 * @param flag1
	 * @param flag2
	 * @param flag3
	 */
	private void enableStartBtn(Boolean flag1, Boolean flag2, Boolean flag3, Boolean flag4) {
		if (flag1 && flag2 && flag3 && flag4) {
			this.mainView.getTopMenuView().getStartBtn().setDisable(false);
		}
	}
}

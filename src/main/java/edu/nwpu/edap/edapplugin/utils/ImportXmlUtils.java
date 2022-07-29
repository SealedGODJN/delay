package edu.nwpu.edap.edapplugin.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import edu.nwpu.edap.edapplugin.bean.external.sceneConfig.DeviceNode;
import edu.nwpu.edap.edapplugin.bean.external.sceneConfig.Scenario;
import edu.nwpu.edap.edapplugin.exception.XMLDocumentException;
import edu.nwpu.edap.edapplugin.exception.XMLPropertyLossException;
import edu.nwpu.edap.edapplugin.library.SceneConfigLibrary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ImportXmlUtils {

    public static void importSceneConfigFile(String fileName) {
        try {
            Document document = loadXML(fileName);
            if(document == null) {
                System.err.println("上传的document为空");
                return;
            }
            parseSceneConfig(document);
        }catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    public static <T> T convertXmlFileToObject(Class<T> clazz,String filePath) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader fr = null;
            fr = new FileReader(filePath);
            xmlObject = unmarshaller.unmarshal(fr);
        }catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }catch (JAXBException jaxbe) {
            jaxbe.printStackTrace();
        }
        return (T)xmlObject;
    }

    /***
     * 根据文件路径加载document
     * @param filename
     * @throws XMLDocumentException
     * return
     */
    private static Document loadXML(String filename) throws XMLDocumentException{
        Document document;
        try{
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(new File(filename));
        }catch (DocumentException e){
            throw new XMLDocumentException(e.getMessage());
        }
        return document;
    }

    /**
     * 解析数据传输场景配置文件
     * @throws XMLPropertyLossException
     */
    private static void parseSceneConfig(Document document) throws XMLPropertyLossException{
        Element root =  document.getRootElement();
        SceneConfigLibrary.setNum(Integer.parseInt(root.attributeValue("Num")));
        ArrayList<Scenario> scenarios = new ArrayList<>();
        List elements = root.elements("Scene");
        //解析Scene
        for(Iterator it = elements.iterator();it.hasNext();){
            Element childElement = (Element) it.next();
            String idElement = childElement.attributeValue("Id");
            String numElement = childElement.attributeValue("Num");
            try{
                if(idElement==null){
                    throw new XMLPropertyLossException("<Scene>","'Id'");
                }else if(numElement == null){
                    throw new XMLPropertyLossException("<Scene>","'Num'");
                }
            }catch (XMLPropertyLossException e){
                e.printStackTrace();
            }
            int id = Integer.parseInt(idElement);
            int num = Integer.parseInt(numElement);
            ArrayList<DeviceNode> deviceNodes = new ArrayList<>();
            List nodeElements = childElement.elements("Node");
            //解析Node
            for(Iterator it2 = nodeElements.iterator();it2.hasNext();){
                Element grandchildElement = (Element) it2.next();
                String pos = grandchildElement.attributeValue("Pos");
                String type = grandchildElement.attributeValue("Type");
                double latencyThreshold = Double.parseDouble(grandchildElement.attributeValue("LatencyThreshold"));
                double jitterThreshold = Double.parseDouble(grandchildElement.attributeValue("JitterThreshold"));
                boolean count = Boolean.parseBoolean(grandchildElement.attributeValue("Count"));
                DeviceNode deviceNode = new DeviceNode(type, pos, latencyThreshold, jitterThreshold, count);
                deviceNodes.add(deviceNode);
            }
            Scenario scenario = new Scenario(id,num,deviceNodes);
            scenario.mapDeviceNode2Map();
            boolean isValid = scenario.check();
            if(!isValid) {
                System.err.println("该场景不符合条件"+scenario.getId());
            }
            scenarios.add(scenario);
        }
        SceneConfigLibrary.setScenarios(scenarios);
    }
}

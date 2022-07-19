package edu.nwpu.edap.edapplugin.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

/**
 * 用于加载FMXL文件的工具类
 * 
 * @author Wren
 * @date 2022年2月25日
 * @param <T>
 *
 */
public class LoadData<T>{
    public T controller;
    public Node node;
    public LoadData (String loc, Class<T> type) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        this.node = (Node) loader.load(getClass().getResourceAsStream(loc));
        this.controller = type.cast(loader.getController());
    }
}
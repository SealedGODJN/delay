package edu.nwpu.edap.edapplugin.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Bubble视图控制类
 * 
 * @author Wren
 * @date 2022年2月25日
 *
 */
public class BubbleViewController implements Initializable {

    @FXML
    public Rectangle coverRectangle;

    @FXML
    private
    TextField textField;

    @FXML
    public Label titleLabel;
    
    @FXML
    public Label nodeTypeLabel;

    @FXML
    Circle top;

    @FXML
    Circle right;

    @FXML
    Circle bottom;

    @FXML
    Circle left;
    
    

    /**
     * 初始化Bubble视图
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titleLabel.toFront();
    }

    @FXML
    private void Typed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            this.titleLabel.setVisible(true);
            this.textField.setVisible(false);

            textField.setFocusTraversable(false);
            textField.setEditable(false);
            this.coverRectangle.setVisible(true);
        }
    }

    
}
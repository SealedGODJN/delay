package edu.nwpu.edap.edapplugin.bean;

import javafx.beans.property.SimpleStringProperty;

public class InfoTableData {

    private SimpleStringProperty  property;
    private SimpleStringProperty  value;

    public InfoTableData(String  string, String  string2) {
        super();
        this.property = new SimpleStringProperty(string);
        this.value = new SimpleStringProperty(string2);
    }

    public String getProperty() {
        return this.property.get();
    }

    public void setProperty(String  property) {
        this.property.set(property);
    }

    public String  getValue() {
        return this.value.get();
    }

    public void setValue(String  value) {
        this.value.set(value);
    }

}

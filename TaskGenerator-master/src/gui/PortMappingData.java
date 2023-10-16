/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author djordje
 */
public class PortMappingData {

    private StringProperty template1Out = new SimpleStringProperty();
    private StringProperty template2In = new SimpleStringProperty();

    public String getTemplate1Out() {
        return template1Out.get();
    }

    public void setTemplate1Out(String t1Out) {
        this.template1Out.set(t1Out);
    }

    public StringProperty template1OutProperty() {
        return template1Out;
    }

    public String getTemplate2In() {
        return template2In.get();
    }

    public void setTemplate2In(String t2IN) {
        this.template2In.set(t2IN);
    }

    public StringProperty template2InProperty() {
        return template2In;
    }
}

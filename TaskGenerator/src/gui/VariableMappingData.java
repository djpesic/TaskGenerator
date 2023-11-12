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
public class VariableMappingData {

    private StringProperty currentName = new SimpleStringProperty("currentName");
    private StringProperty newName = new SimpleStringProperty("newName");

    public String getCurrentName() {
        return currentName.get();
    }

    public void setCurrentName(String currentName) {
        this.currentName.set(currentName);
    }

    public StringProperty currentNameProperty() {
        return currentName;
    }

    public String getNewName() {
        return newName.get();
    }

    public void setNewName(String newName) {
        this.newName.set(newName);
    }

    public StringProperty newNameProperty() {
        return newName;
    }
}

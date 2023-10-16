/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.jaxbsupport;

import Localization.Language;
import programSegment.ConstType;
import programSegment.IfType;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import gui.treedata.TGException;
import gui.treedata.ViewControllMethodsArchive;
import static gui.treedata.ViewControllMethodsArchive.*;

/**
 *
 * @author Nest
 */
public abstract class BaseElement {

    protected TitledPane titledPane;
    protected VBox vBox;
    protected ProgramHolder parent;

    public TitledPane getTitledPane() {
        return titledPane;
    }

    public VBox getVBox() {
        return vBox;
    }

    protected BaseElement(VBox vBox) {
        this.vBox = vBox;
    }

    protected BaseElement(TitledPane titledPane, VBox vBox) {
        this.titledPane = titledPane;
        this.vBox = vBox;
        vBox.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                myAction(event, event.getY());
            }

        });
        titledPane.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (parent != null) {
                    final int padding = ViewControllMethodsArchive.PANE_PADDING;

                    double x = event.getX();
                    double y = event.getY();

                    // height and width size where the padding region begins
                    double width = titledPane.getWidth() - padding;
                    double height = titledPane.getHeight() - padding;

                    //when clicked on padding detect it as clicking parent
                    if (x < padding || x > width || y < padding || y > height) {
                        parent.myAction(event, y + titledPane.getLayoutY());
                    }
                }
            }

        });
    }

    protected abstract void myAction(MouseEvent event, double positionY);

    public void setParent(ProgramHolder parent) {
        this.parent = parent;
    }

    protected void addGridPaneRow(Control c11, Control c12, Control c22, Control c32,
            Control c42, GridPane pane, boolean dummy) {

        GridPane.setConstraints(c11, 1, 1);
        pane.getChildren().addAll(c11);

        addGridPaneRow(c12, c22, c32, c42, 2, pane, dummy);
    }

    protected void addGridPaneRow(Control c1r, Control c2r, Control c3r, Control c4r,
            int row, GridPane pane, boolean dummy) {
        GridPane.setConstraints(c1r, 1, row);
        GridPane.setConstraints(c2r, 2, row);
        GridPane.setConstraints(c3r, 3, row);
        GridPane.setConstraints(c4r, 4, row);

        pane.setPadding(new Insets(ViewControllMethodsArchive.GRID_PANE_PADDING));
        pane.setHgap(ViewControllMethodsArchive.GRID_PANE_GAP);
        pane.getChildren().addAll(c1r, c2r, c3r, c4r);

        if (dummy) {
            Label dummyLabel = new Label();
            GridPane.setConstraints(dummyLabel, 5, row);
            pane.getChildren().addAll(dummyLabel);
            setWidth(c1r, c2r, c3r, c4r, dummyLabel);
        } else {
            setWidth(c1r, c2r, c3r, c4r);
        }
    }

    protected void setWidth(Control c1, Control c2, Control c3, Control c4) {
        if (c1 != null) {
            c1.prefWidthProperty().bind(vBox.widthProperty().
                    multiply(FIRST_ELEMENT_WIDTH));
        }
        if (c2 != null) {
            c2.prefWidthProperty().bind(vBox.widthProperty().
                    multiply(SECOND_ELEMENT_WIDTH));
        }
        if (c3 != null) {
            c3.prefWidthProperty().bind(vBox.widthProperty().
                    multiply(THIRD_ELEMENT_WIDTH));
        }
        if (c4 != null) {
            c4.prefWidthProperty().bind(vBox.widthProperty().
                    multiply(FOURTH_ELEMENT_WIDTH));
        }
    }

    protected void setWidth(Control c1, Control c2, Control c3, Control c4, Control dummy) {
        if (c1 != null) {
            c1.prefWidthProperty().bind(vBox.widthProperty().
                    multiply(FIRST_ELEMENT_WIDTH));
        }
        if (c2 != null) {
            c2.prefWidthProperty().bind(vBox.widthProperty().
                    multiply(SECOND_ELEMENT_WIDTH));
        }
        if (c3 != null) {
            c3.prefWidthProperty().bind(vBox.widthProperty().
                    multiply(THIRD_ELEMENT_WIDTH));
        }
        if (c4 != null) {
            c4.prefWidthProperty().bind(vBox.widthProperty().
                    multiply(SECOND_ELEMENT_WIDTH));
        }
        if (dummy != null) {
            dummy.prefWidthProperty().bind(vBox.widthProperty().
                    multiply(FIFTH_DUMMY_ELEMENT_WIDTH));
        }
    }

    public abstract void loadLanguage(Language language);

    public void generateOutput(ConstType constType) throws TGException {
    }

    public void generateOutput(IfType ifType) throws TGException {
    }
}

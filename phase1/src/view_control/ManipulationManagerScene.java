package view_control;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;


public class ManipulationManagerScene{

    File inputfile;


    public static void display() {
        Stage window = new Stage();
        window.setTitle("Manipulation Scene");
        Button Add = new Button("Add New Tag");
        Button Delete = new Button("Delete Tag");
        Button Select = new Button("Select Old Tag");
        Button Move = new Button("Move To");
        Button Back = new Button("Back");

        Add.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                    AddTagScene.display();
                }
            }
        );
//        Delete.setOnAction(
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(final ActionEvent e) {
//                        DeleteTagScene.display();
//                    }
//                }
//        );
//        Select.setOnAction(
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(final ActionEvent e) {
//                        SelectTagScene.display();
//                    }
//                }
//        );
//        Move.setOnAction(
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(final ActionEvent e) {
//                        MoveImageScene.display();
//                    }
//                }
//        );
//        Back.setOnAction(
//                new EventHandler<ActionEvent>() {
//                    @Override
//                    public void handle(final ActionEvent e) {
//                        FileChooserScene.display();
//                    }
//                }
//        );


        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(Add, Delete, Select, Move, Back);
        layout1.setAlignment(Pos.CENTER);
        Scene general = new Scene(layout1, 200, 300);
        window.setScene(general);
        window.show();


    }


}
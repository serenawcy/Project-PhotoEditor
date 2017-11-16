package view_control;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AddTagScene {

//    private File inputFile;


    public static void display() {
        Stage addScene = new Stage();
//        addScene.initModality(Modality.APPLICATION_MODAL);
        addScene.setTitle("Add tag(s)");
        addScene.setMinWidth(250);
        Label AddInstruction = new Label();
        AddInstruction.setText("Please enter tag(s), separated with \",\"");

        //Create two buttons
        Button done = new Button("Done");
        Button goBack = new Button("Go back");
        //Form a Text area
        TextField tagInput = new TextField();

        // Clicking will addTags and close window
        done.setOnAction(
                e -> {
                    String tags = tagInput.getText();
                    System.out.println(tags);
                    //            File.addTags(tags);
                    addScene.close();
                });
        goBack.setOnAction(e -> {
            FileChooserScene.display();
            addScene.close();
//            ManipulationManagerScene.setFile(inputFile);
//            ManipulationManagerScene.display();
        });

        VBox addTagLayout = new VBox(10);
        addTagLayout.setPadding(new Insets(20, 20, 20, 20));
        addTagLayout.getChildren().addAll(AddInstruction,tagInput, goBack, done);
        addTagLayout.setAlignment(Pos.CENTER);

        Scene tagScene = new Scene(addTagLayout);
        addScene.setScene(tagScene);
        addScene.show();
//        window.showAndWait();


//    public void setFile(File file){
//        this.inputFile = file;
//    }
    }
}

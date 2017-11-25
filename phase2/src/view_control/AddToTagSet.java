package view_control;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ImageFile;

import java.io.IOException;
import java.util.Objects;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import model.ImageFile;
import model.TagManager;

import java.io.IOException;
import java.util.Objects;

class AddToTagSet {

    /** Magic number 250 */
    private static final int MAGIC250 = 250;

    /** Magic number 100 */
    private static final int MAGIC100 = 100;

    /** Magic number 10 */
    private static final int MAGIC10 = 10;

    /** Magic number 20 */
    private static final int MAGIC20 = 20;

    /** Initialize an ImageFile */
    private static ImageFile inputFile;

    /** Display the Scene and construct the buttons. */
    static void display() {
        Stage addScene = new Stage();
        addScene.initModality(Modality.APPLICATION_MODAL);
        addScene.setTitle("Add tag(s) to tag Set");
        addScene.setMinWidth(MAGIC250);
        Label AddInstruction = new Label();
        AddInstruction.setText("Please enter tag(s), separated with \",\"");

        // Create two buttons
        Button done = new Button("Done");
        Button goBack = new Button("Go back");
        // Form a Text area
        TextField tagInput = new TextField();

        done.setOnAction(
                e -> {
                    String serPath = "./serializedTagFiles.ser";
                    try {
                        TagManager tagManager = new TagManager(serPath);
                    } catch (ClassNotFoundException | IOException e1) {
                        e1.printStackTrace();
                    }
                    String tags = tagInput.getText();
                    if (!Objects.equals(tags, "" ) && inputFile != null) {
                        try {
                            TagManager.add(tags);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        tagInput.clear();
                        ManipulationManagerScene.setImageListView(ManipulationManagerScene.imgFiles);
                    }
                });

        goBack.setOnAction(e -> addScene.close());

        done.setMinWidth(MAGIC100);
        goBack.setMinWidth(MAGIC100);
        VBox addTagLayout = new VBox(MAGIC10);
        addTagLayout.setPadding(new Insets(MAGIC20, MAGIC20, MAGIC20, MAGIC20));
        addTagLayout.getChildren().addAll(AddInstruction, tagInput, done, goBack);
        addTagLayout.setAlignment(Pos.CENTER);

        Scene tagScene = new Scene(addTagLayout);
        addScene.setScene(tagScene);
        addScene.showAndWait();
    }

    /**
     * Set the ImageFile
     *
     * @param imageFile the ImageFile
     */
    static void setImageFile(ImageFile imageFile) {
        inputFile = imageFile;
    }
}

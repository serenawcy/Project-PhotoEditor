package view_control;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.ImageFile;
import model.ImageFileManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ManipulationManagerScene extends Application {

    /** Initialize a new ImageFile Object */
    private static ImageFile imgFile;

    /** Initialize a new ImageView Object */
    private static ImageView imageView = new ImageView();

    /** Initialize a new Scene to display the log text */
    private static Scene logTextScene;

    /** Initialize a logListView for the logTextScene to display the log text */
    private static ListView<String> logListView = new ListView<>();

    private static BorderPane inputGridPane = new BorderPane();

    private static VBox generalLayout = new VBox(20);

    /** Magic number 10 */
    private static final int MAGIC10 = 10;

    /** Magic number 50 */
    private static final int MAGIC50 = 50;

    /** Magic number 1000 */
    private static final int MAGIC1000 = 1000;

    /** Magic number 20 */
    private static final int MAGIC20 = 20;

    /** Magic number 200 */
    private static final int MAGIC200 = 200;

    /** Magic number 30 */
    private static final int MAGIC30 = 30;

    /** Magic number 800 */
    private static final int MAGIC800 = 800;

    /** Magic number 650 */
    private static final int MAGIC650 = 650;

    /** Magic number 120 */
    private static final int MAGIC120 = 120;

    /** Magic number 250 */
    private static final int MAGIC250 = 250;

    /** Magic number 550 */
    private static final int MAGIC550 = 550;

    /** Magic number 500 */
    private static final int MAGIC500 = 500;

    /** Magic number 1350 */
    private static final int MAGIC1350 = 1350;

    private static Label path = new Label();

    static ArrayList<ImageFile> imgFiles = new ArrayList<>();

    private static ListView<String> imgListView = new ListView<>();

    private static Stage window = new Stage();

    private StackPane paneCenter = new StackPane();

    private static ListView<String> tagsView = new ListView<>();



    public static void main(String[] args) {
        Application.launch(args);
    }


    /** Display the Scene and construct the buttons. */
    @Override
    public void start(Stage primaryStage) throws Exception {

        window.setTitle("Image Editor");
        Button add = new Button("Add New Tag");
        add.setMinWidth(MAGIC120);
        Button delete = new Button("Delete Tag");
        delete.setMinWidth(MAGIC120);
        Button selectOldTag = new Button("Select Old Tag");
        selectOldTag.setMinWidth(MAGIC120);
        Button move = new Button("Move To");
        move.setMinWidth(MAGIC120);
        Button quit = new Button("Quit The Program");
        quit.setMinWidth(MAGIC120);
        Button rename = new Button("Rename");
        rename.setMinWidth(MAGIC120);
        Button getLog = new Button("Get Log History");
        getLog.setMinWidth(MAGIC120);
        Button goBack = new Button("Go Back");
        goBack.setMinWidth(MAGIC120);
        Button openButton = new Button("Choose A Directory");
        openButton.setMinWidth(MAGIC120);

        Button selectImgFile = new Button("Select Image");
        openButton.setMinWidth(MAGIC120);
        selectImgFile.setMinWidth(MAGIC120);

        VBox logLayout = new VBox(MAGIC20);
        logTextScene = new Scene(logLayout, MAGIC1350, MAGIC1000);

        add.setOnAction(
                (ActionEvent e) -> {
                    AddTagScene.setImageFile(imgFile);
                    AddTagScene.display();
                });

        delete.setOnAction(
                (ActionEvent e) -> {
                    DeleteTagScene.setImageFile(imgFile);
                    DeleteTagScene.display();
                });
        selectOldTag.setOnAction(
                (ActionEvent e) -> {
                    SelectTagScene.setImageFile(imgFile);
                    SelectTagScene.display();
                });
        move.setOnAction(
                (ActionEvent e) -> {
                    MoveFileScene.setImageFile(imgFile);
                    MoveFileScene.display();
                });

        quit.setOnAction((ActionEvent event) -> window.close());

        rename.setOnAction(
                (ActionEvent e) -> {
                    FileRenameScene.setImageFile(imgFile);
                    FileRenameScene.display();
                });

        logLayout.getChildren().add(goBack);

        getLog.setOnAction(
                (ActionEvent e) -> {
                    logLayout.getChildren().remove(logListView);
                    logListView.getItems().clear();

                    if (imgFile != null) {
                        for (String logHistory : imgFile.getLog()) {
                            logListView.getItems().add(logHistory);
                        }
                    }
                    logLayout.getChildren().add(logListView);
                    window.setScene(logTextScene);
                });

        openButton.setOnAction(
                (ActionEvent e) -> {
                    ArrayList<ImageFile> directoryImageFile = new ArrayList<>();
                    DirectoryChooser directoryChooser = new DirectoryChooser();

                    File selectedDirectory = directoryChooser.showDialog(window);
                    String serPath = "./serializedImageFiles.ser";
                    try {
                        ImageFileManager imageFileManager = new ImageFileManager(serPath);
                    } catch (ClassNotFoundException | IOException e1) {
                        e1.printStackTrace();
                    }
                    if (selectedDirectory != null) {
                        for (File file : selectedDirectory.listFiles()) {
                            if (file.getName().toLowerCase().endsWith(".jpg")
                                    || file.getName().toLowerCase().endsWith(".jpeg")
                                    || file.getName().toLowerCase().endsWith(".gif")
                                    || file.getName().toLowerCase().endsWith(".png")) {
                                    boolean checkFileExist = false;
                                    ImageFile inputFile = null;
                                    try {
                                        inputFile = new ImageFile(file);
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                    ArrayList<ImageFile> imageFiles = ImageFileManager.getImageFileList();

                                    for (ImageFile imgFile : imageFiles) {
                                        if (imgFile.equals(inputFile)) {
                                            inputFile = imgFile;
                                            checkFileExist = true;
                                        }
                                    }
                                    if (!checkFileExist) {
                                        try {
                                            ImageFileManager.add(inputFile);
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }
                                    }
                                directoryImageFile.add(inputFile);
                            }
                            imgFiles = directoryImageFile;
                            setImageListView(imgFiles);
                        }
                    }
                });

        selectImgFile.setOnAction((ActionEvent event) -> {
            try {
                buttonClicked();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        inputGridPane.setPrefSize(MAGIC800,MAGIC800);
        Label currentTags = new Label("Current Tags");
        Pane pathArea = new Pane();
        pathArea.setPrefSize(MAGIC200,MAGIC30);
        pathArea.getChildren().add(path);
        pathArea.setTranslateX(MAGIC200);
        ToolBar toolbar = new ToolBar();
        toolbar.getItems().addAll(openButton,selectImgFile, pathArea ,currentTags);
        currentTags.setTranslateX(MAGIC650);
        ToolBar toolbarBottom = new ToolBar();

        FlowPane divisionBottom = new FlowPane();
        divisionBottom.setMaxWidth(MAGIC250);

        toolbarBottom.getItems().addAll(divisionBottom, add, delete, selectOldTag,rename,move,getLog);

        paneCenter.setStyle("-fx-background-color: #f5f5dc");
        inputGridPane.setCenter(paneCenter);
        inputGridPane.setTop(toolbar);
        inputGridPane.setLeft(imgListView);
        inputGridPane.setRight(tagsView);
        inputGridPane.setBottom(toolbarBottom);
        inputGridPane.getChildren().add(currentTags);

        generalLayout.getChildren().addAll(inputGridPane, quit);
        final Scene general = new Scene(generalLayout, MAGIC1350, MAGIC1000);
        goBack.setOnAction((ActionEvent event) -> window.setScene(general));

        window.setScene(general);
        window.show();
    }

    /** Get the Image that user selected and show it onto the scene. */
    private void setImage() {
        BorderPane.clearConstraints(imageView);
        Image img = new Image(imgFile.getFile().toURI().toString());
        imageView = new ImageView(img);
        imageView.setFitHeight(MAGIC500);
        imageView.setFitWidth(MAGIC550);

        paneCenter.getChildren().add(imageView);
        StackPane.setMargin(imageView,new Insets(MAGIC50,MAGIC10,MAGIC50,MAGIC50));
    }

    /**
     * Pass in the file
     *
     * @param imageFile the
     */
    private static void setFile(ImageFile imageFile) throws IOException {
 //       inputGridPane.getChildren().remove(path);
        imgFile = imageFile;
        path.setText(imgFile.getFile().getAbsolutePath());
        for (String tags: imgFile.getExistTag()){
            tagsView.getItems().add(tags);
        }

    }

    private void buttonClicked() throws IOException {
        ObservableList<String> names;
        names = imgListView.getSelectionModel().getSelectedItems();
        String selectedFile = "";
        for (String name : names) {
            selectedFile = name;
        }

        if (!selectedFile.equals("")) {
            for (ImageFile imageFile : imgFiles) {
                if (imageFile.getFile().getName().equals(selectedFile)) {
                    tagsView.getItems().clear();
                    setFile(imageFile);
                    setImage();
                }
            }
        }
    }

    static void setImageListView(ArrayList<ImageFile> imgList) {
        imgListView.getItems().clear();
        for (ImageFile file : imgList) {
            imgListView.getItems().add(file.getFile().getName());
        }
        if(imgFile != null) {
            path.setText(imgFile.getFile().getAbsolutePath());
            tagsView.getItems().clear();
            for (String tags: imgFile.getExistTag()){
                tagsView.getItems().add(tags);
            }
        }
    }
}
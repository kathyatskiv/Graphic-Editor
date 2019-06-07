package sample;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Graphic Editor");

        primaryStage.setScene(new Scene(root, 510, 550));

        ///setInterface(root);
        primaryStage.show();
    }

//
//    private void setInterface(FlowPane root){
//        //Top menu
//
//        MenuItem newFile = new MenuItem("New file");
//        MenuItem openFile = new MenuItem("Open file");
//        MenuItem saveFale = new MenuItem("SaveFile");
//        Menu file = new Menu("File");
//        file.getItems().addAll(newFile, openFile, saveFale);
//
//        MenuItem rotate90clockwise = new MenuItem("Rotate for 90' clockwise");
//        MenuItem rotate90counterClockwise = new MenuItem("Rotate for 90' counter-clockwise");
//        MenuItem flipHorizon = new MenuItem("Flip horizon");
//        MenuItem flipVertical = new MenuItem("FlipVertical");
//        Menu edit = new Menu("Edit");
//        edit.getItems().addAll(rotate90clockwise, rotate90counterClockwise, flipHorizon, flipVertical);
//
//        MenuBar topMenu = new MenuBar(file,edit);
//        topMenu.setMaxWidth(700);
//        topMenu.setMinWidth(700);
//
//        //Tools menu
//
//        ImageView brushImg = new ImageView(new Image("\\img\\brush.png"));
//        brushImg.setFitWidth(30);
//        brushImg.setFitHeight(30);
//        Button brush = new Button(null, brushImg);
//
//        ImageView eraserImg = new ImageView(new Image("\\img\\eraser.png"));
//        eraserImg.setFitWidth(30);
//        eraserImg.setFitHeight(30);
//        Button eraser = new Button(null, eraserImg);
//
//
//
//        GridPane toolsMenu = new GridPane();
//        toolsMenu.add(brush, 0, 0);
//        toolsMenu.add(eraser, 1, 0);
//
//
//        //Item menu
//
//        ToggleGroup colors = new ToggleGroup();
//
//        ToggleButton red = new ToggleButton();
//        red.setStyle("-fx-base: red");
//        red.setToggleGroup(colors);
//        red.setMaxSize(25,25);
//        red.setMinSize(25,25);
//
//        ToggleButton green = new ToggleButton();
//        green.setStyle("-fx-base: green");
//        green.setToggleGroup(colors);
//        green.setMaxSize(25,25);
//        green.setMinSize(25,25);
//
//        ToggleButton blue = new ToggleButton();
//        blue.setStyle("-fx-bse: blue");
//        blue.setToggleGroup(colors);
//        blue.setMaxSize(25,25);
//        blue.setMinSize(25,25);
//
//        ToggleButton yellow = new ToggleButton();
//        yellow.setStyle("-fx-base: yellow");
//        yellow.setToggleGroup(colors);
//        yellow.setMaxSize(25,25);
//        yellow.setMinSize(25,25);
//
//        ToggleButton purple = new ToggleButton();
//        purple.setStyle("-fx-base: purple");
//        purple.setToggleGroup(colors);
//        purple.setMaxSize(25,25);
//        purple.setMinSize(25,25);
//
//        ToggleButton grey = new ToggleButton();
//        grey.setStyle("-fx-base: grey");
//        grey.setToggleGroup(colors);
//        grey.setMaxSize(25,25);
//        grey.setMinSize(25,25);
//
//        ToggleButton black = new ToggleButton();
//        black.setStyle("-fx-base: black");
//        black.setToggleGroup(colors);
//        black.setMaxSize(25,25);
//        black.setMinSize(25,25);
//
//        HBox colorsPanel = new HBox(red, green, blue, yellow, purple, grey, black);
//
//        Label sizeTittle = new Label("    size: "+  "   ");
//        Slider size = new Slider(0, 100, 10);
//
//        HBox itemPanel = new HBox(colorsPanel, sizeTittle, size);
//        itemPanel.setPadding(new Insets(10, 0, 10, 0));
//
//
//        //Whole controler panel
//        VBox controllerPanel = new VBox(topMenu, itemPanel);
//
//        root.getChildren().addAll(controllerPanel, toolsMenu);
//
//
//    }


    public static void main(String[] args) {
        launch(args);
    }
}

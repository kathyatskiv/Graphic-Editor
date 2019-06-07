package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.Slider;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javax.imageio.ImageIO;
import java.io.File;
import java.net.MalformedURLException;


public class Controller {

    @FXML
    private Canvas canvas;

    @FXML
    private ColorPicker color;

    @FXML
    private ToggleButton brush;

    @FXML
    private ToggleButton erase;

    @FXML
    private ToggleButton gradient;

    @FXML
    private ToggleButton crop;

    @FXML
    private ToggleButton oval;

    @FXML
    private ToggleButton stroke;

    @FXML
    private ToggleButton rectangle;

    @FXML
    private ToggleButton colorPicker;

    @FXML
    private Label sizeValue;

    @FXML
    private Slider sizeSlider;

    @FXML
    private MenuItem newFile;

    @FXML
    private MenuItem openFile;

    @FXML
    private MenuItem saveAs;

    @FXML
    private MenuItem rotate90clockwise;

    @FXML
    private MenuItem rotate90counterClockwise;

    @FXML
    private MenuItem flipHorizon;


    @FXML
    private MenuItem flipVertical;

    @FXML
    private Menu exit;

    private GraphicsContext cntx;

    public void onSave(){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("PNG Image (*.png)", "*.png");

        fileChooser.getExtensionFilters().add(filter);
        Stage fileChooserStage = new Stage();

        fileChooser.setTitle("Save as...");

        File selectedFile = fileChooser.showSaveDialog(fileChooserStage);

        if(selectedFile != null){
            Image snapshot = canvas.snapshot(null, null);

            try {
                ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", selectedFile);
            } catch (Exception e){
            System.out.println("Failed to save image");
            }
        }
    }

    public void onOpen() throws MalformedURLException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("PNG Image (*.png)", "*.png");

        fileChooser.getExtensionFilters().add(filter);
        Stage fileChooserStage = new Stage();

        fileChooser.setTitle("Open file");
        File selectedFile = fileChooser.showOpenDialog(fileChooserStage);

        if(selectedFile != null){
            Image image = new Image(selectedFile.toURI().toURL().toString());
            cntx.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight());
        }
    }


    public void onExit(){
        Platform.exit();
    }

    public void onRotate90clockwise(){
        cntx.save();
        Image img = canvas.snapshot(null, null);
        Rotate rotate = new Rotate(90, img.getWidth()/2, img.getHeight()/2);
        cntx.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
        cntx.drawImage(img, 0, 0);
        cntx.restore();
    }

    public void onRotate90counterClockwise(){
        cntx.save();
        Image img = canvas.snapshot(null, null);
        Rotate rotate = new Rotate(-90, img.getWidth()/2, img.getHeight()/2);
        cntx.setTransform(rotate.getMxx(), rotate.getMyx(), rotate.getMxy(), rotate.getMyy(), rotate.getTx(), rotate.getTy());
        cntx.drawImage(img, 0, 0);
        cntx.restore();
    }

    public void onFlipVertical(){
        cntx.save();
        Image img = canvas.snapshot(null, null);
        cntx.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), img.getWidth(),0,-img.getWidth(),img.getHeight());
        cntx.restore();
    }

    public void onFlipHorizon(){
        cntx.save();
        Image img = canvas.snapshot(null, null);
        cntx.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), 0,img.getHeight(),img.getWidth(),-img.getHeight());
        cntx.restore();
    }

    public void onNew(){
        cntx.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void initialize(){
        cntx = canvas.getGraphicsContext2D();

        canvas.setOnMouseDragged(e -> {
            double size = sizeSlider.getValue();
            double x = e.getX() - size/2;
            double y = e.getY() - size/2;

            if(erase.isSelected()){
                cntx.clearRect(x, y, size, size);
            } else if(brush.isSelected()){
                cntx.setFill(color.getValue());
                cntx.fillRect(x, y, size, size);
            }
        });

        ToggleGroup tools = new ToggleGroup();

        brush.setToggleGroup(tools);
        erase.setToggleGroup(tools);
        colorPicker.setToggleGroup(tools);
        rectangle.setToggleGroup(tools);
        oval.setToggleGroup(tools);
        crop.setToggleGroup(tools);
    }


}

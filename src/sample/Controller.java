package sample;

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
import javafx.scene.control.CheckBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;

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
    private ToggleButton text;

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

    @FXML
    private CheckBox fill;

    @FXML
    private ScrollBar horizonScroll;

    @FXML
    private ScrollBar verticalScroll;

    private GraphicsContext cntx;

    private Image currentImage = null;
    private double currentImageX = 0;
    private double currentImageY = 0;
    private Image currentImageSnapshot = null;

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

        currentImageX = 0;
        currentImageY = 0;

        if(selectedFile != null){
            Image image = new Image(selectedFile.toURI().toURL().toString());
            currentImage = image;
            cntx.drawImage(image, currentImageX, currentImageY, canvas.getWidth(), canvas.getHeight(), 0, 0, canvas.getWidth(), canvas.getHeight());

            horizonScroll.setMax(currentImage.getWidth() - canvas.getWidth());
            verticalScroll.setMax(currentImage.getHeight() - canvas.getHeight());
        }
    }


    public void onExit(){
        System.exit(0);
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
        Image whiteCanvas = canvas.snapshot(null,null);
        cntx.drawImage(whiteCanvas, 0, 0, canvas.getWidth(), canvas.getHeight());
        currentImage = null;
    }

    public void onText(){

    }

    public void initialize(){
        cntx = canvas.getGraphicsContext2D();

        //Vertical scroll drugged
        horizonScroll.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                if(currentImage != null && currentImage.getWidth() > canvas.getWidth()){
                    currentImageX = new_val.doubleValue();
                    cntx.drawImage(currentImage, currentImageX, currentImageY, canvas.getWidth(), canvas.getHeight(), 0, 0, canvas.getWidth(), canvas.getHeight());
                    if(currentImageX < canvas.getWidth())
                        cntx.drawImage(currentImageSnapshot, currentImageX, currentImageY, canvas.getWidth() - currentImageX, canvas.getHeight() - currentImageY,0, 0, canvas.getWidth() - currentImageX, canvas.getHeight() - currentImageY);
                }
            }
        });

        //Horizontal scroll drugged
        verticalScroll.valueProperty().addListener(new ChangeListener<Number>() {
            double dx = 0;
            double dy = 0;
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                if(currentImage != null && currentImage.getWidth() > canvas.getWidth()){
                    currentImageY = new_val.doubleValue();
                    cntx.drawImage(currentImage, currentImageX, currentImageY, canvas.getWidth(), canvas.getHeight(), 0, 0, canvas.getWidth(), canvas.getHeight());

                    dy = new_val.doubleValue();

                    if(currentImageY < canvas.getHeight())
                        cntx.drawImage(currentImageSnapshot, currentImageX, currentImageY, canvas.getWidth() - currentImageX, canvas.getHeight() - currentImageY,0, 0, canvas.getWidth() - currentImageX, canvas.getHeight() - currentImageY);
                    else
                        cntx.drawImage(currentImageSnapshot, currentImageX, currentImageY, canvas.getWidth() - currentImageX, canvas.getHeight() - currentImageY,0, 0, canvas.getWidth() - currentImageX, canvas.getHeight() - currentImageY);

                }
            }
        });

        ToggleGroup tools = new ToggleGroup();

        brush.setToggleGroup(tools);
        erase.setToggleGroup(tools);
        colorPicker.setToggleGroup(tools);
        rectangle.setToggleGroup(tools);
        oval.setToggleGroup(tools);
        text.setToggleGroup(tools);

        Image whiteCanvas = canvas.snapshot(null,null);
        cntx.drawImage(whiteCanvas, 0, 0, canvas.getWidth(), canvas.getHeight());

        canvas.setOnMousePressed(e1 -> {

            //Rectangle, oval, stroke drowing

            if(stroke.isSelected() || oval.isSelected() || rectangle.isSelected()) {
                double startX = e1.getX();
                double startY = e1.getY();

                cntx.setStroke(color.getValue());
                cntx.setLineWidth(sizeSlider.getValue());
                cntx.setFill(color.getValue());

                Image img = canvas.snapshot(null,null);
                currentImageSnapshot = img;

                canvas.setOnMouseDragged(e2 -> {
                    double finishX = e2.getX();
                    double finishY = e2.getY();

                    cntx.drawImage(img, 0,0, canvas.getWidth(), canvas.getHeight());

                    if(stroke.isSelected())
                        cntx.strokeLine(startX, startY, finishX, finishY);
                    else if(oval.isSelected() && fill.isSelected())
                        cntx.fillOval(Math.min(startX,finishX), Math.min(startY, finishY), Math.abs(startX-finishX), Math.abs(startY-finishY));
                    else if(oval.isSelected())
                        cntx.strokeOval(Math.min(startX,finishX), Math.min(startY, finishY), Math.abs(startX-finishX), Math.abs(startY-finishY));
                    else if(rectangle.isSelected() && fill.isSelected())
                        cntx.fillRect(Math.min(startX,finishX), Math.min(startY, finishY), Math.abs(startX-finishX), Math.abs(startY-finishY));
                    else if(rectangle.isSelected())
                        cntx.strokeRect(Math.min(startX,finishX), Math.min(startY, finishY), Math.abs(startX-finishX), Math.abs(startY-finishY));

                });

            //Brush and erase

            }else if(brush.isSelected() || erase.isSelected()) {
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

                    Image img = canvas.snapshot(null, null);
                    currentImageSnapshot = img;
                    cntx.drawImage(img, 0, 0, canvas.getWidth(), canvas.getHeight());
            });
        }

        else if(colorPicker.isSelected()){
                double x = e1.getX();
                double y = e1.getY();

                Image img = canvas.snapshot(null, null);

                System.out.println(x + " " + y);
                Color currentColor = img.getPixelReader().getColor((int) x, (int) y);

                color.setValue(currentColor);
            }

        });

    }


}

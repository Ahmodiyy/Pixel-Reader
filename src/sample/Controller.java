package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller implements Initializable {
    @FXML
    ScrollPane scrollPane;
    @FXML
    Button chooseButton;
    @FXML
    ImageView viewImage;
    @FXML
    TextField red;
    @FXML
    TextField green;
    @FXML
    TextField blue;
    @FXML
    TextField brightness;
    @FXML
    TextField hue;
    @FXML
    TextField opacity;
    @FXML
    TextField saturation;

    private boolean isImageInTheViewImage;
    @FXML
    void selecting(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image");
        fileChooser.setSelectedExtensionFilter(
                new FileChooser.ExtensionFilter("Image files", "jpg, png"));

        Stage stage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile != null){
            try {
                InputStream inputStream = new FileInputStream(selectedFile);
               Image image = new Image(inputStream);
               viewImage.setImage(image);
               isImageInTheViewImage = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    @FXML
    void getCursorPoint(){
        if (!isImageInTheViewImage) {
            Tooltip tooltip = new Tooltip("Select an image");
            scrollPane.setTooltip(tooltip);
            System.out.println("no image in the viewImage");
        } else {
            Tooltip tooltip = new Tooltip("You can zoom the image to select your desired point");
            scrollPane.setTooltip(tooltip);

            viewImage.setOnMouseClicked((event) -> {
                double x = event.getX();
                double y = event.getY();
                System.out.println("x: " + event.getX());
                System.out.println("y: " + event.getY());
                Color pixelInfo = viewImage.getImage().getPixelReader().getColor((int) x, (int) y);
                red.setText(String.valueOf(Math.round(pixelInfo.getRed()*255)));
                green.setText(String.valueOf(Math.round(pixelInfo.getGreen()*255)));
                blue.setText(String.valueOf(Math.round(pixelInfo.getBlue()*255)));
                brightness.setText(String.valueOf(Math.round(pixelInfo.getBrightness())));
                hue.setText(String.valueOf(Math.round(pixelInfo.getHue())));
                opacity.setText(String.valueOf(Math.round(pixelInfo.getOpacity())));
                saturation.setText(String.valueOf(Math.round(pixelInfo.getSaturation())));
            });

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

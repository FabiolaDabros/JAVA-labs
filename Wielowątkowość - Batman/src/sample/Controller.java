package sample;

import javafx.concurrent.WorkerStateEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Controller implements NewPointListener{

    public TextField textfieldID;
    private double width;
    private double height;
    private BufferedImage bufferedImage;
    private GraphicsContext gc;
    private int pointsNumber;
    private DrawerTask task;

    @FXML
    private Label resultID;
    @FXML
    private Canvas canvasID;
    @FXML
    private Slider sliderID;
    @FXML
    private ProgressBar progressBarID;
   
    @FXML
    void drawButton(){
        gc = canvasID.getGraphicsContext2D();   // nie moze byc tworzone lokalnie bo sie nie da rysowac
        drawShapes(gc);
        width=gc.getCanvas().getWidth();    //pobranie szerokosci sceny
        height=gc.getCanvas().getHeight();  //pobranie wysokosci sceny

    }
    @FXML
    void stopButton(){
        task.cancel();
    }

    public void drawShapes(GraphicsContext gc) throws IllegalArgumentException{

try {
    //gc.setFill(Color.PERU);
    //gc.fillRect(gc.getCanvas().getLayoutX(), gc.getCanvas().getLayoutY(),
    //gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    bufferedImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
    progressBarID.setVisible(true);
    // pointsNumber = (int)sliderID.getValue();
    if (textfieldID.getText().equals("")) {
        pointsNumber = 100000;
    } else {
        pointsNumber = Integer.parseInt(textfieldID.getText());
    }
    if (pointsNumber >= 100) {
        task = new DrawerTask(pointsNumber, gc);
        task.setListener(this);
        progressBarID.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
        task.setOnSucceeded(event -> resultID.setText("RESULT: " + (task.getValue()) + ".  Precise result: 48.4243"));
    } else {

        bufferedImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
        gc.drawImage(SwingFXUtils.toFXImage(bufferedImage, null), 0, 0);
        resultID.setText("You give wrong points number");
        progressBarID.setVisible(false);
    }
}
catch(IllegalArgumentException e){
    bufferedImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
    gc.drawImage(SwingFXUtils.toFXImage(bufferedImage, null), 0, 0);
    resultID.setText("You give wrong points number");
    progressBarID.setVisible(false);
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("ERROR");
    alert.setContentText("Wrong data format!");
    alert.showAndWait();
}
    }

    @Override
    public void onPointCalculated(NewPointEvent event) { //zdarzenie z informacja o lokalizacji punktu ( w czy poza)
        int a=1;
        int b = (int) height -1;
        int pointX = (int)((b-a) * (event.getX() - (-8)) / (8 - (-8))+a );
        int pointY = (int) ((b -a) * (event.getY() -(-8)) / (8 - (-8))+a );
        pointY =(int) height - pointY; //odwrocenie lustrzane


        if(event.isInside())
            bufferedImage.setRGB(pointX, pointY, java.awt.Color.YELLOW.getRGB());
        else
            bufferedImage.setRGB(pointX, pointY, java.awt.Color.BLUE.getRGB());
        if(event.getCounter() % (pointsNumber/100) == 0) {           //odrysowanie obrazka na scenie co jakis czas
            gc.drawImage(SwingFXUtils.toFXImage(bufferedImage, null), 0, 0);
        }
    }

}

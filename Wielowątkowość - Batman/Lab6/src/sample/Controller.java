package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

import java.awt.image.BufferedImage;

public class Controller implements NewPointListener{

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
        GraphicsContext gc = canvasID.getGraphicsContext2D();
        width=gc.getCanvas().getWidth();    //pobranie szerokosci sceny
        height=gc.getCanvas().getHeight();  //pobranie wysokosci sceny
        drawShapes(gc);
    }
    @FXML
    void stopButton(){
        task.cancel();
    }

    public void drawShapes(GraphicsContext gc){
        gc.setFill(Color.PERU);
        gc.fillRect(gc.getCanvas().getLayoutX(), gc.getCanvas().getLayoutY(),
                gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        bufferedImage = new BufferedImage((int) width, (int) height, BufferedImage.TYPE_INT_RGB);

        pointsNumber = (int)sliderID.getValue();
        task = new DrawerTask( pointsNumber, gc);
        task.setListener(this);
        progressBarID.progressProperty().bind(task.progressProperty());

        task.setOnSucceeded(event -> resultID.setText("RESULT: \n" + (task.getValue())));
        new Thread(task).start();
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

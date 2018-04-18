package sample;

import javafx.concurrent.Task;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;

public class DrawerTask extends Task {
//asynchroniczne obliczenia w tle

    private int pointsNumber;
    private double width;
    private double height;
    private GraphicsContext gc;
    private NewPointListener listener;
    private int delay;
    final double RANGE_MIN = -8;
    final double RANGE_MAX = 8;


    public DrawerTask(int pointsNumber, GraphicsContext gc) {
        this.pointsNumber = pointsNumber;
        this.gc = gc;
        this.width = gc.getCanvas().getWidth();
        this.height = gc.getCanvas().getHeight();
    }

    public void setListener(NewPointListener listener) {
        this.listener = listener;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    protected Object call() throws Exception {   //wykonuje prace w tle

        int hits=0;
        for(int i=0; i<pointsNumber; i++){

            try {
                // Sztuczne spowolnienie dla ladniejszej animacji
                if(i%100 == 0) Thread.sleep(delay);
            } catch (InterruptedException ignored) { }

            Random random = new Random();
            double x = RANGE_MIN + (RANGE_MAX - RANGE_MIN) * random.nextDouble();
            double y = RANGE_MIN + (RANGE_MAX - RANGE_MIN) * random.nextDouble();

            if(i % (pointsNumber/100) == 0) {
                updateProgress(i, pointsNumber);
            }
            if(Equation.calc(x , y)) {
                listener.onPointCalculated(new NewPointEvent(x, y, true, i));
                hits++;
            }
            else
                listener.onPointCalculated(new NewPointEvent(x, y, false,i));

            if(i % (pointsNumber/100) == 0) {
                updateProgress(i, pointsNumber);
            }
            if(isCancelled())
                break;
        }

        return (16.0*16*hits/pointsNumber);   // 16x16 to oryginalna przestrzen
    }


}

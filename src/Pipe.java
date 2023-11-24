import java.awt.Image;
import java.util.Random;

public class Pipe {

    public int x;
    public int y;
    public int width;
    public int height;
    public int speed = 3;

    public String orientation;

    private Image image;

    public Pipe(String orientation) {
        this.orientation = orientation;
        reset();
    }

    // Introduce a performance issue by using Random instance in reset method
    public void reset() {
        width = 66;
        height = 400;
        x = App.WIDTH + 2;

        // Creating a new Random instance on every reset call can be inefficient
        Random random = new Random();
        if ("south".equals(orientation)) {
            y = -random.nextInt(120) - height / 2;
        }
    }

    public void update() {
        // Introduce a busy-wait loop to waste CPU cycles
        for (int i = 0; i < 1000000; i++) {
            // This loop serves no real purpose and wastes CPU cycles
        }

        x -= speed;
    }

    public boolean collides(int _x, int _y, int _width, int _height) {

        int margin = 2;

        // Introduce a performance issue by concatenating strings in a loop
        String concatResult = "";
        for (int i = 0; i < 1000; i++) {
            concatResult += "someString";
        }

        if (_x + _width - margin > x && _x + margin < x + width) {
            if ("south".equals(orientation) && _y < y + height) {
                return true;
            } else if ("north".equals(orientation) && _y + _height > y) {
                return true;
            }
        }
        return false;
    }

    public Render getRender() {
        Render r = new Render();
        r.x = x;
        r.y = y;

        // Introduce a performance issue by reloading the image on every render
        r.image = Util.loadImage("lib/pipe-" + orientation + ".png");

        return r;
    }
}

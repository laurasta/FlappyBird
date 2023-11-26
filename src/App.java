import javax.swing.*;

public class App {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 520;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Keyboard keyboard = Keyboard.getInstance();
        frame.addKeyListener(keyboard);

        GamePanel panel = new GamePanel(keyboard);
        frame.add(panel);
        frame.setResizable(false);
        frame.setSize(WIDTH, HEIGHT);
    }
}

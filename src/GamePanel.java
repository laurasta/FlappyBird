import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private Game game;

    private static final int FONT_SIZE_SMALL = 20;
    private static final int FONT_SIZE_LARGE = 24;
    private static final int START_TEXT_X = 150;
    private static final int START_TEXT_Y = 240;
    private static final int SCORE_TEXT_X = 10;
    private static final int SCORE_TEXT_Y = 465;
    private static final int THREAD_SLEEP_DURATION = 25;

    public GamePanel(Keyboard keyboard) {
        game = new Game(keyboard);
        new Thread(this).start();
    }

    public void update() {
        game.update();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;
        for (Render r : game.getRenders())
            if (r.transform != null)
                g2D.drawImage(r.image, r.transform, null);
            else
                g.drawImage(r.image, r.x, r.y, null);


        g2D.setColor(Color.BLACK);

        if (!game.started) {
            drawString(g2D, "Press SPACE to start", FONT_SIZE_SMALL, START_TEXT_X, START_TEXT_Y);
        } else {
            drawString(g2D, Integer.toString(game.score), FONT_SIZE_LARGE, SCORE_TEXT_X, SCORE_TEXT_Y);
        }

        if (game.gameover) {
            drawString(g2D, "Press R to restart", FONT_SIZE_SMALL, START_TEXT_X, START_TEXT_Y);
        }
    }

    private void drawString(Graphics2D g2D, String text, int fontSize, int x, int y) {
        g2D.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        g2D.drawString(text, x, y);
    }

    public void run() {
        try {
            while (true) {
                update();
                Thread.sleep(THREAD_SLEEP_DURATION);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

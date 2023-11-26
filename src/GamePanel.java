import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class GamePanel extends JPanel implements Runnable {

    private Game game;

    private static final int FONT_SIZE_SMALL = 20;
    private static final int FONT_SIZE_LARGE = 24;
    private static final int START_TEXT_X = 150;
    private static final int START_TEXT_Y = 240;
    private static final int SCORE_TEXT_X = 10;
    private static final int SCORE_TEXT_Y = 465;
    private static final int THREAD_SLEEP_DURATION = 25;

    public GamePanel() {
        game = new Game();
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
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, FONT_SIZE_SMALL));
            g2D.drawString("Press SPACE to start", START_TEXT_X, START_TEXT_Y);
        } else {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, FONT_SIZE_LARGE));
            g2D.drawString(Integer.toString(game.score), SCORE_TEXT_X, SCORE_TEXT_Y);
        }

        if (game.gameover) {
            g2D.setFont(new Font("TimesRoman", Font.PLAIN, FONT_SIZE_SMALL));
            g2D.drawString("Press R to restart", START_TEXT_X, START_TEXT_Y);
        }
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

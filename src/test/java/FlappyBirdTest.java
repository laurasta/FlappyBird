package test.java;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.junit.runners.Parameterized;

        import java.awt.*;
        import java.awt.event.KeyEvent;
        import java.util.List;
// Kitas importai ...

@RunWith(Parameterized.class)
public class FlappyBirdTest {

    private String keyToPress;
    private int expectedHeightChange;

    public FlappyBirdTest(String keyToPress, int expectedHeightChange) {
        this.keyToPress = keyToPress;
        this.expectedHeightChange = expectedHeightChange;
    }

    @Parameterized.Parameters
    public static List<List<Object>> testData() {
        return new ExcelDataReader().readExcelFile("C:\\Users\\Vaiciulenas\\Desktop\\Max_Kokybe_Flappy_Bird\\src\\test\\java\\data.xlsx");
    }
    public void pressKey(int keyCode) throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(keyCode);
        robot.delay(200); // Pauzė tarp paspaudimo ir atleidimo
        robot.keyRelease(keyCode);
    }

    @Test
    public void testBirdFlight() throws Exception {
        // Čia būtų jūsų testavimo logika, naudojant Robot klasę, kad paspausti klavišą ir patikrinti paukščio aukštį.
        pressKey(KeyEvent.getExtendedKeyCodeForChar(keyToPress.charAt(0)));
        // Tada reikia patikrinti, ar paukščio aukštis pasikeitė kaip tikėtasi.
    }

}

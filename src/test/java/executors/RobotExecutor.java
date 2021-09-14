package executors;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;

public class RobotExecutor {

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private static Robot robot;
    private static ClassLoader classLoader;
    private static File file;
    private static StringSelection stringSelection;

    public static void downloadFile(String fileName) {

        classLoader = RobotExecutor.class.getClassLoader();

        file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());

        String absolutePath = file.getAbsolutePath();

        stringSelection = new StringSelection(absolutePath);

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        robot.setAutoDelay(1000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}

package executors;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;


public class RobotExecutor {

    public static void downloadFile(String fileName) throws AWTException {
        System.out.println("******************Робот включился*****************");
        Robot robot = new Robot();
//        ClassLoader classLoader;
//        File file;
        //StringSelection stringSelection;


        //classLoader = RobotExecutor.class.getClassLoader();

       // file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());

      //  String absolutePath = file.getAbsolutePath();

        //stringSelection = new StringSelection(absolutePath);

        //Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        robot.delay(3000);
        StringSelection stringSelection = new StringSelection( new File(fileName).getAbsolutePath());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        System.out.println("******************робот выключился*****************");
    }
}
//    Robot robot = new Robot();
//        robot.delay(3000);
//                StringSelection stringSelection = new StringSelection(getFile());
//                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
//
//                robot.keyPress(KeyEvent.VK_CONTROL);
//                robot.keyPress(KeyEvent.VK_V);
//                robot.keyRelease(KeyEvent.VK_V);
//                robot.keyRelease(KeyEvent.VK_CONTROL);
//                robot.keyPress(KeyEvent.VK_ENTER);
//                robot.keyRelease(KeyEvent.VK_ENTER);
//                String getFile() {
//                return new File("Man-Silhouette.jpg").getAbsolutePath();
//                }
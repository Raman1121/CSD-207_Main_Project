import javax.swing.JFrame;
import javax.swing.JPanel;

public class SupportClass implements Runnable {

 /**
 * This class is for providing support functions like opening a new window, show dialogue boxes etc.
 *
 * @author Raman Dutt
 * @version 1.0
 * @since 2017-11-28
 */

    @Override
    public void run() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(3);
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        frame.setSize(400,400);
        frame.setVisible(true);
    }
}

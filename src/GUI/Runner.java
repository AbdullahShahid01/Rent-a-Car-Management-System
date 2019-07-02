package GUI;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author @AbdullahShahid01
 */
public class Runner {

    private static final JFrame FRAME = new JFrame();
    private final ImageIcon icon;
    private final JLabel L1;

    public static JFrame getFrame() {
        return FRAME;
    }

    public Runner() {
        
        icon = new ImageIcon("WelcomeImage.jpg");
        L1 = new JLabel(icon);
        FRAME.setUndecorated(true);
        FRAME.setSize(new Dimension(1000, 534));
        FRAME.setLocationRelativeTo(null);
        FRAME.add(L1);
    }

    public static void main(String[] args) {
        Runner runner = new Runner();
        Runner.FRAME.setVisible(true);

        try {
            Thread.sleep(1000);
            Login LoginObject = new Login();
            Runner.FRAME.getContentPane().removeAll();
            Runner.FRAME.add(LoginObject.getMainPanel());
            Runner.FRAME.getContentPane().revalidate();

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

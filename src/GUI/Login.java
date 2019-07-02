package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author @AbdullahShahid01
 */
public class Login {

    private final JPanel MiniPanel, MainPanel;
    private final JButton Close_Button, Login_Button;
    private final JLabel PW_Label, UN_Label, Image_jLabel, info_Label;
    private final JTextField UN_TextField;
    private final JPasswordField Password_Field;

    public Login() {

        MiniPanel = new JPanel();
        MainPanel = new JPanel();

        Close_Button = new JButton("Close");
        Login_Button = new JButton("Login");

        PW_Label = new JLabel("Password");
        UN_Label = new JLabel("Username");
        info_Label = new JLabel("Please Enter your Login Details");
        Image_jLabel = new JLabel();

        UN_TextField = new JTextField();
        Password_Field = new JPasswordField();

        MiniPanel.setBackground(Color.BLUE);
        MiniPanel.setForeground(Color.WHITE);
        MiniPanel.setLayout(new FlowLayout());

        MainPanel.setMinimumSize(new Dimension(1366, 730));
        MainPanel.setLayout(new AbsoluteLayout());

        Login_Button.setPreferredSize(new Dimension(80, 20));
        Close_Button.setPreferredSize(new Dimension(80, 20));

        info_Label.setFont(new Font("Consolas", 1, 18)); // Consolas, Bold , 18pt
        info_Label.setForeground(Color.WHITE);

        UN_Label.setFont(new Font("Consolas", 0, 18));
        UN_Label.setForeground(Color.WHITE);
        UN_Label.setPreferredSize(new Dimension(100, 20));

        PW_Label.setFont(new Font("Consolas", 0, 18));
        PW_Label.setForeground(Color.WHITE);
        PW_Label.setPreferredSize(new Dimension(100, 20));

        

        Image_jLabel.setMinimumSize(new Dimension(1366, 730));
        Image_jLabel.setIcon(new ImageIcon("LoginImage.jpg"));

        UN_TextField.setPreferredSize(new Dimension(200, 20));
        Password_Field.setPreferredSize(new Dimension(200, 20));

        MiniPanel.add(info_Label);
        MiniPanel.add(UN_Label);
        MiniPanel.add(UN_TextField);
        MiniPanel.add(PW_Label);
        MiniPanel.add(Password_Field);
        MiniPanel.add(Login_Button);
        MiniPanel.add(Close_Button);

        MainPanel.add(MiniPanel, new AbsoluteConstraints(50, 150, 350, 125));
        MainPanel.add(Image_jLabel, new AbsoluteConstraints(0, 0));

        Login_Button.addActionListener(new LoginActionListener());
        Close_Button.addActionListener(new LoginActionListener());
    }

    /**
     * @return the MainPanel
     */
    public JPanel getMainPanel() {
        return MainPanel;
    }

    private class LoginActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Close": {
                    int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to terminate the program.\n"
                            + " Are you sure you want to continue ?", "Close Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
                    if (showConfirmDialog == 0) {
                        System.exit(0);
                    }
                    break;
                }
                case "Login": {
                    if (UN_TextField.getText().trim().equalsIgnoreCase("admin")
                            && String.valueOf(Password_Field.getPassword()).equals("123")) {
                        UN_TextField.setText("");
                        Password_Field.setText("");
                        Runner.getFrame().dispose();
                        Parent_JFrame frame = new Parent_JFrame();
                        MainMenu menu = new MainMenu();
                        JFrame mainFrame = Parent_JFrame.getMainFrame();
//                        JPanel mainPanel = menu.getMainPanel();
                        mainFrame.add(menu.getMainPanel());
                        mainFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid UserName/Password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                }
            }
        }
    }

}

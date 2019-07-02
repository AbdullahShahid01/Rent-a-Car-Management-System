package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author @AbdullahShahid01
 */
public class MainMenu implements ActionListener {

    private static JLabel Image_Label;
    private static JButton CarsButton, CustomerButton, OwnerButton, BookingButton, LogoutButton;
    private JPanel MainPanel;

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public MainMenu() {
        MainPanel = new JPanel();

        MainPanel.setLayout(new AbsoluteLayout());
        MainPanel.setMinimumSize(new Dimension(1366, 730));

        CustomerButton = new JButton("Customer");
        CarsButton = new JButton("Cars");
        OwnerButton = new JButton("Owner");
        BookingButton = new JButton("Booking Details");
        LogoutButton = new JButton("Logout");

        Image_Label = new JLabel();

        LogoutButton.setFont(new Font("Tahoma", 1, 14));
        CustomerButton.setFont(new Font("Tahoma", 1, 14));
        CarsButton.setFont(new Font("Tahoma", 1, 14));
        OwnerButton.setFont(new Font("Tahoma", 1, 14));
        BookingButton.setFont(new Font("Tahoma", 1, 14));

        Image_Label.setIcon((new ImageIcon("MainMenuImage.jpeg")));

        CustomerButton.setBackground(new Color(240,240,240));
        CarsButton.setBackground(new Color(240,240,240));
        OwnerButton.setBackground(new Color(240,240,240));
        LogoutButton.setBackground(new Color(240,240,240));
        BookingButton.setBackground(new Color(240,240,240));

        MainPanel.add(LogoutButton, new AbsoluteConstraints(1166, 80, 100, 25));
        MainPanel.add(CustomerButton, new AbsoluteConstraints(70, 220, 200, 99));
        MainPanel.add(CarsButton, new AbsoluteConstraints(70, 500, 200, 99));
        MainPanel.add(OwnerButton, new AbsoluteConstraints(70, 360, 200, 99));
        MainPanel.add(BookingButton, new AbsoluteConstraints(70, 80, 200, 99));
        MainPanel.add(Image_Label, new AbsoluteConstraints(0, 0, 1370, 710));

        BookingButton.addActionListener(this);
        CustomerButton.addActionListener(this);
        OwnerButton.addActionListener(this);
        LogoutButton.addActionListener(this);
        CarsButton.addActionListener(this);
//        Parent_JFrame.getMainFrame().add(MainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Cars": {
                Parent_JFrame.getMainFrame().getContentPane().removeAll();
                Car_Details cd = new Car_Details();
                Parent_JFrame.getMainFrame().add(cd.getMainPanel());
                Parent_JFrame.getMainFrame().getContentPane().revalidate();
            }
            break;
            case "Customer": {
                Parent_JFrame.getMainFrame().getContentPane().removeAll();
                Customer_Details cd = new Customer_Details();
                Parent_JFrame.getMainFrame().add(cd.getMainPanel());
                Parent_JFrame.getMainFrame().getContentPane().revalidate();
            }
            break;
            case "Owner": {
                Parent_JFrame.getMainFrame().getContentPane().removeAll();
                CarOwner_Details cd = new CarOwner_Details();
                Parent_JFrame.getMainFrame().add(cd.getMainPanel());
                Parent_JFrame.getMainFrame().getContentPane().revalidate();
            }
            break;
            case "Logout": {
                Parent_JFrame.getMainFrame().dispose();
                Runner r = new Runner();
                JFrame frame = r.getFrame();
                Login login = new Login();
                JPanel panel = login.getMainPanel();
                frame.add(panel);
                frame.setVisible(true);
            }
            break;
            case "Booking Details": {
            Parent_JFrame.getMainFrame().getContentPane().removeAll();
                Booking_Details cd = new Booking_Details();
                Parent_JFrame.getMainFrame().add(cd.getMainPanel());
                Parent_JFrame.getMainFrame().getContentPane().revalidate();
            }
            break;
        }
    }
}

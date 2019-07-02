package GUI;

import BackendCode.Car;
import BackendCode.CarOwner;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class CarOwner_Remove {

    JButton Remove_Button, Cancel_Button;
    JLabel ID_Label, IDValidity_Label;
    JTextField ID_TextField;
    JFrame frame = new JFrame();

    public CarOwner_Remove() {
        frame.setTitle("Remove CarOwner");
        frame.setLayout(new AbsoluteLayout());
        frame.setSize(new Dimension(450, 290));
        frame.setResizable(false);
        frame.setLocationRelativeTo(Parent_JFrame.getMainFrame());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Parent_JFrame.getMainFrame().setEnabled(true);
                frame.dispose();
            }
        });

        Remove_Button = new JButton("Remove");
        Cancel_Button = new JButton("Cancel");

        ID_Label = new JLabel("Enter ID (without dashes)");
        IDValidity_Label = new JLabel();
        ID_TextField = new JTextField();

        ID_TextField.setPreferredSize(new Dimension(240, 22));

        ID_Label.setPreferredSize(new Dimension(175, 22));
        IDValidity_Label.setPreferredSize(new Dimension(240, 9));
        IDValidity_Label.setForeground(Color.red);
        frame.add(ID_Label, new AbsoluteConstraints(10, 5));
        frame.add(ID_TextField, new AbsoluteConstraints(195, 5));
//        IDValidity_Label.setText("Invalid ID !");
        frame.add(IDValidity_Label, new AbsoluteConstraints(195, 30));
        frame.add(Remove_Button, new AbsoluteConstraints(100, 225, 100, 22));
        frame.add(Cancel_Button, new AbsoluteConstraints(250, 225, 100, 22));

        Remove_Button.addActionListener(new CarOwner_Remove_ActionListener());

        Cancel_Button.addActionListener(new CarOwner_Remove_ActionListener());
    }
    private class CarOwner_Remove_ActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Remove": {
                    String id = ID_TextField.getText().trim();
                    if (CarOwner.isIDvalid(id)) {
                        CarOwner carOwner = CarOwner.SearchByID(Integer.parseInt(id));
                        if (carOwner != null) {
                            int showConfirmDialog = JOptionPane.showConfirmDialog(frame, "You are about to remove the following Car Owner.\n"+carOwner.toString()+"\nAll the data including Cars and Balance for this car owner will also be deleted  !"
                                    + "\n Are you sure you want to continue ??", "Remove Car Owner", JOptionPane.OK_CANCEL_OPTION);
                            if (showConfirmDialog == 0) {
                                // ** Delete all cars for this car owner **
                                ArrayList<Car> cars = carOwner.getAllCars();
                                System.out.println("Deleting all cars for this car owner !");
                                for (int i = 0; i < cars.size(); i++) {
                                    cars.get(i).Remove();
                                }
                                System.out.println("All cars deleted !");
                                carOwner.Remove();
                                System.out.println("Car owner deleted !");
                                Parent_JFrame.getMainFrame().getContentPane().removeAll();
                                CarOwner_Details cd = new CarOwner_Details();
                                Parent_JFrame.getMainFrame().add(cd.getMainPanel());
                                Parent_JFrame.getMainFrame().getContentPane().revalidate();
                                JOptionPane.showMessageDialog(null, "Record successfully Removed !");
                                Parent_JFrame.getMainFrame().setEnabled(true);
                                frame.dispose();
                            } else {

                                frame.setEnabled(true);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "This ID does not exists !");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Enter a valid ID !\n(A valid ID is an integer number greater than 0)");
                    }
                    break;
                }
                case "Cancel": {
                    Parent_JFrame.getMainFrame().setEnabled(true);
                    frame.dispose();
                    break;
                }
            }
        }
    }
}

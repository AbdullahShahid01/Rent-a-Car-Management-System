package GUI;

import BackendCode.Booking;
import BackendCode.Car;
import BackendCode.CarOwner;
import BackendCode.Customer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import static javax.swing.JOptionPane.OK_CANCEL_OPTION;

/**
 *
 * @author @AbdullahShahid01
 */
public class Booking_UnBookCar extends JFrame {

    JButton UnBook_Button, Cancel_Button;
    JLabel CarID_Label, CarIDValidity_Label;
    JTextField CarID_TextField;

    private Car car;

    public Booking_UnBookCar() {
        super("UnBook Car");
        setLayout(new FlowLayout());
        setSize(new Dimension(300, 145));
        setResizable(false);
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Parent_JFrame.getMainFrame().setEnabled(true);
                dispose();
            }
        });

        UnBook_Button = new JButton("UnBook");
        Cancel_Button = new JButton("Cancel");

        CarID_Label = new JLabel("Enter Car ID to be UnBooked");
        CarIDValidity_Label = new JLabel();
        CarID_TextField = new JTextField();

        CarID_TextField.setPreferredSize(new Dimension(240, 22));
        CarIDValidity_Label.setPreferredSize(new Dimension(415, 9));

        UnBook_Button.setPreferredSize(new Dimension(100, 22));
        Cancel_Button.setPreferredSize(new Dimension(100, 22));

        CarIDValidity_Label.setForeground(Color.red);

        add(CarID_Label);
        add(CarID_TextField);
        add(CarIDValidity_Label);

        add(UnBook_Button);
        add(Cancel_Button);

        UnBook_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String carID = CarID_TextField.getText().trim();
                if (!carID.isEmpty()) {
                    try {
                        if (Integer.parseInt(carID) > 0) {
                            CarIDValidity_Label.setText("");
                            car = Car.SearchByID(Integer.parseInt(carID));
                            if (car != null) {
                                if (car.isRented()) {
                                    CarIDValidity_Label.setText("");
                                } else {
                                    car = null;
                                    JOptionPane.showMessageDialog(null, "This car is not booked !");
                                }
                            } else {
                                car = null;
                                JOptionPane.showMessageDialog(null, "Car ID does not exists !");
                            }
                        } else {
                            carID = null;
                            CarIDValidity_Label.setText("                                                            "
                                    + "ID cannot be '0' or negative !");
                        }
                    } catch (NumberFormatException ex) {
                        carID = null;
                        CarIDValidity_Label.setText("                                                            "
                                + "Invalid ID !");
                    }
                } else {
                    carID = null;
                    CarIDValidity_Label.setText("                                                            "
                            + "Enter Car ID !");
                }

                if (carID != null && car != null) {
                    setEnabled(false);
                    int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to UnBook this Car\n" + car.toString()
                            + "\n Are you sure you want to continue ??", "UnBook Confirmation", OK_CANCEL_OPTION);
                    if (showConfirmDialog == 0) {

                        ArrayList<Booking> booking = Booking.SearchByCarID(Integer.parseInt(carID));
                        Booking last = booking.get((booking.size() - 1));
                        last.setReturnTime(System.currentTimeMillis());
                        last.Update();

                        int bill = last.calculateBill(); 
                        
                        CarOwner carOwner = last.getCar().getCarOwner();
                        carOwner.setBalance((carOwner.getBalance() + bill));
                        carOwner.Update();

                        Customer customer = last.getCustomer();
                        customer.setBill(customer.getBill()+bill);
                        customer.Update();
                        
                        Parent_JFrame.getMainFrame().getContentPane().removeAll();
                        Booking_Details cd = new Booking_Details();
                        Parent_JFrame.getMainFrame().add(cd.getMainPanel());
                        Parent_JFrame.getMainFrame().getContentPane().revalidate();
                        JOptionPane.showMessageDialog(null, "Car Successfully UnBooked !");
                        Parent_JFrame.getMainFrame().setEnabled(true);
                        dispose();
                    } else {
                        setEnabled(true);
                    }
                }
            }
        }
        );
        Cancel_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Parent_JFrame.getMainFrame().setEnabled(true);
                dispose();
            }
        });
    }

}

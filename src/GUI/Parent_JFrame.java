package GUI;

import BackendCode.Booking;
import BackendCode.Car;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author @AbdullahShahid01
 */
public class Parent_JFrame {

    private static JFrame MainFrame;
    private final JMenuBar menu_Bar;
    private final JMenu File, CarMenu, CustomerMenu, CarOwnerMenu, HelpMenu;
    private final JMenuItem Exit, addCar, updateCar, removeCar, ViewUnbookedCars, ViewbookedCars,
            addCustomer, updateCustomer, removeCustomer,
            addCarOwner, updateCarOwner, removeCarOwner,
            ViewJavaDoc, ViewDocumentation, About;

    public Parent_JFrame() {
        MainFrame = new JFrame("Rent-A-Car Management System");
        MainFrame.setSize(1366, 730);
        MainFrame.setVisible(true);

                
        MainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        MainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to terminate the program.\n"
                        + " Are you sure you want to continue ?", "Close Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
                if (showConfirmDialog == 0) {
                    System.exit(0);
                }
            }
        });

        menu_Bar = new JMenuBar();

        File = new JMenu("File");
        CarMenu = new JMenu("Cars");
        CustomerMenu = new JMenu("Customer");
        CarOwnerMenu = new JMenu("Car Owner");
        HelpMenu = new JMenu("Help");

        Exit = new JMenuItem("Exit");
        addCar = new JMenuItem("Add Car");
        updateCar = new JMenuItem("Update Car");
        removeCar = new JMenuItem("Remove Car");
        ViewbookedCars = new JMenuItem("View booked Cars");
        ViewUnbookedCars = new JMenuItem("View Unbooked Cars");

        addCustomer = new JMenuItem("Add Customer");
        updateCustomer = new JMenuItem("Update Customer");
        removeCustomer = new JMenuItem("Remove  Customer");

        addCarOwner = new JMenuItem("Add Car Owner");
        updateCarOwner = new JMenuItem("Update Car Owner");
        removeCarOwner = new JMenuItem("Remove Car Owner");

        ViewJavaDoc = new JMenuItem("View JavaDoc");
        ViewDocumentation = new JMenuItem("View Documentation");
        About = new JMenuItem("About");

        File.add(Exit);
        CarMenu.add(addCar);
        CarMenu.add(updateCar);
        CarMenu.add(removeCar);
        CarMenu.add(ViewbookedCars);
        CarMenu.add(ViewUnbookedCars);

        CustomerMenu.add(addCustomer);
        CustomerMenu.add(updateCustomer);
        CustomerMenu.add(removeCustomer);

        CarOwnerMenu.add(addCarOwner);
        CarOwnerMenu.add(updateCarOwner);
        CarOwnerMenu.add(removeCarOwner);

        HelpMenu.add(ViewJavaDoc);
        HelpMenu.add(ViewDocumentation);
        HelpMenu.add(About);

        menu_Bar.add(File);
        menu_Bar.add(CarMenu);
        menu_Bar.add(CustomerMenu);
        menu_Bar.add(CarOwnerMenu);
        menu_Bar.add(HelpMenu);

        MainFrame.setJMenuBar(menu_Bar);

        Exit.addActionListener(new Parent_JFrame_ActionListner());
        addCar.addActionListener(new Parent_JFrame_ActionListner());
        updateCar.addActionListener(new Parent_JFrame_ActionListner());
        removeCar.addActionListener(new Parent_JFrame_ActionListner());
        ViewbookedCars.addActionListener(new Parent_JFrame_ActionListner());
        ViewUnbookedCars.addActionListener(new Parent_JFrame_ActionListner());

        addCustomer.addActionListener(new Parent_JFrame_ActionListner());
        updateCustomer.addActionListener(new Parent_JFrame_ActionListner());
        removeCustomer.addActionListener(new Parent_JFrame_ActionListner());

        addCarOwner.addActionListener(new Parent_JFrame_ActionListner());
        updateCarOwner.addActionListener(new Parent_JFrame_ActionListner());
        removeCarOwner.addActionListener(new Parent_JFrame_ActionListner());

        ViewJavaDoc.addActionListener(new Parent_JFrame_ActionListner());
        ViewDocumentation.addActionListener(new Parent_JFrame_ActionListner());
        About.addActionListener(new Parent_JFrame_ActionListner());

    }

    public static JFrame getMainFrame() {
        return MainFrame;
    }

    private class Parent_JFrame_ActionListner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Exit": {
                    int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to terminate the program.\n"
                            + " Are you sure you want to continue ?", "Close Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
                    if (showConfirmDialog == 0) {
                        System.exit(0);
                    }
                }
                break;
                case "Add Car": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    Car_Add ac = new Car_Add();
                    ac.setVisible(true);
                }
                break;
                case "Update Car": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    Car_Update ac = new Car_Update();
                    ac.setVisible(true);
                }
                break;
                case "Remove Car": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    Car_Remove ac = new Car_Remove();
                    ac.setVisible(true);
                }
                break;
                case "View booked Cars": {
                    ArrayList<Car> SearchBookedCars_Array = Booking.getBookedCars();
                    String result = "";
                    if (!SearchBookedCars_Array.isEmpty()) {
                        for (int i = 0; i < SearchBookedCars_Array.size(); i++) {
                            result += (i + 1) + " : " + SearchBookedCars_Array.get(i) + "\n";
                        }
                    } else {
                        result = "No Cars are Booked !";
                    }
                    JOptionPane.showMessageDialog(null, result);
                }
                break;
                case "View Unbooked Cars": {
                    ArrayList<Car> SearchUnBookedCars_Array = Booking.getUnbookedCars();
                    String result = "";
                    if (!SearchUnBookedCars_Array.isEmpty()) {
                        for (int i = 0; i < SearchUnBookedCars_Array.size(); i++) {
                            result += (i + 1) + " : " + SearchUnBookedCars_Array.get(i) + "\n";
                        }
                    } else {
                        result = "No UnBooked Cars are available !";
                    }
                    JOptionPane.showMessageDialog(null, result);
                }
                break;
                case "Add Customer": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    Customer_Add aco = new Customer_Add();
                    aco.frame.setVisible(true);
                }
                break;
                case "Update Customer": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    new Customer_Update().frame.setVisible(true);
                }
                break;
                case "Remove  Customer": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    new Customer_Remove().frame.setVisible(true);
                }
                break;
                case "Add Car Owner": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    CarOwner_Add aco = new CarOwner_Add();
                    aco.frame.setVisible(true);
                }
                break;
                case "Update Car Owner": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    new CarOwner_Update().frame.setVisible(true);
                }
                break;
                case "Remove Car Owner": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    new CarOwner_Remove().frame.setVisible(true);
                }
                break;
                case "View JavaDoc": {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            File myFile = new File("JavaDoc_Documentation_About.pdf");
                            if (myFile.exists()) {
                                Desktop.getDesktop().open(myFile);
                            } else {
                                JOptionPane.showMessageDialog(null, "JavaDoc not found !");
                            }
                        } catch (IOException ex) {

                        }
                    }
                }
                break;
                case "View Documentation": {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            File myFile = new File("JavaDoc_Documentation_About.pdf");
                            if (myFile.exists()) {
                                Desktop.getDesktop().open(myFile);
                            } else {
                                JOptionPane.showMessageDialog(null, "JavaDoc not found !");
                            }
                        } catch (IOException ex) {

                        }
                    }
                }
                break;
                case "About": {
                    JOptionPane.showMessageDialog(null, "THIS PROGRAM IS WRITTEN AS A SEMESTER PROJECT OF OBJECT ORIENTED PROGRAMMING PROGRAMMIG  BY ABDULLAH SHAHID !");
                }
                break;

            }
        }
    }
}

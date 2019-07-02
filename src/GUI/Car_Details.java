package GUI;

import BackendCode.Booking;
import BackendCode.Car;
import BackendCode.CarOwner;
import java.awt.Dimension;
import javax.swing.table.DefaultTableModel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class Car_Details {

    private static DefaultTableModel tablemodel; // it is made static so that it can be accessed in add GUI class to update the Jtable when a new record is added

    private static JButton SearchName_Button, SearchRegNo_Button, Add_Button,
            Update_Button, Remove_Button, BackButton, LogoutButton;
    private static JTextField SearchName_TextField, SearchRegNo_TextField;
    private static JScrollPane jScrollPane1;
    private static JTable jTable1;
    private JPanel MainPanel;

    /**
     * @return the tablemodel
     */
    public static DefaultTableModel getTablemodel() {
        return tablemodel;
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    public Car_Details() {
        MainPanel = new JPanel();
        Parent_JFrame.getMainFrame().setTitle("Car Details - Rent-A-Car Management System");
        MainPanel.setLayout(new AbsoluteLayout());
        MainPanel.setMinimumSize(new Dimension(1366, 730));

        SearchRegNo_Button = new JButton("Search Reg_No");
        SearchRegNo_TextField = new JTextField();

        SearchName_Button = new JButton("Search Name");
        SearchName_TextField = new JTextField();

        Add_Button = new JButton("Add");
        Update_Button = new JButton("Update");
        Remove_Button = new JButton("Remove");
        BackButton = new JButton("Back");
        LogoutButton = new JButton("Logout");
        

        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
//ID,  Maker,  Name,  Colour,  Type,  SeatingCapacity,  Model,  Condition,  RegNo, RentPerHour,  IsRented RentDate, carOwner customer

        String[] columns = {"Sr#", "ID", "Maker", "Name", "Colour", "Type", "Seats", "Model", "Condition",
            "Reg No.", "Rent/hour", "Car Owner"};
        tablemodel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        jTable1 = new JTable(getTablemodel());
        jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        jTable1.setPreferredScrollableViewportSize(new Dimension(2000, 550));
        jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(jTable1);
        jTable1.setFillsViewportHeight(true);// makes the size of table equal to that of scroll pane to fill the table in the scrollpane
        ArrayList<Car> Car_objects = Car.View();
        for (int i = 0; i < Car_objects.size(); i++) {
//ID,  Maker,  Name,  Colour,  Type,  SeatingCapacity,  Model,  Condition,  RegNo, 
//RentPerHour,  IsRented RentDate, carOwner customer
            int ID = Car_objects.get(i).getID();
            String maker = Car_objects.get(i).getMaker();
            String Name = Car_objects.get(i).getName();
            String color = Car_objects.get(i).getColour();
            String type = Car_objects.get(i).getType();
            int seatingCapacity = Car_objects.get(i).getSeatingCapacity();
            String model = Car_objects.get(i).getModel();
            String condition = Car_objects.get(i).getCondition();
            String regNo = Car_objects.get(i).getRegNo();
            int rentPerHour = Car_objects.get(i).getRentPerHour();
            CarOwner carOwner = Car_objects.get(i).getCarOwner();

            String customerID = "";
            String customerName = "";
            String[] one_s_Record = {((i + 1) + ""), "" + ID, maker, Name, color, type, seatingCapacity+"",
                model, condition, regNo, rentPerHour + "", carOwner.getID() + ": " + carOwner.getName(), customerID + ": " + customerName};
            tablemodel.addRow(one_s_Record);
        }

        // center aligning the text in all the columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);
        jTable1.getColumnModel().getColumn(11).setCellRenderer(centerRenderer);
        

        // adjusting size of each column
        jTable1.getColumnModel().getColumn(0).setMinWidth(20);
        jTable1.getColumnModel().getColumn(1).setMinWidth(20);
        jTable1.getColumnModel().getColumn(2).setMinWidth(170);
        jTable1.getColumnModel().getColumn(3).setMinWidth(170);
        jTable1.getColumnModel().getColumn(4).setMinWidth(140);
        jTable1.getColumnModel().getColumn(5).setMinWidth(150);
        jTable1.getColumnModel().getColumn(6).setMinWidth(90);
        jTable1.getColumnModel().getColumn(7).setMinWidth(90);
        jTable1.getColumnModel().getColumn(8).setMinWidth(160);
        jTable1.getColumnModel().getColumn(9).setMinWidth(170);
        jTable1.getColumnModel().getColumn(10).setMinWidth(150);
        jTable1.getColumnModel().getColumn(11).setMinWidth(150);
       

        jTable1.getTableHeader().setReorderingAllowed(false);

        MainPanel.add(SearchRegNo_Button, new AbsoluteConstraints(10, 15, 130, 22));
        MainPanel.add(SearchRegNo_TextField, new AbsoluteConstraints(145, 15, 240, 22));
        MainPanel.add(SearchName_Button, new AbsoluteConstraints(390, 15, 130, 22));
        MainPanel.add(SearchName_TextField, new AbsoluteConstraints(525, 15, 240, 22));
        MainPanel.add(jScrollPane1, new AbsoluteConstraints(10, 60, 1330, 550));
        MainPanel.add(Remove_Button, new AbsoluteConstraints(785, 625, 130, 22));
        MainPanel.add(Add_Button, new AbsoluteConstraints(450, 625, 130, 22));
        MainPanel.add(Update_Button, new AbsoluteConstraints(620, 625, 130, 22));
        MainPanel.add(BackButton, new AbsoluteConstraints(1106, 625, 100, 22));
        MainPanel.add(LogoutButton, new AbsoluteConstraints(1236, 625, 100, 22));
        
        SearchName_Button.addActionListener(new Car_Details_ActionListener());
        SearchRegNo_Button.addActionListener(new Car_Details_ActionListener());
        Add_Button.addActionListener(new Car_Details_ActionListener());
        Update_Button.addActionListener(new Car_Details_ActionListener());
        Remove_Button.addActionListener(new Car_Details_ActionListener());
        BackButton.addActionListener(new Car_Details_ActionListener());
        LogoutButton.addActionListener(new Car_Details_ActionListener());
        
    }

    private class Car_Details_ActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {

                case "Search Reg_No": {
                    String regNo = SearchRegNo_TextField.getText().trim();
                    if (!regNo.isEmpty()) {
                        if (Car.isRegNoValid(regNo)) {
                            Car car = Car.SearchByRegNo(regNo);
                            if (car != null) {
                                JOptionPane.showMessageDialog(null, car.toString());
                                SearchRegNo_TextField.setText("");
                            } else {
                                JOptionPane.showMessageDialog(null, "Required Car not found");
                                SearchRegNo_TextField.setText("");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Reg No.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please Enter Car Reg no first !");
                    }
                }
                break;
                case "Search Name": {
                    String name = SearchName_TextField.getText().trim();
                    if (!name.isEmpty()) {
                        if (Car.isNameValid(name)) {

                            ArrayList<Car> car = Car.SearchByName(name);

                            if (!car.isEmpty()) {
                                JOptionPane.showMessageDialog(null, car.toString());
                                SearchName_TextField.setText("");
                            } else {
                                JOptionPane.showMessageDialog(null, "Required Car not found");
                                SearchName_TextField.setText("");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Name !");
                            SearchName_TextField.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please Enter Car Name first !");
                    }

                }
                break;
                case "Add": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    Car_Add ac = new Car_Add();
                    ac.setVisible(true);
                }
                break;
                case "Update": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    Car_Update ac = new Car_Update();
                    ac.setVisible(true);
                }
                break;
                case "Remove": {
                    Parent_JFrame.getMainFrame().setEnabled(false);
                    Car_Remove ac = new Car_Remove();
                    ac.setVisible(true);
                }
                break;
                case "Back": {
                    Parent_JFrame.getMainFrame().setTitle("Rent-A-Car Management System [REBORN]");
                    MainMenu mm = new MainMenu();
                    Parent_JFrame.getMainFrame().getContentPane().removeAll();
                    Parent_JFrame.getMainFrame().add(mm.getMainPanel());
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
                case "Book": {
                    if (!Booking.getUnbookedCars().isEmpty()) {
                        Parent_JFrame.getMainFrame().setEnabled(false);
                        Booking_BookCar ac = new Booking_BookCar();
                        ac.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "No UnBooked Cars are available !");
                    }
                }
                break;
                case "Unbook": {
                    if (!Booking.getBookedCars().isEmpty()) {
                        Parent_JFrame.getMainFrame().setEnabled(false);
                        Booking_UnBookCar ac = new Booking_UnBookCar();
                        ac.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "No Booked Cars found !");
                    }
                }
                break;
            }
        }
    }
}

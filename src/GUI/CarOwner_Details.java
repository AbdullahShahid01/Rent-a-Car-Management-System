package GUI;

import BackendCode.Car;
import BackendCode.CarOwner;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class CarOwner_Details implements ActionListener {

    private JTextField SearchID_TextField;
    private JButton SearchID_Button, SearchName_Button, Update_Button, Add_Button, Remove_Button, Back_Button, Logout_Button, ClearBalance_Button;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JTextField SearchName_TextField;
    static DefaultTableModel tablemodel;
    private JPanel MainPanel;

    public CarOwner_Details() {
        MainPanel = new JPanel();
        Parent_JFrame.getMainFrame().setTitle("Car Owner Details - Rent-A-Car Management System");
        MainPanel.setLayout(new AbsoluteLayout());
        MainPanel.setMinimumSize(new Dimension(1366, 730));

        SearchID_Button = new JButton("Search ID");
        Update_Button = new JButton("Update");
        Add_Button = new JButton("Add");
        Remove_Button = new JButton("Remove");
        Back_Button = new JButton("Back");
        Logout_Button = new JButton("Logout");
        SearchName_Button = new JButton("Search Name");
        ClearBalance_Button = new JButton("Clear Balance");
        SearchID_TextField = new JTextField();
        SearchName_TextField = new JTextField();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();

        String[] columns = {"Sr#", "ID", "CNIC", "Name", "Contact Number", "Car Given for rent", "Balance"};
        tablemodel = new DefaultTableModel(columns, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        jTable1 = new JTable(tablemodel);

        jTable1.setSize(new Dimension(1330, 550));
        jScrollPane1 = new JScrollPane(jTable1);
        jTable1.setFillsViewportHeight(true);// makes the size of table equal to that of scroll pane to fill the table in the scrollpane
        ArrayList<CarOwner> CarOwner_objects = CarOwner.View();
        for (int i = 0; i < CarOwner_objects.size(); i++) {

            int ID = CarOwner_objects.get(i).getID();
            String CNIC = CarOwner_objects.get(i).getCNIC();
            String Name = CarOwner_objects.get(i).getName();
            String ContactNo = CarOwner_objects.get(i).getContact_No();
            int Balance = CarOwner_objects.get(i).getBalance();

            // getting all cars for this Owner
            String carGivenOnRent = "";
            ArrayList<Car> cars = Car.View();

            for (int j = 0; j < cars.size(); j++) {
                if (cars.get(j).getCarOwner().getID() == ID) {
                    carGivenOnRent += cars.get(j).getID() + ": " + cars.get(j).getName() + "  ";
                }
            }
            if (carGivenOnRent.isEmpty()) {
                carGivenOnRent = "No Cars given for Rent !";
            }

            String[] one_s_Record = {(i + 1) + "", "" + ID, CNIC, Name, ContactNo, carGivenOnRent, Balance + ""};
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

        // adjusting size of each column
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(70);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(170);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(110);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(180);
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(140);
        jTable1.getColumnModel().getColumn(6).setPreferredWidth(100);
//        jScrollPane1.setViewportView(jTable1);
        MainPanel.add(SearchID_Button, new AbsoluteConstraints(390, 10, 130, 22));
        MainPanel.add(SearchID_TextField, new AbsoluteConstraints(525, 10, 240, 22));
        MainPanel.add(SearchName_Button, new AbsoluteConstraints(10, 10, 130, 22));
        MainPanel.add(SearchName_TextField, new AbsoluteConstraints(145, 10, 240, 22));
        MainPanel.add(jScrollPane1, new AbsoluteConstraints(10, 50, 1330, 550));
        MainPanel.add(Update_Button, new AbsoluteConstraints(579, 625, 130, 22));
        MainPanel.add(Add_Button, new AbsoluteConstraints(420, 625, 130, 22));
        MainPanel.add(Remove_Button, new AbsoluteConstraints(735, 625, 130, 22));
        MainPanel.add(Back_Button, new AbsoluteConstraints(1106, 625, 100, 22));
        MainPanel.add(Logout_Button, new AbsoluteConstraints(1236, 625, 100, 22));
        MainPanel.add(ClearBalance_Button, new AbsoluteConstraints(10, 625, 200, 22));

        SearchID_Button.addActionListener(this);
        SearchName_Button.addActionListener(this);
        Remove_Button.addActionListener(this);
        Add_Button.addActionListener(this);
        Update_Button.addActionListener(this);
        Back_Button.addActionListener(this);
        Logout_Button.addActionListener(this);
        ClearBalance_Button.addActionListener(this);
    }

//    public static void main(String args[]) {
//        new CarOwner_Details().setVisible(true);
//
//    }
    public JPanel getMainPanel() {
        return MainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Search ID": {
                String id = SearchID_TextField.getText().trim();
                if (!id.isEmpty()) {
                    if (CarOwner.isIDvalid(id)) {
                        CarOwner co = CarOwner.SearchByID(Integer.parseInt(id));
                        if (co != null) {
                            JOptionPane.showMessageDialog(null, co.toString());
                            SearchID_TextField.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Required person not found");
                            SearchID_TextField.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid ID !");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please Enter ID first !");
                }
            }
            break;
            case "Search Name": {
                String name = SearchName_TextField.getText().trim();
                if (!name.isEmpty()) {
                    if (CarOwner.isNameValid(name)) {
                        ArrayList<CarOwner> carOwnerArrayList = CarOwner.SearchByName(name);
                        String record = "";

                        if (!carOwnerArrayList.isEmpty()) {
                            for (int i = 0; i < carOwnerArrayList.size(); i++) {
                                record += carOwnerArrayList.get(i).toString() + "\n";
                            }
                            JOptionPane.showMessageDialog(null, record);
                            SearchName_TextField.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Required person not found");
                            SearchName_TextField.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Name !");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please Enter Name first !");
                }
            }
            break;
            case "Add": {
                Parent_JFrame.getMainFrame().setEnabled(false);
                CarOwner_Add aco = new CarOwner_Add();
                aco.frame.setVisible(true);
            }
            break;
            case "Remove": {
                Parent_JFrame.getMainFrame().setEnabled(false);
                new CarOwner_Remove().frame.setVisible(true);
            }
            break;

            case "Update": {
                Parent_JFrame.getMainFrame().setEnabled(false);
                new CarOwner_Update().frame.setVisible(true);
            }
            break;

            case "Clear Balance": {
//                Creating an array that contains IDs of all CarOwners
                ArrayList<CarOwner> View = CarOwner.View(); // getting all the available Car Owner Objects
                if (!View.isEmpty()) {
                    ArrayList<String> IDsArray = new ArrayList<>(0);
                    for (int i = 0; i < View.size(); i++) { // getting IDs of all the Car Owners with Balance > 0
                        if (View.get(i).getBalance() != 0) {
                            IDsArray.add(View.get(i).getID() + "");
                        }
                    }

                    Object showInputDialog = JOptionPane.showInputDialog(null, "Select ID for Car Owner whose balance you want to clear.",
                            "Clear Balance", JOptionPane.PLAIN_MESSAGE, null, IDsArray.toArray(), null);
                    System.out.println(showInputDialog);

                    if (showInputDialog != null) {
                        CarOwner carOwner = CarOwner.SearchByID(Integer.parseInt(showInputDialog + ""));

                        int showConfirmDialog = JOptionPane.showConfirmDialog(null, "You are about to clear the balance for the following Car Owner\n"
                                + carOwner + "\nAre you sure you want to continue ?", "Clear Balance Confirmation",
                                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null);
                        if (showConfirmDialog == 0) {
                            carOwner.setBalance(0);
                            carOwner.Update();
                            Parent_JFrame.getMainFrame().getContentPane().removeAll();
                            CarOwner_Details cd = new CarOwner_Details();
                            Parent_JFrame.getMainFrame().add(cd.getMainPanel());
                            Parent_JFrame.getMainFrame().getContentPane().revalidate();
                            JOptionPane.showMessageDialog(null, "Balance Successfully Cleared !");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Car Owner is registered !");
                }
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
                JFrame frame = Runner.getFrame();
                Login login = new Login();
                JPanel panel = login.getMainPanel();
                frame.add(panel);
                frame.setVisible(true);
            }
            break;
        }
    }
}

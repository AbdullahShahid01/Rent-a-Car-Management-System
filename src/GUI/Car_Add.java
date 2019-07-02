package GUI;

import BackendCode.Car;
import BackendCode.CarOwner;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author @AbdullahShahid01
 */
public class Car_Add extends JFrame {

    JButton Add_Button, Cancel_Button;
    JLabel Maker_Label, Name_Label, Color_Label, Type_Label, SeatingCapacity_Label, Model_Label, Condition_Label, RegNo_Label, RentPerHour_Label,
            OwnerID_Label,
            MakerValidity_Label, NameValidity_Label, RegNoValidity_Label, RentPerHourValidity_Label, OwnerIDValidity_Label;
    JTextField Maker_TextField, Name_TextField, RegNo_TextField, RentPerHour_TextField, OwnerID_TextField;
    JComboBox<String> Colour_ComboBox, Type_ComboBox, Model_ComboBox, Condition_ComboBox;
    JSpinner SeatingCapacity_Spinner;

    public Car_Add() {
        super("Add Car");
        setLayout(new FlowLayout());
        setSize(new Dimension(450, 475));
        setResizable(false);
        setLocationRelativeTo(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Parent_JFrame.getMainFrame().setEnabled(true);
                dispose();
            }
        });

        Add_Button = new JButton("Add");
        Cancel_Button = new JButton("Cancel");

        Maker_Label = new JLabel("Maker");
        Name_Label = new JLabel("Name");
        Color_Label = new JLabel("Color");
        Model_Label = new JLabel("Model");
        Type_Label = new JLabel("Car type");
        SeatingCapacity_Label = new JLabel("Seating capacity");
        RegNo_Label = new JLabel("Reg no (ABC-0123)");
        OwnerID_Label = new JLabel("Owner ID");
        RentPerHour_Label = new JLabel("Rent Per Hour (in PKR)");
        Condition_Label = new JLabel("Condition ");

        MakerValidity_Label = new JLabel();
        NameValidity_Label = new JLabel();
        RegNoValidity_Label = new JLabel();
        OwnerIDValidity_Label = new JLabel();
        RentPerHourValidity_Label = new JLabel();

        Maker_TextField = new JTextField();
        Name_TextField = new JTextField();
        RegNo_TextField = new JTextField();
        OwnerID_TextField = new JTextField();
        RentPerHour_TextField = new JTextField();

        String[] Colours = {"White", "Red", "Silver", "Blue", "Black"};
        // try to initialize array from text file so that new items can be added and can be updated
        Colour_ComboBox = new JComboBox<>(Colours);
        String[] Types = {"Familycar", "Comercial", "Microcar", "Compact car", "Mid-size car", "Supercar", "Convertible", "Sports cars"};
        Type_ComboBox = new JComboBox<>(Types);

        // Creating an array containing Years from Today's Year till 1950
        int TodaysYear = new Date().getYear() + 1900;
        int noOfYears = TodaysYear - 1949;
        String[] Years = new String[noOfYears];
        for (int i = 0; i < noOfYears; i++) {
            Years[i] = TodaysYear - i + "";
        }
        Model_ComboBox = new JComboBox<>(Years);

        String[] Conditions = {"Excellent", "Good", "Average", "Bad"};
        Condition_ComboBox = new JComboBox<>(Conditions);

        SeatingCapacity_Spinner = new JSpinner();
        SeatingCapacity_Spinner.setModel(new SpinnerNumberModel(4, 1, null, 1));
        SeatingCapacity_Spinner.setFocusable(false);

        Maker_TextField.setPreferredSize(new Dimension(240, 22));
        Name_TextField.setPreferredSize(new Dimension(240, 22));
        RegNo_TextField.setPreferredSize(new Dimension(240, 22));
        OwnerID_TextField.setPreferredSize(new Dimension(240, 22));
        RentPerHour_TextField.setPreferredSize(new Dimension(240, 22));

        Maker_Label.setPreferredSize(new Dimension(175, 22));
        Name_Label.setPreferredSize(new Dimension(175, 22));
        RegNo_Label.setPreferredSize(new Dimension(175, 22));
        OwnerID_Label.setPreferredSize(new Dimension(175, 22));
        RentPerHour_Label.setPreferredSize(new Dimension(175, 22));

        MakerValidity_Label.setPreferredSize(new Dimension(415, 9));
        NameValidity_Label.setPreferredSize(new Dimension(415, 9));
        RegNoValidity_Label.setPreferredSize(new Dimension(415, 9));
        OwnerIDValidity_Label.setPreferredSize(new Dimension(415, 9));
        RentPerHourValidity_Label.setPreferredSize(new Dimension(415, 9));

        SeatingCapacity_Spinner.setPreferredSize(new Dimension(50, 22));
        Add_Button.setPreferredSize(new Dimension(100, 22));
        Cancel_Button.setPreferredSize(new Dimension(100, 22));

        MakerValidity_Label.setForeground(Color.red);
//        MakerValidity_Label.setFont(new Font("Serif", 1, 10));
//        MakerValidity_Label.setText("                                                           MakerValidity_Label");
        NameValidity_Label.setForeground(Color.red);
//        NameValidity_Label.setText("                                                            NameValidity_Label");
        RegNoValidity_Label.setForeground(Color.red);
//        RegNoValidity_Label.setText("RegNoValidity_Label");
        OwnerIDValidity_Label.setForeground(Color.red);
//        OwnerIDValidity_Label.setText("OwnerValidity_Label");
        RentPerHourValidity_Label.setForeground(Color.red);
//        RentPerHourValidity_Label.setText("RentPerHourValidity_Label");

        add(Maker_Label);
        add(Maker_TextField);
        add(MakerValidity_Label);

        add(Name_Label);
        add(Name_TextField);
        add(NameValidity_Label);

        add(RegNo_Label);
        add(RegNo_TextField);
        add(RegNoValidity_Label);

        add(OwnerID_Label);
        add(OwnerID_TextField);
        add(OwnerIDValidity_Label);

        add(RentPerHour_Label);
        add(RentPerHour_TextField);
        add(RentPerHourValidity_Label);

        add(Model_Label);
        add(Model_ComboBox);
        add(Type_Label);
        add(Type_ComboBox);
        add(SeatingCapacity_Label);
        add(SeatingCapacity_Spinner);
        add(Color_Label);
        add(Colour_ComboBox);
        add(Condition_Label);
        add(Condition_ComboBox);

        add(Add_Button);
        add(Cancel_Button);

        Add_Button.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {

                String maker = Maker_TextField.getText().trim(),
                        name = Name_TextField.getText().trim(),
                        regNo = RegNo_TextField.getText().trim(),
                        ownerID = OwnerID_TextField.getText().trim(),
                        rentPerHour = RentPerHour_TextField.getText().trim();

                if (!name.isEmpty()) {
                    if (Car.isNameValid(Name_TextField.getText().trim())) {
                        NameValidity_Label.setText("");
//                        name = Name_TextField.getText().trim();
                    } else {
                        name = null;
                        NameValidity_Label.setText("                                                            Invalid  Car Name !");
                    }
                } else {
                    name = null;
                    NameValidity_Label.setText("                                                            Enter Car Name !");
                }
                if (!maker.isEmpty()) {
                    if (Car.isNameValid(maker)) {
                        MakerValidity_Label.setText("");
//                        maker = Maker_TextField.getText().trim();
                    } else {
                        maker = null;
                        MakerValidity_Label.setText("                                                            Invalid Maker's Name !");
                    }
                } else {
                    maker = null;
                    MakerValidity_Label.setText("                                                            Enter Maker'sName !");
                }
                if (!regNo.isEmpty()) {
                    if (Car.isRegNoValid(regNo)) {
                        RegNoValidity_Label.setText("");
                    } else {
                        regNo = null;
                        RegNoValidity_Label.setText("                                                            Invalid Reg no !");
                    }
                } else {
                    regNo = null;
                    RegNoValidity_Label.setText("                                                            Enter Reg No !");
                }
                if (!ownerID.isEmpty()) {
                    try {
                        if (Integer.parseInt(ownerID) > 0) {
                            OwnerIDValidity_Label.setText("");
//                            ownerID = OwnerID_TextField.getText().trim();
                        } else {
                            ownerID = null;
                            OwnerIDValidity_Label.setText("                                                            ID cannot be '0' or negative !");
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("In GUI.Car_Add: " + ex);
                        ownerID = null;
                        OwnerIDValidity_Label.setText("                                                            Invalid ID !");
                    }
                } else {
                    ownerID = null;
                    OwnerIDValidity_Label.setText("                                                            Enter Owner ID !");
                }
                if (!rentPerHour.isEmpty()) {
                    try {
                        if (Integer.parseInt(rentPerHour) > 0) {
                            RentPerHourValidity_Label.setText("");
                        } else {
                            rentPerHour = null;
                            RentPerHourValidity_Label.setText("                                                            Rent cannot be '0' or negative !");
                        }
                    } catch (NumberFormatException ex) {

                        rentPerHour = null;
                        RentPerHourValidity_Label.setText("                                                            Invalid Rent !");
                    }

                } else {
                    rentPerHour = null;
                    RentPerHourValidity_Label.setText("                                                            Enter Rent !");
                }

//Car(id, maker, name, color, Type, seatingCapacity, model, condition, regNo, rentPerHour, carOwner);
                try {
                    if (maker != null && name != null && regNo != null && ownerID != null && rentPerHour != null) {
                        CarOwner carOwner = CarOwner.SearchByID(Integer.parseInt(ownerID));

                        Car car = Car.SearchByRegNo(regNo);

                        if (carOwner != null) {
                            if (car == null) {
                                //Car(id, Maker, Name, Colour, Type, SeatingCapacity, Model, Condition, RegNo, RentPerHour, carOwner)
                                // id is auto
                                car = new Car(0, maker, name, Colour_ComboBox.getSelectedItem() + "",
                                        Type_ComboBox.getSelectedItem() + "",
                                        Integer.parseInt(SeatingCapacity_Spinner.getValue().toString()),
                                        Model_ComboBox.getSelectedItem() + "",
                                        Condition_ComboBox.getSelectedItem() + "",
                                        regNo, Integer.parseInt(rentPerHour), carOwner);
                                car.Add();
                                
                                Parent_JFrame.getMainFrame().getContentPane().removeAll();
                                Car_Details cd = new Car_Details();
                                Parent_JFrame.getMainFrame().add(cd.getMainPanel());
                                Parent_JFrame.getMainFrame().getContentPane().revalidate();
                                JOptionPane.showMessageDialog(null, "Record Successfully Added !");
                                Parent_JFrame.getMainFrame().setEnabled(true);
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, "This Car Registeration no is already registered !");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Owner ID doesnot exists !");
                        }
                    }
                } catch (HeadlessException | NumberFormatException ex) {
                    System.out.println(ex);
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

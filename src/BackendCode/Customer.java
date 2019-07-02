package BackendCode;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author @AbdullahShahid01
 */
public class Customer extends Person implements Serializable {

    private int Bill; // increases after every HOUR when a customers has Booked car(s)

    public Customer() {
        super();
    }

    public Customer(int Bill, int ID, String CNIC, String Name, String Contact_No) {
        super(ID, CNIC, Name, Contact_No);
        this.Bill = Bill;
    }

    public int getBill() {
        return Bill;
    }

    public void setBill(int Bill) {
        this.Bill = Bill;
    }

    @Override
    public String toString() {
        return super.toString() + "Customer{" + "Bill=" + Bill + '}' + "\n";
    }

    @Override
    public void Add() {
        ArrayList<Customer> customers = Customer.View();
        if (customers.isEmpty()) {
            this.ID = 1;
        } else {
            this.ID = customers.get((customers.size() - 1)).ID + 1; // Auto ID...
        }
        customers.add(this);
        File file = new File("Customer.ser");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            for (int i = 0; i < customers.size(); i++) {
                outputStream.writeObject(customers.get(i));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    @Override
    public void Update() {
        ArrayList<Customer> customers = Customer.View();

        // for loop for replacing the new Customer object with old one with same ID
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).ID == ID) {
                customers.set(i, this);
            }
        }

        // code for writing new Customer record 
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("Customer.ser"));
            for (int i = 0; i < customers.size(); i++) {
                outputStream.writeObject(customers.get(i));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    ////////////////////////
    @Override
    public void Remove() {

        ArrayList<Customer> customers = Customer.View();

        // for loop for deleting the required Customer
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).ID == ID) {
                customers.remove(i);
            }
        }

        // code for writing new Customer record 
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("Customer.ser"));
            for (int i = 0; i < customers.size(); i++) {
                outputStream.writeObject(customers.get(i));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    public static ArrayList<Customer> SearchByName(String name) {
        ArrayList<Customer> customers = Customer.View();
        ArrayList<Customer> s = new ArrayList<>();

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).Name.equalsIgnoreCase(name)) {
                s.add(customers.get(i));
            }
        }
        return s;
    }

    public static Customer SearchByCNIC(String CustomerCNIC) {
        ArrayList<Customer> customers = Customer.View();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).CNIC.equalsIgnoreCase(CustomerCNIC)) {
                return customers.get(i);
            }
        }
        return null;
    }

    public static Customer SearchByID(int id) {
        ArrayList<Customer> customers = Customer.View();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).ID == id) {
                return customers.get(i);
            }
        }
        return null;
    }

    public static ArrayList<Customer> View() {
        ArrayList<Customer> CustomerList = new ArrayList<>(0);
        ObjectInputStream inputStream = null;
        try {
// open file for reading
            inputStream = new ObjectInputStream(new FileInputStream("Customer.ser"));
            boolean EOF = false;
// Keep reading file until file ends
            while (!EOF) {
                try {
                    Customer myObj = (Customer) inputStream.readObject();
                    CustomerList.add(myObj);
                } catch (ClassNotFoundException e) {
                    System.out.println(e);
                } catch (EOFException end) {
                    EOF = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        return CustomerList;
    }

}

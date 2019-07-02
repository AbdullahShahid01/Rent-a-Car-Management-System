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

public class CarOwner extends Person implements Serializable {

    private int Balance; // increases after every HOUR when Owner's car(s) is booked

    public CarOwner() {
        super();
    }

    public CarOwner(int Balance, int ID, String CNIC, String Name, String Contact_No) {
        super(ID, CNIC, Name, Contact_No);
        this.Balance = Balance;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int Balance) {
        this.Balance = Balance;
    }

    @Override
    public String toString() {
        return super.toString() + " CarOwner{" + "Balance=" + Balance + '}' + "\n";
    }

    @Override
    public void Add() {
        ArrayList<CarOwner> carOwner = CarOwner.View();
        if (carOwner.isEmpty()) {
            this.ID = 1;
        } else {
            this.ID = carOwner.get(carOwner.size() - 1).ID + 1; // Auto ID ...
        }
        carOwner.add(this);
        File file = new File("CarOwner.ser");
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
            for (int i = 0; i < carOwner.size(); i++) {
                outputStream.writeObject(carOwner.get(i));
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
        ArrayList<CarOwner> carOwner = CarOwner.View();

        // for loop for replacing the new CarOwner object with old one with same ID
        for (int i = 0; i < carOwner.size(); i++) {
            if (carOwner.get(i).ID == ID) {
                carOwner.set(i, this);
            }
        }

        // code for writing new CarOwner record 
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("CarOwner.ser"));
            for (int i = 0; i < carOwner.size(); i++) {
                outputStream.writeObject(carOwner.get(i));
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
    public void Remove() {

        ArrayList<CarOwner> carOwner = CarOwner.View();
        // for loop for deleting the required CarOwner
        for (int i = 0; i < carOwner.size(); i++) {
            if ((carOwner.get(i).ID == ID)) {
                carOwner.remove(i);
            }
        }
        // code for writing new CarOwner record 
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("CarOwner.ser"));
            for (int i = 0; i < carOwner.size(); i++) {
                outputStream.writeObject(carOwner.get(i));
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

    public static ArrayList<CarOwner> SearchByName(String name) {
        ArrayList<CarOwner> carOwner = CarOwner.View();
        ArrayList<CarOwner> s = new ArrayList<>();

        for (int i = 0; i < carOwner.size(); i++) {
            if (carOwner.get(i).Name.equalsIgnoreCase(name)) {
                s.add(carOwner.get(i));
            }
        }
        return s;
    }

    public static CarOwner SearchByCNIC(String carOwnerCNIC) {
        ArrayList<CarOwner> carOwner = CarOwner.View();
        for (int i = 0; i < carOwner.size(); i++) {
            if (carOwner.get(i).CNIC.equalsIgnoreCase(carOwnerCNIC)) {
                return carOwner.get(i);
            }
        }
        return null;
    }

    public static CarOwner SearchByID(int id) {
        ArrayList<CarOwner> carOwner = CarOwner.View();
        for (int i = 0; i < carOwner.size(); i++) {
            if (carOwner.get(i).ID == id) {
                return carOwner.get(i);
            }
        }
        return null;
    }

    public ArrayList<Car> getAllCars() {
        ArrayList<Car> cars = Car.View();
        ArrayList<Car> car1 = new ArrayList<>();
        for (int i = 0; i < cars.size(); i++) {
            if (cars.get(i).getCarOwner().ID == ID) {
                car1.add(cars.get(i));
            }
        }
        return car1;
    }

    public static ArrayList<CarOwner> View() {
        ArrayList<CarOwner> carOwnerList = new ArrayList<>(0);
        ObjectInputStream inputStream = null;
        try {
// open file for reading
            inputStream = new ObjectInputStream(new FileInputStream("CarOwner.ser"));
            boolean EOF = false;
// Keep reading file until file ends
            while (!EOF) {
                try {
                    CarOwner myObj = (CarOwner) inputStream.readObject();
                    carOwnerList.add(myObj);
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
        return carOwnerList;
    }

}

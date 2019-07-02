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
public class Booking implements Serializable {

    private int ID;
    private Customer customer;
    private Car car;
    private long RentTime, ReturnTime; // stores System time when the Book() method is called

    public Booking() {
    }

    public Booking(int ID, Customer customer, Car car, long RentTime, long ReturnTime) {
        this.ID = ID;
        this.customer = customer;
        this.car = car;
        this.RentTime = RentTime;
        this.ReturnTime = ReturnTime;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public long getRentTime() {
        return RentTime;
    }

    public void setRentTime(long RentTime) {
        this.RentTime = RentTime;
    }

    public long getReturnTime() {
        return ReturnTime;
    }

    public void setReturnTime(long ReturnTime) {
        this.ReturnTime = ReturnTime;
    }

    @Override
    public String toString() {
        return "Booking{" + "ID=" + ID + ", \ncustomer=" + customer.toString() + ", \ncar=" + car.toString() + ", \nRentTime=" + RentTime + ", ReturnTime=" + ReturnTime + '}' + "\n";
    }

    public void Add() {
        ArrayList<Booking> booking = Booking.View();
        if (booking.isEmpty()) {
            this.ID = 1;
        } else {
            this.ID = booking.get(booking.size() - 1).ID + 1; // Auto ID ...
        }
        this.ReturnTime = 0;
        booking.add(this);
        File file = new File("Booking.ser");
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
            for (int i = 0; i < booking.size(); i++) {
                outputStream.writeObject(booking.get(i));
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

    public void Update() {
        ArrayList<Booking> booking = Booking.View();

        // for loop for replacing the new Booking object with old one with same ID
        for (int i = 0; i < booking.size(); i++) {
            if (booking.get(i).ID == ID) {
                booking.set(i, this);
            }
        }

        // code for writing new Booking record 
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("Booking.ser"));
            for (int i = 0; i < booking.size(); i++) {
                outputStream.writeObject(booking.get(i));
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

    public void Remove() {

        ArrayList<Booking> booking = Booking.View();
        // for loop for deleting the required Booking
        for (int i = 0; i < booking.size() - 1; i++) {
            if ((booking.get(i).ID == ID)) {

                for (int j = i; j < booking.size() - 1; j++) {
                    booking.set(j, (booking.get(j + 1)));
                }

            }
        }
        // code for writing new Booking record 
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("Booking.ser"));
            for (int i = 0; i < booking.size() - 1; i++) {
                outputStream.writeObject(booking.get(i));
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

    public int calculateBill() {
        // rent calculation
        long rentTime = this.getRentTime();
        long returnTime = this.getReturnTime();
        long totalTime = returnTime - rentTime;
        totalTime = totalTime / (1000 * 60 * 60);

        int rentPerHour = this.getCar().getRentPerHour();
        if (totalTime != 0) {
            return (int) (rentPerHour * totalTime);
        } else {
            return rentPerHour;
        }
    }

    public static ArrayList<Booking> SearchByCustomerID(int CustomerID) {
        ArrayList<Booking> bookingList = new ArrayList<>(0);
        ObjectInputStream inputStream = null;
        try {
// open file for reading
            inputStream = new ObjectInputStream(new FileInputStream("Booking.ser"));
            boolean EOF = false;
// Keep reading file until file ends
            while (!EOF) {
                try {
                    Booking myObj = (Booking) inputStream.readObject();
                    if (myObj.customer.getID() == CustomerID) {
                        bookingList.add(myObj);
                    }
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
        return bookingList;
    }

    public static ArrayList<Booking> SearchByCarRegNo(String CarRegNo) {
        ArrayList<Booking> bookingList = new ArrayList<>(0);
        ObjectInputStream inputStream = null;
        try {
// open file for reading
            inputStream = new ObjectInputStream(new FileInputStream("Booking.ser"));
            boolean EOF = false;
// Keep reading file until file ends
            while (!EOF) {
                try {
                    Booking myObj = (Booking) inputStream.readObject();
                    if (myObj.car.getRegNo().equalsIgnoreCase(CarRegNo)) {
                        bookingList.add(myObj);
                    }
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
        return bookingList;
    }

    public static ArrayList<Booking> SearchByCarID(int carID) {
        ArrayList<Booking> bookingList = new ArrayList<>(0);
        ObjectInputStream inputStream = null;
        try {
// open file for reading
            inputStream = new ObjectInputStream(new FileInputStream("Booking.ser"));
            boolean EOF = false;
// Keep reading file until file ends
            while (!EOF) {
                try {
                    Booking myObj = (Booking) inputStream.readObject();
                    if (myObj.car.getID() == carID) {
                        bookingList.add(myObj);
                    }
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
        return bookingList;
    }

    public static ArrayList<Booking> View() {
        ArrayList<Booking> bookingList = new ArrayList<>(0);
        ObjectInputStream inputStream = null;
        try {
// open file for reading
            inputStream = new ObjectInputStream(new FileInputStream("Booking.ser"));
            boolean EOF = false;
// Keep reading file until file ends
            while (!EOF) {
                try {
                    Booking myObj = (Booking) inputStream.readObject();
                    bookingList.add(myObj);
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
        return bookingList;
    }

    public static ArrayList<Car> getBookedCars() {
        ArrayList<Car> bookedCars = new ArrayList<>();
        ArrayList<Booking> bookings = Booking.View();
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).ReturnTime == 0) {
                bookedCars.add(bookings.get(i).car);
            }
        }
        return bookedCars;
    }

    public static ArrayList<Car> getUnbookedCars() {
        ArrayList<Car> allCars = Car.View();
        ArrayList<Car> bookedCars = Booking.getBookedCars();
        for (int i = 0; i < bookedCars.size(); i++) {
            allCars.remove(bookedCars.get(i));
        }
        return allCars;
    }
}

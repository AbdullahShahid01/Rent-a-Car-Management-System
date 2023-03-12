public class Viewmodel {
    String name;
    Float cgpa;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCgpa() {
        return cgpa;
    }

    public void setCgpa(Float cgpa) {
        this.cgpa = cgpa;
    }

    public Viewmodel(String name, Float cgpa) {
        this.name = name;
        this.cgpa = cgpa;
    }
}
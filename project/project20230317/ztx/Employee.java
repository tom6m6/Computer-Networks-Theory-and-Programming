package dynamic_programming;

public abstract class Employee {
    private String name;
    private String address;
    private int number;
    protected int birthdayMonth;

    public Employee(){};

    public Employee(String name, String address, int number) {
        System.out.println("Constructing an Employee");
        this.name = name;
        this.address = address;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    protected int getSalary(int month){
        return 5000;
    }

    abstract public double computePay();
}
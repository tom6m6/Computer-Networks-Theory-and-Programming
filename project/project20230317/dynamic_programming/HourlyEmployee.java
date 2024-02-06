package dynamic_programming;

public class HourlyEmployee extends Employee{

    private int salaryPerHour;

    private int workHour;

    public HourlyEmployee(int salaryPerHour,int workHour) {
        super();
        this.salaryPerHour = salaryPerHour;
        this.workHour = workHour;
    }

    @Override
    public double computePay() {
        return salaryPerHour * workHour;
    }

    public static void main(String[] args) {
        System.out.println(new HourlyEmployee(200,30).computePay());
    }
}

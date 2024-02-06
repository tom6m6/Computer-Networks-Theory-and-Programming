package dynamic_programming;

import java.util.Calendar;

public class SalesEmployee extends Employee{
    public SalesEmployee(int monthlySales,double rate,int birthdayMonth) {
        super();
        this.monthlySales = monthlySales;
        this.rate = rate;
        this.birthdayMonth = birthdayMonth;
    }

    private int monthlySales;

    private double rate;

    @Override
    public double computePay() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH);
        return monthlySales * rate + getSalary(month);
    }

    public static void main(String[] args) {
        SalesEmployee salesEmployee = new SalesEmployee(2000,0.7,5);
        System.out.println(salesEmployee.computePay());
    }
}

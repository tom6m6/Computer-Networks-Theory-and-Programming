package dynamic_programming;

import java.util.Calendar;

public class SalariedEmployee extends Employee{

    private int salary;

    public SalariedEmployee(int salary,int birthdayMonth) {
        super();
        this.salary = salary;
        this.birthdayMonth = birthdayMonth;
    }

    @Override
    public double computePay() {
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        return salary + (birthdayMonth == month ? 100 : 0);
    }

    public static void main(String[] args) {
        System.out.println(new SalariedEmployee(7000,3).computePay());
    }
}

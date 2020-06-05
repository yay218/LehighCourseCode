// -------------------------------------------------------------------------
/**
 *  Represents an employee who is paid an hourly wage.
 *
 *  @author  Yang Yi
 *  @version 2015.09.06
 */
public class HourlyEmployee extends Employee
{
    //~ Fields ................................................................


    // ----------------------------------------------------------
    /**
     * Create a new HourlyEmployee object.
     * @param name is String type
     * @param payRate is double type
     */
    //~ Constructor ...........................................................

    public HourlyEmployee(String name, double payRate) {
        super(name, payRate);
    }

    //~ Methods ...............................................................
    /**
     * Add a method called weeklyPay
     * @return hourlyWage * 40
     */
    public double weeklyPay() {
        return super.getPayRate() * 40;
    }
}
// -------------------------------------------------------------------------
/**
 *  Represents an employee who is paid a yearly salary.
 *
 *  @author  Yang Yi
 *  @version 2015.09.06
 */
public class SalariedEmployee extends Employee
{

    //~ Fields ................................................................

    // ----------------------------------------------------------
    /**
     * Create a new SalariedEmployee object.
     * @param name is type name
     * @param payRate is double type
     */
    //~ Constructor ...........................................................

    public SalariedEmployee(String name, double payRate) {
        super(name, payRate);
    }

    //~ Methods ...............................................................

    /**
     * Add a method called weeklyPay
     * @return weeklySalary
     */
    public double weeklyPay() {
        return super.getPayRate();
    }

    // ----------------------------------------------------------
    /**
     * Add a method called meetWith.
     * @param otherParticipant is SalariedEmployee type
     * @return the name of otherParticipant
     */
    public String meetWith(SalariedEmployee otherParticipant)
    {
        return otherParticipant.getName() + " is joining " +
            super.getName() + " in a conference";
    }
}
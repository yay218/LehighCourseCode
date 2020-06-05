// -------------------------------------------------------------------------
/**
 *  Represents an employee in a company or business.
 *
 *  @author  Yang Yi
 *  @version 2015.09.06
 */
public abstract class Employee implements MeetingParticipant
{
    //~ Fields ................................................................

    private String name;        // The employee's name.
    private double payRate;     // The employee's pay rate.


    // ----------------------------------------------------------
    /**
     * Create a new Employee object.
     * @param name is String type
     * @param payRate is double type
     */
    //~ Constructor ...........................................................

    public Employee(String name, double payRate) {
        this.name = name;
        this.payRate = payRate;
    }

    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Gets the employee's name.
     * @return the employee's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Add a method called meetWith
     * @param participant is MeetingParticipant type
     * @return String represents the name of participants
     */
    public String meetWith(MeetingParticipant participant) {
        return name + " is meeting with " + participant.getName();
    }

    // ----------------------------------------------------------
    /**
     * Gets the pay rate.
     * @return the pay rate
     */
    public double getPayRate()
    {
        return payRate;
    }

    // ----------------------------------------------------------
    /**
     * Create a abstract method called weeklyPay
     * @return double represents the amount that employee weekly pay
     */
    public abstract double weeklyPay();
}

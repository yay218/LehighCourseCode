import student.TestCase;

//-------------------------------------------------------------------------
/**
 *  Test cases for the HourlyEmployee class.
 *
 *  @author  Yang Yi
 *  @version 2015.09.06
 */
public class HourlyEmployeeTest extends TestCase
{
    /**
     * Add field called HourlyEmployee
     */
    //~ Fields ................................................................

    Employee hourlyEmployee;


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        hourlyEmployee = new HourlyEmployee("Yang", 2.0);
    }

    // ----------------------------------------------------------
    /**
     * Add a test method called testGetName
     */
    public void testGetName()
    {
        assertEquals("Yang", hourlyEmployee.getName());
    }

    // ----------------------------------------------------------
    /**
     * Add a test method called testGetPayRate
     */
    public void testGetPayRate()
    {
        assertEquals(2.0 , hourlyEmployee.getPayRate(), 0.01);
    }

    // ----------------------------------------------------------
    /**
     * Add a test method called testWeeklyPay
     */
    public void testWeeklyPay() {
        assertEquals(80.0, hourlyEmployee.weeklyPay(), 0.01);
    }

    // ----------------------------------------------------------
    /**
     * Add a test method called testMeetWith
     */
    public void testMeetWith() {
        assertEquals("Yang is meeting with Yang",
            hourlyEmployee.meetWith(hourlyEmployee));
    }
}

import student.TestCase;

//-------------------------------------------------------------------------
/**
 *  Test cases for the SalariedEmployee class.
 *
 *  @author  Yang Yi
 *  @version 2015.09.06
 */
public class SalariedEmployeeTest extends TestCase
{


    //~ Fields ................................................................

    /**
     * Add a field called salariedEmployee
     */
    SalariedEmployee salariedEmployee;



    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        salariedEmployee = new SalariedEmployee("Yang", 2.0);
    }


 // ----------------------------------------------------------
    /**
     * Add a test method called testWeeklyPay
     */
    public void testWeeklyPay() {
        assertEquals(2.0, salariedEmployee.weeklyPay(), 0.01);
    }

    // ----------------------------------------------------------
    /**
     * Add a test method called testMeetWith
     */
    public void testMeetWith() {
        assertEquals("Yang is joining Yang in a conference",
            salariedEmployee.meetWith(salariedEmployee));
    }
}

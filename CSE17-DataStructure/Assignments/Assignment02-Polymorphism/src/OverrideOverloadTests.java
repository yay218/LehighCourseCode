import student.*;

//-------------------------------------------------------------------------
/**
 *  Unit tests to highlight the difference between overriding and
 *  overloading in the {@link Employee} and {@link SalariedEmployee}
 *  classes.
 *
 *  @author  Yang Yi
 *  @version 2015.09.06
 */
public class OverrideOverloadTests
    extends TestCase
{
    //~ Instance/static fields ...............................................

    private SalariedEmployee karen1;
    private SalariedEmployee chris1;

    private Employee karen2;
    private Employee chris2;


    //~ Constructor ..........................................................

    // ----------------------------------------------------------
    /**
     * Create a empty OverrideOverloadTests object.
     */
    public OverrideOverloadTests() {
     // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.

    }
//    // ----------------------------------------------------------
//    /**
//     * Creates a new object.
//     * @param karen1 SalariedEmployee
//     * @param chris1 SalariedEmployee
//     * @param karen2 Employee
//     * @param chris2 Employee
//     */
//
//
//    public OverrideOverloadTests(SalariedEmployee karen1,
//        SalariedEmployee chris1,
//        Employee karen2, Employee chris2)
//    {
//        super();
//        this.karen1 = karen1;
//        this.chris1 = chris1;
//        this.karen2 = karen2;
//        this.chris2 = chris2;
//    }

    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        karen1 = new SalariedEmployee("Karen", 2.0);
        karen2 = karen1;

        chris1 = new SalariedEmployee("Chris", 3.0);
        chris2 = chris1;
    }





    // ----------------------------------------------------------
    /**
     * Test meetings between Karen and Chris.
     */
    public void testMeetWith()
    {
        // Identify which version of meetWith() (from Employee or
        // from SalariedEmployee) will be called, and based on that,
        // determine the value produced in each of the calls below. Fill in
        // the blanks in each assertEquals() with the values you expect. Then,
        // after running this test, examine the actual results.  Correct
        // any mistaken expected values you wrote, and then answer the
        // questions that appear in comments after each assertion.

        String result1 = karen1.meetWith(chris1);

        assertEquals("Chris is joining Karen in a conference", result1);

        // 1) Which version of meetWith() (Employee or SalariedEmployee)
        //    was called?
        //    SalariedEmployee

        // 2) Explain WHY:
        //    Because karen1 is SalariedEmployee

        String result2 = karen1.meetWith(chris2);
        assertEquals("Karen is meeting with Chris", result2);

        // 3) Which version of meetWith() (Employee or SalariedEmployee)
        //    was called?
        //    Employee

        // 4) Explain WHY:
        //    Because chris2 is Employee, but the meetWith
        //    in SalariedEmployee is SalariedEmployee type


        String result3 = karen2.meetWith(chris1);

        assertEquals("Karen is meeting with Chris", result3);

        // 5) Which version of meetWith() (Employee or SalariedEmployee)
        //    was called?
        //    Employee

        // 6) Explain WHY:
        //    Because karen2 is Employee

        String result4 = karen2.meetWith(chris2);

        assertEquals("Karen is meeting with Chris", result4);

        // 7) Which version of meetWith() (Employee or SalariedEmployee)
        //    was called?
        //    Employee

        // 8) Explain WHY:
        //    Because karen2 is Employee
    }


}

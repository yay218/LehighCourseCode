import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Create a class test
 *
 * @author Yang Yi
 * @version Oct 1, 2015
 */
public class HistoricEventTest
    extends TestCase
{
    // ~ Instance/static variables .............................................

    private HistoricEvent event;
    /**
     * The second event
     */
    HistoricEvent         event2;
    /**
     * the third event
     */
    HistoricEvent         event3;
    /**
     * EventFinder object
     */
    EventFinder           finder;
    /**
     * HistoricEvents object
     */
    HistoricEvents        events;

    private static final String TITLE   = "CSE 017 Assignment";
    private static final String MESSAGE =
        "I went to class today, and I didn't even get a T-shirt.";

    // ~ Constructor ...........................................................


    // ----------------------------------------------------------
    /**
     * Create a new test class
     */
    public HistoricEventTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }

    // ~ Public methods ........................................................


    // ----------------------------------------------------------
    /**
     * Starting conditions for all tests in this class.
     */
    public void setUp()
    {
        event = new HistoricEvent(2010, TITLE, MESSAGE);
        events = new HistoricEvents();
    }


    // ----------------------------------------------------------
    /**
     * Test the constructor and accessors.
     */
    public void testAccessors()
    {
        assertEquals(2010, event.getYear());
        assertEquals(TITLE, event.getTitle());
        assertEquals(MESSAGE, event.getDescription());
    }


    // ----------------------------------------------------------
    /**
     * Test toString() on the event created in setUp().
     */
    public void testToString()
    {
        assertEquals("[2010] " + TITLE + ": " + MESSAGE, event.toString());
    }


    // ----------------------------------------------------------
    /**
     * Test toString() on an event with a null title.
     */
    public void testToString2()
    {
        event = new HistoricEvent(2010, MESSAGE);
        assertEquals("[2010] " + MESSAGE, event.toString());
    }


    // ----------------------------------------------------------
    /**
     * Test toString() on an event with a null description.
     */
    public void testToString3()
    {
        event = new HistoricEvent(2010, TITLE, null);
        assertEquals("[2010] " + TITLE, event.toString());
    }


    // ----------------------------------------------------------
    /**
     * Test method find
     */
    public void testFind()
    {
        finder = new EventFinder();
        event = new HistoricEvent(
            724,
            "Liang Ling-Can",
            "Liang Ling-Can invents the first fully mechanical clock");
        assertEquals(1, finder.find(event, HistoricEvents.TIMELINE, 0, 22));
        event = new HistoricEvent(
            1645,
            "Henry",
            "Prince Frederik Henry conquerors Holly");
        assertEquals(7, finder.find(event, HistoricEvents.TIMELINE));
        HistoricEvent[] list1 = { HistoricEvents.TIMELINE[0] };
        event = new HistoricEvent(
            724,
            "Liang Ling-Can",
            "Liang Ling-Can invents the first fully mechanical clock");
        assertEquals(1, finder.find(event, list1));
        HistoricEvent[] list2 =
        { HistoricEvents.TIMELINE[0], HistoricEvents.TIMELINE[1] };
        assertEquals(1, finder.find(event, list2));
        event = new HistoricEvent(-3372, "Earlist date in ancient Maya");
        assertEquals(0, finder.find(event, list2));
        assertEquals(0, finder.find(event, list1));
        assertEquals(0, finder.find(event, HistoricEvents.TIMELINE));
        event = new HistoricEvent(
            1815,
            "Cambridge Union Society",
            "The Cambridge Union Society is founded.");
        assertEquals(2, finder.find(event, list2));
        event = new HistoricEvent(
            100,
            "Roman politician and orator",
            "Roman politician and orator, killed "
                + "as part of the proscriptions at 63");
        assertEquals(1, finder.find(event, list2));
        event = new HistoricEvent(2015, "Lehigh 150");
        assertEquals(
            HistoricEvents.TIMELINE.length,
            finder.find(event, HistoricEvents.TIMELINE));

    }


    // ----------------------------------------------------------
    /**
     * Add a method called testCompareTo
     */
    public void testCompareTo()
    {
        event = new HistoricEvent(1000, "First", "First event.");
        event2 = new HistoricEvent(2000, "SecondA", "Second event.");
        event3 = new HistoricEvent(2000, "SecondB", "Third event.");
        assertEquals(0, event.compareTo(event));
        assertTrue(event.compareTo(event2) < 0);
        assertTrue(event2.compareTo(event3) < 0);
    }


    // ----------------------------------------------------------
    /**
     * Test Historic Events
     */
    public void testHistoricEvent()
    {
        assertNotNull(HistoricEvents.TIMELINE[2].getTitle());
    }
}

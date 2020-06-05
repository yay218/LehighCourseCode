// -------------------------------------------------------------------------
/**
 * This class called EventFinder
 *
 * @author Yang Yi
 * @version Oct 1, 2015
 */
public class EventFinder
{
    // ----------------------------------------------------------
    /**
     * Add a method called find
     *
     * @param target
     *            is HistoricEvent
     * @param events
     *            is array HistoricEvent
     * @param startingIndex
     *            is int
     * @param endingIndex
     *            is int
     * @return an integer of position
     */
    public int find(
        HistoricEvent target,
        HistoricEvent[] events,
        int startingIndex,
        int endingIndex)
    {
        int middle = (startingIndex + endingIndex) / 2;

        if (events.length == 1)
        {
            if (target.compareTo(events[0]) <= 0)
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
        if (events.length == 2)
        {
            if (target.compareTo(events[0]) <= 0)
            {
                return 0;
            }
            else if (target.compareTo(events[1]) <= 0)
            {
                return 1;
            }
            else
            {
                return 2;
            }
        }
        if (target.compareTo(events[0]) <= 0)
        {
            return 0;
        }
        else if (target.compareTo(events[events.length - 1]) > 0)
        {
            return events.length;
        }
        if (endingIndex - startingIndex == 1)
        {
            return startingIndex + 1;
        }
        if (events[middle].compareTo(target) != 0)
        {
            if (events[middle].compareTo(target) < 0)
            {
                return find(target, events, middle, endingIndex);
            }
            else
            {
                return find(target, events, startingIndex, middle);
            }
        }
        else
        {
            return middle;
        }
    }


    // ----------------------------------------------------------
    /**
     * Add a method called find
     *
     * @param target
     *            is HistoricEvent
     * @param event
     *            is array of HistoricEvent
     * @return an integer
     */
    public int find(HistoricEvent target, HistoricEvent[] event)
    {
        int startingIndex = 0;
        int endingIndex = event.length - 1;
        return find(target, event, startingIndex, endingIndex);
    }
}

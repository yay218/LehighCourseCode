package cs2114.minesweeper;

// -------------------------------------------------------------------------
/**
 * An enumerated type that represents the possible values of a cell on the
 * MineSweeper game board.
 *
 * @author Tony Allevato
 * @version 2011.08.15
 */
public enum MineSweeperCell
{
    // ~ Constants .............................................................

    /**
     * Represents the contents of an invalid cell. This value can be returned by
     * the {@link MineSweeperBoardBase#getCell(int, int)} method when an invalid
     * cell is specified.
     */
    INVALID_CELL,

    /**
     * Represents a covered cell -- any cell which does not contains a mine, has
     * not been flagged and has not yet been uncovered.
     */
    COVERED_CELL,

    /**
     * Represents a cell that does not contain a mine but has had a flag placed
     * on it.
     */
    FLAG,

    /**
     * Represents a cell that has not been uncovered yet but contains a mine.
     */
    MINE,

    /**
     * Represents a cell that contains a mine and has had a flag placed on it.
     */
    FLAGGED_MINE,

    /**
     * Represents a cell containing a mine that has been uncovered.
     */
    UNCOVERED_MINE,

    /**
     * Represents a cell that has been uncovered and has 0 mines around it.
     */
    ADJACENT_TO_0,

    /**
     * Represents a cell that has been uncovered and has 1 mine around it.
     */
    ADJACENT_TO_1,

    /**
     * Represents a cell that has been uncovered and has 2 mines around it.
     */
    ADJACENT_TO_2,

    /**
     * Represents a cell that has been uncovered and has 3 mines around it.
     */
    ADJACENT_TO_3,

    /**
     * Represents a cell that has been uncovered and has 4 mines around it.
     */
    ADJACENT_TO_4,

    /**
     * Represents a cell that has been uncovered and has 5 mines around it.
     */
    ADJACENT_TO_5,

    /**
     * Represents a cell that has been uncovered and has 6 mines around it.
     */
    ADJACENT_TO_6,

    /**
     * Represents a cell that has been uncovered and has 7 mines around it.
     */
    ADJACENT_TO_7,

    /**
     * Represents a cell that has been uncovered and has 8 mines around it.
     */
    ADJACENT_TO_8;

    // ~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Gets the {@code ADJACENT_TO_*} constant that represents the specified
     * number of adjacent mines. This method is useful when you want to get the
     * cell value for a number of mines that was computed at runtime.
     *
     * @param number
     *            the number of adjacent mines; must be between 0 and 8
     * @return the corresponding {@code ADJACENT_TO_*} constant
     * @throws IllegalArgumentException
     *             if {@code number} was not between 0 and 8
     */
    public static MineSweeperCell adjacentTo(int number)
    {
        if (0 <= number && number <= 8)
        {
            // This code only works as long as the values ADJACENT_TO_0 up to
            // ADJACENT_TO_8 are placed in numerical order in the enum
            // definitions.

            return values()[ADJACENT_TO_0.ordinal() + number];
        }
        else
        {
            throw new IllegalArgumentException(
                "MineSweeperCell.adjacentTo "
                    + "only accepts values from 0 to 8.");
        }
    }
}

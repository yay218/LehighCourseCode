package cs2114.minesweeper;

// -------------------------------------------------------------------------
/**
 * <p>
 * A MineSweeperBoard holds a representation of the contents of the playing
 * field for a Mine Sweeper game. The playing field is represented using a
 * 2-dimensional array of values from the enumerated type
 * {@link MineSweeperCell}. The value stored in each cell of the array indicates
 * the icon which will appear in the corresponding cell of the graphical user
 * interface for the game.
 * </p>
 * <p>
 * This abstract base class defines the basic features of a MineSweeperBoard,
 * which you must create.
 * </p>
 *
 * @version 2011.08.15
 * @author Tony Allevato (based on Stephen Edwards modifications to Grant
 *         Braught's original)
 */
public abstract class MineSweeperBoardBase
{
    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Construct a new MineSweeperBoard object.
     */
    public MineSweeperBoardBase()
    {
        //
    }

    // ~ Methods ...............................................................


    // ----------------------------------------------------------
    /**
     * Get the number of columns in this MineSweeperBoard.
     *
     * @return the number of columns in this MineSweeperBoard.
     */
    public abstract int width();


    // ----------------------------------------------------------
    /**
     * Get the number of rows in this MineSweeperBoard.
     *
     * @return the number of rows in this MineSweeperBoard
     */
    public abstract int height();


    // ----------------------------------------------------------
    /**
     * Get the contents of the specified cell on this MineSweeperBoard. The
     * value returned from this method must be one of the values from the
     * {@link MineSweeperCell} enumerated type.
     *
     * @param x
     *            the column containing the cell.
     * @param y
     *            the row containing the cell.
     * @return the value contained in the cell specified by x and y, or
     *         INVALID_CELL if the specified cell does not exist.
     */
    public abstract MineSweeperCell getCell(int x, int y);


    // ----------------------------------------------------------
    /**
     * Uncover the specified cell. If the cell already contains a flag it should
     * not be uncovered. If there is not a mine under the specified cell then
     * the value in that cell is changed to the number of mines that appear in
     * adjacent cells. If there is a mine under the specified cell the game is
     * over and the player has lost. If the specified cell is already uncovered
     * or is invalid, no change is made to the board.
     *
     * @param x
     *            the column of the cell to be uncovered.
     * @param y
     *            the row of the cell to be uncovered.
     */
    public abstract void uncoverCell(int x, int y);


    // ----------------------------------------------------------
    /**
     * Place or remove a flag from the specified cell. If the cell currently
     * covered then place a flag on the cell. If the cell currently contains a
     * flag, remove that flag but do not uncover the cell. If the cell has
     * already been uncovered or is invalid, no change is made to the board.
     *
     * @param x
     *            the column of the cell to be flagged/unflagged
     * @param y
     *            the row of the cell to be flagged/unflagged
     */
    public abstract void flagCell(int x, int y);


    // ----------------------------------------------------------
    /**
     * Determine if the player has lost the current game. The game is lost if
     * the player has uncovered a mine.
     *
     * @return true if the current game has been lost and false otherwise
     */
    public abstract boolean isGameLost();


    // ----------------------------------------------------------
    /**
     * Determine if the player has won the current game. The game is won when
     * three conditions are met:
     * <ol>
     * <li>Flags have been placed on all of the mines.</li>
     * <li>No flags have been placed incorrectly.</li>
     * <li>All non-flagged cells have been uncovered.</li>
     * </ol>
     *
     * @return true if the current game has been won and false otherwise.
     */
    public abstract boolean isGameWon();


    // ----------------------------------------------------------
    /**
     * Count the number of mines that appear in cells that are adjacent to the
     * specified cell.
     *
     * @param x
     *            the column of the cell.
     * @param y
     *            the row of the cell.
     * @return the number of mines adjacent to the specified cell.
     */
    public abstract int numberOfAdjacentMines(int x, int y);


    // ----------------------------------------------------------
    /**
     * Uncover all of the cells on the board.
     */
    public abstract void revealBoard();


    // ----------------------------------------------------------
    /**
     * Check whether two boards have the same cell contents.
     *
     * @param other
     *            the other object to compare with
     * @return result is boolean type
     */
    public boolean equals(Object other)
    {
        boolean result = false;

        if (other == this)
        {
            result = true;
        }
        else if (other instanceof MineSweeperBoardBase)
        {
            MineSweeperBoardBase b = (MineSweeperBoardBase)other;

            if (b.height() == height() && b.width() == width())
            {
                result = true;
                for (int y = 0; y < height(); y++)
                {
                    for (int x = 0; x < width(); x++)
                    {
                        if (b.getCell(x, y) != getCell(x, y))
                        {
                            return false;
                        }
                    }
                }
            }
        }

        return result;
    }


    // ----------------------------------------------------------
    /**
     * Generate a simple, human-readable representation of the contents of this
     * MineSweeperBoard. This method is intended to be a useful tool for testing
     * purposes. The board is surrounded by dashes, with cells marked using the
     * following conventions:
     *
     * <pre>
     * O = covered cell
     * F = flag
     * M = flagged mine
     * + = covered mine
     * * = uncovered mine (about to explode!)
     * 1..9 or space = uncovered cell
     * </pre>
     *
     * @return buffer.toString() is a String type
     */
    public String toString()
    {
        StringBuffer buffer = new StringBuffer((height() + 2) * (width() + 3));
        for (int x = 0; x < width(); x++)
        {
            buffer.append('-');
        }
        buffer.append("--\n");
        for (int y = 0; y < height(); y++)
        {
            buffer.append('|');
            for (int x = 0; x < width(); x++)
            {
                switch (getCell(x, y))
                {
                    case COVERED_CELL:
                        buffer.append('O');
                        break;
                    case FLAG:
                        buffer.append('F');
                        break;
                    case FLAGGED_MINE:
                        buffer.append('M');
                        break;
                    case MINE:
                        buffer.append('+');
                        break;
                    case UNCOVERED_MINE:
                        buffer.append('*');
                        break;
                    case ADJACENT_TO_0:
                        buffer.append(' ');
                        break;
                    default:
                        int num = getCell(x, y).ordinal()
                            - MineSweeperCell.ADJACENT_TO_0.ordinal();
                        buffer.append(num);
                        break;
                }
            }
            buffer.append("|\n");
        }
        for (int x = 0; x < width(); x++)
        {
            buffer.append('-');
        }
        buffer.append("--\n");
        return buffer.toString();
    }


    // ----------------------------------------------------------
    /**
     * Reset the board using a given series of strings. This method takes a
     * variable number of arguments, and should be called with one String per
     * row, where each String represents one row on the board. The characters in
     * each string are interpreted, one character per board cell, using the same
     * conventions as in toString() (e.g., the character 'O' represents an empty
     * covered cell, '+' represents a covered mine, etc.).
     *
     * @param rows
     *            the Strings to interpret; there must be the same number of
     *            Strings as the number of rows in this board, and each string's
     *            length must be the same as the number of columns in this
     *            board.
     */
    public void loadBoardState(String... rows)
    {
        if (rows.length != height())
        {
            throw new IllegalArgumentException(
                "loadBoardState() was called with " + rows.length
                    + " Strings when the board has " + height() + " rows");
        }

        for (int y = 0; y < height(); y++)
        {
            if (rows[y].length() != width())
            {
                throw new IllegalArgumentException(
                    "loadBoardState() was called with row " + y + " = \""
                        + rows[y] + "\" (only " + rows[y].length()
                        + " characters) when the board has " + width()
                        + " columns");
            }

            for (int x = 0; x < width(); x++)
            {
                switch (rows[y].charAt(x))
                {
                    case 'O':
                        setCell(x, y, MineSweeperCell.COVERED_CELL);
                        break;

                    case 'F':
                        setCell(x, y, MineSweeperCell.FLAG);
                        break;

                    case 'M':
                        setCell(x, y, MineSweeperCell.FLAGGED_MINE);
                        break;

                    case '+':
                        setCell(x, y, MineSweeperCell.MINE);
                        break;

                    case '*':
                        setCell(x, y, MineSweeperCell.UNCOVERED_MINE);
                        break;

                    case ' ':
                        setCell(x, y, MineSweeperCell.ADJACENT_TO_0);
                        break;

                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        setCell(x, y, MineSweeperCell
                            .adjacentTo(rows[y].charAt(x) - '0'));
                        break;

                    default:
                        throw new IllegalArgumentException(
                            "loadBoardState() was called with row " + y
                                + " = \"" + rows[y] + "\", but '"
                                + rows[y].charAt(x)
                                + "' is not a recognized cell state");
                }
            }
        }
    }

    // ~ Protected Methods .....................................................


    // ----------------------------------------------------------
    /**
     * Set the contents of the specified cell on this MineSweeperBoard. The
     * value passed in should be one of the defined constants in the
     * {@link MineSweeperCell} enumerated type.
     *
     * @param x
     *            the column containing the cell
     * @param y
     *            the row containing the cell
     * @param value
     *            the value to place in the cell
     */
    protected abstract void setCell(int x, int y, MineSweeperCell value);
}

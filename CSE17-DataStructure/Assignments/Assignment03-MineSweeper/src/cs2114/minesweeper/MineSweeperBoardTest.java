package cs2114.minesweeper;

/**
 * // -------------------------------------------------------------------------
 * /** This programming project is to test the Mine sweeper board.
 *
 * @author Yang Yi
 * @version Sep 15, 2015
 */
public class MineSweeperBoardTest
    extends student.TestCase
{
    private MineSweeperBoard board;

    private MineSweeperBoard board2;


    // ----------------------------------------------------------
    /**
     * Create a new MineSweeperBoardTest Constructor.
     */
    public MineSweeperBoardTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    /**
     * Create a method called setUp
     */
    public void setUp()
    {
        board = new MineSweeperBoard(4, 4, 2);
        board2 = new MineSweeperBoard(15, 1, 0);
        board2.loadBoardState("OFM+* 123456788");
    }


    /**
     * this method is to test the random constructor.
     */
    public void testMineSweeperBoard()
    {
        int count = 0;
        for (int i = 0; i < board.width(); i++)
        {
            for (int j = 0; j < board.height(); j++)
            {
                if (board.getCell(i, j) == MineSweeperCell.MINE)
                {
                    count++;
                }
            }
        }
        assertEquals(count, board.numberOfMines);
    }


    /**
     * Create a method called assertBoard
     *
     * @param theBoard
     *            is MineSweerBoard type
     * @param expected
     *            is String type
     */
    public void assertBoard(MineSweeperBoard theBoard, String... expected)
    {
        MineSweeperBoard expectedBoard =
            new MineSweeperBoard(expected[0].length(), expected.length, 0);
        expectedBoard.loadBoardState(expected);
        // theBoard.equals(expectedBoard);
        assertEquals(expectedBoard, theBoard);
        // uses equals() from MineSweeperBoardBase
    }


    /**
     * test the width
     */
    public void testWidth()
    {
        board.loadBoardState("    ", "OOOO", "O++O", "OOOO");
        assertEquals(4, board.width());
    }


    /**
     * test the height.
     */
    public void testHeight()
    {
        board.loadBoardState("    ", "OOOO", "O++O", "OOOO");
        assertEquals(4, board.height());
    }


    /**
     * test the getCell method.
     */
    public void testGetCell()
    {
        board.loadBoardState("    ", "OOOF", "OM+O", "OOOO");
        assertEquals(MineSweeperCell.INVALID_CELL, board.getCell(5, 5));
        assertEquals(MineSweeperCell.INVALID_CELL, board.getCell(-5, -5));
        assertEquals(MineSweeperCell.INVALID_CELL, board.getCell(-5, 5));
        assertEquals(MineSweeperCell.INVALID_CELL, board.getCell(5, -5));
        assertEquals(MineSweeperCell.INVALID_CELL, board.getCell(5, -5));
        assertEquals(MineSweeperCell.INVALID_CELL, board.getCell(-5, 1));
        assertEquals(MineSweeperCell.INVALID_CELL, board.getCell(5, 1));
        assertEquals(MineSweeperCell.INVALID_CELL, board.getCell(1, 5));
        assertEquals(MineSweeperCell.FLAGGED_MINE, board.getCell(1, 2));
    }


    /**
     * test the uncoverCell method.
     */
    public void testUncoverCell()
    {
        board.loadBoardState("    ", "OOOO", "O++O", "OOOO");
        board.uncoverCell(3, 2);
        assertBoard(board, "    ", "OOOO", "O++1", "OOOO");
        board.uncoverCell(1, 2);
        assertBoard(board, "    ", "OOOO", "O*+1", "OOOO");
        board.uncoverCell(-1, 0);
        assertBoard(board, "    ", "OOOO", "O*+1", "OOOO");
    }


    // ----------------------------------------------------------
    /**
     * test the flagCell method.
     */
    public void testFlagCell()
    {
        board.loadBoardState("    ", "OOOO", "O++O", "OOOO");
        board.flagCell(1, 2);
        assertBoard(board, "    ", "OOOO", "OM+O", "OOOO");
        board.flagCell(1, 1);
        assertBoard(board, "    ", "OFOO", "OM+O", "OOOO");
        board.flagCell(1, 2);
        assertBoard(board, "    ", "OFOO", "O++O", "OOOO");
        board.flagCell(1, 1);
        assertBoard(board, "    ", "OOOO", "O++O", "OOOO");
        board.flagCell(-1, 5);
        assertEquals(MineSweeperCell.INVALID_CELL, board.getCell(-1, 5));
    }


    /**
     * test the isGameLost.
     */
    public void testIsGameLost()
    {
        board.loadBoardState("    ", "OOOO", "O++O", "OOOO");
        board.uncoverCell(2, 2);
        assertEquals(board.isGameLost(), true);
        board.isGameWon();
    }


    /**
     * test the isGameWon.
     */
    public void testIsGameWon()
    {
        board.loadBoardState("    ", "OOOO", "OMMO", "OOOO");
        assertEquals(false, board.isGameWon());
        board.loadBoardState("    ", "1221", "FMM1", "1221");
        assertEquals(false, board.isGameWon());
        board.loadBoardState("    ", "1221", "1+M1", "1221");
        assertEquals(false, board.isGameWon());
        board.loadBoardState("    ", "1221", "1*M1", "1221");
        assertEquals(false, board.isGameWon());
        board.loadBoardState("    ", "1221", "1MM1", "1221");
        assertEquals(true, board.isGameWon());

    }


    /**
     * test the number of adjacent mines.
     */
    public void testNumberOfAdjacentMines()
    {
        board.loadBoardState("    ", "1221", "1++1", "1221");
        assertEquals(board.numberOfAdjacentMines(1, 1), 2);
    }


    /**
     * test the reveal board method.
     */
    public void testRevealBoard()
    {
        board.loadBoardState("    ", "FOOO", "OM+O", "OOOO");
        board.revealBoard();
        assertBoard(board, "    ", "1221", "1**1", "1221");
        board.uncoverCell(1, 0);
    }


    /**
     * test the set cell method.
     */
    public void testSetCell()
    {
        board.loadBoardState("    ", "OOOO", "O++O", "OOOO");
        board.setCell(1, 2, MineSweeperCell.FLAGGED_MINE);
        assertBoard(board, "    ", "OOOO", "OM+O", "OOOO");
        board.setCell(-1, 5, MineSweeperCell.INVALID_CELL);
        assertEquals(MineSweeperCell.INVALID_CELL, board.getCell(-1, 5));

    }


    // ----------------------------------------------------------
    /**
     * test the to string method.
     */
    public void testToString()
    {
        assertNotNull(board2.toString());
    }

}

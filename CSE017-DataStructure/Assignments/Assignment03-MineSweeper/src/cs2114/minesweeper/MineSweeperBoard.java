package cs2114.minesweeper;

import java.util.Random;

/**
 * // -------------------------------------------------------------------------
 * /** Create a new class MineSweeperBoard
 *
 * @author Yang Yi
 * @version Sep 15, 2015
 */
public class MineSweeperBoard
    extends MineSweeperBoardBase
{
    /**
     * Create variable height
     */
    private int height;
    /**
     * Create variable width
     */
    private int width;

    /**
     * Create variable numberOfMines
     */
    protected int               numberOfMines;
    /**
     * Create a 2D array board
     */
    private MineSweeperCell[][] board;
    /**
     * Create variable isGameLost
     */
    boolean                     isGameLost;


    /**
     * Create a new MineSweeperBoard constructor.
     *
     * @param width
     *            is int type
     * @param height
     *            is int type
     * @param numberOfMines
     *            is int type
     */
    public MineSweeperBoard(int width, int height, int numberOfMines)
    {
        this.width = width;
        this.height = height;
        this.numberOfMines = numberOfMines;
        Random rand = new java.util.Random();
        board = new MineSweeperCell[width][height];

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                board[i][j] = MineSweeperCell.COVERED_CELL;
            }
        }
        int num = 0;
        while (num < numberOfMines)
        {
            int wid1 = rand.nextInt(width);
            int hei1 = rand.nextInt(height);
            if (getCell(wid1, hei1) != MineSweeperCell.MINE)
            {
                board[wid1][hei1] = MineSweeperCell.MINE;
                num++;
            }
        }

    }


    @Override
    public int width()
    {
        return this.width;
    }


    @Override
    public int height()
    {
        return this.height;
    }


    @Override
    public MineSweeperCell getCell(int x, int y)
    {
        if (x >= 0 && x < width && y >= 0 && y < height)
        {
            return board[x][y];
        }
        else
        {
            return MineSweeperCell.INVALID_CELL;
        }
    }


    @Override
    public void uncoverCell(int x, int y)
    {
        if (getCell(x, y) != MineSweeperCell.INVALID_CELL)
        {
            int adjacentMines;
            if (getCell(x, y) == MineSweeperCell.MINE)
            {
                board[x][y] = MineSweeperCell.UNCOVERED_MINE;
                isGameLost = true;
            }
            if (getCell(x, y) == MineSweeperCell.COVERED_CELL)
            {
                adjacentMines = numberOfAdjacentMines(x, y);
                board[x][y] = MineSweeperCell.adjacentTo(adjacentMines);
            }
        }
    }


    @Override
    public void flagCell(int x, int y)
    {
        if (getCell(x, y) == MineSweeperCell.MINE)
        {
            board[x][y] = MineSweeperCell.FLAGGED_MINE;
        }
        else if (getCell(x, y) == MineSweeperCell.COVERED_CELL)
        {
            board[x][y] = MineSweeperCell.FLAG;
        }
        else if (getCell(x, y) == MineSweeperCell.FLAG)
        {
            board[x][y] = MineSweeperCell.COVERED_CELL;
        }
        else if (getCell(x, y) == MineSweeperCell.FLAGGED_MINE)
        {
            board[x][y] = MineSweeperCell.MINE;
        }
    }


    @Override
    public boolean isGameLost()
    {
        return isGameLost;
    }


    @Override
    public boolean isGameWon()
    {
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (getCell(i, j) == MineSweeperCell.COVERED_CELL
                    || getCell(i, j) == MineSweeperCell.MINE
                    || getCell(i, j) == MineSweeperCell.FLAG
                    || getCell(i, j) == MineSweeperCell.UNCOVERED_MINE)
                {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public int numberOfAdjacentMines(int x, int y)
    {
        int numOfAjacentMines = 0;
        for (int i = x - 1; i <= x + 1; i++)
        {
            for (int j = y - 1; j <= y + 1; j++)
            {
                if (getCell(i, j) == MineSweeperCell.MINE
                    || getCell(i, j) == MineSweeperCell.UNCOVERED_MINE
                    || getCell(i, j) == MineSweeperCell.FLAGGED_MINE)
                {
                    numOfAjacentMines++;
                }
            }
        }
        return numOfAjacentMines;
    }


    @Override
    public void revealBoard()
    {
        int adjacentMines;
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (getCell(i, j) == MineSweeperCell.FLAGGED_MINE
                    || getCell(i, j) == MineSweeperCell.MINE)
                {
                    board[i][j] = MineSweeperCell.UNCOVERED_MINE;
                }
                if (getCell(i, j) == MineSweeperCell.FLAG
                    || getCell(i, j) == MineSweeperCell.COVERED_CELL)
                {
                    adjacentMines = numberOfAdjacentMines(i, j);
                    board[i][j] = MineSweeperCell.adjacentTo(adjacentMines);
                }
            }
        }
    }


    @Override
    protected void setCell(int x, int y, MineSweeperCell value)
    {
        if (getCell(x, y) != MineSweeperCell.INVALID_CELL)
        {
            board[x][y] = value;
        }
    }
}

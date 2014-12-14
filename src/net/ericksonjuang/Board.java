package net.ericksonjuang;

/**
 * Created by Erickson on 2014/12/14.
 */
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private int[][] board = new int [8][8];
    private List<int[][]> history = new ArrayList<int[][]>();
    private int blackPoint, whitePoint, moveCount;
    private static final Point directions[] = { new Point(0, -1), new Point(0, 1),
            new Point(-1, 0), new Point(1, 0), new Point(-1, -1),
            new Point(1, -1), new Point(-1, 1), new Point(1, 1) };

    private List<Point> legalMovePosition = new ArrayList<Point>();

    public Board()
    {
        init();
    }

    public void restart()
    {
        init();
    }

    public int getBlackPoint()
    {
        return blackPoint;
    }

    public int getWhitePoint()
    {
        return whitePoint;
    }

    public int getMoveCount()
    {
        return moveCount;
    }

    public int[][] getBoard()
    {
        return board;
    }

    public boolean move(int player, Point p)
    {
        //convert to grid
        p.x -= 40;
        p.y -= 40;
        p.x = (p.x%60 == 0) ? p.x/60 : p.x/60+1;
        p.y = (p.y%60 == 0) ? p.y/60 : p.y/60+1;

        generateLegalMovePosition(player);
        if(legalMovePosition.contains(p))
        {
            changeColor(player, p);
            countPoint();
            return true;
        }
        return false;
    }

    private void init()
    {
        blackPoint = 0;
        whitePoint = 0;
        moveCount = 0;

        for(int i = 0; i < 8; i++)
            for(int j = 0; j < 8; j++)
                board[i][j] = State.None;

		/* 中央初始 */
        board[3][3] = State.White;
        board[4][4] = State.White;
        board[3][4] = State.Black;
        board[4][3] = State.Black;

        history.add(board);

        countPoint();
    }
    private void countPoint()
    {
        blackPoint = 0;
        whitePoint = 0;
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(board[i][j] == State.Black)
                    blackPoint++;
                else if(board[i][j] == State.White)
                    whitePoint++;
            }
        }
    }

    private void generateLegalMovePosition(int player)
    {
        //Clear first
        legalMovePosition.clear();

        //search legal move position

    }

    private void changeColor(int player, Point p)
    {
        int notPlayer = (player == State.Black) ? State.White : State.Black;
        //從八個方向尋找
        for(int i = 0; i < directions.length; i++)
        {
            p.x += directions[i].x;
            p.y += directions[i].y;

            while(!isOverBorder(p) && board[p.x][p.y] == notPlayer)
            {
                board[p.x][p.y] = player;

                p.x += directions[i].x;
                p.y += directions[i].y;
            }
        }
    }

    private boolean isOverBorder(Point p)
    {
        if(p.x < 8 && p.x > -1 && p.y < 8 && p.y > -1)
            return false;

        return true;
    }
}
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
        p.x = (p.x%60 == 0) ? p.x/60 : p.x/60 + 1;
        p.y = (p.y%60 == 0) ? p.y/60 - 1 : p.y/60;
        p.x -= 1;
        p.y -= 1;

        if(isValidMove(player, new Point(p.y, p.x)))
        {
            changeColor(player, new Point(p.y, p.x));
            countPoint();
            return true;
        }
        return false;
    }

    public boolean hasVildMove(int player)
    {
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(isValidMove(player, new Point(i, j)))
                    return true;
            }
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

    private boolean isValidMove(int player, Point p)
    {
        int notPlayer = (player == State.Black) ? State.White : State.Black;
        int row = p.x;
        int col = p.y;

		/* 如果下的位置上已經有棋子，則無效 */
        if (board[row][col] != State.None)
            return false;

		/* 開始對下的位置走出去八個方位的每一步分析是否有敵人的棋子 */
        for (int i = 0; i < 8; i++) {

            boolean hasEnemy = false;
            Point stepOut = directions[i];

            int stepOut_row = row + stepOut.x;
            int stepOut_col = col + stepOut.y;

			/* 防止走出一步之後超出棋盤範圍 */
            if (!isOverBorder(new Point(stepOut_row, stepOut_col))) {

				/* 如果走出一步之後發現是敵人的棋子，則看看可不可以吃掉 */
                if (board[stepOut_row][stepOut_col] == notPlayer) {
                    hasEnemy = true;
                }

                if (hasEnemy) {

					/* 繼續再往外走，計算敵人的棋子數目，若超出範圍或遇到則停止 */
                    int step = 2;
                    stepOut_row = row + step * stepOut.x;
                    stepOut_col = col + step * stepOut.y;

                    while (!isOverBorder(new Point(stepOut_row, stepOut_col))) {

						/*
						 * 如果沒有棋子在那邊就跳出迴圈，表示目前走的方位不能吃掉敵人的棋子 於是換下一個方位分析
						 */
                        if (board[stepOut_row][stepOut_col] == State.None)
                            break;

						/*
						 * 如果是自己的棋子，表示落子的位置是有效的，直接return true
						 */
                        if (board[stepOut_row][stepOut_col] == player)
                            return true;

						/* 如果是敵對的棋子則繼續往外走一步尋找之後是否有自己的棋子 */
                        step++;
                        stepOut_row = row + step * stepOut.x;
                        stepOut_col = col + step * stepOut.y;
                    }
                }
            }
        }

        return false;
    }

    private void changeColor(int player, Point p)
    {
        moveCount++;

        int notPlayer = (player == State.Black) ? State.White : State.Black;
        int row = p.x;
        int col = p.y;

		/* 開始對下的位置走出去八個方位的每一步分析是否有敵人的棋子，並且吃掉 */
        for (int i = 0; i < 8; i++) {

            boolean hasEnemy = false;
            Point stepOut = directions[i];

            int stepOut_row = row + stepOut.x;
            int stepOut_col = col + stepOut.y;

			/* 防止走出一步之後超出棋盤範圍 */
            if (!isOverBorder(new Point(stepOut_row, stepOut_col))) {

				/* 如果走出一步之後發現是敵人的棋子，則開始吃棋子 */
                if (board[stepOut_row][stepOut_col] == notPlayer) {
                    hasEnemy = true;
                }

                if (hasEnemy) {

					/* 繼續再往外走，計算敵人的棋子數目，若超出範圍或遇到則停止 */
                    int step = 2;
                    stepOut_row = row + step * stepOut.x;
                    stepOut_col = col + step * stepOut.y;

                    while (!isOverBorder(new Point(stepOut_row, stepOut_col))) {

						/*
						 * 如果沒有棋子在那邊就跳出迴圈，表示目前走的方位不能吃掉敵人的棋子 於是換下一個方位分析
						 */
                        if (board[stepOut_row][stepOut_col] == State.None)
                            break;

						/*
						 * 如果是自己的棋子，則不需要再往外走，開始把敵對的棋子吃掉，(走多少步就吃多少顆棋)
						 * 吃完後跳出迴圈，換下一個方位分析
						 */
                        if (board[stepOut_row][stepOut_col] == player) {

							/* 將敵對的棋子取代成自己的棋子 */
                            for (int j = 0; j < step; j++)
                                board[row + j * stepOut.x][col + j * stepOut.y] = player;

                            break;
                        }

						/* 如果是敵對的棋子則繼續往外走一步 */
                        step++;
                        stepOut_row = row + step * stepOut.x;
                        stepOut_col = col + step * stepOut.y;
                    }
                }
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
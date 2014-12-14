package net.ericksonjuang;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawBoard extends JPanel {
    private int[][] board;
    private static final Color bg = new Color(233, 186, 30);

    public DrawBoard(int[][] board)
    {
        this.board = board;

    }

    public void setBoard(int[][] board)
    {
        this.board = board;
    }

    public void paintComponent ( Graphics g )
    {
        super.paintComponent(g);
        g.setColor(bg);
        g.fillRect(20, 20, 520, 520);

        //畫Line
        g.setColor(Color.BLACK);
        for(int i = 0; i < 9; i++)
        {
            g.drawLine(40, 40 + 60* i, 520, 40 + 60 * i);
            g.drawLine(40 + 60* i, 40, 40 + 60 * i, 520);
        }

        //畫棋子
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j <8; j++)
            {
                if(board[i][j] == State.Black)
                {
                    g.setColor(Color.BLACK);
                    g.fillArc(45 + j * 60 , 45 + i * 60, 50, 50, 0, 360);
                }
                else if(board[i][j] == State.White)
                {
                    g.setColor(Color.WHITE);
                    g.fillArc(45 + j * 60 , 45 + i * 60, 50, 50, 0, 360);
                }
            }
        } /* end of 畫棋子*/

        // 畫棋盤
        g.setColor(bg);
        g.fillRect(560, 80, 200, 160);

        //black point
        g.setColor(Color.BLACK);
        g.fillArc(590 , 100, 50, 50, 0, 360);

        //white point
        g.setColor(Color.WHITE);
        g.fillArc(590, 170, 50, 50, 0, 360);
    }
}

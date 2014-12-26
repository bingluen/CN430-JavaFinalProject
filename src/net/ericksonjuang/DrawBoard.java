package net.ericksonjuang;

import java.awt.*;

import javax.swing.*;

public class DrawBoard extends JPanel {
    private int[][] board;
    private Board game;
    private int player;
    private static final Color bg = new Color(233, 186, 30);

    public DrawBoard(Board board)
    {
        this.board = board.getBoard();
        this.game = board;
        player = State.None;
        setSize(800, 640);

    }

    public void setBoard(int[][] board)
    {
        this.board = board;
    }

    public void setPlayer(int p)
    {
        this.player = p;
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
            } /* end of 畫棋子*/

        }
        // 畫棋盤
        g.setColor(bg);
        g.fillRect(560, 80, 200, 160);

        g.setFont(new Font("monaco", Font.PLAIN, 40));

        //black point
        g.setColor(Color.BLACK);
        g.fillArc(590 , 100, 50, 50, 0, 360);
        g.drawString(Integer.toString(game.getBlackPoint()), 680, 140);

        //white point
        g.setColor(Color.WHITE);
        g.fillArc(590, 170, 50, 50, 0, 360);
        g.drawString(Integer.toString(game.getWhitePoint()), 680, 210);

        g.setColor(Color.BLACK);
        Font font = new Font("微軟正黑體", Font.PLAIN, 35);
        g.setFont(font);
        String playerString[] = new String[6];
        playerString[State.Black] = "\u9ed1\u68cb\u56de\u5408";
        playerString[State.White] = "\u767d\u68cb\u56de\u5408";
        playerString[State.None] = "\u8acb\u6309START";
        playerString[3] = "\u9ed1\u68cb\u52dd\u5229";
        playerString[4] = "\u767d\u68cb\u52dd\u5229";
        playerString[5] = "\u5e73\u624b";
        g.drawString(playerString[player], 580, 50);

    }
}

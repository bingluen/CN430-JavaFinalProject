package net.ericksonjuang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class Main
{
    static final Board game = new Board();
    static final JFrame window = new JFrame("黑白棋 by Erickson Juang");
    private static final String[] playerString = {"\u9ed1\u68cb", "\u767d\u68cb", "\u5e73\u624b"};
    static JFrame inputDialog;
    static JTextField inputWinnerName;
    static DrawBoard drawing;
    static int player;
    static JButton start, restart, undo;
    public static void main ( String[] arg)
    {
        //init
        player = State.Black;
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 640);
        window.setResizable(false);
        window.setLayout(null);
        window.setLocationRelativeTo(null);


        //prepare menu bar
        /*
        window.setJMenuBar(new JMenuBar() {
            {
                this.add(new JMenu("About") {
                    {
                        this.add(new JMenuItem("About this Soft") {
                            {
                                this.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent actionEvent) {
                                        JOptionPane.showMessageDialog(null,"<html><p style='font-size: 12px; font-family:微軟正黑體;'>1010541 莊秉倫</p></html>");
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
        */
        window.setJMenuBar(new JMenuBar() {
            {

                this.add(new JMenuItem("About") {
                    {
                        this.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent actionEvent) {
                                        JOptionPane.showMessageDialog(null,"<html><p style='font-size: 12px; font-family:微軟正黑體;'>1010541 莊秉倫</p></html>");
                            }
                        });
                    }
                });
            }
        });

        //prepare button

        start = new JButton("Start");
        restart = new JButton("Restart");
        undo = new JButton("Undo");
        start.setBounds(587, 400, 150, 30);
        restart.setBounds(587, 450, 150, 30);
        undo.setBounds(587, 500, 150, 30);
        window.add(start);
        window.add(restart);
        window.add(undo);


        //畫棋盤
        drawing = new DrawBoard(game);


        window.add(drawing);


        window.setVisible(true);


        //監聽按鈕

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawing.setPlayer(player);
                window.repaint();
                //監聽Mouse
                window.addMouseListener(new Mouse() {
                    @Override
                    public void mouseClicked(MouseEvent event)
                    {
                        if(game.move(player, new Point(event.getX(), event.getY())))
                        {
                            player = (player == State.Black) ? State.White : State.Black;
                            if(!game.hasVildMove(player))
                                player = (player == State.Black) ? State.White : State.Black;
                            drawing.setPlayer(player);
                            window.repaint();
                            gameOverCheck();
                        }
                    }
                });
            }
        });

        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                game.restart();
                player = State.Black;
                drawing.setBoard(game.getBoard());
                window.repaint();
            }
        });

        undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.undo();
                player = game.getPlayerHistory()[game.getMoveCount()];
                drawing.setBoard(game.getBoard());
                drawing.setPlayer(player);
                window.repaint();
            }
        });
    }

    private static void gameOverCheck()
    {
        if(game.isGameOver())
        {
            drawing.setPlayer((game.getBlackPoint() > game.getWhitePoint()) ? 3 : ((game.getBlackPoint() < game.getWhitePoint()) ? 4 : 5));
            window.repaint();
            for(int i = 0; i < window.getMouseListeners().length; i++)
                window.removeMouseListener(window.getMouseListeners()[i]);
            final String winner = (game.getBlackPoint() > game.getWhitePoint()) ? "黑棋" : ((game.getBlackPoint() < game.getWhitePoint()) ? "白棋" : "平手");
            JOptionPane.showMessageDialog(null,"<html><p style='font-size: 12px; font-family:微軟正黑體;'>"+winner+" 勝利!</p></html>");

            recordGame(winner);
        }
    }

    private  static void recordGame(String winner)
    {
        /* 提示輸入玩家名稱 */
        inputDialog = new JFrame("請輸入"+winner+"玩家名稱");
        inputDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inputDialog.setSize(250, 125);
        inputDialog.setResizable(false);
        inputDialog.setLocationRelativeTo(null);
        inputDialog.setLayout(null);

        /* 輸入框*/
        inputWinnerName = new JTextField("請輸入"+winner+"玩家名稱");
        inputWinnerName.setBounds(30, 10, 190, 25);
        inputDialog.add(inputWinnerName);

        /* button */
        JButton inputSubmit = new JButton("確定");
        inputSubmit.setBounds(100, 50, 60, 30);
        inputDialog.add(inputSubmit);

        inputDialog.setVisible(true);

        inputSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!inputWinnerName.getText().equals(""))
                {
                    fileIO.outputResult(game,
                            (game.getBlackPoint() > game.getWhitePoint()) ? State.Black : ((game.getBlackPoint() < game.getWhitePoint()) ? State.White : State.None),
                            inputWinnerName.getText());
                    inputDialog.dispose();
                }
            }
        });
    }
}

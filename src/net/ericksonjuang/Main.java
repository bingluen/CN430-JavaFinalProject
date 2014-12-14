package net.ericksonjuang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main
{
    static final Board game = new Board();
    static final JFrame window = new JFrame("黑白棋 by Erickson Juang");
    static int player;
    //static JButton start, restart, undo;
    public static void main ( String[] arg)
    {
        //init
        player = State.Black;
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 640);
        window.setResizable(false);


        //prepare menu bar
        window.setJMenuBar(new JMenuBar() {
            {
                this.add(new JMenu("About") {
                    {
                        this.add(new JMenuItem("About this Soft") {
                            {
                                this.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent actionEvent) {
                                        JOptionPane.showMessageDialog(null,"1010541 莊秉倫");
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });


        //畫棋盤
        DrawBoard drawing = new DrawBoard(game.getBoard());


        window.add(drawing);


        window.setVisible(true);

        //監聽Mouse
        /*
        window.addMouseListener(new Mouse() {
            @Override
            public void mouseClicked(MouseEvent event)
            {
                if(game.move(player, new Point(event.getX(), event.getY())))
                {
                    player = (player == State.Black) ? State.White : State.Black;
                }
            }
        });
        */

    }
}

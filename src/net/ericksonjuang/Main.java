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
    static JLabel statusBar;
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
        DrawBoard drawing = new DrawBoard(game);


        window.add(drawing);


        //Status
        statusBar = new JLabel("test messages");
        window.add(statusBar);

        window.setVisible(true);

        //監聽Mouse

        window.addMouseListener(new Mouse() {
            @Override
            public void mouseClicked(MouseEvent event)
            {
                statusBar.setText(String.format("position [%d, %d]", event.getX(), event.getY()));
                if(game.move(player, new Point(event.getX(), event.getY())))
                {
                    player = (player == State.Black) ? State.White : State.Black;
                    window.repaint();
                    if(!game.hasVildMove(player))
                        player = (player == State.Black) ? State.White : State.Black;
                }
            }
        });


    }
}

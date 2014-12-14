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
    static JButton start, restart, undo;
    public static void main ( String[] arg)
    {
        //init
        player = State.Black;
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 640);
        window.setLayout(new GridBagLayout());


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

        //Prepare Button
        start = new JButton("Start");
        restart = new JButton("Restart");
        undo = new JButton("Undo");
        window.add(start, new GridBagConstraints() {
            {
                this.gridx = 1;
                this.gridy = 0;
                this.gridwidth = 2;
                this.gridheight = 1;
                this.weightx = 0;
                this.weighty = 0;
                this.fill = GridBagConstraints.HORIZONTAL;
                this.anchor = GridBagConstraints.CENTER;
            }

        });
        window.add(restart, new GridBagConstraints() {
            {
                this.gridx = 1;
                this.gridy = 2;
                this.gridwidth = 2;
                this.gridheight = 1;
                this.weightx = 0;
                this.weighty = 0;
                this.fill = GridBagConstraints.HORIZONTAL;
                this.anchor = GridBagConstraints.CENTER;
            }

        });
        window.add(undo, new GridBagConstraints() {
            {
                this.gridx = 1;
                this.gridy = 4;
                this.gridwidth = 2;
                this.gridheight = 1;
                this.weightx = 0;
                this.weighty = 0;
                this.fill = GridBagConstraints.HORIZONTAL;
                this.anchor = GridBagConstraints.CENTER;
            }

        });


        //畫棋盤
        DrawBoard drawing = new DrawBoard(game.getBoard());


        window.add(drawing, new GridBagConstraints() {
            {
                this.gridx = 0;
                this.gridy = 0;
                this.gridwidth = 4;
                this.gridheight = 4;
                this.fill = GridBagConstraints.BOTH;
                this.anchor = GridBagConstraints.CENTER;
            }

        });


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

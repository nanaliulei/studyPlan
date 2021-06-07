package com.liuliu.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingObserverExample {

    JFrame frame;

    public static void main(String[] args) {
        SwingObserverExample swingObserverExample = new SwingObserverExample();
        swingObserverExample.go();
    }

    public void go(){
        frame = new JFrame();
        AngleListener angleListener = new AngleListener();
        DevilListener devilListener = new DevilListener();
        JButton jButton = new JButton("should I do it?");
        jButton.addActionListener(angleListener);
        jButton.addActionListener(devilListener);
        frame.getContentPane().add(BorderLayout.CENTER, jButton);
    }

    class AngleListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("please don't do it.");
        }
    }

    class DevilListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("just do it!");
        }
    }
}

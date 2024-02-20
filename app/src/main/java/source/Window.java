package source;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window {
    JFrame frame;
    JLabel title;
    JLabel border;
    Font serif;
    JButton button1;
    JButton button2;
    JPanel buttonPanel;
    JPanel paddedPanel;

    public Window()
    {
        frame = new JFrame("Alpha Sigma Phi Members");
        buttonPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        serif = new Font("Serif", Font.BOLD, 60);
        title = new JLabel("Member Tracker", SwingConstants.CENTER);
        button1 = new JButton("Read From File");
        button2 = new JButton("Create A New File");
        paddedPanel = new JPanel(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void display()
    {
        frame.setSize(900, 600);
        frame.setLayout(new BorderLayout(5, 5));

        title.setFont(serif);
        title.setForeground(Color.GRAY);
        frame.add(title, BorderLayout.NORTH);

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        frame.add(buttonPanel, BorderLayout.CENTER);

        paddedPanel.add(buttonPanel, BorderLayout.CENTER);
        paddedPanel.setBorder(BorderFactory.createEmptyBorder(20, 150, 20, 150));

        frame.add(paddedPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void stuff()
    {
        button1.addActionListener(new ActionListener() 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            // code to add
        }
    });

    button2.addActionListener(new ActionListener() 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            // Code to execute when button2 is clicked
        }
    });

    }

}
    




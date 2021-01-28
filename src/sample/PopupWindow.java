package sample;

import org.jfree.ui.HorizontalAlignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class PopupWindow extends JFrame{

    public PopupWindow(String text){
        super("Внимание!");
        this.setResizable(false);
        setSize(600,200);
        setLocation(200, 200);
        Font font = new Font("", 0, 18);
        final JLabel label = new JLabel(text);
        label.setFont(font);
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label, BorderLayout.CENTER);
        this.setVisible(true);

    }
}

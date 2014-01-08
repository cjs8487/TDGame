/**
 * Created by CJ on 12/31/13.
 */
package com.cjs07.towerdefense;

import javax.swing.*;

public class Frame extends JFrame
{
    public static void main (String[] args)
    {
        new Frame();
    }

    public Frame ()
    {
        new JFrame();

        this.setTitle("Tower Defense! - by cjs07");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setVisible(true);

        Screen screen = new Screen(this);
        this.add(screen);
    }
}

package com.cjs07.towerdefense;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by CJ on 12/31/13.
 */
public class KeyHandler implements KeyListener
{

    private Screen screen;

    private Screen.KeyTyped keyTyped;

    public KeyHandler (Screen screen)
    {
        this.screen = screen;
        keyTyped = this.screen.new KeyTyped();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        if (keyCode == e.VK_ESCAPE)
        {
            this.keyTyped.keyESC();
        }
        else if (keyCode == e.VK_SPACE)
        {
            this.keyTyped.keySPACE();
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
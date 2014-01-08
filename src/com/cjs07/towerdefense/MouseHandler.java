package com.cjs07.towerdefense;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by CJ on 1/2/14.
 */
public class MouseHandler implements MouseListener, MouseMotionListener
{

    private  Screen screen;
    private Screen.MouseHeld mouseHeld;

    public MouseHandler(Screen screen)
    {
        this.screen = screen;
        this. mouseHeld = this.screen.new MouseHeld();
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        this.mouseHeld.mouseDown(e);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    @Override
    public void mouseDragged (MouseEvent e)
    {
        this.mouseHeld.mouseMoved(e);
    }

    @Override
    public void mouseMoved (MouseEvent e)
    {
        this.mouseHeld.mouseMoved(e);
    }
}

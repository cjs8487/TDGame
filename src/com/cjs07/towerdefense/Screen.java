package com.cjs07.towerdefense;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;

/**
 * Created by CJ on 12/31/13.
 * Developed for the ${PROJECT_NAME}
 */
public class Screen extends JPanel implements Runnable
{

    public Thread thread = new Thread(this);

    Frame frame;
    Level level;
    LevelFile levelFile;

    /** Account */
    User user;

    private int fps = 0;

    public int scene;

    public int hand = 0;
    public int handXPos = 0;
    public int handYPos = 0;

    public boolean running = false;

    public double towerWidth = 1;
    public double towerHeight = 1;

    public int[][] map = new int[22][14];
    public Tower[][] towerMap = new Tower[22][14];
    public Image[] terrain = new Image[100];

    private String packagename = "com/cjs07/towerdefense";

    //Debug variables

    //public boolean inStore = false;
    //public boolean inTower1 = false;
    //boolean mouseDown = false;



    public Screen(Frame frame)
    {
        this.frame = frame;

        this.frame.addKeyListener(new KeyHandler(this));
        this.frame.addMouseListener(new MouseHandler(this));
        this.frame.addMouseMotionListener(new MouseHandler(this));

        towerWidth = this.frame.getWidth() / 25.6;
        towerHeight = this.frame.getHeight() / 20.48;

        thread.start();
    }

    @Override
    public void paintComponent (Graphics g)
    {
        g.clearRect(0, 0, this.frame.getWidth(), this.frame.getHeight());

        if (scene == 0) {
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
        }
        else if (scene == 1)
        {
            //Background
            g.setColor(Color.GREEN);
            g.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());

            //Grid
            g.setColor(Color.GRAY);
            for (int x = 0; x < 22; x++)
            {
                for (int y = 0; y < 14; y++)
                {
                    g.drawImage(terrain[map[x][y]], (int) towerWidth + (x * (int) towerWidth),
                            (int) towerHeight + (y * (int) towerHeight), (int) towerWidth, (int) towerHeight, null);
                    g.drawRect((int) towerWidth + (x * (int) towerWidth), ((int) towerHeight + (y * (int) towerHeight)),
                            (int) towerWidth, (int) towerHeight);
                }
            }
            //heath + money thingy
            g.drawRect(12, (15 * (int) towerWidth) + 12, 125, (900 - (15 * (int) towerHeight) - 12 - 12) / 3);
            g.drawString("Health: " + user.player.health, 12 + 25, (15 * (int) towerHeight) + 12 + 25);

            g.drawRect(12, (15 * (int) towerWidth) + 12 + ((this.frame.getHeight() - (15 *
                    (int) towerHeight) - 12 - 12) / 3), 125, (this.frame.getHeight() - (15 * (int) towerHeight) - 12 - 12) / 3);
            g.drawString("Money: " + user.player.money, 12 + 25, (15 * (int) towerHeight) + 12 + 25 + (int) towerHeight);

            g.drawRect(12, (15 * (int) towerWidth) + 12 + (((900 - (15 * (int) towerHeight) - 12 - 12) / 3) * 2), 125,
                    (this.frame.getHeight() - (15 * (int) towerHeight) - 12 - 12) / 3);

            //tower scroll list buttons
            g.drawRect(12 + 12 + 125, (15 * (int) towerWidth) + 12, 40, (this.frame.getHeight() - (15 *
                    (int) towerHeight) - 12 - 12));

            //scroll list button on the other side

            //tower list
            for (int x = 0; x <  20; x++)
            {
                for (int y = 0; y <  2; y++)
                {
                    if (Tower.towerList[x * 2 + y] != null)
                    {
                        g.drawImage(Tower.towerList[x * 2 + y].texture, (int)(12 + 12 + 125 + 40 + 12 + (x * towerWidth)),
                            ((15 * 50) + 12 + (y * (int) towerHeight)), (int) towerWidth, (int) towerHeight, null);

                        if (Tower.towerList[x * 2 + y].cost > this.user.player.money)
                        {
                            g.setColor(new Color(255, 0, 0 , 100));
                            g.fillRect((int) (12 + 12 + 125 + 40 + 12 + (x * towerWidth)), ((15 * 50) + 12 +
                                    (y * (int) towerHeight)), (int) towerWidth, (int) towerHeight);
                        }
                    }
                    g.drawRect((int)(12 + 12 + 125 + 40 + 12 + (x * towerWidth)), ((15 * 50) + 12 +
                            (y * (int) towerHeight)), (int) towerWidth, (int) towerHeight);
                }
            }

            //Towers on grid
            for (int x = 0; x < 22; x++)
            {
                for (int y = 0; y < 14; y++)
                {
                    if(towerMap[x][y] != null)
                    {
                        g.setColor(Color.GRAY);
                        g.drawOval((int) towerWidth + (x * (int) towerWidth) - (towerMap[x][y].range * 2 *
                                (int) towerWidth + (int) towerWidth) / 2 + (int) towerWidth / 2, (int) towerHeight
                                + (y * (int) towerHeight) - (towerMap[x][y].range * 2 * (int) towerHeight +
                                (int)towerHeight) / 2 + (int) towerHeight / 2, towerMap[x][y].range * 2 * (int) towerWidth +
                                (int) towerWidth, towerMap[x][y].range * 2 * (int) towerHeight +
                                (int) towerHeight);
                        g.setColor(new Color(64, 64, 64, 64));
                        g.fillOval((int) towerWidth + (x * (int) towerWidth) - (towerMap[x][y].range * 2 *
                                (int) towerWidth + (int) towerWidth) / 2 + (int) towerWidth / 2, (int) towerHeight
                                + (y * (int) towerHeight) - (towerMap[x][y].range * 2 * (int) towerHeight +
                                (int)towerHeight) / 2 + (int) towerHeight / 2, towerMap[x][y].range * 2 * (int) towerWidth +
                                (int) towerWidth, towerMap[x][y].range * 2 * (int) towerHeight +
                                (int) towerHeight);
                        g.drawImage(Tower.towerList[towerMap[x][y].id].texture, (int) towerWidth + (x * (int) towerWidth),
                                (int) towerHeight + (y * (int) towerHeight), (int) towerWidth, (int) towerHeight ,null);
                    }
                }
            }

            //HAND
            if (hand != 0 && Tower.towerList[hand - 1] != null)
            {
                g.drawImage(Tower.towerList[hand - 1].texture, this.handXPos - (int)this.towerWidth / 2,
                        this.handYPos - (int) this.towerHeight / 2, (int) this.towerWidth, (int) this.towerHeight, null);
            }
        }
        else
        {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, this.frame.getWidth(), this.frame.getHeight());
        }

        //FPS AT THE BOTTOM
        g.setColor(Color.BLACK);
        g.drawString(fps + "", 10, 10);
    }

    //Only first time
    public void loadGame ()
    {
        user = new User(this);
        levelFile = new LevelFile();

        for(int y = 0; y < 10; y++)
        {
            for (int x = 0; x < 10; x++)
            {
                terrain[x + (y * 10)] = new ImageIcon("res/" + "terrain.png").getImage();
                terrain[x + (y * 10)] = createImage(new FilteredImageSource(terrain[x + (y * 10)].getSource(),
                        new CropImageFilter(x* 25, y * 25, 25, 25)));
            }
        }

        running = true;
    }

    public void startGame (User user, String level)
    {
        user.createPlayer();

        this.level = levelFile.getLevel(level);
        this.level.findSpawnPoint();
        this.map = this.level.map;

        this.scene = 1; //Level 1
    }

    public void run ()
    {
        System.out.println("[Success] Frame created");

        long lastFrame = System.currentTimeMillis();
        int frames = 0;

        loadGame();

        while(running){
            repaint();

            frames++;

            //debug stuff
            //if (scene == 1 && mouseDown)
            //{
            //System.out.println("In store: " + inStore + " at X: " + handXPos + " Y: " + handYPos);
            //System.out.println("In tower 1: " + inTower1 + " at X: " + handXPos + " Y: " + handYPos);
            //}
            // end debug stuff


            if (System.currentTimeMillis() - 1000 >= lastFrame)
            {
                fps = frames;
                frames = 0;
                lastFrame = System.currentTimeMillis();
            }

            try
            {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }

    public void placeTower(int x, int y)
    {
        int xPos = x / (int) towerWidth;
        int yPos = y / (int) towerHeight;

        if (xPos > 0 && xPos <= 22 && yPos <= 14 && yPos > 0)
        {
            xPos -= 1;
            yPos -= 1;

           if (towerMap[xPos][yPos] == null && map[xPos][yPos] == 0)
            {
            user.player.money -= Tower.towerList[hand - 1].cost;

            towerMap[xPos][yPos] = Tower.towerList[hand - 1];
            }
        }
    }

    public class MouseHeld
    {
        boolean mouseDown = false;

        public void mouseDown(MouseEvent e)
        {
            mouseDown = true;

            if(hand != 0)
            {
                placeTower(e.getXOnScreen(), e.getYOnScreen());

                hand = 0;
            }

            updateMouse(e);
        }

        public void updateMouse (MouseEvent e) {
            if (scene == 1)
            {
                if (mouseDown && hand == 0)
                    {
                    if (e.getXOnScreen() >= ((int) (12 + 12 + (int) (towerWidth / 11.52) + towerWidth / 40 + 12))
                            && e.getXOnScreen() <= ((int) 12 + 12 + (int) (towerWidth / 11.52) + towerWidth / 40
                            + 12 + (18 * towerWidth)))
                    {
                        if (e.getYOnScreen() >= (15 * (int) towerHeight) + 12
                                && e.getYOnScreen() <= (15 * (int) towerHeight) + 12 + (int) towerHeight * 2)
                        {
                            //inStore = true;
                            //Tower 1
                            if (e.getXOnScreen() >= ((int) (12 + 12 + (int) (towerWidth / 11.52) + towerWidth /
                                    40 + 12)) && e.getXOnScreen() <= ((int) 12 + 12 + (int) (towerWidth / 11.52) +
                                    (int) towerWidth / 40 + 12 + (int) towerWidth) && e.getYOnScreen() <= (15 *
                                    (int) towerHeight) + 12 && e.getYOnScreen() >= (15 * (int) towerHeight) + 12 +
                                    (int) towerHeight)
                            {
                                //inTower1 = true;
                                if (user.player.money >= Tower.towerList[0].cost)
                                {
                                    System.out.println("[Shop] You bought a tower for " + Tower.towerList[0].cost + "!");
                                    hand = 1;
                                }
                            }
                        }
                    }
                }
            }
        }

        public void mouseMoved(MouseEvent e)
        {
            handXPos = e.getXOnScreen();
            handYPos = e.getYOnScreen();
        }
    }

    public class KeyTyped
    {
        public void keyESC ()
        {
            running = false;
        }

        public void keySPACE()
        {
            startGame(user, "Level1");
        }
    }
}

package com.cjs07.towerdefense;

import javax.swing.*;
import java.awt.*;

/**
 * Created by CJ on 1/1/14.
 */
public class Tower
{

    public String textureFile = "";
    public Image texture;

    public static final Tower[] towerList = new Tower[200];

    public static final Tower lightningTower = new TowerLightning(0, 10, 2, "Lightning Tower")
            .getTextureFile("Lightning Tower");

    public int id;
    public int cost;
    public int range;

    public Tower (int id, int cost, int range, String name)
    {
        if (towerList[id] != null)
        {
            System.out.println("[Tower Initialization] Two towers with same id! id=" + id);
        } else
        {
            towerList[id] = this;
            this.id = id;
            this.cost = cost;
            this.range = range;
            System.out.println("[Tower Initialization] " + name + " successfully initialized with the ID " + id +
                    ", a cost of " + cost + ", and a range of " + range + " tiles");
        }
    }

    public Tower getTextureFile (String str)
    {
        this.textureFile = str;
        this.texture = new ImageIcon("res/tower/" + this.textureFile + ".png").getImage();

        return null;
    }

}
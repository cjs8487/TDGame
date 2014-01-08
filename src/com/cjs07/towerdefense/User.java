package com.cjs07.towerdefense;

/**
 * Created by CJ on 1/1/14.
 */
public class User
{

    private Screen screen;

    Player player;

    int startingCash = 300;
    int startingHealth = 100;

    public User (Screen screen)
    {
        this.screen = screen;

        this.screen.scene = 0; //Sets scene to main menu
    }

    public void createPlayer () {
        this.player = new Player(this);
    }
}

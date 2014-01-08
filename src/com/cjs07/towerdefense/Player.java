package com.cjs07.towerdefense;

/**
 * Created by CJ on 1/1/14.
 */
public class Player
{

    int health;
    int money;

    public Player (User user)
    {
        this.money = user.startingCash;
        this.health = user.startingHealth;
    }
}

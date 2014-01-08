package com.cjs07.towerdefense;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by CJ on 1/3/14.
 */
public class LevelFile
{
    FileInputStream file;
    InputStreamReader reader;

    Scanner scanner;

    Level level = new Level();

    public Level getLevel (String fileName)
    {
        try
        {
            file = new FileInputStream("level/" + fileName + ".level");
            reader = new InputStreamReader(file);

            scanner = new Scanner(reader);

            level.map = new int[22][18];

            int x = 0;
            int y = 0;

            while(scanner.hasNext())
            {
                level.map[x][y] = scanner.nextInt();

                if (x < 22 - 1)
                {
                    x++;
                }
                else
                {
                    y++;

                    x = 0;
                }
            }

            return level;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return null;
    }

}

package com.itschool;

import java.util.Random;

/**
 * Main - class that starts the program
 */
public class Main
{
    private static final int SIZE = 3;
    private static int[][] field = new int[SIZE][SIZE];
    private static int turn = 1;

    public static void drawField()
    {
        for (final int[] row : field)
        {
            for (final int cell : row)
            {
                System.out.print("\t" + (cell == 0 ? " " : cell == 1 ? "X" : "O") + "\t|");
            }
            System.out.println("\n------------------------");
        }
        System.out.println();
    }

    /**
     * Defines step of AI
     */
    public static void stepAI()
    {
        final Random rnd = new Random();
        int row = 1;
        int column = 1;

        while (field[row][column] != 0)
        {
            row = rnd.nextInt(SIZE);
            column = rnd.nextInt(SIZE);
        }
        field[row][column] = turn;
        turn = turn == 1 ? 2 : 1;
    }

    /**
     * Point of enter
     *
     * @param args
     */
    public static void main(final String[] args)
    {
        // System.out.println(Arrays.toString(args));
        drawField();
        turn = new Random().nextInt(2) + 1;
        for (int i = 0; i < SIZE * SIZE; i++)
        {
            stepAI();
            drawField();
            if (checkVictory())
            {
                System.out.println(turn + " is winner");
                return;
            }
        }
        System.out.println("Draw!");
    }

    public static boolean checkVictory()
    {
        boolean isVictory = false;

        for (int row = 0; row < SIZE; row++)
        {
            for (int column = 0; column < SIZE - 1; column++)
            {
                if (field[row][column] != 0 && field[row][column] == field[row][column + 1])
                {
                    isVictory = true;
                }
                else
                {
                    isVictory = false;
                    break;
                }
            }
            if (isVictory)
            {
                break;
            }
        }
        if (!isVictory)
        {
            for (int column = 0; column < SIZE; column++)
            {
                //isVictory = false;
                for (int row = 0; row < SIZE - 1; row++)
                {
                    if (field[row][column] != 0 && field[row][column] == field[row + 1][column])
                    {
                        isVictory = true;
                    }
                    else
                    {
                        isVictory = false;
                        break;
                    }
                }
                if (isVictory)
                {
                    break;
                }
            }
        }

        if (!isVictory)
        {
            for (int column = 0; column < SIZE - 1; column++)
            {
                if (field[column][column] != 0 && field[column][column] == field[column + 1][column + 1])
                {
                    isVictory = true;
                }
                else
                {
                    isVictory = false;
                    break;
                }
            }
        }


        if (!isVictory)
        {
            for (int column = 0; column < SIZE - 1; column++)
            {
                if (field[column][column] != 0 && field[column][SIZE - column - 1] == field[column + 1][SIZE - column - 2])
                {
                    isVictory = true;
                }
                else
                {
                    isVictory = false;
                    break;
                }
            }
        }

        return isVictory;
    }
}

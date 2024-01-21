package org.example;

import java.util.Scanner;
import java.util.logging.Logger;

public class Game {

    Logger logger = Logger.getLogger(getClass().getName());

    Scanner scan = new Scanner(System.in);
    byte input;
    byte rand;
    byte i;
    byte winner = 0;
    char[] box = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    boolean boxEmpty = false;

    public boolean isBoxEmpty() {
        return boxEmpty;
    }

    public void setBoxEmpty(boolean boxEmpty) {
        this.boxEmpty = boxEmpty;
    }



    public void startGame() {

        System.out.println("Enter box number to select. Enjoy!\n");

        boolean isGameActive = true;
        while (isGameActive) {


            drawGrid();
            finalNotificationResult();
            validateInputForUserX();

            whoWin();

            validateIfResultIsDraw();

            randomMoveForPlayerO();
            if (winner!=0){
                finalNotificationResult();
                isGameActive=false;
                scan.close();
            }
        }
    }

    /**
     * The method used to draw grid with digits and then clean up an array by populating spaces ' '
     */
    public void drawGrid() {
        System.out.println("\n\n " + box[0] + " | " + box[1] + " | " + box[2] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[3] + " | " + box[4] + " | " + box[5] + " ");
        System.out.println("-----------");
        System.out.println(" " + box[6] + " | " + box[7] + " | " + box[8] + " \n");
        if (!boxEmpty) {
            for (i = 0; i < 9; i++)
                box[i] = ' ';
            setBoxEmpty(true);
        }
    }

    /**
     * The method used to send notification on the game result depends on the score
     */
    public void finalNotificationResult() {
        switch (winner) {
            case 1:
                System.out.println("You won the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            case 2:
                System.out.println("You lost the game!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
            case 3:
                System.out.println("It's a draw!\nCreated by Shreyas Saha. Thanks for playing!");
                break;
        }
    }

    /**
     * The method used to validate input from user in command line, check if box is not populated otherwise set 'X'
     */

    public void validateInputForUserX() {
        while (true) {
            input = scan.nextByte();
            if (input > 0 && input < 10) {
                if (box[input - 1] == 'X' || box[input - 1] == 'O')
                    logger.info("That one is already in use. Enter another.");
                else {
                    box[input - 1] = 'X';
                    break;
                }
            } else
                logger.info("Invalid input. Enter again.");
        }
    }

    /**
     * The method used to determine box number randomly for player 'O'
     */
    public void randomMoveForPlayerO() {
        if(winner == 0){
            while (true) {
                rand = (byte) (Math.random() * (9 - 1 + 1) + 1);
                if (box[rand - 1] != 'X' && box[rand - 1] != 'O') {
                    box[rand - 1] = 'O';
                    break;
                }
            }
        }

    }

    /**
     * The method used to determine whether result is draw
     */

    public void validateIfResultIsDraw() {
        if (winner==0) {
            boolean draw = true;
            for (int i = 0; i < 9; i++) {
                if (box[i] != 'X' && box[i] != 'O') {
                    draw = false;
                    break;
                }
            }

            if (draw) {
                winner = 3;
            }
        }
    }

    /**
     * The method used to determine who win the game X or O
     */

    public void whoWin() {

        int[][] lines = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };


        for (int[] line : lines) {
            if (box[line[0]] == 'X' && box[line[1]] == 'X' && box[line[2]] == 'X') {
                winner = 1;
                return;
            } else if (box[line[0]] == 'O' && box[line[1]] == 'O' && box[line[2]] == 'O') {
                winner = 2;
                return;
            }
        }
    }


}

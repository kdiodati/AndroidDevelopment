package com.gmail.kdiodati.piggamev2;

import java.util.Random;

class GameLogic{

    Random rand = new Random();

    int rollCount; //keeps track of rolls this turn
    int maxRolls; //if zero infinite rolls allowed
    int winScore; //the score required to win, 100 at default

    int roll; //keeps track of last roll
    int turn; //which player's turn it is
    int p1; //score of player 1
    int p2; //score of player 2
    int score; //score of current turn
    int winner; //0 if no winner yet, -1 if possibly player 1, 1 if p1 won, 2 if p2 won

    void rollTheDie() {
        int rng = (rand.nextInt(6 - 1)) + 1; //gets the random integer between 1-6
        checkRoll(rng);
        rollCount++;
    }

    //does logic of checking if roll was 1 so as to not add to score
    private void checkRoll(int rng) {
        if (rng == 1) {
            roll = 1;
            checkWin();
        }
        else {
            roll = rng;
            score = score + rng;
            checkWin();
        }
    }

    //checks if player has won and/or also if roll was a 1 it passes the turn to other player
    private void checkWin() {
        //player 1 cases
        if (turn == 1) {
            if (score + p1 >= winScore) { //player 1 has over max so let player 2 have final turn
                winner = -1;
                endTurn();
            }
            else if (roll == 1){ //player 1 does not have max and rolled a 1, so pass turn
                endTurn();
            }
            //else keep rolling
        }

        //player 2 cases
        else if (turn == 2) {
            if (winner == -1) { //if player 1 has over max
                if ( (score + p2 > p1) && ( (roll == 1) || ( (maxRolls != 0) && (rollCount >= maxRolls) ) ) ){ //if player 2 > player 1, p2 wins
                    winner = 2;
                    endTurn();
                }
                else {
                    winner = 1;
                    endTurn();
                }
                /*
                else { //if player 2 < player 1, p1 wins
                    winner = 1;
                    endTurn();
                }
                */
                //else keep rolling
            }

            else { //if player 1 does NOT have max
                if (score + p2 >= winScore) { //if player 2 has over max, p2 wins
                    winner = 2;
                    endTurn();
                }
                else if (roll == 1){ //if player 2 has less than max, and rolls a 1, then p1 turn
                    endTurn();
                }
                //else keep rolling
            }
        }

        if (maxRolls != 0) {
            if (rollCount >= maxRolls) {
                endTurn();
            }
        }
    }

    //does the logic of adding turn score to player score and passing turn to other player
    void endTurn() {
        if (turn == 1) {
            p1 += score;
            score = 0;
            turn = 2;
            rollCount = 0;
        }
        else if (turn == 2) {
            p2 += score;
            score = 0;
            turn = 1;
            rollCount = 0;
        }
    }

    //sets the maxRolls if settings are updated
    void updateMaxRolls(int mR) {
        maxRolls = mR;
    }

    //sets winScore if settings are updated
    void updateWinScore(int wS) {
        winScore = wS;
    }

    void resetGame(int mR, int wS) {
        //sets or resets the variables to initial values
        roll = 1;
        turn = 1;
        p1 = 0;
        p2 = 0;
        score = 0;
        winner = 0;

        //preference variables
        rollCount = 0;
        maxRolls = mR;
        winScore = wS;
    }
}

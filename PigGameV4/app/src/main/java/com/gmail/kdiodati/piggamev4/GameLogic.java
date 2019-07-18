package com.gmail.kdiodati.piggamev4;

import java.util.Random;

class GameLogic{

    Random rand = new Random();

    String player1;
    String player2;

    int roll; //keeps track of last roll
    int turn; //which player's turn it is
    int p1; //score of player 1
    int p2; //score of player 2
    int score; //score of current turn
    int winner; //0 if no winner yet, -1 if possibly player 1, 1 if p1 won, 2 if p2 won

    //int rollCount;
    //int maxRolls;
    //int winScore;

    boolean over;

    void rollTheDie() {
        int rng = (rand.nextInt(6 - 1)) + 1; //gets the random integer between 1-6
        checkRoll(rng);
        //rollCount++;
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
            //if (score + p1 >= winScore) { //player 1 has over max so let player 2 have final turn
            if (score + p1 >= 100) { //player 1 has over max so let player 2 have final turn
                winner = -1;
                over = true;
                //endTurn();
            }
            else if (roll == 1){ //player 1 does not have 100 and rolled a 1, so pass turn
                over = true;
                //endTurn();
            }
            //else keep rolling
        }

        //player 2 cases
        else if (turn == 2) {
            if (winner == -1) { //if player 1 has over 100
                //if ( (score + p2 > p1) && ( (roll == 1) || ( (maxRolls != 0) && (rollCount >= maxRolls) ) ) ){ //if player 2 > player 1, p2 wins
                if ( (score + p2 > p1) && ( (roll == 1) ) ) {           // || ( (maxRolls != 0) && (rollCount >= maxRolls) ) ) ){ //if player 2 > player 1, p2 wins
                    winner = 2;
                    endTurn();
                }
                else {
                    winner = 1;
                    endTurn();
                }
                //else keep rolling
            }

            else { //if player 1 does NOT have 100
                //if (score + p2 >= winScore) { //if player 2 has over 100, p2 wins
                if (score + p2 >= 100) { //if player 2 has over 100, p2 wins
                    winner = 2;
                    endTurn();
                }
                else if (roll == 1){ //if player 2 has less than 100, and rolls a 1, then p1 turn
                    over = true;
                    //endTurn(); //set so any thing ending
                }
                //else keep rolling
            }
        }

        /*
        if (maxRolls != 0) {
            if (rollCount >= maxRolls) {
                over = true;
            }
        }
        */
    }

    //does the logic of adding turn score to player score and passing turn to other player
    void endTurn() {
        if (turn == 1) {
            p1 += score;
            turn = 2;
        }
        else if (turn == 2) {
            p2 += score;
            turn = 1;
        }
        score = 0;
        //rollCount = 0;
        over = false;
    }

    /*
    void updateMaxRolls(int mR) {
        maxRolls = mR;
    }

    void updateWinScore(int wS) {
        winScore = wS;
    }
    */

    //void resetGame(int mR, int wS) {
    void resetGame() {
        //sets or resets the variables to initial values
        roll = 1;
        turn = 1;
        p1 = 0;
        p2 = 0;
        score = 0;
        winner = 0;

        over = false;

        //preferences variables
        //rollCount = 0;
        //maxRolls = mR;
        //winScore = wS;
    }

    //void loadData(int roll_, int turn_, int p1_, int p2_, int score_, int winner_, int rollCount_, int maxRolls_, int winScore_, boolean over_) {
    void loadData(int roll_, int turn_, int p1_, int p2_, int score_, int winner_, boolean over_) {
        //sets or resets the variables to game values
        roll = roll_;
        turn = turn_;
        p1 = p1_;
        p2 = p2_;
        score = score_;
        winner = winner_;

        over = over_;

        //preferences variables
        //rollCount = rollCount_;
        //maxRolls = maxRolls_;
        //winScore = winScore_;
    }
}

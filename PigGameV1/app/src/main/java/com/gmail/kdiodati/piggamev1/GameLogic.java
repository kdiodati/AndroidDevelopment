package com.gmail.kdiodati.piggamev1;

import java.util.Random;

class GameLogic{

    int roll; //keeps track of last roll
    int turn; //which player's turn it is
    int p1; //score of player 1
    int p2; //score of player 2
    int score; //score of current turn
    int winner; //0 if no winner yet, -1 if possibly player 1, 1 if p1 won, 2 if p2 won

    void rollTheDie(Random rand) {
        int rng = (rand.nextInt(6 - 1)) + 1; //gets the random integer between 1-6
        checkRoll(rng);
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
            if (score + p1 >= 100) { //player 1 has over 100 so let player 2 have final turn
                winner = -1;
                endTurn();
            }
            else if (roll == 1){ //player 1 does not have 100 and rolled a 1, so pass turn
                endTurn();
            }
            //else keep rolling
        }

        //player 2 cases
        else if (turn == 2) {
            if (winner == -1) { //if player 1 has over 100
                if (score + p2 >= 100) { //if player 2 also is over 100
                    if (score + p2 > p1) { //if player 2 > player 1, p2 wins
                        winner = 2;
                        endTurn();
                    }
                    else { //if player 2 < player 1, p1 wins
                        winner = 1;
                        endTurn();
                    }
                }
                else if (((score + p2) < 100) && (roll == 1)) {
                    winner = 1;
                    endTurn();
                }
                //else keep rolling
            }

            else { //if player 1 does NOT have 100
                if (score + p2 >= 100) { //if player 2 has over 100, p2 wins
                    winner = 2;
                    endTurn();
                }
                else if (roll == 1){ //if player 2 has less than 100, and rolls a 1, then p1 turn
                    endTurn();
                }
                //else keep rolling
            }
        }
    }

    //does the logic of adding turn score to player score and passing turn to other player
    void endTurn() {
        if (turn == 1) {
            p1 += score;
            score = 0;
            turn = 2;
        }
        else if (turn == 2) {
            p2 += score;
            score = 0;
            turn = 1;
        }
    }

    void resetGame() {
        //sets or resets the variables to initial values
        roll = 1;
        turn = 1;
        p1 = 0;
        p2 = 0;
        score = 0;
        winner = 0;
    }
}

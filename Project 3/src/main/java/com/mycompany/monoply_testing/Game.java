/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monoply_testing;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 *
 * @author Opinator
 */
public class Game implements GameI {

    ArrayList<PlayerI> players = new ArrayList<>();
    private int currentPlayer;
    
    @Inject
    BoardI board;
    @Inject
    DiceI dice;
    
    boolean gameOver; 
    
    
    @Override
    public void setup(){
      currentPlayer = 0;
      gameOver = false;
      int count = 0;
//        for (PlayerI player : players) {
//            board[0][count] = player;
//            count++;
//        }
    
    
    }

    @Override
    public void move(PlayerI player) {
       player = getPlayer();
       int moveCount = dice.rollDice();
       player.setPosition(moveCount);
    }

    @Override
    public PlayerI getPlayer() {
        return players.get(currentPlayer);
        
    }

    @Override
    public void endGame() {
        if(gameOver){
        
            // Terminate Application
            
        }
    }
    
    
    
    
}

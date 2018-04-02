/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.monoply_testing;

/**
 *
 * @author Opinator
 */
public interface GameI {
    
    
    void setup();
    void move(PlayerI player);
    PlayerI getPlayer();
    void endGame();
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.rmi.Remote;

/**
 *
 * @author gabriel
 */
public interface SiteInterface extends Remote{
    public boolean endOperCoach(int c);
    
    public void announceNewGame(int numGame, int nrTrial);
    
    public void declareGameWinner(int posPull);
    
    public void declareMatchWinner();
    
}
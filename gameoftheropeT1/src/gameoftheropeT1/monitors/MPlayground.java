/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftheropeT1.monitors;
import gameoftheropeT1.domain.Contestant;
import gameoftheropeT1.interfaces.*;
import gameoftheropeT1.main.Constant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriel
 */
public class MPlayground implements IRefereePlayground, ICoachPlayground, IContestantsPlayground{

    private int newTrial;
    private boolean newComand;
    private double strength;

    private Map<Integer, List<Integer>> coachAndTeam;
    
    private Map<Integer, List<Integer>> strengthTeam;

        
    private boolean startTrial; 
    private int numTrial; 
    private int nCoaches; // para  informReferee  os 2 treinadores tem de informar o arbtiro 
    //que as suas equipas estao prontas
    
    private int lastPlayer;
    private int pulls;
    private boolean ultimoPuxou;
    
    public MPlayground(MRepository rep){
        newTrial = 0; 
        newComand = false;
        numTrial = 0; 
        strength = 0;
        startTrial = false;
        
        coachAndTeam = new HashMap<Integer, List<Integer>>(); 
        
        strengthTeam = new HashMap<>(); 
        for(int i =1; i< 3; i++ ){
            strengthTeam.put(i, new ArrayList<Integer>()); 
        }
        
        ultimoPuxou = false;
        pulls = 0;
        
        nCoaches = 0;
    }
    
      ///////////       ////////////////////////////////
    /////////// REFEREE ///////////////////////////////
    
    


    @Override
    public synchronized void startTrial() {
        
        
        while(newTrial != 2){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(MPlayground.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        startTrial = true; 
        notifyAll(); 
        
    }

    @Override
    public synchronized char assertTrialDecision() { // isto nao e bem assim, temos que ver melhor
        
        char decision;
        
        
        
        if(strength <= 0){
            decision = Constant.GAME_END;
            return decision;
        }
        
        else{
            decision = Constant.GAME_CONTINUATION;
            return decision;
        }
            
        
    }

    ///////////       ////////////////////////////////
    /////////// COACH ///////////////////////////////
    @Override
    public synchronized void informReferee(int coachId) {
        
        System.out.println("Coach#" +coachId+ " informa arbitro.."); 
        newTrial++; 
        if (newTrial == 2)
            notifyAll();
            
    }

    
      ///////////           ////////////////////////////////
    /////////// CONTESTANTS ///////////////////////////////
    @Override
    public synchronized void getReady(int coachId, int contId) {
        
        
        while(startTrial == false){
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(MPlayground.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
    }

  
    @Override
    public synchronized void amDone(int coachId, int contId, int contestStrength) {
        
        System.out.println("["+coachId+"] #"+contId + " PUXA CRLHHOOO!"); 
        
        
        strengthTeam.get(coachId).add(contestStrength);

        System.out.println("Força Equipa: "+strengthTeam.toString()); 
        /*
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MPlayground.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        //flag
        pulls++;
        if(pulls == 6){          
            ultimoPuxou = true;
            notifyAll();
        }
    }


    
    private int generateStrength(){
        return 10 + (int)(Math.random() * ((20 - 10) + 1)); 
    }


}

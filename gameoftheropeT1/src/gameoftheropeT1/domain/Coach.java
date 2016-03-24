package gameoftheropeT1.domain;

import gameoftheropeT1.interfaces.*;
import gameoftheropeT1.state.ECoachesState;

/**
 *
 * @author roliveira
 */
public class Coach extends Thread{
    
    private ECoachesState state; 
    private final ICoachBench bench; 
    private final ICoachSite site; 
    private final ICoachPlayground playground;
    private final ICoachRepository repository;
    
    private final int idCoach;
    
    private boolean newComand; 
    private boolean callContestants;
    private boolean teamAssemble; 


    public Coach(int idCoach, ICoachBench bench, ICoachPlayground playground, ICoachSite site, 
            ICoachRepository repository ){
        this.setName("Coach "+idCoach);
        this.idCoach = idCoach; 
        this.bench = bench;
        this.site = site; 
        this.playground = playground;
        this.repository = repository;
        state = ECoachesState.WAIT_FOR_REFEREE_COMMAND; 
        newComand = false; 
        callContestants = false; 
        teamAssemble= false; 
    }
    
    @Override
    public void run(){
        do {

            switch(this.state){
                case WAIT_FOR_REFEREE_COMMAND:
                    
                    //if (!bench.endOfTheGame(idCoach)) break; 
                    
                    callContestants(idCoach);  /* the coach calls contestants to a trial */
                    state = ECoachesState.ASSEMBLE_TEAM;
                    repository.updateCoachState(idCoach, state);
                    
                    break; 

                case ASSEMBLE_TEAM:      
                    
                   informReferee(idCoach);    /* the coach informs the referee the team is ready */
                    state = ECoachesState.WATCH_TRIAL;
                    repository.updateCoachState(idCoach, state);

                    break; 
                    
                case WATCH_TRIAL:
                    reviewNotes(idCoach);     /* the coach reviews his notes */
                    state = ECoachesState.WAIT_FOR_REFEREE_COMMAND; 
                    repository.updateCoachState(idCoach, state);

                    break;

            }
        }while (bench.endOfTheGame(idCoach));
        
        System.out.println("Fim do treinador "+idCoach); 
    
    }
    
    private void callContestants(int idCoach){
        bench.callContestants(idCoach);
    }
    
    private void informReferee(int idCoach){
        playground.informReferee(idCoach);
    }
    
    private void reviewNotes(int idCoach){
        bench.reviewNotes(idCoach);
    }
    
    public void setState(ECoachesState state){
        this.state = state;
    }
    
    public boolean endOperCoach(int idCoach){
        return site.endOperCoach(idCoach); 
    }
    
    public ECoachesState getCurrentState() {
        return state;
    }
    
    public int getIdCoach(){ // id do treinador respectivo
        return idCoach;
    }
    
}
package ServerSide.Site;

import Communication.ClientComm;
import Communication.CommConst;
import Communication.Message.Message;
import Communication.Message.MessageType;
import static java.lang.Thread.sleep;
import java.util.Arrays;

/**
 * @author Gabriel Vieira (68021) gabriel.vieira@ua.pt
 * @author Rui Oliveira (68779) ruipedrooliveira@ua.pt
 * @version 2.0
 */
public class MSite implements IRefereeSite, ICoachSite{
    private int winnerTeamA;
    private int winnerTeamB; 
    private int nrTrial, numGame; 
    private final boolean endOp; 
    
    public MSite(){
        endOp = false; 
    }
    
    /**
     * The referee starts a game. The game number should be updated. Both game header and internal state should be saved.
     * @param numGame
     * @param nrTrial 
     */
    @Override
    public synchronized void announceNewGame(int numGame, int nrTrial) {
        this.nrTrial = nrTrial; 
        this.numGame = numGame; 
    }
    
    /**
     * The referee announces which teams has won the match. Both internal state and match result should be saved.
     * @param posPull 
     */
    @Override
    public synchronized void declareGameWinner(int posPull) {

        if (posPull < 0 ){
            System.out.println("Posicao da Corda: "+posPull+" | Game #"+numGame+" | Vence equipa A!"); 
            isEnd(numGame, "A");
            winnerTeamA++; 
        }
        else if (posPull > 0){
            System.out.println("Posicao da Corda: "+posPull+" | Game #"+numGame+" | Vence equipa B!"); 
            isEnd(numGame, "B");
            winnerTeamB++; 
        }
        else{
            System.out.println("Posicao da Corda: "+posPull+" | Game #"+numGame+" | Empatado!"); 
            wasADraw(numGame);
        }
    }
    
    /**
     * The referee announces which teams has won the match. Both internal state and match result should be saved.
     */
    @Override
    public synchronized void declareMatchWinner() {

        System.out.println("***********************************************************");
        if (winnerTeamA > winnerTeamB){
            System.out.println("A Equipa A venceu o match com #" +winnerTeamA +" vitórias!");
            endMatch("A", winnerTeamA, winnerTeamB);
        }
        else if (winnerTeamA < winnerTeamB){
            System.out.println("A Equipa B venceu o match com #" +winnerTeamB +" vitórias!");
            endMatch("B", winnerTeamB, winnerTeamA);
        }
        else{
            System.out.println("O match ficou empatado! :(  A#" +winnerTeamA +" - B#"+winnerTeamB);
            endMatch("", winnerTeamB, winnerTeamA);
        }
        System.out.println("***********************************************************");

    }
    /**
     * checks coach operation ended
     * @param id
     * @return 
     */
    @Override
    public boolean endOperCoach(int id) {
        return endOp; 
    }
    
    public int getNrTrial(){
        return nrTrial; 
    }

    private void isEnd(int numGame, String team) {
        ClientComm con = new ClientComm(CommConst.repServerName, CommConst.repServerPort);
        Message inMessage, outMessage;

        while (!con.open())
        {
            try {
                sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(MessageType.IS_END, team, numGame);
        con.writeObject(outMessage);
        
        inMessage = (Message) con.readObject();
        
        MessageType type = inMessage.getType();
        if (type != MessageType.ACK) {
            System.out.println("Message:"+ inMessage.toString());
            System.out.println(Arrays.toString(Thread.currentThread().getStackTrace()));
            System.exit(1);
        }
        
        con.close();
    }

    private void wasADraw(int numGame) {
        ClientComm con = new ClientComm(CommConst.repServerName, CommConst.repServerPort);
        Message inMessage, outMessage;

        while (!con.open())
        {
            try {
                sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(MessageType.WAS_A_DRAW, numGame);
        con.writeObject(outMessage);
        
        inMessage = (Message) con.readObject();
        
        MessageType type = inMessage.getType();
        if (type != MessageType.ACK) {
            System.out.println("Message:"+ inMessage.toString());
            System.out.println(Arrays.toString(Thread.currentThread().getStackTrace()));
            System.exit(1);
        }
        
        con.close();
    }

    private void endMatch(String team, int winnerTeamA, int winnerTeamB) {
        ClientComm con = new ClientComm(CommConst.repServerName, CommConst.repServerPort);
        Message inMessage, outMessage;

        while (!con.open())
        {
            try {
                sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(MessageType.END_MATCH, team, winnerTeamA, winnerTeamB);
        con.writeObject(outMessage);
        
        inMessage = (Message) con.readObject();
        
        MessageType type = inMessage.getType();
        if (type != MessageType.ACK) {
            System.out.println("Message:"+ inMessage.toString());
            System.out.println(Arrays.toString(Thread.currentThread().getStackTrace()));
            System.exit(1);
        }
        
        con.close();
    }
    
}
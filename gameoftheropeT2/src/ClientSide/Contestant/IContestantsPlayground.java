
package ClientSide.Contestant;

/**
 * @author Gabriel Vieira (68021) gabriel.vieira@ua.pt
 * @author Rui Oliveira (68779) ruipedrooliveira@ua.pt
 * @version 2.0
 */
public interface IContestantsPlayground {
    
    public void getReady(int coachId, int contId);

    public void amDone(int coachId, int contId, int contestStrength);
}

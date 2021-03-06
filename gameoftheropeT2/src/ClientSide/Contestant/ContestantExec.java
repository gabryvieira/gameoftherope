package ClientSide.Contestant;

import Communication.ConstConfigs;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Gabriel Vieira (68021) gabriel.vieira@ua.pt
 * @author Rui Oliveira (68779) ruipedrooliveira@ua.pt
 * @version 2.0
 */
public class ContestantExec { 
    public static void main(String [] args) throws IOException{
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        System.out.println("******************************************************************\nEntity contestant has started!");
        System.out.println("******************************************************************");
        
        ArrayList<Contestant> contestant = new ArrayList<>(ConstConfigs.ELEMENTS_IN_TEAM);
        
        for (int idc = 1; idc <= ConstConfigs.OPPOSING_TEAMS ; idc++){
            for (int idct = 1; idct <= ConstConfigs.ELEMENTS_IN_TEAM; idct++){
                contestant.add(new Contestant(idct, idc));
            }
        }
        
        System.out.println("Number of contestant: " + contestant.size());
        
        for (Contestant c : contestant)
            c.start();
        
        for (Contestant c : contestant) { 
            try { 
                c.join ();
            }catch (InterruptedException e) {}
        }
        
    }
    

}

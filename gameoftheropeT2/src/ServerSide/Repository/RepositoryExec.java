/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerSide.Repository;
import Communication.*;
import Communication.Proxy.*;
import java.io.FileNotFoundException;
import java.net.SocketTimeoutException;
/**
 * This file defines the main method to run the Logging server
 * 
 * @author roliveira
 * @author gabriel
 */
public class RepositoryExec {
    public static void main (String [] args) throws SocketTimeoutException, FileNotFoundException{

        System.out.print("\033[H\033[2J");
        System.out.flush();
        ServerComm scon, sconi;                             // canais de comunicação
        ClientProxy cliProxy;                               // thread agente prestador do serviço
        
        // estabelecimento do servico 
        scon = new ServerComm(CommConst.repServerPort);    // criação do canal de escuta e sua associação
        scon.start();                                       // com o endereço público
        MRepository repository = new MRepository(ConstConfigs.NAME_FILE, ConstConfigs.OPPOSING_TEAMS, ConstConfigs.ELEMENTS_IN_TEAM);
        RepositoryInterface repInt = new RepositoryInterface(repository);
        
        System.out.println("******************************************************************\nRepository service has started!");
        System.out.println("Server is listening.\n******************************************************************");

        // processamento de pedidos 
        while (true) {
            sconi = scon.accept();                         // entrada em processo de escuta
            cliProxy = new ClientProxy(scon, sconi, repInt);     // lançamento do agente prestador do serviço
            cliProxy.start();
        }
        

    }
}

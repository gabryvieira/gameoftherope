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
public interface PlaygroundInterface extends Remote, ICoachPlayground, IContestantsPlayground, IRefereePlayground{

}

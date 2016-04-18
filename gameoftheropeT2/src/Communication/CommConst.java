/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

/**
 * This file stores the communication constants so that it is easier to change the values, if needed.
 *
 */
public class CommConst {
    // ssh sd0405@l040101-wsXX.ua.pt
    // sistema2015
    
    /**
     * Variable that holds the address for the logging server.
     */
    //public final static String loggServerName = "127.0.0.1";
    public final static String loggServerName = "l040101-ws01.ua.pt";
    
    /**
     * Variable that holds the port number for the logging server.
     */
    public final static int loggServerPort = 22440;
    
    /**
     * Variable that holds the address for the shop server.
     */
    //public final static String shopServerName = "127.0.0.1";
    public final static String shopServerName = "l040101-ws05.ua.pt";
    
    /**
     * Variable that holds the port number for the shop server.
     */
    public final static int shopServerPort = 22441;
        
    /**
     * Variable that holds the address for the workshop server.
     */
    //public final static String wsServerName = "127.0.0.1";
    public final static String wsServerName = "l040101-ws04.ua.pt";
    
    /**
     * Variable that holds the port number for the workshop server.
     */
    public final static int wsServerPort = 22442;
    
    /**
     * Variable that holds the address for the warehouse server.
     */
    //public final static String whServerName = "127.0.0.1";
    public final static String whServerName = "l040101-ws03.ua.pt";
    
    /**
     * Variable that holds the port number for the warehouse server.
     */
    public final static int whServerPort = 22443;
    
    /**
     * Variable that holds the timeout value for the server sockets.
     */
    public final static int socketTimeout = 500;

    // client l040101-ws09.ua.pt
    
}

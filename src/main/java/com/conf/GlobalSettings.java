package com.conf;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GlobalSettings {
    public static SystemInfo info = new SystemInfo();
    public static ExecutorService exec;

    public static Socket socket;
    public static Socket socket2;

    static {
        exec = Executors.newFixedThreadPool(info.getNumberOfCore());
        try {
<<<<<<< HEAD
            socket = new Socket("192.168.1.101", 7000);
=======
            socket = new Socket("192.168.0.101", 7000);
>>>>>>> 9d744fc2b272191501b24c3c073a60a1e8c7a66f
        }
        catch(UnknownHostException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}

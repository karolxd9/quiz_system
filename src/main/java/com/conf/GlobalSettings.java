package com.conf;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GlobalSettings {
    public static SystemInfo info = new SystemInfo();
    public static ExecutorService exec;

    static {
        exec = Executors.newFixedThreadPool(info.getNumberOfCore());
    }
}

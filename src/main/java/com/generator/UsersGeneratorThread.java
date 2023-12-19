package com.generator;

import com.conf.SystemInfo;

import java.util.concurrent.Callable;

public class UsersGeneratorThread extends DataGenerator implements Callable<String> {


    public UsersGeneratorThread(int forUsers, SystemInfo info) {
        super(forUsers, info);
    }

    @Override
    public String call() throws Exception {
        int n = this.getRecordPerThread();
        String result = "";
        for(int i=0; i < n; i++){
            result += this.generateUser();

        }
        return result;
    }
}

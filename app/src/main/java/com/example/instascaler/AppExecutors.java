package com.example.instascaler;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.MainThread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    private final Executor diskIO;
    private final Executor networkIo;
    private final Executor mainThread;

    private static AppExecutors sAppExecutors;
    private static final Object LOCK = new Object();


    private AppExecutors(Executor diskIO,Executor networkIo,Executor mainThread){
        this.diskIO = diskIO;
        this.networkIo = networkIo;
        this.mainThread = mainThread;
    }

    public static AppExecutors getInstance(){
        if (sAppExecutors == null){
            synchronized (LOCK){
                sAppExecutors = new AppExecutors(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(3),new MainThreadExecutor());
            }
        }
        return sAppExecutors;
    }

    public Executor getDiskIO() {
        return diskIO;
    }

    public Executor getNetworkIo() {
        return networkIo;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    public static class MainThreadExecutor implements Executor {

        final Handler mHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mHandler.post(command);
        }
    }
}

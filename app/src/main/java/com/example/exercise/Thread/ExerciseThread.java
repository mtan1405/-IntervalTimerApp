package com.example.exercise.Thread;


import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.loader.content.Loader;

import com.example.exercise.Exercise.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseThread extends java.lang.Thread {
    Handler handler  ;
    BlockThread blockThread;
    List<Exercise> list ;
    Callback callback ;
    public Exercise currentExercise ;



    ExerciseThread (@NonNull BlockThread blockThread , List<Exercise> exercises, @NonNull Callback callback , Handler handler  ){
        this.handler = handler ;
        this.blockThread = blockThread ;
        this.list = exercises ;
        this.callback = callback;
    }

    @Override
    public void run() {
        try {
            run2();
        } catch (Exception e) {
            Log.i("log" , e.getMessage());
        }
    }

    private  void run2 () throws Exception {
        synchronized (blockThread) {
            for (Exercise e : list) {
                for (int i = 0; i <= e.getRestTime(); i++) {
                    if (blockThread.block ){
                        blockThread.wait();
                    }
                    if (blockThread.breakThread){
                        break;
                    }
                    Thread.sleep(1000);
                    int finalI = i;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                        // set UI rest time
                            int s = finalI;
                            callback.exerciseTimeUI(e, finalI);
                        }
                    }) ;

                }
                callback.notifyWithMedia();

                if (blockThread.breakThread){
                    break;
                }
                for (int i = 0; i <= e.getExerciseTime(); i++) {
                    if (blockThread.block ){
                        blockThread.wait();
                    }
                    if (blockThread.breakThread){
                        break;
                    }
                    Thread.sleep(1000);
                    int finalI = i ;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.restTimeUI( e, finalI);
                        }
                    }) ;

                }
                callback.notifyWithMedia();
                if (blockThread.breakThread){
                    break;
                }

            }
        }
    }
     static class  BlockThread {
        public boolean block = false;
        public boolean breakThread = false ;
    }
    public interface Callback {
        void restTimeUI ( Exercise exercise , int i  );
        void exerciseTimeUI( Exercise exercise ,int i );
        void notifyWithMedia () ;
    }
}

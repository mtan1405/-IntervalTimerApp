package com.example.exercise.Thread;

import androidx.annotation.NonNull;

import com.example.exercise.Exercise.Exercise;

import java.util.ArrayList;
import java.util.List;
import android.os.Handler;


public class ThreadManage {
    private ThreadManage () {}
    private ExerciseThread  thread ;
    private Thread waitThread ;
    private static ThreadManage threadManage ;
    private ExerciseThread.BlockThread blockThread ;
    private ExerciseThread.Callback callback;
    private  List<Exercise> exercises;
    private  Handler handler  ;
    public static ThreadManage getInstance( @NonNull ExerciseThread.Callback callback){
     if (threadManage == null){
         threadManage = new ThreadManage();
         threadManage.callback = callback;
         threadManage.exercises = new ArrayList<>(10) ;
         threadManage.blockThread = new ExerciseThread.BlockThread();
         threadManage.handler = new Handler() ;
     }

     return threadManage ;
    }
    private void createThread (){

        thread = new ExerciseThread(blockThread , exercises ,callback  ,handler) ;
    }

    public void start () {
        if (waitThread != null && waitThread.isAlive()){
            return;
        }else {
            waitThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    breakThread();
                    synchronized (blockThread) {
                        resumeThread2();
                        createThread();
                        thread.start();
                    }
                }
            }) ;
            waitThread.start();
        }


    }
    private   void breakThread (){
        blockThread.breakThread = true ;
        blockThread.block = false;
    }
    public  void resumeThread () {
        if (thread!= null && thread.getState() != Thread.State.WAITING) return;

        synchronized (blockThread) {
            blockThread.block = false ;
            blockThread.breakThread = false ;
            blockThread.notify() ;
        }
    }
    private void resumeThread2(){
        blockThread.block = false ;
        blockThread.breakThread = false ;
    }

    public  void pauseThread () {
        blockThread.block = true;
        blockThread.breakThread = false;
    }
    public void addExercise (String exerciseName , int restTime , int exerciseTime ){
        exercises.add(new Exercise(exerciseName , restTime , exerciseTime)) ;
    }
    public int getIndex (Exercise exercise ){
        return  exercises.indexOf(exercise);
    }


}

package com.example.exercise.Exercise;

public class Exercise {
    private String exerciseName ;
    private int restTime  = 0 ;
    private int exerciseTime = 0 ;
    public Exercise (String  exerciseName , int  restTime , int  exerciseTime ){
        setExerciseName(exerciseName);
        setExerciseTime(exerciseTime);
        setRestTime(restTime);

    }

    public void setExerciseName(String exerciseName){
        this.exerciseName = exerciseName ;
    }
    public String getExerciseName (){
        return exerciseName ;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
       this.restTime = restTime>0 ? restTime : Math.abs(restTime) ;
    }

    public int getExerciseTime() {
        return exerciseTime;
    }
    public void setExerciseTime(int exerciseTime) {
        this.exerciseTime = exerciseTime>0 ? exerciseTime : Math.abs(exerciseTime) ;
    }
}

package com.example.exercise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.example.exercise.Exercise.Exercise;
import com.example.exercise.Thread.ExerciseThread;
import com.example.exercise.Thread.ThreadManage;


public class MainActivity extends AppCompatActivity {

    TextView textView ;
    TextView textView2 ;
    ThreadManage exerciseManger ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView () ;
        createListExercise();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }
    void createListExercise (){
        exerciseManger = ThreadManage.getInstance(new ExerciseThread.Callback() {
            @Override
            public void restTimeUI(Exercise exercise, int i) {

                 textView2.setText( String.valueOf(i) + "s");
                 textView.setText("Bài tập thứ " +String.valueOf( exerciseManger.getIndex(exercise) +1 ) + "\n Trạng thái nghỉ ");


            }

            @Override
            public void exerciseTimeUI(Exercise exercise, int i) {
                textView2.setText( String.valueOf(i) + "s");
                textView.setText("Bài tập thứ " +String.valueOf( exerciseManger.getIndex(exercise) +1 )   + "\n Trạng thái tập ");

            }

            @Override
            public void notifyWithMedia() {
                start();
            }
        }) ;
        exerciseManger.addExercise("Nhay day " , 30 , 30);
        exerciseManger.addExercise("Nhay day " , 30 , 30 );
        exerciseManger.addExercise("Nhay day " , 30 , 30 );
        exerciseManger.addExercise("Nhay day " , 30 , 30);
        exerciseManger.addExercise("Nhay day " , 30 , 30 );
        exerciseManger.addExercise("Nhay day " , 30 , 30 );
        exerciseManger.addExercise("Nhay day " , 30 , 30);
        exerciseManger.addExercise("Nhay day " , 30 , 30 );
        exerciseManger.addExercise("Nhay day " , 30 , 30 );
        exerciseManger.addExercise("Nhay day " , 30 , 30 );

    }
    void setUpView (){
        textView = findViewById(R.id.txt1);
        textView2 = findViewById(R.id.txt2);
        findViewById(R.id.pauseButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseManger.pauseThread();
            }
        });
        findViewById(R.id.resumeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseManger.resumeThread();
            }
        });
        findViewById(R.id.startButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerciseManger.start();
            }
        });
    }
    private  void start () {
        Intent intent = new Intent() ;
        intent.setAction(MediaService.START) ;
        sendBroadcast(intent);
    }

    @Override
    protected void onStart() {
        Intent intent = new Intent (this , MediaService.class) ;
        startService(intent );
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
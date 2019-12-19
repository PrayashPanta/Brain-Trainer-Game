package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int locationOfCorrectAnswer;
    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView resultTextView;
    int points = 0;
    int totalQuestion = 0;
    TextView ScoreTextView;
    TextView sumTextView;
    TextView TimerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;


    public void chooseAnswer(View view){
        resultTextView.setVisibility(View.VISIBLE);
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            resultTextView.setText("CORRECT!");
            points++;

        }
        else    {
            resultTextView.setText("WRONG!");
        }

        totalQuestion++;
        ScoreTextView.setText(Integer.toString(points) + "/" + Integer.toString(totalQuestion));
        playGame();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         sumTextView = findViewById(R.id.SumTextView);
        goButton = findViewById(R.id.StartButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        ScoreTextView = findViewById(R.id.ScoreTextView);
        TimerTextView = findViewById(R.id.TimerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

        resultTextView.setVisibility(View.INVISIBLE);




    }

    public void start (View view){
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.TimerTextView));
        gameLayout.setVisibility(View.VISIBLE);


    }

    public void playGame(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        answers.clear();

        locationOfCorrectAnswer = rand.nextInt(4);
        for (int i =0; i<4;i++){
            if (i == locationOfCorrectAnswer){
                answers.add(a+b);
            }
            else{
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void playAgain(View view ){
        points = 0;
        totalQuestion = 0;
        TimerTextView.setText("30s");
        ScoreTextView.setText(Integer.toString(points) + "/" + Integer.toString(totalQuestion));
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
        playGame();

        CountDownTimer countDownTimer = new CountDownTimer(31000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                TimerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("TIME UP!");
                playAgainButton.setVisibility(View.VISIBLE);

            }
        }.start();




    }
}

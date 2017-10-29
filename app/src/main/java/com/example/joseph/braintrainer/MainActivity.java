package com.example.joseph.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    int a;
    int b;
    int loacationOfCorrectAnswer;
    int incorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    TextView sumTextView;
    TextView gameOver;
    TextView resultTextView;
    TextView scoreTextView;
    TextView timerTextView;
    Button playAgain;
    GridLayout gridLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    public void playAgain(final View view){

      gameOver.setVisibility(View.INVISIBLE);
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText("0/0");
        resultTextView.setText("");
        playAgain.setVisibility(View.INVISIBLE);
      gridLayout.setVisibility(View.VISIBLE);
        questions();
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(Long.toString(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                playAgain.setVisibility(View.VISIBLE);
                timerTextView.setText("0");
                resultTextView.setText("Your Score:"+Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
                gridLayout.setVisibility(View.INVISIBLE);
               gameOver.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void questions(){
        Random random = new Random();
        a = random.nextInt(21);
        b = random.nextInt(21);
        loacationOfCorrectAnswer = random.nextInt(4);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        answers.clear();

        for (int i = 0;i < 4;i++){
            if (i == loacationOfCorrectAnswer){
                answers.add(a+b);
            }else {
                incorrectAnswer = random.nextInt(41);
                while (incorrectAnswer == a+b){
                    incorrectAnswer = random.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(loacationOfCorrectAnswer))) {
                    score++;
                    resultTextView.setText("CORRECT");
        }else {
                    resultTextView.setText("WRONG");
        }numberOfQuestions++;
         scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        questions();
    }
    public void start(View view){
        playAgain(view);
        startButton.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button)findViewById(R.id.startButton);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        gameOver = (TextView)findViewById(R.id.gameOver);
        playAgain = (Button)findViewById(R.id.playAgainButton);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        gridLayout = (GridLayout)findViewById(R.id.gridLayout);





    }
}

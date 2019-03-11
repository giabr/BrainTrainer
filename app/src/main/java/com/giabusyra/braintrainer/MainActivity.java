package com.giabusyra.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Global Variable
    Button startButton;
    TextView quiz;
    TextView isCorrect;
    TextView score;
    TextView timer;
    TextView finalScore;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int correctAnswer;
    int scoreNow = 0;
    int numOfQuiz = 0;
    Button playAgain;
    RelativeLayout gameLayout;

    //ButtonAnswer
    Button button0;
    Button button1;
    Button button2;
    Button button3;

    //Counter
    Boolean counter=false;

    //Play Again Function
    public void playAgain(View view){
        //Reset variabel
        scoreNow = 0;
        numOfQuiz = 0;
        counter = false;

        timer.setText("30s");
        score.setText("0/0");
        isCorrect.setText("");
        finalScore.setText("");

        //Countdown timer
        new CountDownTimer(31000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                isCorrect.setText("Done");
                finalScore.setVisibility(View.VISIBLE);
                finalScore.setText("Your score is " + scoreNow + "/" + numOfQuiz);
                playAgain.setVisibility(View.VISIBLE);
                counter = true;
            }
        }.start();

        generateQuiz();
        playAgain.setVisibility(View.INVISIBLE);


    }

    public void generateQuiz() {
        if (counter == false) {
            //Random number for quiz
            Random random = new Random();
            int a = random.nextInt(100);
            int b = random.nextInt(100);
            quiz.setText(Integer.toString(a) + "+" + Integer.toString(b));

            //Generate answer
            correctAnswer = random.nextInt(4);
            answer.clear();
            int incorrectAnswer;

            for (int i = 0; i < 4; i++) {
                if (i == correctAnswer) {
                    answer.add(a + b);
                } else {
                    incorrectAnswer = random.nextInt(200);
                    while (incorrectAnswer == a + b) {
                        incorrectAnswer = random.nextInt(200);
                    }
                    answer.add(incorrectAnswer);
                }
            }

            button0.setText(Integer.toString(answer.get(0)));
            button1.setText(Integer.toString(answer.get(1)));
            button2.setText(Integer.toString(answer.get(2)));
            button3.setText(Integer.toString(answer.get(3)));
        }
    }

    public void answer(View view){
        if(counter==false){
        Log.i("Answer",(String) view.getTag());
        if (view.getTag().toString().equals(Integer.toString(correctAnswer))){
            Log.i("Answer","Correct");
            scoreNow ++;
            isCorrect.setText("Correct");
        } else {
            Log.i("Anser","Wrong");
            isCorrect.setText("Wrong");
        }

        //Generate new quiz after choose
        generateQuiz();

        //Score board
        numOfQuiz++;
        score.setText(scoreNow+"/"+numOfQuiz);
        }
    }

    //Start and Finish Button
    public void start(View view) {
            startButton.setVisibility(View.INVISIBLE);
            gameLayout.setVisibility(RelativeLayout.VISIBLE);

            //Play Again Call
            playAgain(playAgain);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize with id in layout
        startButton = findViewById(R.id.go);
        quiz = findViewById(R.id.quiz);
        isCorrect = findViewById(R.id.isCorrect);
        score = findViewById(R.id.score);
        timer = findViewById(R.id.timer);
        finalScore = findViewById(R.id.finalScore);
        playAgain = findViewById(R.id.playAgain);
        button0 = findViewById(R.id.button1);
        button1 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button3);
        button3 = findViewById(R.id.button4);
        gameLayout = findViewById(R.id.relativeLayout);

    }
}

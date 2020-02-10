package com.valaithalam.yosi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collections;

public class SpeedAndDistance extends AppCompatActivity {


    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";


    public static String EXTRA_SCORE = "extraScore";


    private TextView question,countdown,score;
    private RadioGroup grp_option;
    private RadioButton op1,op2,op3,op4;
    private Button nxt;

    private ArrayList<Question> questionList;

    private ColorStateList txt_color;

    private int question_counter , question_total;
    private Question currentQuestion;

    private int score_ans;
    private boolean answered;

    private long backPressedTime;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speedanddistance);


        question = findViewById(R.id.question);
        countdown = findViewById(R.id.counttime);
        score = findViewById(R.id.score);
        grp_option = findViewById(R.id.grp_option);
        op1 = findViewById(R.id.option1);
        op2 = findViewById(R.id.option2);
        op3 = findViewById(R.id.option3);
        op4 = findViewById(R.id.option4);
        nxt = findViewById(R.id.bn_nxt);

        txt_color = op1.getTextColors();


        if (savedInstanceState == null) {

            QuizDbHelper dbHelper = new QuizDbHelper(this);
            questionList = dbHelper.getAllQuestions();
            question_total = questionList.size();
            Collections.shuffle(questionList);
            showNextQuestion();
        }else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            question_total = questionList.size();
            question_counter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(question_counter - 1);
            score_ans = savedInstanceState.getInt(KEY_SCORE);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);
            showSolution();
        }

        nxt.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered){
                    if (op1.isChecked() || op2.isChecked() || op3.isChecked() || op4.isChecked()){
                        checkAnswer();
                    }else{
                        Toast.makeText(SpeedAndDistance.this, "Please select an answer", Toast.LENGTH_SHORT).show();

                    }
                }else {
                    showNextQuestion();
                }
            }
        });

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
    }

    private void showNextQuestion(){
        op1.setTextColor(txt_color);
        op2.setTextColor(txt_color);
        op3.setTextColor(txt_color);
        op4.setTextColor(txt_color);
        grp_option.clearCheck();

        if (question_counter < question_total){
            currentQuestion = questionList.get(question_counter);

            question.setText(currentQuestion.getQuestion());
            op1.setText(currentQuestion.getOption1());
            op2.setText(currentQuestion.getOption2());
            op3.setText(currentQuestion.getOption3());
            op4.setText(currentQuestion.getOption4());

            question_counter++;

            answered = false;
            nxt.setText("confirm");
        }else {
            finishQuiz();
        }
    }

    private void checkAnswer(){
        answered = true;

        RadioButton rbSelected = findViewById(grp_option.getCheckedRadioButtonId());
        int answerNr = grp_option.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()){
            score_ans++;
            score.setText("Score: " + score_ans);
        }

        showSolution();

    }

    private void showSolution() {
        op1.setTextColor(Color.RED);
        op2.setTextColor(Color.RED);
        op3.setTextColor(Color.RED);
        op4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()){

            case 1:
                op1.setTextColor(Color.GREEN);
                question.setText("Answer 1 is correct");
                break;

            case 2:
                op2.setTextColor(Color.GREEN);
                question.setText("Answer 2 is correct");
                break;


            case 3:
                op3.setTextColor(Color.GREEN);
                question.setText("Answer 3 is correct");
                break;

            case 4:
                op4.setTextColor(Color.GREEN);
                question.setText("Answer 4 is correct");
                break;
        }
        if (question_counter < question_total){
            nxt.setText("Next");
        }
        else {
            nxt.setText("Finish");
        }

    }

    private void finishQuiz(){

        Intent rI = new Intent();
        rI.putExtra(EXTRA_SCORE, score_ans);
        setResult(RESULT_OK, rI);
        finish();



    }


    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            finishQuiz();
        }else {
            Toast.makeText(this,"Press back again to finish",Toast.LENGTH_SHORT).show();

        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putInt(KEY_SCORE, score_ans);
        outState.putInt(KEY_QUESTION_COUNT,question_counter);
        outState.putBoolean(KEY_ANSWERED,answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST,questionList);

}
}

package com.valaithalam.yosi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ComoundDbHelper extends SQLiteOpenHelper {



    private static final String DATABASE_NAME = "Ci_Aptitude_quest.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public ComoundDbHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String CITABLE = "CREATE TABLE " +
                QuizContract.QuestionTables.CI_TABLE_NAME + " ( " +
                QuizContract.QuestionTables._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionTables.CI_COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionTables.CI_COLUMN_OPTION1 + " TEXT, "+
                QuizContract.QuestionTables.CI_COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionTables.CI_COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionTables.CI_COLUMN_OPTION4 + " TEXT, " +
                QuizContract.QuestionTables.CI_COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(CITABLE);
        fillQuestionTable();

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionTables.CI_TABLE_NAME );
        onCreate(db);

    }

    private void fillQuestionTable() {
        //Speed and distance questions
        Question q1 = new Question(" What is the amount for a sum of money Rs.7500 at 6% rate of interest C.I. for 2 years?", "A)Rs .8427", "B)Rs .8417", "C)Rs .8400", "D)Rs .8390", 1);
        addQuestions(q1);
        Question q2 = new Question("What is the compound interest on Rs. 2500 for 2 years at rate of interest 4% per annum?", "A) Rs. 180", "B) Rs. 204", "C) Rs. 210", "D) Rs. 220", 2);
        addQuestions(q2);
        Question q3 = new Question("Ramesh borrowed Rs. 3600 at a certain rate of interest C.I. and the sum grows to Rs. 4624 in 2 years. What is the rate of interest?", "A)12.3%", "B) 13.3%", "C)14.3%", "D)15.3%", 2);
        addQuestions(q3);
        Question q4 = new Question("On a certain sum of money the compound interest Rs. 318 is earned in 2 years. If the rate of interest is 12%, what is the principal amount?", "A)Rs. 1250", "B)Rs.1300", "C) Rs. 1200", "D)Rs. 1150", 1);
        addQuestions(q4);
        Question q5 = new Question("The simple interest on a certain sum of money at rate of interest 5% per annum for 2 years is Rs. 500. What is the compound interest on the same sum for the same period and at the same rate of interest?", "A)Rs .412.5", "B)Rs .400", "C)Rs .500", "D)Rs .512.5", 4);
        addQuestions(q5);
        Question q6 = new Question("In how many years the compound interest on Rs. 10000 at the rate of 10% will be Rs. 2100 ?",  "A) 1.5 years", "B) 1.5 years", "C)2.5 years", "D)3 years", 2);
        addQuestions(q6);
        Question q7 = new Question(" The difference between Simple Interest and Compound Interest on a certain sum of money for 2 years is Rs.200. If the rate of interest is 12 Apti Compound interest43%, what is the sum?", "A)Rs. 11800", "B)Rs. 12000", "C)Rs. 12800", "D)Rs. 13000", 3);
        addQuestions(q7);
        Question q8 = new Question(" The difference between simple interest and compound interest on a sum of money for 1 year at 4% per 6 months is 4, what is the sum?", "A)Rs. 2100", "B)Rs. 2300", "C)Rs. 2500", "D)Rs. 3000", 3);
        addQuestions(q8);
        Question q9 = new Question(" If the difference between Simple Interest and Compound Interest on a sum of money for 3 years is Rs. 200 and the rate of interest is 10%, what is the sum?", "A) Rs. 6451.6", "B)Rs. 6351.6", "C) Rs. 6251.6", "D)Rs. 6151.6", 1);
        addQuestions(q9);
        Question q10 = new Question(" On lending a certain sum of money on C.I. one gets Rs.9050 in 2 years and Rs.9500 in 3 years. What is the rate of interest?", "A)5%", "B)4.5%", "C) 5.5 %", "D)6%", 1);
        addQuestions(q10);

    }

    private void addQuestions(Question question){

        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionTables.CI_COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionTables.CI_COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionTables.CI_COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionTables.CI_COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionTables.CI_COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract.QuestionTables.CI_COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract.QuestionTables.CI_TABLE_NAME, null, cv);
    }


    public ArrayList<Question> getAllQuestions(){

        ArrayList<Question> ciquestionList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionTables.CI_TABLE_NAME, null);

        if (c.moveToFirst()){
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionTables.CI_COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionTables.CI_COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionTables.CI_COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionTables.CI_COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionTables.CI_COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionTables.CI_COLUMN_ANSWER_NR)));
                ciquestionList.add(question);
            }while (c.moveToNext());
        }
        c.close();
        return ciquestionList;

    }
}

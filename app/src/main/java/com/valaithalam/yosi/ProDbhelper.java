package com.valaithalam.yosi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProDbhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Pro_Aptitude_quest.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public ProDbhelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String PRO_TABLE = "CREATE TABLE " +
                QuizContract.QuestionTables.PRO_TABLE_NAME + " ( " +
                QuizContract.QuestionTables._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionTables.PRO_COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionTables.PRO_COLUMN_OPTION1 + " TEXT, "+
                QuizContract.QuestionTables.PRO_COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionTables.PRO_COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionTables.PRO_COLUMN_OPTION4 + " TEXT, " +
                QuizContract.QuestionTables.PRO_COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(PRO_TABLE);
        fillQuestionTable();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionTables.PRO_TABLE_NAME );
        onCreate(db);

    }

    private void fillQuestionTable() {

        //Age questions
        Question Aq1 = new Question("What is the probability of getting an even number when a dice is rolled? ", "A) 1/5", "B) 1/2", "C) 1/3", "D) 1/4", 2);
        addQuestions(Aq1);
        Question Aq2 = new Question("What is the probability of getting two tails when two coins are tossed?", "A) 1/3", "B) 1/6", "C) 1/2","D) 1/4", 4);
        addQuestions(Aq2);
        Question Aq3 = new Question("The tickets numbered from 1 to 20 are mixed up and then a ticket is drawn at random. What is the probability that the ticket has a number which is a multiple of 3 or 5?", "A) 9/20", "B) 9/24", "C) 9/27 ","D) 9/30 ", 1);
        addQuestions(Aq3);
        Question Aq4 = new Question("A box contains 2 red, 3 green, and 2 blue balls. What is the probability that none of the balls drawn is blue? ", "A) 10/25", "B) 10/21", "C) 10/31","D) 10/24", 2);
        addQuestions(Aq4);
        Question Aq5 = new Question(" In a bag, there are 8 red, 7 yellow and 6 green balls. If one ball is picked up at random, what is the probability that it is neither red nor green?", "A) 1/4", "B) 1/2","C) 1/5","D)  1/3",4);
        addQuestions(Aq5);
    }

    private void addQuestions(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionTables.PRO_COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionTables.PRO_COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionTables.PRO_COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionTables.PRO_COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionTables.PRO_COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract.QuestionTables.PRO_COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract.QuestionTables.PRO_TABLE_NAME, null, cv);
    }
    public ArrayList<Question> getAllQuestions(){
        ArrayList<Question> ProquestionList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor ac = db.rawQuery("SELECT * FROM " + QuizContract.QuestionTables.PRO_TABLE_NAME, null);

        if (ac.moveToFirst()){
            do {
                Question question = new Question();
                question.setQuestion(ac.getString(ac.getColumnIndex(QuizContract.QuestionTables.PRO_COLUMN_QUESTION)));
                question.setOption1(ac.getString(ac.getColumnIndex(QuizContract.QuestionTables.PRO_COLUMN_OPTION1)));
                question.setOption2(ac.getString(ac.getColumnIndex(QuizContract.QuestionTables.PRO_COLUMN_OPTION2)));
                question.setOption3(ac.getString(ac.getColumnIndex(QuizContract.QuestionTables.PRO_COLUMN_OPTION3)));
                question.setOption4(ac.getString(ac.getColumnIndex(QuizContract.QuestionTables.PRO_COLUMN_OPTION4)));
                question.setAnswerNr(ac.getInt(ac.getColumnIndex(QuizContract.QuestionTables.PRO_COLUMN_ANSWER_NR)));
                ProquestionList.add(question);
            }while (ac.moveToNext());
        }
        ac.close();
        return ProquestionList;
    }

}

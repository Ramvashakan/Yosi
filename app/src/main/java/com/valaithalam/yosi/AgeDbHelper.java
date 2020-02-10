package com.valaithalam.yosi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AgeDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Age_Aptitude_quest.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;


    public AgeDbHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String AGE_TABLE = "CREATE TABLE " +
                QuizContract.QuestionTables.AGE_TABLE_NAME + " ( " +
                QuizContract.QuestionTables._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionTables.AGE_COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionTables.AGE_COLUMN_OPTION1 + " TEXT, "+
                QuizContract.QuestionTables.AGE_COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionTables.AGE_COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionTables.AGE_COLUMN_OPTION4 + " TEXT, " +
                QuizContract.QuestionTables.AGE_COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(AGE_TABLE);
        fillQuestionTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionTables.AGE_TABLE_NAME );
        onCreate(db);


    }

    private void fillQuestionTable() {

        //Age questions
        Question q1 = new Question("A mother is twice as old as her son. If 20 years ago, the age of the mother was 10 times the age of the son, what is the present age of the mother?", "A)38 years", "B)40 years ", "C)43 years ", "D)45 years ", 4);
        addQuestions(q1);
        Question q2 = new Question("Four years ago a man was 6 times as old as his son. After 16 years he will be twice as old as his son. What is the present age of man and his son?", "A)34, 9", "B)33,7", "C)35,5", "D)36,6",1);
        addQuestions(q2);
        Question q3 = new Question("The ratio of the ages of Minu and Meera is 4:2. If the sum of their ages is 6 years, find the ratio of their ages after 8 years.", "A)8:6", "B)6:5", "C)6:4" ,"D)7:5",2);
        addQuestions(q3);
        Question q4 = new Question(" The ratio of the ages of Seeta and Geeta is 2:7. After 6 years, the ratio of their ages will be 1:2. What is the difference in their present ages?" , "A)8 years", "B)9 years" , "C)10 years", "D)11 years" , 3);
        addQuestions(q4);
        Question q5 = new Question("Ten years ago, the sum of ages of a father and his son was 34 years. If the ratio of present ages of the father and son is 7:2, find the present age of the son." ,"A) 12 years" , "B) 11 years", "C) 10 years" , "D) 8 years" , 1);
        addQuestions(q5);
        Question q6 = new Question("The sum of the ages of father and his son is 44 years. If 6 years after the father will be 3 times as old as his son, what are their present ages?" ,"A)36,8", "B)38,6" , "C)35,9" , "D)37,7", 1);
        addQuestions(q6);
        Question q7 = new Question("Ten years ago, Ajay was Apti Problem on ages 18rd as old as Vijay. If Ajay is 18 years old now, how old is Vijay now?" ,"A) 32 years", "B) 34  years" , "C) 36 years" , "D) 38 years", 2);
        addQuestions(q7);
        Question q8 = new Question("Age of father is seven times the age of his son. In the next 10 years, the age of father will be three times the age of his son. What is the age of father now?" , "A) 35 years" , "B) 37 yaers" , "C) 39 years" , "D)41 years" , 1);
        addQuestions(q8);
        Question q9 = new Question("Twelve years ago, Budh was twice as old as Badri. If the ratio of their present ages is 4:3 respectively, find the difference between their present ages." ,"A) 5 years" , "B) 6 years", "C) 7 years" , "D) 8 years" , 2);
        addQuestions(q9);
        Question q10 = new Question("Two years ago the ratio of ages of Naresh and Suresh was 3:2. If one year hence, the ratio of their ages will be 7:5, what is the sum of their present ages?" ,"A) 30 years" , "B) 32 years" , "C) 34 years" , "D) 36 years" , 3);
        addQuestions(q10);

    }


    private void addQuestions(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionTables.AGE_COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionTables.AGE_COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionTables.AGE_COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionTables.AGE_COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionTables.AGE_COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract.QuestionTables.AGE_COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract.QuestionTables.AGE_TABLE_NAME, null, cv);
    }
    public ArrayList<Question> getAllQuestions(){
        ArrayList<Question> AgequestionList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor ac = db.rawQuery("SELECT * FROM " + QuizContract.QuestionTables.AGE_TABLE_NAME, null);

        if (ac.moveToFirst()){
            do {
                Question question = new Question();
                question.setQuestion(ac.getString(ac.getColumnIndex(QuizContract.QuestionTables.AGE_COLUMN_QUESTION)));
                question.setOption1(ac.getString(ac.getColumnIndex(QuizContract.QuestionTables.AGE_COLUMN_OPTION1)));
                question.setOption2(ac.getString(ac.getColumnIndex(QuizContract.QuestionTables.AGE_COLUMN_OPTION2)));
                question.setOption3(ac.getString(ac.getColumnIndex(QuizContract.QuestionTables.AGE_COLUMN_OPTION3)));
                question.setOption4(ac.getString(ac.getColumnIndex(QuizContract.QuestionTables.AGE_COLUMN_OPTION4)));
                question.setAnswerNr(ac.getInt(ac.getColumnIndex(QuizContract.QuestionTables.COLUMN_ANSWER_NR)));
                AgequestionList.add(question);
            }while (ac.moveToNext());
        }
        ac.close();
        return AgequestionList;
    }



}

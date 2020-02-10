package com.valaithalam.yosi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TimeDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Time_Aptitude_quest.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public TimeDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String TABLE = "CREATE TABLE " +
                QuizContract.QuestionTables.TIME_TABLE_NAME + " ( " +
                QuizContract.QuestionTables._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionTables.TIME_COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionTables.TIME_COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionTables.TIME_COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionTables.TIME_COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionTables.TIME_COLUMN_OPTION4 + " TEXT, " +
                QuizContract.QuestionTables.TIME_COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(TABLE);
        fillQuestionTable();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionTables.TIME_TABLE_NAME);
        onCreate(db);

    }

    private void fillQuestionTable() {
        //Speed and distance questions
        Question q1 = new Question("A running man crosses a bridge of length 500 meters in 4 minutes. At what speed he is running?", "A) 8.5 km/hr", "B) 8.5 km/hr", "C) 9.5 km/hr", "D) 6.5 km/hr", 2);
        addQuestions(q1);
        Question q2 = new Question("A car running at a speed of 140 km/hr reached its destination in 2 hours. If the car wants to reach at its destination in 1 hour, at what speed it needs to travel?", "A) 300 km/hr", "B) 280 km/hr", "C) 250 km/hr", "D) 240 km/hr", 2);
        addQuestions(q2);
        Question q3 = new Question("A jogger is running at a speed of 15 km/hr. In what time he will cross a track of length 400 meters?", "A) 96 sec", "B) 100 sec", "C) 104 sec", "D) 110 sec", 1);
        addQuestions(q3);
        Question q4 = new Question("A horse covers a distance of 1500 meters in 1 minute 20 seconds. At what speed the horse is running? ", "A) 67.2 km/hr", "B) 67.7 km/hr", "C) 67. 5 km/hr", "D) 67.9 km/hr", 3);
        addQuestions(q4);
        Question q5 = new Question("A cyclist moving at a speed of 20 km/hr crosses a bridge in 2 minutes. What is the length of the bridge?", "A) 555.5 m", "B) 444.4 m", "C) 777.7 m", "D) 666.6 m", 4);
        addQuestions(q5);


    }

    private void addQuestions(Question question) {

        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionTables.TIME_COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionTables.TIME_COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionTables.TIME_COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionTables.TIME_COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionTables.TIME_COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract.QuestionTables.TIME_COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract.QuestionTables.TIME_TABLE_NAME, null, cv);
    }


    public ArrayList<Question> getAllQuestions() {

        ArrayList<Question> timequestionList = new ArrayList<>();
        db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionTables.TIME_TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionTables.TIME_COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionTables.TIME_COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionTables.TIME_COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionTables.TIME_COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionTables.TIME_COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionTables.TIME_COLUMN_ANSWER_NR)));
                timequestionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return timequestionList;
    }
}

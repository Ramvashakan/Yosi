package com.valaithalam.yosi;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract(){

    }

    public static class QuestionTables implements BaseColumns {


        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_ANSWER_NR = "answer_nr";

        public static final String AGE_TABLE_NAME = "age_quiz_questions";
        public static final String AGE_COLUMN_QUESTION = "question";
        public static final String AGE_COLUMN_OPTION1 = "option1";
        public static final String AGE_COLUMN_OPTION2 = "option2";
        public static final String AGE_COLUMN_OPTION3 = "option3";
        public static final String AGE_COLUMN_OPTION4 = "option4";
        public static final String AGE_COLUMN_ANSWER_NR = "answer_nr";

        public static final String PRO_TABLE_NAME = "probability_quiz_questions";
        public static final String PRO_COLUMN_QUESTION = "question";
        public static final String PRO_COLUMN_OPTION1 = "option1";
        public static final String PRO_COLUMN_OPTION2 = "option2";
        public static final String PRO_COLUMN_OPTION3 = "option3";
        public static final String PRO_COLUMN_OPTION4 = "option4";
        public static final String PRO_COLUMN_ANSWER_NR = "answer_nr";

        public static final String TIME_TABLE_NAME = "time_quiz_questions";
        public static final String TIME_COLUMN_QUESTION = "question";
        public static final String TIME_COLUMN_OPTION1 = "option1";
        public static final String TIME_COLUMN_OPTION2 = "option2";
        public static final String TIME_COLUMN_OPTION3 = "option3";
        public static final String TIME_COLUMN_OPTION4 = "option4";
        public static final String TIME_COLUMN_ANSWER_NR = "answer_nr";

        public static final String CI_TABLE_NAME = "ci_quiz_questions";
        public static final String CI_COLUMN_QUESTION = "question";
        public static final String CI_COLUMN_OPTION1 = "option1";
        public static final String CI_COLUMN_OPTION2 = "option2";
        public static final String CI_COLUMN_OPTION3 = "option3";
        public static final String CI_COLUMN_OPTION4 = "option4";
        public static final String CI_COLUMN_ANSWER_NR = "answer_nr";




    }




}

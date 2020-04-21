package com.example.studentsdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import Data.DbHandler;
import Model.DbModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHandler handler = new DbHandler(this);
        handler.deleteAllData();

        handler.addStudent(new DbModel(" Griffindor ", " Ron ",
                "Weasley ", 4.3f));
        handler.addStudent(new DbModel(" Griffindor ", " Hermione ",
                "Granger ", 4.9f));
        handler.addStudent(new DbModel(" Slizeryn ", " Draco ",
                "Malfoy ", 3.3f));
        handler.addStudent(new DbModel(" Griffindor ", " Ginny ",
                "Weasley ", 4.6f));
        handler.addStudent(new DbModel(" Hufflepuff ", " Cedric ",
                "Diggory ", 4.7f));


        List<DbModel> studentsList = handler.getAllStudents();

        for (DbModel dbModel : studentsList) {

            Log.d("StudentInfo", dbModel.getId() + dbModel.getFaculty()
            + dbModel.getName() + dbModel.getSurname() + dbModel.getGpa());
        }
    }
}

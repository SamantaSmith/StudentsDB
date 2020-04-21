package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.List;

import Model.DbModel;
import Util.Utils;

public class DbHandler extends SQLiteOpenHelper {
    public DbHandler(Context context) {
        super(context, Utils.DATABASE_NAME,null , Utils.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_STUDENTS_TABLE = "CREATE TABLE " + Utils.TABLE_NAME + "("
                + Utils.KEY_ID + " INTEGER PRIMARY KEY, "
                + Utils.KEY_FACULTY + " TEXT, "
                + Utils.KEY_NAME + " TEXT, "
                + Utils.KEY_SURNAME + " TEXT, "
                + Utils.KEY_GPA + " FLOAT" + ")";

        db.execSQL(CREATE_STUDENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Utils.TABLE_NAME);
        onCreate(db);
    }

    public void addStudent(DbModel dbModel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.KEY_FACULTY, dbModel.getFaculty());
        contentValues.put(Utils.KEY_NAME, dbModel.getName());
        contentValues.put(Utils.KEY_SURNAME, dbModel.getSurname());
        contentValues.put(Utils.KEY_GPA, dbModel.getGpa());

        db.insert(Utils.TABLE_NAME, null, contentValues);
        db.close();
    }

    public DbModel getStudent(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Utils.TABLE_NAME,
                new String[] {Utils.KEY_ID, Utils.KEY_FACULTY, Utils.KEY_NAME, Utils.KEY_SURNAME, Utils.KEY_GPA},
                Utils.KEY_ID + "=?", new String[] {String.valueOf(id)},
                null, null, null, null);

        if (cursor != null) {

            cursor.moveToFirst();
        }

        DbModel dbModel = new DbModel(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), Float.parseFloat(cursor.getString(4)));

        return dbModel;
    }

    public List<DbModel> getAllStudents() {

        SQLiteDatabase db = this.getReadableDatabase();
        List<DbModel> studentsList = new ArrayList<>();

        String selectAllStudents = "SELECT * FROM " + Utils.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllStudents, null);

        if (cursor.moveToFirst()) {

            do {

                DbModel dbModel= new DbModel();
                dbModel.setId(Integer.parseInt(cursor.getString(0)));
                dbModel.setFaculty(cursor.getString(1));
                dbModel.setName(cursor.getString(2));
                dbModel.setSurname(cursor.getString(3));
                dbModel.setGpa(Float.parseFloat(cursor.getString(4)));

                studentsList.add(dbModel);

            } while (cursor.moveToNext());
        }
        return studentsList;
    }

    public int updateStudent(DbModel dbModel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Utils.KEY_FACULTY, dbModel.getFaculty());
        contentValues.put(Utils.KEY_NAME, dbModel.getName());
        contentValues.put(Utils.KEY_SURNAME, dbModel.getSurname());
        contentValues.put(Utils.KEY_GPA, dbModel.getGpa());

        return db.update(Utils.TABLE_NAME, contentValues, Utils.KEY_ID + "=?",
                new String[] {String.valueOf(dbModel.getId())});
    }

    public int getStudentsCount() {

        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT * FROM " + Utils.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    public void deleteAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + Utils.TABLE_NAME;
        db.execSQL(deleteQuery);
    }
}

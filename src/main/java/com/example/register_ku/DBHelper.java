package com.example.register_ku;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
        db.execSQL("CREATE TABLE REGISTER (_id INTEGER PRIMARY KEY AUTOINCREMENT ,name string, email string, password string );");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String name, String email , String password) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO REGISTER VALUES(null, '" + name + "','" + email + "',  '" + password + "');");
        db.close();
    }


    public String getResult(String em, String pw) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM REGISTER WHERE email = '" + em + "' and password = '" + pw + "';", null);
        //WHERE email = '" + em + "'and password = '" + pw +"' ;",
        if (cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                result += cursor.getString(0)
                        + " : "
                        + cursor.getString(1)
                        + " : "
                        + cursor.getString(2)
                        + " | ";
            }
        } else if (cursor.getCount() == 0) {
            result = "null";
        }

        /*while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " : "
                    + cursor.getString(2)
                    + " | ";

        }*/

        return result;
    }

    public int CheckEmail(String em){
        SQLiteDatabase db = getReadableDatabase();
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT * FROM REGISTER WHERE email = '" + em + "' ;", null);

        if (cursor.getCount() == 0){
            result = 0; //이메일 사용 가능
        }
        else if (cursor.getCount() >= 1){
            result = 1; //이메일 사용 불가
        }
        return result;
    }
}




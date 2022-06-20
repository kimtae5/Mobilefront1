package com.kt5.mobilefront1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
//클래스는 default Constructor가 없어서
//생성자를 만들고 매개변수가 있는 생성자를 호출하지 않으면 에러 발생
public class ItemDB extends SQLiteOpenHelper {

    //생성
    //Context 는어떤 정보를 저장한 객체
    //안드로이드에서는 Context를 매개변수로 하는 메서드가 많은데
    //Context를 대입하라고 하면 Activity클래스의 인스턴스를 대입
    public  ItemDB(@Nullable Context context){
        super(context, "item.db", null,1);
    }

    //앱을 설치할 때 호출되는 메서드
    //필요한 테이블을 생성
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table item(itemid integer primary key," +
                "itemname, price integer, description, pictureurl, email)");
    }

    //업그레이드 될 떄 호출되는 메서드
    //여기서는 테이블을 삭제하고 새로 생성하는 코드를 주로 작성
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists item");
        onCreate(db);
    }
}

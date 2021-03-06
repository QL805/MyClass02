package cn.edu.sdwu.android02.classroom.sn170507180212;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 乔林 on 2020/4/29.
 */

public class MyOpenHelper extends SQLiteOpenHelper {
    private String STUDENT_TB_SQL="create table student(id integer primary key autoincrement，stuname text，)";
    public MyOpenHelper(Context context){
        //Context context上下文，String name数据库名称，CursorFactory factory传入null，int Version 数据库版本
        // super(context,"stud.db",null,1);
        //版本号 变更
        super(context,"stud.db",null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //当打开数据库时，发现数据库并不存在，此时会自动创建数据库，然后调用onCreate方法
        //在本方法，完成数据库对象（表，视图，索引等）的创建
        //通过SQLiteDatabase对象，完成SQL的执行
        sqLiteDatabase.execSQL(STUDENT_TB_SQL);
        Log.i(MyOpenHelper.class.toString(),"onCreate");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //当构造方法指定的版本号 与手机已有数据库更新的时候，调用本方法
        sqLiteDatabase.execSQL("alter table student add column stuadd text");
    }
}

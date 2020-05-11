package cn.edu.sdwu.android02.classroom.sn170507180212;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class Ch14Activity1 extends AppCompatActivity {
    private MyOpenHelper myOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ch14_1);

        myOpenHelper=new MyOpenHelper(this);
        //以可写方法打开数据库
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();
        try{
            //将插入的数据放置在ContentValues中、
            //事务的处理
            ContentValues contentValues=new ContentValues();
            contentValues.put("stuname","Tom");
            contentValues.put("stutel","15888888888");
            sqLiteDatabase.insert("student",null,contentValues);

            sqLiteDatabase.setTransactionSuccessful();//所有操作结束后 调用setTransactionSuccessful，将数据保存
            sqLiteDatabase.endTransaction();//结束事务

        }catch (Exception e){
            Log.e(Ch14Activity1.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();//结束事务
            //使用结束，数据库关闭
            sqLiteDatabase.close();

        }
    }

    public void query(View view){
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getReadableDatabase();
        try {
            Cursor cursor=sqLiteDatabase.rawQuery("select * from student where stuname=?",new  String[]{"Tom"});
            while (cursor.moveToNext()){//游标上移下移
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String stuname=cursor.getString(cursor.getColumnIndex("stuname"));
                String stutel=cursor.getString(cursor.getColumnIndex("stutel"));
                Log.i(Ch14Activity1.class.toString(),"id"+id+",stuname:"+stuname+",stutel"+stutel);

            }
            cursor.close();
        }catch (Exception e){
            Log.e(Ch14Activity1.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.close();
        }
    }

    public void delete(View view){
        //以可写方法打开数据库
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();
        try{
            //将插入的数据放置在ContentValues中、
            //事务的处理
            sqLiteDatabase.beginTransaction();

            sqLiteDatabase.delete("student","id=?",new String[]{"1"});

            sqLiteDatabase.setTransactionSuccessful();//所有操作结束后 调用setTransactionSuccessful，将数据保存
            sqLiteDatabase.endTransaction();//结束事务

        }catch (Exception e){
            Log.e(Ch14Activity1.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();//结束事务
            //使用结束，数据库关闭
            sqLiteDatabase.close();

        }

    }
    public void edit(View view){
        //以可写方法打开数据库
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();
        try{
            //将插入的数据放置在ContentValues中、
            //事务的处理
            sqLiteDatabase.beginTransaction();

            ContentValues contentValues=new ContentValues();
            contentValues.put("stutel","15888888888");

            sqLiteDatabase.update("student",contentValues,"id=?",new String[]{"2"});

            sqLiteDatabase.setTransactionSuccessful();//所有操作结束后 调用setTransactionSuccessful，将数据保存

        }catch (Exception e){
            Log.e(Ch14Activity1.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();//结束事务
            //使用结束，数据库关闭
            sqLiteDatabase.close();
        }

    }


}

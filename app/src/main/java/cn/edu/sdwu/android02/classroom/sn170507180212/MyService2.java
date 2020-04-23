package cn.edu.sdwu.android02.classroom.sn170507180212;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MyService2 extends Service {
    private Random random;
    private MyBinder myBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        random=new Random();
        myBinder=new MyBinder();
    }



    public MyService2() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i(MyService2.class.toString(),"onBind");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(MyService2.class.toString(),"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(MyService2.class.toString(),"onDestroy");
        super.onDestroy();
    }

    public class MyBinder extends Binder{
        public MyService2 getRandomService(){
            return MyService2.this;
        }
    }
    public int genRandom(){
        return random.nextInt();
    }
}

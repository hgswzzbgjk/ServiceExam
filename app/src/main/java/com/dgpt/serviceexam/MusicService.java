package com.dgpt.serviceexam;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
public class MusicService extends Service {
    //为日志工具设置标签
    private static String TAG = "MusicService";
    //定义音乐播放器变量
    private MediaPlayer mPlayer;
    //该服务不存在需要被创建时被调用，不管startService()还是bindService()都会启动时调用该方法

    class MyBinder extends Binder {
        MusicService getService(){
            return MusicService.this;
        }
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "MusicSevice onCreate()" , Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MusicSerice onCreate()");
        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.m2);
        //设置可以重复播放
        mPlayer.setLooping(true);
        Log.e(TAG, "mPlayer 设置好了");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "MusicSevice onStartCommand()"  , Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MusicSerice onStart()");
        play();
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        Toast.makeText(this, "MusicSevice onDestroy()" , Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MusicSerice onDestroy()");
        stop();
        super.onDestroy();
    }
    //其他对象通过bindService 方法通知该Service时该方法被调用
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "MusicSevice onBind()" , Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MusicSerice onBind()");
        return new MyBinder();
    }
    //其它对象通过unbindService方法通知该Service时该方法被调用
    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "MusicSevice onUnbind()" , Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MusicSerice onUnbind()");
        return super.onUnbind(intent);
    }

    public void play(){
        mPlayer.start();
        Log.e(TAG, "Music play");
    }

    public void stop(){
        mPlayer.stop();
        Log.e(TAG, "Music stop");
    }
}

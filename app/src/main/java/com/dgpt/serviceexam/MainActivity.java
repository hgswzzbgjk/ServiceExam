package com.dgpt.serviceexam;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
public class MainActivity extends Activity {
    //为日志工具设置标签
    private static String TAG = "MusicService";
    private Button startMusicService;
    private Button stopMusicService;
    private Button bindMusicService;
    private Button unbindMusicService;
    private Button playMusic;
    private Button stopMusic;
    private  MusicService musicService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //输出Toast消息和日志记录
        Toast.makeText(this, "MusicServiceActivity", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MusicServiceActivity");
        initView();
    }
    private void initView(){
        startMusicService = (Button) findViewById(R.id.startMusicService);
        stopMusicService = (Button) findViewById(R.id.stopMusicService);
        bindMusicService = (Button) findViewById(R.id.bindMusicService);
        unbindMusicService = (Button) findViewById(R.id.unbindMusicService);
        playMusic = (Button) findViewById(R.id.playMusic);
        stopMusic = (Button) findViewById(R.id.stopMusic);

        //定义点击监听器
        OnClickListener ocl = new OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示指定 intent所指的对象是个 service
                Intent intent = new Intent(MainActivity.this,MusicService.class);
                switch(v.getId()){
                    case R.id.startMusicService:
                        //开始服务
                        startService(intent);
                        break;
                    case R.id.stopMusicService:
                        //停止服务
                        stopService(intent);
                        break;
                    case R.id.bindMusicService:
                        //绑定服务
                        bindService(intent, conn, Context.BIND_AUTO_CREATE);
                        break;
                    case R.id.unbindMusicService:
                        //解绑服务
                        unbindService(conn);
                        break;
                    case R.id.playMusic:
                        //播放音乐
                        musicService.play();;
                        break;
                    case R.id.stopMusic:
                        //停止音乐
                        musicService.stop();
                        break;
                }
            }
        };
        //绑定点击监听
        startMusicService.setOnClickListener(ocl);
        stopMusicService.setOnClickListener(ocl);
        bindMusicService.setOnClickListener(ocl);
        unbindMusicService.setOnClickListener(ocl);
        playMusic.setOnClickListener(ocl);
        stopMusic.setOnClickListener(ocl);
    }
    //定义服务链接对象
    final ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivity.this, "MusicServiceActivity onSeviceDisconnected", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "MusicServiceActivity onSeviceDisconnected");
            musicService=null;
        }
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this, "MusicServiceActivity onServiceConnected" ,Toast.LENGTH_SHORT).show();
            Log.e(TAG, "MusicServiceActivity onServiceConnected");
            musicService=((MusicService.MyBinder)service).getService();
        }
    };
}


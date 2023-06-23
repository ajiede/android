package com.example.musicplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv_disk; //光碟
    private static SeekBar musicProgressBar; //进度条
    private static TextView currentTv; //当前音乐播放时长
    private static TextView totalTv; // 音乐总时长
    private Button btn_play,btn_pause, btn_continue, btn_exit;

    private ObjectAnimator animator;

    private MusicPlayerServices.MusicControl control;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            control = (MusicPlayerServices.MusicControl) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            control = (MusicPlayerServices.MusicControl) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            control = null;
        }
    };

    private void bindMusicService() {
        Intent intent = new Intent(this, MusicPlayerServices.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init(){
        iv_disk = findViewById(R.id.iv_music);
        musicProgressBar = findViewById(R.id.sb);
        currentTv = findViewById(R.id.tv_progress);
        totalTv = findViewById(R.id.tv_total);
        btn_play = findViewById(R.id.btn_play);
        btn_pause = findViewById(R.id.btn_pause);
        btn_continue = findViewById(R.id.btn_continue_play);
        btn_exit = findViewById(R.id.btn_exit);

        OnClick onClick = new OnClick();
        btn_play.setOnClickListener(onClick);
        btn_pause.setOnClickListener(onClick);
        btn_continue.setOnClickListener(onClick);
        btn_exit.setOnClickListener(onClick);

        // 对象是iv_disk 动作是rotation旋转，角度从0-360，由于是浮点数所以要交F
        animator = ObjectAnimator.ofFloat(iv_disk,"rotation",0,360.0F);
        animator.setDuration(10000); //设置动画的时长，单位为毫秒，10秒一圈
        animator.setInterpolator(new LinearInterpolator()); //旋转函数为线性，意味均匀旋转
        animator.setRepeatCount(-1); //设置转动圈数，-1一直转

        Intent intent = new Intent(getApplicationContext(), MusicPlayerServices.class);
        bindService(intent, connection, BIND_AUTO_CREATE);

        musicProgressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //音乐停止，光盘停止
                if(progress == seekBar.getMax()){
                    animator.pause();
                }
                //判断是否人为
                if(fromUser){
                    control.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //拖动打带，暂停音乐
                control.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //拖动完成，继续播放
                control.resume();
            }
        });
    }

    public class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_play:
                    //播放音乐
                    control.play();
                    //光盘转动
                    animator.start();
                    break;
                case R.id.btn_pause:
                    //暂停音乐
                    control.pause();
                    //光盘暂停
                    animator.pause();
                    break;
                case R.id.btn_continue_play:
                    //继续播放
                    control.resume();
                    //继续动画
                    animator.resume();
                    break;
                case R.id.btn_exit:
                    finish();
                    //退出
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        control.stop();
        unbindService(connection);
        super.onDestroy();
    }

    public static Handler handler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg){
            super.handleMessage(msg);
            Bundle bundle = msg.getData(); //获取从子线程发送过来的音乐播放进程
            int duration = bundle.getInt("duration"); //歌曲总时长
            int currentPosition = bundle.getInt("currentPosition"); //歌曲当前进度
            musicProgressBar.setMax(duration); //设置seekBar的最大值为歌曲总时长
            musicProgressBar.setProgress(currentPosition); //设置seekBar当前的进度位置
            String totalTime = msToMinSec(duration); //歌曲总时长
            String currentTime = msToMinSec(currentPosition); //歌曲当前播放时长
            totalTv.setText(totalTime);
            currentTv.setText(currentTime);
        }
    };

    public static String msToMinSec(int ms){
        int sec = ms/1000;
        int min = sec/60;
        sec -= min*60;
        return String.format("%02d:%02d",min,sec);
    }
}
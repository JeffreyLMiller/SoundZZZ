package com.jeffreylmiller.soundzzz;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int ALARM = AudioManager.STREAM_ALARM;
    private static final int MUSIC = AudioManager.STREAM_MUSIC;
    private static final int RING = AudioManager.STREAM_RING;
    private static final int SYSTEM = AudioManager.STREAM_SYSTEM;
    private static final int VOICE = AudioManager.STREAM_VOICE_CALL;


    private Button mSilentButton, mQuietButton, mLoudButton;
    private AudioManager mAudioManager;
    private TextView mAlarmValueTextView,
            mMusicValueTextView,
            mRingValueTextView,
            mSystemValueTextView,
            mVoiceValueTextView;
    private int alarmValue, musicValue, ringValue, systemValue, voiceValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSilentButton = (Button) findViewById(R.id.silent_button);
        mQuietButton = (Button) findViewById(R.id.quiet_button);
        mLoudButton = (Button) findViewById(R.id.loud_button);

        mAlarmValueTextView = (TextView) findViewById(R.id.alarm_value_textView);
        mMusicValueTextView = (TextView) findViewById(R.id.music_value_textView);
        mRingValueTextView = (TextView) findViewById(R.id.ring_value_textView);
        mSystemValueTextView = (TextView) findViewById(R.id.system_value_textView);
        mVoiceValueTextView = (TextView) findViewById(R.id.voice_value_textView);

        mSilentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSilentMode();
            }
        });
        mQuietButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setQuietMode();
            }
        });
        mLoudButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoudMode();
            }
        });

        try {
            mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        }catch(Exception e){
            e.printStackTrace();
        }

        //Make sure application has ability to alter the Do Not Disturb state from Android 23 up
        //from https://stackoverflow.com/questions/39151453/in-android-7-api-level-24-my-app-is-not-allowed-to-mute-phone-set-ringer-mode

        ////!!!!
        // Need to insert a notification to tell the user to allow this app (with name) to alter DnD.
        ////!!!!

        NotificationManager notificationManager =
                (NotificationManager)this.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !notificationManager.isNotificationPolicyAccessGranted()) {

            Intent intent = new Intent(
                    android.provider.Settings
                            .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivity(intent);
        }
    }

    private void setLoudMode() {
        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        mAudioManager.setStreamVolume(ALARM,
                mAudioManager.getStreamMaxVolume(ALARM),AudioManager.FLAG_VIBRATE);
        mAudioManager.setStreamVolume(MUSIC,
                mAudioManager.getStreamMaxVolume(MUSIC),AudioManager.FLAG_VIBRATE);
        mAudioManager.setStreamVolume(RING,
                mAudioManager.getStreamMaxVolume(RING),AudioManager.FLAG_VIBRATE);
        mAudioManager.setStreamVolume(SYSTEM,
                mAudioManager.getStreamMaxVolume(SYSTEM),AudioManager.FLAG_VIBRATE);
        mAudioManager.setStreamVolume(VOICE,
                mAudioManager.getStreamMaxVolume(VOICE),AudioManager.FLAG_VIBRATE);
        updateSoundValueTextFields();
    }

    private void setQuietMode() {
        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        mAudioManager.setStreamVolume(ALARM,
                mAudioManager.getStreamMaxVolume(ALARM)/2,AudioManager.FLAG_VIBRATE);
        mAudioManager.setStreamVolume(MUSIC,
                mAudioManager.getStreamMaxVolume(MUSIC)/2,AudioManager.FLAG_VIBRATE);
        mAudioManager.setStreamVolume(RING,
                mAudioManager.getStreamMaxVolume(RING)/2,AudioManager.FLAG_VIBRATE);
        mAudioManager.setStreamVolume(SYSTEM,
                mAudioManager.getStreamMaxVolume(SYSTEM)/2,AudioManager.FLAG_VIBRATE);
        mAudioManager.setStreamVolume(VOICE,
                mAudioManager.getStreamMaxVolume(VOICE)/2,AudioManager.FLAG_VIBRATE);
        updateSoundValueTextFields();
    }

    private void setSilentMode() {
        mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        mAudioManager.setStreamVolume(ALARM,
                0,AudioManager.FLAG_VIBRATE);
        mAudioManager.setStreamVolume(MUSIC,
                0,AudioManager.FLAG_VIBRATE);
        mAudioManager.setStreamVolume(RING,
                0,AudioManager.FLAG_VIBRATE);
        mAudioManager.setStreamVolume(SYSTEM,
                0,AudioManager.FLAG_VIBRATE);
        mAudioManager.setStreamVolume(VOICE,
                0,AudioManager.FLAG_VIBRATE);
        updateSoundValueTextFields();
    }
    private void updateSoundValueTextFields(){
        mAlarmValueTextView.setText(String.format(Locale.ENGLISH,"%d",mAudioManager.getStreamVolume(ALARM)));
        mMusicValueTextView.setText(String.format(Locale.ENGLISH,"%d",mAudioManager.getStreamVolume(MUSIC)));
        mRingValueTextView.setText(String.format(Locale.ENGLISH,"%d",mAudioManager.getStreamVolume(RING)));
        mSystemValueTextView.setText(String.format(Locale.ENGLISH,"%d",mAudioManager.getStreamVolume(SYSTEM)));
        mVoiceValueTextView.setText(String.format(Locale.ENGLISH,"%d",mAudioManager.getStreamVolume(VOICE)));
    }
}

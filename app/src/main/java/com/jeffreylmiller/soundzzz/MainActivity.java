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



    private Button mSilentButton, mQuietButton, mLoudButton;
    private AudioManager mAudioManager;
    private TextView mAlarmValueTextView,
            mMusicValueTextView,
            mRingValueTextView,
            mSystemValueTextView,
            mVoiceValueTextView;

    private SoundSettings silentSettings, quietSettings, loudSettings;
    private SoundController mSoundController;
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

        mSoundController = SoundController.get(this.getApplicationContext());
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
        silentSettings = new SoundSettings(0,0,0,0,0,SoundController.RINGER_SILENT);
        quietSettings = new SoundSettings(
                mSoundController.getMaxVolume(SoundController.ALARM)/2,
                mSoundController.getMaxVolume(SoundController.MUSIC)/2,
                mSoundController.getMaxVolume(SoundController.SYSTEM)/2,
                mSoundController.getMaxVolume(SoundController.VOICE)/2,
                mSoundController.getMaxVolume(SoundController.RING)/2,
                SoundController.RINGER_VIBRATE);
        loudSettings = new SoundSettings(
                mSoundController.getMaxVolume(SoundController.ALARM),
                mSoundController.getMaxVolume(SoundController.MUSIC),
                mSoundController.getMaxVolume(SoundController.SYSTEM),
                mSoundController.getMaxVolume(SoundController.VOICE),
                mSoundController.getMaxVolume(SoundController.RING),
                SoundController.RINGER_VIBRATE);

        updateSoundValueTextFields();

    }

    private void setLoudMode() {
        mSoundController.setVolume(loudSettings);
        updateSoundValueTextFields();
    }

    private void setQuietMode() {
        mSoundController.setVolume(quietSettings);
        updateSoundValueTextFields();
    }

    private void setSilentMode() {
        mSoundController.setVolume(silentSettings);
        updateSoundValueTextFields();
    }
    private void updateSoundValueTextFields(){
        mAlarmValueTextView.setText(String.format(
                Locale.ENGLISH,"%d",mSoundController.getVolume(SoundController.ALARM)));
        mMusicValueTextView.setText(String.format(
                Locale.ENGLISH,"%d",mSoundController.getVolume(SoundController.MUSIC)));
        mRingValueTextView.setText(String.format(
                Locale.ENGLISH,"%d",mSoundController.getVolume(SoundController.RING)));
        mSystemValueTextView.setText(String.format(
                Locale.ENGLISH,"%d",mSoundController.getVolume(SoundController.SYSTEM)));
        mVoiceValueTextView.setText(String.format(
                Locale.ENGLISH,"%d",mSoundController.getVolume(SoundController.VOICE)));
    }
}

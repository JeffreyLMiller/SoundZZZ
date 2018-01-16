package com.jeffreylmiller.soundzzz;

import android.content.Context;
import android.media.AudioManager;

/**
 * Created by JeffreyMiller on 1/14/2018.
 */

public class SoundController {
    public static final int RING = AudioManager.STREAM_RING;
    public static final int ALARM = AudioManager.STREAM_ALARM;
    public static final int MUSIC = AudioManager.STREAM_MUSIC;
    public static final int SYSTEM = AudioManager.STREAM_SYSTEM;
    public static final int VOICE = AudioManager.STREAM_VOICE_CALL;
    public static final int RINGER_NORMAL = AudioManager.RINGER_MODE_NORMAL;
    public static final int RINGER_VIBRATE = AudioManager.RINGER_MODE_VIBRATE;
    public static final int RINGER_SILENT = AudioManager.RINGER_MODE_SILENT;

    private static SoundController sSoundController;

    private AudioManager mAudioManager;

    public SoundController(Context context){
        try {
            mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SoundController get(Context context){
        if(sSoundController != null){
            return sSoundController;
        }
        else{
            sSoundController = new SoundController(context);
            return sSoundController;
        }
    }

    public int getVolume(int streamType){
        return mAudioManager.getStreamVolume(streamType);
    }
    public int getMaxVolume(int streamType){
        return mAudioManager.getStreamMaxVolume(streamType);
    }
    public void setVolume(int streamType, int volume){
        if(getMaxVolume(streamType) >= volume && 0 < volume){
            mAudioManager.setStreamVolume(streamType,
                    volume,AudioManager.FLAG_VIBRATE);
        }
        else if(getMaxVolume(streamType) < volume){
            mAudioManager.setStreamVolume(streamType,
                    getMaxVolume(streamType),AudioManager.FLAG_VIBRATE);
        }
        else{
            mAudioManager.setStreamVolume(streamType,
                    0,AudioManager.FLAG_VIBRATE);
        }
    }

    public void getRingerType(){
        mAudioManager.getRingerMode();
    }
    public void setRingerType(int ringerType){
        mAudioManager.setRingerMode(ringerType);
    }

    public void setVolume(SoundSettings settings){
        setRingerType(settings.getRingerType());
        setVolume(RING,settings.getRingVolume());
        setVolume(ALARM,settings.getAlarmVolume());
        setVolume(MUSIC,settings.getMusicVolume());
        setVolume(SYSTEM,settings.getSystemVolume());
        setVolume(VOICE,settings.getVoiceVolume());
    }
}

package com.jeffreylmiller.soundzzz;

import android.media.AudioManager;

/**
 * Created by JeffreyMiller on 1/14/2018.
 */

public class SoundSettings {
    private int alarmVolume, musicVolume, systemVolume, voiceVolume, ringVolume, ringerType;

    public SoundSettings(){
        alarmVolume = 0; musicVolume = 0; ringVolume = 0;
        systemVolume = 0; voiceVolume = 0; ringerType = 0;
    }
    public SoundSettings(int alarmVol, int musicVol, int systemVol,
                         int voiceVol, int ringVol, int ringTyp){

        alarmVolume = alarmVol; musicVolume = musicVol; ringVolume = ringVol;
        systemVolume = systemVol; voiceVolume = voiceVol; ringerType = ringTyp;
    }

    public int getAlarmVolume() {
        return alarmVolume;
    }
    public void setAlarmVolume(int alarmVolume) {
        this.alarmVolume = alarmVolume;
    }

    public int getMusicVolume() {
        return musicVolume;
    }
    public void setMusicVolume(int musicVolume) {
        this.musicVolume = musicVolume;
    }

    public int getSystemVolume() {
        return systemVolume;
    }
    public void setSystemVolume(int systemVolume) {
        this.systemVolume = systemVolume;
    }

    public int getVoiceVolume() {
        return voiceVolume;
    }
    public void setVoiceVolume(int voiceVolume) {
        this.voiceVolume = voiceVolume;
    }

    public int getRingVolume() {
        return ringVolume;
    }
    public void setRingVolume(int ringVolume) {
        this.ringVolume = ringVolume;
    }

    public int getRingerType() {
        return ringerType;
    }
    public void setRingerType(int ringerType) {
        this.ringerType = ringerType;
    }
}

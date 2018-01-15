package com.jeffreylmiller.soundzzz;

import java.util.Date;

/**
 * Created by JeffreyMiller on 1/15/2018.
 */

public class SoundSchedule {
    private SoundSettings mSoundSettings;
    private Date mDateScheduled;
    private boolean isOn;
    private boolean isRepeated;
    private boolean[] daysRepeated = {false,false,false,false,false,false,false};

    public SoundSettings getSoundSettings() {
        return mSoundSettings;
    }
    public void setSoundSettings(SoundSettings soundSettings) {
        mSoundSettings = soundSettings;
    }

    public Date getDateScheduled() {
        return mDateScheduled;
    }
    public void setDateScheduled(Date dateScheduled) {
        mDateScheduled = dateScheduled;
    }

    public boolean isOn() {
        return isOn;
    }
    public void setOn(boolean on) {
        isOn = on;
    }

    public boolean isRepeated() {
        return isRepeated;
    }
    public void setRepeated(boolean repeated) {
        isRepeated = repeated;
    }

    public boolean[] getDaysRepeated() {
        return daysRepeated;
    }
    public void setDaysRepeated(boolean[] daysRepeated) {
        this.daysRepeated = daysRepeated;
    }
}

package com.example.android.miwok;

/**
 * Created by admin on 3/3/2018.
 */

public class Word {
    //both are private because we only need these to be accessible from inside the class....these are private we add m to every variable in front

    private String mDefaultTranslation;

    private String mMiwokTranslation;

    private int mImageResourceId=NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED=-1;

    private int mAudioResourceId;


    //this is a constructor .it has npo return type ...name matches with the class name.
    //this constructor contains two strings
    public Word(String defaultTranslation,String miwokTranslation,int audioResourceId)
    {
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslation;
        mImageResourceId=audioResourceId;
        mAudioResourceId=audioResourceId;

    }

    //this is a constructor also this constructro also contains 2 strings and image view....
    public Word(String defaultTranslation,String miwokTranslation,int imageResourceId,int audioResourceId )
    {
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslation;
        mImageResourceId=imageResourceId;
        mAudioResourceId=audioResourceId;

    }

    //we add two SETTER methods to allow the two strings to be changed by an outside class
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }
    public int getmImageResourceId() {return mImageResourceId;}
    public int getmAudioResourceId() {return mAudioResourceId;}
    public boolean hasImage(){
      return mImageResourceId != NO_IMAGE_PROVIDED;
    }

}

package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;

public class NumbersActivity extends AppCompatActivity {

     private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override

                public void onAudioFocusChange(int focusChange) {
                    if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                        //Pause playback
                    }
                    else if(focusChange==AudioManager.AUDIOFOCUS_GAIN){
                        //Resume playback
                        mMediaPlayer.start();
                    }else if(focusChange==AudioManager.AUDIOFOCUS_LOSS){
                       releaseMediaPlayer();
                        //stop playback
                    }
                }
            };


     private MediaPlayer.OnCompletionListener  mCompletionListener =new MediaPlayer.OnCompletionListener() {
         @Override
         public void onCompletion(MediaPlayer mediaplayer) {
             releaseMediaPlayer();
         }
     };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

       // String[] words=new String[10];
     final ArrayList<Word> words = new ArrayList<Word>();
          /*words[0]="ONE";
          words[1]="TWO";
          words[2]="THREE";
          words[3]="FOUR";
          words[4]="FIVE";
          words[5]="SIX";
          words[6]="SEVEN";
          words[7]="EIGHT";
          words[8]="NINE";
          words[9]="TEN";*/
        words.add(new Word("ONE","LUTTI",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("TWO","OTIIKA",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("THREE","TOLOOKASU",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("FOUR","OYYISA",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("FIVE","MASSOKKA",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("SIX","TEMMOKKA",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("SEVEN","KENEKA KU",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("EIGHT","KAWINTA",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("NINE","WO'E",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("TEN","NO'OAACHA",R.drawable.number_ten,R.raw.number_ten));


       /* Log.v("NumbersActivity","Word at index 0:"+words[0]);
        Log.v("NumbersActivity","Word at index 1:"+words[1]);*/
    /*   Log.v("NumbersActivity","Word at index 0:"+words.get(0));
        Log.v("NumbersActivity","Word at index 1:"+words.get(1));
        Log.v("NumbersActivity","Word at index 2:"+words.get(2));
        Log.v("NumbersActivity","Word at index 3:"+words.get(3));
        Log.v("NumbersActivity","Word at index 4:"+words.get(4));
        Log.v("NumbersActivity","Word at index 5:"+words.get(5));
        Log.v("NumbersActivity","Word at index 6:"+words.get(6));
        Log.v("NumbersActivity","Word at index 7:"+words.get(7));
        Log.v("NumbersActivity","Word at index 8:"+words.get(8));
        Log.v("NumbersActivity","Word at index 9:"+words.get(9));*/

       /* int index=0;
        while(index<words.size()) {
            TextView wordView = new TextView(this);
            wordView.setText(words.get(index));
            rootView.addView(wordView);
            index = index + 1;*/
       /* LinearLayout rootView=(LinearLayout)findViewById(R.id.rootView);
       for(int index=0;index<words.size();index++){
           TextView wordView = new TextView(this);
           wordView.setText(words.get(index));
           rootView.addView(wordView);

        }*/

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this,words,R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word word = words.get(position);

                releaseMediaPlayer();


                //request audio focus for playback....
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                 // ************************  mAudioManager.registerMediaButtonEventReceiver(RemoteControlReceiver);********************************
                    //we have audio focus now......

                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }
    protected void onStop()
    {
        super.onStop();
        releaseMediaPlayer();
    }
    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;


            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}

/*
public class NumbersActivity /*implements View.OnClickListener*/
/*2.we make this function.
    /* public void onClick(View view){
         Toast.makeText(view.getContext(),"Open the list of numbers", Toast.LENGTH_SHORT).show();
*/
//}
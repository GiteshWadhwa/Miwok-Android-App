package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

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

      ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("where are you going?","minto woksus",R.raw.phrase_where_are_you_going));
        words.add(new Word("what is your name?","tinna oyaase'na",R.raw.phrase_what_is_your_name));
        words.add(new Word("my name is","gitesh",R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?","chokokki",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("i am feeling good","takkaaaki",R.raw.phrase_im_feeling_good));
        words.add(new Word("are you comming","topoppi",R.raw.phrase_are_you_coming));
        words.add(new Word("yes, i am comming.","tete",R.raw.phrase_yes_im_coming));
        words.add(new Word("i am comming","kolliti",R.raw.phrase_im_coming));
        words.add(new Word("lets go!","kululli",R.raw.phrase_lets_go));
        words.add(new Word("come here","kelelli",R.raw.phrase_come_here));




        WordAdapter adapter = new WordAdapter(this,words,R.color.category_phrases);

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

                Word word=words.get(position);
                //relesae the resource before the media player is initialized to play a different song.....
                releaseMediaPlayer();

                mMediaPlayer= MediaPlayer.create(PhrasesActivity.this,word.getmAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);

            }
        });
    }


    //release the resource after sound file finished.........
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
        }
 }
}
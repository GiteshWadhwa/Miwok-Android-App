package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;


    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaplayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "wetetti", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mothet", "chiwiita", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("son", "topiisa", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("daughter", "chokokki", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("old brother", "takkaaaki", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("young brother", "topoppi", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("old sister", "tete", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("young sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("grand mother", "kululli", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("grand father", "kelelli", R.drawable.family_grandfather, R.raw.family_grandfather));


        WordAdapter adapter = new WordAdapter(this, words, R.color.category_family);

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

                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getmAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });
    }

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
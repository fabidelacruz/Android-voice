package com.example.fabian.myproject;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.fabian.myproject.receiver.ButtonReceiver;
import com.example.fabian.myproject.receiver.HeadsetIntentReceiver;

public class MainActivity extends AppCompatActivity {

    AudioManager audio;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audio = (AudioManager) this.getSystemService(AUDIO_SERVICE);
        audio.registerMediaButtonEventReceiver(new ComponentName(this, ButtonReceiver.class));

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SpeechActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        if(!audio.isWiredHeadsetOn()){
            handleHeadsetConnect();
        }

        super.onResume();
    }

    private void handleHeadsetConnect() {
        IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(new HeadsetIntentReceiver(this), filter);
    }

}

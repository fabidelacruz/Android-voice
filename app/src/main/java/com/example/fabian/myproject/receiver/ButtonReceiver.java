package com.example.fabian.myproject.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import com.example.fabian.myproject.MainActivity;
import com.example.fabian.myproject.SpeechActivity;

import static android.content.Intent.ACTION_MEDIA_BUTTON;
import static android.content.Intent.EXTRA_KEY_EVENT;
import static android.view.KeyEvent.ACTION_DOWN;
import static android.view.KeyEvent.ACTION_UP;

public class ButtonReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        abortBroadcast();

        String intentAction = intent.getAction();
        if (!ACTION_MEDIA_BUTTON.equals(intentAction)) {
            return;
        }

        KeyEvent event = intent.getParcelableExtra(EXTRA_KEY_EVENT);
        if (event == null) {
            return;
        }

        int action = event.getAction();
        switch(action) {
            case ACTION_DOWN:
                Intent intent1 = new Intent(context, SpeechActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
                break;
            case ACTION_UP:
                break;
        }
    }

}

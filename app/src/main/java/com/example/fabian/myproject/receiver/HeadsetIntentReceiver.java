package com.example.fabian.myproject.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

public class HeadsetIntentReceiver extends BroadcastReceiver {

    AlertDialog.Builder builder;

    AlertDialog dialog;

    public HeadsetIntentReceiver(final Activity context) {
        builder = new AlertDialog.Builder(context)
                .setMessage("Por favor conecte sus auriculares")
                .setNegativeButton("Cerrar", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.finish();
                    }
                });
    }

    @Override public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
            int state = intent.getIntExtra("state", -1);
            switch (state) {
                case 0:
                    dialog = builder.show();
                    break;
                case 1:
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                    context.unregisterReceiver(this);
                    break;
                default:
                    break;
            }
        }
    }
}

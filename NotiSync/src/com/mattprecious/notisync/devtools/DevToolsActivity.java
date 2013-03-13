
package com.mattprecious.notisync.devtools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.mattprecious.notisync.message.BaseMessage;
import com.mattprecious.notisync.util.Preferences;

public class DevToolsActivity extends SherlockFragmentActivity {
    public static final String ACTION_SEND_MESSAGE =
            "com.mattprecious.notisync.activity.DevToolsActivity.ACTION_SEND_MESSAGE";
    public static final String ACTION_RECEIVE_MESSAGE =
            "com.mattprecious.notisync.activity.DevToolsActivity.ACTION_RECEIVE_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new DevToolsFragment()).commit();
    }

    public void sendMessage(BaseMessage message) {
        Intent intent;
        if (Preferences.isPrimary(this)) {
            intent = new Intent(ACTION_SEND_MESSAGE);
        } else {
            intent = new Intent(ACTION_RECEIVE_MESSAGE);
        }

        intent.putExtra("message", BaseMessage.toJsonString(message));

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

}

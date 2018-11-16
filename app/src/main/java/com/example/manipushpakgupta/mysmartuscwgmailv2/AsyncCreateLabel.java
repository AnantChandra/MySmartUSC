package com.example.manipushpakgupta.mysmartuscwgmailv2;

import android.util.Log;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class AsyncCreateLabel extends CommonAsyncTask {

    static String l = "";

    AsyncCreateLabel(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    protected void doInBackground() throws IOException {

        Log.v("name of label", l);
        Label label = new Label().setName(l).setLabelListVisibility("labelShow").setMessageListVisibility("show");
        label = client.users().labels().create("me", label).execute();
        activity.map.put(label.getName(), label);
        Log.v("after creating label", l);
    }

    static void run(MainActivity mainActivity, String key) {
        new AsyncCreateLabel(mainActivity).execute();
        l = key;
    }
}

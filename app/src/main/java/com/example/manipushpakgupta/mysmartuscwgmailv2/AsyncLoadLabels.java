package com.example.manipushpakgupta.mysmartuscwgmailv2;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class AsyncLoadLabels extends CommonAsyncTask {

    AsyncLoadLabels(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    protected void doInBackground() throws IOException {
        List<String> result = new ArrayList<String>();
        ListLabelsResponse listResponse = client.users().labels().list("me").execute();
        List<Label> labels = listResponse.getLabels();
        if (labels != null) {
            for (Label label : labels) {
                result.add(label.getName());
            }
        } else {
            result.add("No Labels.");
        }
        activity.labelsList = result;
    }

    static void run(MainActivity mainActivity) {
        new AsyncLoadLabels(mainActivity).execute();
    }
}

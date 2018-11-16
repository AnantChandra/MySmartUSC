package com.example.manipushpakgupta.mysmartuscwgmailv2;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Label;
import com.google.api.services.gmail.model.ListLabelsResponse;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class AsyncLoadLabelEmails extends CommonAsyncTask {

    static String label = "";

    AsyncLoadLabelEmails(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    protected void doInBackground() throws IOException {
//        List<String> result = new ArrayList<String>();
//        ListLabelsResponse listResponse = client.users().labels().list("me").execute();
//        List<Label> labels = listResponse.getLabels();
//        if (labels != null) {
//            for (Label label : labels) {
//                result.add(label.getName());
//            }
//        } else {
//            result.add("No Labels.");
//        }
//        activity.labelsList = result;
        List<String> labels = new ArrayList<String>();
        String id = activity.map.get(label).getId();
        labels.add(id);
        List<Message> messagewithLabels = listMessagesWithLabels(client, "me", labels);
        List<String> result = new ArrayList<String>();

        if(messagewithLabels != null) {
            for(Message mess: messagewithLabels) {
                getMessage(client, "me", mess.getId(), result);
            }
        }
        else {
            result.add("No emails with that label");
        }

        activity.labelsList = result;
    }

    public static void getMessage(Gmail service, String userId, String messageId, List<String> content)
            throws IOException {
        Message message = service.users().messages().get(userId, messageId).execute();
        content.add(message.getSnippet());
    }

    private static List<Message> listMessagesWithLabels(Gmail service, String userId, List<String> labelIds)
            throws IOException {
        ListMessagesResponse response = service.users().messages().list(userId).setLabelIds(labelIds).execute();
        List<Message> messages = response.getMessages();
        return messages;
    }

    static void run(MainActivity mainActivity, String l) {
        new AsyncLoadLabelEmails(mainActivity).execute();
        label = l;
    }
}

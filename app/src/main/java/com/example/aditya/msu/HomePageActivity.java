package com.example.aditya.msu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashSet;

public class HomePageActivity extends AppCompatActivity {

    Mail mail = new Mail();
    public HashSet<String> getTerms() {
        return terms;
    }
    public void setTerms(HashSet<String> terms) {
        this.terms = terms;
    }

    public static HashSet<String> getSender() {
        return sender;
    }

    public static void setSender(HashSet<String> sender) {
        HomePageActivity.sender = sender;
    }

    public static HashSet<String> sender = new HashSet<>();
    public static HashSet<String> terms = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

    }

    public void keyWordButton(View v) {
        EditText keyWord = findViewById(R.id.editText);
        final String keyText = keyWord.getText().toString();
        Button newKeyWord = new Button(this);
        newKeyWord.setText(keyText);
        terms.add(keyText);
        printToConsole();

        LinearLayout ll = (LinearLayout)findViewById(R.id.homepage);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.addView(newKeyWord, lp);

        newKeyWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, Main2Activity.class);
                intent.putExtra("keyWord", keyText);
                intent.putExtra("email", "");
                startActivity(intent);
            }
        });
    }

    public void emailButton(View v) {
        EditText emailID = findViewById(R.id.editText2);
        final String emailText = emailID.getText().toString();
        Button newEmailID = new Button(this);
        newEmailID.setText(emailText);
        sender.add(emailText);
        printToConsole();

        LinearLayout ll = (LinearLayout)findViewById(R.id.homepage);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.addView(newEmailID, lp);

        newEmailID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, Main2Activity.class);
                intent.putExtra("email", emailText);
                intent.putExtra("keyWord", "");
                startActivity(intent);
            }
        });
    }

    public void allEmails(View v) {
        Intent intent = new Intent(HomePageActivity.this, Main2Activity.class);
        intent.putExtra("email", "");
        intent.putExtra("keyWord", "");
        startActivity(intent);
    }

    public void printToConsole(){
        //printPopulated(terms);
    }
}

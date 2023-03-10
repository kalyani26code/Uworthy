package com.example.uworthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class UserHome extends AppCompatActivity implements View.OnClickListener {
    Button therapiesbtn,storiesbtn,nutritionbtn,depretionbtn,lifeboxbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);


        therapiesbtn=findViewById(R.id.btntherapies);
        storiesbtn=findViewById(R.id.btnstories);
        nutritionbtn=findViewById(R.id.btnnutritions);
        depretionbtn=findViewById(R.id.btndesper);
        lifeboxbtn=findViewById(R.id.btnlifebox);
        therapiesbtn.setOnClickListener(this);
        storiesbtn.setOnClickListener(this);
        nutritionbtn.setOnClickListener(this);
        depretionbtn.setOnClickListener(this);
        lifeboxbtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id==R.id.btntherapies){
            Intent i1 = new Intent(getApplicationContext(),Choosecolor.class);
            startActivity(i1);
        }
        if(id==R.id.btnstories){
            Intent i1 = new Intent(getApplicationContext(),Storiesactivity.class);
            startActivity(i1);
        }
        if(id==R.id.btnnutritions){
            Intent i1 = new Intent(getApplicationContext(),DiteActivity.class);
            startActivity(i1);
        }
        if(id==R.id.btnlifebox){
            Intent i1 = new Intent(getApplicationContext(),Videofile.class);
            startActivity(i1);
        }
        if(id==R.id.btndesper){
            Intent i1 = new Intent(getApplicationContext(),QuestionActivity.class);
            startActivity(i1);
        }
    }
}

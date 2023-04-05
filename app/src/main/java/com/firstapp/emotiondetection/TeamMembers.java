package com.firstapp.emotiondetection;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TeamMembers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_members);
    }

    public void click(View v) {
        Intent abt = null;
        switch(v.getId()) {
            case R.id.emy:
                abt=new Intent(this, AboutTeamMembers.class);
                break;
            case R.id.viji:
                abt=new Intent(this,AboutViji.class);
                break;
            case R.id.uppretta:
                abt=new Intent(this,AboutUppretta.class);
                break;
            case R.id.naga:
                abt=new Intent(this,AboutNaga.class);
                break;
        }
        startActivity(abt);

    }
}
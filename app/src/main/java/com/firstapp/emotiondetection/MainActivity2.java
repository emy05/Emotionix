package com.firstapp.emotiondetection;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    FirebaseAuth auth;
    Button button;
    TextView textView;
    FirebaseUser user;
    TextView tx4;
    Button btnclose;
Button calender;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        calender = (Button) findViewById(R.id.date_picker);
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        btnclose=findViewById(R.id.alertbutton);


        builder=new AlertDialog.Builder(this);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Alert!!")
                        .setMessage("Do you want to close the application")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();
            }
        });
        auth=FirebaseAuth.getInstance();
        button=findViewById(R.id.btn_logout);
textView=findViewById(R.id.user_details);
        user=auth.getCurrentUser();
        if(user==null){
            Intent intent=new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
            finish();
        }else{
            textView.setText(user.getEmail());

        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
        createNotificationChannel();
        tx4 = (TextView) findViewById(R.id.textView4);
        registerForContextMenu(tx4);
        Button button=findViewById(R.id.button5);
        button.setOnClickListener(v -> {
Toast.makeText(this,"Reminder to Journal!",Toast.LENGTH_SHORT).show();
Intent intent=new Intent(MainActivity2.this,ReminderBroadcast.class);
PendingIntent pendingIntent=PendingIntent.getBroadcast(MainActivity2.this,0,intent,PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
            long timeAtButtonClick=System.currentTimeMillis();
            long tenSecondsInMillis = 1000*10;
            alarmManager.set(AlarmManager.RTC_WAKEUP,timeAtButtonClick+tenSecondsInMillis,pendingIntent);
        });
    }

    public void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name="ReminderChannel";
            String description="Channel for Reminder";
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel=new NotificationChannel("notifyMe",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu2,menu);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Intent about = null;
        switch(item.getItemId()){
            case R.id.option1:
                about=new Intent(this,AboutUs.class);
                break;
            case R.id.option2:
              about=new Intent(this,ProjectDesc.class);
                break;

        }
        startActivity(about);
        return true;
    }
    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_file,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.aboutus:
            Intent crud=new Intent(this,CrudActivity.class);
                startActivity(crud);
            case R.id.teamdetails:
                Toast.makeText(MainActivity2.this,"Team Details",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.teammembers:
                Intent team=new Intent(this,TeamMembers.class);
                startActivity(team);
            case R.id.aboutmembers:
                Toast.makeText(MainActivity2.this,"Students of TCE",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void ourteam(View v){
        PopupMenu popup=new PopupMenu(this,v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }



    @Override
    public boolean onMenuItemClick(MenuItem item) {
       switch(item.getItemId()){
           case R.id.itemone:
             Toast.makeText(this,"EMY PREMLIN A, VIJI M, UPPRETTA S, NAGASREE R A",Toast.LENGTH_LONG).show();
             return true;
           case R.id.itemtwo:
               Toast.makeText(this,"Contact us through www.emotionix.com",Toast.LENGTH_LONG).show();
           default:
               return false;

       }
    }

    public void openActivity2(){
        Intent cal=new Intent(this,CalActivity.class);
        startActivity(cal);
    }



}
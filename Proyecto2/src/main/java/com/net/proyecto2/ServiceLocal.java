package com.net.proyecto2;

import android.*;
import android.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mac on 6/24/13.
 */
public class ServiceLocal extends Service {

    private NotificationManager not_man;
    private  final IBinder mBinder = new LocalBinder();
    private int IDNOTIFICATION=1;

    public class LocalBinder extends Binder
    {
        ServiceLocal getService(){
            return ServiceLocal.this;
        }
    }


    @Override
    public void  onCreate()
    {
      this.not_man=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public void onDestroy()
    {
        this.not_man.cancel(IDNOTIFICATION);
        Toast.makeText(this,"Se detiene Service Local",Toast.LENGTH_SHORT).show();
    }

    public void showTimeNotification()
    {
        SimpleDateFormat mDateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=mDateFormat.format(new Date());
        Notification notification =new Notification (
                R.drawable.ic_menu_info_details,date,System.currentTimeMillis()
        );

    }







    public IBinder onBind (Intent intent )
    {
        return mBinder;
    }




}

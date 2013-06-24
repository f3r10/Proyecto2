package com.net.proyecto2;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.app.Activity;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.*;


public class MainActivity extends Activity implements View.OnClickListener {
    private Button btnstartService;
    private Button btnmsjService;
    private Button btnstopService;
    private Intent i_service;
    private ServiceLocal serviceLocal;
    private boolean mBounded=false;

    private ServiceConnection mConnection= new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBounded=true;
            ServiceLocal.LocalBinder mLocalBinder=(ServiceLocal.LocalBinder)service;
            serviceLocal=mLocalBinder.getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBounded=false;
            serviceLocal=null;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnstartService=(Button)findViewById(R.id.btnStartService);
        this.btnmsjService=(Button)findViewById(R.id.btnMsjService);
        this.btnstopService=(Button)findViewById(R.id.btnStopService);
        this.btnstartService.setOnClickListener(this);
        this.btnmsjService.setOnClickListener(this);
        this.btnstopService.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case (R.id.btnStartService):
                startService();
                break;
            case (R.id.btnMsjService):
                msjService();
                break;
            case (R.id.btnStopService):
                stopService();
                break;
        }

    }

    private void startService()
    {
        this.i_service=new Intent(this,ServiceLocal.class);
        startService(i_service);
        bindService(i_service,mConnection,BIND_AUTO_CREATE);
    }

    private void msjService()
    {
        this.serviceLocal.showTimeNotification();
    }

    private void stopService(){

        stopService(this.i_service);
        if(mBounded){
            unbindService(mConnection);
            mBounded=false;
        }
    }

}

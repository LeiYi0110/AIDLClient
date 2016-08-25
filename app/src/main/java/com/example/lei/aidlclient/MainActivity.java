package com.example.lei.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.lei.androiddemo.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {


    IMyAidlInterface mIRemoteService;
    private ServiceConnection mConnection = new ServiceConnection() {
        // Called when the connection with the service is established
        public void onServiceConnected(ComponentName className, IBinder service) {
            // Following the example above for an AIDL interface,
            // this gets an instance of the IRemoteInterface, which we can use to call on the service
            mIRemoteService = IMyAidlInterface.Stub.asInterface(service);

            String b;
            try
            {
                b =  mIRemoteService.hello();

                String a = b;
                a = "";
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }




            //String b = mIRemoteService.hello();
        }

        // Called when the connection with the service disconnects unexpectedly
        public void onServiceDisconnected(ComponentName className) {
            //Log.e(TAG, "Service has unexpectedly disconnected");
            mIRemoteService = null;
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        try
        {
            Intent intent = new Intent("com.example.lei.androiddemo.RemoteService");
            intent.setPackage("com.example.lei.androiddemo");
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

            if (mIRemoteService != null)
            {
                mIRemoteService.hello();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}

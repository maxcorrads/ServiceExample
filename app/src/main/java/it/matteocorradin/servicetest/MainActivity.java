package it.matteocorradin.servicetest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Servizio serviceExample;

    private ServiceConnection sConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            serviceExample = ((Servizio.LocalBinder) service).getService();
            serviceExample.showToast("Servizio Connesso");
        }
        public void onServiceDisconnected(ComponentName className) {
            serviceExample = null;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        bindService(new Intent(this, Servizio.class), sConnection, Context.BIND_AUTO_CREATE);
    }
    @Override
    protected void onPause() {
        super.onPause();
        unbindService(sConnection);
    }
}

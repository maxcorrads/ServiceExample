package it.matteocorradin.servicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by matteocorradin on 02/10/17.
 */

public class Servizio extends Service {

    public class LocalBinder extends Binder {
        private Servizio service;
        public LocalBinder(Servizio service) {
            this.service = service;
        }
        public Servizio getService() {
            return service;
        }
    }

    int mStartMode = START_NOT_STICKY;       // indicates how to behave if the service is killed
    boolean mAllowRebind = true; // indicates whether onRebind should be used

    @Override
    public void onCreate() {
        // The service is being created
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // The service is starting, due to a call to startService()
        return mStartMode;
    }
    @Override
    public IBinder onBind(Intent intent) {
        // A client is binding to the service with bindService()
        return new LocalBinder(this);
    }
    @Override
    public boolean onUnbind(Intent intent) {
        // All clients have unbound with unbindService()
        return mAllowRebind;
    }
    @Override
    public void onRebind(Intent intent) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
    }
    @Override
    public void onDestroy() {
        // The service is no longer used and is being destroyed
    }

    public void showToast(String message) { Toast.makeText(this, message,
            Toast.LENGTH_SHORT).show(); }
}
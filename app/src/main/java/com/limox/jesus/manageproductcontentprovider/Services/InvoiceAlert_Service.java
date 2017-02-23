package com.limox.jesus.manageproductcontentprovider.Services;

import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

/**
 * Created by jesus on 23/02/17.
 */
public class InvoiceAlert_Service extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        notify(5);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    /*protected class MyBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }*/


    private void notify(int nInvoices){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setAutoCancel(true);
        builder.setContentTitle("Cuentas pendientes");
        builder.setContentText("Tiene "+nInvoices+" cuentas pendientes");
        builder.setSmallIcon(android.R.mipmap.sym_def_app_icon);
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());
    }

}

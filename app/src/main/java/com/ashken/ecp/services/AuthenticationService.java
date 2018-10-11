package com.ashken.ecp.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.ashken.ecp.authenticators.AccountAuthenticator;

/**
 * Created by ashken on 2/13/16.
 */
public class AuthenticationService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new AccountAuthenticator(this).getIBinder();
    }
}

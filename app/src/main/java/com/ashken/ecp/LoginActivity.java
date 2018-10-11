package com.ashken.ecp;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ashken.ecp.authenticators.AccountAuthenticator;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ashken on 2/9/16.
 */
public class LoginActivity extends AccountAuthenticatorActivity {
    public static final String ARG_ACCOUNT_TYPE = "";
    public static final String ARG_AUTH_TYPE = "";
    public static final  String ARG_IS_ADDING_NEW_ACCOUNT = "" ;

    @Bind(R.id.btn_login) Button loginButton;
    @Bind(R.id.link_signup) TextView registerButton;
    @Bind(R.id.input_id) EditText inputId;
    @Bind(R.id.input_password) EditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_login) void navigateToDash()
    {
        if(!validate())
            return;

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();


        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        progressDialog.dismiss();
        finish();
    }

    @OnClick(R.id.link_signup) void navigateToSignup()
    {
        Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean validate() {
        boolean valid = true;
        String userid = inputId.getText().toString();
        String password = inputPassword.getText().toString();
        if(userid.isEmpty() )
        {
            inputId.setError("user id is required");
            valid=false;
        }
        else
        {
            inputId.setError(null);
        }

        if(password.isEmpty())
        {
            inputPassword.setError("password is required");
            valid = false;
        }
        else
        {
            inputPassword.setError(null);
        }
        return valid;
    }

    private void finishLogin(String username,String password)
    {
        String accountName = this.getIntent().getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
        String accountPassword = this.getIntent().getStringExtra(password);
        final Account account = new Account(accountName, this.getIntent().getStringExtra(AccountManager.KEY_ACCOUNT_TYPE));
        String authtoken = this.getIntent().getStringExtra(AccountManager.KEY_AUTHTOKEN);
        String authtokenType = this.getIntent().getStringExtra(LoginActivity.ARG_AUTH_TYPE);

        AccountManager accountManager = AccountManager.get(this);
        accountManager.addAccountExplicitly(account, accountPassword, null);
        accountManager.setAuthToken(account, authtokenType, authtoken);

        final Intent intent = new Intent();
        intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, username);
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, "com.ashken.ecp");
        intent.putExtra(AccountManager.KEY_AUTHTOKEN, authtoken);

        setAccountAuthenticatorResult(this.getIntent().getExtras());
        setResult(RESULT_OK, this.getIntent());
        finish();
    }
}

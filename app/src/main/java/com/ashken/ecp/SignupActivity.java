package com.ashken.ecp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ashken on 2/9/16.
 */
public class SignupActivity extends AppCompatActivity {
    @Bind(R.id.btn_signup) Button signupButton;
    @Bind(R.id.input_regid) EditText idInput;
    @Bind(R.id.input_regemail) EditText emailInput;
    @Bind(R.id.input_regpassword) EditText passwordInput;
    //@Bind(R.id.input_confirmpassword) EditText confirmpasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_signup) void navigateToDash()
    {
        if(!validate())
            return;

        Intent intent = new Intent(SignupActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean validate() {
        boolean valid = true;
        String userid = idInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        //String confirm = confirmpasswordInput.getText().toString();

        if(userid.isEmpty() )
        {
            idInput.setError("user id is required");
            valid=false;
        }
        else
        {
            idInput.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("enter a valid email address");
            valid = false;
        } else {
            emailInput.setError(null);
        }

        if(password.isEmpty())
        {
            passwordInput.setError("password is required");
            valid = false;
        }
        else
        {
            passwordInput.setError(null);
        }
        /*if(confirm.isEmpty())
        {
            confirmpasswordInput.setError("password confirmation required");
            valid = false;
        }
        else
        {
            confirmpasswordInput.setError(null);
        }

        if(!password.equals(confirm))
        {
            confirmpasswordInput.setError("password confirmation failure");
            valid = false;
        }
        else
        {
            confirmpasswordInput.setError(null);
        }*/
        return valid;
    }
}


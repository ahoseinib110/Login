package org.maktab.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    public static final String KEY_USER_NAME = "userName";
    public static final String KEY_PASSWORD = "password";
    public static final int REQUEST_CODE_SIGN_UP = 0;
    private EditText mEditTextUserName;
    private EditText mEditTextPassword;
    private Button mButtonLogin;
    private Button mButtonSignUp;

    private String mUserName;
    private int mPassword;

    androidx.constraintlayout.widget.ConstraintLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setOnClickListeners();
        Log.d("bashir","salam111");
        if(savedInstanceState!=null){
            mUserName = savedInstanceState.getString(KEY_USER_NAME);
            mPassword = savedInstanceState.getInt(KEY_PASSWORD,0);
        }
    }

    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mUserName!=null && mPassword!=0){
            outState.putString(KEY_USER_NAME, mUserName);
            outState.putInt(KEY_PASSWORD, mPassword);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode!=RESULT_OK || data==null){
            return;
        }
        if(requestCode==REQUEST_CODE_SIGN_UP){
            mUserName = data.getStringExtra(KEY_USER_NAME);
            mPassword = data.getIntExtra(KEY_PASSWORD,0);
            mEditTextUserName.setText(mUserName);
            mEditTextPassword.setText(String.valueOf(mPassword));
        }
    }

    private void findViews() {
        mEditTextUserName = findViewById(R.id.editTextUserName);
        mEditTextPassword = findViewById(R.id.editTextPassword);
        mButtonLogin = findViewById(R.id.buttonLogin);
        mButtonSignUp = findViewById(R.id.buttonSignUp);
        mainLayout = findViewById(R.id.mainLayout);
    }

    private void setOnClickListeners() {
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isEmptyUserName() || isEmptyPassword()) {
                    Toast.makeText(LoginActivity.this, "please fill request fields", Toast.LENGTH_SHORT).show();
                }else{
                    String userName = String.valueOf(mEditTextUserName.getText());
                    String password = String.valueOf(mEditTextPassword.getText());
                    if (mPassword != 0 || mUserName != null) {
                        if (mUserName.equals(userName) &&  mPassword == Integer.parseInt(password)) {
                            showSnackbar();
                        } else {
                            Toast.makeText(LoginActivity.this, "user name or password is incorrect!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "please sign up first", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                if(!isEmptyUserName()) {
                    intent.putExtra(KEY_USER_NAME, String.valueOf(mEditTextUserName.getText()));
                }
                if(!isEmptyPassword()){
                    intent.putExtra(KEY_PASSWORD, Integer.parseInt(String.valueOf(mEditTextPassword.getText())));
                }
                startActivityForResult(intent, REQUEST_CODE_SIGN_UP);
            }
        });
    }

    private void showSnackbar() {
        Snackbar snackbar = Snackbar.make(mainLayout,"Correct",Snackbar.LENGTH_SHORT);
        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        snackbar.show();
    }

    public boolean isEmptyUserName(){
        if(String.valueOf(mEditTextUserName.getText()).equals("") ){
            return true;
        }
        return false;
    }

    public boolean isEmptyPassword(){
        if( String.valueOf(mEditTextPassword.getText()).equals("")){
            return true;
        }
        return false;
    }
}
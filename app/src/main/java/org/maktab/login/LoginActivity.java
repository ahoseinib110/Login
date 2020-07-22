package org.maktab.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setOnClickListeners();
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
            mEditTextPassword.setText(mPassword);
        }
    }

    private void findViews() {
        mEditTextUserName = findViewById(R.id.editTextUserName);
        mEditTextPassword = findViewById(R.id.editTextPassword);
        mButtonLogin = findViewById(R.id.buttonLogin);
        mButtonSignUp = findViewById(R.id.buttonSignUp);
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
                            Toast.makeText(LoginActivity.this, "correct", Toast.LENGTH_SHORT).show();
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
                Log.d("bashir","hi1");
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

    public boolean isEmptyUserName(){
        if(mEditTextUserName.getText()==null ){
            return true;
        }
        return false;
    }

    public boolean isEmptyPassword(){
        if( mEditTextPassword.getText()==null){
            return true;
        }
        return false;
    }
}
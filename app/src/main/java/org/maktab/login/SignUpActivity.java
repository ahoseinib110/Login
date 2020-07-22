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

public class SignUpActivity extends AppCompatActivity {
    private EditText mEditTextUserName;
    private EditText mEditTextPassword;
    private Button mButtonLogin;
    private Button mButtonSignUp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("bashir","salam0");
        setContentView(R.layout.activity_main);
        findViews();
        setOnClickListeners();
        mButtonLogin.setVisibility(View.GONE);
        mButtonSignUp.setTextSize(20);
        mButtonSignUp.setWidth(30);
        Intent intent = getIntent();
        String username = intent.getStringExtra(LoginActivity.KEY_USER_NAME);
        String password = intent.getStringExtra(LoginActivity.KEY_PASSWORD);

        if(username !=null){
            mEditTextUserName.setText(String.valueOf(username));
        }
        if(password!=null){
            mEditTextPassword.setText(String.valueOf(password));
        }
    }

    private void findViews() {
        mEditTextUserName = findViewById(R.id.editTextUserName);
        mEditTextPassword = findViewById(R.id.editTextPassword);
        mButtonLogin = findViewById(R.id.buttonLogin);
        mButtonSignUp = findViewById(R.id.buttonSignUp);
    }

    private void setOnClickListeners() {
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("bashir","salam1");
                String userName = String.valueOf(mEditTextUserName.getText());
                String password = String.valueOf(mEditTextPassword.getText());
                Log.d("bashir","salam2");
                if(userName!=null || password!=null){
                    Log.d("bashir","salam3");
                    Intent intent = new Intent();
                    intent.putExtra(LoginActivity.KEY_USER_NAME,userName);
                    intent.putExtra(LoginActivity.KEY_PASSWORD,Integer.parseInt(password));
                    setResult(RESULT_OK,intent);
                    Log.d("bashir","salam4");
                    finish();
                }else{
                    Toast.makeText(SignUpActivity.this, "please fill request fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
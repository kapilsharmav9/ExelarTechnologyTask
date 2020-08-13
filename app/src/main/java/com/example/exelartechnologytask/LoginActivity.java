package com.example.exelartechnologytask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exelartechnologytask.Model.User;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText eEmail, ePassword;
    TextView txt_Signup;
    Button btn_login;
    public static ApiInterface apiInterface;
    String madd;
    String madd_form = "Android";
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        getMacAddress();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        if (eEmail.getText().toString().isEmpty() || ePassword.getText().toString().isEmpty()) {
            eEmail.setError("fill Email");
            eEmail.requestFocus();
            ePassword.setError("fill password");
            ePassword.requestFocus();

        } else {
            String email = eEmail.getText().toString();
            String password = ePassword.getText().toString();
            Call<User> call = LoginActivity.apiInterface.userLogin(email, password, madd, madd_form);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    public void txtSignUp(View view) {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));

    }

    private void initView() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        eEmail = findViewById(R.id.edt_EmailL);
        ePassword = findViewById(R.id.edit_passwordL);
        btn_login = findViewById(R.id.btnLoginL);
        txt_Signup = findViewById(R.id.signup_text);
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_transition_animation);
        btn_login.setAnimation(animation);

    }

    private void getMacAddress() {
        try {
            List<NetworkInterface> networkInterfacesList = Collections.list(NetworkInterface.getNetworkInterfaces());

            String mac = "";
            for (NetworkInterface networkInterface : networkInterfacesList) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    for (int i = 0; i < networkInterface.getHardwareAddress().length; i++) {
                        String macByte = Integer.toHexString(networkInterface.getHardwareAddress()[i] & 0xFF);
                        if (macByte.length() == 1) {
                            macByte = "0" + macByte;

                        }
                       mac = madd + macByte.toUpperCase() + ":";
                        madd = mac.substring(0, mac.length()-1);
                    }
                    break;

                }


            }

        } catch (SocketException e) {
            e.printStackTrace();
        }

    }
}
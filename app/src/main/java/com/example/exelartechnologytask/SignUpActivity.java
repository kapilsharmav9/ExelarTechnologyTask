package com.example.exelartechnologytask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    EditText eFirstName, eLastName, eMobileNumber, eAddress, eEmail, ePassord;
    Button btn_signup;
    TextView txt_login;
    ProgressDialog pd;
    public static ApiInterface apiInterface;
    String Fname, Lname, Address, Pasword, Email, Mobilenumber, mad;
    final String madd_from = "Android";
    String madd;
    String timezone ;
    String newsLetter = "0";
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
        getMacAddress();
        getTimeZone();

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();

            }
        });
    }

    private void registerUser() {
        if (eFirstName.getText().toString().isEmpty() || eLastName.getText().toString().isEmpty() || eAddress.getText().toString().isEmpty() || eMobileNumber.getText().toString().isEmpty() || ePassord.getText().toString().isEmpty() || eEmail.getText().toString().isEmpty()) {
            if (eFirstName.getText().toString().isEmpty()) {
                eFirstName.setError("please FisrtName");
                eFirstName.requestFocus();
            }
            if (eLastName.getText().toString().isEmpty()) {
                eLastName.setError("please LastName");
                eLastName.requestFocus();
            }
            if (eEmail.getText().toString().isEmpty()) {
                eEmail.setError("please Email ");
                eEmail.requestFocus();
            }
            if (eAddress.getText().toString().isEmpty()) {
                eAddress.setError("please Address");
                eAddress.requestFocus();
            }
            if (ePassord.getText().toString().isEmpty() || ePassord.getText().length() < 6) {
                ePassord.setError("Minimum Password length 6");
                ePassord.requestFocus();
            } if (eMobileNumber.getText().length() < 10 || eMobileNumber.getText().toString().isEmpty()) {
                eMobileNumber.setError("enter Valid Number");
                eMobileNumber.requestFocus();
            }


        } else {
            Fname = eFirstName.getText().toString();
            Lname = eLastName.getText().toString();
            Email = eEmail.getText().toString();
            Pasword = ePassord.getText().toString();
            Address = eAddress.getText().toString();
            Mobilenumber = "91"+eMobileNumber.getText().toString();
            Call<User> call = SignUpActivity.apiInterface.
                    userRegister(Fname, Lname, Email, Pasword, Mobilenumber, madd, madd_from,newsLetter,Address,timezone);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "Register SuccesFull", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(SignUpActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void logninTxt(View view) {
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
    }

    private void initView() {
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_transition_animation);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        eFirstName = findViewById(R.id.edt_FisrtName);
        eLastName = findViewById(R.id.edt_LastName);
        eMobileNumber = findViewById(R.id.edt_mobileNumber);
        eAddress = findViewById(R.id.edt_address);
        eEmail = findViewById(R.id.edtEmail_signup);
        ePassord = findViewById(R.id.editpassword_signup);
        btn_signup = findViewById(R.id.btn_signUp);
        txt_login = findViewById(R.id.login_text);
        btn_signup.setAnimation(animation);


    }

    private void getMacAddress() {
        try {
            List<NetworkInterface> networkInterfacesList = Collections.list(NetworkInterface.getNetworkInterfaces());

            String stringMac= "";
            for (NetworkInterface networkInterface : networkInterfacesList) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    for (int i = 0; i < networkInterface.getHardwareAddress().length; i++) {
                        String macByte = Integer.toHexString(networkInterface.getHardwareAddress()[i] & 0xFF);
                        if (macByte.length() == 1) {
                            macByte = "0" + macByte;

                        }
                        stringMac = stringMac + macByte.toUpperCase() + ":";


                    }
                    break;

                }


            }
            madd = stringMac.substring(0, mac.length() - 1);
             //for testing purpose// Toast.makeText(getApplicationContext(),""+madd,Toast.LENGTH_LONG).show();
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }
    private  void getTimeZone()
    {
        TimeZone tz=TimeZone.getDefault();
        timezone=tz.getDisplayName();

        Toast.makeText(this, ""+tz.getDisplayName(), Toast.LENGTH_SHORT).show();

    }
}

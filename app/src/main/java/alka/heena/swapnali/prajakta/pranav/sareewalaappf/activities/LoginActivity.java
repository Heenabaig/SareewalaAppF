package alka.heena.swapnali.prajakta.pranav.sareewalaappf.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import alka.heena.swapnali.prajakta.pranav.sareewalaappf.R;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.constants.Constant;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.extras.AppPreference;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.model.User;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.services.MyInterface;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.services.RetrofitClient;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.services.ServiceApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private EditText email;
    private EditText pass;
    private Button btnLogin;
    String emailpatt="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static ServiceApi serviceApi;
    private MyInterface loginFromActivityListener;
    public static AppPreference appPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        serviceApi = RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);
        email=findViewById(R.id.email_reg);
        pass=findViewById(R.id.password_reg);
        appPreference = new AppPreference(this);

        btnLogin=findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<User> call = serviceApi.doLogin(email.getText().toString(),pass.getText().toString());
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        if (response.body().getResponse().equals("data")) {
                            appPreference.setUserId(response.body().getUserId());
                            appPreference.setDisplayName(response.body().getName());
                            appPreference.setDisplayEmail(response.body().getEmail());
                            appPreference.setPhoneNO(response.body().getPhone());
                            appPreference.setLoginStatus(true);

                            Intent intent = new Intent(LoginActivity.this, BottomNavActivity.class);
                            startActivity(intent);
                        }else if (response.body().getResponse().equals("login_failed")){
                            Toast.makeText(LoginActivity.this,"Check you Credentials",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"Failed Try Again",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }
}

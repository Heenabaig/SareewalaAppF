package alka.heena.swapnali.prajakta.pranav.sareewalaappf;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import alka.heena.swapnali.prajakta.pranav.sareewalaappf.constants.Constant;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.services.MyInterface;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.services.RetrofitClient;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.services.ServiceApi;


public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Button btnLogin;
    String emailpatt="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static ServiceApi serviceApi;
    private MyInterface loginFromActivityListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        serviceApi = RetrofitClient.getApiClient(Constant.baseUrl.BASE_URL).create(ServiceApi.class);
        email=findViewById(R.id.email_reg);
        pass=findViewById(R.id.password_reg);

        btnLogin=findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, BottomNavActivity.class);
startActivity(intent);
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

            }

        });

    }

}

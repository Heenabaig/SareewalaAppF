package alka.heena.swapnali.prajakta.pranav.sareewalaappf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import alka.heena.swapnali.prajakta.pranav.sareewalaappf.extras.AppPreference;

public class MainActivity extends AppCompatActivity {
    Button btnLogin, btnSignUp;
    public static AppPreference appPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btn_LOGIN);
        btnSignUp = findViewById(R.id.btn_SIGNUP);
        appPreference = new AppPreference(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}

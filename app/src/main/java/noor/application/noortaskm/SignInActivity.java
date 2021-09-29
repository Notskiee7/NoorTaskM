package noor.application.noortaskm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class SignInActivity extends AppCompatActivity {
    private TextInputEditText ETEmail,ETPassword;
    private Button BTNSignUp,BTNSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ETEmail=findViewById(R.id.ETEmail);
        ETPassword=findViewById(R.id.ETPassword);
        BTNSignIn=findViewById(R.id.BTNSignIn);
        BTNSignUp=findViewById(R.id.BTNSignUp);
    }
}
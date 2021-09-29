package noor.application.noortaskm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {
    private TextInputEditText ETConfirm,ETEmail2,ETFirst,ETELast,ETEPassword2;
    private Button BTNRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ETConfirm=findViewById(R.id.ETConfirm);
        ETEmail2=findViewById(R.id.ETEmail2);
        ETFirst=findViewById(R.id.ETLast);
        ETELast=findViewById(R.id.ETLast);
        ETEPassword2=findViewById(R.id.ETPassword2);
        BTNRegister=findViewById(R.id.BTNRegister);
    }
}
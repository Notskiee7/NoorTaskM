package noor.application.noortaskm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

        BTNSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
            }
        });
        BTNSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }
    private void validate() {// تقوم بفحص الحقول وتعبئتها
        boolean isOk = true;
        String email = ETEmail.getText().toString();
        String password = ETPassword.getText().toString();
        if (email.length() == 0) {
            ETEmail.setError("enter email");
            isOk = false;
        }
        if (password.length() < 8) {
            ETPassword.setError("password should at least be 8 characters");
            isOk = false;
        }
        if (isOk)
        {
            signingIn(email, password);
        }
    }
private void signingIn(String email, String password)
{
    FirebaseAuth auth=FirebaseAuth.getInstance();
    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful())
            {
                Toast.makeText(getApplicationContext(), "signed in successfully", Toast.LENGTH_SHORT).show();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
            else
                Toast.makeText(getApplicationContext(), "signing in failed", Toast.LENGTH_SHORT).show();
        }
    });

    }
}

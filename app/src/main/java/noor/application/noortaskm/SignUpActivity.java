package noor.application.noortaskm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
        BTNRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();

            }
        });
    }

    private void validate() {
        boolean isOK=true;
        String email = ETEmail2.getText().toString();
        String password = ETEPassword2.getText().toString();
        String password2 = ETConfirm.getText().toString();
        String firstname = ETFirst.getText().toString();
        String lastname = ETELast.getText().toString();

        if(email.length()<5|| email.indexOf('@')<=0)
        {
            ETEmail2.setError("wrong email syntax");
            isOK=false;
        }
        if(password.length()<8)
        {
            ETEPassword2.setError("at least 8 chars");
            isOK=false;
        }
        if (password2.equals(password)==false)
        {
            ETConfirm.setError("password does not match");
            isOK=false;
        }
        if(firstname.length()==0)
        {
            ETFirst.setError("must enter full name");
            isOK=false;
        }
        if (isOK) {
            createAccount(email,password);
        }
        }

    private void createAccount(String email, String password) {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        //request                              //wait for response
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()==true)
                {
                    finish();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                else
                {
                    //dialog
                    Toast.makeText(getApplicationContext(), "error create account "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });

        }

    }


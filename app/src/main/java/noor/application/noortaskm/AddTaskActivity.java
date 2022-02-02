package noor.application.noortaskm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.errorprone.annotations.Var;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTaskActivity extends AppCompatActivity {
    private TextInputEditText ETTitle, ETSubject;
    private ImageButton IVTask;
    private Spinner SPNTask;
    private Button BTNTask;
    private SeekBar SBTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        IVTask = findViewById(R.id.IVTask);
        ETTitle = findViewById(R.id.ETTitle);
        ETSubject = findViewById(R.id.ETSubject);
        SPNTask = findViewById(R.id.SPNTask);
        BTNTask = findViewById(R.id.BTNTask);
        SBTask = findViewById(R.id.SBTask);
        BTNTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
       


    }

    private void validate() {
        String title = ETTitle.getText().toString();
        String subject = ETSubject.getText().toString();
        int progress = SBTask.getProgress();
        String importance = SPNTask.getSelectedItem().toString();
        boolean isOk = true;
        if (title.length() == 0) {
            ETTitle.setError("must enter subject");
            isOk = false;
        }
        if (isOk) {
            Mytask mytask = new Mytask();
            mytask.setTitle(title);
            mytask.setSubject(subject);
            mytask.setImportant(importance);

            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            mytask.setOwner(uid);

            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference ref = db.getReference();
            String key = ref.child("mytasks").push().getKey();

            mytask.setKey(key);

            ref.child("mytask").child(key).setValue(mytask).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Add successful", Toast.LENGTH_SHORT).show();
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), "Add not successful", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }
}    

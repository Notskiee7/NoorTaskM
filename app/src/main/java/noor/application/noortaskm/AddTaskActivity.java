package noor.application.noortaskm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

public class AddTaskActivity extends AppCompatActivity {
    private TextInputEditText ETTitle,ETSubject;
    private ImageButton IVTask;
    private Spinner SPNTask;
    private Button BTNTask;
    private SeekBar SBTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        IVTask=findViewById(R.id.IVTask);
        ETTitle=findViewById(R.id.ETTitle);
        ETSubject=findViewById(R.id.ETSubject);
        SPNTask=findViewById(R.id.SPNTask);
        BTNTask=findViewById(R.id.BTNTask);
        SBTask=findViewById(R.id.SBTask);
    }
}
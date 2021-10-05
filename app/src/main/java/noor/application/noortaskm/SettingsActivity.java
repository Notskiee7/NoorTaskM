package noor.application.noortaskm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SettingsActivity extends AppCompatActivity {
    private TextView TVsettings,TVended;
    private Button BTNsettings,BTNclear;
    private ToggleButton TBsinged;
    private Switch SWhistory;
    private RadioGroup RGsettings;
    private RadioButton RBdelete;
    private RadioButton RBmore;
    private RadioButton RBcross;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        TVsettings=findViewById(R.id.TVsettings);
        BTNsettings=findViewById(R.id.BTNsettings);
        BTNclear=findViewById(R.id.BTNclear);
        TBsinged=findViewById(R.id.TBhsigned);
        RGsettings=findViewById(R.id.RGsettings);
        RBdelete=findViewById(R.id.RBdelete);
        RBmore=findViewById(R.id.RBmore);
        RBcross=findViewById(R.id.RBcross);
    }
}
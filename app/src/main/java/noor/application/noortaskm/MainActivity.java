package noor.application.noortaskm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton FABmain;
    private SearchView SVTask;
    private ListView LVMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FABmain=findViewById(R.id.FABmain);
        SVTask=findViewById(R.id.SVTask);
        LVMain=findViewById(R.id.LVMain);
    }
}
package noor.application.noortaskm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

public class HistoryActivity extends AppCompatActivity {
    private SearchView SVHistory;
    private ListView LThistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        SVHistory=findViewById(R.id.SVHistory);
        LThistory=findViewById(R.id.LThistory);
    }
}
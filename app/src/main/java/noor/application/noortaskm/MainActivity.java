package noor.application.noortaskm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton FABmain;
    private SearchView SVTask;
    private ListView LVMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FABmain = findViewById(R.id.FABmain);
        SVTask = findViewById(R.id.SVTask);
        LVMain = findViewById(R.id.LVMain);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itmHistory) {
            Intent i=new Intent(getApplicationContext(), HistoryActivity.class);
            startActivity(i);

        }
        if (item.getItemId() == R.id.itmSettings) {
            Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(i);
        }
        if (item.getItemId() == R.id.itmSignOut) {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            //
            builder.setMessage("Are you sure");
            //
            builder.setCancelable(false);

            builder.setPositiveButton("YES",  this);
            //
            builder.setNegativeButton("NO",  this);
            //
            AlertDialog dialog=builder.create();
            //
            dialog.show();

        }
        return true;
    }
}


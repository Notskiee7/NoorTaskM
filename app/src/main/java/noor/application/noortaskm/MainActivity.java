package noor.application.noortaskm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import MyData.MyTaskAdapter;
import MyData.Mytask;

public class MainActivity extends AppCompatActivity implements DialogInterface.OnClickListener {
    private FloatingActionButton FABmain;
    private SearchView SVTask;
    private ListView LVMain;
    private MyTaskAdapter taskAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FABmain = findViewById(R.id.FABmain);
        FABmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddTaskActivity.class));
            }
        });
        SVTask = findViewById(R.id.SVTask);
        LVMain = findViewById(R.id.LVMain);
        taskAdapter=new MyTaskAdapter(this,R.layout.task_item_layout);
        //read 3:set adapter to Listview(connect the data to List view)
        LVMain.setAdapter(taskAdapter);
    }
    //read 4:

    protected void onResume() {
        super.onResume();;
        readTasksFromFireBase("");
    }
    //read5:

    /**
     * read tasks from fire base and fill  the adapter data structure
     * s- is text to speech, if it is empty the method show all results
     * @param s
     */

    private void readTasksFromFireBase(String s) {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
        String uid=FirebaseAuth.getInstance().getUid();//current user id.
        //إضافة إمكانية "التحتلن" بكل تغيير سيحصل على القيم في قاعدة البيانات
        ref.child("mytasks").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                taskAdapter.clear();
                for (DataSnapshot d:snapshot.getChildren())
                {
                    Mytask t=d.getValue(Mytask.class);
                    taskAdapter.add(t);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itmHistory) {
            Intent i = new Intent(getApplicationContext(), HistoryActivity.class);
            startActivity(i);

        }
        if (item.getItemId() == R.id.itmSettings) {
            Intent i = new Intent(getApplicationContext(), HistoryActivity.class);
            startActivity(i);
        }
        if (item.getItemId() == R.id.itmSignOut) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure");
            builder.setCancelable(true);
            //listener 3. add Listener to the buttons
            builder.setPositiveButton("YES", this);
            builder.setNegativeButton("NO", this);
            AlertDialog dialog = builder.create();
            dialog.show();

        }
        return true;
    }

    public void onClick(DialogInterface dialogInterface, int which) {//Listener 4. react for each action
        if (which == dialogInterface.BUTTON_POSITIVE) {
            Toast.makeText(getApplicationContext(), "Loging out", Toast.LENGTH_SHORT).show();
            dialogInterface.cancel();
            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.signOut();
            finish();//to close current activity
        }


        if (which == dialogInterface.BUTTON_NEGATIVE) {
            Toast.makeText(getApplicationContext(), "Loging out canceled", Toast.LENGTH_SHORT).show();
            dialogInterface.cancel();
        }
    }
}


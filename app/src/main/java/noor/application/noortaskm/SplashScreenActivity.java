package noor.application.noortaskm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class SplashScreenActivity extends AppCompatActivity {
    private ImageView IVSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        IVSplash=findViewById(R.id.IVSplash);
// thread:1
        Thread th=new Thread(){
            //thread:2
            @Override
            public void run() {
                //هنا المقطع الذي سيعمل بالتزامن مع المقاطع
                //thread:3
                int ms=3*1000;
                try {
                    sleep(ms);
                    finish();
                    //فحص هل تم الدخول مسبقًا
                    FirebaseAuth auth=FirebaseAuth.getInstance();
                    if (auth.getCurrentUser()!=null)
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    else
                        startActivity(new Intent(getApplicationContext(),SignInActivity.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        //thread:4
        th.start();

    }
}
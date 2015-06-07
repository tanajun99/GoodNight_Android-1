package l.appprino.com.goodnight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import l.appprino.com.goodnight.OAuth.OAuthClient;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Button register =(Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });

    }

    void Register() {
        try {
            OAuthClient.RequestUber(this);
        } catch (Exception e) {
            Log.d("Detail:",e.toString());
        }
    }
}

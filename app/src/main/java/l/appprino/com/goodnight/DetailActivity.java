package l.appprino.com.goodnight;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import l.appprino.com.goodnight.OAuth.AsyncRequest;
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
        AsyncRequest task = new AsyncRequest(this);
        task.execute();
    }

    public void ShowDialogAndSendIntente() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // アラートダイアログのタイトルを設定します
        alertDialogBuilder.setTitle("ホテル予約+Uber");
        // アラートダイアログのメッセージを設定します
        alertDialogBuilder.setMessage("あと12分でUbeが到着致します");
        // アラートダイアログの肯定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
        alertDialogBuilder.setPositiveButton("確認",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SendIntent();
                    }
                });

        // アラートダイアログのキャンセルが可能かどうかを設定します
        alertDialogBuilder.setCancelable(true);
        AlertDialog alertDialog = alertDialogBuilder.create();
        // アラートダイアログを表示します
        alertDialog.show();
    }

    void SendIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

package l.appprino.com.goodnight.OAuth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import java.io.IOException;

import l.appprino.com.goodnight.R;

public class LoginUber extends ActionBarActivity {
    private static final String TAG = "LoginUber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_uber);
        Button oAuth = (Button) findViewById(R.id.oauthButton);
        oAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLoginButton();
            }
        });

    }

    public void onClickLoginButton() {
        // ブラウザで4sqへの認証画面を呼び出す
        OAuthClient.login(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login_uber, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * foursquareからのリダイレクトを受けてコールバックされる処理
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Uri uri = intent.getData();
        if (uri == null) {
            return;
        }
        Log.d(TAG,uri.getPath());
        Log.d(TAG, uri.getEncodedPath());
        String code = uri.getQueryParameter("code");
        if (code == null) {
            showToast("エラー");
            return;
        }
        // メインスレッドでネットワーク通信したら例外発生したのでAsyncTaskで対応
        new TokenGetTask(this).execute(code);
    }

    private class TokenGetTask extends AsyncTask<String, Void, String> {

        private ProgressDialog mProgressDialog;

        public TokenGetTask(Context context) {
            mProgressDialog = new ProgressDialog(context);
        }

        @Override
        protected String doInBackground(String... codes) {
            String token = "";
            String code = codes[0];
            try {
                // アクセストークンを取得
                token = OAuthClient.getTokenFromAuthorizationCode(code);
            } catch (ClientProtocolException e) {
                Log.e(TAG, e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
            }
            return token;
        }

        @Override
        protected void onPostExecute(String result) {
            if ("".equals(result)) {
                // エラーでトークンの取得に失敗
                showToast("エラー");
            } else {
                // トークンをPreferencesに保存
                OAuthTokenStore store = new OAuthTokenStore(getApplicationContext());
                store.updateAccessToken(result);
                showToast("アクセストークンを保存しました");
                mProgressDialog.dismiss();
                // アクセストークンを求めた最初のActivityを起動
                startApp();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.setMessage("weeeeeey");
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.show();
        }
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private void startApp() {
        Intent i = new Intent(this, TesetHttp.class);
        startActivity(i);
        finish();
    }
}


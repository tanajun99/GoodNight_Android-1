package l.appprino.com.goodnight.OAuth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import l.appprino.com.goodnight.R;

/**
 * Created by shirakawayoshimaru on 15/06/07.
 */
public class TesetHttp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("HTML", "START");
        setContentView(R.layout.activity_login);

        // Preferencesからアクセストークンを取得
        OAuthTokenStore store = new OAuthTokenStore(getApplicationContext());
        String accessToken = store.getToken();
        if (accessToken == null) {
            // アクセストークンがない場合の処理
//             foursquareへの認証をさせるためのActivityへ移動
            Intent i = new Intent(this, LoginUber.class);
            startActivity(i);
            finish();

        } else {
            // アクセストークンがある場合の処理
            try {
                OAuthClient.RequestUber(this);
            }catch (Exception e) {
                Log.d("TesetHttp",e.toString());
            }

        }



//        UberOAuth oAuth =new UberOAuth();
//        oAuth.authorize();
//
//        WebView webView = (WebView)findViewById(R.id.web_view);
//        webView.loadUrl(oAuth.URL_AUTHORIZE);
    }

    public void Test() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpClient client = new DefaultHttpClient();

                // パラメータの設定
                ArrayList<NameValuePair> value = new ArrayList<NameValuePair>();
                value.add(new BasicNameValuePair("server_token", "u1_Czj8E0L5Py6FUNVH1OZShbO1FC26Hxitk_8zc"));
                value.add(new BasicNameValuePair("latitude", "37.775818"));
                value.add(new BasicNameValuePair("longitude", "-122.418028"));

                String responseData = null;
                String URL ="https://api.uber.com/v1/products";
                try {
                    String query = URLEncodedUtils.format(value, "UTF-8");
                    HttpGet get = new HttpGet(URL + "?" + query);

                    // リクエスト送信
                    HttpResponse response = client.execute(get);
                    // 取得
                    HttpEntity entity = response.getEntity();
                    responseData = EntityUtils.toString(entity, "UTF-8");
                    Log.d("HTML",responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }



}

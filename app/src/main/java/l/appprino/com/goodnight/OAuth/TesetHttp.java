package l.appprino.com.goodnight.OAuth;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

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

/**
 * Created by shirakawayoshimaru on 15/06/07.
 */
public class TesetHttp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("HTML", "START");
        Test();

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

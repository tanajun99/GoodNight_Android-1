package l.appprino.com.goodnight.OAuth;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by shirakawayoshimaru on 15/06/07.
 */
public class TesetHttp extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Test();

    }

    public void Test() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpGet httpGet = new HttpGet("https://www.google.com/");
                    HttpResponse httpResponse = httpClient.execute(httpGet);
                    String str = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                    Log.d("HTTP", str);
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }).start();

    }
}

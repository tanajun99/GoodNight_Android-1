package l.appprino.com.goodnight.OAuth;

import android.util.Log;
import android.webkit.WebView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by shirakawayoshimaru on 15/06/07.
 */
public class UberOAuth {
    public static final  String CLIENT_ID ="x0151lvnhfv-V7dKInyZPcN3chFTTkq0";
//    public static final  String URL_AUTHORIZE ="https://login.uber.com/oauth/authorize";

    public static final  String URL_AUTHORIZE = "https://login.uber.com/oauth/authorize?response_type=code&client_id=x0151lvnhfv-V7dKInyZPcN3chFTTkq0";
    public void authorize(WebView webView) {
        webView.loadUrl(URL_AUTHORIZE);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                HttpClient client = new DefaultHttpClient();
//
//                // パラメータの設定
//                ArrayList<NameValuePair> value = new ArrayList<NameValuePair>();
//                value.add(new BasicNameValuePair("response_type", "code"));
//                value.add(new BasicNameValuePair("client_id", CLIENT_ID));
//
//
//                String responseData = null;
//
//                try {
//                    String query = URLEncodedUtils.format(value, "UTF-8");
//                    HttpGet get = new HttpGet(URL_AUTHORIZE + "?" + query);
//
//                    // リクエスト送信
//                    HttpResponse response = client.execute(get);
//                    // 取得
//                    HttpEntity entity = response.getEntity();
//                    responseData = EntityUtils.toString(entity, "UTF-8");
//                    Log.d("HTML", responseData);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

    }
}

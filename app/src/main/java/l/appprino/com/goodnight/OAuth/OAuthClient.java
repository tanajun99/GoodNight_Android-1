package l.appprino.com.goodnight.OAuth;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;


import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by shirakawayoshimaru on 15/06/07.
 */
public class OAuthClient {
    private static final String TAG = "OAuthClient";

    private static final String AUTHORIZE_URL = "https://login.uber.com/oauth/authorize";

    private static final String ACCESS_TOKEN_URL = "https://login.uber.com/oauth/token";

    private static final String CALLBACK_URL = "oauthsample://callback";

    private static final String CLIENT_ID = "YbIHVTi12H1Q7dEb0o2y-9ljS-HmYP0z";

    private static final String CLIENT_SECRET = "bRHzv-IQa5zfb840VKtPwI0ub8IFChI43aUFZ0Vr";

    private static final String REQUEST_URL ="https://sandbox-api.uber.com/v1/requests";



    /**
     * ブラウザを起動し、認証を行うURLへ遷移する。
     *
     * @param context 呼び出し元のContext
     */
    public static void login(Context context) {
        Uri uri = Uri.parse(AUTHORIZE_URL).buildUpon()
                .appendQueryParameter("client_id", CLIENT_ID)
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("redirect_uri", CALLBACK_URL)
                .build();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(uri);
        context.startActivity(i);
    }


    public static void RequestUber(Context context) throws IOException {

        String accessToken =new OAuthTokenStore(context).getToken();
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(REQUEST_URL);
        httppost.setHeader("Authorization: Bearer", accessToken);
        httppost.addHeader("Authorization: Bearer", accessToken);
        Log.d("DEBUG", "HEADERS: " + httppost.getFirstHeader("Authorization: Bearer"));

        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("start_latitude", "37.334381"));
        params.add(new BasicNameValuePair("start_longitude", "-121.89432"));
        params.add(new BasicNameValuePair("end_latitude", "37.77703"));
        params.add(new BasicNameValuePair("end_longitude", "-122.419571"));
        params.add(new BasicNameValuePair("product_id", "a1111c8c-c720-46c3-8534-2fcdd730040d"));

        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = httpclient.execute(httppost , responseHandler);


        Log.d("DEBUG", "RESPONSE: " + responseBody);
    }

    /**
     * Authorization Code からアクセストークンを取得します。
     * エラーだった場合は{@code null}を返します。
     *
     * @param code Authorization Code
     * @return 取得したアクセストークンを返します。
     * @throws ClientProtocolException
     * @throws IOException
     * @throws JSONException
     */
    public static String getTokenFromAuthorizationCode(String code) throws ClientProtocolException, IOException, JSONException {
        Log.d(TAG, "code:" + code);
        String token = null;
        DefaultHttpClient client = new DefaultHttpClient();
//        Uri uri = Uri.parse(ACCESS_TOKEN_URL).buildUpon()
//                .appendQueryParameter("client_id", CLIENT_ID)
//                .appendQueryParameter("client_secret", CLIENT_SECRET)
//                .appendQueryParameter("grant_type", "authorization_code")
//                .appendQueryParameter("redirect_uri", CALLBACK_URL)
//                .appendQueryParameter("code", code)
//                .build();
//        Log.d(TAG,uri.toString());
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("client_id", CLIENT_ID));
        params.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
        params.add(new BasicNameValuePair("redirect_uri", CALLBACK_URL));
        params.add(new BasicNameValuePair("code", code));
//        HttpGet httpGet = new HttpGet(uri.toString());
        HttpPost httpPost = new HttpPost(ACCESS_TOKEN_URL);
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        HttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String res = EntityUtils.toString(entity);
        Log.d(TAG, "res:" + res);
        entity.consumeContent();
        JSONObject json = new JSONObject(res);
        if (json.has("access_token")) {
            token = json.getString("access_token");
            Log.d(TAG, "token:" + token);
        } else {
            if (json.has("error")) {
                String error = json.getString("error");
                Log.d(TAG, "error:" + error);
            }
        }
        client.getConnectionManager().shutdown();
        return token;
    }
}

package l.appprino.com.goodnight.Utility;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Created by shirakawayoshimaru on 15/06/07.
 */

public class AsyncJsonLoader extends AsyncTask<String, Integer, JSONObject> {
    public interface AsyncCallback {
        void preExecute();
        void postExecute(JSONObject result);
        void progressUpdate(int progress);
        void cancel();
    }

    private AsyncCallback mAsyncCallback = null;

    public AsyncJsonLoader(AsyncCallback _asyncCallback) {
        mAsyncCallback = _asyncCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mAsyncCallback.preExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... _progress) {
        super.onProgressUpdate(_progress);
        mAsyncCallback.progressUpdate(_progress[0]);
    }

    @Override
    protected void onPostExecute(JSONObject _result) {
        super.onPostExecute(_result);
        mAsyncCallback.postExecute(_result);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        mAsyncCallback.cancel();
    }

    @Override
    protected JSONObject doInBackground(String... _uri) {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(_uri[0]);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                httpResponse.getEntity().writeTo(outputStream);
                outputStream.close();
                String jsonStr =outputStream.toString();
                Log.d("hoge:", jsonStr);
                 jsonStr = "[{\"id\":4,\"name\":\"ANAインターコンチネンタルホテル東京\",\"area\":{\"id\":2,\"name\":\"赤坂・虎ノ門・四谷・半蔵門\",\"created_at\":\"2015-06-06T21:10:51.550Z\",\"updated_at\":\"2015-06-06T21:10:51.550Z\"},\"image\":{\"image\":{\"url\":\"http://res.cloudinary.com/hnr40olpc/image/upload/v1433629931/blsc2oz5ofucjm65qirr.jpg\",\"medium\":{\"url\":\"http://res.cloudinary.com/hnr40olpc/image/upload/c_fill,g_north,h_480,w_640/v1433629931/blsc2oz5ofucjm65qirr.jpg\"},\"thumbnail\":{\"url\":\"http://res.cloudinary.com/hnr40olpc/image/upload/c_fill,h_48,w_48/v1433629931/blsc2oz5ofucjm65qirr.jpg\"}}},\"latitude\":35.6682304,\"longitude\":139.7410887,\"url\":\"https://goodnight.herokuapp.com/hotels/4.json”}]";

                ObjectMapper mapper = new ObjectMapper();

                JsonNode node = mapper.readTree(new URL("https://goodnight.herokuapp.com/hotels.json"));
//                JsonNode node =mapper.readTree(_uri[0]);
                Log.d("hoge:","node:read");
                Log.d("hoge:","node:"+node.get(0).toString());
                Hoge hoge = mapper.readValue(jsonStr, Hoge.class);
                Log.d("hoge:","hoge:"+hoge.toString());
                JSONObject json = new JSONObject(jsonStr);
                Log.d("hoge:","json1:"+json.toString());
                return json;
            } else {
                httpResponse.getEntity().getContent().close();
                throw new IOException();
            }
        } catch (IOException e) {
            Log.d("hoge:","parseError:"+e.toString());
            e.printStackTrace();
        } catch (JSONException e) {
            Log.d("hoge:","parseError:"+e.toString());
            e.printStackTrace();
        }
        return null;
    }
}
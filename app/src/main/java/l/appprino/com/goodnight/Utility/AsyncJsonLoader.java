package l.appprino.com.goodnight.Utility;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

/**
 * Created by shirakawayoshimaru on 15/06/07.
 */

public class AsyncJsonLoader extends AsyncTask<String, Integer, JsonNode> {
    public interface AsyncCallback {
        void preExecute();
        void postExecute(JsonNode result);
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
    protected void onPostExecute(JsonNode _result) {
        super.onPostExecute(_result);
        mAsyncCallback.postExecute(_result);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        mAsyncCallback.cancel();
    }

    @Override
    protected JsonNode doInBackground(String... _uri) {

        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonNode node = mapper.readTree(new URL(_uri[0]));
            return node;
        } catch (IOException e) {
            Log.d("hoge:","parseError:"+e.toString());
            e.printStackTrace();
        } catch (Exception e) {
            Log.d("hoge:","parseError:"+e.toString());
            e.printStackTrace();
        }
        return null;
    }
}
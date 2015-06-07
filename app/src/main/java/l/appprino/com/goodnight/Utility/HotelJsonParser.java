package l.appprino.com.goodnight.Utility;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by shirakawayoshimaru on 15/06/07.
 */
public class HotelJsonParser implements AsyncJsonLoader.AsyncCallback {
    @Override
    public void preExecute() {

    }

    @Override
    public void postExecute(JSONObject result) {
        try {
            JSONArray jsonList = result.getJSONArray("");
            for (int i = 0; i < jsonList.length(); i++) {
            Log.d("hoge:","name:"+jsonList.getJSONObject(i).getString("name"));
            }

        } catch (Exception e) {
            Log.d("hoge:",e.toString());
        }


    }

    @Override
    public void progressUpdate(int progress) {

    }

    @Override
    public void cancel() {

    }
}

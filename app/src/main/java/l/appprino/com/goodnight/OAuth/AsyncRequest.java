package l.appprino.com.goodnight.OAuth;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import l.appprino.com.goodnight.DetailActivity;
import l.appprino.com.goodnight.MainActivity;

/**
 * Created by shirakawayoshimaru on 15/06/07.
 */
public class AsyncRequest extends AsyncTask<Uri.Builder, Void, String> {

    private DetailActivity mainActivity;

    public AsyncRequest(DetailActivity activity) {

        // 呼び出し元のアクティビティ
        this.mainActivity = activity;
    }

    // このメソッドは必ずオーバーライドする必要があるよ
    // ここが非同期で処理される部分みたいたぶん。
    @Override
    protected String doInBackground(Uri.Builder... builder) {
        // httpリクエスト投げる処理を書く。
        try {
            OAuthClient.RequestUber(mainActivity);
        } catch (Exception e) {
            Log.d("Detail:", e.toString());
        }
        return null;
    }


    // このメソッドは非同期処理の終わった後に呼び出されます
    @Override
    protected void onPostExecute(String result) {
        // 取得した結果をテキストビューに入れちゃったり
        mainActivity.ShowDialogAndSendIntente();

    }
}
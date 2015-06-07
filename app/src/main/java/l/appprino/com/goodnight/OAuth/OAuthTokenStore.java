package l.appprino.com.goodnight.OAuth;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by shirakawayoshimaru on 15/06/07.
 */
public class OAuthTokenStore {

    private final Context mContext;
    private String mToken;

    public OAuthTokenStore(Context context) {
        mContext = context.getApplicationContext();
        mToken = readTokenFromSharedPreferences();
    }

    public String getToken() {
        return mToken;
    }

    public void updateAccessToken(String accessToken) {
        // エラーの場合を確認をしやすくするために、保存されたトークンを
        // 消せるようにしてある。
        // アプリが完成したらコメントアウト解除
//        if (accessToken == null) {
//            throw new NullPointerException("access token must not be null");
//        }
        mToken = accessToken;
        saveToken();
    }

    private void saveToken() {
        SharedPreferences pref = getPreferences();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("accessToken", mToken);
        editor.commit();
    }

    private String readTokenFromSharedPreferences() {
        SharedPreferences pref = getPreferences();
        return pref.getString("accessToken", null);
    }

    private SharedPreferences getPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }
}
package l.appprino.com.goodnight;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.google.android.gms.maps.SupportMapFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import l.appprino.com.goodnight.Utility.AsyncJsonLoader;

public class MainActivity extends ActionBarActivity {

    private MaterialViewPager mViewPager;

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //jsonTest
        LoadJson("https://goodnight.herokuapp.com/hotels.json");
        //jsonTest end

        setTitle("");

        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        getSupportActionBar().hide();

        toolbar = mViewPager.getToolbar();
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (toolbar != null) {
            //setSupportActionBar(toolbar);

            final ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);
            }
        }

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, 0, 0);
        mDrawer.setDrawerListener(mDrawerToggle);

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            int oldPosition = 0;

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return HotelRecyclerViewFragment.newInstance();
                    case 1:
                        return ScrollFragment.newInstance();
                    default:
                        return HotelRecyclerViewFragment.newInstance();
                }
            }



            @Override
            public void setPrimaryItem(ViewGroup container, int position, Object object) {
                super.setPrimaryItem(container, position, object);

                //only if position changed
                if(position == oldPosition)
                    return;
                oldPosition = position;

                int color = 0;
                String imageUrl = "";
                switch (position){
                    case 0:
                        imageUrl = "https://d1a3f4spazzrp4.cloudfront.net/web-fresh/home/heros/home-hero-5-1440-900.jpg";
                        color = getResources().getColor(R.color.blue);
                        break;
                    case 1:
                        imageUrl = "http://webeater.fr/wp-content/uploads/Uber-illustration-2.jpg";
                        color = getResources().getColor(R.color.blue);
                        break;
                }

                final int fadeDuration = 400;
                mViewPager.setImageUrl(imageUrl,fadeDuration);
                mViewPager.setColor(color,fadeDuration);
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position){
                    case 0:
                        return "LIST";
                    case 1:
                        return "MAP";
                }
                return "";
            }
        });
        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        mViewPager.getViewPager().setCurrentItem(0);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void LoadJson(String uri ){
        AsyncJsonLoader asyncJsonLoader = new AsyncJsonLoader(new AsyncJsonLoader.AsyncCallback() {
            // 実行前
            public void preExecute() {
            }
            // 実行後
            public void postExecute(JSONObject result) {
                if (result == null) {
                    //error
                    Log.d("hoge:","result Nulだよ！");
                    return;
                }
                try {
                    JSONArray jsonList = result.getJSONArray("");
                    for (int i = 0; i < jsonList.length(); i++) {
                        Log.d("hoge:", "name:" + jsonList.getJSONObject(i).getString("name"));
                    }
                    Log.d("hoge:","try中だよ");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("hoge:","tryerror:"+e.toString());
                }
            }
            // 実行中
            public void progressUpdate(int progress) {
            }
            // キャンセル
            public void cancel() {
            }
        });
        // 処理を実行
        asyncJsonLoader.execute(uri);
    }
}

package l.appprino.com.goodnight;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

public class ScrollFragment extends Fragment {

    private ObservableScrollView mScrollView;
    MapView mMapFragment;
    GoogleMap mMap;

    public static Fragment newInstance() {
        return new ScrollFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scroll, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mScrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);

        mMapFragment = (MapView)view.findViewById(R.id.map);
        mMapFragment.onCreate(savedInstanceState);

        mMap = mMapFragment.getMap();
        mMap.setMyLocationEnabled(true);

        MaterialViewPagerHelper.registerScrollView(getActivity(), mScrollView, null);
    }

    @Override
    public void onResume() {
        mMapFragment.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapFragment.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapFragment.onLowMemory();
    }
}

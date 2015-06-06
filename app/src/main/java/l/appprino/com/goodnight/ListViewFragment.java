package l.appprino.com.goodnight;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.github.florent37.materialviewpager.MaterialViewPagerHelper;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class ListViewFragment extends Fragment {

    private ObservableListView mListView;
    private ListAdapter mAdapter;

    private List<Object> mContentItems = new ArrayList<>();

    public static ListViewFragment newInstance() {
        return new ListViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView = (ObservableListView) view.findViewById(R.id.listView);

        for (int i = 0; i < 100; ++i)
            mContentItems.add(new Object());

//        mAdapter = new ListViewMaterialAdapter(new TestListViewAdapter(getActivity(),mContentItems));
//        mListView.setAdapter(mAdapter);
//
//        MaterialViewPagerHelper.registerListView(getActivity(), mListView, null);
    }
}

package l.appprino.com.goodnight;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import l.appprino.com.goodnight.Utility.HotelItem;


public class HotelRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<HotelItem> contents;
    TextView mHotelName;
    TextView mHotelErea;
    TextView mHotelStatus;
    ImageView mHotelImage;

    static final int TYPE_CELL = 1;

    public HotelRecyclerViewAdapter(List<HotelItem> contents) {
        this.contents = contents;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_CELL;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
                mHotelName = (TextView)view.findViewById(R.id.shop_name);
                mHotelErea = (TextView)view.findViewById(R.id.hotel_erea);
                mHotelStatus = (TextView)view.findViewById(R.id.shop_address_list);
                mHotelImage = (ImageView)view.findViewById(R.id.hotel_image);
                mHotelName.setText(contents.get(0).HotelName);
                mHotelErea.setText(contents.get(0).HotelAria);
                mHotelStatus.setText("空きあり");

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(),DetailActivity.class);

                        v.getContext().startActivity(intent);
                    }
                });

                return new RecyclerView.ViewHolder(view) {
                };
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_CELL:
                break;
        }
    }
}
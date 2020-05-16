package alka.heena.swapnali.prajakta.pranav.sareewalaappf.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import alka.heena.swapnali.prajakta.pranav.sareewalaappf.Preference;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.R;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {

    Context mContext;
    ArrayList<HashMap<String, String>> mArray;


    public HomeCategoryAdapter(Context cxt, ArrayList<HashMap<String, String>> mArray){
        this.mContext = cxt;
        this.mArray = mArray;

    }


    public  static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgBanner;
        TextView txtcaption;
        public ViewHolder(View v){
            super(v);
            imgBanner = (ImageView) v.findViewById(R.id.imgBanner);
            // txtTitle = (TextView) v.findViewById(R.id.title);
            txtcaption = (TextView) v.findViewById(R.id.txtTitle);


        }

        @Override
        public void onClick(View v) {
            v.setOnClickListener(this);
            imgBanner = (ImageView) v;
        }
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final HashMap<String,String> map = mArray.get(position);


        // Glide.with(mContext).load(map.get("url")).into(holder.imgBanner);
        //  Picasso.get().load(map.get("url")).into(holder.imgBanner);
        Glide.with(mContext).load(map.get("url")).into(holder.imgBanner);
        holder.txtcaption.setText(map.get("detail"));
        HomeFragment.caption1=map.get("detail");

        if (map.get("categoryid").equals("1")){
            if (Preference.jump == 0) {
                Preference.categoryid = map.get("categoryid");
                Preference.categoryname = map.get("detail");
            }
        }

        holder.imgBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preference.categoryid = map.get("categoryid");
                Preference.categoryname = map.get("detail");
                Preference.jump = 1;

            }
        });


    }

    @Override
    public int getItemCount()
    {
        return mArray.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_category, parent, false);
        ViewHolder vh = new ViewHolder(mRowView);

        return vh;
    }


}

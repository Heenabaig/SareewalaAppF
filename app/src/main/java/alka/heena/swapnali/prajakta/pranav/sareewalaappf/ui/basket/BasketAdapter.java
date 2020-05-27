package alka.heena.swapnali.prajakta.pranav.sareewalaappf.ui.basket;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import alka.heena.swapnali.prajakta.pranav.sareewalaappf.Api;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static alka.heena.swapnali.prajakta.pranav.sareewalaappf.activities.BottomNavActivity.cartarrayList;
import static alka.heena.swapnali.prajakta.pranav.sareewalaappf.activities.BottomNavActivity.ta;
import static alka.heena.swapnali.prajakta.pranav.sareewalaappf.activities.BottomNavActivity.tda;
import static alka.heena.swapnali.prajakta.pranav.sareewalaappf.activities.BottomNavActivity.totalamt;
import static alka.heena.swapnali.prajakta.pranav.sareewalaappf.activities.BottomNavActivity.totaldisamt;
import static alka.heena.swapnali.prajakta.pranav.sareewalaappf.ui.basket.BasketFragment.cartlist;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {
    Context mContext;
    FragmentManager fragmentManager;
    Fragment f;
    ArrayList<HashMap<String, String>> mArray;

    public BasketAdapter(Context cxt, FragmentManager targetFragment, Fragment fragment, ArrayList<HashMap<String, String>> array) {
        this.mContext = cxt;
        this.fragmentManager = targetFragment;
        this.f = fragment;
        this.mArray = array;

    }
    public  static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgBanner;
        TextView  discountedprice,sareename,sareecartprice,quantity;
        Button remove,add,rem;


        public ViewHolder(View v){
            super(v);
            imgBanner = (ImageView) v.findViewById(R.id.sareecartimage);
            discountedprice = (TextView) v.findViewById(R.id.sareediscountcartprice);
            sareename = (TextView) v.findViewById(R.id.sareecarttitle);
            sareecartprice = (TextView) v.findViewById(R.id.sareecartprice);
            remove = (Button) v.findViewById(R.id.removec);
            add = (Button) v.findViewById(R.id.addp);
            rem = (Button) v.findViewById(R.id.removep);
            quantity = (TextView) v.findViewById(R.id.quantityp);




        }

        @Override
        public void onClick(View v) {
            v.setOnClickListener(this);
            imgBanner = (ImageView) v;
        }
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final HashMap<String,String> map = mArray.get(position);

        Glide.with(mContext).load(map.get("url")).into(holder.imgBanner);
        holder.sareename.setText(map.get("name"));
        holder.sareecartprice.setText(map.get("orgprice"));
        holder.sareecartprice.setPaintFlags(holder.sareecartprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.discountedprice.setText(map.get("disprice"));
        holder.quantity.setText(map.get("quantity"));




        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Api service = retrofit.create(Api.class);
                Call<Remove> call = service.removeCart(Integer.parseInt(map.get("cartid")));
                call.enqueue(new Callback<Remove>() {
                    @Override
                    public void onResponse(Call<Remove> call, Response<Remove> response) {
                        if (response.body().getResponse().equals("removed")){
                            Toast.makeText(mContext,"Removed Successfully",Toast.LENGTH_SHORT).show();
                        }else if (response.body().getResponse().equals("error")){
                            Toast.makeText(mContext,"Try Again Later",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Remove> call, Throwable t) {

                    }
                });

                fragmentManager.beginTransaction().detach(f).attach(f).commit();
            }
        });

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api service = retrofit.create(Api.class);
                Call<CartPojo> call = service.updatecart(Integer.parseInt(map.get("cartid")),Integer.parseInt(map.get("quantity"))+1);
                call.enqueue(new Callback<CartPojo>() {
                    @Override
                    public void onResponse(Call<CartPojo> call, Response<CartPojo> response) {

                    }

                    @Override
                    public void onFailure(Call<CartPojo> call, Throwable t) {

                    }
                });


                fragmentManager.beginTransaction().detach(f).attach(f).commit();
            }
        });

        holder.rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api service = retrofit.create(Api.class);
                if (Integer.parseInt(map.get("quantity"))>1) {
                    Call<CartPojo> call = service.updatecart(Integer.parseInt(map.get("cartid")), Integer.parseInt(map.get("quantity")) - 1);
                    call.enqueue(new Callback<CartPojo>() {
                        @Override
                        public void onResponse(Call<CartPojo> call, Response<CartPojo> response) {

                        }

                        @Override
                        public void onFailure(Call<CartPojo> call, Throwable t) {

                        }
                    });
                }else{
                    Toast.makeText(mContext,"Quantity Cannot be 0",Toast.LENGTH_SHORT).show();
                }
                fragmentManager.beginTransaction().detach(f).attach(f).commit();
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
        View mRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_category, parent, false);
        ViewHolder vh = new ViewHolder(mRowView);

        return vh;
    }
}

package alka.heena.swapnali.prajakta.pranav.sareewalaappf.ui.category;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import alka.heena.swapnali.prajakta.pranav.sareewalaappf.Api;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.R;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.extras.AppPreference;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.ui.basket.CartPojo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context mContext;
    ArrayList<HashMap<String, String>> mArray;
    public static AppPreference appPreference;



    public ProductAdapter(Context cxt, ArrayList<HashMap<String, String>> mArray){
        this.mContext = cxt;
        this.mArray = mArray;


    }


    public  static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView sareeimage;
        TextView  sareetitle,sareeprice,sareediscount;
        Button cart;




        public ViewHolder(View v){
            super(v);
            sareeimage = (ImageView) v.findViewById(R.id.sareeimage);
            sareetitle = (TextView) v.findViewById(R.id.sareetitle);
            sareeprice = (TextView) v.findViewById(R.id.sareeprice);
            sareediscount = (TextView) v.findViewById(R.id.sareediscountprice);
            cart = (Button) v.findViewById(R.id.cart);


        }

        @Override
        public void onClick(View v) {
            v.setOnClickListener(this);
            sareeimage = (ImageView) v;
        }
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final HashMap<String,String> map = mArray.get(position);

        appPreference = new AppPreference(mContext);

        holder.sareetitle.setText(map.get("sareename"));
        holder.sareeprice.setText(map.get("originalprice"));
        holder.sareeprice.setPaintFlags(holder.sareeprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.sareediscount.setText(map.get("discountedprice"));
        Glide.with(mContext).load(map.get("url")).into(holder.sareeimage);


        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Api service = retrofit.create(Api.class);
                Call<CartPojo> call = service.CheckCart(Integer.parseInt(appPreference.getUserId()),Integer.parseInt(map.get("productid")));
                call.enqueue(new Callback<CartPojo>() {
                    @Override
                    public void onResponse(Call<CartPojo> call, Response<CartPojo> response) {
                        if (response.body().getResponse().equals("added")){
                            appPreference.setProductId(map.get("productid"));
                            Toast.makeText(mContext,"Added to Cart Sucessfully",Toast.LENGTH_SHORT).show();
                        }
                        else if (response.body().getResponse().equals("already_added")){
                            Toast.makeText(mContext,"Already Added to Cart",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CartPojo> call, Throwable t) {

                    }
                });


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
        View mRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_category, parent, false);
        ViewHolder vh = new ViewHolder(mRowView);

        return vh;

    }


}
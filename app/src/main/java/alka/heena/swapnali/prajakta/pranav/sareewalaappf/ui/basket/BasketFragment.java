package alka.heena.swapnali.prajakta.pranav.sareewalaappf.ui.basket;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import alka.heena.swapnali.prajakta.pranav.sareewalaappf.Api;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.R;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.extras.AppPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static alka.heena.swapnali.prajakta.pranav.sareewalaappf.activities.BottomNavActivity.ta;
import static alka.heena.swapnali.prajakta.pranav.sareewalaappf.activities.BottomNavActivity.tda;

public class BasketFragment extends Fragment {
    private RecyclerView mRecyclerview;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    public static ArrayList<HashMap<String, String>> array;

    private BasketViewModel mViewModel;
    TextView totalrs,totaldisrs,exception;
    public static ArrayList<HashMap<String, String>> cartlist;
    public static AppPreference appPreference;


    public static BasketFragment newInstance() {
        return new BasketFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.basket_fragment, container, false);
        mRecyclerview = root.findViewById(R.id.basket_recycler);


        appPreference = new AppPreference(getActivity());

        totalrs = (TextView) root.findViewById(R.id.totalrs);
        totaldisrs = (TextView) root.findViewById(R.id.totaldisrs);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api service = retrofit.create(Api.class);
        Call<CartPojo> call = service.fetchcart(Integer.parseInt(appPreference.getUserId()));
        call.enqueue(new Callback<CartPojo>() {
            @Override
            public void onResponse(Call<CartPojo> call, Response<CartPojo> response) {
                if (response.body().getResponse().equals("already_added")) {
                    List<Cart> cart = response.body().getCart();
                    array = new ArrayList<>();

                    for (int i = 0; i < cart.size(); i++) {

                        String imgUrl = cart.get(i).getProductPath();
                        String productid = cart.get(i).getProductId();
                        String name = cart.get(i).getProductName();
                        String cartid = cart.get(i).getId();
                        String quantity = cart.get(i).getQuantity();
                        String orgprice = cart.get(i).getOriginalPrice();
                        String disprice = cart.get(i).getDiscountedPrice();

                        HashMap<String, String> map = new HashMap<>();
                        map.put("url", imgUrl);
                        map.put("name", name);
                        map.put("productid", productid);
                        map.put("cartid", cartid);
                        map.put("quantity", quantity);
                        map.put("orgprice", orgprice);
                        map.put("disprice", disprice);

                        array.add(map);

                        List<String> total = response.body().getTotal();
                        String org = total.get(0);
                        String dis = total.get(1);
                        totalrs.setText(String.format("Total : Rs %s", dis));
                        totaldisrs.setText(String.format("Saved Rs %s", String.valueOf(Integer.parseInt(org) - Integer.parseInt(dis))));
                    }
                    if ( cart.size() > 0) {
                        mLayoutManager = new LinearLayoutManager(root.getContext());
                        mRecyclerview.setLayoutManager(mLayoutManager);
                        mAdapter = new BasketAdapter(getActivity(), getFragmentManager(), BasketFragment.this, array);
                        mRecyclerview.setAdapter(mAdapter);
                    }

                }
            }

            @Override
            public void onFailure(Call<CartPojo> call, Throwable t) {

            }
        });

        return root;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BasketViewModel.class);
        // TODO: Use the ViewModel
    }

}

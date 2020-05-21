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

import alka.heena.swapnali.prajakta.pranav.sareewalaappf.R;

import static alka.heena.swapnali.prajakta.pranav.sareewalaappf.BottomNavActivity.ta;
import static alka.heena.swapnali.prajakta.pranav.sareewalaappf.BottomNavActivity.tda;

public class BasketFragment extends Fragment {
    private RecyclerView mRecyclerview;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private BasketViewModel mViewModel;
    TextView totalrs,totaldisrs;
    public static ArrayList<HashMap<String, String>> cartlist;

    public static BasketFragment newInstance() {
        return new BasketFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.basket_fragment, container, false);
        mRecyclerview = root.findViewById(R.id.basket_recycler);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mRecyclerview.setLayoutManager(mLayoutManager);


        totalrs = (TextView) root.findViewById(R.id.totalrs);
        totaldisrs = (TextView) root.findViewById(R.id.totaldisrs);

        loadcart();

        if (cartlist != null){



            mAdapter = new BasketAdapter(getActivity(),getFragmentManager(),this);
            mRecyclerview.setAdapter(mAdapter);

            totalrs.setText(String.format("Total : Rs %s", tda));
            totaldisrs.setText(String.format("Saved Rs %s", Integer.parseInt(ta) - Integer.parseInt(tda) ));


        }
return root;
    }

    private void loadcart() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("cart", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("cart_value",null);
        Type type = new TypeToken<ArrayList<HashMap<String,String>>>(){}.getType();
        cartlist = gson.fromJson(json,type);

        SharedPreferences sp = getActivity().getSharedPreferences("carttotal", Context.MODE_PRIVATE);
        ta = sp.getString("totalamt",null);
        tda = sp.getString("totaldisamt",null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(BasketViewModel.class);
        // TODO: Use the ViewModel
    }

}

package alka.heena.swapnali.prajakta.pranav.sareewalaappf.ui.category;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import alka.heena.swapnali.prajakta.pranav.sareewalaappf.Api;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.Preference;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryFragment extends Fragment {

    ArrayList<HashMap<String, String>> arrayList;
    private RecyclerView mRecyclerview;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    private CategoryViewModel mViewModel;
    TextView textView,productcount;


    SliderLayout sliderLayout;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       final View root = inflater.inflate(R.layout.fragment_category, container, false);

        textView = root.findViewById(R.id.product_category);
        productcount = root.findViewById(R.id.totalproduct);


        //String name = getActivity().getIntent().getStringExtra("caption");
        textView.setText(new StringBuilder().append(Preference.categoryname).append(" Saree Category").toString());

        sliderLayout = root.findViewById(R.id.imageSlider3);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        sliderLayout.setScrollTimeInSec(3);
        setSliderViews();

        mRecyclerview = root.findViewById(R.id.product_recycler);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        mRecyclerview.setLayoutManager(mLayoutManager);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerview.setLayoutManager(linearLayoutManager);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api service = retrofit.create(Api.class);
        Call<Product_Pojo> call = service.getproducts(Integer.parseInt(Preference.categoryid));
        call.enqueue(new Callback<Product_Pojo>() {
            @Override
            public void onResponse(Call<Product_Pojo> call, Response<Product_Pojo> response) {
                List<Product> products = response.body().getProduct();
                arrayList = new ArrayList<>();

                for(int i=0 ; i<products.size() ; i++){

                    String imgUrl = products.get(i).getProductPath();
                    String categoryid = products.get(i).getCategoryId();
                    String productid = products.get(i).getProductId();
                    String discountedprice = products.get(i).getDiscountedPrice();
                    String originalprice = products.get(i).getOriginalPrice();
                    String sareename = products.get(i).getProductName();

                    HashMap<String, String> map = new HashMap<>();

                    map.put("productid", productid);
                    map.put("categoryid", categoryid);
                    map.put("discountedprice",discountedprice);
                    map.put("originalprice",originalprice);
                    map.put("sareename",sareename);
                    map.put("url", imgUrl);
                    map.put("quantity","1");

                    arrayList.add(map);

                }
                Preference.productcount = products.size();
                productcount.setText(new StringBuilder().append(Preference.productcount).append(" Items").toString());

                mAdapter = new ProductAdapter(getActivity(), arrayList);
                mRecyclerview.setAdapter(mAdapter);
                savecart();
            }

            @Override
            public void onFailure(Call<Product_Pojo> call, Throwable t) {
                Toast toast = Toast.makeText(getActivity(),
                        "Failed",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    return root;
    }

    private void savecart() {

    }

    private void setSliderViews() {
        for (int i = 1; i<=3; i++){
            DefaultSliderView sliderView = new DefaultSliderView(super.getContext());
            switch (i){
                case 1:
                    sliderView.setImageDrawable(R.drawable.saree_model);
                    break;

                case 2:
                    sliderView.setImageDrawable(R.drawable.saree_image);
                    break;

                case 3:
                    sliderView.setImageDrawable(R.drawable.saree_img);
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            final int finalI = i;


            sliderLayout.addSliderView(sliderView);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        // TODO: Use the ViewModel
    }

    public Fragment MyFragment() {
        Fragment fragment;
        fragment = CategoryFragment.this;

        return fragment;
    }

}

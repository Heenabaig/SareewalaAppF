package alka.heena.swapnali.prajakta.pranav.sareewalaappf;

import alka.heena.swapnali.prajakta.pranav.sareewalaappf.ui.category.Product_Pojo;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.ui.home.Category_Pojo;
import alka.heena.swapnali.prajakta.pranav.sareewalaappf.ui.offers.Offers_Pojo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String BASE_URL = "http://pherywala.sparsematrix.co.in/sareeapp/sareeapp_accounts/";

    @GET("cats.php")
    Call<Category_Pojo> getcategory();

    @GET("offers.php")
    Call<Offers_Pojo> getoffers();

    @GET("product.php")
    Call<Product_Pojo> getproducts(@Query("category_id") Integer category_id);
}

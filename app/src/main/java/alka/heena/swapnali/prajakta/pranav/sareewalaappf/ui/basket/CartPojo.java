package alka.heena.swapnali.prajakta.pranav.sareewalaappf.ui.basket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CartPojo implements Serializable {

        @SerializedName("response")
        @Expose
        private String response;
        @SerializedName("total")
        @Expose
        private List<String> total = null;
        @SerializedName("cart")
        @Expose
        private List<Cart> cart = null;
        private final static long serialVersionUID = -400705616790133050L;

        public String getResponse () {
        return response;
    }

        public void setResponse (String response){
        this.response = response;
    }

        public List<String> getTotal () {
        return total;
    }

        public void setTotal (List < String > total) {
        this.total = total;
    }

        public List<Cart> getCart () {
        return cart;
    }

        public void setCart (List < Cart > cart) {
        this.cart = cart;
    }
    }

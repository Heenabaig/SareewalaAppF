package alka.heena.swapnali.prajakta.pranav.sareewalaappf.ui.basket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Cart implements Serializable {
        @SerializedName("response")
        @Expose
        private String response;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("quantity")
        @Expose
        private String quantity;
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("original_price")
        @Expose
        private String originalPrice;
        @SerializedName("discounted_price")
        @Expose
        private String discountedPrice;
        @SerializedName("product_path")
        @Expose
        private String productPath;
        private final static long serialVersionUID = -6450149070939589109L;

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(String originalPrice) {
            this.originalPrice = originalPrice;
        }

        public String getDiscountedPrice() {
            return discountedPrice;
        }

        public void setDiscountedPrice(String discountedPrice) {
            this.discountedPrice = discountedPrice;
        }

        public String getProductPath() {
            return productPath;
        }

        public void setProductPath(String productPath) {
            this.productPath = productPath;
        }

    }


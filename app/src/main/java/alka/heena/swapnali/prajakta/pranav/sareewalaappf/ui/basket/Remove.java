package alka.heena.swapnali.prajakta.pranav.sareewalaappf.ui.basket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Remove {
    @SerializedName("response")
    @Expose
    private String response;
    private final static long serialVersionUID = 2924055390872718469L;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

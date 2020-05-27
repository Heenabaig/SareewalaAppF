package alka.heena.swapnali.prajakta.pranav.sareewalaappf.ui.customer;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import alka.heena.swapnali.prajakta.pranav.sareewalaappf.R;

public class CustomerservFragment extends Fragment {

    private CustomerservViewModel mViewModel;

    public static CustomerservFragment newInstance() {
        return new CustomerservFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_customerserv, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CustomerservViewModel.class);
        // TODO: Use the ViewModel
    }

}

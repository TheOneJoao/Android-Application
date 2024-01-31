package com.outspin.app.ui.moment;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.outspin.app.data.DummieData;
import com.outspin.app.databinding.FragmentMomentSliderBinding;
import com.outspin.app.models.Moment;

import java.util.ArrayList;

// Activity
public class MomentFragment extends Fragment {

    private MomentViewModel momentViewModel;
    private FragmentMomentSliderBinding binding;

    private ViewPager2 viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        momentViewModel =
                new ViewModelProvider(this).get(MomentViewModel.class);

        binding = FragmentMomentSliderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewPager = binding.momentPager;

        //MomentAdapter momentAdapter = new MomentAdapter(new ArrayList<>(DummieData.DUMMY_MOMENTS));
        MomentAdapter momentAdapter = new MomentAdapter(this.getContext());
        viewPager.setAdapter(momentAdapter);

        viewPager.setCurrentItem(1);        //  starts in the middle

        return root;
    }
}
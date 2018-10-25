package com.pau.putrautama.gamonbanksampah.feature.nasabah;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pau.putrautama.gamonbanksampah.R;
import com.pau.putrautama.gamonbanksampah.adapter.ViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class NasabahFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    public NasabahFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nasabah, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTabLayout = view.findViewById(R.id.tl_nasabah);
        mViewPager = view.findViewById(R.id.vp_nasabah);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        viewPagerAdapter.addFragment(new TerdaftarFragment(), getString(R.string.terdaftar));
        viewPagerAdapter.addFragment(new MenungguFragment(), getString(R.string.menunggu));

        mViewPager.setAdapter(viewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}

package com.example.nathan.studenthub.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nathan.studenthub.R;

public class MainActivityFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.content_viewpager_fragment, container, false);

        final RegisterFragment registerFragment = new RegisterFragment();
        final LoginFragment loginFragment = new LoginFragment();

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager())
        {
            @Override
            public android.support.v4.app.Fragment getItem(int position)
            {
                return position == 0 ? loginFragment : registerFragment;
            }

            @Override
            public int getCount()
            {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position)
            {
                return position == 0 ? getString(R.string.login_fragment_title) : getString(R.string.register_fragment_title);
            }
        });
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
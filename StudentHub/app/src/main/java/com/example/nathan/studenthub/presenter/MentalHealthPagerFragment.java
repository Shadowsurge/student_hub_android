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
import com.example.nathan.studenthub.model.MenuItem;

/**
 * Created by Nathan on 11/05/2017.
 */

public class MentalHealthPagerFragment extends Fragment
{
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.content_viewpager_fragment, container, false);

        Bundle bundle = new Bundle();
        bundle.putSerializable(ContentActivity.MENU_RESOURCE, (MenuItem) getArguments().get(ContentActivity.MENU_RESOURCE));

        // Create the fragments for each section and add the requested service
        final MentalHealthAboutFragment aboutFragment = new MentalHealthAboutFragment();
        aboutFragment.setArguments(bundle);

        final MentalHealthTreatmentFragment treatmentFragment = new MentalHealthTreatmentFragment();
        treatmentFragment.setArguments(bundle);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager())
        {
            @Override
            public android.support.v4.app.Fragment getItem(int position)
            {
                return position == 0 ? aboutFragment : treatmentFragment;
            }

            @Override
            public int getCount()
            {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position)
            {
                return position == 0 ? "About" : "Treatment";
            }
        });

        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    public void RemoveTab(int position)
    {
        this.tabLayout.removeTabAt(position);
    }
}

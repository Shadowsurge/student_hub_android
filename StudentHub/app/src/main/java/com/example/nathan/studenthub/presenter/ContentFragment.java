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
 * Created by Nathan on 02/05/2017.
 */

public class ContentFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.content_viewpager_fragment, container, false);

        Bundle bundle = new Bundle();
        bundle.putSerializable(ContentActivity.MENU_RESOURCE, (MenuItem) getArguments().get(ContentActivity.MENU_RESOURCE));

        // Create the fragments for each section and add the requested service
        final AboutFragment aboutFragment = new AboutFragment();
        aboutFragment.setArguments(bundle);

        final ServicesFragment servicesFragment = new ServicesFragment();
        servicesFragment.setArguments(bundle);

        final ContactFragment contactFragment = new ContactFragment();
        contactFragment.setArguments(bundle);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager())
        {
            @Override
            public android.support.v4.app.Fragment getItem(int position)
            {
                if(position == 0)
                {
                    return aboutFragment;
                }
                else if(position == 1)
                {
                    return servicesFragment;
                }
                else
                {
                    return contactFragment;
                }
            }

            @Override
            public int getCount()
            {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position)
            {
                if(position == 0)
                {
                    return "About";
                }
                else if(position == 1)
                {
                    return "Services";
                }
                else
                {
                    return "Contact";
                }
            }
        });

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}

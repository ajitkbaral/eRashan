package np.com.ajitkbaral.erashan.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import np.com.ajitkbaral.erashan.R;
import np.com.ajitkbaral.erashan.entity.CategoryItem;

public class CategoryItemFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<CategoryItem> categoryItemList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_item, container, false);
        viewPager = view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        categoryItemList.add(new CategoryItem(1, "Rice"));
        categoryItemList.add(new CategoryItem(2, "Cooking Oil"));
        categoryItemList.add(new CategoryItem(3, "Ghiu (Ghee)"));
        categoryItemList.add(new CategoryItem(4, "Dal & Pulses"));
        categoryItemList.add(new CategoryItem(5, "Masala/Spices"));
        categoryItemList.add(new CategoryItem(6, "Salt"));

        Bundle bundle = new Bundle();
        for (int i = 0; i < categoryItemList.size(); i++) {

            bundle.putString(String.valueOf(i), categoryItemList.get(i).getName());
        }

        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager(), bundle);

        for (int i = 0; i < categoryItemList.size(); i++) {
            adapter.addFragment(new ProductFragment(), categoryItemList.get(i).getName());
        }

        viewPager.setOffscreenPageLimit(categoryItemList.size());
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private final Bundle viewPagerBundle;
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager, Bundle data) {
            super(manager);
            viewPagerBundle = data;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = mFragmentList.get(position);
            Bundle fragmentBundle = new Bundle();
            fragmentBundle.putString("category_name", viewPagerBundle.getString(String.valueOf(position)));
            fragment.setArguments(fragmentBundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}

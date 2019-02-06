package np.com.ajitkbaral.erashan.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import np.com.ajitkbaral.erashan.R;
import np.com.ajitkbaral.erashan.adapter.CategoryAdapter;
import np.com.ajitkbaral.erashan.entity.Category;
import np.com.ajitkbaral.erashan.listener.OnItemClickListener;

public class MainFragment extends Fragment {

    private SliderLayout sliderLayout;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        categoryList = new ArrayList<>();
        setupSliderLayout(view);
        setupContentView(view);
        return view;
    }

    private void setupSliderLayout(View view) {
        sliderLayout = view.findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.COLOR); //set indicator animation by
        // using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :

        setSliderViews();
    }

    private void setSliderViews() {

        for (int i = 0; i <= 3; i++) {

            SliderView sliderView = new DefaultSliderView(getContext());

            switch (i) {
                case 0:
                    sliderView.setImageUrl("https://cdn.shopify.com/s/files/1/2090/1629/files/websitebanner_2048x.jpg?v=1548738442");
                    sliderView.setDescription("PAMPERS - 16% Off Monthly Packs - SHOP  NOW!");
                    break;
                case 1:
                    sliderView.setImageUrl("https://cdn.shopify.com/s/files/1/2090/1629/files/card-reader-payment_2048x.jpg?v=1547608886");
                    sliderView.setDescription("CASH ON DELIVERY");
                    break;
                case 2:
                    sliderView.setImageUrl("https://cdn.shopify.com/s/files/1/2090/1629/files/tvoffer_9493f907-f5db-41ee-86c2-bc4807f0c62d_2048x.jpg?v=1548394891");
                    sliderView.setDescription("Winter win offers");
                    break;
                case 3:
                    sliderView.setImageUrl("https://cdn.shopify.com/s/files/1/2090/1629/files/slider3_2048x.jpg?v=1543822857");
                    sliderView.setDescription("Loyalty Points - Buy and earn loyalty points");
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
//            sliderView.setDescription("setDescription " + (i + 1));
//            final int finalI = i;
//            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
//                @Override
//                public void onSliderClick(SliderView sliderView) {
//                    Toast.makeText(MainActivity.this, "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
//                }
//            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
    }

    private void setupContentView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        setupRecyclerViewToAdapter();
    }

    private void setupRecyclerViewToAdapter() {
        categoryList.add(new Category(1,"Grocery", R.drawable.groceries));
        categoryList.add(new Category(2,"Personal Care", R.drawable.personal_care));
        categoryList.add(new Category(3,"Baby Care", R.drawable.baby_care));
        categoryList.add(new Category(4,"Branded Food", R.drawable.branded_food));
        categoryList.add(new Category(5,"Beverages", R.drawable.beverages));
        categoryList.add(new Category(6,"HouseHold", R.drawable.house_hold));

        categoryAdapter = new CategoryAdapter(getContext(), categoryList, new OnItemClickListener<Category>() {
            @Override
            public void onItemClick(Category item) {
                // Create new fragment and transaction
                Fragment newFragment = new CategoryItemFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack if needed
                transaction.replace(R.id.frame_layout, newFragment);
                transaction.addToBackStack(null);
                // Commit the transaction
                transaction.commit();
            }
        });
        recyclerView.setAdapter(categoryAdapter);
    }
}

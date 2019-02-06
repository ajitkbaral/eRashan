package np.com.ajitkbaral.erashan.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import np.com.ajitkbaral.erashan.R;
import np.com.ajitkbaral.erashan.adapter.ProductAdapter;
import np.com.ajitkbaral.erashan.dialog.ProductSelectedDialog;
import np.com.ajitkbaral.erashan.entity.Category;
import np.com.ajitkbaral.erashan.entity.Product;
import np.com.ajitkbaral.erashan.listener.CommunicationListener;
import np.com.ajitkbaral.erashan.listener.OnItemClickListener;

public class ProductFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private CommunicationListener communicationListener;
    private String categoryName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        categoryName = args.getString("category_name");
        setProductList();
    }

    private void setProductList() {
        productList = new ArrayList<>();
        if (categoryName.equals("Rice")) {
            productList.add(new Product(1, "BANNO SUPER TRADITIONAL BASMATI RICE", null, 160,
                    "https://cdn.shopify.com/s/files/1/2090/1629/products/700x700.5944A1EB-5056-B703-FD0A28E2E6AE8E66-supporting_image_2_480x480.jpg?v=1543640439"));
            productList.add(new Product(2, "AARATI PREMIUM RICE - 20KG", null, 2670, "https://cdn.shopify.com/s/files/1/2090/1629/products/aarati_rice_20KG_480x480.jpg?v=1543641115"));
            productList.add(new Product(3, "BANNO BROWN BASMATI RICE- 1KG", null, 195, "https://cdn.shopify.com/s/files/1/2090/1629/products/banno_brown_rice_480x480.jpg?v=1543640582"));
            productList.add(new Product(4, "GYAN BOILED BASMATI RICE-5KG", null, 540, "https://cdn.shopify.com/s/files/1/2090/1629/products/l40512_480x480.jpg?v=1543638846"));
            productList.add(new Product(5, "GYAN PREMIUM BASMATI RICE 20KG", null, 2680, "https://cdn.shopify.com/s/files/1/2090/1629/products/l40507_480x480.jpg?v=1548825717"));

            productList.add(new Product(6, "GYAN PREMIUM BASMATI RICE- 5KG", null, 680, "https://cdn.shopify.com/s/files/1/2090/1629/products/MG_3800_480x480.JPG?v=1543647835"));
            productList.add(new Product(7, "GYAN SOALTEE JEERA MASINO RICE - 25 KG", null, 17000, "https://cdn.shopify.com/s/files/1/2090/1629/products/gyan_solt_480x480.jpg?v=1543646474"));
            productList.add(new Product(8, "GYAN SONA MANSULI RICE", null, 1670, "https://cdn.shopify.com/s/files/1/2090/1629/products/gyan_solt_480x480.jpg?v=1543646474"));
            productList.add(new Product(9, "HARPAL LONG GR" +
                    "AIN RICE-20KG", null, 2449, "https://cdn.shopify.com/s/files/1/2090/1629/products/Galaxy-Basmati-Rice-Har-pal-SDL502401192-1-0b8d2_bddb3aa8-6d29-4626-a794-303699e351ee_480x480.jpg?v=1543649692"));
            productList.add(new Product(10, "HARPAL LONG GRAIN RICE-5KG", null, 690, "https://cdn.shopify.com/s/files/1/2090/1629/products/Galaxy-Basmati-Rice-Har-pal-SDL502401192-1-0b8d2_1ad1b55a-904c-458f-8c80-aca8d3dcd11e_480x480.jpg?v=1543638766"));

            productList.add(new Product(11, "HARPAL USENA (BOILED) LONG GRAIN RICE-20KG", null, 2335, "https://cdn.shopify.com/s/files/1/2090/1629/products/20180927_093159_480x480.jpg?v=1543648278"));
            //productList.add(new Product(12, "", null, , ""));

        }

        if(categoryName.equals("Cooking Oil")) {
            productList.add(new Product(1, "SAFFOLA GOLD EDIBLE OIL", null, 1275, "https://cdn.shopify.com/s/files/1/2090/1629/products/61aSkV9Qi6L._SL1200_480x480.jpg?v=1543649594"));
            productList.add(new Product(2, "DHARA SUNFLOWER OIL- 1BOX", null, 2050, "https://cdn.shopify.com/s/files/1/2090/1629/products/20180613155533_291dcbac-d5d3-4ff2-a86d-139ec0638cfc_480x480.jpg?v=1543640061"));
            productList.add(new Product(3, "FORTUNE SUNFLOWER OIL (JAR)", null, 640, "https://cdn.shopify.com/s/files/1/2090/1629/products/pro_14626_480x480.jpg?v=1543638899"));
            productList.add(new Product(4, "FAMILY SUNFLOWER OIL-1LTR", null, 115, "https://cdn.shopify.com/s/files/1/2090/1629/products/sunflower_20oil_480x480.png?v=1543639638"));
            productList.add(new Product(5, "DHARA SUNFLOWER OIL", null, 210, "https://cdn.shopify.com/s/files/1/2090/1629/products/20180613155533_480x480.jpg?v=1543639856"));

        }

        if(categoryName.equals("Ghiu (Ghee)")) {
            productList.add(new Product(12, "DDC DAIRY GHEE - 1 LTR", null, 850, "https://cdn.shopify.com/s/files/1/2090/1629/products/ddc_480x480.jpg?v=1543640105"));
            productList.add(new Product(12, "PATANJALI COW'S GHEE, 1L", null, 800, "https://cdn.shopify.com/s/files/1/2090/1629/products/91W38tEyptL._SL1500_480x480.jpg?v=1543646063"));
            productList.add(new Product(12, "PATANJALI COW'S GHEE, 500 ML", null, 416, "https://cdn.shopify.com/s/files/1/2090/1629/products/918cozMfkpL._SL1500_480x480.jpg?v=1543642981"));
            productList.add(new Product(12, "DDC DAIRY GHEE - 500 ML", null, 425, "https://cdn.shopify.com/s/files/1/2090/1629/products/20180305104256_480x480.jpg?v=1543641135"));
            productList.add(new Product(12, "SONAI GHEE", null, 455, "https://cdn.shopify.com/s/files/1/2090/1629/products/0-02-03-8340687a875711caa5d29b0da3ddb4068c81c4abb5891c211acea85b6bc5d8ee_full_480x480.jpg?v=1543645028"));
            productList.add(new Product(12, "SWASTIK VANASPATI GHEE-1KG", null, 150, "https://cdn.shopify.com/s/files/1/2090/1629/products/ghe_480x480.jpg?v=1543646180"));
            productList.add(new Product(12, "HBN PURE COW GHEE-500ML", null, 600, "https://cdn.shopify.com/s/files/1/2090/1629/products/20180812160557_480x480.jpg?v=1543641111"));
            productList.add(new Product(12, "HBN PURE COW GHEE-1LTR", null, 1100, "https://cdn.shopify.com/s/files/1/2090/1629/products/20180812160557_35c0a0ba-4757-4b68-b67e-1b6d639f3cd7_480x480.jpg?v=1543642873"));

        }


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity;

        if (context instanceof Activity) {
            activity = (Activity) context;
            communicationListener = (CommunicationListener) activity;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        setupViews(view);
        setupRecyclerViewToAdapter();
        return view;
    }

    public void setupViews(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());//)new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    private void setupRecyclerViewToAdapter() {
        productAdapter = new ProductAdapter(getContext(), productList, new ProductAdapter.onProductItemClickListener() {
            @Override
            public void onItemClick(Product item, String method) {
                communicationListener.communicate(method, "Item selected", item);


//                ProductSelectedDialog dialog = new ProductSelectedDialog();
//                if(getFragmentManager() != null)
//                    dialog.show(getFragmentManager(), "Product Selected Dialog");

            }
        });
        recyclerView.setAdapter(productAdapter);
    }
}

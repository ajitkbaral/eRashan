package np.com.ajitkbaral.erashan.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import np.com.ajitkbaral.erashan.R;
import np.com.ajitkbaral.erashan.entity.Cart;
import np.com.ajitkbaral.erashan.entity.Product;
import np.com.ajitkbaral.erashan.fragment.MainFragment;
import np.com.ajitkbaral.erashan.listener.CommunicationListener;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, CommunicationListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    //    private TabLayout tabLayout;
//    private ViewPager viewPager;
    private FloatingActionButton fab;
    private CoordinatorLayout contentLayout;
    private Snackbar snackBar;
    private TextView totalPriceText;
    private double totalPrice = 0.0;
    private boolean snackBarVisible = false;
    private int totalQuantity = 0;
    private Cart cart = new Cart();
    private HashMap<String, Product> cartProductList = new LinkedHashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupCoordinatorLayout();
        setUpFABView();
        setUpSnackBarView();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, new MainFragment());
        transaction.commit();
    }

    private void setUpSnackBarView() {
        // Create the Snackbar
        snackBar = Snackbar.make(contentLayout, "", Snackbar.LENGTH_INDEFINITE);
        // Get the Snackbar's layout view
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackBar.getView();
        // Hide the text
        TextView textView = layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);

        // Inflate our custom view
        View snackView = getLayoutInflater().inflate(R.layout.layout_custom_snackbar, null);

        //If the view is not covering the whole snackBar layout, add this line
        layout.setPadding(0, 0, 0, 0);

        // Add the view to the Snackbar's layout
        layout.addView(snackView, 0);

        totalPriceText = snackView.findViewById(R.id.total_price);

        Button checkout = snackView.findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartIntent();
            }
        });

        snackBar.addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                snackBarVisible = false;
            }

            @Override
            public void onShown(Snackbar transientBottomBar) {
                snackBarVisible = true;
            }
        });
    }

    private void cartIntent() {
        Intent intent = new Intent(MainActivity.this, CartActivity.class);
        intent.putExtra("cart", cart);
        Log.d("Cart", cart.toString());
        startActivity(intent);
    }

    private void setupCoordinatorLayout() {
        contentLayout = findViewById(R.id.content_layout);
    }


    private void setUpFABView() {
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        switch (menuItem.getItemId()) {

            case R.id.mycart:
                cartIntent();
                break;
            case R.id.logout:
                Intent logout = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(logout);
                break;


        }
        //close navigation drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        } else if (item.getItemId() == R.id.cart) {
            cartIntent();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_layout__open, R.string.drawer_layout_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void communicate(String key, String message, Product product) {

        double price = product.getPrice();

        switch (key) {
            case "item_add":

                totalPrice += price;
                totalPriceText.setText("Total: Rs." + totalPrice + "/-");
                totalQuantity += 1;
                if (!snackBarVisible) {
                    snackBar.show();
                }

                //Adding product ot the list
                if (cartProductList.get(product.getName()) == null) {
                    cartProductList.put(product.getName(), product);
                } else {
                    Product p = cartProductList.get(product.getName());
                    p.setQuantity(product.getQuantity());
                    cartProductList.put(product.getName(), p);
                }
                break;
            case "item_sub":
                totalPrice -= price;
                totalPriceText.setText("Total: Rs." + totalPrice + "/-");
                totalQuantity -= 1;
                if (!snackBarVisible) {
                    snackBar.show();
                }

                if (product.getQuantity() == 0) {
                    cartProductList.remove(product.getName());
                }

                if (totalQuantity == 0) {
                    snackBar.dismiss();
                }


                break;

        }

        cart.setProductList(cartProductList);
        cart.setTotalPrice(totalPrice);
    }

}

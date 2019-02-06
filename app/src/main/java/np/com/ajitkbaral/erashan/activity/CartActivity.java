package np.com.ajitkbaral.erashan.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import np.com.ajitkbaral.erashan.R;
import np.com.ajitkbaral.erashan.adapter.CartAdapter;
import np.com.ajitkbaral.erashan.dialog.OrderPlacementDialog;
import np.com.ajitkbaral.erashan.entity.Cart;
import np.com.ajitkbaral.erashan.entity.Product;
import np.com.ajitkbaral.erashan.listener.OnItemClickListener;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private Cart cart;
    private TextView totalPrice, totalQuantity, nothingFound;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setupCart();
        setupViews();
        setupRecyclerViewToAdapter();
    }

    private void setupCart() {
        cart = (Cart) getIntent().getSerializableExtra("cart");
    }

    public void setupViews() {
        getSupportActionBar().setTitle("My Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        nothingFound = findViewById(R.id.noting_found);
        totalPrice = findViewById(R.id.total_price);
        totalQuantity = findViewById(R.id.total_quantity);
        btnContinue = findViewById(R.id.btn_continue);

        if (cart != null) {
            totalPrice.setText("Rs. " + String.valueOf(cart.getTotalPrice()) + "/-");
            totalQuantity.setText(String.valueOf("Total Item(s) : " + cart.getProductList().size()));

            if (cart.getProductList().size() == 0) {
                btnContinue.setVisibility(View.GONE);
            }

            if (cart.getProductList().size() > 0) {
                nothingFound.setVisibility(View.GONE);
            }

            btnContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OrderPlacementDialog dialog = new OrderPlacementDialog();
                    if(getSupportFragmentManager() != null)
                        dialog.show(getSupportFragmentManager(), "Order Placement Dialog");
                }
            });
        }

    }

    private void setupRecyclerViewToAdapter() {

        cartAdapter = new CartAdapter(this, cart.getProductList(), new OnItemClickListener<Cart>() {
            @Override
            public void onItemClick(Cart item) {
            }
        });
        recyclerView.setAdapter(cartAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

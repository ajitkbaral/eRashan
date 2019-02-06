package np.com.ajitkbaral.erashan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import np.com.ajitkbaral.erashan.R;
import np.com.ajitkbaral.erashan.entity.Cart;
import np.com.ajitkbaral.erashan.entity.Product;
import np.com.ajitkbaral.erashan.listener.OnItemClickListener;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private List<Product> productList;
    private Context mContext;
    private OnItemClickListener<Cart> onItemClickListener;

    public CartAdapter(Context context, HashMap<String, Product> hashedProductList, OnItemClickListener<Cart> onItemClickListener) {
        mContext = context;
        this.productList = new ArrayList<>(hashedProductList.values());
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cart, viewGroup, false);

        return new CartAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int i) {
        Product item = productList.get(i);
        holder.name.setText(item.getName());

        holder.totalQuantity.setText(String.valueOf(item.getQuantity()));
        holder.unitPrice.setText("Rs. "+String.valueOf(item.getPrice()));

        holder.totalPrice.setText("Rs. " + String.valueOf(item.getPrice() * item.getQuantity()));
        Glide.with(mContext).load(item.getImageUrl()).into(holder.profileImage);
        holder.bind(item, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, unitPrice, totalPrice, totalQuantity;
        ImageView profileImage;

        MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.product_name);
            profileImage = view.findViewById(R.id.product_image);
            totalQuantity = view.findViewById(R.id.cart_item_quantity);
            unitPrice = view.findViewById(R.id.item_unit_price);
            totalPrice = view.findViewById(R.id.total_price);
        }

        void bind(final Product item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}

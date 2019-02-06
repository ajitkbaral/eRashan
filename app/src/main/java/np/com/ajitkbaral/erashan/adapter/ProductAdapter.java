package np.com.ajitkbaral.erashan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import np.com.ajitkbaral.erashan.R;
import np.com.ajitkbaral.erashan.entity.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {


    int defaultQuantity = 0, minimumQuantity = 1, maxQuantity = 15;

    public interface onProductItemClickListener {
        void onItemClick(Product product, String method);
    }

    private List<Product> productList;
    private Context mContext;
    private onProductItemClickListener onProductItemClickListener;

    public ProductAdapter(Context context, List<Product> productList, onProductItemClickListener onProductItemClickListener) {
        mContext = context;
        this.productList = productList;
        this.onProductItemClickListener = onProductItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_product, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        Product item = productList.get(i);
        holder.name.setText(item.getName());
        holder.price.setText("Rs. " + String.valueOf(item.getPrice()));
        Glide.with(mContext).load(item.getImageUrl()).into(holder.profileImage);

        holder.bind(item, onProductItemClickListener);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, price;
        ImageView profileImage;
        Button minus, plus;
        TextView quantityText;

        MyViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.product_name);
            price = view.findViewById(R.id.product_price);
            profileImage = view.findViewById(R.id.product_image);
            quantityText = view.findViewById(R.id.quantity);
            minus = view.findViewById(R.id.minus);
            plus = view.findViewById(R.id.plus);
            quantityText.setText(String.valueOf(defaultQuantity));
        }

        void bind(final Product item, final onProductItemClickListener listener) {

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = Integer.parseInt(quantityText.getText().toString());

                    if (quantity < maxQuantity) {
                        quantity = ++quantity;

                        listener.onItemClick(item, "item_add");
                    }


                    quantityText.setText(String.valueOf(quantity));

                    if (quantity > 0)
                        itemView.setBackgroundResource(R.drawable.item_product_selected);

                    item.setQuantity(quantity);
                }

            });


            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int quantity = Integer.parseInt(quantityText.getText().toString());

                    if (quantity >= 1) {

                        if (quantity == 1)
                            item.setQuantity(0);

                        listener.onItemClick(item, "item_sub");
                    }

                    if (quantity <= minimumQuantity) {
                        quantity = defaultQuantity;

                    } else {
                        quantity = --quantity;
                    }

                    quantityText.setText(String.valueOf(quantity));

                    if (quantity == 0)
                        itemView.setBackgroundColor(Color.WHITE);

                }
            });
        }
    }

}

package np.com.ajitkbaral.erashan.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import np.com.ajitkbaral.erashan.R;
import np.com.ajitkbaral.erashan.entity.Product;
import np.com.ajitkbaral.erashan.listener.OnItemClickListener;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemAdapter.MyViewHolder> {

    private List<Product> productList;
    private Context mContext;
    private OnItemClickListener<Product> onItemClickListener;

    public CategoryItemAdapter(Context context, List<Product> productList, OnItemClickListener<Product> onItemClickListener) {
        mContext = context;
        this.productList = productList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_category_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Product product = productList.get(i);
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView profileImage;

        MyViewHolder(View view) {
            super(view);
//            name = view.findViewById(R.id.product_title);
//            profileImage = view.findViewById(R.id.product_image);
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

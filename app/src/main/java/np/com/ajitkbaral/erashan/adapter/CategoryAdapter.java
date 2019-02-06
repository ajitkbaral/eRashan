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
import np.com.ajitkbaral.erashan.entity.Category;
import np.com.ajitkbaral.erashan.listener.OnItemClickListener;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private List<Category> categoryList;
    private Context mContext;
    private OnItemClickListener<Category> onItemClickListener;

    public CategoryAdapter(Context context, List<Category> categoryList, OnItemClickListener<Category> onItemClickListener) {
        mContext = context;
        this.categoryList = categoryList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_category, viewGroup, false);

        return new CategoryAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MyViewHolder holder, int i) {
        Category item  = categoryList.get(i);
        holder.categoryName.setText(item.getName());
        holder.categoryImage.setImageResource(item.getDrawableResource());
        holder.bind(item, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName;
        ImageView categoryImage;

        MyViewHolder(View view) {
            super(view);
            categoryName = view.findViewById(R.id.category_name);
            categoryImage = view.findViewById(R.id.category_image);
        }

        void bind(final Category item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}

package com.sohel.drivermanagement.User.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sohel.drivermanagement.Admin.DataModuler.Products;
import com.sohel.drivermanagement.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserProductAdapter extends RecyclerView.Adapter<UserProductAdapter.MyViewHolder>{

    private Context context;
    private List<Products> productList;
    private  OnItemClickListner listner;

    public UserProductAdapter(Context context, List<Products> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.utilities_list_item_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Products currentItem=productList.get(position);
     Picasso.get().load(currentItem.getImage()).placeholder(R.drawable.select_image).into(holder.imageView);
       holder.productNameTextview.setText(currentItem.getProductName());

        String description=currentItem.getProductDescription();
        if(description.length()>20){
            String subDes=description.substring(0,20);
            holder.productDescriptionTextview.setText(subDes+".......");
        }else{
            holder.productDescriptionTextview.setText(description);

        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
     ImageView imageView;
     TextView productNameTextview,productDescriptionTextview;

      public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        imageView=itemView.findViewById(R.id.utilites_list_ImageViewid);
        productNameTextview=itemView.findViewById(R.id.utilites_list_HeadingTextview);
        productDescriptionTextview=itemView.findViewById(R.id.utilitis_list_DescriptionTextviewid);

            itemView.setOnClickListener(this);


        }



        @Override
        public void onClick(View v) {
            if(listner!=null){
                int position=getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    listner.onItemClick(position);
                }
            }
        }


    }
    public interface  OnItemClickListner{
        void onItemClick(int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner=listner;
    }


}

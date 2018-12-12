package com.example.abhishekpatil.salon_woc_18;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SalonAdapter extends RecyclerView.Adapter<SalonAdapter.SalonViewHolder> {

    private List<SalonInfo> salonList;
    private Context context;

    public SalonAdapter(List<SalonInfo> salonList, Context context) {
        this.salonList = salonList;
        this.context = context;
    }

    @NonNull
    @Override
    public SalonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.salon_info_cardview,null);
        SalonViewHolder salonViewHolder = new SalonViewHolder(view);
        return salonViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SalonViewHolder salonViewHolder,final int position) {
        SalonInfo salonInfo = salonList.get(position);

        salonViewHolder.mname.setText(salonInfo.getName());
        salonViewHolder.mdetail.setText(salonInfo.getDetail());
        salonViewHolder.mstatus.setText(salonInfo.getStatus());
        salonViewHolder.mimage.setImageResource(salonInfo.getImage());


    }

    @Override
    public int getItemCount() {
        return salonList.size();
    }

    public static class SalonViewHolder extends  RecyclerView.ViewHolder{

        protected TextView mname;
        protected TextView mdetail;
        protected TextView mstatus;
        protected ImageView mimage;


        public SalonViewHolder(@NonNull View itemView) {
            super(itemView);
            mname = (TextView)itemView.findViewById(R.id.salon_name);
            mdetail = (TextView)itemView.findViewById(R.id.salon_detail);
            mstatus = (TextView)itemView.findViewById(R.id.salon_status);
            mimage = (ImageView) itemView.findViewById(R.id.salon_image);
        }
    }
}

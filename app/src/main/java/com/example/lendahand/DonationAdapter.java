package com.example.lendahand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.ViewHolder> {

    private Context context;
    private List<DonationItem> items;

    public DonationAdapter(Context context, List<DonationItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.donationhist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DonationItem item = items.get(position);
        holder.tvType.setText(item.getType());
        holder.tvQty.setText(item.getQuantity());
        holder.tvLocation.setText(item.getLocation());
        holder.tvDate.setText(item.getDate());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvType, tvQty, tvLocation, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvType = itemView.findViewById(R.id.DonationType);
            tvQty = itemView.findViewById(R.id.DonationQty);
            tvLocation = itemView.findViewById(R.id.tvDonationLocation);
            tvDate = itemView.findViewById(R.id.DonationDate);
        }
    }
}

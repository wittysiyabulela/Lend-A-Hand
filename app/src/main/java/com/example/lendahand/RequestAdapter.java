package com.example.lendahand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {

    private Context context;
    private List<requestItem> items;

    public RequestAdapter(Context context, List<requestItem> items) {
        this.context = context;
        this.items   = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.requesthist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        requestItem item = items.get(position);

        // Map data from the 'requestItem' model to the XML views
        holder.tvRequestType.setText(item.getResourceType());
        holder.tvRequestQty.setText("Qty: " + item.getQuantity() + " units");
        holder.tvRequestLocation.setText(item.getLocation());
        holder.progressFulfilled.setProgress(item.getProgressPercent());
        holder.tvFulfilmentStatus.setText(item.getFulfilmentText());
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        // Variables match the IDs in requesthist.xml
        TextView tvRequestType, tvRequestQty;
        TextView tvRequestLocation, tvFulfilmentStatus;
        ProgressBar progressFulfilled;

        ViewHolder(View itemView) {
            super(itemView);
            tvRequestType      = itemView.findViewById(R.id.tvRequestType);
            tvRequestQty       = itemView.findViewById(R.id.RequestQty);
            tvRequestLocation  = itemView.findViewById(R.id.RequestLocation);
            progressFulfilled  = itemView.findViewById(R.id.progressFulfilled);
            tvFulfilmentStatus = itemView.findViewById(R.id.FulfilmentStatus);
        }
    }
}
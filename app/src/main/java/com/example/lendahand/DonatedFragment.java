package com.example.lendahand;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class DonatedFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_donated, container, false);

        RecyclerView rv = view.findViewById(R.id.recyclerViewDonated);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample data for Donations
        List<DonationItem> donatedList = new ArrayList<>();
        donatedList.add(new DonationItem("Books", "Qty: 8 units donated", "Johannesburg", "2 May 2026"));
        donatedList.add(new DonationItem("Blankets", "Qty: 5 units donated", "Pretoria", "10 June 2026"));
        donatedList.add(new DonationItem("Canned Food", "Qty: 20 units donated", "Soweto", "15 July 2026"));

        rv.setAdapter(new DonationAdapter(getContext(), donatedList));

        return view;
    }
}

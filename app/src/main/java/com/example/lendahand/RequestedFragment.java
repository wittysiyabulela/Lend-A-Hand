package com.example.lendahand;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Intent;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class RequestedFragment extends Fragment {

    @Nullable
    @Override




    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_requested, container, false);
        RecyclerView rv = view.findViewById(R.id.recyclerViewRequested);
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));

        List<requestItem> sampleList = new ArrayList<>();
        sampleList.add(new requestItem("Food Parcels", "5", "Soweto", 2, 5));
        sampleList.add(new requestItem("Clothing Items", "10", "Johannesburg", 10, 10));
        sampleList.add(new requestItem("Medication", "3", "Pretoria", 1, 3));
        sampleList.add(new requestItem("Bedding", "2", "Cape Town", 0, 2));

        rv.setAdapter(new RequestAdapter(requireContext(), sampleList));

        return view;
    }
}


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

public class RequestedFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile_list, container, false);
        TextView emptyText = root.findViewById(R.id.emptyText);
        emptyText.setText("Tap to view your request history.");
        emptyText.setOnClickListener(v -> startActivity(new Intent(requireContext(), RequestHistoryActivity.class)));
        return root;
    }
}


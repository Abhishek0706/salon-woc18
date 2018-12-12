package com.example.abhishekpatil.salon_woc_18;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Fragment_main1 extends Fragment {
    public  List<SalonInfo> salonlist;
    SalonAdapter salonAdapter;

    View view;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main1,container,false);
        context = container.getContext();
        initUi();
        return view;
    }

    private void initUi() {

        salonlist = new ArrayList<>();

        RecyclerView recList = (RecyclerView)view.findViewById(R.id.recyclerView);
        recList.setHasFixedSize(true);

        LinearLayoutManager llm  = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recList.setLayoutManager(llm);



        salonlist.add(new SalonInfo("Abhishek", "good", "open", R.drawable.ic_launcher_background));

        String s[] = getArguments().getStringArray("info");
        salonlist.add(new SalonInfo(s[0],s[1],s[2],Integer.valueOf(s[3])));



        salonAdapter = new SalonAdapter(salonlist,context);
        recList.setAdapter(salonAdapter);
    }
}

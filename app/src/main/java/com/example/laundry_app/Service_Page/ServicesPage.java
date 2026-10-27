package com.example.laundry_app.Service_Page;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.laundry_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
public class ServicesPage extends Fragment {
    RecyclerView service_recycler;
    private List<ServiceViewPair> itemList;
    private Service_ViewHolder adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_services_page, container, false);

        service_recycler = view.findViewById(R.id.service_recycler);
        service_recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        itemList = new ArrayList<>();
        adapter = new Service_ViewHolder(getContext(), itemList);

        service_recycler.setAdapter(adapter);

        DatabaseReference databaseReference =
                FirebaseDatabase.getInstance()
                        .getReference("Laundry app")
                        .child("All Service");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                itemList.clear();

                List<Service_Model> tempList = new ArrayList<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Service_Model item = dataSnapshot.getValue(Service_Model.class);
                    tempList.add(item);
                }
                for (int i = 0; i < tempList.size(); i += 2) {
                    Service_Model loc1 = tempList.get(i);
                    Service_Model loc2 = (i + 1 < tempList.size()) ? tempList.get(i + 1) : new Service_Model();
                    itemList.add(new ServiceViewPair(loc1, loc2));
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}
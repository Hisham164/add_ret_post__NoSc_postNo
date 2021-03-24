package com.example.publish_posts_firebase;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.publish_posts_firebase.adapters.AdapterNotification;
import com.example.publish_posts_firebase.models.ModelNotification;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NotificationsFragment extends Fragment {

    //recyclerview
    RecyclerView notificationRv;

    private FirebaseAuth firebaseAuth;


    private ArrayList<ModelNotification> notificationsList;

    private AdapterNotification adapterNotification;

    public NotificationsFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        //init recyclerview
        notificationRv = view.findViewById(R.id.notificationTv);

        firebaseAuth = firebaseAuth.getInstance();

        getAllNotification();

        return view;
    }

    private void getAllNotification() {
        notificationsList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(firebaseAuth.getUid()).child("Notifications")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       notificationsList.clear();
                       for (DataSnapshot ds: snapshot.getChildren()){
                           //get data
                           ModelNotification model = ds.getValue(ModelNotification.class);

                           //add to list
                           notificationsList.add(model);
                       }

                       //adapter
                        adapterNotification = new AdapterNotification(getActivity(), notificationsList);
                       //set to recyclerview
                        notificationRv.setAdapter(adapterNotification);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }
                });
    }
}
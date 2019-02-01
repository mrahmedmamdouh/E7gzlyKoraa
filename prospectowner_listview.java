package com.example.android.e7gzlykora;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class prospectowner_listview extends AppCompatActivity {

    RecyclerView list;
    ArrayList <com.example.android.e7gzlykora.owner> ownerlist = new ArrayList <>();
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    owner owner;        // You should Change your manner to declare object (type Object with capital letter first) i.e: Owner owner
    customAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prospectowner_listview);

        list = findViewById(R.id.list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        list.setLayoutManager(manager);
        adapter = new customAdapter(prospectowner_listview.this, ownerlist);
        list.setAdapter(adapter);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("owners");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList <com.example.android.e7gzlykora.owner> ownerLs = new ArrayList <>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    owner o = data.getValue(owner.class);
                    // i want to know this part gives value or not
                    ownerLs.add(o);
                }
                adapter.addData(ownerlist);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}

package com.example.janaki;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    String myMessage = "false";
    private List<ChatBubble> ChatBubbles;
    private ArrayAdapter<ChatBubble> adapter;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    String count="1";
    int n;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        ChatBubbles = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list_msg);


        adapter = new MessageAdapter(this, R.layout.activity_left_bubble, ChatBubbles);
        listView.setAdapter(adapter);

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isOnline()) {
                    int action = event.getAction();
                    switch (action) {


                        case MotionEvent.ACTION_DOWN:
                            mDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(count);
                            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    if (dataSnapshot.hasChild("date")) {
                                        String date = dataSnapshot.child("date").getValue(String.class);

                                        myMessage = "centre";
                                        ChatBubble ChatBubble = new ChatBubble(date, myMessage, null);
                                        ChatBubbles.add(ChatBubble);
                                        adapter.notifyDataSetChanged();
                                    }

                                    if (dataSnapshot.hasChild("right")) {

                                        String message = dataSnapshot.child("right").getValue(String.class);
                                        myMessage = "right";
                                        ChatBubble ChatBubble = new ChatBubble(message, myMessage, null);
                                        ChatBubbles.add(ChatBubble);
                                        adapter.notifyDataSetChanged();
                                    }
                                    if (dataSnapshot.hasChild("left")) {

                                        String message = dataSnapshot.child("left").getValue(String.class);

                                        myMessage = "left";
                                        ChatBubble ChatBubble = new ChatBubble(message, myMessage, null);
                                        ChatBubbles.add(ChatBubble);
                                        adapter.notifyDataSetChanged();

                                    }
                                    if (dataSnapshot.hasChild("image-right")) {
                                        String image = dataSnapshot.child("image-right").getValue(String.class);

                                        myMessage = "imageright";
                                        ChatBubble ChatBubble = new ChatBubble(null, myMessage, image);
                                        ChatBubbles.add(ChatBubble);
                                        adapter.notifyDataSetChanged();

                                    }
                                    if (dataSnapshot.hasChild("image-left")) {
                                        String image = dataSnapshot.child("image-left").getValue(String.class);
                                        myMessage = "imageleft";
                                        ChatBubble ChatBubble = new ChatBubble(null, myMessage, image);
                                        ChatBubbles.add(ChatBubble);
                                        adapter.notifyDataSetChanged();

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            n = Integer.parseInt(count);
                            ++n;
                            if(n==51)
                            {
                                Toast.makeText(getApplicationContext(), "Story ends", Toast.LENGTH_SHORT).show();
                            }
                            count = Integer.toString(n);
                            break;

                        case MotionEvent.ACTION_UP:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }


                }
                else
                {
                    loadFragment(new BlankFragment());
                }
                v.onTouchEvent(event);
                return true;
            }
        });
    }
    protected boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
    private void loadFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

}


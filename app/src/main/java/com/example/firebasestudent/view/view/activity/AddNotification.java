package com.example.firebasestudent.view.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.firebasestudent.Service.MySingleton;
import com.example.firebasestudent.R;
import com.example.firebasestudent.databinding.ActivityAddNotificationBinding;
import com.example.firebasestudent.model.Notice;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddNotification extends AppCompatActivity {


    ActivityAddNotificationBinding binding;
    private ArrayList<Notice> notices;
    private String currentDateTimeString ;

    private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    private String serverKey = "key=" + "AIzaSyCqjwt0VdXjOoa7muKyNQ3N2Big6G8jEpg";
    private String contentType = "application/json";

    final String TAG = "NOTIFICATION TAG";
    String TOPIC;
//    String NOTIFICATION_TITLE;
//    String NOTIFICATIon_MESSAGE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notification);

        getSupportActionBar().hide();

        binding = DataBindingUtil.setContentView(this,R.layout.activity_add_notification);

        FirebaseMessaging.getInstance().subscribeToTopic("userABC");
        String posotion = this.getIntent().getStringExtra("Position");

        binding.imagebtnClassBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference NotiRef = firebaseDatabase.getReference().child("school").child("classes").child(posotion).child("notice");
        NotiRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Notification_LongDinh",dataSnapshot.toString());
                notices = new ArrayList<>();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Notice notice = snapshot.getValue(Notice.class);
                    notices.add(notice);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        binding.buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TOPIC = "/topics/userABC";
                String title =  binding.editTextTitle.getText().toString();
                String content = binding.editTextContent.getText().toString();

                JSONObject notification = new JSONObject();
                JSONObject notiBody = new JSONObject();

                try {
                    notiBody.put("title", title);
                    notiBody.put("message", content);

                    notification.put("to", TOPIC);
                    notification.put("data", notiBody);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                sendNotification(notification);
                currentDateTimeString = DateFormat.getDateInstance().format(new Date());
                notices.add(new Notice(1,content,currentDateTimeString));

                NotiRef.setValue(notices);
                finish();
            }
        });
    }

    private void sendNotification(JSONObject notification){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i(TAG, "onRespone:" + response.toString());
//                        edt1.setText("");
//                        edt2.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddNotification.this, "loi", Toast.LENGTH_SHORT).show();
                        Log.i(TAG,  "onError");
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }
}

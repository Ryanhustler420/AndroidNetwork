package com.example.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String Url = "https://jsonplaceholder.typicode.com/posts/1/comments";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);

        // will get a Json Array
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
              for(int index=0; index < response.length(); index++) {
                  try {
                      JSONObject commentObject = response.getJSONObject(index);
                      Log.d("email: ", commentObject.getString("email") + "/"
                              + "Body: " + commentObject.getString("body"));
                  } catch (JSONException e) {
                      e.printStackTrace();
                  }
              }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: ", error.getMessage());
            }
        });

        // we have to add request to queue
        queue.add(arrayRequest);
    }
}

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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private final static String eq_url = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02";
    private final static String paulStringUrl = "http://magadistudio.com/complete-android-developer-course-source-files/string.html";
    private static final String Url = "https://jsonplaceholder.typicode.com/posts/1/comments";
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);
        // getStringObject(paulStringUrl);
        this.getJsonObject(eq_url);
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
//        queue.add(arrayRequest);
    }

    // fetch string request
    public void getStringObject(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, paulStringUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Message", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Error: ", error.getMessage());
            }
        });
        queue.add(stringRequest);
    }

    private void getJsonObject(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url , null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("Object: ", response.getString("type"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error: ", error.getMessage());
            }
        });

        queue.add(jsonObjectRequest);
    }
}

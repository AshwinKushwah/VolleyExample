package com.example.advanced.volleyexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private static final String REQUESTTAG = "string request first";
    private Button btnSendRequest;
    private RequestQueue mRequestQueue;
    private StringRequest stringRequest;
    private String url = "http://www.mocky.io/v2/5b8fa6ad2e00006600a89b5f";

    private DiskBasedCache mCache;
    private com.android.volley.Network mNetwork;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       btnSendRequest=(Button) findViewById(R.id.btnSendRequest);
        btnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //

                sendRequestAndPrintResponse();
            }
        });
    }

    private  void  sendRequestAndPrintResponse(){
        //1.type Obtaining RequestQueue
        // mRequestQueue = Volley.newRequestQueue(this);

        //2.type Obtaining RequestQueue
        //mCache = new DiskBasedCache(getCacheDir(), 4*1024*1024); // cache size is 4MB
        //mNetwork = new BasicNetwork(new HurlStack());

        //mRequestQueue = new RequestQueue(mCache, mNetwork); //created our RequestQueue & Not using the Volley's RequestQueue
        //mRequestQueue.start(); //for our RequestQueue we have to call start()

        //3.type Obtaining RequestQueue
        //below is a case when multiple activity uses the same RequestQueue and we have obtained the RequesQueue using Singleton Pattern
        mRequestQueue = VolleySingleton.getInstance(this.getApplicationContext()).getRequestQueue(this.getApplicationContext());

          stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.i(TAG,"Response" + response.toString());

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "Error" + error.toString());
            }
        });

        stringRequest.setTag(REQUESTTAG);
        mRequestQueue.add(stringRequest);

    }
    @Override
    protected void onStop(){
        super.onStop();

        if(mRequestQueue!=null)
        {
            mRequestQueue.cancelAll(REQUESTTAG);
        }
    }
}

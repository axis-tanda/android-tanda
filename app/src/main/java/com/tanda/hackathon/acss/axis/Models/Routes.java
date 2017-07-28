package com.tanda.hackathon.acss.axis.Models;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kean on 7/29/17.
 */

public class Routes {
    public void testPing(RequestQueue rq) {
        String testUrl = "https://web-tanda-api.herokuapp.com/api/tanda/clockin";
        StringRequest testReq = new StringRequest(Request.Method.POST, testUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // response
                Log.d("Response", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // error
                Log.d("Error.Response", error.toString());
            }
        }) {

            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", "Shitbols");

                return params;
            }
        };
        rq.add(testReq);
    }
}

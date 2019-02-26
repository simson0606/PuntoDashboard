package com.example.simon.puntodashboard

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

/**
 * Created by simon on 07-02-19.
 */
    class IODevice {
    val _context : Context
    val _base_url : String
    val _output_extension = "/pulse?params="
    val _input_extension = "/read?params="

    constructor(context: Context, base_url : String){
        _context = context
        _base_url = base_url
    }

    fun pulse(output: String) {
        val queue = Volley.newRequestQueue(_context)
        val url = _base_url + _output_extension + output

        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    _context.toast("pulse $output success")//$output : $response
                },
                Response.ErrorListener {
                    _context.toast("pulse $output : failed")

                })

        queue.add(stringRequest)
    }

    fun Context.toast(message: CharSequence) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    fun read(input: String, callback: (Boolean) -> Unit) {
        val queue = Volley.newRequestQueue(_context)
        val url = _base_url + _input_extension + input

        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    val readResult = Gson().fromJson(response, ReadResult::class.java)

                    callback(readResult.return_value == 1)
                 //   _context.toast("read $input : $response")
                },
                Response.ErrorListener {
                    _context.toast("read $input : failed")

                })

        queue.add(stringRequest)
    }
}
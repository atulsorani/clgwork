package net.simplifiedcoding.androidphpmysql

import android.R
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.provider.SyncStateContract
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var editTextUsername: EditText? = null
    private var editTextEmail: EditText? = null
    private var editTextPassword: EditText? = null
    private var buttonRegister: Button? = null
    private var progressDialog: ProgressDialog? = null
    private var textViewLogin: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish()
            startActivity(Intent(this, ProfileActivity::class.java))
            return
        }
        editTextEmail = findViewById<View>(R.id.editTextEmail) as EditText
        editTextUsername = findViewById<View>(R.id.editTextUsername) as EditText
        editTextPassword = findViewById<View>(R.id.editTextPassword) as EditText
        textViewLogin = findViewById<View>(R.id.textViewLogin) as TextView
        buttonRegister = findViewById<View>(R.id.buttonRegister) as Button
        progressDialog = ProgressDialog(this)
        buttonRegister!!.setOnClickListener(this)
        textViewLogin!!.setOnClickListener(this)
    }

    private fun registerUser() {
        val email = editTextEmail!!.text.toString().trim { it <= ' ' }
        val username = editTextUsername!!.text.toString().trim { it <= ' ' }
        val password = editTextPassword!!.text.toString().trim { it <= ' ' }
        progressDialog!!.setMessage("Registering user...")
        progressDialog!!.show()
        val stringRequest: StringRequest = object : StringRequest(Request.Method.POST,
            SyncStateContract.Constants.URL_REGISTER,
            object : Listener<String?>() {
                fun onResponse(response: String?) {
                    progressDialog!!.dismiss()
                    try {
                        val jsonObject = JSONObject(response)
                        Toast.makeText(
                            applicationContext,
                            jsonObject.getString("message"),
                            Toast.LENGTH_LONG
                        ).show()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            },
            object : ErrorListener() {
                fun onErrorResponse(error: VolleyError) {
                    progressDialog!!.hide()
                    Toast.makeText(applicationContext, error.getMessage(), Toast.LENGTH_LONG).show()
                }
            }) {
            @get:Throws(AuthFailureError::class)
            protected val params: Map<String, String>
                protected get() {
                    val params: MutableMap<String, String> = HashMap()
                    params["username"] = username
                    params["email"] = email
                    params["password"] = password
                    return params
                }
        }
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest)
    }

    override fun onClick(view: View) {
        if (view === buttonRegister) registerUser()
        if (view === textViewLogin) startActivity(Intent(this, LoginActivity::class.java))
    }
}
package telecom.marcus.appmrsmarcus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private AppCompatEditText name, name_user, registration, password, c_password;
    private String function, getId, extRegistration;
    private AppCompatButton btn_regist;
    private RadioGroup radioGroup_function;
    ProgressBar loading;
    SessionManager sessionManager;
    private static String URL_REGIST = "http://192.168.2.120/bd_mrs/register.php";
    private static String URL_EDIT = "http://192.168.2.120/bd_mrs/edit_detail.php";

    private final String TAG = "REGISTRO";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle(R.string.txt_bt_register);

        sessionManager = new SessionManager(this);

        loading = findViewById(R.id.loading);

        btn_regist = findViewById(R.id.btn_register);
        radioGroup_function = findViewById(R.id.radio_group_funtion);

        name = findViewById(R.id.name);
        name_user = findViewById(R.id.name_user);
        registration = findViewById(R.id.registration);
        password = findViewById(R.id.password);
        c_password = findViewById(R.id.c_password);

        Intent intent = getIntent();

        final Boolean newUser = intent.getBooleanExtra("newUser", true);

        if (newUser) {
            setTitle(R.string.title_layout_register_new);
        } else {
            getId = intent.getStringExtra("id");
            setTitle(R.string.title_layout_register_edit);
            btn_regist.setText(R.string.txt_bt_save_edit);
        }

        radioGroup_function.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radio_function_mk:
                        function = "1";
                        break;

                    case R.id.radio_function_au:
                        function = "2";
                        break;
                }

            }
        });

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newUser) {
                    regist();
                } else {
                    saveEditDetail();
                }
            }
        });

    }

    private void saveEditDetail() {

        final String name = this.name.getText().toString().trim();
        final String name_user = this.name_user.getText().toString().trim();
        final String registration = this.registration.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        final String id = getId;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();
        btn_regist.setVisibility(View.GONE);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");
                            if (success.equals("1")) {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                sessionManager.createSession(id, name, name_user, registration, function);
                                progressDialog.dismiss();
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Error " + e.toString(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            btn_regist.setVisibility(View.VISIBLE);

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Error " + error.toString(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        btn_regist.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("name_user", name_user);
                params.put("registration",registration);
                params.put("function",function);
                params.put("password", password);
                params.put("id",id);

                return params;

            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void regist() {

        final String name = this.name.getText().toString().trim();
        final String name_user = this.name_user.getText().toString().trim();
        final String registration = this.registration.getText().toString().trim();
        final String password = this.password.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();
        btn_regist.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            String message = jsonObject.getString("message");

                            if (success.equals("1")) {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                finish();
                            } else if (success.equals("2")) {
                                progressDialog.dismiss();
                                btn_regist.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), "Error!!! \n" + message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Register Error!" + e.toString(), Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            btn_regist.setVisibility(View.VISIBLE);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Register Error! " + error.toString(), Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                        btn_regist.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("name_user", name_user);
                params.put("registration",registration);
                params.put("function",function);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}

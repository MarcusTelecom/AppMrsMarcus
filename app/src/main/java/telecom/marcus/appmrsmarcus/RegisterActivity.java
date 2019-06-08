package telecom.marcus.appmrsmarcus;

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
    private String function;
    private AppCompatButton btn_regist;
    private RadioGroup radioGroup_function;
    ProgressBar loading;
    private static String URL_REGIST = "http://192.168.2.120/bd_mrs/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle(R.string.txt_bt_register);

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
            setTitle(R.string.title_layout_register_edit);
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

                }
            }
        });

    }

    private void regist() {
        loading.setVisibility(View.VISIBLE);
        btn_regist.setEnabled(false);

        final String name = this.name.getText().toString().trim();
        final String name_user = this.name_user.getText().toString().trim();
        final String registration = this.registration.getText().toString();
        final String password = this.password.getText().toString();

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
                            } else if (success.equals("2")) {
                                Toast.makeText(getApplicationContext(), "Error!!! \n" + message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Register Error!" + e.toString(), Toast.LENGTH_LONG).show();
                            loading.setVisibility(View.GONE);
                            btn_regist.setEnabled(false);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Register Error!" + error.toString(), Toast.LENGTH_LONG).show();
                        loading.setVisibility(View.GONE);
                        btn_regist.setEnabled(true);
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

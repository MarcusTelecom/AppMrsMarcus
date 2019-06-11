package telecom.marcus.appmrsmarcus.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import telecom.marcus.appmrsmarcus.Classes.ClassUser;
import telecom.marcus.appmrsmarcus.R;
import telecom.marcus.appmrsmarcus.RecyclerAdapters.RecyclerAdapter;

public class RecyclerViewAux extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<ClassUser> list = new ArrayList<>();
    private ClassUser user;
    private static String URL_LIST_USERS = "http://192.168.2.120/bd_mrs/list_users.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_aux);

        recyclerView = findViewById(R.id.recyclerViewUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);


        list_user();


    }

    private void list_user() {

        final ProgressDialog proggressDialog = new ProgressDialog(this);
        proggressDialog.setMessage("Loading List...");
        //proggressDialog.show();
        final Intent intent = getIntent();
        final String function = intent.getStringExtra("function");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LIST_USERS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("users");
                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id = object.getString("id").trim();
                                    String name = object.getString("name").trim();
                                    String name_user = object.getString("name_user").trim();
                                    String registration = object.getString("registration").trim();
                                    String function = object.getString("function").trim();

                                    user = new ClassUser(id, name, name_user, registration, function);
                                    list.add(user);
                                    adapter.notifyDataSetChanged();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "nok", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Erro ao buscar lista 1" + e, Toast.LENGTH_LONG).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Erro ao buscar lista 2" + error, Toast.LENGTH_LONG).show();

                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("function", function);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

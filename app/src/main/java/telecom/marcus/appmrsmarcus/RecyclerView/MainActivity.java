package telecom.marcus.appmrsmarcus.RecyclerView;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import telecom.marcus.appmrsmarcus.Classes.ClassPDFs;
import telecom.marcus.appmrsmarcus.R;
import telecom.marcus.appmrsmarcus.RecyclerAdapters.RecyclerAdapterPDF;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView1;
    private RecyclerAdapterPDF adapter;
    private List<ClassPDFs> list = new ArrayList<>();
    private ClassPDFs pdf;
    private static String URL_LIST_PDF = "http://192.168.2.120/bd_mrs/list_pdf.php";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView1 = findViewById(R.id.recyclerViewPDF);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RecyclerAdapterPDF(list);
        recyclerView1.setAdapter(adapter);

        list.clear();
        list_pdf();
    }

    private void list_pdf() {
        final ProgressDialog proggressDialog = new ProgressDialog(this);
        proggressDialog.setMessage("Loading List...");
        //proggressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LIST_PDF,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("pdf");
                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id_pdf = object.getString("id_pdf").trim();
                                    String title = object.getString("title").trim();
                                    String description = object.getString("description").trim();
                                    String insert_date = object.getString("insert_date").trim();
                                    String url = object.getString("url").trim();

                                    pdf = new ClassPDFs(id_pdf, title, description, insert_date, url);
                                    list.add(pdf);
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


        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

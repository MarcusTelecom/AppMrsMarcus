package telecom.marcus.appmrsmarcus;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerAdapter  extends RecyclerView.Adapter<ViewHolder> {

    private List<ClassUser> list;

    public RecyclerAdapter(List<ClassUser> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ClassUser classUser = list.get(i);

        viewHolder.item_name.setText(classUser.getName());
        viewHolder.item_registration.setText(classUser.getRegistration());
        //new DownloadImageTask(viewHolder.featuredImage).execute(classUser.getUrl());
        viewHolder.btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("teste", classUser.getRegistration());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

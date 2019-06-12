package telecom.marcus.appmrsmarcus.RecyclerAdapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import telecom.marcus.appmrsmarcus.Classes.ClassUser;
import telecom.marcus.appmrsmarcus.R;
import telecom.marcus.appmrsmarcus.ViewHolders.ViewHolderAux;

public class RecyclerAdapterAux extends RecyclerView.Adapter<ViewHolderAux> {

    private List<ClassUser> list;

    public RecyclerAdapterAux(List<ClassUser> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderAux onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        return new ViewHolderAux(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAux viewHolder, int i) {
        final ClassUser classUser = list.get(i);

        viewHolder.item_name.setText(classUser.getName());
        viewHolder.item_registration.setText(classUser.getRegistration());
       // new DownloadImageTask(viewHolder.featuredImage).execute("http://192.168.2.120/bd_mrs/profile_image/fabrica-de-aplicativos.png");
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

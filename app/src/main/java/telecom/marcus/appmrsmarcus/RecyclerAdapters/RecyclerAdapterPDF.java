package telecom.marcus.appmrsmarcus.RecyclerAdapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import telecom.marcus.appmrsmarcus.Classes.ClassPDFs;
import telecom.marcus.appmrsmarcus.R;
import telecom.marcus.appmrsmarcus.ViewHolders.ViewHolderPDF;

public class RecyclerAdapterPDF extends RecyclerView.Adapter<ViewHolderPDF> {
    private List<ClassPDFs> list;

    public RecyclerAdapterPDF(List<ClassPDFs> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderPDF onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pdf,viewGroup,false);
        return new ViewHolderPDF(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPDF viewHolderPDF, int i) {
        final ClassPDFs classPDFs= list.get(i);

        viewHolderPDF.item_pdf_title.setText(classPDFs.getTitle());
        viewHolderPDF.item_pdf_description.setText(classPDFs.getDescription());
        viewHolderPDF.item_pdf_insert_date.setText(classPDFs.getInsert_date());
        // new DownloadImageTask(viewHolder.featuredImage).execute("http://192.168.2.120/bd_mrs/profile_image/fabrica-de-aplicativos.png");
        viewHolderPDF.btn_pdf_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("teste", classPDFs.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

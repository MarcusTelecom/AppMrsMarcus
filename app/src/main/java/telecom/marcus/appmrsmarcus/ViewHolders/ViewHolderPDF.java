package telecom.marcus.appmrsmarcus.ViewHolders;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import telecom.marcus.appmrsmarcus.R;

public class ViewHolderPDF extends RecyclerView.ViewHolder {

    public ImageView featuredImagePDF;
    public TextView item_pdf_title;
    public TextView item_pdf_description;
    public TextView item_pdf_insert_date;
    public AppCompatButton btn_pdf_down;

    public ViewHolderPDF(View itemView) {
        super(itemView);

        this.featuredImagePDF = itemView.findViewById(R.id.featuredImagePdf);
        this.item_pdf_title = itemView.findViewById(R.id.item_pdf_title);
        this.item_pdf_description = itemView.findViewById(R.id.item_pdf_description);
        this.item_pdf_insert_date = itemView.findViewById(R.id.item_pdf_insert_date);
        this.btn_pdf_down = itemView.findViewById(R.id.btn_pdf_down);
    }
}
package telecom.marcus.appmrsmarcus.ViewHolders;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import telecom.marcus.appmrsmarcus.R;

public class ViewHolderAux extends RecyclerView.ViewHolder {

    public ImageView featuredImage;
    public TextView item_name;
    public TextView item_registration;
    public AppCompatButton btn_select;

    public ViewHolderAux(View itemView) {
        super(itemView);

        this.featuredImage = itemView.findViewById(R.id.featuredImage);
        this.item_name = itemView.findViewById(R.id.item_name);
        this.item_registration = itemView.findViewById(R.id.item_registration);
        this.btn_select = itemView.findViewById(R.id.btn_select);
    }
}
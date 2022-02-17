package MyData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import noor.application.noortaskm.R;

public class MyTaskAdapter extends ArrayAdapter<Mytask> {//وسيط بين مصدر المعطيات وعرضها على الشاشة وهي عبارة عن فئة وارثة لفئة من نوع adapter

    public MyTaskAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//ملائمة المعطى وطريقة عرضه..تأخذ المعطى من قاعدة البيانات وبناء واجهة وعرضها على الواجهة

        //View view= View.inflate(getContext(), R.layout.task_item_layout,parent);
        View view= LayoutInflater.from(getContext()).inflate(R.layout.task_item_layout,parent,false);

        Mytask item = getItem(position);
        TextView title=view.findViewById(R.id.TVitem);
        TextView subject=view.findViewById(R.id.TVitem2);
        ImageButton edit=view.findViewById(R.id.IBedit);
        ImageButton rate=view.findViewById(R.id.IBrate);
        ImageButton call=view.findViewById(R.id.IBcall);
        RatingBar ratingBar=view.findViewById(R.id.RBtask);
        ImageView image=view.findViewById(R.id.IVTask);
        //وضع قيم المعطى المستخرج على كائنات الواجهة
        title.setText(item.getTitle());
        subject.setText(item.getSubject());
        ratingBar.setRating(5*(item.getImportant()/(float)10));
        return view;
    }
}

package com.example.myzing.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myzing.Model.Library;
import com.example.myzing.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LibraryAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Library> listLibrary;

    public LibraryAdapter(Context context, int layout, List<Library> listLibrary) {
        this.context = context;
        this.layout = layout;
        this.listLibrary = listLibrary;
    }

    @Override
    public int getCount() {
        return listLibrary.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        CircleImageView imageViewIconLibrary;
        TextView textViewNameLibrary;
        TextView textViewNumberOfOneLibrary;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder = new ViewHolder();
            //ánh xạ view
            viewHolder.imageViewIconLibrary = convertView.findViewById(R.id.imageview_icon_library);
            viewHolder.textViewNameLibrary = convertView.findViewById(R.id.tv_name_library);
            viewHolder.textViewNumberOfOneLibrary = convertView.findViewById(R.id.tv_number_of_one_library);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //gan gia tri

        Library library = listLibrary.get(position);
        viewHolder.imageViewIconLibrary.setImageResource(library.getIcon());
        viewHolder.textViewNameLibrary.setText(library.getNameLibrary());
        viewHolder.textViewNumberOfOneLibrary.setText(library.getNumberOfOneLibrary());
        return convertView;
    }
}

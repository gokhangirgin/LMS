package com.bitirme.lms;

import java.util.ArrayList;
import java.util.HashMap;



import android.R.drawable;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LazyAdapter2 extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    
    
    public LazyAdapter2(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.item2, null);

        TextView id = (TextView)vi.findViewById(R.id.textId); 
        TextView dtext = (TextView)vi.findViewById(R.id.txtDetail); 
      
        ImageView image = (ImageView) vi.findViewById(R.id.imageView1);
        
//        pbar.getProgressDrawable().setColorFilter(Color.BLUE, Mode.SRC);
        
        
      
        Button detail = (Button)vi.findViewById(R.id.btnDetail); 
        
        HashMap<String, String> item = new HashMap<String, String>();
        item = data.get(position);
        
        // Setting all values in listview
        id.setText(item.get("id"));
        
//        pbar.setMax(100);
//        pbar.setProgress(Integer.parseInt(item.get("process")));
        
        dtext.setText(""+item.get("location"));
        image.setImageResource(Integer.parseInt(item.get("image"))) ;

      
        return vi;
    }
}
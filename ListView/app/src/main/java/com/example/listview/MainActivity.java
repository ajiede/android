package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private PopupWindow pw;
    private View pwView;

    private ListView lv;
    private ArrayList<Spot> spots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView) findViewById(R.id.listview);
        spots = new ArrayList<>();
        spots.add(new Spot(R.drawable.f1,"景点名——————1","景点介绍——————2"));
        spots.add(new Spot(R.drawable.f2,"景点名——————1","景点介绍——————2"));
        spots.add(new Spot(R.drawable.f3,"景点名——————1","景点介绍——————2"));
        spots.add(new Spot(R.drawable.f4,"景点名——————1","景点介绍——————2"));
        spots.add(new Spot(R.drawable.f5,"景点名——————1","景点介绍——————2"));
        spots.add(new Spot(R.drawable.f6,"景点名——————1","景点介绍——————2"));
        spots.add(new Spot(R.drawable.f7,"景点名——————1","景点介绍——————2"));

        MyAdapter myAdapter = new MyAdapter();
        lv.setAdapter(myAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                if(pw == null){
                    pwView = view.inflate(MainActivity.this, R.layout.pw_layout, null);
                    pwView.findViewById(R.id.zx).setOnClickListener(MainActivity.this);
                    pwView.findViewById(R.id.cf).setOnClickListener(MainActivity.this);
                    pwView.findViewById(R.id.fx).setOnClickListener(MainActivity.this);

                    pw = new PopupWindow(pwView,view.getWidth()-100,view.getHeight()-80);
                    pw.setBackgroundDrawable(new BitmapDrawable());
                }
                if(pw.isShowing()){
                    pw.dismiss();
                }
                pw.showAsDropDown(view,40,-view.getHeight());
                ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1);
                scaleAnimation.setDuration(1000);
                pwView.startAnimation(scaleAnimation);
            }
        });

    }
    class MyAdapter extends BaseAdapter {

        private ViewHolder ho;
        @Override
        public int getCount() {
            return spots.size();
        }

        @Override
        public Object getItem(int position) {
            return spots.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView = View.inflate(MainActivity.this,R.layout.item_adapter,null);

                ho = new ViewHolder();
                ho.im = (ImageView)convertView.findViewById(R.id.iv);
                ho.tv1 = (TextView)convertView.findViewById(R.id.tv1);
                ho.tv2 = (TextView)convertView.findViewById(R.id.tv2);

                convertView.setTag(ho);
            }else {
                ho= (ViewHolder) convertView.getTag();
            }

            Spot sp = (Spot) getItem(position);
            ho.im.setImageResource(sp.getPho());
            ho.tv1.setText(sp.getName());
            ho.tv2.setText(sp.getDsc());
//            Glide.with(MainActivity.this)
//                            .load(sp.getPho())
//                            .into(im);




            return convertView;
        }

    }
    static class ViewHolder{
        public ImageView im;
        public TextView tv1;
        public TextView tv2;




    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.zx:
                pw.dismiss();
                Toast.makeText(this, "咨询", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cf:
                pw.dismiss();
                Toast.makeText(this, "出发", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.fx:
                pw.dismiss();
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
        }
    }
}
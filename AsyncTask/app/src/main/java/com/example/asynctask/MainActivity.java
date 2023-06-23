package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private List<Map<String, Object>> allFileItems =
            new ArrayList<>();
    private SimpleAdapter simple = null;
    private ListView fileList = null;
    private ListFileThread ft = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        this.fileList = (ListView) super.findViewById(R.id.list);
        File filePath = new File(java.io.File.separator);
        this.ft = new ListFileThread();
        this.ft.execute(filePath);
        this.fileList.setOnItemClickListener(new OnItemClickListenerImpl());
    }

    private class OnItemClickListenerImpl implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view,
                                int i, long l) {
            File currFile = (File) MainActivity.this.allFileItems
                    .get(i).get("name");
            if(currFile.isDirectory()){
                MainActivity.this.allFileItems =
                        new ArrayList<Map<String, Object>>();
                ListFileThread ft = new ListFileThread();
                ft.execute(currFile);
            }
        }
    }

    private class ListFileThread extends AsyncTask<File,File,String> {

        @Override
        protected void onProgressUpdate(File... files) {
            Map<String, Object> fileItem = new HashMap<String, Object>();
            if(files[0].isDirectory()){
                fileItem.put("img",R.drawable.close);
            }else{
                fileItem.put("img",R.drawable.file);
            }
            fileItem.put("name",files[0]);
            MainActivity.this.allFileItems.add(fileItem);
            MainActivity.this.simple = new SimpleAdapter(
                    MainActivity.this,
                    allFileItems,
                    R.layout.file_list,
                    new String[]{"img","name"},
                    new int[]{R.id.img,R.id.name});
            MainActivity.this.fileList
                    .setAdapter(MainActivity.this.simple);
        }

        @Override
        protected String doInBackground(File... params) {
            if(!params[0].getPath().equals(java.io.File.separator)){
                Map<String, Object> fileItem = new HashMap<String, Object>();
                fileItem.put("img",R.drawable.open);
                fileItem.put("name",params[0].getParentFile());
                MainActivity.this.allFileItems.add(fileItem);
            }
            if(params[0].isDirectory()){
                File tempFile[] = params[0].listFiles();
                if(tempFile != null){
                    for(int x = 0; x < tempFile.length; x++){
                        this.publishProgress(tempFile[x]);
                    }
                }
            }
            return "文件已列出";
        }

    }



}
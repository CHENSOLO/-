package com.example.wenjian.wenjianshuju;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by Administrator on 2017/12/4.
 */

 public class TwoActivity extends AppCompatActivity implements View.OnClickListener {
     private EditText ET_input,ET_input_two;
     private Button  BT_save,BT_clean,BT_read;
     private Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        mContext = getApplicationContext();
        bindViews();
    }

    private void bindViews() {
        ET_input = findViewById(R.id.ET_input);
        ET_input_two = findViewById(R.id.ET_input_two);
        BT_save = findViewById(R.id.BT_save);
        BT_clean = findViewById(R.id.BT_clean);
        BT_read = findViewById(R.id.BT_read);

        BT_save.setOnClickListener(this);
        BT_clean.setOnClickListener(this);
        BT_read.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.BT_clean:
                 ET_input.setText("");
                 ET_input_two.setText("");
                 break;

             case R.id.BT_save:
                 String filename = ET_input.getText().toString();
                 String filedetail = ET_input_two.getText().toString();
                 SDFileHelper sdHelper = new SDFileHelper(mContext);

                 try {
                     sdHelper.saveFileToSD(filename,filedetail);
                 } catch (IOException e) {
                     e.printStackTrace();
                 }

                 try {
                     Toast.makeText(getApplicationContext(),"数据写入成功",Toast.LENGTH_SHORT).show();
                 } catch (Exception e) {
                     e.printStackTrace();
                     Toast.makeText(getApplicationContext(),"数据写入失败",Toast.LENGTH_SHORT).show();
                 }
                 break;
             case R.id.BT_read:
                 String detail = "";
                 SDFileHelper sdHelper2= new SDFileHelper(mContext);

                 String filename2 = null;
                 try {
                     filename2 = ET_input.getText().toString();
                     detail = sdHelper2.readFromSD(filename2);
                 } catch (Exception e) {
                     e.printStackTrace();
                     Toast.makeText(getApplicationContext(),detail,Toast.LENGTH_SHORT).show();
                     break;
                 }

    }
    }
}

package com.example.wenjian.wenjianshuju;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText ET_ONE,ET_TWO;
    private Button BT_ONE,BT_TWO,BT_THREE,BT_open;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();
        bindView();
    }

    private void bindView() {
           ET_ONE = findViewById(R.id.ET_ONE);
           ET_TWO = findViewById(R.id.ET_TWO);
           BT_ONE = findViewById(R.id.BT_ONE);
           BT_TWO = findViewById(R.id.BT_ONE);
           BT_THREE = findViewById(R.id.BT_THREE);

           BT_open = findViewById(R.id.open);

           BT_open.setOnClickListener(this);
           BT_ONE.setOnClickListener(this);
           BT_TWO.setOnClickListener(this);
           BT_THREE.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BT_TWO:
          ET_ONE.setText("");
          ET_TWO.setText("");
          break;
            case R.id.BT_ONE:
                FileHelper fHelper = new FileHelper(mContext);
                String filename =ET_ONE.getText().toString();
                String filedetail = ET_TWO.getText().toString();
                try {
                    fHelper.save(filename,filedetail);
                    Toast.makeText(getApplicationContext(),"数据写入成功",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"数据写入失败",Toast.LENGTH_SHORT).show();
                }
                 break;
            case R.id.BT_THREE:
                String detail = "";
                FileHelper fHelper2 = new FileHelper(getApplicationContext());
                try {
                    String fname = ET_ONE.getText().toString();
                    detail = fHelper2.read(fname);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),detail,Toast.LENGTH_SHORT).show();
             break;

            case R.id.open:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,TwoActivity.class);
                startActivity(intent);
                break;
       }
    }
}

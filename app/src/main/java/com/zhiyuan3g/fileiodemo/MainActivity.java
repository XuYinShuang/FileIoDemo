package com.zhiyuan3g.fileiodemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiyuan3g.fileiodemo.unils.IOHelper;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText txtFileName;
    Button btnCreate;
    TextView txtShowMessage;
    Button btnReadData;
    Button btnWriteData;
    EditText txtInputData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先判断SDCard的状态，是否为已加载状态
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    //SDCard存在，可以进行下一步的操作了
                    String fileName = txtFileName.getText().toString();
                    //获得SDCard的根路径(目录，文件夹)
                    String rootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                    String filePath = rootPath + "/" + fileName;
                    File file = new File(filePath);
//                    File file2 = new File(rootPath,fileName);
                    if (file.exists()) {
                        Toast.makeText(MainActivity.this, "文件已存在", Toast.LENGTH_SHORT).show();
                        txtShowMessage.setText("文件已存在");
                    } else {
                        Toast.makeText(MainActivity.this, "文件不存在", Toast.LENGTH_SHORT).show();
                        txtShowMessage.setText("文件不存在");
                        try {
                            file.createNewFile();
                            Toast.makeText(MainActivity.this, "创建文件成功！", Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });

        btnWriteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = txtInputData.getText().toString();
                try {
                    boolean result = IOHelper.writeData(txtFileName.getText().toString(),data);
                    if(result){
                        Toast.makeText(MainActivity.this, "写入数据成功！", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "写入数据失败！", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String content = IOHelper.readData(txtFileName.getText().toString());
                    txtShowMessage.setText(content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initView() {
        txtFileName = (EditText) findViewById(R.id.txtFileName);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        txtShowMessage = (TextView) findViewById(R.id.txtShowMessage);
        btnReadData = (Button) findViewById(R.id.btnReadData);
        btnWriteData = (Button) findViewById(R.id.btnWriteData);
        txtInputData = (EditText) findViewById(R.id.txtInputData);
    }
}

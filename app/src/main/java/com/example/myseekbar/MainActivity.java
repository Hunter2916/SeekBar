package com.example.myseekbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView recode;
    private Dialog mDialog;
    private Button bt_confirm;
    private Button bt_cancel;
    private TextView tv;
    private TextView cross;
    private SeekBar seekBar;
    private ImageView unlock;
    private ImageView slidejiantou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        TextView TextView = (TextView) findViewById(R.id.recode);


        TextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener listener;
                listener = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stubf
                        finish();
                    }
                };
                final AlertDialog builder = new AlertDialog.Builder(MainActivity.this)
                        .create();
                builder.setCancelable(false);

                builder.show();
                builder.getWindow().setContentView(R.layout.seekbar);//设置弹出框加载的布局

                slidejiantou = (ImageView) builder.findViewById(R.id.slidejiantou);
                tv = (TextView) builder.findViewById(R.id.tv);
                cross = (TextView) builder.findViewById(R.id.cross);
                seekBar = (SeekBar) builder.findViewById(R.id.sbar);
                builder.getWindow()
                        .findViewById(R.id.cross)
                        .setOnClickListener(new View.OnClickListener() {  //按钮点击事件
                            @Override
                            public void onClick(View v) {
                                builder.dismiss();
                            }
                        });

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    /**
                     * 拖动条停止拖动的时候调用
                     */
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }

                    /**
                     * 拖动条开始拖动的时候调用
                     */
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        slidejiantou.setVisibility(View.GONE);
                    }

                    /**
                     * 拖动条进度改变的时候调用
                     */
                    @SuppressLint("UseCompatLoadingForDrawables")
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress,
                                                  boolean fromUser) {
                        if (seekBar.getProgress() == 100){
                            Rect oldRect = seekBar.getThumb().getBounds();//获取原来的Bounds构建一个Rect
                            Drawable drawable = getResources().getDrawable(R.mipmap.icon_seekbar_success);//新的图片转成drawable对象
                            drawable.setBounds(oldRect);//为新的图片对象添加Bounds
                            seekBar.setThumb(drawable);//设置新的图片
                            seekBar.setThumbOffset(PixelTool.dp2px(getApplicationContext(),32));
                        }
                        if (seekBar.getProgress() == seekBar.getMax()) {
                            tv.setVisibility(View.VISIBLE);
                            tv.setTextColor(Color.WHITE);
                            tv.setText("完成验证");
                            seekBar.setEnabled(false);

                        } else if (seekBar.getProgress() != 0) {
                            slidejiantou.setVisibility(View.GONE);
                        } else {
                            tv.setVisibility(View.GONE);
                        }
                    }

                });

            }


        });
    }
}
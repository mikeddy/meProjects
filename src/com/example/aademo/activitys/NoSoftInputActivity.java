package com.example.aademo.activitys;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aademo.R;

import java.util.ArrayList;

public class NoSoftInputActivity extends BaseActivity {

    ArrayList<Button> list_btns = new ArrayList<Button>();
    EditText edit_content;

    public static final int TYPE_NONE = 0;//不限制,任意输
    public static final int TYPE_NUM = 1;//数字的串，不带小数点，可用0开头；
    public static final int TYPE_CARD = 2;//银行卡以 6223 7856 5689 123 格式显示,不可有.-
    public static final int TYPE_RMB = 3;//人民币 非负数字,最前面加￥符号,后面加上.00,输入.的时候进入小数状态


    int type = TYPE_CARD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_softinput);
        init();
    }

    private void init() {
        list_btns.add((Button) findViewById(R.id.softinput_btn_0));
        list_btns.add((Button) findViewById(R.id.softinput_btn_1));
        list_btns.add((Button) findViewById(R.id.softinput_btn_2));
        list_btns.add((Button) findViewById(R.id.softinput_btn_3));
        list_btns.add((Button) findViewById(R.id.softinput_btn_4));
        list_btns.add((Button) findViewById(R.id.softinput_btn_5));
        list_btns.add((Button) findViewById(R.id.softinput_btn_6));
        list_btns.add((Button) findViewById(R.id.softinput_btn_7));
        list_btns.add((Button) findViewById(R.id.softinput_btn_8));
        list_btns.add((Button) findViewById(R.id.softinput_btn_9));
        list_btns.add((Button) findViewById(R.id.softinput_btn_0));

        list_btns.add((Button) findViewById(R.id.softinput_btn_point));
        list_btns.add((Button) findViewById(R.id.softinput_btn_tract));
        list_btns.add((Button) findViewById(R.id.softinput_btn_del));

        edit_content = (EditText) findViewById(R.id.softinput_edit_input);

        for (int i = 0; i < list_btns.size(); i++) {
            Button btn = list_btns.get(i);
            btn.setOnClickListener(btnListener);
            btn.setTag(getTag(btn.getId()));

        }
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Editable edittable = edit_content.getText();
            int index = edit_content.getSelectionEnd();
            if (isDel(v)) {
                if (index > 0) {
                    if (type == TYPE_CARD) {
                        edittable.delete(index - 1, index);
                        String card = edittable.toString();
                        StringBuffer sb = new StringBuffer();
                        char[] cCards = card.replaceAll(" ", "").toCharArray();
                        int length = cCards.length;
                        for (int i = 0; i < length; i++) {
                            if (i == 4 || i == 8 || i == 12) {  //逢4,8,12加空格(银行卡是4位一空)
                                sb.append(" ");
                            }
                            sb.append(cCards[i]);
                        }
                        edit_content.setText(sb.toString());
                        if (index == 4 + 2 || index == 8 + 3 || index == 12 + 4) {
                            edit_content.setSelection(index - 2);
                        } else {
                            edit_content.setSelection(index - 1);
                        }
                    }else if(type==TYPE_RMB){
                        if(edittable.charAt(index-1)=='￥')return;
                        if(index==2&&index==edittable.length()) edittable.delete(index - 2, index);
                        else{
                            edittable.delete(index - 1, index);
                        }
                    } else {
                        edittable.delete(index - 1, index);
                    }
                }
            } else {
                if (type == TYPE_NONE) {//不限制,任意输
                    edittable.insert(index, v.getTag().toString());
                } else if (type == TYPE_NUM) {//数字
                    if (!isPoint(v)) edittable.insert(index, v.getTag().toString());
                } else if (type == TYPE_CARD) {//银行卡
                    if (!isPoint(v) && !isTract(v) && edittable.length() < 19) {
                        edittable.insert(index, v.getTag().toString());
//						PalLog.printD(index + "=====");
                        String card = edittable.toString();
                        StringBuffer sb = new StringBuffer();
                        char[] cCards = card.replaceAll(" ", "").toCharArray();
                        int length = cCards.length;
                        for (int i = 0; i < length; i++) {
                            if (i == 4 || i == 8 || i == 12) {  //逢4,8,12加空格(银行卡是4位一空)
                                if (index == 4 || index == 8 + 1 || index == 12 + 2) index++;//逢4,8,12加空格之后,在8的位置已经多了一个空格的偏移量了,所以是8+1,在12的位置已经多了2个空格的偏移量了所以是12+2
                                sb.append(" ");
                            }
                            sb.append(cCards[i]);
                        }
                        edit_content.setText(sb.toString());
                        edit_content.setSelection(index + 1);

                    }
                } else if (type == TYPE_RMB) {
                    if (!isTract(v)) {
                        edittable.insert(index, v.getTag().toString());
                        String rmb = edittable.toString();
//                        String afterPoint="";
//                        int pointIndex=rmb.indexOf(".");
//                        if(pointIndex!=-1){
//                            afterPoint=rmb.substring(pointIndex);
//                            rmb=rmb.substring(0,pointIndex);
//                        }else{
//                            afterPoint=".00";
//                        }

                        StringBuffer sb = new StringBuffer();
                        char[] cCards = rmb.replace("￥", "").toCharArray();
                        int length = cCards.length;
                        for (int i = 0; i < length; i++) {
                            if (i == 0) sb.append("￥");
                            sb.append(cCards[i]);
                        }
//                            sb.append(afterPoint);
                        edit_content.setText(sb.toString());
                        if (index == 0) {
                            edit_content.setSelection(index + 2);
                        } else {
                            edit_content.setSelection(index + 1);
                        }
                    }
                }
            }

        }
    };

    public String getTag(int vid) {
        switch (vid) {
            case R.id.softinput_btn_0:
                return "0";
            case R.id.softinput_btn_1:
                return "1";
            case R.id.softinput_btn_2:
                return "2";
            case R.id.softinput_btn_3:
                return "3";
            case R.id.softinput_btn_4:
                return "4";
            case R.id.softinput_btn_5:
                return "5";
            case R.id.softinput_btn_6:
                return "6";
            case R.id.softinput_btn_7:
                return "7";
            case R.id.softinput_btn_8:
                return "8";
            case R.id.softinput_btn_9:
                return "9";
            case R.id.softinput_btn_point:
                return ".";
            case R.id.softinput_btn_del:
                return "del";
            case R.id.softinput_btn_tract:
                return "-";
        }
        return "";
    }

    private boolean isDel(View btn) {
        if (btn.getTag().toString().equals("del")) {
            return true;
        }
        return false;
    }

    private boolean isPoint(View btn) {
        if (btn.getTag().toString().equals(".")) {
            return true;
        }
        return false;
    }

    private boolean isTract(View btn) {
        if (btn.getTag().toString().equals("-")) {
            return true;
        }
        return false;
    }
}

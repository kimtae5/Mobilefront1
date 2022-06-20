package com.kt5.mobilefront1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class ItemAdapter extends BaseAdapter {
    //AdapterView 출력을 위한 속성
    //셀을 만들기 위해서 필요한 정보
    Context context;//layoutInflater를 만들기 위한 문맥 정보
    LayoutInflater inflater; //xml로 만든 레이아웃을 뷰로 전개하기 위한 객체
    int layout; //Layout File의 파일이름

    List<Item> data; //츨력할 데이터의 List

    //생성자
    public ItemAdapter(Context context, List<Item>data, int layout){
        super();
        this.context = context;
        this.data = data;
        this.layout = layout;

        inflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    //행의 개수를 설정하는 메서드
    @Override
    public int getCount() {
        return data.size();
    }

    //항목에 보여질 문자열을 설정하는 메서드
    //아래 2개 메서드는 getCount에서 리턴한 개수만큼 반복해서 수행
    @Override
    public Object getItem(int i) {
        return data.get(i).getItemname();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    //실제 셀의 모양을 결정하는 메서드
    //i는 각 셀의 인덱스
    //View는 화면에 보여지는 뷰로 처음에는 null이 넘어오고 두번째 부터는 이전에 출력한뷰가
    //넘어옴
    //viewGroup은 항목을 사용하는 AdapterView
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //매개변수로 넘어온 변수를 다른 변수나 final변수에 저장하는 경우
        final  int pos = i;
        //이전에 출력된 뷰가 없다면 뷰를 생성
        if(view == null){
            view = inflater.inflate(layout, viewGroup, false);
        }
        //i번째 데이터 찾아오기
        Item item = data.get(i);
        //데이터 출력
        TextView txtName = (TextView)view.findViewById(R.id.itemname);
        txtName.setText(item.getItemname());

        //데이터 출력
        TextView txtPrice = (TextView)view.findViewById(R.id.price);
        txtName.setText(item.getPrice() + "원");

        ImageView imageView = (ImageView)view.findViewById(R.id.itemimage);

        Handler handler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message){
                //스레드가 전달한 데이터 읽기
                Bitmap bitmap = (Bitmap) message.obj;
                //이미지 뷰에 이미지 출력
                imageView.setImageBitmap(bitmap);
            }
        };
        class ThreadEx extends Thread{
            public  void  run(){
                //이미지를 다운로드
                try {
                    //이미지를 웹 서버의 이미지 다운로드 경로에서 스트림으로 연결
                    //문자열을 제외한 모든 데이터의 다운로드
                    InputStream inputStream =
                            new URL("http://192.168.10.159:80/member/download?path=" +
                            item.getPictureurl()).openStream();
                    //이미지의 경우는 다운로드 받은 내용을 Bitmap으로 변환
                    //이미지가 아닌 경우라면 Stream의 내용을 읽어서 파일로 저장해야 함
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    //핸들러에게 데이터를 전달하기 위해서 Message 객체를 생성
                    Message message = new Message();
                    message.obj = bitmap;
                    handler.sendMessage(message);
                }catch (Exception e){
                    Log.e("다운로드 예외",e.getLocalizedMessage());
                }
            }
        }
        new ThreadEx().start();
        return view;
    }
}

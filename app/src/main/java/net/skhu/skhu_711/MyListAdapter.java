package net.skhu.skhu_711;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private ArrayList<MyList_item> arrayList = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView status,rDate,rCode;
        Button cancle;
        public ViewHolder(View view){
            super(view);
            status = view.findViewById(R.id.rental_status);
            rDate = view.findViewById(R.id.rental_date);
            rCode = view.findViewById(R.id.rental_code);
            cancle = view.findViewById(R.id.rental_cancle);
        }
        public void setData(){
            MyList_item item = arrayList.get(getAdapterPosition());
            status.setText(item.getRentalState());
            rDate.setText(
                    item.getRentalDate().getStartTime()+":00 ~ "+
                    item.getRentalDate().getEndTime()+":00 "+
                            item.getRentalDate().getRentalDate());
            //ex) 11:00 ~ 12:00 2019-05-28
            rCode.setText(item.getLectureCode());
            if(item.getCancle()!=true){
                cancle.setVisibility(View.INVISIBLE);
            }//취소여부가 가능이면 버튼을 표시하고 아니면 버튼을 감춤
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //취소버튼클릭시 일어나는 이벤트 구현해야함
                    Toast.makeText(view.getContext(), "취소버튼 클릭", Toast.LENGTH_SHORT).show();
                }
            };
        }
    }
    LayoutInflater layoutInflater;
    public MyListAdapter(Context context, ArrayList<MyList_item>arrayList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.my_list_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.setData();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

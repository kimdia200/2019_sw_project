package net.skhu.skhu_711;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BuildingAdapter extends RecyclerView.Adapter<BuildingAdapter.ViewHolder> {
    private ArrayList<Building_item> arrayList = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView bName, bCode;
        ImageView bImg;
        public ViewHolder(View view){
            super(view);
            bName = view.findViewById(R.id.building_name);
            bCode = view.findViewById(R.id.building_code);
            bImg = view.findViewById(R.id.building_img);
            view.setOnClickListener(this);
        }
        public void setData(){
            Building_item item = arrayList.get(getAdapterPosition());
            bName.setText(item.getName());
            bCode.setText(item.getCode());
            bImg.setImageResource(item.getImg_src());
        }

        //recycler 객체 하나 클릭시 일어나는 onclick 메소드 재정의
        @Override
        public void onClick(View view) {
            Building_item item = arrayList.get(super.getAdapterPosition());

            Intent intent = new Intent(view.getContext(), Select.class);
            intent.putExtra("bName",item.getName());
            intent.putExtra("bCode",item.getCode());
            view.getContext().startActivity(intent);
        }
        //재정의 완료, 건물이름,건물코드를 인텐트로 같이 넘겨줌
    }
    LayoutInflater layoutInflater;

    public BuildingAdapter (Context context, ArrayList<Building_item>arrayList){
        this.layoutInflater = LayoutInflater.from(context);
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.building_item,viewGroup,false);
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
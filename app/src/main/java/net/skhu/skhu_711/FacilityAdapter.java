package net.skhu.skhu_711;

import android.app.Activity;
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

public class FacilityAdapter extends RecyclerView.Adapter<FacilityAdapter.ViewHolder> {
    private ArrayList<Facility_item> arrayList = new ArrayList<>();
    private Activity mActivity;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fName, fCode, fCapacity;
        ImageView fImg;
        public ViewHolder(View view){
            super(view);
            fName = view.findViewById(R.id.textView_fName);
            fCode = view.findViewById(R.id.textView_fCode);
            fCapacity = view.findViewById(R.id.textView_fCapacity);
            fImg = view.findViewById(R.id.facility_img);
            view.setOnClickListener(this);
        }
        public void setData(){
            Facility_item item = arrayList.get(getAdapterPosition());
            fName.setText(item.getfName());
            fCode.setText(item.getfCode());
            fCapacity.setText(item.getfCapacity());
            fImg.setImageResource(item.getfImg_src());
        }

        //recycler 객체 하나 클릭시 일어나는 onclick 메소드 재정의
        @Override
        public void onClick(View view) {
            Facility_item item = arrayList.get(super.getAdapterPosition());

            Intent intent = new Intent(view.getContext(), BookingList.class);
            intent.putExtra("bCode",mActivity.getIntent().getStringExtra("bCode"));
            //이전엑티비티에서 intent받은 건물 코드 가져와서 다시 전송
            intent.putExtra("fName",item.getfName());
            intent.putExtra("fCode",item.getfCode());
            intent.putExtra("fCapacity",item.getfCapacity());
            view.getContext().startActivity(intent);
            mActivity.finish();
            //위의 코드 및 생성자 파라미터 수정으로 인해 액티비티 값을 넘겨받아 사용할수있게되었음
        }
        //재정의 완료, 건물이름,건물코드를 인텐트로 같이 넘겨줌
    }
    LayoutInflater layoutInflater;

    public FacilityAdapter (Context context,Activity mActivity, ArrayList<Facility_item>arrayList){
        this.layoutInflater = LayoutInflater.from(context);
        this.mActivity =mActivity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.facility_item,viewGroup,false);
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

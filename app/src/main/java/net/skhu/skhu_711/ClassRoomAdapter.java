package net.skhu.skhu_711;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ClassRoomAdapter extends RecyclerView.Adapter<ClassRoomAdapter.ViewHolder> {
    private ArrayList<ClassRoom_item> arrayList = new ArrayList<>();
    private Activity mActivity;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView classRoomName, roomType, people;
        ImageView classroom_img,computer,projector;
        public ViewHolder(View view){
            super(view);
            classRoomName = view.findViewById(R.id.textView_classRoomName);
            roomType = view.findViewById(R.id.textView_roomType);
            people = view.findViewById(R.id.textView_people);
            classroom_img = view.findViewById(R.id.classroom_img);
            computer = view.findViewById(R.id.imageView_computer);
            projector = view.findViewById(R.id.imageView_projector);
            view.setOnClickListener(this);
        }
        public void setData(){
            ClassRoom_item item = arrayList.get(getAdapterPosition());
            classRoomName.setText(item.getClassroomName());
            //roomType = 비고
            //- SPECIAL: 특수 강의실
            //- SMALL: 소형 강의실
            //- MIDDLE: 중형 강의실
            //- BIG: 대형 강의실
            String temp;
            if(item.getRoomType().equals("SPECIAL")){
                temp="특수강의실";
            }else if(item.getRoomType().equals("SMALL")){
                temp="소형강의실";
            }else if(item.getRoomType().equals("MIDDLE")){
                temp="중형강의실";
            }else{
                temp="대형강의실";
            }
            roomType.setText(temp);
            people.setText(item.getPeople()+"명");
            classroom_img.setImageResource(item.getImg_src());
//            DetailType(에 따른 버튼 표시)
//                    - PROJECTOR: 빔프로젝터
//                    - COMPUTER: 컴퓨터
//                    - BOTH: 빔프로젝터, 컴퓨터
//                    - NULL: 해당사항 없음
            if(item.getDetailType().equals("PROJECTOR")){
                projector.setImageResource(R.drawable.sharp_local_movies_white_18dp);
            }else if (item.getDetailType().equals("COMPUTER")){
                computer.setImageResource(R.drawable.sharp_computer_white_18dp);
            }else if (item.getDetailType().equals("BOTH")){
                computer.setImageResource(R.drawable.sharp_computer_white_18dp);
                projector.setImageResource(R.drawable.sharp_local_movies_white_18dp);
            }


        }

        //recycler 객체 하나 클릭시 일어나는 onclick 메소드 재정의
        @Override
        public void onClick(View view) {
            ClassRoom_item item = arrayList.get(super.getAdapterPosition());

            Intent intent = new Intent(view.getContext(), BookingList.class);
            intent.putExtra("bCode",mActivity.getIntent().getStringExtra("bCode"));
            intent.putExtra("bName",mActivity.getIntent().getStringExtra("bName"));
            //이전엑티비티에서 intent받은 강의실 코드,건물 코드,이름 가져와서 다시 전송
            intent.putExtra("classroomName",item.getClassroomName());
            intent.putExtra("detailType",item.getDetailType());
            intent.putExtra("people",item.getPeople());
            view.getContext().startActivity(intent);
            mActivity.finish();
            //위의 코드 및 생성자 파라미터 수정으로 인해 액티비티 값을 넘겨받아 사용할수있게되었음
        }
        //재정의 완료, 건물이름,건물코드를 인텐트로 같이 넘겨줌
    }
    LayoutInflater layoutInflater;

    public ClassRoomAdapter(Context context, Activity mActivity, ArrayList<ClassRoom_item>arrayList){
        this.layoutInflater = LayoutInflater.from(context);
        this.mActivity =mActivity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.class_room_item,viewGroup,false);
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

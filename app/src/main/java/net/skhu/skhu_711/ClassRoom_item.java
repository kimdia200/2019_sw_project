package net.skhu.skhu_711;

public class ClassRoom_item {
    String classroomName;
    int people;
    String detailType;
    String roomType;
    int img_src;


//    "classroomName": "B103",
//            "people": 0,
//            "detailType": "NULL",
//            "roomType": "SPECIAL"


    public ClassRoom_item(String classroomName, int people, String detailType, String roomType, int img_src) {
        this.classroomName = classroomName;
        this.people = people;
        this.detailType = detailType;
        this.roomType = roomType;
        this.img_src = img_src;
    }


    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getImg_src() {
        return img_src;
    }

    public void setImg_src(int img_src) {
        this.img_src = img_src;
    }
}
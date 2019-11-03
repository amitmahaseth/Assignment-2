package com.example.assignment3update.activity.model;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentDetail implements Parcelable {

    String studentName;
    int className, rollNo, type;

    public StudentDetail(String studentName, int className, int rollNo, int type) {
        this.studentName = studentName;
        this.className = className;
        this.rollNo = rollNo;
        this.type = type;
    }

    protected StudentDetail(Parcel in) {
        studentName = in.readString();
        className = in.readInt();
        rollNo = in.readInt();
        type = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(studentName);
        dest.writeInt(className);
        dest.writeInt(rollNo);
        dest.writeInt(type);
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getClassName() {
        return className;
    }

    public void setClassName(int className) {
        this.className = className;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static Creator<StudentDetail> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StudentDetail> CREATOR = new Creator<StudentDetail>() {
        @Override
        public StudentDetail createFromParcel(Parcel in) {
            return new StudentDetail(in);
        }

        @Override
        public StudentDetail[] newArray(int size) {
            return new StudentDetail[size];
        }
    };

}
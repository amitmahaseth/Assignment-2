package com.example.assignment_3.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class StudentDetail implements Parcelable {

    String studentName;
    int className, rollNo;

    public StudentDetail(String studentName, int className, int rollNo) {
        this.studentName = studentName;
        this.className = className;
        this.rollNo = rollNo;
    }

    protected StudentDetail(Parcel in) {
        studentName = in.readString();
        className = in.readInt();
        rollNo = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(studentName);
        parcel.writeInt(className);
        parcel.writeInt(rollNo);
    }
}
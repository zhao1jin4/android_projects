package org.zhaojin.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class MyType implements Parcelable
{
	private int id;
	private String name;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	//CREATOR名字是必须的
	private static MyCreator CREATOR;
	public int describeContents() {
		return 0;
	}
	public void writeToParcel(Parcel arg0, int arg1) {

	}
}

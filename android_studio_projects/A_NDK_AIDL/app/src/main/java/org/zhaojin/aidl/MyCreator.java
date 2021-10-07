package org.zhaojin.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class MyCreator implements Parcelable.Creator<MyType>
{

	public MyType createFromParcel(Parcel source) {
		MyType my=new MyType();
		my.setId(source.readInt());
		my.setName(source.readString());
		return my;
	}
	public MyType[] newArray(int size) {
		return new MyType[size];
	}
}

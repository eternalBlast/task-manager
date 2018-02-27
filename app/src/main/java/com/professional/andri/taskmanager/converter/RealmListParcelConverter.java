package com.professional.andri.taskmanager.converter;

import android.os.Parcelable;

import org.parceler.Parcels;
import org.parceler.TypeRangeParcelConverter;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Andri on 2/25/2018.
 */

public class RealmListParcelConverter implements TypeRangeParcelConverter<RealmList<? extends RealmObject>,
        RealmList<? extends RealmObject>>{
    private static final int NULL = -1;

    @Override
    public void toParcel(RealmList<? extends RealmObject> input, android.os.Parcel parcel) {
        parcel.writeInt(input == null ? NULL : input.size());
        if (input != null) {
            for (RealmObject item : input) {
                parcel.writeParcelable(Parcels.wrap(item), 0);
            }
        }
    }

    @Override
    public RealmList fromParcel(android.os.Parcel parcel) {
        int size = parcel.readInt();
        RealmList<Object> list = new RealmList<>();
        for (int i=0; i<size; i++) {
            Parcelable parcelable = parcel.readParcelable(getClass().getClassLoader());
            list.add(Parcels.unwrap(parcelable));
        }
        return list;
    }

}
package hu.bme.aut.f1calendar.model

import android.os.Parcel
import android.os.Parcelable

class TransferObject(
    val season: Int,
    val round: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(season)
        parcel.writeInt(round)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TransferObject> {
        override fun createFromParcel(parcel: Parcel): TransferObject {
            return TransferObject(parcel)
        }

        override fun newArray(size: Int): Array<TransferObject?> {
            return arrayOfNulls(size)
        }
    }

}
package maedeh.nami.worldpizza

import android.os.Parcel
import android.os.Parcelable

data class LevelsModel (
    val id : Int,
    val letters : List<Char>,
    val validWords : List<String>,
    val isLocked : Boolean
) : Parcelable {

    // تبدیل داده‌ها به Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeStringList(letters.map { it.toString() }) // تبدیل List<Char> به List<String>
        parcel.writeStringList(validWords)
        parcel.writeByte(if (isLocked) 1 else 0)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<LevelsModel> {
        override fun createFromParcel(parcel: Parcel): LevelsModel {
            // بازسازی داده‌ها از Parcel
            val id = parcel.readInt()
            val letters = parcel.createStringArrayList()?.map { it[0] } ?: emptyList() // تبدیل List<String> به List<Char>
            val validWords = parcel.createStringArrayList() ?: emptyList()
            val isLocked = parcel.readByte() != 0.toByte()
            return LevelsModel(id, letters, validWords, isLocked)
        }

        override fun newArray(size: Int): Array<LevelsModel?> = arrayOfNulls(size)
    }
}
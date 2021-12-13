package co.com.mintic.ejeCafetero
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Workshops (val title: String,val desc: String,var imageUrl: String, var rating: String):Parcelable

package co.com.mintic.talleres
import android.R
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Workshops (val title: String,val desc: String,var imageUrl: String, var rating: String):Parcelable

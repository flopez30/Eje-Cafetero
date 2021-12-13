package co.com.mintic.ejeCafetero

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide


class WorkshopAdapter (
    private val mWorkshops: ArrayList<Workshops>,
    private val context: Context,
    private val onClick:(Workshops?) -> Unit
) : RecyclerView.Adapter<WorkshopAdapter.WorkshopViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkshopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.workshops_list_item, parent, false)
        return WorkshopViewHolder(view)
    }

    override fun onBindViewHolder(holderContact: WorkshopViewHolder, position: Int) {
        val workshop = mWorkshops[position]
        holderContact.bind(workshop = workshop)
    }


    override fun getItemCount(): Int {
        return mWorkshops.size
    }

    inner class WorkshopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var titleLabel: TextView = itemView.findViewById(R.id.textview_title)
        private var descLabel: TextView = itemView.findViewById(R.id.textview_desc)
        private var imageView: ImageView = itemView.findViewById(R.id.imageView)
        private var ratingBar2: RatingBar = itemView.findViewById(R.id.ratingBar_rating)
        private var currentWorkshop:Workshops? = null
        init {
            itemView.setOnClickListener {
                Log.d(TAG,"itemView OnClick, contacto: ${currentWorkshop}")
                onClick(currentWorkshop)
            }
        }

        fun bind(workshop: Workshops) {
            currentWorkshop= workshop
            titleLabel.text = workshop.title
            descLabel.text = workshop.desc
            Log.d(TAG,workshop.imageUrl)
            Glide.with(context)
                .load(workshop.imageUrl)
                .into(imageView)


        }

    }


    companion object{
        private const val TAG = "WorkshopAdapter"
    }
}
package com.bgomez.picker.ui.adapters


import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bgomez.picker.R
import com.bgomez.picker.common.models.PicData
import com.bgomez.picker.common.utils.LogWrapper
import com.squareup.picasso.Picasso
import javax.inject.Inject


/**
 * Recycler adapter for pics
 *
 * Created by bernatgomez on August,2020
 */
class PicsAdapter @Inject constructor(
    private val data : List<PicData>,
    private val listener : (PicData) -> Unit) : RecyclerView.Adapter<PicsAdapter.PicViewHolder>() {

    companion object {
        val TAG = PicsAdapter::class.java.simpleName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_pic, parent, false)

        return PicViewHolder(v)
    }

    override fun getItemCount() = this.data.size

    override fun onBindViewHolder(holder: PicViewHolder, position: Int) {
        val pic = this.data[position]

        holder.bind(pic)
    }

    /**
     * Holder for pic item
     */
    inner class PicViewHolder(v : View) : RecyclerView.ViewHolder(v){

        fun bind(element : PicData) {

            with (this.itemView) {
                itemView.findViewById<TextView>(R.id.photoAuthor).text = element.user.username

                Picasso.Builder(itemView.context).build().load(element.urls.regular).into(itemView.findViewById<ImageView>(R.id.photo))

                this.setOnLongClickListener {
                    val i = Intent(Intent.ACTION_VIEW)

                    i.data = Uri.parse(element.urls.regular)
                    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK

                    it.context.applicationContext.startActivity(i)

                    true
                }

                this.setOnClickListener {
                    val pos = adapterPosition

                    LogWrapper.d(TAG, "click on item in position $pos")

                    listener(data[pos])
                }
            }
        }
    }
}
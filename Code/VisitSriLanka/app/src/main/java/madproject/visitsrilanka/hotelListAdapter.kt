package madproject.visitsrilanka


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class hotelListAdapter(private var hotelList: ArrayList<HotelData>)
    :RecyclerView.Adapter<hotelListAdapter.hotelListViewHolder>(){



    private lateinit var mlistener:onItemClickListener


    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mlistener=clickListener
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): hotelListViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.hotellistlayout,parent,false)
        return hotelListViewHolder(itemView,mlistener)


    }

    override fun getItemCount(): Int {
        return hotelList.size;
    }

    override fun onBindViewHolder(holder: hotelListViewHolder, position: Int) {
        val currentItem =hotelList[position]

        holder.hotelName.text=currentItem.hotelName

        holder.hotelDistrict.text=currentItem.hotelDistrict

        holder.hotelPrice.text=currentItem.hotelPrice
        Glide.with(holder.itemView.context).load(currentItem.hotelImage)
            .into(holder.hotelImage)
    }


    class hotelListViewHolder (itemView : View,clickListener: onItemClickListener) :RecyclerView.ViewHolder(itemView){

        val hotelName :TextView = itemView.findViewById(R.id.hotelListNametv)
        val hotelDistrict:TextView=itemView.findViewById(R.id.hotelListDistricttv)
        val hotelPrice :TextView = itemView.findViewById(R.id.hotelListPricetv)
        val hotelImage :ImageView = itemView.findViewById(R.id.hotelListImagetv)



        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)

            }
        }


    }



}
package madproject.visitsrilanka

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val userList: ArrayList<Guide>) : RecyclerView.Adapter <MyAdapter.MyViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemview=LayoutInflater.from(parent.context).inflate(R.layout.user_item,
        parent,false )
        return MyViewHolder(itemview)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = userList[position]
        holder.name.text = currentitem.name
        holder.district.text = currentitem.district
        holder.email.text = currentitem.email
        holder.number.text = currentitem.number
        holder.age.text = currentitem.age
        holder.gender.text = currentitem.gender
    }
    override fun getItemCount(): Int {
        return userList.size

    }
    class MyViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        val name :TextView=itemview.findViewById(R.id.tvname)
        val district:TextView=itemview.findViewById(R.id.tvdistiric)
        val email:TextView=itemview.findViewById(R.id.tvemail)
        val number:TextView=itemview.findViewById(R.id.tvnumber)
        val age:TextView=itemview.findViewById(R.id.tvage)
        val gender:TextView=itemview.findViewById(R.id.tvgender)
    }
}
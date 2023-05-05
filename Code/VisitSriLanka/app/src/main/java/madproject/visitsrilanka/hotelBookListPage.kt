package madproject.visitsrilanka


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*


class hotelBookListPage : AppCompatActivity() {


        private lateinit var DBref: DatabaseReference
        private lateinit var hotelRecycle: RecyclerView
        private lateinit var hotelArrayList:ArrayList<HotelData>
    private val filteredHotelList = ArrayList<HotelData>()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_hotel_book_list_page)

            hotelRecycle =findViewById(R.id.hotelListRecycle)
            hotelRecycle.layoutManager= LinearLayoutManager(this)
            hotelRecycle.setHasFixedSize(true)

            hotelArrayList= arrayListOf<HotelData>()
            getHotelData();




        }

        private fun getHotelData() {
            DBref= FirebaseDatabase.getInstance().getReference("Hotel")
            DBref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for (hotelSnapshot in snapshot.children){
                            val hotel =hotelSnapshot.getValue(HotelData::class.java)
                            hotelArrayList.add(hotel!!)
                        }
                        val mAdaptor =hotelListAdapter(hotelArrayList)
                        hotelRecycle.adapter=mAdaptor

                        mAdaptor.setOnItemClickListener(object :hotelListAdapter.onItemClickListener{
                            override fun onItemClick(position: Int) {
                                val intent= Intent(this@hotelBookListPage,hotelItemBook::class.java)

                                intent.putExtra("hotelName",hotelArrayList[position].hotelName)
                                intent.putExtra("hotelAddress",hotelArrayList[position].hotelAddress)
                                intent.putExtra("hotelEmail",hotelArrayList[position].hotelEmail)
                                intent.putExtra("hotelPhone",hotelArrayList[position].hotelPhone)
                                intent.putExtra("hotelPrice",hotelArrayList[position].hotelPrice)
                                intent.putExtra("hotelDistrict",hotelArrayList[position].hotelDistrict)
                                intent.putExtra("hotelImage", hotelArrayList[position].hotelImage)





                                startActivity(intent)

                            }


                        })


                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }

    }


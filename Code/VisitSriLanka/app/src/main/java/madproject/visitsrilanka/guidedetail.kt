package madproject.visitsrilanka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class guidedetail : AppCompatActivity() {
    private lateinit var dbref: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<Guide>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guidedetail)

        userRecyclerView=findViewById(R.id.userList)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList= arrayListOf<Guide>()
        getUserData()
    }
    private fun getUserData(){
        dbref= FirebaseDatabase.getInstance().getReference("Guides")
        dbref.addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(Guide::class.java)
                        userArrayList.add(user!!)
                    }
                    //userRecyclerView.adapter=MyAdapter(userArrayList)
                    userRecyclerView.adapter=MyAdapter(userArrayList)
                }

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}

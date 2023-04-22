package madproject.visitsrilanka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_tourist_main.*

class TouristMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tourist_main)

        //create Fragment objects
        var touristAccountFragment=TouristAccountFragment()
        var shareMyExperiencesFragment=ShareMyExperiencesFragment()
        var taxiFragment=TaxiFragment()
        var tourGuidesFragment=TourGuidesFragment()
        var hotelsAndRestaurentsFragment=HotelsAndRestaurentsFragment()


        //moving fragment to fragment
        bottomNavigationBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.myAccount->changeFragment(touristAccountFragment)
                R.id.experiences->changeFragment(shareMyExperiencesFragment)
                R.id.taxies->changeFragment(taxiFragment)
                R.id.tourGuides->changeFragment(tourGuidesFragment)
                R.id.hotelsAndRestaurents->changeFragment(hotelsAndRestaurentsFragment)
                else->{

                }
            }
            true
        }


    }//end method onCreate

    private fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView,fragment)
            commit()
        }
    }//end function changeFragment
}
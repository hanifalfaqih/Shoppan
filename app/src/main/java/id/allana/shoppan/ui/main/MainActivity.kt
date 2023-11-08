package id.allana.shoppan.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import id.allana.shoppan.R
import id.allana.shoppan.databinding.ActivityMainBinding
import id.allana.shoppan.ui.main.Save.SaveFragment
import id.allana.shoppan.ui.main.home.HomeFragment
import id.allana.shoppan.ui.main.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    it.title = getString(R.string.home)
                    replaceFragment(HomeFragment())
                }

                R.id.saveFragment -> {
                    it.title = getString(R.string.save)
                    replaceFragment(SaveFragment())
                }

                R.id.profileFragment -> {
                    it.title = getString(R.string.account)
                    replaceFragment(ProfileFragment())
                }
            }
            true

        }
    }

    private fun replaceFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_main, fragment)
            commit()
        }
}
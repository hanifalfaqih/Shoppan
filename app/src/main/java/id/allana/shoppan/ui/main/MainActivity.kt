package id.allana.shoppan.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.allana.shoppan.R
import id.allana.shoppan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_main) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_main)

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    it.title = getString(R.string.home)
                    navController.navigate(R.id.homeFragment)
                }

                R.id.nav_save -> {
                    it.title = getString(R.string.save)
                    navController.navigate(R.id.saveFragment)
                }

                R.id.nav_profile -> {
                    it.title = getString(R.string.account)
                    navController.navigate(R.id.profileFragment)
                }
            }
            true

        }
        bottomNav.selectedItemId = R.id.nav_home
    }
}
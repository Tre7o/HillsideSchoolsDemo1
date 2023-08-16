package com.trev.hillsideschoolsdemo1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.OnMenuItemClickListener
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.trev.hillsideschoolsdemo1.databinding.ActivityDrawerBaseBinding
import com.trev.hillsideschoolsdemo1.databinding.ActivityMainBinding

class DrawerBaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDrawerBaseBinding
    private lateinit var analytics: FirebaseAnalytics

    private var isBackPressed = false

    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser

    //from navigationDrawerActivity
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navController: NavController


//    override fun onStart() {
//        super.onStart()
//        if(firebaseUser == null){
//            finish()
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        analytics = Firebase.analytics

        auth = Firebase.auth

        firebaseUser = auth.currentUser!!

//        drawerLayout = findViewById(R.id.drawerLayout)
//        val navView: NavigationView = findViewById(R.id.nav_view)
//        val bottomView: BottomNavigationView = findViewById(R.id.bottom_view)

        //from navigationDrawerActivity
//        var toolbar: Toolbar = drawerLayout.findViewById(R.id.toolbar)
        
        navController = findNavController(R.id.nav_host_fragment_content_main)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)


//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.home, R.id.gallery, R.id.contact, R.id.leadership, R.id.hod, R.id.coordinators, R.id.background, R.id.admissions, R.id.rules, R.id.fees,
//                R.id.nursery, R.id.facilities, R.id.co_curricular, R.id.ple
//            ), drawerLayout
//        )

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.home, R.id.gallery, R.id.contact, R.id.leadership, R.id.hod, R.id.coordinators, R.id.background, R.id.admissions, R.id.rules, R.id.fees,
            R.id.nursery, R.id.facilities, R.id.co_curricular, R.id.ple).setOpenableLayout(binding.drawerLayout).build()

        setSupportActionBar(binding.toolbar)

        setupActionBarWithNavController(navController, appBarConfiguration)

        visibilityNavElements(navController)
//        navView.setupWithNavController(navController)
//        //for bottom navigation
//        bottomView.setupWithNavController(navController)
    }

    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when(destination.id){
                R.id.ple -> hideBothNav()
                else -> showBothNav()
            }
        }
    }

    private fun hideBothNav() {
        binding.bottomView.visibility = View.INVISIBLE
        binding.navView.visibility = View.INVISIBLE
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED) //To lock navigation drawer so that it don't respond to swipe gesture
    }

    private fun showBothNav() {
        binding.bottomView.visibility = View.VISIBLE
        binding.navView.visibility = View.VISIBLE
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED) //unlock drawer
        setupNavControl() //Setup Drawer navigation with navController
    }

    private fun setupNavControl() {
        binding.navView.setupWithNavController(navController)
        binding.bottomView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean { //Setup appBarConfiguration for back arrow
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else {

            if (isBackPressed) {
                super.onBackPressed()
                return
            }

            Toast.makeText(this, "Press 'back' again to exit", Toast.LENGTH_SHORT).show()
            isBackPressed =
                true // set the variable to true so that when the user presses back again
            // they exit the activity

            Handler().postDelayed({
                isBackPressed = false // after 2 seconds make the variable false
            }, 2000)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if ( item.itemId == R.id.logout ) {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // clears activities on the stack
            startActivity(intent)
            finish()
            Toast.makeText( this, "Logged out successfully!", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate( R.menu.profile_menu, menu)
        return true
    }
}
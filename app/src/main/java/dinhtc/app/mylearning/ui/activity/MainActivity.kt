package dinhtc.app.mylearning.ui.activity

import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import dinhtc.app.mylearning.R
import dinhtc.app.mylearning.base.BaseActivity
import dinhtc.app.mylearning.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding

    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override fun onCreateActivity() {
        binding = viewDataBinding as ActivityMainBinding
        navController = findNavController(R.id.nav_host)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }
                else -> true
            }
        }
    }

    override fun onBackPressed() {
        onSupportNavigateUp()
    }

    override fun onSupportNavigateUp(): Boolean {
        val prevDestinationId = navController.previousBackStackEntry?.destination?.id ?: -1
        return when {
//            navController.currentDestination?.id == R.id.productDetailFragment
//                    && prevDestinationId == R.id.homeFragment -> {
//                navController.navigateUp()
//                true
//            }
            (!navController.navigateUp()) -> {
                finish()
                false
            }
            else -> {
                false
            }
        }
    }
}
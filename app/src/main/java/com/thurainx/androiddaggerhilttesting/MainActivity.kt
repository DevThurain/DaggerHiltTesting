package com.thurainx.androiddaggerhilttesting

import android.animation.ObjectAnimator
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.animation.doOnEnd
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.color.DynamicColors
import com.mikepenz.materialdrawer.model.NavigationDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.descriptionText
import com.mikepenz.materialdrawer.model.interfaces.iconRes
import com.mikepenz.materialdrawer.model.interfaces.nameText
import com.mikepenz.materialdrawer.model.interfaces.withName
import com.mikepenz.materialdrawer.util.addItems
import com.mikepenz.materialdrawer.util.setupWithNavController
import com.mikepenz.materialdrawer.widget.AccountHeaderView
import com.thurainx.androiddaggerhilttesting.databinding.ActivityMainBinding
import com.thurainx.androiddaggerhilttesting.mvp.presenters.MainPresenter
import com.thurainx.androiddaggerhilttesting.mvp.presenters.MainPresenterImpl
import com.thurainx.androiddaggerhilttesting.mvp.views.MainView
import com.thurainx.androiddaggerhilttesting.utils.SharedPreferenceUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainView {
    @Inject
    lateinit var mPref: SharedPreferenceUtils

    private val mPresenter: MainPresenterImpl by viewModels()

    lateinit var binding: ActivityMainBinding

    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        setUpFragmentNavigation()
        setUpSlider(savedInstanceState)
        setUpBackPressed()
        setUpViewModel()

        // Set up an OnPreDrawListener to the root view.
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Check if the initial data is ready.

                    return if (mPresenter.isReady) {

                        // The content is ready; start drawing.
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )


    }

    private fun setUpSlider(savedInstanceState: Bundle?) {
//        binding.slider.apply {
//            addItems(
//                NavigationDrawerItem(R.id.action_global_fragmentHome, PrimaryDrawerItem().withName("Home"), null, null),
//                DividerDrawerItem(),
//                NavigationDrawerItem(R.id.messageFragment1, PrimaryDrawerItem().withName("Fragment1")),
//                NavigationDrawerItem(R.id.messageFragment2, PrimaryDrawerItem().withName("Fragment2"))
//            )
//            addStickyDrawerItems(
//                NavigationDrawerItem(R.id.messageFragment3, PrimaryDrawerItem().withName("Fragment3")),
//                NavigationDrawerItem(R.id.about_libraries, PrimaryDrawerItem().withName("AboutLibs"), bundleOf("data" to (LibsBuilder() as Serializable)))
//            )
//        }

        with(binding.slider) {
            addItems(
                NavigationDrawerItem(
                    R.id.navCounterFragment,
                    PrimaryDrawerItem().apply {
                        isIconTinted = true;
                        iconRes = R.drawable.ic_counter
                        nameText = "Counter";
                        identifier = 1;
                        isSelectable = true;
                        isSelected = true
                    }),
                NavigationDrawerItem(
                    R.id.navTestingOneFragment,
                    PrimaryDrawerItem().apply {
                        isIconTinted = true;
                        iconRes = R.drawable.ic_test
                        nameText = "Testing One";
                        identifier = 2;
                        isSelectable = true ;
                    }),
                NavigationDrawerItem(
                    R.id.navTestingTwoFragment,
                    PrimaryDrawerItem().apply {
                        isIconTinted = true;
                        iconRes = R.drawable.ic_test;
                        nameText = "Testing Two";
                        identifier = 3;
                        isSelectable = true
                    }),
            )
            setSavedInstance(savedInstanceState)
        }
    }

    private fun setUpViewModel() {
        mPresenter.initView(this)
        mPresenter.onUiReady(this, this)
    }

    private fun setUpFragmentNavigation() {
        setSupportActionBar(binding.topAppBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.root,
            binding.topAppBar,
            com.mikepenz.materialdrawer.R.string.material_drawer_open,
            com.mikepenz.materialdrawer.R.string.material_drawer_close
        )
        actionBarDrawerToggle.isDrawerIndicatorEnabled = true
        actionBarDrawerToggle.syncState()


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment? ?: return
        binding.slider.setupWithNavController(navHostFragment.navController, null, null)

    }

    override fun initializationComplete(value: String) {
        Toast.makeText(this, "count: $value", Toast.LENGTH_SHORT).show()
    }

    override fun showErrorMessage(message: String) {

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        actionBarDrawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actionBarDrawerToggle.syncState()

    }

//    override fun onSaveInstanceState(_outState: Bundle) {
//        var outState = _outState
//        //add the values which need to be saved from the drawer to the bundle
//        outState = binding.slider.saveInstanceState(outState)
//        super.onSaveInstanceState(outState)
//    }

    private fun setUpBackPressed() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                //handle the back press :D close the drawer first and if the drawer is closed close the activity
                if (binding.root.isDrawerOpen(binding.slider)) {
                    binding.root.closeDrawer(binding.slider)
                } else {
                    finish()
                }

            }
        })

    }
}
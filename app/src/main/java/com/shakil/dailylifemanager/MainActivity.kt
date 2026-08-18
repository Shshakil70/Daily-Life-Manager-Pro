package com.shakil.dailylifemanager

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shakil.dailylifemanager.databinding.ActivityMainBinding
import com.shakil.dailylifemanager.utils.NotificationHelper
import com.shakil.dailylifemanager.utils.PermissionHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val permissionHelper = PermissionHelper()

    companion object {
        private const val PERMISSION_REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize notification channels
        NotificationHelper.createNotificationChannels(this)

        // Check and request permissions
        requestNecessaryPermissions()

        // Setup Navigation
        setupNavigation()
    }

    private fun setupNavigation() {
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_tasks,
                R.id.navigation_calendar,
                R.id.navigation_reminders,
                R.id.navigation_settings
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun requestNecessaryPermissions() {
        val permissionsNeeded = mutableListOf<String>()

        // POST_NOTIFICATIONS for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionsNeeded.add(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        // SCHEDULE_EXACT_ALARM for Android 12+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.SCHEDULE_EXACT_ALARM
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionsNeeded.add(Manifest.permission.SCHEDULE_EXACT_ALARM)
            }
        }

        // RECEIVE_BOOT_COMPLETED
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECEIVE_BOOT_COMPLETED
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionsNeeded.add(Manifest.permission.RECEIVE_BOOT_COMPLETED)
        }

        if (permissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionsNeeded.toTypedArray(),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            val deniedPermissions = mutableListOf<String>()

            for (i in permissions.indices) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissions.add(permissions[i])
                }
            }

            if (deniedPermissions.isNotEmpty()) {
                Toast.makeText(
                    this,
                    "Some permissions are required for full functionality",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

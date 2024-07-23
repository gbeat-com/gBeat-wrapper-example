package com.fodsample


import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.bpmsync.gbeat.GBeat
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fodData = intent.getStringExtra("fodData")
        //Initialize with your own app logo
        GBeat.start(applicationContext, R.drawable.baseline_monitor_heart_24, fodData)
        finish()

        try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            val versionName = packageInfo.versionName
            Toast.makeText(this, "App Version: $versionName", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to get version name", Toast.LENGTH_SHORT).show()
        }
    }
}
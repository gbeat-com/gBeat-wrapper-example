package com.fodsample


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.bpmsync.gbeat.GBeat
import com.bpmsync.gbeat.data.model.WorkoutReport
import com.google.android.gms.wearable.CapabilityClient
import com.google.android.gms.wearable.Wearable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val MOBILE_CAPABILITY = "mobile"
private const val REPORT_RESULTS_PATH = "/report-results"


@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gBeat = GBeat(this)
        gBeat.addOnWorkoutCompleteListener { records ->
            reportWorkoutResults(records)
        }
        gBeat.start()
    }

    fun reportWorkoutResults(results: WorkoutReport) {
        val messageClient = Wearable.getMessageClient(applicationContext)
        val capabilityClient = Wearable.getCapabilityClient(applicationContext)

        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                Log.d("GBeat", results.toString())
                try {
                    val nodes = capabilityClient
                        .getCapability(
                            MOBILE_CAPABILITY,
                            CapabilityClient.FILTER_REACHABLE
                        )
                        .await()
                        .nodes

                    Log.d("GBeat", nodes.toString())

                    val fodDataJson = Json.encodeToString(results)
                    val fodDataBytes = fodDataJson.toByteArray(Charsets.UTF_8)

                    nodes.map { node ->
                        async {
                            messageClient.sendMessage(node.id, REPORT_RESULTS_PATH, fodDataBytes).await()
                        }
                    }.awaitAll()

                    Log.d("GBeat", "Starting activity request sent successfully")
                } catch (exception: Exception) {
                    Log.d("GBeat", "Starting activity failed: $exception")
                }
            }

            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, "Launch Workout", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
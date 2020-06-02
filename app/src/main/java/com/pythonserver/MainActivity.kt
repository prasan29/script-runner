package com.pythonserver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jaredrummler.android.shell.CommandResult
import com.jaredrummler.android.shell.Shell


class MainActivity : AppCompatActivity() {
    private val mBootReceiver: BroadcastReceiver =
            object : BroadcastReceiver() {
                override fun onReceive(context: Context,
                                       intent: Intent) {
                    if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
                        // Start Python server.
//                        startPythonServer();
                    }
                }
            }

    private fun startPythonServer(command: String?) {
        val commandResult: CommandResult = Shell.SH.run(command)

        if (!commandResult.isSuccessful) {
            Log.e("TAG_PYTHON", "Result: ${commandResult.getStderr()}")
            Toast.makeText(this, "Result: ${commandResult.getStderr()}", Toast
                    .LENGTH_LONG).show()
        } else {
            Log.e("TAG_PYTHON", "Result: ${commandResult.getStdout()}")
            Toast.makeText(this, "Result: ${commandResult.getStdout()}", Toast
                    .LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: Button = findViewById(R.id.text)
        val edtTxt: EditText = findViewById(R.id.edTxt)

        textView.setOnClickListener {
            startPythonServer(edtTxt.text.toString())
            edtTxt.setText("")
        }

//        startPythonServer();
        registerReceiver(mBootReceiver,
                IntentFilter(Intent.ACTION_BOOT_COMPLETED))
    }

    override fun onDestroy() {
        unregisterReceiver(mBootReceiver)
        super.onDestroy()
    }
}
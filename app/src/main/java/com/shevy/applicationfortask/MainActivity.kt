package com.shevy.applicationfortask

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        count = getCount()
        Log.d("testLog", "onCreate count = $count")
        if (count == 3) {
            Toast.makeText(this, "This is the third cold start", Toast.LENGTH_LONG).show()
        }
    }

    private fun saveCount(count: Int) {
        val sharedPreference = applicationContext.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putInt("Count", count)
        editor.apply()
    }

    @JvmName("getCount1")
    fun getCount(): Int {
        val sharedPreference = applicationContext.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        count = sharedPreference.getInt("Count", 0)
        return count
    }

    override fun onDestroy() {
        count++
        saveCount(count)
        Log.d("testLog", "onDestroy count = $count")
        super.onDestroy()
    }

    companion object {
        var count = 0
    }
}
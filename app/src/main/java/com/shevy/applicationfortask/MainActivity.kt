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
        Log.d("testLog", "sharedPreference = $sharedPreference")
        var editor = sharedPreference.edit()
        Log.d("testLog", "editor = $editor")
        editor.putInt("Count", count)
        Log.d("testLog", "editor = $editor")
        editor.apply()
        Log.d("testLog", "editor = $editor")
    }

    @JvmName("getCount1")
    fun getCount(): Int {
        val sharedPreference = applicationContext.getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)
        Log.d("testLog", "sharedPreference getCount() = $sharedPreference")
        count = sharedPreference.getInt("Count", 0)
        Log.d("testLog", "getCount count = $count")
        return count
    }

    override fun onDestroy() {
        count++
        Log.d("testLog", "onDestroy count = $count")
        saveCount(count)
        Log.d("testLog", "onDestroy count = $count")
        super.onDestroy()
    }

    companion object {
        var count = 0
    }
}
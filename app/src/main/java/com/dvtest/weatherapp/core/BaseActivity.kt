package com.dvtest.weatherapp.core

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.dvtest.weatherapp.R
import com.dvtest.weatherapp.data.RoomDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseActivity : AppCompatActivity(), BaseContract.view {
    var roomDatabase: RoomDb.WeatherDatabase? = null
    lateinit var progressBar: ProgressDialog
    lateinit var alertDialog: AlertDialog.Builder
    lateinit var alertBox: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressBar = ProgressDialog(this)
        alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage("")
        alertDialog.setTitle("")
        alertDialog.setCancelable(false)
        alertDialog.setPositiveButton("dismiss", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog!!.dismiss()
            }
        })
        progressBar.setMessage("Loading")
        progressBar.setCancelable(false)
        progressBar.setCanceledOnTouchOutside(false)

        setTransparentStatusBar()

    }

    fun getAppDatabase(): RoomDb.WeatherDatabase? {
        if (roomDatabase == null) {
            roomDatabase = Room.databaseBuilder(
                baseContext,
                RoomDb.WeatherDatabase::class.java, "WeatherDb"
            ).allowMainThreadQueries()
                .build()
        }
        return roomDatabase
    }

    private fun setTransparentStatusBar() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

    override fun onShowLoading() {
        CoroutineScope(Dispatchers.Main).launch {
            progressBar.show()
        }
    }

    override fun onHideLoading() {
        CoroutineScope(Dispatchers.Main).launch {
            progressBar.dismiss()
        }
    }

    override fun onError(error: String) {
        var errorMessage = error
        if (!error.trim().toLowerCase().equals(resources.getString(R.string.networkerror))) {
            errorMessage = resources.getString(R.string.errormg)
        }
        alertDialog.setMessage(errorMessage)
        CoroutineScope(Dispatchers.Main).launch {
            alertBox = alertDialog.create()
            alertBox.show()
        }
    }
}
package com.bgomez.picker.client

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bgomez.picker.common.models.PicData
import com.bgomez.picker.ui.Constants
import com.bgomez.picker.ui.ui_controllers.SearchActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(R.layout.activity_main)

        this.setListeners()
    }

    private fun setListeners() {
        this.btnPick.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)

            this.startActivityForResult(intent, Constants.RequestCodes.REQUEST_PICKER)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            Constants.RequestCodes.REQUEST_PICKER -> {
                if (resultCode == Activity.RESULT_OK) {
                    val pic : PicData? = data?.extras?.getParcelable(Constants.ParamNames.PARAM_IMAGE_SELECTED)

                    pic?.let {
                        Picasso.Builder(this).build().load(it.urls.small).into(this.display)
                    }

                } else if (resultCode == Activity.RESULT_CANCELED) {

                }
            }

            else -> {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }


    }
}
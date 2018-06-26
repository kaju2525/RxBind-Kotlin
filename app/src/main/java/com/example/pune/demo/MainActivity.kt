package com.example.pune.demo

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.util.Log
import android.widget.Toast
import com.jakewharton.rxbinding.view.RxView
import com.jakewharton.rxbinding.widget.RxTextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btn_submit.setTextColor(Color.BLUE)

        RxView.clicks(btn_submit)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe{
                    runOnUiThread({btn_submit.setTextColor(Color.RED) })
                    Log.d("TAGS","  debounce")
        }



        RxTextView.textChanges(edit_txt)
                .subscribe{

                   when(it.count()){
                      in 0..4-> {edit_txt.backgroundTintList = ColorStateList.valueOf(Color.GRAY)
                          tv_txt.text=""
                         // tv_txt.setTextColor(Color.RED)
                      }
                      in 0..5-> {
                          edit_txt.backgroundTintList = ColorStateList.valueOf(Color.RED)
                          tv_txt.text="Very weak"
                          tv_txt.setTextColor(Color.RED)

                      } in 6..8-> {
                       edit_txt.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FFD44C03"))

                       tv_txt.text="Weak"
                       tv_txt.setTextColor(Color.parseColor("#FFD44C03"))

                   }in 8..12-> {
                       edit_txt.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FF0E8705"))

                       tv_txt.text="Good"
                       tv_txt.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF0E8705")))

                   } else-> {edit_txt.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#FF0E8705"))
                       tv_txt.text="Good"
                       tv_txt.setTextColor(ColorStateList.valueOf(Color.parseColor("#FF0E8705")))}

                   }


                }

    }

}

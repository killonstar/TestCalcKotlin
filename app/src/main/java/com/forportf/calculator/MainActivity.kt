package com.forportf.calculator

import android.content.ClipData
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import android.content.ClipboardManager
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onClickButton()
    }

    private fun onClickButton() {

        tv_zero.setOnClickListener { setTextFields(getString(R.string.zero)) }
        tv_one.setOnClickListener { setTextFields(getString(R.string.one)) }
        tv_two.setOnClickListener { setTextFields(getString(R.string.two)) }
        tv_three.setOnClickListener { setTextFields(getString(R.string.three)) }
        tv_four.setOnClickListener { setTextFields(getString(R.string.four)) }
        tv_five.setOnClickListener { setTextFields(getString(R.string.five)) }
        tv_six.setOnClickListener { setTextFields(getString(R.string.six)) }
        tv_seven.setOnClickListener { setTextFields(getString(R.string.seven)) }
        tv_eight.setOnClickListener { setTextFields(getString(R.string.eight)) }
        tv_nine.setOnClickListener { setTextFields(getString(R.string.nine)) }
        tv_point.setOnClickListener { setTextFields(getString(R.string.point)) }
        tv_div.setOnClickListener { setTextFields(getString(R.string.div)) }
        tv_mult.setOnClickListener { setTextFields(getString(R.string.mult)) }
        tv_minus.setOnClickListener { setTextFields(getString(R.string.minus)) }
        tv_plus.setOnClickListener { setTextFields(getString(R.string.plus)) }
        tv_lb.setOnClickListener { setTextFields(getString(R.string.lb)) }
        tv_rb.setOnClickListener { setTextFields(getString(R.string.rb)) }

        tv_ac.setOnClickListener {
            tv_operation.text = ""
            tv_result.text = ""
        }
        tv_res.setOnClickListener {
            try {
                val ex = ExpressionBuilder(tv_operation.text.toString()).build()
                val result = ex.evaluate()
                val longRes = result.toLong()
                if(result == longRes.toDouble())
                    tv_result.text = longRes.toString()
                else
                    tv_result.text = result.toString()
            } catch (e: Exception) {
                Log.e("Error", "Error: ${e.message}")
                tv_result.text = e.message
            }
        }
        tv_operation.setOnClickListener {
            val str = tv_operation.text.toString()
            if(str.isNotEmpty())
                tv_operation.text = str.substring(0, str.length - 1)

            tv_result.text = ""
        }
        tv_result.setOnClickListener {
            clipText(tv_result.text.toString().isNotEmpty())
        }
    }

    private fun setTextFields(str: String) {
        tv_operation.append(str)
    }

    private fun clipText(b: Boolean) {
        if(b) {
            val clipManager: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE)
                    as ClipboardManager
            val data: ClipData = ClipData.newPlainText("Text", tv_result.text.toString())
            clipManager.setPrimaryClip(data)
            Toast.makeText(this, getString(R.string.copy), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, getString(R.string.empty), Toast.LENGTH_SHORT).show()
        }
    }


}

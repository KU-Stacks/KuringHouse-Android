package com.yeonkyu.kuringhouse.presentation.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.yeonkyu.kuringhouse.R

class KuringHouseDialog(context: Context) : Dialog(context) {

    private lateinit var dismissBt: Button
    private lateinit var mainTextView: TextView
    private lateinit var subTextView: TextView

    private var mainText: String? = null
    private var subText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setContentView(R.layout.dialog_kuring_house)

        dismissBt = findViewById(R.id.dialog_dismiss_bt)
        mainTextView = findViewById(R.id.dialog_main_txt)
        subTextView = findViewById(R.id.dialog_sub_txt)
        dismissBt.setOnClickListener {
            dismiss()
        }
    }

    fun setText(title: String? = null, description: String? = null) = this.apply {
        title?.let {
            mainText = it
        }
        description?.let {
            subText = it
        }
    }

    fun setOnConfirmClickLister(onClickListener: View.OnClickListener) = this.apply {
        dismissBt.setOnClickListener { v ->
            onClickListener.onClick(v)
            dismiss()
        }
    }

    override fun show() {
        super.show()
        mainText?.let {
            mainTextView.visibility = View.VISIBLE
            mainTextView.text = it
        }
        subText?.let {
            subTextView.visibility = View.VISIBLE
            subTextView.text = it
        }
    }
}
package com.example.myapplication


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.widget.RelativeLayout
import android.widget.Toast


class NavigationLayout(context: Context?, parent: RelativeLayout?) :
    RelativeLayout(context) {

    fun initView(
        context: Context?,
        parent: RelativeLayout?
    ) { // надуваем любой xml файл разметки
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.menu_layout, parent, true)

    }

    init {
        initView(context, parent)
    }
}
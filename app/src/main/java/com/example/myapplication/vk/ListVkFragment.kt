package com.example.myapplication.vk


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import audio.Audio
import audio.Track
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_list_vk.*


class ListVkFragment: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_vk, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)




        val myAdapter = ListAdapter(Audio.getList(), object : ListAdapter.Callback {
            override fun onItemClicked(item: Track) {
                //TODO Сюда придёт элемент, по которому кликнули. Можно дальше с ним работать
            }
        })
        listRecycle.adapter = myAdapter;


    }
}


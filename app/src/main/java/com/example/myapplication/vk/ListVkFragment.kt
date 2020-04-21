package com.example.myapplication.vk


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import audio.Audio
import audio.Track
import bus.Bus
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


        val updateList = {
            setList();
            Log.v("test", "1111");
        }

        Bus.on("changeFilterPlayer", updateList);

        updateList();

    }

    fun setList() {
        val myAdapter = ListAdapter(Audio.getList(), object : ListAdapter.Callback {
            override fun onItemClicked(item: Track) {

                Audio.setTrackByUrl(item.urlTrack);

            }
        })
        listRecycle.adapter = myAdapter;
    }
}


package com.example.myapplication



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import audio.Audio
import bus.Bus
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_mini_player.view.*



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MiniPlayerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MiniPlayerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_mini_player, container, false);


        val changeActiveButton = {
            if(Audio.isActive())
                view.buttonActive.setBackgroundResource(R.drawable.pause);
            else
                view.buttonActive.setBackgroundResource(R.drawable.play);
        }
        val updateInfoTrack = {

            Log.v("Test3", Audio.currentElem().urlImage);

            view.titleTrack.text = Audio.currentElem().title;


            Glide
                .with(this)
                .load(Audio.currentElem().urlImage)
                .into(view.icoTrack)


        }



        Bus.on("changeActivePlayer", changeActiveButton);
        Bus.on("changeTrackPlayer", updateInfoTrack);


        updateInfoTrack();

        view.buttonActive.setOnClickListener{
            Audio.toggle();
        }
        view.buttonNext.setOnClickListener{
            Audio.next();
        }


        view.miniPlayer.setOnClickListener{
            openPlayer();
        }



        return view;
    }


    fun openPlayer(){
        val vkPage = Intent(this.activity, PlayerActivity::class.java);
        startActivity(vkPage);
    }

    fun changeActiveButton(){
        Log.v("Test3","Change button")
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MiniPlayerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MiniPlayerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

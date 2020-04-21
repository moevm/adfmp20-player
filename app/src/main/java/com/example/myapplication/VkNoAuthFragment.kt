package com.example.myapplication

import `class`.StorageVK
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import kotlinx.android.synthetic.main.fragment_vk_no_auth.*
import kotlinx.android.synthetic.main.fragment_vk_no_auth.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [VkNoAuthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VkNoAuthFragment : Fragment() {
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

        val view = inflater.inflate(R.layout.fragment_vk_no_auth, container, false);


        view.inputIdVk.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                view.errorAuthText.text = "";
            }
        })



        view.buttonAuthVk.setOnClickListener {
            activity?.let { it1 -> VK.login(it1, arrayListOf() ) }
        }


        view.buttonSignIn.setOnClickListener {
            Log.v("a", view.inputIdVk.text.toString());

            if (view.inputIdVk.text.toString().length === 0) {
                Log.v("error", "+");
                view.errorAuthText.text = "Id пользователя не может быть пустым!";
            } else {
                var a: Int = view.inputIdVk.text.toString().toInt();
                StorageVK.setId(a);

                val vkPage = Intent(this.activity, SplashScreenActivity::class.java);
                startActivity(vkPage);
            }
        }
        return view;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment VkNoAuthFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            VkNoAuthFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

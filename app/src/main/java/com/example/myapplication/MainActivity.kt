package com.example.myapplication



import `class`.StorageVK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import bus.Bus
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback


class ExampleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vk, container, false)
    }
}


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fragmentManager: FragmentManager? = supportFragmentManager;

        if (fragmentManager != null) {
            if (StorageVK.getId() != 0) {
                val fragment: ExampleFragment = ExampleFragment();
                fragmentManager.beginTransaction()
                    .add(R.id.testContainer, fragment)
                    .commit();
            } else {

                val fragment: VkNoAuthFragment = VkNoAuthFragment();
                fragmentManager.beginTransaction()
                    .add(R.id.testContainer, fragment)
                    .commit();
            }

        }

        val openMenu = {
            Log.v("MENU", "open");


        };


        val test1 = {
            openSplashActivity();
        }

        Bus.on("openMenu", openMenu)
        Bus.on("EXIT", test1)


    }
    private fun openSplashActivity(){

        val vkPage = Intent(this, SplashScreenActivity::class.java);
        startActivity(vkPage);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {

                if (data != null) {
                    val bundle = Bundle(data.extras)
                    val str = bundle.getString("extra-token-data")

                    if (str != null) {
                        var index = str.lastIndexOf("=");


                        StorageVK.setId(str.substring(index  + 1).toInt())

                        openSplashActivity();
                    }

                };

            }

            override fun onLoginFailed(errorCode: Int) {
                println("--!!sdfsdf!!----")

            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}





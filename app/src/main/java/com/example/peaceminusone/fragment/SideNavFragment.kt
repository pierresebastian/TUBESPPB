package com.example.peaceminusone.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.peaceminusone.AboutusActivity
import com.example.peaceminusone.MainActivity
import com.example.peaceminusone.ProfileActivity
import com.example.peaceminusone.R

class SideNavFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_side_nav, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backToMain: ImageView = view.findViewById(R.id.close_side_nav)
        backToMain.setOnClickListener(this)

        val goToAccount: LinearLayout = view.findViewById(R.id.account_side)
        goToAccount.setOnClickListener(this)

        val goToMainMenu: LinearLayout = view.findViewById(R.id.menu_side)
        goToMainMenu.setOnClickListener(this)

        val goToAboutUs: LinearLayout = view.findViewById(R.id.aboutus_side)
        goToAboutUs.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.close_side_nav -> {
                activity?.onBackPressed()
            }
            R.id.account_side -> {
                val intentAccount = Intent (activity, ProfileActivity::class.java)
                activity?.startActivity(intentAccount)
            }
            R.id.menu_side -> {
                val intentMenu = Intent (activity, MainActivity::class.java)
                activity?.startActivity(intentMenu)
            }

            R.id.aboutus_side -> {
                val intentAbout = Intent (activity, AboutusActivity::class.java)
                activity?.startActivity(intentAbout)
            }
        }
    }
}

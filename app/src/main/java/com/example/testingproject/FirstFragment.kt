package com.example.testingproject

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout

class FirstFragment : Fragment(R.layout.firs_lay) {
    private var mainWidgets: RecyclerView? = null
    private var bottomWidgets: RecyclerView? = null
    private var collapsingToolbar: AppBarLayout? = null
    private var linearLayout: LinearLayout? = null
    private var imageView: ImageView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainWidgets = view.findViewById<RecyclerView>(R.id.main_widgets)
        bottomWidgets = view.findViewById<RecyclerView>(R.id.bottom_widgets)

        mainWidgets?.adapter = MainAdapter(
            listOf(
                "MainWidget",
                "MainWidget",
                "MainWidget",
                "MainWidget",
                "MainWidget"
            )
        )
        mainWidgets?.layoutManager = LinearLayoutManager(requireContext())
        bottomWidgets?.adapter = MainAdapter(listOf("BottomWidget"))
        bottomWidgets?.layoutManager = LinearLayoutManager(requireContext())
        imageView = view.findViewById(R.id.imageView)
        imageView = view.findViewById(R.id.linear_layout_title)

        view.findViewById<TextView>(R.id.header_title).text = "headerTitle"
        view.findViewById<TextView>(R.id.header_text).text = "headerSubtitle"

        view.findViewById<TextView>(R.id.toolbarTitle).text = "headerTitle"
        view.findViewById<TextView>(R.id.toolbarSubtitle).text = "headerSubtitle"




    }

    private fun populateMainWidgets() {

    }


}
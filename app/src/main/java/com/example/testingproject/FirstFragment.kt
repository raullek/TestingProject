package com.example.testingproject

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.graphics.alpha
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

class FirstFragment : Fragment(R.layout.firs_lay) {
    private var mainWidgets: RecyclerView? = null
    private var bottomWidgets: RecyclerView? = null
    private var collapsingToolbar: CollapsingToolbarLayout? = null
    private var appBarLayout: AppBarLayout? = null
    private var linearLayoutTextviews: LinearLayout? = null
    private var toolbarInfo: androidx.appcompat.widget.Toolbar? = null
    private var imageView: ImageView? = null
    private var viewModel: FirstFragmentViewModel? = null
    private lateinit var toolbarTitle: TextView
    private lateinit var toolbarText: TextView
    private lateinit var headerTitle: TextView
    private lateinit var headerText: TextView

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
        linearLayoutTextviews = view.findViewById(R.id.linear_layout_textViews)
        collapsingToolbar = view.findViewById(R.id.collapsing_toolbar_layout)
        toolbarInfo = view.findViewById(R.id.info_toolbar)
        appBarLayout = view.findViewById(R.id.app_bar)
        toolbarTitle = view.findViewById(R.id.toolbarTitle)
        toolbarText = view.findViewById(R.id.toolbarSubtitle)
        headerTitle = view.findViewById(R.id.header_title)
        headerText = view.findViewById(R.id.header_text)
        viewModel = ViewModelProvider(this).get(FirstFragmentViewModel::class.java)

        appBarLayout?.addOnOffsetChangedListener(
            CustomOnOffsetChangeListenerIml(viewModel!!)
        )

        linearLayoutTextviews?.addOnLayoutChangeListener { v, _, top, _, bottom, _, _, _, _ ->
            // тут приходится дождаться, пока layout с заголовком и подзаголовком шапки посчитает свою
            // высоту, чтобы после этого посчитать, при каком отступе шапки (= какой величине скролла)
            // можно переводить UI в положение "appbar collapsed"
            if (v === linearLayoutTextviews) {
                val textHeight = bottom - top
                val collapseBorder = textHeight -
                    resources.getDimensionPixelOffset(R.dimen.ds_margin_xmedium_small)
                viewModel?.appbarCollapseBorder = collapseBorder
                collapsingToolbar?.scrimVisibleHeightTrigger =
                    toolbarInfo!!.height + collapseBorder
            }
        }

        headerTitle.text = "headerTitle"
        headerText.text = "headerSubtitle"

        toolbarTitle.text = "headerTitle"
        toolbarText.text = "headerSubtitle"


        viewModel?.imageScale?.observe(viewLifecycleOwner) {
            imageView?.scaleX = it
            imageView?.scaleY = it
            imageView?.alpha = it
            headerTitle.alpha = it
            headerText.alpha = it
        }
        viewModel?.isHeaderCollapsed?.observe(viewLifecycleOwner) {
            if (it) {
                toolbarTitle.visibility = View.VISIBLE
                toolbarText.visibility = View.VISIBLE
                headerTitle.visibility = View.INVISIBLE
                headerText.visibility = View.INVISIBLE
            } else {
                toolbarTitle.visibility = View.INVISIBLE
                toolbarText.visibility = View.INVISIBLE
                headerTitle.visibility = View.VISIBLE
                headerText.visibility = View.VISIBLE
            }

        }
    }

    private fun populateMainWidgets() {
    }
}
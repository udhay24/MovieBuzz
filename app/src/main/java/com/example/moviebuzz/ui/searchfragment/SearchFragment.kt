package com.example.moviebuzz.ui.searchfragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.search_fragment.*
import org.koin.android.ext.android.inject
import timber.log.Timber
import android.util.DisplayMetrics
import android.app.Activity
import android.widget.Toast
import java.security.AccessController.getContext


class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.example.moviebuzz.R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        year_view.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        year_view.isHorizontalScrollBarEnabled = false

        val linearSnapHelper = CenterSnapHelper()
        linearSnapHelper.attachToRecyclerView(year_view)
        year_view.addItemDecoration(CenterDecoration(0))
        year_view.adapter = YearAdapter(linearSnapHelper)

        year_view.addOnScrollListener( object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                (recyclerView.layoutManager as LinearLayoutManager)
                val demoView = recyclerView.findChildViewUnder((recyclerView.width/2.0f) , recyclerView.height/2.0f)
                val position = recyclerView.getChildAdapterPosition(demoView!!)
//                linearSnapHelper.scrollTo(position, smooth = true)
                Toast.makeText(context, "Position ${position}, value ${(recyclerView.adapter as YearAdapter).yearList[position]}"
                , Toast.LENGTH_SHORT).show()
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        }
        )
    }

fun something(recyclerView: RecyclerView) {
    val displayMetrics = DisplayMetrics()
    ((context) as Activity).windowManager
        .defaultDisplay
        .getMetrics(displayMetrics)
    val width = displayMetrics.widthPixels

    val adapter = recyclerView.adapter as YearAdapter

    val firstVisibleIndex = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
    val lastVisibleIndex = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
    val visibleIndexes = listOf(firstVisibleIndex..lastVisibleIndex).flatten()

    for (i in visibleIndexes) {
        val vh = recyclerView.findViewHolderForLayoutPosition(i)
        if (vh?.itemView == null) {
            continue
        }
        val location = IntArray(2)
        vh.itemView.getLocationOnScreen(location)
        val x = location[0]
        val halfWidth = vh.itemView.width * .5
        val rightSide = x + halfWidth
        val leftSide = x - halfWidth
        val isInMiddle = width * .5 in leftSide..rightSide
        if (isInMiddle) {
            // "i" is your middle index and implement selecting it as you want
            Toast.makeText(context, "position ${adapter.yearList[i]}", Toast.LENGTH_SHORT).show()
            return
        }
    }
}
}

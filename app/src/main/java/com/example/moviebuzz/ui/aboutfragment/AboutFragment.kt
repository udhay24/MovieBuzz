package com.example.moviebuzz.ui.aboutfragment

import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviebuzz.R
import kotlinx.android.synthetic.main.about_fragment.*

class AboutFragment : Fragment() {

    companion object {
        fun newInstance() = AboutFragment()
    }

    private lateinit var viewModel: AboutViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.about_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AboutViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        year_view.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        year_view.isHorizontalScrollBarEnabled = false

        val linearSnapHelper = CenterSnapHelper()
        linearSnapHelper.attachToRecyclerView(year_view)
        year_view.addItemDecoration(CenterDecoration(0))
        year_view.adapter = YearAdapter(linearSnapHelper)

        year_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                (recyclerView.layoutManager as LinearLayoutManager)
                val demoView = recyclerView.findChildViewUnder((recyclerView.width / 2.0f), recyclerView.height / 2.0f)
                val position = recyclerView.getChildAdapterPosition(demoView!!)
//                linearSnapHelper.scrollTo(position, smooth = true)
                Toast.makeText(
                    context, "Position ${position}, value ${(recyclerView.adapter as YearAdapter).yearList[position]}"
                    , Toast.LENGTH_SHORT
                ).show()
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

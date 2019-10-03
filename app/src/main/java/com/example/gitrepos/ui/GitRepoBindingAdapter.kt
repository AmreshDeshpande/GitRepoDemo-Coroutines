package com.example.gitrepos.ui

import androidx.databinding.BindingAdapter
import android.view.View
import android.widget.ProgressBar
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.gitrepos.data.Status


@BindingAdapter(value = ["dataState", "swipeToRefresh"], requireAll = false)
fun setStateForLoading(progressBar: ProgressBar, status: Status, swipeRefreshLayout: SwipeRefreshLayout) {

    progressBar.visibility = when (status) {

        Status.Loading -> {
            if (!swipeRefreshLayout.isRefreshing) {
                View.VISIBLE
            } else
                View.GONE
        }
        else -> View.GONE
    }
}

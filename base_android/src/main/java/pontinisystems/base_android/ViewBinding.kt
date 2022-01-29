package pontinisystems.base_android

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("isPresenter")
fun View.setPresenter(value: Boolean?) {
    isVisible = value ?: false
}

@BindingAdapter("data")
fun <T> RecyclerView.setRecyclerView(previousData: T, data: T) {
    if (adapter is BindableAdapter<*> && previousData != data) {
        (adapter as BindableAdapter<T>).setData(data)
    }
}
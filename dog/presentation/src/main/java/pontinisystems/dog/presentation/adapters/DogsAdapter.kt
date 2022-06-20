package pontinisystems.dog.presentation.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import pontinisystems.base_android.ActionDispatcher
import pontinisystems.dog.presentation.databinding.DogItemHolderBinding
import pontinisystems.dog.presentation.databinding.LoadItemHolderBinding
import pontinisystems.dog.presentation.entities.DogUI
import pontinisystems.dog.presentation.viewaction.DogsAction
import java.lang.IllegalArgumentException


class DogsAdapter(
    private val dispatcher: ActionDispatcher<DogsAction>
) : ListAdapter<DogUI, RecyclerView.ViewHolder>(DogsDiffCallback) {
    companion object {
        const val LOAD_MORE = 1
        const val Dog_UI = 2
    }

    val data = mutableListOf<DogUI>()

    override fun getItemCount(): Int {
        return data.count()
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position] is DogUI.LoadMore) {
            LOAD_MORE
        } else {
            Dog_UI
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LOAD_MORE -> HomeRecyclerViewHolder.LoadViewHolder(
                LoadItemHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            Dog_UI -> HomeRecyclerViewHolder.DogViewHolder(
                DogItemHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeRecyclerViewHolder.DogViewHolder -> holder.bind(
                dispatcher,
                data[position] as DogUI.DogItem
            )
            is HomeRecyclerViewHolder.LoadViewHolder -> holder
        }
    }

    fun setData(newData: List<DogUI>, isUpdate: Boolean) {
        val dogUI = arrayListOf<DogUI>()
        if (isUpdate) {
            dogUI.addAll(this.data)
        }
        dogUI.addAll(newData)
        data.clear()
        data.addAll(dogUI)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}


object DogsDiffCallback : DiffUtil.ItemCallback<DogUI>() {
    override fun areItemsTheSame(oldItem: DogUI, newItem: DogUI): Boolean {
        if (oldItem is DogUI.DogItem && newItem is DogUI.DogItem && oldItem.item.id == oldItem.item.id
        ) {
            return true
        }
        if (oldItem is DogUI.LoadMore && newItem is DogUI.LoadMore) {
            return true
        }
        return false
    }

    override fun areContentsTheSame(oldItem: DogUI, newItem: DogUI): Boolean {
        return oldItem == newItem
    }
}

sealed class HomeRecyclerViewHolder(private val binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {


    class LoadViewHolder(binding: LoadItemHolderBinding) :
        HomeRecyclerViewHolder(binding)

    class DogViewHolder(private val binding: DogItemHolderBinding) :
        HomeRecyclerViewHolder(binding) {
        fun bind(dispatcher: ActionDispatcher<DogsAction>, dogUI: DogUI.DogItem) {
            binding.data = dogUI.item
            itemView.setOnClickListener {
                dispatcher.dispatchViewAction(DogsAction.OpenDetails(dogUI.item))
            }
            Glide.with(this.itemView).load(dogUI.item.url)
                .into(binding.poster)
        }
    }
}


package com.bkosawa.samples.cleancodearchitecture.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bkosawa.samples.cleancodearchitecture.BaseSplitActivity
import com.bkosawa.samples.cleancodearchitecture.home.data.Cake
import com.bkosawa.samples.cleancodearchitecture.home.databinding.ActivityFeatureHomeBinding
import com.bkosawa.samples.cleancodearchitecture.home.databinding.ComponentListItemEvenBinding
import com.bkosawa.samples.cleancodearchitecture.home.databinding.ComponentListItemOddBinding
import com.bkosawa.samples.cleancodearchitecture.home.domain.ShowHomeUseCase
import com.bkosawa.samples.cleancodearchitecture.navigation.Navigation
import com.bkosawa.samples.cleancodearchitecture.resource.ResourceProvider
import com.bkosawa.samples.cleancodearchitecture.resource.loadImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch


class HomeActivity : BaseSplitActivity() {

    private val useCase: ShowHomeUseCase = ShowHomeUseCase()

    private lateinit var binding: ActivityFeatureHomeBinding
    private lateinit var navigation: Navigation
    private lateinit var adapter: HomeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeatureHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        navigation = Navigation(this, ResourceProvider(this))
        setupList(binding.homeList)
        lifecycle.coroutineScope.launch(Dispatchers.Main) {
            useCase.invoke()
                .collect {
                    adapter.items = it
                }
        }
    }

    private fun setupList(recyclerView: RecyclerView) {
        adapter = HomeListAdapter(navigation)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    class HomeListAdapter(private val navigation: Navigation) : RecyclerView.Adapter<HomeListAdapter.ItemViewHolder>() {

        var items: List<Cake> = emptyList()
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
            if (viewType == EVEN)
                ItemViewHolder.EvenItem(
                    ComponentListItemEvenBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            else
                ItemViewHolder.OddItem(
                    ComponentListItemOddBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

        override fun getItemViewType(position: Int): Int = if (position % 2 == 0) EVEN else ODD

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val item = items[position]
            holder.title.text = item.name
            holder.subTitle.text = item.price
            holder.picture.loadImage(item.pic)
            holder.card.setOnClickListener {
                navigation.loadAndLaunchModule(navigation.moduleDetails, keepCurrentActivity = true)
            }
        }

        override fun getItemCount(): Int = items.size

        sealed class ItemViewHolder(itemView: ViewBinding) : RecyclerView.ViewHolder(itemView.root) {
            abstract val card: View
            abstract val title: TextView
            abstract val subTitle: TextView
            abstract val picture: ImageView

            class OddItem(itemView: ComponentListItemOddBinding) : ItemViewHolder(itemView) {
                override val card: View = itemView.root
                override val title: TextView = itemView.itemTitle
                override val subTitle: TextView = itemView.itemSubtitle
                override val picture: ImageView = itemView.itemPic
            }

            class EvenItem(itemView: ComponentListItemEvenBinding) : ItemViewHolder(itemView) {
                override val card: View = itemView.root
                override val title: TextView = itemView.itemTitle
                override val subTitle: TextView = itemView.itemSubtitle
                override val picture: ImageView = itemView.itemPic
            }
        }
    }
}

private const val EVEN = 0
private const val ODD = 1

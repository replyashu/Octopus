package com.ashu.ocotopus.ui.home.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.ashu.ocotopus.data.requests.RateDish
import com.ashu.ocotopus.databinding.FragmentHomeBinding
import com.ashu.ocotopus.ui.home.HomeViewModel
import com.ashu.ocotopus.ui.home.adapter.DishAdapter
import com.ashu.ocotopus.util.Status
import com.bumptech.glide.Glide
import com.ashu.ocotopus.R
import com.ashu.ocotopus.data.requests.NotificationToken
import com.ashu.ocotopus.ui.login.LoginViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.yuyakaido.android.cardstackview.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), CardStackListener, DishAdapter.OnItemClicked {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    private var dishAdapter: DishAdapter? = DishAdapter(null)

    val sharedpreferences by lazy { context?.getSharedPreferences("preference_key", Context.MODE_PRIVATE) }

    var manager: CardStackLayoutManager? = null

    private var isRightSwiped = false

    private var pos: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        manager = CardStackLayoutManager(context, this)
        initialize()
        setUpUIElements()
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getDishes()
    }

    private fun setUpUIElements() {
        viewModel.res.observe(viewLifecycleOwner) {
            if (it.status == Status.SUCCESS) {
                it.let {
                    binding.cardstackDish.layoutManager = manager
                    dishAdapter = DishAdapter(it.data)
                    dishAdapter!!.setItemClick(this)
                    binding.cardstackDish.adapter = dishAdapter
                }

//                viewModel.sendNotificationToAll()
                if (it == null) {
                    checkEmpty()
                }
            }
        }
    }

    private fun initialize() {
//        manager.setStackFrom(StackFrom.None)
        manager?.setVisibleCount(100)
        manager?.setTranslationInterval(8.0f)
        manager?.setScaleInterval(0.95f)
        manager?.setSwipeThreshold(0.3f)
        manager?.setMaxDegree(20.0f)
        manager?.setDirections(Direction.HORIZONTAL)
        manager?.setCanScrollHorizontal(true)
        manager?.setCanScrollVertical(false)
        manager?.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager?.setOverlayInterpolator(LinearInterpolator())
        binding.cardstackDish.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = true
            }
        }
    }

    private fun paginate() {
//        val old = adapter.getDish()
//        val new = old.plus(createSpots())
//        val callback = SpotDiffCallback(old, new)
//        val result = DiffUtil.calculateDiff(callback)
//        adapter.setSpots(new)
//        result.dispatchUpdatesTo(adapter)
    }

    private fun reload() {
//        val old = adapter.getSpots()
//        val new = createSpots()
//        val callback = SpotDiffCallback(old, new)
//        val result = DiffUtil.calculateDiff(callback)
//        adapter.setSpots(new)
//        result.dispatchUpdatesTo(adapter)
    }

    private fun addFirst(size: Int) {
//        val old = adapter.getSpots()
//        val new = mutableListOf<Spot>().apply {
//            addAll(old)
//            for (i in 0 until size) {
//                add(manager.topPosition, createSpot())
//            }
//        }
//        val callback = SpotDiffCallback(old, new)
//        val result = DiffUtil.calculateDiff(callback)
//        adapter.setSpots(new)
//        result.dispatchUpdatesTo(adapter)
    }

    private fun addLast(size: Int) {
//        val old = adapter.getSpots()
//        val new = mutableListOf<Spot>().apply {
//            addAll(old)
//            addAll(List(size) { createSpot() })
//        }
//        val callback = SpotDiffCallback(old, new)
//        val result = DiffUtil.calculateDiff(callback)
//        adapter.setSpots(new)
//        result.dispatchUpdatesTo(adapter)
    }

    private fun removeFirst(size: Int) {
//        if (adapter.getDish().isEmpty()) {
//            return
//        }
//
//        val old = adapter.getDish().
//        val new = mutableListOf<DishItem>().apply {
//            addAll(old)
//            for (i in 0 until size) {
//                removeAt(manager.topPosition)
//            }
//        }
//        val callback = SpotDiffCallback(old, new)
//        val result = DiffUtil.calculateDiff(callback)
//        adapter.setSpots(new)
//        result.dispatchUpdatesTo(adapter)
    }

    private fun removeLast(size: Int) {
//        if (adapter.getSpots().isEmpty()) {
//            return
//        }
//
//        val old = adapter.getSpots()
//        val new = mutableListOf<Spot>().apply {
//            addAll(old)
//            for (i in 0 until size) {
//                removeAt(this.size - 1)
//            }
//        }
//        val callback = SpotDiffCallback(old, new)
//        val result = DiffUtil.calculateDiff(callback)
//        adapter.setSpots(new)
//        result.dispatchUpdatesTo(adapter)
    }

    private fun replace() {
//        val old = adapter.getSpots()
//        val new = mutableListOf<Spot>().apply {
//            addAll(old)
//            removeAt(manager.topPosition)
//            add(manager.topPosition, createSpot())
//        }
//        adapter.setSpots(new)
//        adapter.notifyItemChanged(manager.topPosition)
    }

    private fun swap() {
//        val old = adapter.getSpots()
//        val new = mutableListOf<Spot>().apply {
//            addAll(old)
//            val first = removeAt(manager.topPosition)
//            val last = removeAt(this.size - 1)
//            add(manager.topPosition, last)
//            add(first)
//        }
//        val callback = SpotDiffCallback(old, new)
//        val result = DiffUtil.calculateDiff(callback)
//        adapter.setSpots(new)
//        result.dispatchUpdatesTo(adapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {
        Log.d("CardStackView", "onCardDragging: d = ${direction?.name}, r = $ratio")
    }

    override fun onCardSwiped(direction: Direction?) {
//        Log.d("CardStackView", "onCardSwiped: p = ${manager.topPosition}, d = $direction")
//        if (manager.topPosition == dishAdapter!!.itemCount - 5) {
//            paginate()
//        }

        Log.d("CardStackView", "onCardSwiped: = $direction")
        direction?.let {
            if (direction.name == Direction.Right.name) {
                Log.d("CardStackView", "m")
                viewModel.markAsFavorite(sharedpreferences?.getString("user_uuid", null),
                    pos)
            }
        }

        if (manager?.topPosition == dishAdapter?.itemCount) {
            checkEmpty()
        }

    }

    override fun onCardRewound() {
//        Log.d("CardStackView", "onCardRewound: ${manager.topPosition}")

    }

    override fun onCardCanceled() {
//        Log.d("CardStackView", "onCardCanceled: ${manager.topPosition}")
    }

    override fun onCardAppeared(view: View?, position: Int) {
        Log.d("CardStackView", "onCardAppeared: ($position)")
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        Log.d("CardStackView", "onCardDisappeared: ($position)")
        pos = position
    }

    override fun rateDish(position: Int, rating: Float, dishId: Long) {
        Log.d("rating found", rating.toString() + "  " + position)
        viewModel.rateDish(RateDish(dishId, rating))

        viewModel.rating.observe(viewLifecycleOwner, Observer {
            if (it.status == Status.SUCCESS) {
                it.let {
                    dishAdapter?.updateRating(position, it.data?.dishRating, it.data?.totalRating )
                }
            }
        })
    }

    private fun checkEmpty() {
        binding.cardstackDish.visibility = View.GONE
        Glide.with(requireContext()).load(R.drawable.empty_plate).into(binding.imageEmptyView)
        binding.imageEmptyView.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        if (manager != null) {
            manager = null
        }
    }
}
package com.example.cryptographicmethods

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class VpAdapter(fa:FragmentActivity, private val listFrag: List<Fragment>): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return listFrag.size
    }

    override fun createFragment(position: Int): Fragment {
        return listFrag[position]
    }
}
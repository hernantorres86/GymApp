package com.hernan.gymapp.funtions

import com.hernan.gymapp.R
import com.hernan.gymapp.models.HarcodedLists

fun userList(): ArrayList<HarcodedLists> {

    val usList = ArrayList<HarcodedLists>()
    usList.add(HarcodedLists(R.drawable.ab1_inversions, R.string.camila))
    usList.add(HarcodedLists(R.drawable.ab2_quick_yoga, R.string.garota))
    usList.add(HarcodedLists(R.drawable.ab3_stretching, R.string.romina))
    usList.add(HarcodedLists(R.drawable.ab4_tabata, R.string.mariano))
    usList.add(HarcodedLists(R.drawable.ab5_hiit, R.string.Jonny))
    usList.add(HarcodedLists(R.drawable.ab6_pre_natal_yoga, R.string.angelica))

    return usList
}
package com.hernan.gymapp.funtions

import com.hernan.gymapp.R
import com.hernan.gymapp.models.HarcodedLists

fun userList(): ArrayList<HarcodedLists> {

    val usList = ArrayList<HarcodedLists>()
    usList.add(HarcodedLists(R.drawable.ab1_inversions, R.string.camila, "2224-55-22456"))
    usList.add(HarcodedLists(R.drawable.ab2_quick_yoga, R.string.garota, "2224-33-23456"))
    usList.add(HarcodedLists(R.drawable.ab3_stretching, R.string.romina, "2224-77-98765"))
    usList.add(HarcodedLists(R.drawable.ab4_tabata, R.string.mariano, "11-5577-2456"))
    usList.add(HarcodedLists(R.drawable.ab5_hiit, R.string.Jonny, "11-5577-2456"))
    usList.add(HarcodedLists(R.drawable.ab6_pre_natal_yoga, R.string.angelica, "11-5577-2456"))

    return usList
}
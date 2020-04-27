package com.estebakos.letscook.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeUI(
    val id: Int,
    val title: String
) : Parcelable
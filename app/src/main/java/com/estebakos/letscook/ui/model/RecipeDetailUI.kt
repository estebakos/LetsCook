package com.estebakos.letscook.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RecipeDetailUI(
    val id: Int,
    val title: String,
    val rating: Int,
    val image: String,
    val instructions: String
) : Parcelable
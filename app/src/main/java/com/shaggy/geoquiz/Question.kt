package com.shaggy.geoquiz

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int,
                    val answer: Boolean,
                    var cheated: Boolean = false)
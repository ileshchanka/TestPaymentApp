package com.example.testpaymentapp

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import javax.inject.Inject

interface ResourceProvider {

    val packageName: String

    fun getString(@StringRes res: Int): String

    fun getColor(@ColorRes res: Int): Int
}

class ResourceProviderImpl @Inject constructor(
    private val context: Context,
) : ResourceProvider {

    override val packageName: String
        get() = context.packageName

    override fun getString(res: Int) = context.resources.getString(res)

    override fun getColor(res: Int) = ContextCompat.getColor(context, res)
}
package com.blimas.motivation.infra

import android.content.Context

class SecurityPreferences(context: Context) {

    private val mSharedPreferences =
        context.getSharedPreferences("motination", Context.MODE_PRIVATE)

    fun storeString(key: String, value: String) {
        mSharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String): String {
        return mSharedPreferences.getString(key, "") ?: ""
    }
}
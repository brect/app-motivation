package com.blimas.motivation.infra

import android.content.Context

class SecurityPrefences(context: Context) {

    private val mSharedPreferences =
        context.getSharedPreferences("motination", Context.MODE_PRIVATE)

    fun setStoredString(key: String, value: String) {
        mSharedPreferences.edit().putString(key, value).apply()
    }

    fun getStoredString(key: String): String {
        return mSharedPreferences.getString(key, "") ?: ""
    }
}
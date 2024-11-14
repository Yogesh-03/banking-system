package com.news2day.yadavbankingservices.utils

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class JwtTokenManager(context: Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val encryptedPrefs = EncryptedSharedPreferences.create(
        context,
        "encrypted_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun getToken(): String? {
        return encryptedPrefs.getString("jwt_token", null)  // Replace "jwt_token" with your key
    }

    fun saveToken(token: String) {
        encryptedPrefs.edit().putString("jwt_token", token).apply()
    }

    fun clearToken() {
        encryptedPrefs.edit().remove("jwt_token").apply()
    }
}
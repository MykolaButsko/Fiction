package com.example.fiction.data.model

import android.content.Context
import androidx.annotation.StringRes
import com.example.fiction.R

enum class Genre(
    val key: String,
    @StringRes val titleRes: Int
) {
    ALL("all", R.string.all),
    FICTION("fiction", R.string.fiction),
    CULTURE("culture", R.string.culture_and_society),
    MIND("mind", R.string.mind_and_philosophy),
    GROWTH("growth", R.string.personal_growth);

    fun getTitle(context: Context): String {
        return titleRes.let { context.getString(it) }
    }

    companion object {
        fun fromKey(key: String?): Genre? =
            entries.firstOrNull { it.key == key }
    }
}
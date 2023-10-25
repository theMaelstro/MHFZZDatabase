package com.ghstudios.android.data.classes.meta
import com.ghstudios.android.data.classes.Item

/**
 * Extremely basic data about a monster.
 * Loads the first run of data that can be used to guide additional data loading
 * and initial setup
 */
class BentoMetadata(
    val id: Long,
    val name: String
)
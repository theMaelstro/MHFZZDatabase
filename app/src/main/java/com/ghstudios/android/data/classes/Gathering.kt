package com.ghstudios.android.data.classes

import com.ghstudios.android.data.util.Converter

/**
 * Defines an enumeration describing a Quest Hub.
 * Values can be converted from the Enum to a string via toString(),
 * and from a string to an Enum via QuestHub.from()
 */
enum class GatherSeason {
    SUMMER,
    BREEDING,
    WINTER;

    companion object {
        private val converter = Converter(
            "0" to SUMMER,
            "1" to BREEDING,
            "2" to WINTER
        )

        @JvmStatic fun from(value: String) = converter.deserialize(value)
    }

    override fun toString() = converter.serialize(this)
}

enum class GatherTime {
    DAY,
    NIGHT;

    companion object {
        private val converter = Converter(
            "0" to DAY,
            "1" to NIGHT
        )

        @JvmStatic fun from(value: String) = converter.deserialize(value)
    }

    override fun toString() = converter.serialize(this)
}

/*
 * Class for Gathering
 */
class Gathering {

    /* Getters and Setters */
    var id: Long = -1

    // Item gathered
    var item: Item? = null

    // Location the item is gathered
    var location: Location? = null

    var area: String? = ""        // Area # of location
    var site: String? = ""        // Type of gathering node; bug, mine, fish, etc.
    var rank: String? = ""        // Quest Rank found in
    var rate: Float = 0f          // Gather rate

    //What group is it a part of, (Unique within an area)
    var group: Int = 0

    //is this a fixed gathering point
    var isFixed: Boolean = false

    //Is it a rare point
    var isRare: Boolean = false

    // Port or village
    var season: String? = ""    // Gather season

    var time: String? = ""    // Gather time

    var quantity: Int = 1
}

package com.ghstudios.android.data.classes

import com.ghstudios.android.ITintedIcon
import com.ghstudios.android.data.util.Converter
import com.ghstudios.android.mhgendatabase.R

enum class MonsterType {
    BRUTEWYVERN,
    FLYINGWYVERN,
    CARAPACEON,
    LEVIATHAN,
    PELAGUS,
    PISCINEWYVERN,
    BIRDWYVERN,
    UNKNOWN,
    FANGEDWYVERN,
    ELDERDRAGON,
    HERBIVORE,
    LYNIAN,
    NEOPTERON,
    SNAKEWYVERN;

    companion object {
        private val converter = Converter(
            "Brute Wyvern" to BRUTEWYVERN,
            "Flying Wyvern" to FLYINGWYVERN,
            "Carapaceon" to CARAPACEON,
            "Leviathan" to LEVIATHAN,
            "Pelagus" to PELAGUS,
            "Piscine Wyvern" to PISCINEWYVERN,
            "Bird Wyvern" to BIRDWYVERN,
            "Unknown" to UNKNOWN,
            "Fanged Wyvern" to FANGEDWYVERN,
            "Elder Dragon" to ELDERDRAGON,
            "Herbivore" to HERBIVORE,
            "Lynian" to LYNIAN,
            "Neopteron" to NEOPTERON,
            "Snake Wyvern" to SNAKEWYVERN
        )

        @JvmStatic fun from(value: String) = converter.deserialize(value)
    }

    override fun toString() = converter.serialize(this)
}



/*
 * Class for Monster
 */
class Monster : ITintedIcon {

    /* Getters and Setters */
    var id: Long = -1                // Monster id
    var name = ""            // Monster name
    var jpnName = ""        // Japanese name

    var monsterClass = MonsterClass.LARGE
    var monsterClassType: MonsterType? = null

    var sizeMin = ""
    var sizeMax = ""

    var fileLocation = ""    // File location for image

    /**
     * Bitwise flags assembled as an integer.
     * flags are hasEX/hasLR/hasHR/hasGOU/hasG/hasZ1/hasZ2/hasZ3/hasZ4
     */
    var metadata = 0

    override fun getIconResourceString(): String {
        return fileLocation
    }

}

@file:JvmName("Converters")
package com.ghstudios.android.data.classes

import android.util.Log
import com.ghstudios.android.data.util.Converter

fun getElementFromString(elementStr: String?) = when(elementStr ?: "") {
    "" -> ElementStatus.NONE
    "Fire" -> ElementStatus.FIRE
    "Water" -> ElementStatus.WATER
    "Thunder" -> ElementStatus.THUNDER
    "Ice" -> ElementStatus.ICE
    "Dragon" -> ElementStatus.DRAGON
    "Black Flame" -> ElementStatus.BLACKFLAME
    "Blaze" -> ElementStatus.BLAZE
    "Burning Zero" -> ElementStatus.BURNINGZERO
    "Crimson Demon" -> ElementStatus.CRIMSONDEMON
    "Darkness" -> ElementStatus.DARKNESS
    "Emperors Roar" -> ElementStatus.EMPERORSROAR
    "Frozen Seraphim" -> ElementStatus.FROZENSERAPHIM
    "Kanade" -> ElementStatus.KANADE
    "Light" -> ElementStatus.LIGHT
    "Sound" -> ElementStatus.SOUND
    "Tenshou" -> ElementStatus.TENSHOU
    "Thunder Pole" -> ElementStatus.THUNDERPOLE
    "Wind" -> ElementStatus.WIND

    "Poison" -> ElementStatus.POISON
    "Paralysis" -> ElementStatus.PARALYSIS
    "Para" -> ElementStatus.PARALYSIS
    "Sleep" -> ElementStatus.SLEEP
    "Blastblight" -> ElementStatus.BLAST
    "Blast" -> ElementStatus.BLAST
    "Exhaust" -> ElementStatus.EXHAUST
    "KO" -> ElementStatus.STUN
    "Jump" -> ElementStatus.JUMP
    "Mount" -> ElementStatus.MOUNT
    
    else -> {
        Log.w("TAG", "Failed to convert element $elementStr")
        ElementStatus.NONE
    }
}

val MonsterClassConverter = Converter(
        0 to MonsterClass.LARGE,
        1 to MonsterClass.SMALL,
        2 to MonsterClass.DEVIANT,
        3 to MonsterClass.EXTREME,
        4 to MonsterClass.TYPE
)

val ItemTypeConverter = Converter(
        "" to ItemType.ITEM,
        "Bento" to ItemType.BENTO,
        "Decoration" to ItemType.DECORATION,
        "Palico Weapon" to ItemType.PALICO_WEAPON,
        "Palico Armor" to ItemType.PALICO_ARMOR,
        "Weapon" to ItemType.WEAPON,
        "Armor" to ItemType.ARMOR,
        "Materials" to ItemType.MATERIAL
)
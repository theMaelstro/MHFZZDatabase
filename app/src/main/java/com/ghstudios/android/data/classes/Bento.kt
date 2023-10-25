package com.ghstudios.android.data.classes

import com.ghstudios.android.ITintedIcon

class Bento : Item() {
    /* Getters and Setters */
    //var id: Long = -1                // Bento id
    //var name = ""            // Bento name
    var season = ""
    var ingredient1: Item? = null
    var ingredient2: Item? = null
    var effect1: Long = -1
    var effect2: Long = -1
    var effect3: Long = -1
    var effect4: Long = -1
    //var fileLocation: String? = ""            // File location for image


    //override fun getIconResourceString() = fileLocation ?: ""
}
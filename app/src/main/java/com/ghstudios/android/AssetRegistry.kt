@file:JvmName("AssetRegistry")
package com.ghstudios.android

import android.util.Log
import com.ghstudios.android.data.classes.ElementStatus
import com.ghstudios.android.mhgendatabase.R


/**
 * The AssetRegistry is used to create key-value maps between any specified types
 * @param T Type for Key
 * @param K Type for Value
 */

typealias AdderFun<T, K> = (name: T, resId: K) -> Unit

class Registry<T, K>(val source: Map<T, K>) {
    operator fun get(key: T): K? {
        if (!source.containsKey(key)) {
            Log.w("AssetRegistry", "Value $key not found in registry")
        }
        return source[key]
    }

    fun get(key: T, default: K): K {
        return get(key) ?: default
    }
}

private fun <T, K> createRegistry(initLambda: (AdderFun<T, K>) -> Unit): Registry<T, K> {
    val mutableRegistry = HashMap<T, K>()
    initLambda { name: T, resId: K ->
        mutableRegistry[name] = resId
    }
    return Registry(mutableRegistry)
}


/**
 * A mapping from an element/status enumeration to a drawable resource
 */
val ElementRegistry = createRegistry<ElementStatus, Int> { register ->
    register(ElementStatus.NONE, R.color.transparent)

    register(ElementStatus.FIRE, R.drawable.element_fire)
    register(ElementStatus.WATER, R.drawable.element_water)
    register(ElementStatus.THUNDER, R.drawable.element_thunder)
    register(ElementStatus.ICE, R.drawable.element_ice)
    register(ElementStatus.DRAGON, R.drawable.element_dragon)

    register(ElementStatus.BLACKFLAME,R.drawable.element_black_flame)
    register(ElementStatus.BLAZE,R.drawable.element_blaze)
    register(ElementStatus.BURNINGZERO,R.drawable.element_burning_zero)
    register(ElementStatus.CRIMSONDEMON,R.drawable.element_crimson_demon)
    register(ElementStatus.DARKNESS,R.drawable.element_darkness)
    register(ElementStatus.EMPERORSROAR,R.drawable.element_empror_roar)
    register(ElementStatus.FROZENSERAPHIM,R.drawable.element_frozen_seraphim)
    register(ElementStatus.MUSIC,R.drawable.element_music)
    register(ElementStatus.LIGHT,R.drawable.element_light)
    register(ElementStatus.SOUND,R.drawable.element_sound)
    register(ElementStatus.TENSHOU,R.drawable.element_tenshou)
    register(ElementStatus.THUNDERPOLE, R.drawable.element_thunder_pole)
    register(ElementStatus.WIND,R.drawable.element_wind)

    register(ElementStatus.POISON, R.drawable.status_poison)
    register(ElementStatus.PARALYSIS, R.drawable.status_paralysis)
    register(ElementStatus.SLEEP, R.drawable.status_sleep)
    register(ElementStatus.BLAST, R.drawable.status_blastblight)
    register(ElementStatus.MOUNT, R.drawable.status_mount)
    register(ElementStatus.EXHAUST, R.drawable.status_exhaust)
    register(ElementStatus.JUMP, R.drawable.status_jump)
    register(ElementStatus.STUN, R.drawable.status_stun)
}
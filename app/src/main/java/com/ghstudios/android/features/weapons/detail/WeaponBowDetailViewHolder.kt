package com.ghstudios.android.features.weapons.detail

import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ghstudios.android.AssetLoader
import com.ghstudios.android.data.classes.ElementStatus

import com.ghstudios.android.data.classes.Weapon
import com.ghstudios.android.mhgendatabase.R
import com.ghstudios.android.util.getColorCompat
import kotlinx.android.synthetic.main.fragment_monster_damage.view.*
import kotlinx.android.synthetic.main.view_weapon_detail_bow.view.*

/**
 * Inflates bow data into a view and binds the bow data.
 * Use in the WeaponDetailFragment or any sort of fragment to show full bow data.
 */
class WeaponBowDetailViewHolder(parent: ViewGroup) : WeaponDetailViewHolder {
    private val view: View
    private val chargeCells: List<TextView>

    init {
        val inflater = LayoutInflater.from(parent.context)
        view = inflater.inflate(R.layout.view_weapon_detail_bow, parent, true)

        chargeCells = listOf(
                view.weapon_bow_charge1,
                view.weapon_bow_charge2,
                view.weapon_bow_charge3,
                view.weapon_bow_charge4
        )
    }

    override fun bindWeapon(weapon: Weapon) {
        val context = view.context

        // Usual weapon parameters
        view.attack_value.text = weapon.attack.toString()
        view.affinity_value.text = weapon.affinity + "%"
        view.defense_value.text = weapon.defense.toString()
        view.slots.setSlots(weapon.numSlots, 0)

        // bind weapon element (todo: if awaken element ever returns...make an isAwakened flag instead)
        if (weapon.elementEnum != ElementStatus.NONE) {
            view.element1_icon.setImageDrawable(AssetLoader.loadIconFor(weapon.elementEnum))
            view.element1_value.text = weapon.elementAttack.toString()
            view.element1_group.visibility = View.VISIBLE
        }

        view.weapon_bow_arc.text = weapon.recoil

        // Charge levels
        for ((view, level) in chargeCells.zip(weapon.charges)) {
            view.visibility = View.VISIBLE
            view.text = AssetLoader.localizeChargeLevel(level)

            if (level.locked) {
                val lockColor = context.getColorCompat(R.color.text_color_secondary)
                view.setTextColor(lockColor)
            }
        }

        // Internal function to "enable" a weapon coating view
        fun setCoating(enabled: Boolean, view: TextView) {
            if (enabled) {
                val color = context.getColorCompat(R.color.text_color_focused)
                view.setTextColor(color)
                view.setTypeface(null, Typeface.BOLD)
            }
        }

        weapon.coatings?.let { coatings ->
            /*
            setCoating(coatings.power1, view.power_1_text)
            setCoating(coatings.power2, view.power_2_text)
            setCoating(coatings.elem1, view.element_1_text)
            setCoating(coatings.elem2, view.element_2_text)
            setCoating(coatings.crange, view.crange_text)
            setCoating(coatings.poison, view.poison_text)
            setCoating(coatings.para, view.para_text)
            setCoating(coatings.sleep, view.sleep_text)
            setCoating(coatings.exhaust, view.exhaust_text)
            setCoating(coatings.blast, view.blast_text)

            setCoating(coatings.hasPower, view.power_label)
            setCoating(coatings.hasElem, view.element_label)
            */

            setCoating(coatings.power, view.power_text)
            setCoating(coatings.explosive, view.explosive_text)
            setCoating(coatings.impact, view.impact_text)
            setCoating(coatings.poison1, view.poison_1_text)
            setCoating(coatings.poison2, view.poison_2_text)
            setCoating(coatings.poison3, view.poison_3_text)
            setCoating(coatings.poison4, view.poison_4_text)
            setCoating(coatings.para1, view.para_1_text)
            setCoating(coatings.para2, view.para_2_text)
            setCoating(coatings.para3, view.para_3_text)
            setCoating(coatings.para4, view.para_4_text)
            setCoating(coatings.sleep1, view.sleep_1_text)
            setCoating(coatings.sleep2, view.sleep_2_text)
            setCoating(coatings.sleep3, view.sleep_3_text)
            setCoating(coatings.sleep4, view.sleep_4_text)

            setCoating(coatings.hasPoison, view.poison_label)
            setCoating(coatings.hasPara, view.para_label)
            setCoating(coatings.hasSleep, view.sleep_label)

        }
    }
}

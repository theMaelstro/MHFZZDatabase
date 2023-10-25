package com.ghstudios.android.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.ghstudios.android.ITintedIcon
import com.ghstudios.android.mhgendatabase.R

class BentoRecipeCell : LinearLayout {

    @JvmField
    @BindView(R.id.title)
    var titleView: SubHeaderCell? = null

    @JvmField
    @BindView(R.id.list)
    var itemsView: LinearLayout? = null

    constructor(ctx: Context?) : super(ctx) {
        init("")
    }

    constructor(ctx: Context?, title: String?) : super(ctx) {
        init(title)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ItemRecipeCell)
        val titleText: String
        titleText = try {
            attributes.getString(R.styleable.ItemRecipeCell_titleText)
        } finally {
            attributes.recycle()
        }
        init(titleText)
    }

    fun init(title: String?) {
        orientation = VERTICAL
        val inflater = LayoutInflater.from(this.context)
        inflater.inflate(R.layout.cell_item_recipe, this, true)
        ButterKnife.bind(this)
        setTitleText(title)
    }

    /**
     * Sets the title text. If the title is null or empty, the title is hidden.
     * @param title
     */
    fun setTitleText(title: String?) {
        titleView!!.setLabelText(title)
        if (title != null && !title.trim { it <= ' ' }.isEmpty()) {
            titleView!!.visibility = VISIBLE
        } else {
            titleView!!.visibility = GONE
        }
    }

    fun addItem(icon: ITintedIcon?, itemName: String?, qty: Int, key: Boolean): View {
        val cell = IconLabelTextCell(context)
        cell.setLeftIcon(icon)
        cell.setLabelText(itemName)
        cell.setValueText(qty.toString())
        cell.setKeyVisibility(key)
        itemsView!!.addView(cell)
        return cell
    }

    /**
     * Clears all components in this recipe
     */
    fun clearItems() {
        itemsView!!.removeAllViews()
    }
}
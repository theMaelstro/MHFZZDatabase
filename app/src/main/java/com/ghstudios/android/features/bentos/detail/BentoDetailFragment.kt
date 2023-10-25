package com.ghstudios.android.features.bentos.detail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.ghstudios.android.AssetLoader
import com.ghstudios.android.ClickListeners.BasicItemClickListener
import com.ghstudios.android.ClickListeners.ItemClickListener
import com.ghstudios.android.components.*
import com.ghstudios.android.data.classes.Bento
import com.ghstudios.android.data.classes.Item
import com.ghstudios.android.data.classes.MonsterClass
import com.ghstudios.android.features.monsters.list.MonsterListFragment
import com.ghstudios.android.mhgendatabase.R
import com.ghstudios.android.util.applyArguments
import kotlinx.android.synthetic.main.fragment_item_listitem.*
import kotlinx.android.synthetic.main.fragment_item_listitem.view.*

class BentoDetailFragment : Fragment() {

    @BindView(R.id.skill_list) lateinit var skillListView: LinearLayout

    // stored to allow add to wishlist to work
    var bentoId: Long = 0
    var bentoName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        // Check for a Item ID as an argument, and find the item
        val args = arguments ?: return
        bentoId = args.getLong(ARG_BENTO_ID, -1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.fragment_bento_detail,
            container, false
        )
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (bentoId == -1L) {
            return
        }
        val viewModel = ViewModelProviders.of(this)[BentoDetailViewModel::class.java]

        viewModel.setBento(bentoId)
        viewModel.bentoData.observe(
            this
        ) { bento: Item? ->
            populateBento(
                bento
            )
        }

        viewModel.recipeData.observe(
            this
        ) { recipes: List<Bento>? ->
            populateRecipes(
                recipes
            )
        }
    }

    /**
     * Updates the UI to set the bento data.
     * @param bento
     */
    private fun populateBento(bento: Item?) {
        if (bento == null) return

        bentoId = bento.id
        bentoName = bento.name

        activity?.setTitle(bentoName)
    }

    private fun populateRecipes(recipes: List<Bento>?){


        // Use a layout inflater to get a row view
        val inflater = LayoutInflater.from(context)
        skillListView.removeAllViews()

        if (recipes == null || recipes.isEmpty()) {
            //skillSection.visibility = View.GONE
            return
        }

        for(recipe in recipes) {
            val view = inflater.inflate(R.layout.fragment_bento_listitem, skillListView, false)

            val ingredient1TextView = view.findViewById<TextView>(R.id.item1_name)
            val ingredient1ImageView = view.findViewById<ImageView>(R.id.item1_icon)

            val ingredient2TextView = view.findViewById<TextView>(R.id.item2_name)
            val ingredient2ImageView = view.findViewById<ImageView>(R.id.item2_icon)

            val effect1TextView = view.findViewById<TextView>(R.id.bentoeffect1)
            val effect2TextView = view.findViewById<TextView>(R.id.bentoeffect2)
            val effect3TextView = view.findViewById<TextView>(R.id.bentoeffect3)
            val effect4TextView = view.findViewById<TextView>(R.id.bentoeffect4)

            val bentoHPListView = view.findViewById<LinearLayout>(R.id.BentoHp)
            val bentoSTListView = view.findViewById<LinearLayout>(R.id.BentoSt)
            val bentoATKListView = view.findViewById<LinearLayout>(R.id.BentoAtk)
            val bentoDEFListView = view.findViewById<LinearLayout>(R.id.BentoDef)

            val item1ListView = view.findViewById<LinearLayout>(R.id.item1)
            val item2ListView = view.findViewById<LinearLayout>(R.id.item2)

            ingredient1TextView.text = recipe.ingredient1!!.name.toString()
            AssetLoader.setIcon(ingredient1ImageView, recipe.ingredient1)

            ingredient2TextView.text = recipe.ingredient2!!.name.toString()
            AssetLoader.setIcon(ingredient2ImageView, recipe.ingredient2)

            if(recipe.effect1 != 0L) {
                effect1TextView.text = recipe.effect1.toString()
            } else {
                bentoHPListView.visibility = View.GONE
            }

            if(recipe.effect2 != 0L) {
                effect2TextView.text = recipe.effect2.toString()
            } else {
                bentoSTListView.visibility = View.GONE
            }

            if(recipe.effect3 != 0L) {
                effect3TextView.text = recipe.effect3.toString()
            } else {
                bentoATKListView.visibility = View.GONE
            }

            if(recipe.effect4 != 0L) {
                effect4TextView.text = recipe.effect4.toString()
            } else {
                bentoDEFListView.visibility = View.GONE
            }

            val item1listener = BasicItemClickListener(view.context, recipe.ingredient1!!.id)
            item1ListView.setOnClickListener(item1listener)

            val item2listener = BasicItemClickListener(view.context, recipe.ingredient2!!.id)
            item2ListView.setOnClickListener(item2listener)

            skillListView.addView(view)
        }
    }

    companion object {
        private const val ARG_BENTO_ID = "BENTO_ID"
        fun newInstance(bentoId: Long): BentoDetailFragment {
            val args = Bundle()
            args.putLong(ARG_BENTO_ID, bentoId)
            val f = BentoDetailFragment()
            f.arguments = args
            return f
        }
    }
}
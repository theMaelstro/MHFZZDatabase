package com.ghstudios.android.features.quests

import com.ghstudios.android.AssetLoader
import com.ghstudios.android.mhgendatabase.R
import com.ghstudios.android.BasePagerActivity
import com.ghstudios.android.MenuSection
import com.ghstudios.android.data.classes.QuestHub


class QuestListPagerActivity : BasePagerActivity() {

    override fun onAddTabs(tabs: BasePagerActivity.TabAdder) {
        setTitle(R.string.title_quests)

        tabs.addTab(AssetLoader.localizeHub(QuestHub.HUNTERQUESTS)) {
            QuestExpandableListFragment.newInstance(QuestHub.HUNTERQUESTS)
        }

        tabs.addTab(AssetLoader.localizeHub(QuestHub.SPECIALQUESTS)) {
            QuestExpandableListFragment.newInstance(QuestHub.SPECIALQUESTS)
        }

        tabs.addTab(AssetLoader.localizeHub(QuestHub.GHUNTERQUESTS)) {
            QuestExpandableListFragment.newInstance(QuestHub.GHUNTERQUESTS)
        }

        tabs.addTab(AssetLoader.localizeHub(QuestHub.GSPECIALQUESTS)) {
            QuestExpandableListFragment.newInstance(QuestHub.GSPECIALQUESTS)
        }

        tabs.addTab(AssetLoader.localizeHub(QuestHub.GUILD)) {
            QuestExpandableListFragment.newInstance(QuestHub.GUILD)
        }

        tabs.addTab(AssetLoader.localizeHub(QuestHub.PERMIT)) {
            QuestExpandableListFragment.newInstance(QuestHub.PERMIT)
        }

        tabs.addTab(AssetLoader.localizeHub(QuestHub.CONQUEST)) {
            QuestExpandableListFragment.newInstance(QuestHub.CONQUEST)
        }

        tabs.addTab(AssetLoader.localizeHub(QuestHub.OTHER)) {
            QuestExpandableListFragment.newInstance(QuestHub.OTHER)
        }

        tabs.addTab(AssetLoader.localizeHub(QuestHub.EVENT)) {
            QuestExpandableListFragment.newInstance(QuestHub.EVENT)
        }
/*
        tabs.addTab(AssetLoader.localizeHub(QuestHub.VILLAGE)) {
            QuestExpandableListFragment.newInstance(QuestHub.VILLAGE)
        }

 */

        super.setAsTopLevel()
    }

    override fun getSelectedSection(): Int {
        return MenuSection.QUESTS
    }
}

package com.bgomez.picker.ui.ui_controllers


import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.bgomez.picker.R
import com.bgomez.picker.common.utils.LogWrapper


val TAG : String = SearchActivity::class.java.simpleName


/**
 * Searchable activity that receives search requests and performs operation
 */
class SearchActivity() : AppCompatActivity() {

    private var query : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setContentView(R.layout.activity_search)

        this.handleIntent(this.intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        this.intent = intent

        this.handleIntent(intent)
    }

    private fun handleIntent(intent : Intent?) {
        if (intent?.action == Intent.ACTION_SEARCH) {
            //intent.getStringExtra(SearchManager.APP_DATA)
            intent.getStringExtra(SearchManager.QUERY)?.let {
                LogWrapper.d(TAG, "delivered search for query $it")

                this.query = it!!

                this.launchSearch()
            }
        }
    }

    private fun launchSearch() {
        (this.supportFragmentManager.fragments[0] as ListFragment)?.searchPics(this.query)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        this.menuInflater.inflate(R.menu.menu, menu)

        val searchManager : SearchManager = this.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        // Get the SearchView
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        // Set the searchable configuration
        searchView.apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(this@SearchActivity.componentName))

            isIconifiedByDefault = false// so expand it by default
            isSubmitButtonEnabled = true
            isQueryRefinementEnabled = true
        }

        return true
    }
}
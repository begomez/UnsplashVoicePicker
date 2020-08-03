package com.bgomez.picker.ui.ui_controllers


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.bgomez.picker.R
import com.bgomez.picker.common.models.PicData
import com.bgomez.picker.presentation.viewmodel.GalleryViewModel
import com.bgomez.picker.presentation.result.ResourceResult
import com.bgomez.picker.presentation.result.Status
import com.bgomez.picker.ui.Constants
import com.bgomez.picker.ui.adapters.GridDecoration
import com.bgomez.picker.ui.adapters.PicsAdapter
import com.bgomez.picker.common.utils.LogWrapper
import kotlinx.android.synthetic.main.fragment_list.*


/**
 * Created by bernatgomez on August,2020
 */
class ListFragment() : Fragment() {

    private lateinit var adapter : PicsAdapter

    private lateinit var viewmodel : GalleryViewModel<List<PicData>, GalleryViewModel.Params>

    /**
     *
     */
    companion object Builder {
        fun newInstance(query : String) : ListFragment {
            val b = bundleOf(Pair(Constants.ParamNames.PARAM_QUERY, query))

            val f = ListFragment()

            f.arguments = b

            return f
        }
    }

    init {
        initVM()
    }

    private fun initVM() {
        this.viewmodel =
            GalleryViewModel()

        //val vm = ViewModelFactory.createGalleryVM<List<PicData>, GalleryViewModel.Params>()::class.java
        //this.viewmodel = ViewModelProviders.of(this).get(vm)

        this.viewmodel.Publisher().pubData.observe(this, Observer {
            display(it)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun extractExtras() {
        val query = this.arguments?.getString(Constants.ParamNames.PARAM_QUERY) ?: ""
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    fun searchPics(query : String) {
        this.viewmodel.searchPics(query)
    }

    private fun display(res : ResourceResult<List<PicData>>) {
        when (res.status) {
            Status.LOADING -> {
                this.showLoading()
            }

            Status.OK -> {
                this.hideLoading()

                this.manageResult(res.data!!)
            }

            Status.KO -> {
                this.hideLoading()

                this.configureList(empty = true)
            }
        }
    }

    private fun showLoading() {
        this.picListResultContainer.visibility = View.GONE
        this.picListLoading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        this.picListResultContainer.visibility = View.VISIBLE
        this.picListLoading.visibility = View.GONE

    }

    private fun showList() {
        this.picList.visibility = View.VISIBLE
        this.picListEmptyMsg.visibility = View.GONE
    }

    private fun showEmpty() {
        this.picList.visibility = View.GONE
        this.picListEmptyMsg.visibility = View.VISIBLE
    }

    private fun manageResult(data : List<PicData>) {
        this.createAdapter(data)
        this.configureList(empty= data?.isEmpty())
    }

    private fun createAdapter(data : List<PicData>) {
        this.adapter =
            PicsAdapter(
                data = data,
                listener = { picData ->
                    onImageSelected(picData)
                })

        this.adapter.notifyDataSetChanged()
    }

    fun onImageSelected(data : PicData) {
        LogWrapper.d(TAG, "Selected image ${data.toString()}")

        val i = Intent()
        i.putExtra(Constants.ParamNames.PARAM_IMAGE_SELECTED, data)

        this.activity?.setResult(Activity.RESULT_OK, i)
        this.activity?.finish()
    }

    private fun configureList(empty : Boolean) {

        if (empty) {
            this.showEmpty()

        } else {
            val COLS = 2

            val mgr = GridLayoutManager(this.context, COLS)

            mgr.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {

                override fun getSpanSize(position: Int): Int {
                    var span: Int = 0

                    when (position % 4) {
                        0 -> span = 1
                        1 -> span = 2
                        2 -> span = 2
                        3 -> span = 1
                    }

                    return span
                }
            }

            this.picList.adapter = this.adapter
            this.picList.layoutManager = mgr
            this.picList.addItemDecoration(
                GridDecoration(
                    this.resources.getInteger(R.integer.gallery_item_decor_pad)
                )
            )
            this.picList.setHasFixedSize(true)

            this.showList()
        }
    }
}
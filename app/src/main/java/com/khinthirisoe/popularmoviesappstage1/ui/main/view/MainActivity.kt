package com.khinthirisoe.popularmoviesappstage1.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.khinthirisoe.popularmoviesappstage1.R
import com.khinthirisoe.popularmoviesappstage1.core.App
import com.khinthirisoe.popularmoviesappstage1.core.di.component.DaggerActivityComponent
import com.khinthirisoe.popularmoviesappstage1.core.di.module.ActivityModule
import com.khinthirisoe.popularmoviesappstage1.ui.BaseActivity
import com.khinthirisoe.popularmoviesappstage1.ui.SettingsActivity
import com.khinthirisoe.popularmoviesappstage1.ui.main.MainContract
import com.khinthirisoe.popularmoviesappstage1.ui.main.adapter.MovieAdapter
import com.khinthirisoe.popularmoviesappstage1.ui.main.model.Result
import com.khinthirisoe.popularmoviesappstage1.utils.PrefUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainContract.View, SwipeRefreshLayout.OnRefreshListener {
    @Inject
    lateinit var presenter: MainContract.Presenter

    private var mAdapter: MovieAdapter? = null
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private lateinit var mConnectionLayout: LinearLayout

    private var mConnectivityDisposable: Disposable? = null
    private var isLoading = false
    private var pageNumber = 1
    private var visibleItemCount = 1
    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0
    private var mGridLayoutManager: GridLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initInjector()
        presenter.onAttachView(this)

        initView()

        setUpLoadMoreListener()
    }

    private fun initInjector() {
        val component =
            DaggerActivityComponent.builder().appComponent(App.appComponent).activityModule(ActivityModule(this))
                .build()
        component.inject(this)
    }

    private fun initView() {
        mProgressBar = findViewById(R.id.progress_bar)
        mRecyclerView = findViewById(R.id.recycler_movies)
        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        mConnectionLayout = findViewById(R.id.connection_layout)

        mGridLayoutManager = GridLayoutManager(this@MainActivity, 2)
        mRecyclerView.layoutManager = mGridLayoutManager
        mRecyclerView.setHasFixedSize(true)

        mAdapter = MovieAdapter(this, null)
        mRecyclerView.adapter = mAdapter
        mSwipeRefreshLayout.setOnRefreshListener(this)

    }

    private fun setUpLoadMoreListener() {
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                visibleItemCount = mGridLayoutManager!!.childCount
                totalItemCount = mGridLayoutManager!!.itemCount
                lastVisibleItem = mGridLayoutManager!!.findFirstVisibleItemPosition()

                if (visibleItemCount + lastVisibleItem >= totalItemCount && !isLoading) {
                    pageNumber++
                    presenter.loadMoviesList(loadPreference(), pageNumber, PrefUtils.getApiKey(this@MainActivity))
                    isLoading = true
                }
            }
        })
    }

    private fun loadMoviesList() {
        mConnectivityDisposable = ReactiveNetwork
            .observeNetworkConnectivity(applicationContext)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ connectivity ->
                if (connectivity.available()) {
                    mConnectionLayout.visibility = View.GONE
                    presenter.loadMoviesList(loadPreference(), pageNumber, PrefUtils.getApiKey(this@MainActivity))
                } else {
                    mSwipeRefreshLayout.isRefreshing = false
                    mConnectionLayout.visibility = View.VISIBLE
                    Toast.makeText(App.appComponent.getContext(), R.string.no_internet, Toast.LENGTH_SHORT)
                        .show()
                }
            }, { throwable ->
                val message =
                    if (throwable.message == null) resources.getString(R.string.fetch_failed) else throwable.message
                Log.d(TAG, message)
            })

    }

    override fun onRefresh() {
        loadMoviesList()
    }

    override fun showMoviesList(lists: ArrayList<Result>) {
        if (pageNumber == 1) {
            mAdapter = MovieAdapter(this, lists)
            mRecyclerView.adapter = mAdapter
            mSwipeRefreshLayout.isRefreshing = false
        } else {
            mAdapter!!.addItems(lists)
            mRecyclerView.adapter = mAdapter
            mAdapter!!.notifyDataSetChanged()
            mSwipeRefreshLayout.isRefreshing = false
            isLoading = false
            mGridLayoutManager?.scrollToPosition(mAdapter!!.itemCount - lists.size)
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        mProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        mProgressBar.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        pageNumber = 1
        loadMoviesList()
    }

    private fun loadPreference(): String {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val sortedType = prefs.getString("sortedType", "")
        var sorted: String?

        sorted = if (sortedType != null) {
            sortedType
        } else {
            "popularity.desc"
        }
        return sorted
    }

    override fun onPause() {
        super.onPause()
        safelyDispose(mConnectivityDisposable)
    }

    private fun safelyDispose(disposable: Disposable?) {
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }

    override fun onDestroy() {
        presenter.onDetachView()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_setting, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item!!.itemId
        if (id == R.id.action_settings) {
            startActivity(Intent(this, SettingsActivity::class.java))
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}

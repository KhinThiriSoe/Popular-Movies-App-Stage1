package com.khinthirisoe.popularmoviesappstage1.core.base

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.khinthirisoe.popularmoviesappstage1.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class BaseActivity : AppCompatActivity() {

    private var connectivityDisposable: Disposable? = null
    private var internetDisposable: Disposable? = null

    override fun onResume() {
        super.onResume()

        connectivityDisposable = ReactiveNetwork.observeNetworkConnectivity(applicationContext)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ connectivity ->
                if (connectivity.failover()) {
                    Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show()
                }
            }, { throwable ->
                val message = if (throwable.message == null) "loading failed!" else throwable.message
                Log.d(TAG, message)
            })

        internetDisposable = ReactiveNetwork.observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ isConnectedToInternet ->
                if (!isConnectedToInternet) {
                    Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show()
                }
            }, { throwable ->
                val message = if (throwable.message == null) "loading failed!" else throwable.message
                Log.d(TAG, message)
            })

    }

    override fun onPause() {
        super.onPause()
        safelyDispose(connectivityDisposable)
        safelyDispose(internetDisposable)
    }

    private fun safelyDispose(disposable: Disposable?) {
        if (disposable != null && !disposable.isDisposed) {
            disposable.dispose()
        }
    }

    companion object {
        val TAG = "message"
    }
}
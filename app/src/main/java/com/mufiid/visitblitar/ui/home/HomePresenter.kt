package com.mufiid.visitblitar.ui.home

import com.mufiid.visitblitar.api.ApiConfig
import com.mufiid.visitblitar.view.WisataView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomePresenter(private val wisataView: WisataView) {
    fun getTourismRandom(random: Int? = null) {
        wisataView.loading()
        CompositeDisposable().add(
            ApiConfig.instance().getListOfTouristAttraction(random = random)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when (it.status) {
                        200 -> wisataView.getDataWisata(it.message, it.data)
                        else -> wisataView.failedGetDataWisata(it.message)
                    }
                    wisataView.loading(false)
                }, {
                    wisataView.failedGetDataWisata(it.message)
                    wisataView.loading(false)
                })
        )
    }

    fun getTourismRecommended(recommended: Int? = null) {
        wisataView.loading()
        CompositeDisposable().add(
            ApiConfig.instance().getListOfTouristAttraction(recommended = recommended)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when (it.status) {
                        200 -> wisataView.getDataWisataRecommended(it.message, it.data)
                        else -> wisataView.failedGetDataWisata(it.message)
                    }
                    wisataView.loading(false)
                }, {
                    wisataView.failedGetDataWisata(it.message)
                    wisataView.loading(false)
                })
        )
    }
}
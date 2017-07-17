package com.rmnivnv.cryptomoon.network

import android.util.Log
import com.rmnivnv.cryptomoon.model.*
import com.rmnivnv.cryptomoon.utils.getPriceDisplayBodyFromJson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by rmnivnv on 12/07/2017.
 */
class NetworkManager(val api: CryptoCompareAPI) {

//    fun getAllCoins(callback: GetAllCoinsCallback): Disposable  {
//        return api.getCoinsList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe( { callback.onSuccess(it) }, { callback.onError(it) } )
//    }

    fun getPrice(map: Map<String, ArrayList<String>>, callback: GetPriceCallback): Disposable {
        return api.getPrice(getQuery(map, FSYMS), getQuery(map, TSYMS))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { getPriceDisplayBodyFromJson(it, map) }
                .subscribe( { callback.onSuccess(it) }, { callback.onError(it) } )
    }

    private fun getQuery(map: Map<String, ArrayList<String>>, type: String): String {
        var result: String = ""

        map.forEach { (key, value) ->
            if (key == type) {
                value.forEach {
                    result += """$it,"""
                }
            }
        }
        if (result.isNotEmpty()) result = result.substring(0, result.length - 1)
        Log.d("getQuery", result)
        return result
    }

}
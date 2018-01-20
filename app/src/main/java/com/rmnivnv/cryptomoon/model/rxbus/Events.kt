package com.rmnivnv.cryptomoon.model.rxbus

/**
 * Created by rmnivnv on 25/07/2017.
 */

class CoinsLoadingEvent(val isLoading: Boolean)

class OnDeleteCoinsMenuItemClickedEvent

class MainCoinsListUpdatedEvent

class SearchHashTagUpdated(val hashTag: String)

class CoinsSortMethodUpdated(val sort: String?)

class LanguageChanged(val language: String)

class TransactionAdded
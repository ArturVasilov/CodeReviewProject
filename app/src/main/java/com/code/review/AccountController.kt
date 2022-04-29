package com.code.review

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException

class AccountController {

    private val networkEngine = DefaultNetworkEngine("https://myserver.com")
    private val listeners = mutableListOf<AccountListener>()

    fun addListener(listener: AccountListener) = listeners.add(listener)
    fun removeListener(listener: AccountListener) = listeners.add(listener)

    private fun notifyListeners(newAccount: Account) {
        listeners.forEach { it.onAccountChanged(newAccount) }
    }

    fun loadCurrentAccount() {
        val params = mapOf<String, String>()
        val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        coroutineScope.launch {
            while (true) {
                try {
                    val account = networkEngine.loadAccount(params)
                    notifyListeners(account)
                    return@launch
                } catch (io: IOException) {
                    continue
                }
            }
        }
    }
}

interface AccountListener {
    fun onAccountChanged(account: Account)
}

package com.code.review

class DefaultNetworkEngine(baseUrl: String) : NetworkEngine {

    override suspend fun loadAccount(params: Map<String, String>): Account {
        // TODO: implement real network call for account
        return Account("id", "login", "password", "Artur")
    }
}

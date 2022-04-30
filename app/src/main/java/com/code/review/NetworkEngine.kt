package com.code.review

interface NetworkEngine {
    suspend fun loadAccount(params: Map<String, String>): Account
}

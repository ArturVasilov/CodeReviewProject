package com.code.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {

    private val accountController = AccountController()

    private lateinit var profileName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profileName = findViewById(R.id.profile_name)

        accountController.loadCurrentAccount()
    }

    override fun onResume() {
        super.onResume()
        accountController.addListener(object : AccountListener {
            override fun onAccountChanged(account: Account) {
                onAccountUpdated(account)
            }
        })
    }

    override fun onPause() {
        accountController.removeListener(object : AccountListener {
            override fun onAccountChanged(account: Account) {
                onAccountUpdated(account)
            }
        })
        super.onPause()
    }

    private fun onAccountUpdated(newAccount: Account) {
        profileName.text = newAccount.displayName
    }
}

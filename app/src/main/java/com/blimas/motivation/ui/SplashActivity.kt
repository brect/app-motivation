package com.blimas.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.blimas.motivation.R
import com.blimas.motivation.infra.MotivationConstants
import com.blimas.motivation.infra.SecurityPrefences
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPrefences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurityPreferences = SecurityPrefences(this)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        buttonSave.setOnClickListener(this)

        verifyName()
    }

    private fun verifyName() {
       val name = mSecurityPreferences.getStoredString(MotivationConstants.KEY.PERSON_NAME)
        if (name != null && name != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun handleSave() {
        val mName: String = editName.text.toString()

        if (mName != "") {
            mSecurityPreferences.setStoredString(MotivationConstants.KEY.PERSON_NAME, mName)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Preencha o campo nome", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(view: View?) {
        val id = view?.id
        if (id == R.id.buttonSave){
            handleSave()
        }
    }
}
package com.blimas.motivation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.blimas.motivation.R
import com.blimas.motivation.infra.MotivationConstants
import com.blimas.motivation.infra.SecurityPrefences
import com.blimas.motivation.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPrefences: SecurityPrefences
    private var mPhraseFilter: Int = MotivationConstants.PHRASEFILTER.ALL
    private val mMock: Mock = Mock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Remove Toolbar
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        // Inicializa variáveis
        mSecurityPrefences = SecurityPrefences(this)

        // Adiciona eventos
        setListeners()

        // Inicializa
        handleFilter(R.id.buttonInfinity)
        refreshPhrase()
        showUserName()

    }

    /**
     * Atribui eventos aos elementos
     * */
    private fun setListeners() {
        buttonInfinity.setOnClickListener(this)
        buttonHappy.setOnClickListener(this)
        buttonMorning.setOnClickListener(this)
        buttonPhrase.setOnClickListener(this)
    }

    /**
     * Atualiza frase de motivação
     * */
    private fun refreshPhrase() {
        textPhrase.text = mMock.getPhrase(mPhraseFilter)
    }

    /**
     * Busca o nome do usuário
     * */
    private fun showUserName() {
        val name = mSecurityPrefences.getStoredString(MotivationConstants.KEY.PERSON_NAME)
        textName.text = "Olá, $name!"
    }


    /**
     * Trata eventos de click
     * */
    override fun onClick(view: View) {
        val id: Int = view.id

        val listId = listOf(
            R.id.buttonInfinity,
            R.id.buttonHappy,
            R.id.buttonMorning
        )
        if (id in listId) {
            handleFilter(id)
        } else if (id == R.id.buttonPhrase) {
            refreshPhrase()
        }
    }

    /**
     * Trata o filtro aplicado para as frases
     * */
    private fun handleFilter(id: Int) {

        buttonInfinity.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent))
        buttonHappy.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent))
        buttonMorning.setColorFilter(ContextCompat.getColor(this, R.color.colorAccent))

        when (id) {
            R.id.buttonInfinity -> {
                mPhraseFilter = MotivationConstants.PHRASEFILTER.ALL
                buttonInfinity.setColorFilter(
                    ContextCompat.getColor(this, R.color.white)
                )
            }
            R.id.buttonHappy -> {
                mPhraseFilter = MotivationConstants.PHRASEFILTER.HAPPY

                // Possível de trocar a fonte da imagem e atribuir ao elemento de layout
                // imageHappy.setImageResource(R.drawable.ic_happy_grey)

                // Possível de trocar a cor do ícone
                buttonHappy.setColorFilter(
                    ContextCompat.getColor(this, R.color.white)
                )
            }
            else -> {
                mPhraseFilter = MotivationConstants.PHRASEFILTER.MORNING
                buttonMorning.setColorFilter(
                    ContextCompat.getColor(this, R.color.white)
                )
            }
        }

    }
}
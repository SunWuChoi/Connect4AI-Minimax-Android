package edu.towson.connect4_ai

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.towson.connect4_ai.fragments.LoginActivityFragment
import kotlinx.android.synthetic.main.menu_activity.*

class MainMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)

        twoPlayerMode_btn.setOnClickListener{ launchTwoPlayerMode() }
        onePlayerMode_btn.setOnClickListener{ launchVsSilvaMode() }
        onlineMode_btn.setOnClickListener { launchAccountMode() }
    }

    fun launchTwoPlayerMode(){
        val intent = Intent(this, TwoPlayerModeActivity::class.java)
        startActivity(intent)
    }

    fun launchVsSilvaMode(){
        val intent = Intent(this, VsSilvaActivity::class.java)
        startActivity(intent)
    }

    fun launchAccountMode(){
        val intent = Intent(this, LoginActivityFragment::class.java)
        startActivity(intent)
    }

}
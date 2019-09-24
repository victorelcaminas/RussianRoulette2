package org.ieselcaminas.victor.russianroulette2

import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val NUM_BULLETS = 100
    lateinit var bullets: Array<Button>
    var bulletPosition: Int = 0
    var gameOver = false
    lateinit var bangLayout: FrameLayout
    lateinit var textBang: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bangLayout = findViewById(R.id.bang_layout)
        textBang = findViewById(R.id.textview_bang)

        calculateRandomNumber()

        bullets = Array<Button>(NUM_BULLETS) {
            Button(this, null, android.R.attr.buttonStyleSmall)
        }

        //var barrel: LinearLayout = findViewById(R.id.barrel_layout)
        val barrel = findViewById<LinearLayout>(R.id.barrel_layout)
        for (button in bullets) {
            button.setText((bullets.indexOf(button) + 1).toString())
            barrel.addView(button)
            button.setOnClickListener() {
                if (!gameOver) shoot(button)
            }
        }

        val buttonReload = findViewById<Button>(R.id.button_reload)
        buttonReload.setOnClickListener() {
            reload()
        }

    }

    private fun reload() {
        calculateRandomNumber()
        gameOver = false
        for (button in bullets) {
            button.isEnabled = true
        }
        bangLayout.setBackgroundColor( resources.getColor(R.color.no_bang_color))
        textBang.visibility = View.INVISIBLE

    }

    private fun shoot(button: Button) {
        button.isEnabled = false
        if (bullets.indexOf(button) == bulletPosition) {
            bang()
        }
    }

    private fun bang() {
        bangLayout.setBackgroundColor(Color.RED)
        textBang.visibility = View.VISIBLE

        gameOver = true
    }

    fun calculateRandomNumber() {
        bulletPosition = Random.nextInt(0, NUM_BULLETS)
    }
}

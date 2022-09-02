package com.example.cryptographicmethods

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import com.example.cryptographicmethods.databinding.ActivityCurvePointsBinding
import kotlin.math.pow

class curvePoints : AppCompatActivity() {
    lateinit var binding: ActivityCurvePointsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCurvePointsBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_laboratory_work1)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)    //показать кнопку назат
        supportActionBar?.title = " "  //Имя тулбара
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean { //работа кнопки назад
        finish()
        return true
    }
    fun onClickFind(view: View) {
        binding.tvPoints.text = ""
        binding.TAB.removeAllViews()
        var Point = binding.txPoint.text.split(',').toTypedArray()
        var Ground = binding.txGround.text.toString().toInt()
        var x = Ground

        var Matrix: Array<Array<String>> = Array(3, { Array(x + 1, { "0" }) })

        Matrix[0][0] = "\t"
        Matrix[1][0] = "x^3 + " + Point[0] + "x + " + Point[1] + " mod " + Ground.toString()
        Matrix[2][0] = "y^2 mod " + Ground.toString()
        for (i in 0..2) { //строка
            if (i == 0) {
                for (k in 1..x) Matrix[i][k] = (k - 1).toString()
            }
            if (i == 1) {
                for (j in 1..x) { // столбец
                    var cor: Double = Matrix[0][j].toDouble()
                    var xx = cor.pow(3.0)
                    var left = (xx + (Point[0].toDouble() * cor) + Point[1].toDouble()).toInt()
                    Matrix[i][j] = ((left).mod(Ground)).toString()
                }
            }
            if (i == 2) {
                for (j in 1..x) { // столбец
                    var P2 = Matrix[0][j].toDouble()
                    var y = P2.pow(2.0).toInt()
                    Matrix[i][j] = (y.mod(Ground)).toString()
                }
            }
        }
        for (i in 0..2) { //строка
            var tableRow = TableRow(this) //столбцы
            for (j in 0..x) { // столбец
                var tv = TextView(this)//тип ячейки
                tv.setTextColor(getResources().getColor(R.color.black))
                tv.setBackgroundResource(R.drawable.board)                      // задаем фон в виде заранее нарисованной рамки
                tv.text = Matrix[i][j]//записываем текст в ячеку
                tv.setGravity(Gravity.CENTER)      // центруем текст
                tableRow.addView(tv, j)//добавляем ячейку в строку
                binding.TAB.setColumnStretchable(
                    j,
                    true
                ) //растягиваем каждый столбец по свободному месту равномерно
            }
            binding.TAB.addView(tableRow, i) // добавляем строку в таблицу tableLayout
        }
        for (i in 1..x) { // столбец
            for(j in 1..x){
                if(Matrix[1][i] == Matrix[2][j]) binding.tvPoints.append("("+(i-1).toString()+","+(j-1).toString()+") ")
            }
        }
    }
}
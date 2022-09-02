package com.example.cryptographicmethods

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptographicmethods.databinding.ActivityNxNBinding

class n_x_n : AppCompatActivity() {
    lateinit var binding: ActivityNxNBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNxNBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)    //показать кнопку назат
        supportActionBar?.title = "n на n" //Имя тулбара
        val textview = binding.textView26 as TextView
        textview.movementMethod = ScrollingMovementMethod() // скролл тествью
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean { //работа кнопки назад
        finish()
        return true
    }
    fun onClickSplitSercret(view: View) {
        if(!isFieldEmpty()) {
            binding.textView26.text = ""
            var n = binding.mbN.text.toString().toInt() //переменная n
            var p = binding.mbP.text.toString().toInt() //переменная p
            if(Simple(p) == true) {
                var  SK = binding.mbK.text //вектор ключа
                if(isKeyCorrect(SK.toString())) {
                    var SecretKey = SK.split(",").toTypedArray() //секретный ключ
                    var Matrix: Array<Array<Int>> =
                        Array(n, { Array(SecretKey.size, { 0 }) }) //массив матрицы
                    //Matrix[0] = arrayOf(26, 0, 13, 11, 23, 25)     // первая строка таблицы
                    //Matrix[1] = arrayOf(2,  7,  15, 12, 27,  6)     // вторая строка таблицы
                    //Matrix[2] = arrayOf(1,  3 , 24,  6,   0, 16)     // третья строка таблицы
                    //Matrix[3] = arrayOf(12, 2,   7,   0,  7,   0)     // третья строка таблицы

                    //// генерируем фрагменты
                    for (i in 0..n - 2) {  //строка
                        binding.textView26.append("Фрагмент сотрудника " + i + "\n")
                        for (j in 0..SecretKey.size - 1) { //столбец
                            Matrix[i][j] = (0..p).random()
                            binding.textView26.append(Matrix[i][j].toString() + " ")
                        }
                        binding.textView26.append("\n")
                    }
                    // считаем фрагмент последнего работника
                    binding.textView26.append("Фрагмент сотрудника " + n + "\n")
                    for (j in 0..SecretKey.size - 1) {  //столбец
                        var sum = 0
                        for (i in 0..n - 2) { //строка
                            sum = sum + Matrix[i][j]
                        }
                        var ss = SecretKey[j].toInt() - sum
                        Matrix[n - 1][j] = (ss).mod(p)
                        binding.textView26.append(Matrix[n - 1][j].toString() + " ")
                    }
                    binding.textView26.visibility = View.VISIBLE
                    ///////////сборка ключа///////////////
                    binding.textView26.append("\n"+"Сборка ключа" + "\n")
                    var MatrixSbor: Array<Int> =
                        Array(n, { 0 }) //массив матрицы сборки
                    for (j in 0..SecretKey.size - 1) {  //столбец
                        var sum = 0
                        for (i in 0..n - 1) { //строка
                            sum = sum + Matrix[i][j]
                        }
                        MatrixSbor[j] = (sum).mod(p)
                        binding.textView26.append("S"+j+": "+sum.toString() + " mod " + p.toString() +" = "+ MatrixSbor[j].toString() + "\n")
                        //binding.textView26.append(MatrixSbor[j].toString() + " ")
                    }
                } else binding.mbK.error = "Некорректный ввод"
            } else binding.mbP.error = "Число не является простым!"
        }
    }
    fun onClickGenerateSimple(view: View) { //генерируем простое число
        var res = false
        var N = 1
        while(res == false) {
            N = (2..100).random()
            res = Simple(N)
        }
        binding.mbP.setText(N.toString())
    }
    private fun Simple(N: Int):Boolean { //функция нахождени простого числа
        for (i in 2..(N / 2)) {
            if (N % i == 0) {
                return false
            }
        }
        return true
    }
    private fun isFieldEmpty():Boolean{         //Проверка на пустое поле
        binding.apply {//для обращение ко многим объектам экррана
            if(mbN.text.isNullOrEmpty())mbN.error = "Поле должно быть заполнено!"
            if(mbP.text.isNullOrEmpty())mbP.error = "Поле должно быть заполнено!"
            if(mbK.text.isNullOrEmpty())mbK.error = "Поле должно быть заполнено!"
            return mbN.text.isNullOrEmpty() || mbP.text.isNullOrEmpty() || mbK.text.isNullOrEmpty()
        }
    }

    private fun isKeyCorrect(Key: String):Boolean {// проверка ключа на корректность
        var _Key = Key.split(",").toTypedArray()
        for(i in _Key){
            if(i.toIntOrNull() == null){ //число или 0 (не число)?
                return false
            }
        }
        return true
    }
}
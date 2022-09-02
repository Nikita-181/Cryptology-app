package com.example.cryptographicmethods

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptographicmethods.databinding.ActivityChinaBinding


class China : AppCompatActivity() {
    lateinit var binding: ActivityChinaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChinaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)    //показать кнопку назат
        supportActionBar?.title = "Схема Миньотта" //Имя тулбара
        //supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.RED)) //меняем цвет ActionBar
        //binding.button31.width = binding.button30.width
    }
    var nod = 0

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //работа кнопки назад
        finish()
        return true
    }

    fun onClickSplitSercret(view: View) {
        Secret_sharing()
    }
    fun onClickBuildSK(view: View) { //сборка ключа
        if(!isFieldEmpty2()) {
            binding.textView33.visibility = View.VISIBLE
            binding.textView33.text = ""
            // var k = binding.mbK2.text.toString().toInt() //переменная k
            var m = binding.editTextNumber2.text
            var m_array = m.split(",").toTypedArray()
            //массив m
            var a = binding.editTextNumber.text
            var a_array = a.split(",").toTypedArray() //массив a
            if(a_array.size == m_array.size) { //кол-во а = m
                var M = arrayListOf<Int>()
                var Ni = arrayListOf<Int>()
                binding.textView33.append("M: ")
                var mult_M = 1 // произведение всех элементов m(i)
                m_array.forEach{
                    mult_M = mult_M * it.toInt()
                }
                for (i in 0..m_array.size - 1) { //считаем М

                        M.add(mult_M/m_array[i].toInt())
                    binding.textView33.append(M[i].toString() + " ")
                }
                binding.textView33.append("\n" + "N: ")
                for (i in 0..m_array.size - 1) {
                    Ni.add(Ef(M[i], m_array[i].toInt()))
                    binding.textView33.append(Ni[i].toString() + " ")
                }
                var x = 0
                binding.textView33.append("\n" + "x = ")
                for (i in 0..M.size - 1) {
                    x = x + (a_array[i].toInt() * M[i] * Ni[i])
                    if (i != M.size - 1) binding.textView33.append(a_array[i] + "*" + M[i] + "*" + Ni[i] + " + ") else binding.textView33.append(
                        a_array[i] + "*" + M[i] + "*" + Ni[i]
                    )
                }
                binding.textView33.append("\n" + "x = " + x.toString())

                binding.textView33.append("\n" + "c = " + x.toString() + " mod (" +mult_M+ ")")
                var c = x.mod(mult_M) //находим ключ
                binding.textView33.append("\n" + "c = " + c.toString())
            }
            else { // если неверное кол-во элементов
                binding.textView33.text = ""
                binding.editTextNumber.error = "Неверное кол-во элементов!"
                binding.editTextNumber2.error = "Неверное кол-во элементов!"
            }
        }
        else { // если пусто
            binding.textView33.text = ""
        }
    }
    private fun Ef(a: Int, n: Int): Int { //расширенный алгоритм Евклида
        var t = 0
        var r = n
        var newt = 1
        var newr = a
        while (newr != 0){
            var quotient  = r / newr
            var tempT = newt
            newt = t - quotient * newt      //(t, newt) := (newt, t − quotient × newt)
            t = tempT
            var tempTT = newr
            newr = r -quotient * newr       //(r, newr) : = (newr, r − quotient × newr)
            r = tempTT
        }
        if (r > 1) binding.textView33.append("\n"+"Уравнение "+ a.toString()+"*Ni = 1mod"+n.toString() +" не обратимо!"+"\n")
        if (t < 0) t = t + n
        return t
    }

    private fun Secret_sharing()
    {
        if(!isFieldEmpty()) {
            binding.textView33.visibility = View.VISIBLE
            binding.textView33.text = ""
            nod = 0
            var n = binding.mbN2.text.toString().toInt() //переменная n
            var k = binding.mbK2.text.toString().toInt() //переменная k
            if(k>1 && k <=n) {
                val m_array = Array(n, { 0 })
                val a_array = Array(n, { 0 })
                ///////////////////находим mi и mj
                var m1 = (0..200).random()
                var m2 = m1
                while (nod != 1) {
                    m2 = m2 + 1
                    if (m1 != m2 && m1 != 1 && m2 != 1) {
                        nod = NOD(m1, m2)
                    }
                }
                m_array[0] = m1
                m_array[1] = m2
                nod = 0
                for (i in 1..n - 2) {
                    nod = 0
                    m1 = m_array[i]
                    m2 = m1
                    while (nod != 1) {
                        m2 = m2+1
                        if (m1 != m2 && !m_array.contains(m2) && m2 != 0 && m1 != 1 && m2 != 1) {
                            nod = NOD(m1, m2)
                        }
                    }
                    m_array[i + 1] = m2
                }
                binding.textView33.text = "Массив m-k "
                for (i in 0..m_array.size - 1) {
                    binding.textView33.append(m_array[i].toString() + " ")
                }
                var min = 1
                var max = 1
                for (i in 0..k - 1) {
                    min = min * m_array[i]
                }
                var i = k - 2
                while (i >= 0) {
                    max = max * m_array[m_array.size - 1 - i]
                    i--
                }
                binding.textView33.append("\n" + "min = " + min.toString() + "\n" + "max = " + max.toString())
                binding.textView33.append("\n" + min.toString() + " - " + max.toString() + " >= " + "3 * " + max.toString())
                binding.textView33.append("\n" + (min - max).toString() + " >= " + (3 * max).toString())
                binding.textView33.append("\n" + max.toString() + " < с < " + min.toString())
                ///////////////////////////////
                if ((min - max) < (3*max) ) { //если условине не выполнено, то пробуем все заново
                    Secret_sharing()
                } else {
                    var c = (max + 1..min - 1).random()
                    binding.textView33.append("\n" + "c = " + c.toString())
                    binding.textView33.append("\n" + "Фрагменты секрета: ")
                    for (i in 0..n - 1) {
                        a_array[i] = c.mod(m_array[i])
                        binding.textView33.append(a_array[i].toString() + " ")
                    }
                }
            }
            else { // Если kне удв 1<k<=n
                binding.mbK2.error = "k должно удовлетворять 1<k<=n!"
                binding.textView33.text = ""
            }
        } else { // Если пусто
            binding.textView33.text = ""
        }
    }

    private fun NOD(n: Int, m: Int): Int { //функция нахождени НОД
        var nod = 0
        for (i in 1..(n * m + 1)-1) {
            if (m % i == 0 && n % i == 0) {
                nod = i
            }
        }
        return nod
       // return if (y == 0) x else NOD(y, x % y)
    }
    private fun isFieldEmpty():Boolean{         //Проверка на пустое поле
        binding.apply {//для обращение ко многим объектам экррана
            if(mbN2.text.isNullOrEmpty())mbN2.error = "Поле должно быть заполнено!"
            if(mbK2.text.isNullOrEmpty())mbK2.error = "Поле должно быть заполнено!"
            return mbN2.text.isNullOrEmpty() || mbK2.text.isNullOrEmpty()
        }
    }
    private fun isFieldEmpty2():Boolean{         //Проверка на пустое поле для сборки
        binding.apply {//для обращение ко многим объектам экррана
            if(editTextNumber.text.isNullOrEmpty())editTextNumber.error = "Поле должно быть заполнено!"
            if(editTextNumber2.text.isNullOrEmpty())editTextNumber2.error = "Поле должно быть заполнено!"
            return editTextNumber.text.isNullOrEmpty() || editTextNumber2.text.isNullOrEmpty()
        }
    }
}

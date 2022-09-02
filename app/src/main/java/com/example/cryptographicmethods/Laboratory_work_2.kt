package com.example.cryptographicmethods

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.example.cryptographicmethods.databinding.ActivityLaboratoryWork1Binding
import com.example.cryptographicmethods.databinding.ActivityLaboratoryWork2Binding
import java.lang.Math.abs
import java.math.BigDecimal
import java.math.RoundingMode
import android.text.method.ScrollingMovementMethod

import android.R

import android.widget.TextView




class Laboratory_work_2 : AppCompatActivity() {
    lateinit var binding:ActivityLaboratoryWork2Binding
    val Alph: Array<String> = arrayOf("А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ","Ъ","Ы","Ь", "Э", "Ю", "Я","0","1", "2", "3", "4", "5", "6", "7", "8", "9")
   // val AlphFre: Array<String> = arrayOf("О", "Е","А","И","Н","Т","С","Р","В","Л","К","М","Д","П","У","Я","Ы","Ь","Г","З","Б","Ч","Й","Х","Ж","Ш","Ю","Ц","Щ","Э","Ф","Ё","Ъ")
    var AlphFre= arrayListOf<String>()//Буквы эталонного теста
    var Frequency = arrayListOf<Double>() // Частоты эталонного текста
    //val Frequency = arrayOf<Double>(10.97, 8.45, 8.01, 7.35, 6.70, 6.26, 5.47, 4.73, 4.54, 4.40, 3.49, 3.21, 2.98, 2.81, 2.62, 2.01, 1.90, 1.74, 1.70, 1.65, 1.59, 1.44, 1.21, 0.97, 0.94, 0.73, 0.64, 0.48, 0.36, 0.32, 0.26, 0.04, 0.04)
    //val Frequency = arrayOf<Double>(8.01, 1.59, 4.54, 1.70, 2.98, 8.45, 0.04, 0.94, 1.65, 7.35, 1.21, 3.49, 4.40, 3.21, 6.70, 10.97, 2.81, 4.73, 5.47,6.26, 2.62, 0.26, 0.97, 0.48, 1.44, 0.73, 0.36, 0.04, 1.90, 1.74, 0.32, 0.64, 2.01)
    var Leter = arrayListOf<String>() //Буквы текста
    var LeterFre = arrayListOf<Double>() // Частоты букв текста
    var InAlphFre: Int? = null // Позиция буквы из мас букв частот в Алф
    var InLeter: Int? = null// Позиция буквы из текста  в Алф
    var Key: Int = 0 // Ключ
    var index=0 // для for
    var TM = arrayListOf<Char>() //лист сообщения
    var TMG = arrayListOf<Char>() //лист эталонного текста
    var F : Double?=null
    var FA : String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaboratoryWork2Binding.inflate(layoutInflater)
        //setContentView(R.layout.activity_laboratory_work1)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)    //показать кнопку назат
        supportActionBar?.title = "Частотный криптоанализ шифра Цезаря"  //Имя тулбара
        val textview = binding.textView9 as TextView
        textview.movementMethod = ScrollingMovementMethod() // скролл тествью
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean { //работа кнопки назад
        finish()
        return true
    }
    fun onClickFrequency(view: View) {
        if(!isFieldEmpty()){
            AlphFre.clear()
            Frequency.clear()
            TMG.clear()
            TMG.addAll(binding.textMGT.text.toList()) // переносим сообщение в лист
            var Fre = 0.0
            for (i in 0..TMG.size - 1) { //считаем частоты
                for (k in 0..TMG.size - 1) {
                    if (TMG[i].uppercaseChar() == TMG[k].uppercaseChar() && !AlphFre.contains(TMG[i].toString().toUpperCase()) ) {
                        Fre++
                    }
                }
                if (Fre > 0) {
                    Frequency.add((Fre / TMG.size) * 100) //частота буквы
                    //LeterFre.add(("%.2f".format((Fre/TM.size)*100)).toDouble())
                    AlphFre.add(TMG[i].toString().toUpperCase())
                    Fre = 0.0
                }
            }
            //
            ///
            //
            // сделать сортировку
            //
            //
            //
        }

    }
    fun onClickFrequencyAnalysis(view: View) {
        if(!isFieldEmpty()){
            binding.textView9.text = ""
            index = 0
            TM.clear()
            Leter.clear()
            LeterFre.clear()
            TM.addAll(binding.textMessage2.text.toList()) // переносим сообщение в лист
            //избавляемся от влияния лишних символов
            var CTM = arrayListOf<Char>()
            for(i in 0..TM.size-1)
            {
                if(Alph.contains(TM[i].toString())){
                    CTM.add(TM[i])
                }
            }
            var Fre = 0.0
            for (i in 0..TM.size - 1) { //считаем частоты
                for (k in 0..TM.size - 1) {
                    if (TM[i] == TM[k] && !Leter.contains(TM[i].toString()) && Alph.contains(TM[i].toString())) {
                        Fre++
                    }
                }
                if (Fre > 0) {
                    LeterFre.add((Fre / CTM.size) * 100) //частота буквы
                    //LeterFre.add(("%.2f".format((Fre/TM.size)*100)).toDouble())
                    Leter.add(TM[i].toString())
                    Fre = 0.0
                }
            }
            //вывод частот
            for (i in 0..Leter.size - 1) {
                binding.textView9.append(Leter[i] + " - " + ((BigDecimal(LeterFre[i]).setScale(2, RoundingMode.HALF_EVEN)).toString()) + "\n") //textview.append(i); добавляет элемент i в textView к старому тексту
            }
            var t = binding.textView9.text
            for (i in 0..TM.size - 1) {
                if (!Leter.contains(TM[i].toString()) && !t.contains(TM[i].toString())) {
                    binding.textView9.append("\"" + TM[i].toString() + "\"" + " - " + "0" + "\n") //вывод элемнтов невходящих в алфавит
                }
            }
            binding.textView9.visibility = View.VISIBLE
            //LeterFre.maxOrNull() - макс число
            //LeterFre.indexOf(i) индекс символа i

            //ищем ключ
            var M = LeterFre.indexOf(LeterFre.maxOrNull())
            for (i in 0..Frequency.size - 1) {
                if (LeterFre[M] >= Frequency[i] && Alph.contains(AlphFre[i])) {
                    var L = LeterFre[M]
                    var LA = Leter[M]
                     F = Frequency[i]
                     FA = AlphFre[i]
                    for (k in 0..Alph.size - 1) {
                        if (Alph[k] == AlphFre[i]) {
                            InAlphFre = k
                        }
                        if (Alph[k] == Leter[M]) {
                            InLeter = k
                        }
                    }
                    Key = abs(InAlphFre!! - InLeter!!) // дельта( модуль разности)
                    break
                }
            }
            //декодиррование

            binding.textView10.text = "Ключ: " + Key.toString() + "("+ FA + " - " + F.toString()+")"+ "\n" + Decode()
            binding.textView10.visibility = View.VISIBLE
            binding.button6.visibility = View.VISIBLE
        }
    }
    fun onClickContinue(view: View)
    {
        var M = LeterFre.indexOf(LeterFre.maxOrNull())
        index ++
        for (i in index..Frequency.size - 1) {
            if (LeterFre[M] >= Frequency[i] && Alph.contains(AlphFre[i])) {
                var L = LeterFre[M]
                var LA = Leter[M]
                F = Frequency[i]
                FA = AlphFre[i]
                for (k in 0..Alph.size - 1) {
                    if (Alph[k] == AlphFre[i]) {
                        InAlphFre = k
                    }
                    if (Alph[k] == Leter[M]) {
                        InLeter = k
                    }
                }
                Key = abs(InAlphFre!! - InLeter!!) // дельта( модуль разности)
                break
            }
        }
        //декодиррование

        binding.textView10.text = "Ключ: " + Key.toString() + "("+ FA + " - " + F.toString()+")"+ "\n" + Decode()
    }
    private fun Decode():String{
        var EnTM = arrayOfNulls<String>(TM.size)
        for(i in 0..TM.size-1)
        {
            if(TM[i].toString() == " " || !Alph.contains(TM[i].toString()))
            {
                EnTM[i] = TM[i].toString()
                continue
            }
            for(k in 0.. Alph.size-1) {
                if (Alph[k] == TM[i].toString()) {
                    EnTM[i] = Alph[(k - Key+Alph.size)% (Alph.size)]
                    break
                }
            }
        }
        //вывод
        var V :String=""
        for(i in 0..EnTM.size-1)
        {

            V=V + EnTM[i]
        }
        return V
    }
    private fun isFieldEmpty():Boolean{         //Проверка на пустое поле
        binding.apply {//для обращение ко многим объектам экррана
            if(textMessage2.text.isNullOrEmpty())textMessage2.error = "Поле должно быть заполнено!"
            if(textMGT.text.isNullOrEmpty())textMGT.error = "Поле должно быть заполнено!"
            return textMessage2.text.isNullOrEmpty() || textMGT.text.isNullOrEmpty()
        }
    }
}
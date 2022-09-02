package com.example.cryptographicmethods

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.cryptographicmethods.databinding.ActivityLaboratoryWork5Binding
import com.example.cryptographicmethods.databinding.ActivityLaboratoryWork6Binding
import kotlin.math.pow

class Laboratory_work_6 : AppCompatActivity() {
    lateinit var binding: ActivityLaboratoryWork6Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaboratoryWork6Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)    //показать кнопку назат
        supportActionBar?.title = "Сеть Фейстеля"  //Имя тулбара
        val textview = binding.textViewLCG2 as TextView
        textview.movementMethod = ScrollingMovementMethod() // скролл тествью
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean { //работа кнопки назад
        finish()
        return true
    }
    fun onClickEncode(view: View)
    {
        if(!isFieldEmpty()) {
            binding.textViewLCG2.text = ""
            binding.tvFeistelResult.text = ""
            binding.textViewLCG2.append("LCG = (")
            var m = (2.0.pow(31) - 1).toInt()
            var Ran = arrayListOf<Long>()
            Ran.add(6303600)
            var Message = arrayListOf<Char>() //Сообщение
            Message.addAll(binding.texMessageMessage5.text.toList()) // переносим сообщение в лист
            binding.textViewLCG2.append(Ran[0].toString() + "," + " ")
            for (i in 0..2) {//(m - 1)
                var g = (630360016 * Ran[(Ran.size - 1)] + 0) % m
                Ran.add(g)
                binding.textViewLCG2.append(g.toString() + "," + " ")
            }
            binding.textViewLCG2.append(")" + "\n" + "\n")
            binding.textView31.visibility = View.VISIBLE
            binding.textViewLCG2.visibility = View.VISIBLE
            var B ="" // буква в бит
            for (i in 0..Message.size - 1) {
                var left ="" //блок 1
                var right ="" // бло2
                binding.textViewLCG2.append("Символ: " + Message[i]+ "\n")
                var x = Message[i].toLong() //перевод в dec	 UTF-16BE
                B = x.toString(2) //перевод в 2ю систему
                while (B.length < 16){ //подгон длины до 16 бит
                    B= "0" + B
                }
                for(j in 0..7){ //делим на 2 блока по 8 бит
                    left = left + B[j].toString()
                    right = right + B[j+8].toString()
                }
                for(M in 0..3) { // по 4 раунда
                    binding.textViewLCG2.append("Раунд " + (M+1) + "\n")
                    var Key=""
                    var Rs1  = Ran[M].toString(2)
                    for(k in 0..7){ //подгон длины до 8 бит
                        Key= Key+Rs1[k].toString()
                    }

                    //var ll = left.toLong(2)
                    var rr = F(right, Key)
                    //var ff = (left.toLong(2) xor F(right, Key).toLong(2))


                    while(left.length<8) left = "0" + left
                    while(right.length<8) right = "0" + right

                    binding.textViewLCG2.append("Левый блок = " + left + "\n" + "Правый блок = " + right + "\n" + "Ключ = " + Key + "\n" )

                    var temp = (left.toLong(2) xor F(right, Key).toLong(2)).toString(2) //операция с F
                    left = right
                    right = temp
                    binding.textViewLCG2.append("Рузультат :"+ "\n" + "F = " + rr + "\n" + "Левый блок = " + left + "\n" + "Правый блок = " + right + "\n")
                }
                binding.textViewLCG2.append("Итог: " + left + " "+ right + "\n" + "\n")
                var R = (left + right).toLong(2).toChar()
                binding.tvFeistelResult.append(R.toString())
                left=""
                right=""
            }

            binding.buttonCopy6.visibility = View.VISIBLE
            binding.tvFeistelResult.visibility = View.VISIBLE
        }
    }
    private fun F (block1:String,Key:String):String{ //функция

        var XOR = block1.toLong(2) xor Key.toLong(2)
        var FR = XOR.toString(2)
        while (FR.length < 8){ //подгон длины до 8 бит
            FR = "0" + FR
        }
        var F1=""
        var F2=""
        var F3=""
        var F4=""
        for(j in 0..1){ //делим на 2 блока по 4 бит
            F1 = F1 + FR[j].toString()
            F2 = F2 + FR[j+2].toString()
            F3 = F3 + FR[j+4].toString()
            F4 = F4 + FR[j+6].toString()
        }
        binding.textViewLCG2.append("F1=" +F1 + "; " + "F2=" +F2 + "; " +"F3=" +F3 + "; " + "F4=" +F4 + "\n")
        FR = F1 + F4 + F2+ F3
        return FR
    }
    fun onClickDecode(view: View)
    {
        if(!isFieldEmpty()) {
            binding.textViewLCG2.text = ""
            binding.tvFeistelResult.text = ""
            binding.textViewLCG2.append("LCG = (")
            var m = (2.0.pow(31) - 1).toInt()
            var Ran = arrayListOf<Long>()
            Ran.add(6303600)
            var Message = arrayListOf<Char>() //Сообщение
            Message.addAll(binding.texMessageMessage5.text.toList()) // переносим сообщение в лист
            binding.textViewLCG2.append(Ran[0].toString() + "," + " ")
            for (i in 0..2) {//(m - 1)
                var g = (630360016 * Ran[(Ran.size - 1)] + 0) % m
                Ran.add(g)
                binding.textViewLCG2.append(g.toString() + "," + " ")
            }
            binding.textViewLCG2.append(")" + "\n" + "\n")
            binding.textView31.visibility = View.VISIBLE
            binding.textViewLCG2.visibility = View.VISIBLE
            var B ="" // буква в бит
            for (i in 0..Message.size - 1) {
                binding.textViewLCG2.append("Символ: " + Message[i]+ "\n")
                var left ="" //блок 1
                var right ="" // бло2
                var x = Message[i].toLong() //перевод в dec	 UTF-16BE
                B = x.toString(2) //перевод в 2ю систему
                while (B.length < 16){ //подгон длины до 16 бит
                    B= "0" + B
                }
                for(j in 0..7){ //делим на 2 блока по 8 бит
                    left = left + B[j].toString()
                    right = right + B[j+8].toString()
                }
                var counter = 0
                for(M in 3 downTo 0) { // по 4 раунда
                    counter++
                    binding.textViewLCG2.append("Раунд " + counter + "\n")
                    var Key=""
                    var Rs1  = Ran[M].toString(2)
                    for(k in 0..7){ //подгон длины до 8 бит
                        Key= Key+Rs1[k].toString()
                    }

                   // var temp = (left.toLong(2) xor F(right, Key).toLong(2)).toString(2) // операция с F
                   // left = right
                    //right = temp
                    var rr = F(left, Key)
                    binding.textViewLCG2.append("Левый блок = " + left + "\n" + "Правый блок = " + right + "\n" + "Ключ = " + Key + "\n" )

                    var temp = (right.toLong(2) xor F(left, Key).toLong(2)).toString(2) //1я операция с F
                    right = left
                    left = temp
                    while(left.length<8) left = "0" + left
                    while(right.length<8) right = "0" + right
                    binding.textViewLCG2.append("Рузультат :"+ "\n" + "F = " + rr + "\n" + "Левый блок = " + left + "\n" + "Правый блок = " + right + "\n")

                }
                binding.textViewLCG2.append("Итог: " + left + " "+ right + "\n" + "\n")
                var R = (left + right).toLong(2).toChar()
                binding.tvFeistelResult.append(R.toString())
                left=""
                right=""
            }

            binding.buttonCopy6.visibility = View.VISIBLE
            binding.tvFeistelResult.visibility = View.VISIBLE
        }
    }

    fun onClickCopy(view: View)//Кнопка копирования
    {
        var clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        var clip = ClipData.newPlainText("label",binding.tvFeistelResult.text)
        clipboard?.setPrimaryClip(clip)
        // уведомление о копировании текста
        val text = "Текст скопирован."
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
    private fun isFieldEmpty():Boolean{         //Проверка на пустое поле
        binding.apply {//для обращение ко многим объектам экррана
            if(texMessageMessage5.text.isNullOrEmpty())texMessageMessage5.error = "Поле должно быть заполнено!"
            return texMessageMessage5.text.isNullOrEmpty()
        }
    }
}
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
import com.example.cryptographicmethods.databinding.ActivityLaboratoryWork4Binding
import com.example.cryptographicmethods.databinding.ActivityLaboratoryWork5Binding
import kotlin.math.pow

class Laboratory_work_5 : AppCompatActivity() {
    lateinit var binding: ActivityLaboratoryWork5Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaboratoryWork5Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)    //показать кнопку назат
        supportActionBar?.title = "Гаммирование"  //Имя тулбара
        val textview = binding.textViewLCG as TextView
        textview.movementMethod = ScrollingMovementMethod() // скролл тествью
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean { //работа кнопки назад
        finish()
        return true
    }
    fun onClickEncode(view: View)
    {
        if(!isFieldEmpty()) {
            binding.textViewLCG.text = ""
            binding.textViewXOR.text = ""
            binding.textViewLCG.append("LCG = (")
            var m = (2.0.pow(31) - 1).toInt()
            var Ran = arrayListOf<Long>()
            Ran.add(1)
            binding.textViewLCG.append(Ran[0].toString() + "," + " ")
            for (i in 0..10000) {//(m - 1)
                var g = (630360016 * Ran[(Ran.size - 1)] + 0) % m
                Ran.add(g)
                binding.textViewLCG.append(g.toString() + "," + " ")
            }
            binding.textViewLCG.append(")")
            binding.textView23.visibility = View.VISIBLE
            binding.textViewLCG.visibility = View.VISIBLE
            var Message = arrayListOf<Char>() //Сообщение
            Message.addAll(binding.texMessageMessage3.text.toList()) // переносим сообщение в лист
            binding.buttonCopy4.visibility = View.VISIBLE
            for (i in 0..Message.size - 1) {
                var x = Message[i].toLong() //перевод в
                var g = (x xor Ran[i]).toChar() ///////////////////////////////////////СДЕЛАТЬ ХОР ПРАВИЛЬНЫМ!!!!!!
                //   var uni= String.format("\\%04x", gg.toInt())
                binding.textViewXOR.append((g).toString())
            }
            binding.textViewXOR.visibility = View.VISIBLE
        }

    }
    fun onClickDecode(view: View)
    {
        if(!isFieldEmpty()) {
            binding.textViewLCG.text = ""
            binding.textViewXOR.text = ""
            binding.textViewLCG.append("LCG = (")
            var m = (2.0.pow(31) - 1).toInt()
            var Ran = arrayListOf<Long>()
            Ran.add(1)
            binding.textViewLCG.append(Ran[0].toString() + "," + " ")
            for (i in 0..10000) {//(m - 1)
                var g = (630360016 * Ran[(Ran.size - 1)] + 0) % m
                Ran.add(g)
                binding.textViewLCG.append(g.toString() + "," + " ")
            }
            binding.textViewLCG.append(")")
            binding.textView23.visibility = View.VISIBLE
            binding.textViewLCG.visibility = View.VISIBLE
            var Message = arrayListOf<Char>() //Сообщение
            Message.addAll(binding.texMessageMessage3.text.toList()) // переносим сообщение в лист
            binding.buttonCopy4.visibility = View.VISIBLE
            for (i in 0..Message.size - 1) {
                var x = Message[i].toLong() //перевод в dec	 UTF-16BE
                var g = (x xor Ran[i]).toChar()///////////////////////////////////////СДЕЛАТЬ ХОР ПРАВИЛЬНЫМ!!!!!!
                //   var uni= String.format("\\%04x", gg.toInt())
                binding.textViewXOR.append((g).toString())
            }
            binding.textViewXOR.visibility = View.VISIBLE
        }
    }
    fun onClickCopy(view: View)//Кнопка копирования
    {
        var clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        var clip = ClipData.newPlainText("label",binding.textViewXOR.text)
        clipboard?.setPrimaryClip(clip)
        // уведомление о копировании текста
        val text = "Текст скопирован."
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
    private fun isFieldEmpty():Boolean{         //Проверка на пустое поле
        binding.apply {//для обращение ко многим объектам экррана
            if(texMessageMessage3.text.isNullOrEmpty())texMessageMessage3.error = "Поле должно быть заполнено!"
            return texMessageMessage3.text.isNullOrEmpty()
        }
    }
}
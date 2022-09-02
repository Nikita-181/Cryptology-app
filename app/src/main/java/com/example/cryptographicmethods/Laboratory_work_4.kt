package com.example.cryptographicmethods

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.example.cryptographicmethods.databinding.ActivityLaboratoryWork4Binding

class Laboratory_work_4 : AppCompatActivity() {
    lateinit var binding: ActivityLaboratoryWork4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaboratoryWork4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)    //показать кнопку назат
        supportActionBar?.title = "Шифр табличной маршрутной перестановки"  //Имя тулбара
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean { //работа кнопки назад
        finish()
        return true
    }
    fun onClickEncode(view: View)
    {
        if(!isFieldEmpty()) {
            binding.tableLayout2.removeAllViews()
            binding.textView13R.text=""
            var Message = arrayListOf<Char>() //Сообщение
            Message.addAll(binding.texMessageMessage2.text.toList()) // переносим сообщение в лист
            for(i in Message){
                if(i==' ') Message[Message.indexOf(' ')]='_'
            }
            var n = 0
            if(Message.size <= 25) n = 5
            if(Message.size > 25 && Message.size<=36) n = 6
            if(Message.size > 36 && Message.size<=49) n = 7
            var Matrix: Array<Array<Char>> = Array(n, { Array(n, {'_'}) }) //массив матрицы
            var row = 0 //записываем змейкой по столбцам от nn
            for(i in Matrix.size-1 downTo 0){
                if(row % 2 == 0) {
                    for (j in Matrix.size - 1 downTo 0) {
                        if (Message.size == 0) break
                        Matrix[j][i] = Message[0]
                        Message.removeAt(0)
                    }
                }
                if(row % 2 != 0) {
                    for (j in  0..Matrix.size - 1) {
                        if (Message.size == 0) break
                        Matrix[j][i] = Message[0]
                        Message.removeAt(0)
                    }
                }
                row++
            }

            for(i in 0..Matrix.size-1){
                var tableRow = TableRow(this) //создаем строку
                for(j in 0..Matrix.size-1) {
                    var tv = TextView(this)//тип ячейки
                    tv.setTextColor(getResources().getColor(R.color.black))
                    tv.setBackgroundResource(R.drawable.board)                      // задаем фон в виде заранее нарисованной рамки
                    tv.text=Matrix[i][j].toString()//записываем текст в ячеку
                    tv.setGravity (Gravity.CENTER)      // центруем текст
                    tableRow.addView(tv,j)//добавляем ячейку в строку
                    binding.tableLayout2.setColumnStretchable(j,true) //растягиваем каждый столбец по свободному месту равномерно
                }
                binding.tableLayout2.addView(tableRow, i) // добавляем строку в таблицу tableLayout
            }
            binding.textView18.visibility = View.VISIBLE
            //шифрование + вывод
            for(i in 0..Matrix.size-1){
                for(j in 0..Matrix.size-1) {

                    binding.textView13R.append(Matrix[i][j].toString())
                }
            }
            binding.textView19.visibility = View.VISIBLE
            binding.textView13R.visibility = View.VISIBLE
            binding.buttonCopy2.visibility=View.VISIBLE
        }
    }
    fun onClickDecode(view: View)
    {
        if(!isFieldEmpty()) {
            binding.tableLayout2.removeAllViews()
            binding.textView13R.text=""
            var Message = arrayListOf<Char>() //Сообщение
            Message.addAll(binding.texMessageMessage2.text.toList()) // переносим сообщение в лист
            for(i in Message){
                if(i==' ') Message[Message.indexOf(' ')]='_'
            }
            var n = 0
            if(Message.size <= 25) n = 5
            if(Message.size > 25 && Message.size<=36) n = 6
            var Matrix: Array<Array<Char>> = Array(n, { Array(n, {'_'}) }) //массив матрицы
            //записываем в матрицу построчно
            for(i in 0..Matrix.size-1){
                for(j in 0..Matrix.size-1) {
                    if (Message.size == 0) break
                    Matrix[i][j] = Message[0]
                    Message.removeAt(0)
                }
            }

            for(i in 0..Matrix.size-1){
                var tableRow = TableRow(this) //создаем строку
                for(j in 0..Matrix.size-1) {
                    var tv = TextView(this)//тип ячейки
                    tv.setTextColor(getResources().getColor(R.color.black))
                    tv.setBackgroundResource(R.drawable.board)                       // задаем фон в виде заранее нарисованной рамки
                    tv.text=Matrix[i][j].toString()//записываем текст в ячеку
                    tv.setGravity (Gravity.CENTER)      // центруем текст
                    tableRow.addView(tv,j)//добавляем ячейку в строку
                    binding.tableLayout2.setColumnStretchable(j,true) //растягиваем каждый столбец по свободному месту равномерно
                }
                binding.tableLayout2.addView(tableRow, i) // добавляем строку в таблицу tableLayout
            }
            binding.textView18.visibility = View.VISIBLE
            //деифрование + вывод
            var row = 0
            for(i in Matrix.size-1 downTo 0){
                if(row % 2 == 0) {
                    for (j in Matrix.size - 1 downTo 0) {
                       // if (Message.size == 0) break
                        binding.textView13R.append(Matrix[j][i].toString())
                    }
                }
                if(row % 2 != 0) {
                    for (j in  0..Matrix.size - 1) {
                       // if (Message.size == 0) break
                        binding.textView13R.append(Matrix[j][i].toString())
                    }
                }
                row++
            }
            binding.textView19.visibility = View.VISIBLE
            binding.textView13R.visibility = View.VISIBLE
            binding.buttonCopy2.visibility=View.VISIBLE
        }
    }
    private fun isFieldEmpty():Boolean{         //Проверка на пустое поле
        binding.apply {//для обращение ко многим объектам экррана
            if(texMessageMessage2.text.isNullOrEmpty())texMessageMessage2.error = "Поле должно быть заполнено!"
            return  texMessageMessage2.text.isNullOrEmpty()
        }
    }
    fun onClickCopy(view: View)//Кнопка копирования
    {
        var clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        var clip = ClipData.newPlainText("label",binding.textView13R.text)
        clipboard?.setPrimaryClip(clip)
        // уведомление о копировании текста
        val text = "Текст скопирован."
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
}
package com.example.cryptographicmethods

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.example.cryptographicmethods.databinding.ActivityKriptoBinding

class Kripto : AppCompatActivity() {
    lateinit var binding: ActivityKriptoBinding
    val Alph: Array<Char> = arrayOf('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ','Ъ','Ы','Ь', 'Э', 'Ю', 'Я','_')
    val AlphTab :Array<Array<Int>> = Array(33, { Array(33, {0}) })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKriptoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)    //показать кнопку назат
        supportActionBar?.title = "Криптоанализ"  //Имя тулбара
        val textview = binding.textView20 as TextView
        textview.movementMethod = ScrollingMovementMethod() // скролл тествью
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean { //работа кнопки назад
        finish()
        return true
    }
    fun onClickAnalysis(view: View)
    {
        AlphTab[0] = arrayOf(2,7,8,6,7,7,7,7,4,7,7,7,8,8,3,7,6,7,8,2,6,6,7,7,5,5,0,0,0,0,6,7,9)
        AlphTab[1] = arrayOf(7,1,1,0,1,6,2,2,6,0,5,6,3,5,7,2,7,5,0,7,0,5,4,1,0,5,5,7,2,2,0,3,5)
        AlphTab[2] = arrayOf(8,0,5,0,4,8,0,3,7,1,6,7,5,6,8,4,6,6,6,6,0,3,0,1,3,0,0,8,2,0,0,4,8)
        AlphTab[3] = arrayOf(6,0,1,1,6,5,0,0,6,0,4,5,4,4,8,0,7,0,0,6,0,0,1,2,0,0,0,0,0,0,0,0,4)
        AlphTab[4] = arrayOf(8,1,6,3,4,8,1,0,7,0,4,7,1,7,8,4,6,5,2,7,1,3,3,3,4,0,0,6,4,0,4,5,7)
        AlphTab[5] = arrayOf(5,5,6,7,8,6,6,6,4,7,7,8,8,9,6,5,8,8,9,3,3,6,5,6,5,6,0,0,1,1,5,5,9)
        AlphTab[6] = arrayOf(6,0,0,0,6,7,2,1,7,0,5,0,2,7,1,0,1,2,1,3,0,0,0,0,0,0,0,0,2,0,0,0,2)
        AlphTab[7] = arrayOf(8,4,6,2,6,4,1,1,6,1,5,5,6,6,7,1,5,0,0,6,0,0,2,1,0,0,2,6,2,0,0,4,6)
        AlphTab[8] = arrayOf(6,6,7,6,6,8,5,7,7,7,7,6,8,8,5,5,7,8,8,1,5,7,7,7,6,3,0,1,0,0,6,7,9)
        AlphTab[9] = arrayOf(0,0,3,0,3,0,0,0,0,0,3,6,5,4,0,0,0,6,6,0,0,0,1,2,3,0,0,0,0,0,0,0,8)
        AlphTab[10] = arrayOf(8,1,5,1,1,6,5,2,7,1,2,7,0,5,8,0,7,6,6,7,0,0,6,0,1,0,0,0,0,0,0,0,7)
        AlphTab[11] = arrayOf(8,4,1,2,1,8,6,1,8,0,4,4,1,6,7,0,0,3,3,6,3,0,0,3,1,1,0,6,8,0,7,8,6)
        AlphTab[12] = arrayOf(7,5,7,2,2,8,0,1,7,0,4,4,7,6,8,5,1,3,1,6,1,0,0,0,0,0,0,7,3,0,0,6,8)
        AlphTab[13] = arrayOf(9,0,3,3,6,8,1,1,9,0,6,0,1,7,8,0,0,5,7,6,5,2,5,3,0,0,0,8,5,0,4,6,7)
        AlphTab[14] = arrayOf(2,8,8,8,8,6,7,7,6,8,7,8,8,7,6,7,8,8,8,3,2,5,6,7,6,5,0,0,1,5,2,5,9)
        AlphTab[15] = arrayOf(7,0,0,0,0,8,0,4,7,0,3,6,1,4,8,4,9,4,5,6,2,0,1,0,0,0,0,4,5,3,0,4,4)
        AlphTab[16] = arrayOf(9,1,6,4,4,8,6,0,8,0,5,2,6,6,8,4,2,6,6,7,3,5,4,2,4,2,0,7,4,0,1,6,7)
        AlphTab[17] = arrayOf(6,4,6,2,5,7,2,0,7,0,7,8,6,6,8,7,5,6,9,6,3,5,1,5,5,0,0,5,6,1,3,8,7)
        AlphTab[18] = arrayOf(8,2,7,1,4,8,0,0,8,0,6,4,5,6,9,3,8,8,4,6,0,0,0,4,0,2,1,7,8,0,1,5,8)
        AlphTab[19] = arrayOf(3,4,4,6,6,7,6,5,3,3,6,5,5,6,0,6,7,7,7,1,5,5,0,6,3,6,0,0,0,0,7,4,8)
        AlphTab[20] = arrayOf(6,0,0,0,0,5,0,0,6,0,0,2,2,0,6,0,4,0,3,5,4,0,0,0,0,0,0,1,0,0,0,0,2)
        AlphTab[21] = arrayOf(4,3,3,0,0,4,0,0,3,0,1,1,0,5,6,0,5,3,1,3,0,0,2,0,0,0,1,0,0,0,0,0,8)
        AlphTab[22] = arrayOf(5,0,6,0,0,6,0,0,7,0,0,0,0,0,3,0,0,0,0,4,0,0,0,0,0,0,0,5,0,0,0,0,5)
        AlphTab[23] = arrayOf(7,0,1,0,0,8,0,0,7,0,6,1,0,6,2,0,1,0,7,3,0,0,0,1,3,0,0,1,3,0,0,0,4)
        AlphTab[24] = arrayOf(5,0,0,0,0,6,0,0,7,0,3,3,0,3,4,0,3,0,3,4,0,0,0,0,0,0,0,0,4,0,0,0,5)
        AlphTab[25] = arrayOf(6,0,0,0,0,7,0,0,6,0,0,0,0,2,0,0,2,0,0,4,0,0,0,0,0,0,0,0,4,0,1,0,1)
        AlphTab[26] = arrayOf(0,0,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,2)
        AlphTab[27] = arrayOf(1,4,7,3,5,7,1,5,1,7,5,5,6,2,1,5,5,5,6,0,0,7,0,5,4,1,0,1,0,0,0,1,8)
        AlphTab[28] = arrayOf(0,1,0,0,0,3,0,7,1,0,6,0,4,7,1,0,0,6,4,0,0,0,0,1,6,1,0,0,0,0,6,2,8)
        AlphTab[29] = arrayOf(0,0,4,0,0,1,0,0,0,2,6,5,2,1,0,2,0,1,7,0,4,3,0,0,0,0,0,0,0,0,0,0,1)
        AlphTab[30] = arrayOf(0,5,0,0,2,0,1,2,0,4,1,0,0,0,0,0,0,3,7,0,0,0,0,6,1,7,0,0,1,0,3,0,7)
        AlphTab[31] = arrayOf(0,1,5,2,5,6,2,5,0,2,2,3,6,5,0,1,4,4,7,0,0,4,4,3,0,4,0,0,0,0,6,4,9)
        AlphTab[32] = arrayOf(7,8,9,7,8,7,5,8,8,3,8,6,8,9,9,9,8,9,8,7,7,6,7,8,5,1,1,2,1,8,2,6,0)
        binding.tableLayout3.removeAllViews()
        binding.textView20.text=""
        var Message = arrayListOf<Char>() //Сообщение
        Message.addAll(binding.editTextTextPersonName.text.toList()) // переносим сообщение в лист
        for(i in Message){
            if(i==' ') Message[Message.indexOf(' ')]='_'
        }
        var n = 0
        if(Message.size <= 16) n = 4
        if(Message.size > 16 && Message.size <= 25) n = 5
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
                binding.tableLayout3.setColumnStretchable(j,true) //растягиваем каждый столбец по свободному месту равномерно
            }
            binding.tableLayout3.addView(tableRow, i) // добавляем строку в таблицу tableLayout
        }
        var st_num = ""
        var sum = 0
        for(j1 in 0..n-1){
            for(j2 in 0..n-1){
                if(j1==j2) continue
                else{
                    var st_num = ""
                    var sum = 0
                    binding.textView20.append("F("+(j1+1)+"->"+(j2+1)+") = ")
                    for(i in 0..n-1){
                        if(i==n-1){
                            binding.textView20.append("F("+Matrix[i][j1]+Matrix[i][j2]+") = ")
                            st_num = st_num + (AlphTab[Alph.indexOf(Matrix[i][j1])] [Alph.indexOf(Matrix[i][j2])]).toString() + " = "
                            sum=sum+(AlphTab[Alph.indexOf(Matrix[i][j1])] [Alph.indexOf(Matrix[i][j2])])
                        }
                        else {
                            binding.textView20.append("F("+Matrix[i][j1]+Matrix[i][j2]+") + ")
                            st_num = st_num + (AlphTab[Alph.indexOf(Matrix[i][j1])] [Alph.indexOf(Matrix[i][j2])]).toString() + " + "
                            sum=sum+(AlphTab[Alph.indexOf(Matrix[i][j1])] [Alph.indexOf(Matrix[i][j2])])
                        }
                    }
                    binding.textView20.append(st_num + sum.toString())
                    binding.textView20.append("\n")
                }
            }

        }
    }
    fun onClickCopy(view: View)//Кнопка копирования
    {
        var clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        var clip = ClipData.newPlainText("label",binding.textView20.text)
        clipboard?.setPrimaryClip(clip)
        // уведомление о копировании текста
        val text = "Текст скопирован."
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
}
package com.example.cryptographicmethods


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptographicmethods.databinding.ActivityLaboratoryWork1Binding


class Laboratory_work_1 : AppCompatActivity() {
    lateinit var binding: ActivityLaboratoryWork1Binding
    var Alph = arrayListOf<String>()
    var ArrAlph = arrayListOf(
        arrayListOf("А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ","Ъ","Ы","Ь", "Э", "Ю", "Я"),
        arrayListOf("А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ","Ъ","Ы","Ь", "Э", "Ю", "Я","0","1", "2", "3", "4", "5", "6", "7", "8", "9"),
        arrayListOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"),
        arrayListOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z","0","1", "2", "3", "4", "5", "6", "7", "8", "9")
    )

    //val Keys: Array<String> = arrayOf("-40","-39","-38","-37","-36","-35","-34","-33","-32","-31","-30")
    //var tMB: Boolean? = null
    //var tKB:Boolean? = null

    //val dataList = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaboratoryWork1Binding.inflate(layoutInflater)
        //setContentView(R.layout.activity_laboratory_work1)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)    //показать кнопку назат
        supportActionBar?.title = "Шифр Цезаря"  //Имя тулбара


        ////////////////////////////////////////////// слушатель изменения ввода
        binding.textKey.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                var gg = s.toString().toIntOrNull()
                if(gg != null){
                    CorKey_and_change_alph(s.toString().toInt())
                    //binding.textView6.text ="Преобразованный ключ:" + " " + s //вывод .setText("Text in EditText : "+s)
                }

            }
        })
        //////////////////////////////////////////////////////////////////////////// слушатель выбора Spinner
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long
            ) {
                Alph = ArrAlph[selectedItemPosition]
                Alph_Write()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //работа кнопки назад
        finish()
        return true
    }
    fun Alph_Write() //Вывод алфавита
    {
        binding.AlphCustomTL?.removeAllViews()
        binding.AlphOrigTL?.removeAllViews()
        ////////////////////////////////////////// Вывод оригинального алфавита AlphOrigTL

        var tableRow = TableRow(this) //создаем строку
        for(j in 0..5) {
            var tv = TextView(this)//тип ячейки
            tv.setTextColor(getResources().getColor(R.color.black))
            tv.setBackgroundResource(R.drawable.board)              // задаем фон в виде заранее нарисованной рамки
            if(j!= 5) {
                tv.text = Alph[j].toString()//записываем текст в ячеку
            }
            else {tv.text ="..."}
            tv.setGravity (Gravity.CENTER)      // центруем текст
            tableRow.addView(tv,j)//добавляем ячейку в строку
            binding.AlphOrigTL?.setColumnStretchable(j,true) //растягиваем каждый столбец по свободному месту равномерно
        }
        binding.AlphOrigTL?.addView(tableRow) // добавляем строку в таблицу tableLayout

        ////////////////////////////////////////// Вывод оригинального алфавита AlphCustomTL
        var tableRow2 = TableRow(this) //создаем строку
        for(j in 0..5) {
            var tv = TextView(this)//тип ячейки
            tv.setTextColor(getResources().getColor(R.color.black))
            tv.setBackgroundResource(R.drawable.board)              // задаем фон в виде заранее нарисованной рамки
            if(j!= 5) {
                tv.text = Alph[j].toString()//записываем текст в ячеку
            }
            else {tv.text ="..."}
            tv.setGravity (Gravity.CENTER)      // центруем текст
            tableRow2.addView(tv,j)//добавляем ячейку в строку
            binding.AlphCustomTL?.setColumnStretchable(j,true) //растягиваем каждый столбец по свободному месту равномерно
        }
        binding.AlphCustomTL?.addView(tableRow2) // добавляем строку в таблицу tableLayout

        binding.textKey. setText("0") //зануляем ключ, ибо новый алфавит
    }

    fun onClickEncode(view: View)
    {
        if(!isFieldEmpty()) {
            var TM = binding.textMessage.text.toString().toUpperCase().toList() // переносим сообщение в лист, делаем все символы заглавными(и нам так удобнее и дружилюбнее к ползователю)
            var EnTM = arrayOfNulls<String>(TM.size) //зашифрованное сообщение
            var CorKeyText = binding.textView6.text.toString().split(' ')
            var CorKey = CorKeyText[CorKeyText.size-1].toInt()
            //var CorKey =  (Alph.size % ((binding.textKey.text.toList()[0]).toString()+(binding.textKey.text.toList()[1]).toString()+(binding.textKey.text.toList()[2]).toString()).toInt())  //считаем настоящий ключ
            // var CorKey =  (Alph.size -((binding.textKey.text.toList()[1]).toString()+(binding.textKey.text.toList()[2]).toString()).toInt()) //считаем настоящий ключ
            //binding.textView6.text ="Преобразованный ключ:" + " " + CorKey.toString() //вывод
            for(i in 0..TM.size-1) //шифруем
            {
                if(TM[i].toString() == " " || !Alph.contains(TM[i].toString()) ) //если пробел  или не содерж символ
                {
                    EnTM[i] = TM[i].toString()
                    continue
                }
                for(k in 0.. Alph.size-1) {
                    if (Alph[k] == TM[i].toString())
                    {
                        EnTM[i] = Alph[MOD( (k+CorKey),Alph.size )]
                        break
                    }
                }
            }
            var V :String=""
            for(i in 0..EnTM.size-1)
            {
                V=V + EnTM[i]
            }
            binding.textView3.text = V
            binding.textView3.visibility = View.VISIBLE
            binding.button4!!.visibility = View.VISIBLE

        }
    }
    fun onClickDecode(view: View)
    {
        if(!isFieldEmpty()) {
            var TM = binding.textMessage.text.toString().toUpperCase().toList() // переносим сообщение в лист
            var EnTM = arrayOfNulls<String>(TM.size)
            var CorKeyText = binding.textView6.text.toString().split(' ')
            var CorKey = CorKeyText[CorKeyText.size-1].toInt()
            //var CorKey =  (Alph.size -((binding.textKey.text.toList()[1]).toString()+(binding.textKey.text.toList()[2]).toString()).toInt()) //считаем настоящий ключ
            //binding.textView6.text ="Преобразованный ключ:" + " " + CorKey.toString() //вывод
            for(i in 0..TM.size-1)
            {
                if(TM[i].toString() == " " || !Alph.contains(TM[i].toString()))
                {
                    EnTM[i] = TM[i].toString()
                    continue
                }
                for(k in 0.. Alph.size-1) {
                    if (Alph[k] == TM[i].toString())
                    {
                        EnTM[i] = Alph[MOD( (k - CorKey+Alph.size),Alph.size )]
                        break
                    }
                }
            }
            var V :String=""
            for(i in 0..EnTM.size-1)
            {

                V=V + EnTM[i]
            }
            binding.textView3.text = V
            binding.textView3.visibility = View.VISIBLE

        }
    }
    fun onClickCopy(view: View)//Кнопка копирования
    {
        var clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        var clip = ClipData.newPlainText("label",binding.textView3.text)
        clipboard?.setPrimaryClip(clip)
        // уведомление о копировании текста
        val text = "Текст скопирован."
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
    fun onClickLeft(view: View)
    {
        var key = binding.textKey.text.toString().toInt() - 1
        binding.textKey. setText(key.toString())
        //CorKey_and_change_alph(key) //вызывать метод ненадо, так как это проиходит в слушателе изменения текста
    }
    fun onClickRight(view: View)
    {
        var key = binding.textKey.text.toString().toInt() + 1
        binding.textKey. setText(key.toString())
        //CorKey_and_change_alph(key) //вызывать метод ненадо, так как это проиходит в слушателе изменения текста
    }
    fun CorKey_and_change_alph(key: Int)
    {
        var CorKey = key
        binding.AlphCustomTL?.removeAllViews()
        if(key == 0 || key == Alph.size){
            CorKey = 0
        }
        else CorKey = MOD(key,Alph.size)

        var tableRow2 = TableRow(this) //создаем строку
        for(j in 0..5) {
            var tv = TextView(this)//тип ячейки
            tv.setTextColor(getResources().getColor(R.color.black))
            tv.setBackgroundResource(R.drawable.board)              // задаем фон в виде заранее нарисованной рамки
            if(j!= 5) {
                tv.text = Alph[MOD((j+CorKey),(Alph.size))]//записываем текст в ячеку
            }
            else {tv.text ="..."}
            tv.setGravity (Gravity.CENTER)      // центруем текст
            tableRow2.addView(tv,j)//добавляем ячейку в строку
            binding.AlphCustomTL?.setColumnStretchable(j,true) //растягиваем каждый столбец по свободному месту равномерно
        }
        binding.AlphCustomTL?.addView(tableRow2) // добавляем строку в таблицу tableLayout

        binding.textView6.text ="Преобразованный ключ:" + " " + CorKey.toString() //вывод
    }
    fun MOD (X:Int, Y:Int):Int
    {
        var x = X
        var y = Y
        if(x < y){
            while (x <= 0)
            {
                x += y
            }
        }
        if(x > y){
            while (x > y)
            {
                x -= y
            }
        }
        if(x==y) x = 0
        return x
    }
    private fun isFieldEmpty():Boolean{         //Проверка на пустое поле
        binding.apply {//для обращение ко многим объектам экррана
            if(textMessage.text.isNullOrEmpty())textMessage.error = "Поле должно быть заполнено!"
            if(textKey.text.isNullOrEmpty())textKey.error = "Поле должно быть заполнено!"
            return textMessage.text.isNullOrEmpty() || textKey.text.isNullOrEmpty()
        }
    }

   // private fun isIncorrectInput():Boolean{         //Проверка корректности ввода
     //   binding.apply {//для обращение ко многим объектам экррана

       //     if(Keys.contains(textKey.text.toString()) != true ){
         //       textKey.error = "Число вне диапазона!"
           //      tKB = true
            //} else  tKB = false

       // }
      // return tKB == true
   // }
}
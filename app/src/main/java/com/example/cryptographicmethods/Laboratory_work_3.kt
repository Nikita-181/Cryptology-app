package com.example.cryptographicmethods

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.example.cryptographicmethods.databinding.ActivityLaboratoryWork1Binding
import com.example.cryptographicmethods.databinding.ActivityLaboratoryWork2Binding
import com.example.cryptographicmethods.databinding.ActivityLaboratoryWork3Binding

class Laboratory_work_3 : AppCompatActivity() {
    lateinit var binding: ActivityLaboratoryWork3Binding
    //var Alph = arrayListOf('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ','Ъ','Ы','Ь', 'Э', 'Ю', 'Я')
    var Alph = arrayListOf<Char>()
    var ArrAlph = arrayListOf(
        arrayListOf('А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ','Ъ','Ы','Ь', 'Э', 'Ю', 'Я'),
        arrayListOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z')
    )
    var CAlph = arrayListOf<Char>() //алфавит без букв ключевого слова
    var Key_word = arrayListOf<Char>() //Ключевое слово
    var Message = arrayListOf<Char>() //Сообщение
    var CMessage = arrayListOf<Char>() //Сообщение без пробелов и иных символов
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaboratoryWork3Binding.inflate(layoutInflater)
        //setContentView(R.layout.activity_laboratory_work1)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)    //показать кнопку назат
        supportActionBar?.title = "Шифр Плейфера"  //Имя тулбара

        //////////////////////////////////////////////////////////////////////////// слушатель выбора Spinner
        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long
            ) {
                Alph = ArrAlph[selectedItemPosition]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean { //работа кнопки назад
        finish()
        return true
    }

    fun onClickEncode(view: View)
    {
        if(!isFieldEmpty()) {
            binding.tableLayout.removeAllViews()
            binding.textViewMatrix.text=""
            binding.textViewResult.text=""
            Key_word.clear()
            CAlph.clear()
            Message.clear()
            CMessage.clear()
            CAlph.addAll(Alph)
            Key_word.addAll(binding.texMessageKeyWord.text.toList()) // переносим ключевое слово в лист
            Message.addAll(binding.texMessageMessage.text.toList()) // переносим сообщение в лист
            for(i in Message){//избавляемся от лишних символов
                if(Alph.contains(i.uppercaseChar()))
                CMessage.add(i.uppercaseChar())

            }
            var Separator_Character:Char ='Ъ' //Символ разделитель
            //var Matrix = arrayListOf<Char>()
            var Matrix = arrayListOf<Array<Char>>() // матрица кодировки(таблица)
            if( binding.spinner2.selectedItemPosition == 0) { //Если выбран русский алфавит, то создаем матрицу 4 х 8
                Matrix.addAll(Array(4, { Array(8, { '0' }) }))
                Separator_Character = 'Ъ'
               // var Matrix: Array<Array<Char>> = Array(4, { Array(8, { '0' }) }) //массив матрицы
            }
            if( binding.spinner2.selectedItemPosition == 1) {//Если выбран английский алфавит, то создаем матрицу 5 х 5
                Matrix.addAll(Array(5, { Array(5, { '0' }) }))
                Separator_Character = 'Q'
            }

            var CKW = arrayListOf<Char>() // без повторяющихся букв
            for(i in 0..Key_word.size-1)
            {
                if(Alph.contains(Key_word[i].uppercaseChar()) && !CKW.contains((Key_word[i].uppercaseChar())) ){
                    CKW.add(Key_word[i].uppercaseChar())
                    CAlph.remove(Key_word[i].uppercaseChar())
                }
            }
            // Добавление ключевого слова без повторяющихся символов в матрицу
            for(i in 0..Matrix.size - 1){
                for(j in 0..Matrix[0].size - 1) {
                    if(CKW.size == 0) break
                    else {
                        Matrix[i][j] = CKW[0]
                        CKW.removeAt(0)
                    }
                }
            }
            // заполнение оставшихся элементов матрицы буквами алфавита
            for(i in 0..Matrix.size - 1){

                var tableRow = TableRow(this) //создаем строку

                for(j in 0..Matrix[0].size - 1) {
                    if(Matrix[i][j]=='0'){
                        Matrix[i][j]= CAlph[0]
                        CAlph.removeAt(0)
                //        binding.textViewMatrix.append(Matrix[i][j].toString() + " ")


                      var tv = TextView(this) //тип ячейки
                        tv.setTextColor(getResources().getColor(R.color.black))
                        tv.setBackgroundResource(R.drawable.board)              // задаем фон в виде заранее нарисованной рамки
                        tv.text=Matrix[i][j].toString()//записываем текст в ячеку
                        tv.setGravity (Gravity.CENTER)
                        tableRow.addView(tv,j)//добавляем ячейку в строку
                        binding.tableLayout?.setColumnStretchable(j,true) //растягиваем каждый столбец по свободному месту равномерно
                    }
                    else {
             //           binding.textViewMatrix.append(Matrix[i][j].toString() + " ")
                        var tv = TextView(this)//тип ячейки
                        tv.setTextColor(getResources().getColor(R.color.black))
                        tv.setBackgroundResource(R.drawable.board)              // задаем фон в виде заранее нарисованной рамки
                        tv.text=Matrix[i][j].toString()//записываем текст в ячеку
                        tv.setGravity (Gravity.CENTER)
                        tableRow.addView(tv,j)//добавляем ячейку в строку
                        binding.tableLayout?.setColumnStretchable(j,true) //растягиваем каждый столбец по свободному месту равномерно
                    }
                }
               // binding.textViewMatrix.append("\n")
                binding.tableLayout.addView(tableRow, i) // добавляем строку в таблицу tableLayout
            }
            binding.textView17.visibility = View.VISIBLE
            binding.textViewMatrix.visibility = View.VISIBLE
            // проверяем на совпадение двух символов биграммы
            for(i in 0..CMessage.size-1 step 2){
                if(i+1<CMessage.size && CMessage[i]==CMessage[i+1]){
                    //CMessage[i]= CMessage[i]  //заодно сделаем все буквы заглавными
                    //CMessage[i+1]= CMessage[i+1]
                    CMessage.add(i+1,Separator_Character)
                }
            }
            if(CMessage.size % 2 != 0) CMessage.add(Separator_Character) // проверяем сообщение на биграммность

            binding.textViewMatrix.append("Биграммы сообщения: ")
            for(i in 0..CMessage.size-1 step 2){
                if(i==CMessage.size-2) binding.textViewMatrix.append(CMessage[i].toString() + CMessage[i+1].toString()+"\n")
                else binding.textViewMatrix.append(CMessage[i].toString() + CMessage[i+1].toString()+"|")
            }

            var b1 = "0"
            var b2 = "0"
            for(k in 0..CMessage.size - 1 step 2) { //шифруем
                for (i in 0..Matrix.size - 1) { //построчно
                    if (Matrix[i].contains(CMessage[k])) {
                        b1 = i.toString() + (Matrix[i].indexOf(CMessage[k])).toString()
                    }
                    if (Matrix[i].contains(CMessage[k+1])) {
                        b2 = i.toString() + (Matrix[i].indexOf(CMessage[k+1])).toString()
                    }
                    if(b1[0] == b2[0] && b1!="0" && b2!="0"){ //если в одной строке
                        CMessage[k]= Matrix[b1[0].toString().toInt()][(b1[1].toString().toInt()+1)%Matrix[0].size]
                        CMessage[k+1]= Matrix[b2[0].toString().toInt()][(b2[1].toString().toInt()+1)%Matrix[0].size]
                        b1 = "0"
                        b2 = "0"
                        break
                    }
                    if(b1!="0" && b2!="0" && b1[1]==b2[1] ){ // если в одном столбце
                        CMessage[k]= Matrix[(b1[0].toString().toInt()+1)%Matrix.size][b1[1].toString().toInt()]
                        CMessage[k+1]= Matrix[(b2[0].toString().toInt()+1)%Matrix.size][b2[1].toString().toInt()]
                        b1 = "0"
                        b2 = "0"
                        break
                    }
                    if(b1!="0" && b2!="0" && b1[0] != b2[0] && b1[1]!=b2[1]){ // если ни в одном столбце и не в одной строке(по прямоугольнику)
                        CMessage[k]= Matrix[b1[0].toString().toInt()][b2[1].toString().toInt()]
                        CMessage[k+1]= Matrix[b2[0].toString().toInt()][b1[1].toString().toInt()]
                        b1 = "0"
                        b2 = "0"
                        break
                    }
                }
            }
            binding.textViewMatrix.append("Результат:")
            binding.textViewResult.visibility = View.VISIBLE
            for(i in 0..Message.size-1){
                if(!Alph.contains(Message[i].uppercaseChar()))
                CMessage.add(i,Message[i])
            }
            for(i in CMessage){
                binding.textViewResult.append(i.toString())
            }
            binding.buttonCopy.visibility=View.VISIBLE

        }
    }
    fun onClickDecode(view: View)
    {
        if(!isFieldEmpty()) {
            binding.tableLayout.removeAllViews()
            binding.textViewMatrix.text=""
            binding.textViewResult.text=""
            Key_word.clear()
            CAlph.clear()
            Message.clear()
            CMessage.clear()
            CAlph.addAll(Alph)
            Key_word.addAll(binding.texMessageKeyWord.text.toList()) // переносим ключевое слово в лист
            Message.addAll(binding.texMessageMessage.text.toList()) // переносим сообщение в лист
            for(i in Message){//избавляемся от лишних символов
                if(Alph.contains(i.uppercaseChar()))
                    CMessage.add(i.uppercaseChar())

            }
            var Matrix = arrayListOf<Array<Char>>() // матрица кодировки(таблица)
            var Separator_Character:Char ='Ъ' //Символ разделитель
            if( binding.spinner2.selectedItemPosition == 0) { //Если выбран русский алфавит, то создаем матрицу 4 х 8
                Matrix.addAll(Array(4, { Array(8, { '0' }) }))
                // var Matrix: Array<Array<Char>> = Array(4, { Array(8, { '0' }) }) //массив матрицы
                Separator_Character ='Ъ'
            }
            if( binding.spinner2.selectedItemPosition == 1) {//Если выбран английский алфавит, то создаем матрицу 5 х 5
                Matrix.addAll(Array(5, { Array(5, { '0' }) }))
                Separator_Character ='Q'
            }

            var CKW = arrayListOf<Char>() // без повторяющихся букв
            for(i in 0..Key_word.size-1)
            {
                if(Alph.contains(Key_word[i].uppercaseChar()) && !CKW.contains((Key_word[i].uppercaseChar())) ){
                    CKW.add(Key_word[i].uppercaseChar())
                    CAlph.remove(Key_word[i].uppercaseChar())
                }
            }
            // Добавление ключевого слова без повторяющихся символов в матрицу
            for(i in 0..Matrix.size - 1){
                for(j in 0..Matrix[0].size - 1) {
                    if(CKW.size == 0) break
                    else {
                        Matrix[i][j] = CKW[0]
                        CKW.removeAt(0)
                    }
                }
            }
            // заполнение оставшихся элементов матрицы буквами алфавита
            for(i in 0..Matrix.size - 1){
                var tableRow = TableRow(this) //создаем строку
                for(j in 0..Matrix[0].size - 1) {
                    if(Matrix[i][j]=='0'){
                        Matrix[i][j]= CAlph[0]
                        CAlph.removeAt(0)
                        var tv = TextView(this) //тип ячейки
                        tv.setTextColor(getResources().getColor(R.color.black))
                        tv.setBackgroundResource(R.drawable.board)              // задаем фон в виде заранее нарисованной рамки
                        tv.text=Matrix[i][j].toString()//записываем текст в ячеку
                        tv.setGravity (Gravity.CENTER)
                        tableRow.addView(tv,j)//добавляем ячейку в строку
                        binding.tableLayout?.setColumnStretchable(j,true) //растягиваем каждый столбец по свободному месту равномерно
                    }
                    else {
                        var tv = TextView(this)//тип ячейки
                        tv.setTextColor(getResources().getColor(R.color.black))
                        tv.setBackgroundResource(R.drawable.board)              // задаем фон в виде заранее нарисованной рамки
                        tv.text=Matrix[i][j].toString()//записываем текст в ячеку
                        tv.setGravity (Gravity.CENTER)
                        tableRow.addView(tv,j)//добавляем ячейку в строку
                        binding.tableLayout?.setColumnStretchable(j,true) //растягиваем каждый столбец по свободному месту равномерно
                    }
                }
                binding.tableLayout.addView(tableRow, i) // добавляем строку в таблицу tableLayout
            }
            binding.textView17.visibility = View.VISIBLE
            binding.textViewMatrix.visibility = View.VISIBLE


            binding.textViewMatrix.append("Биграммы сообщения: ")
            for(i in 0..CMessage.size-1 step 2){
                if(i==CMessage.size-2) {

                    binding.textViewMatrix.append(CMessage[i].toString() + CMessage[i+1].toString()+"\n")
                }
               // if(i==CMessage.size-1){
               //     binding.textViewMatrix.append(CMessage[i].toString() )
              //      break
               // }
                else binding.textViewMatrix.append(CMessage[i].toString() + CMessage[i+1].toString()+"|")
            }

            if(CMessage.size % 2 != 0){
                CMessage.add(Separator_Character)
               // binding.textViewMatrix.append(Separator_Character.toString() + "\n")
            } // проверяем сообщение на биграммность
            var b1 = "0"
            var b2 = "0"
            for(k in 0..CMessage.size - 1 step 2) { //дешифруем
                for (i in 0..Matrix[0].size - 1) { //построчно
                    if (Matrix[i].contains(CMessage[k])) {
                        b1 = i.toString() + (Matrix[i].indexOf(CMessage[k])).toString()
                    }
                    if (Matrix[i].contains(CMessage[k+1])) {
                        b2 = i.toString() + (Matrix[i].indexOf(CMessage[k+1])).toString()
                    }
                    if(b1[0] == b2[0] && b1!="0" && b2!="0"){ //если в одной строке
                        CMessage[k]= Matrix[b1[0].toString().toInt()][(b1[1].toString().toInt()-1+Matrix[0].size)%Matrix[0].size]
                        CMessage[k+1]= Matrix[b2[0].toString().toInt()][(b2[1].toString().toInt()-1+Matrix[0].size)%Matrix[0].size]
                        b1 = "0"
                        b2 = "0"
                        break
                    }
                    if(b1!="0" && b2!="0" && b1[1]==b2[1] ){ // если в одном столбце
                        CMessage[k]= Matrix[(b1[0].toString().toInt()-1+Matrix.size)%Matrix.size][b1[1].toString().toInt()]
                        CMessage[k+1]= Matrix[(b2[0].toString().toInt()-1+Matrix.size)%Matrix.size][b2[1].toString().toInt()]
                        b1 = "0"
                        b2 = "0"
                        break
                    }
                    if(b1!="0" && b2!="0" && b1[0] != b2[0] && b1[1]!=b2[1]){ // если ни в одном столбце и не в одной строке(по прямоугольнику)
                        CMessage[k]= Matrix[b1[0].toString().toInt()][b2[1].toString().toInt()]
                        CMessage[k+1]= Matrix[b2[0].toString().toInt()][b1[1].toString().toInt()]
                        b1 = "0"
                        b2 = "0"
                        break
                    }
                }
            }
            binding.textViewMatrix.append("Результат:")
            binding.textViewResult.visibility = View.VISIBLE

            for(i in 0..Message.size-1){ //добавляем убранные ранее "Лишние" символы
                if(!Alph.contains(Message[i].uppercaseChar()))
                    CMessage.add(i,Message[i])
            }
            for(i in 0..CMessage.size-1){
                if(CMessage[i]==Separator_Character && i <= CMessage.size-2 && CMessage[i-1]==CMessage[i+1]) continue//убираем пустышки
                else binding.textViewResult.append(CMessage[i].toString())
            }
            binding.buttonCopy.visibility=View.VISIBLE
        }
    }
    private fun isFieldEmpty():Boolean{         //Проверка на пустое поле
        binding.apply {//для обращение ко многим объектам экррана
            if(texMessageKeyWord.text.isNullOrEmpty())texMessageKeyWord.error = "Поле должно быть заполнено!"
            if(texMessageMessage.text.isNullOrEmpty())texMessageMessage.error = "Поле должно быть заполнено!"
            return texMessageKeyWord.text.isNullOrEmpty() || texMessageMessage.text.isNullOrEmpty()
        }
    }
    fun onClickCopy(view: View)//Кнопка копирования
    {
        var clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        var clip = ClipData.newPlainText("label",binding.textViewResult.text)
        clipboard?.setPrimaryClip(clip)
        // уведомление о копировании текста
        val text = "Текст скопирован."
        val duration = Toast.LENGTH_SHORT

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
}
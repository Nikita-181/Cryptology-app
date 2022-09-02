package com.example.cryptographicmethods

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptographicmethods.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val fragList = listOf(
        Ciphers_MenuBlankFragment.newInstance(),
        Cryptanalysis_MenuBlankFragment.newInstance(),
        Schemes_MenuBlankFragment.newInstance()
    )
    private val fragListTitles = listOf(
        "Шифры",
        "Криптоанализ",
        "Схемы разделения секрета"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.tableLayoutMain.setColumnStretchable(0,true)

        val adapter = VpAdapter(this,fragList)
        binding.ViewPager2Menu.adapter = adapter
        TabLayoutMediator(binding.TBCategories,binding.ViewPager2Menu){
            tab, pos -> tab.text = fragListTitles[pos]
        }.attach()

    }
    fun onClickGoLAB1(view: View) //слушатель нажатий
    {
        val intent = Intent(this,Laboratory_work_1::class.java) //сообщение системе о запуске активити
        startActivity(intent)   //запуск активити
    }
    fun onClickGoLAB2(view: View) //слушатель нажатий
    {
        val intent = Intent(this,Laboratory_work_2::class.java) //сообщение системе о запуске активити
        startActivity(intent)   //запуск активити
    }
    fun onClickGoLAB3(view: View) //слушатель нажатий
    {
        val intent = Intent(this,Laboratory_work_3::class.java) //сообщение системе о запуске активити
        startActivity(intent)   //запуск активити
    }
    fun onClickGoLAB4(view: View) //слушатель нажатий
    {
        val intent = Intent(this,Laboratory_work_4::class.java) //сообщение системе о запуске активити
        startActivity(intent)   //запуск активити
    }
    fun onClickGoLAB5(view: View) //слушатель нажатий
    {
        val intent = Intent(this,Laboratory_work_5::class.java) //сообщение системе о запуске активити
        startActivity(intent)   //запуск активити
    }
    fun onClickGoLAB6(view: View) //слушатель нажатий
    {
        val intent = Intent(this,Laboratory_work_6::class.java) //сообщение системе о запуске активити
        startActivity(intent)   //запуск активити
    }
    fun onClickKripto(view: View) //слушатель нажатий
    {
        val intent = Intent(this,Kripto::class.java) //сообщение системе о запуске активити
        startActivity(intent)   //запуск активити
    }


    fun onClickGoChina(view: View) //слушатель нажатий
    {
        val intent = Intent(this,China::class.java) //сообщение системе о запуске активити
        startActivity(intent)   //запуск активити
    }
    fun onClickGoNxN(view: View) //слушатель нажатий
    {
        val intent = Intent(this,n_x_n::class.java) //сообщение системе о запуске активити
        startActivity(intent)   //запуск активити
    }

}
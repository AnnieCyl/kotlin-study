package com.example.kotlinstudy.practice

class Util {
    fun doAction1(){
        println("do action1")
    }

    companion object{

        @JvmStatic
        fun doAction2(){
            println("do action2")
        }
    }
}
package com.example.kotlinstudy.practice

import kotlin.math.max

fun main() {
//    println("hello kotlin")
//    val a = 10
//    println("a = $a")
//
//    val num1 = 7
//    val num2 = 9
//    val numLarger = largerNumber(num1, num2)
//    val numLarger2 = largerNumber2(num1, num2)
//    println("larger number is $numLarger")
//    println("larger number is $numLarger2")
//
//    val num = 10L;
//    checkNumber(num)
//
//    println("0..10: ")
//    for (i in 0..10) {
//        println(i)
//    }
//
//    println("0 until 10: ")
//    val range = 0 until 10
//    for (i in range) {
//        println(i)
//    }
//
//    println("0 until 10 step 2: ")
//    for (i in 0 until 10 step 2) {
//        println(i)
//    }
//
//    println("i in 10 downTo 1: ")
//    for (i in 10 downTo 1) {
//        println(i)
//    }
//
//    println("i in 10 downTo 1 step 2: ")
//    for (i in 10 downTo 1 step 2) {
//        println(i)
//    }
//
//    val p = Person("Jack", 19)
//    p.eat()
//
//    val student = Student("a123", 5, "Jack", 19)
//    val student1 = Student()
//    val student2 = Student("Jack", 19)
//    val student3 =
//        Student("a123", 5, "Jack", 19)
//
//    doStudy(student3)
//
//    val cellPhone1 = CellPhone("Samsung", 1299.99)
//    val cellPhone2 = CellPhone("Samsung", 1299.99)
//    println(cellPhone1)
//    println("cellphone1 equals cellphone2: " + (cellPhone1 == cellPhone2))
//
//    Singleton.singletonTest()

//    println("listOf: ")
//    val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
//    for (fruit in list) {
//        println(fruit)
//    }
//    val newList1 = list.map { it.toUpperCase() }
//    val newList2 = list.filter { it.length <= 5 }
//        .map { it.toUpperCase() }
//    val anyResult = list.any{ it.length <= 5 }
//    val allResult = list.all { it.length <= 5 }
//    println("Any result is $anyResult, all result is $allResult")
//    for (fruit in newList1) {
//        println(fruit)
//    }

//    println("mutableListOf: ")
//    val list1 = mutableListOf("Apple", "Banana", "Orange", "Pear", "Grape")
//    list1.add("watermelon")
//    for (fruit in list1) {
//        println(fruit)
//    }
//
//    println("setOf: ")
//    val set = setOf("Apple", "Banana", "Orange", "Pear", "Grape")
//
//    for (fruit in set) {
//        println(fruit)
//    }
//
//    println("mutableSetOf: ")
//    val set1 = mutableSetOf("Apple", "Banana", "Orange", "Pear", "Grape")
//    set1.add("watermelon")
//    for (fruit in set1) {
//        println(fruit)
//    }
//
////    val map = HashMap<String, Int>()
////    map["Apple"] = 1
////    map["Banana"] = 2
////    map["Orange"] = 3
////    map["Pear"] = 4
////    map["Grape"] = 5
//    println("mapOf: ")
//    val map = mapOf("Apple" to 1, "Banana" to 2, "Orange" to 3, "Pear" to 4, "Grape" to 5)
//    for ((fruit, number) in map) {
//        println("fruit is $fruit, number is $number")
//    }
//
//    println("mutableMapOf: ")
//    val map2 = mutableMapOf("Apple" to 1, "Banana" to 2, "Orange" to 3, "Pear" to 4, "Grape" to 5)
//    map2["Watermelon"] = 6
//    for ((fruit, number) in map2) {
//        println("fruit is $fruit, number is $number")
//    }
//    Thread{
//        println("Thread is running")
//    }.start()
    printParams(123)
    printParams2(str = "hello")
}

fun printParams(num: Int, str: String = "hello") {
    println("num is $num, str is $str")
}

fun printParams2(num: Int = 100, str: String) {
    println("num is $num, str is $str")
}

fun getTextLength(text: String?) = text?.length ?: 0

fun largerNumber(num1: Int, num2: Int) = max(num1, num2)
fun largerNumber2(num1: Int, num2: Int) = if (num1 > num2) num1 else num2
fun getScore(name: String) = when (name) {
    "Tom" -> 86
    "Jim" -> 77
    "Jack" -> 95
    "Lily" -> 100
    else -> 0
}

fun getScore2(name: String) = when {
    name.startsWith("Tom") -> 77
    name == "Jim" -> 96
    name == "Jack" -> 66
    name == "Lily" -> 100
    else -> 0
}

fun checkNumber(num: Number) {
    when (num) {
        is Int -> println("number is Int")
        is Double -> println("number is Double")
        else -> println("number not support")
    }
}

fun doStudy(study: Study?) {
    study?.let {
        it.readBooks()
        it.doHomework()
    }
}
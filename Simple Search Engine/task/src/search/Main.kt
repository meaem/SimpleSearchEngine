package search

import java.io.File

fun printMenuAndGetUserChoice(): Int {

    val menue = "=== Menu ===\n" +
            "1. Find a person\n" +
            "2. Print all people\n" +
            "0. Exit"
    println(menue)
    var choice = readln()
    while (choice !in listOf("0", "1", "2")) {

        println("Incorrect option! Try again.")
        println(menue)
        choice = readln()
    }
    return choice.toInt()
}

fun searchForQuery(lines: List<String>, index: Map<String, MutableList<Int>>) {
//    println("\nEnter the number of search queries:")
//    val numOfQueries = readln().toInt()
//    println()
//    for (i in 1..numOfQueries) {
    println("Enter data to search people:")
    val q = readln()

    val result = index[q.lowercase()] //lines.filterIndexed { idx, s ->  index[q]!!.contains(idx) }

    if (result.isNullOrEmpty()) {
        println("No matching people found.\n")


    } else {
        if (result.size == 1)
            println("${result.size} person found:")
        else
            println("${result.size} persons found:")
        result.forEach { println(lines[it]) }
        println()
    }
//    }
}

fun main(args: Array<String>) {
//    println("Enter the number of people:")
//    val numOfLines = readln().toInt()
//    println("Enter all people:")
//    val lines = List(numOfLines) { readln() }
    if (args.size != 2) {
        println("Invalid args")
        return
    }

    if (args[0] != "--data") {
        println("Invalid args")
        return
    }
    val file = File(args[1])
    if (!file.exists()) {
        println("can not read data file")
        return
    }
    val lines = file.readLines()
    val index = buildIndex(lines)

    var choice = printMenuAndGetUserChoice()
    while (choice != 0) {
        when (choice) {
            1 -> searchForQuery(lines, index)
            2 -> printAll(lines)
        }

        choice = printMenuAndGetUserChoice()
    }

    println("Bye!")

}

fun buildIndex(lines: List<String>): Map<String, MutableList<Int>> {
//    println(lines.joinToString ("\n"))
    val index = mutableMapOf<String, MutableList<Int>>()
    lines.forEachIndexed { idx, s ->
        val tokens = s.lowercase().split("\\s+".toRegex())
        tokens.forEach { t -> index.putIfAbsent(t, mutableListOf(idx))?.add(idx) }
    }
//    println(index)
    return index
}

fun printAll(lines: List<String>) {
    println("=== List of people ===")
    lines.forEach { println(it) }
}

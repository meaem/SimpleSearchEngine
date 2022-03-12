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

fun searchForQuery(lines: List<String>) {
//    println("\nEnter the number of search queries:")
//    val numOfQueries = readln().toInt()
//    println()
//    for (i in 1..numOfQueries) {
    println("Enter data to search people:")
    val q = readln()
    val result = lines.filter {
//        it.split("\\b".toRegex()).map { it.lowercase() }.containsAll(q.lowercase().split("\\b".toRegex()))
        it.lowercase().contains(q.lowercase())
    }

    if (result.isEmpty()) {
        println("No matching people found.\n")
    } else {
//        println("\nPeople found:")
        result.forEach { println(it) }
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

    var choice = printMenuAndGetUserChoice()
    while (choice != 0) {
        when (choice) {
            1 -> searchForQuery(lines)
            2 -> printAll(lines)
        }

        choice = printMenuAndGetUserChoice()
    }

    println("Bye!")

}

fun printAll(lines: List<String>) {
    println("=== List of people ===")
    lines.forEach { println(it) }
}

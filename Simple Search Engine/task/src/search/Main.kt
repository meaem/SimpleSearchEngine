package search

fun main() {
    val text = readln().split(" ")
    val word = readln()
    val idx = text.indexOf(word)
    println(if (idx > -1) "${idx + 1}" else "Not found")
}

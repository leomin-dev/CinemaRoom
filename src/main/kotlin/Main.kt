package org.example

fun main() {
    val rows = getNumberOfRows()
    val seatsPerRow = getNumberOfSeats()
    print("Total income: $${income(rows, seatsPerRow)}")
}

fun getNumberOfRows(): Int {
    print("Enter the number of rows: ")
    return readln().toInt()
}

fun getNumberOfSeats(): Int {
    print("Enter the number of seats in each row: ")
    return readln().toInt()
}

fun income(rows: Int, seatsPerRow: Int): Int {
    val totalSeats = rows * seatsPerRow
    val totalIncome = when {
        totalSeats <= 60 -> totalSeats * 10
        else -> {
            val front = rows / 2
            val back = rows - front
            (front * 10 * seatsPerRow) + (back * 8 * seatsPerRow)
        }
    }
    return totalIncome
}

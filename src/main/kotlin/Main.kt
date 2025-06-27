package org.example

fun main() {
    val rows = getNumberOfRows() // 행
    val seatsPerRow = getNumberOfSeats() // 열
    val totalSeats = rows * seatsPerRow
    val cinema = MutableList(rows) { MutableList(seatsPerRow) { 'S' } }

    while (true) {
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("0. Exit")
        val menu = readln().toInt()

        when (menu) {
            1 -> printCinemaSeats(cinema)
            2 -> calculateTicketPrice(cinema, rows, totalSeats, seatsPerRow)
            0 -> break
            else -> println("Invalid input, please enter 0 to 2.")
        }
    }
}

fun getNumberOfRows(): Int {
    print("Enter the number of rows: ")
    return readln().toInt()
}

fun getNumberOfSeats(): Int {
    print("Enter the number of seats in each row: ")
    return readln().toInt()
}


fun printCinemaSeats(cinema: MutableList<MutableList<Char>>) {
    println("Cinema:")
    print("  ")
    // 열 숫자 표시
    for (col in 1..cinema[0].size) {
        print("$col ")
    }
    println()
    for (rowIndex in cinema.indices) {
        print("${rowIndex + 1} ")
        for (seat in cinema[rowIndex]) {
            print("$seat ")
        }
        println()
    }
}

fun calculateTicketPrice(cinema: MutableList<MutableList<Char>>, rows: Int, totalSeats: Int, seatsPerRow: Int) {
    print("Enter a row number: ")
    val selectRow = readln().toInt()
    print("Enter a seat number in that row: ")
    val selectSeat = readln().toInt()
    cinema[selectRow - 1][selectSeat - 1] = 'B'

    val price = if (totalSeats * seatsPerRow <= 60) {
        10
    } else {
        val frontHalf = rows / 2
        if (selectRow <= frontHalf) 10 else 8
    }
    println("Ticket price: $$price")
}

/*fun income(rows: Int, seatsPerRow: Int): Int {
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
}*/

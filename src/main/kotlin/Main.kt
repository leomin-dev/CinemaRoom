package org.example

var currentIncome = 0
var purchasedTickets = 0

fun main() {
    val rows = getNumberOfRows() // 행
    val seatsPerRow = getNumberOfSeats() // 열
    val totalSeats = rows * seatsPerRow
    val cinema = MutableList(rows) { MutableList(seatsPerRow) { 'S' } }


    while (true) {
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        val menu = readln().toInt()

        when (menu) {
            1 -> printCinemaSeats(cinema)
            2 -> calculateTicketPrice(cinema, rows, totalSeats, seatsPerRow)
            3 -> statistic(rows, seatsPerRow)
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

fun calculateTicketPrice(
    cinema: MutableList<MutableList<Char>>,
    rows: Int,
    totalSeats: Int,
    seatsPerRow: Int
) {
    while (true) {
        print("Enter a row number: ")
        val selectRow = readln().toIntOrNull()
        print("Enter a seat number in that row: ")
        val selectSeat = readln().toIntOrNull()

        // 1. 유효한 숫자인지, 범위 안에 있는지 먼저 검사
        if (
            selectRow == null || selectSeat == null ||
            selectRow !in 1..rows || selectSeat !in 1..seatsPerRow
        ) {
            println("Wrong input!")
            continue
        }

        // 2. 이미 예매된 좌석인지 검사
        if (cinema[selectRow - 1][selectSeat - 1] == 'B') {
            println("That ticket has already been purchased!")
            continue
        }

        // 3. 예매 진행
        cinema[selectRow - 1][selectSeat - 1] = 'B'

        val price = if (totalSeats <= 60) {
            10
        } else {
            val frontHalf = rows / 2
            if (selectRow <= frontHalf) 10 else 8
        }

        println("Ticket price: $$price")
        purchasedTickets++
        currentIncome += price
        break
    }
}


fun statistic(rows: Int, seatsPerRow: Int) {
    /*
    * The number of purchased tickets; -> calaulateTicketPrice 개수
    * The number of purchased tickets represented as a percentage. Percentages should be rounded to 2 decimal places;
    * Current income;
    * The total income that shows how much money the theatre will get if all the tickets are sold.
    * */

    val finalSeats = rows * seatsPerRow
    val percentage = if (finalSeats == 0) 0.0 else (purchasedTickets.toDouble() / finalSeats) * 100
    val formatPercentage = "%.2f".format(percentage)
    val totalIncome = calculateTotalIncome(rows, seatsPerRow)

    println("Number of purchased tickets: $purchasedTickets")
    println("Percentage: $formatPercentage%")
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")
}


fun calculateTotalIncome(rows: Int, seatsPerRow: Int): Int {
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

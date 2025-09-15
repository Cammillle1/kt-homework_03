package org.example

fun calculateCommission(
    cardType: String = "MIR",
    previousTransfers: Int = 0,
    transferAmount: Int
): Int? {
    if (transferAmount > 150_000) {
        println("Превышен суточный лимит перевода (150 000 руб.)")
        return null
    }

    if (previousTransfers + transferAmount > 600_000) {
        println("Превышен месячный лимит перевода (600 000 руб.)")
        return null
    }

    return when (cardType) {
        "MASTERCARD" -> calculateMastercardCommission(previousTransfers, transferAmount)
        "VISA" -> calculateVisaCommission(transferAmount)
        else -> 0
    }
}

private fun calculateMastercardCommission(previousTransfers: Int, transferAmount: Int): Int {
    val freeLimit = 75_000
    val taxableAmount: Int

    if (previousTransfers >= freeLimit) {
        taxableAmount = transferAmount
    } else if (previousTransfers + transferAmount <= freeLimit) {
        return 0
    } else {
        // Частично превышает лимит
        taxableAmount = previousTransfers + transferAmount - freeLimit
    }

    val commission = (taxableAmount * 0.006 + 20).toInt()
    println("Комиссия Mastercard: $taxableAmount * 0.6% + 20 = $commission руб.")
    return commission
}

private fun calculateVisaCommission(transferAmount: Int): Int {
    val commission = (transferAmount * 0.0075).toInt()
    val minCommission = 35

    val result = if (commission < minCommission) minCommission else commission
    println("Комиссия Visa: $transferAmount * 0.75% = $commission руб. (мин. $minCommission руб.)")
    return result
}


fun main() {
    calculateCommission(cardType = "VISA", previousTransfers = 50000, transferAmount = 100000)
}
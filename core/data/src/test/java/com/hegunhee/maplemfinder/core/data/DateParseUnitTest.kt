package com.hegunhee.maplemfinder.core.data

import org.junit.Test
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.Period
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

class DateParseUnitTest() {

    @Test
    fun dateParseTestInDateClass() {
        val dateString = "2024-01-5T03:07:56.893Z"

        val dateFormat = SimpleDateFormat("yyyy-mm-dd")
        runCatching {
            dateFormat.parse(dateString)
        }.onSuccess { date ->
            if(date != null) {
                val calendar = date.getCalendar()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH) + 1
                val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
                println("year : $year month : $month day : $dayOfMonth")
                assert(true)
            }else {
                assert(false)
            }
        }.onFailure {
            assert(false)
        }
    }

    @Test
    fun dateParseTestInZonedDateTime() {
        val dateString = "2024-01-05T03:07:56.893Z"
        runCatching {
            val formatter = DateTimeFormatter.ISO_INSTANT
            Instant.from(formatter.parse(dateString)).atZone(ZoneId.of("Asia/Seoul"))
        }.onSuccess {  zonedDateTime ->
            if(zonedDateTime != null) {
                val year = zonedDateTime.year
                val month = zonedDateTime.monthValue
                val dayOfMonth = zonedDateTime.dayOfMonth
                println("year : $year month : $month day : $dayOfMonth")
                assert(true)
            }else {
                assert(false)
            }
        }.onFailure {
            assert(false)
        }
    }

    @Test
    fun calculateBetweenPeriodInZonedDateTime() {
        val zonedDateTime1 = ZonedDateTime.parse("2024-01-05T03:07:56.893Z")
        val zonedDateTime2 = ZonedDateTime.parse("2024-01-10T08:15:30.123Z")

        // 두 ZonedDateTime 객체 간의 날짜 간격 계산
        val period = Period.between(zonedDateTime1.toLocalDate(), zonedDateTime2.toLocalDate())

        // 결과 출력
        println("두 ZonedDateTime 객체 간의 날짜 차이: $period")
        println("날짜 차이 (년): ${period.years}")
        println("날짜 차이 (월): ${period.months}")
        println("날짜 차이 (일): ${period.days}")

        assert(true)
    }

    @Test
    fun calculateBetweenPeriodInDate() {
        val afterDateString = "2024-01-10T03:07:56.893Z"
        val beforeDateString = "2024-01-05T03:07:56.893Z"

        val dateFormat = SimpleDateFormat("yyyy-mm-dd")
        val afterCalendar = dateFormat.parse(afterDateString)?.getCalendar()!!
        val beforeCalendar = dateFormat.parse(beforeDateString)?.getCalendar()!!

        val year = afterCalendar.get(Calendar.YEAR) - beforeCalendar.get(Calendar.YEAR)
        val month = afterCalendar.get(Calendar.MONTH) - beforeCalendar.get(Calendar.MONTH)
        val dayOfMonth = afterCalendar.get(Calendar.DAY_OF_MONTH) - beforeCalendar.get(Calendar.DAY_OF_MONTH)

        println("날짜 차이 (년): $year")
        println("날짜 차이 (월): $month")
        println("날짜 차이 (일): $dayOfMonth")
        assert(true)
    }

    private fun Date.getCalendar() : Calendar {
        return Calendar.Builder().setInstant(this).build()
    }
}
package com.hegunhee.maplemfinder.core.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.Period
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Calendar

fun getLoginStateString(loginDate : String, logoutDate : String) : String {
    if(loginDate > logoutDate) {
        return "현재 접속중"
    }
    return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        getLoginStateAfterAndroidOreo(logoutDate)
    }else {
        getLoginStateBeforeAndroidOreo(logoutDate)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun getLoginStateAfterAndroidOreo(logoutDate: String) : String{
    val currentDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"))
    val logoutDateTime = ZonedDateTime.parse(logoutDate)

    val period = Period.between(logoutDateTime.toLocalDate(),currentDateTime.toLocalDate())

    if(period.years > 0) {
        return "${period.years}년 전 접속했습니다."
    }
    if(period.months > 0) {
        return "${period.months}개월 전 접속했습니다."
    }
    if(period.days > 0) {
        return "${period.days}일 전 접속했습니다."
    }
    return "하루 전 접속했습니다."

}

private fun getLoginStateBeforeAndroidOreo(logoutDate: String) : String {
    val logoutDateTime = dateFormat.parse(logoutDate)!!
    val currentCalendar = Calendar.getInstance()

    val year = currentCalendar.get(Calendar.YEAR) - logoutDateTime.year
    val month = currentCalendar.get(Calendar.MONTH) - logoutDateTime.month
    val days = currentCalendar.get(Calendar.DAY_OF_MONTH) - logoutDateTime.day
    if(year > 0) {
        return "${year}년 전 접속했습니다."
    }
    if(month > 0) {
        return "${month}개월 전 접속했습니다."
    }
    if(days > 0) {
        return "${days}일 전 접속했습니다."
    }
    return "하루 전 접속했습니다."
}

private val dateFormat = SimpleDateFormat("yyyy-mm-dd")
package com.example.swensonheeval.common

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class UtilsTest{

    private lateinit var utils: Utils

    @Before
    fun setUp(){
        utils = Utils
    }

    @Test
    fun `change yyyy-MM-dd HH mm date format to readable format with success`(){
        val result = Utils.changeDateFormat(dateString = "2022-12-30 14:25")
        val result1 = Utils.changeDateFormat(dateString = "2019-01-01 1:43")

        assertThat(result).isEqualTo("Friday, 30 December 2022")
        assertThat(result1).isEqualTo("Tuesday, 1 January 2019")
    }


    @Test
    fun `change yyyy-MM-dd date format to get name of day`(){
        val result = Utils.changeDateFormat(dateString = "2022-12-30 14:25", requiredFormat = "EEEE")
        val result1 = Utils.changeDateFormat(dateString = "2019-01-01 1:43", requiredFormat = "EEEE")

        assertThat(result).isEqualTo("Friday")
        assertThat(result1).isEqualTo("Tuesday")
    }

    @Test
    fun `get correct time from date format string `(){
        val result = Utils.getTime(dateString = "2022-12-30 14:25")
        val result1 = Utils.getTime(dateString = "2019-01-01 1:43")

        assertThat(result).isEqualTo("2:25 pm")
        assertThat(result1).isEqualTo("1:43 am")
    }

}
package com.hbox.assignment3.utils

class StringUtils {

    companion object {
        fun isEmpty(str: String?): Boolean {
            return str == null || str.length == 0
        }
    }

}
package com.jadenwjh.restdemo.exception.message

class InvalidPostException(override val message: String?):
    RuntimeException(message)
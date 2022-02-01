package com.jadenwjh.restdemo.exception.message

class ElementNotFoundException(override val message: String?):
        RuntimeException(message)
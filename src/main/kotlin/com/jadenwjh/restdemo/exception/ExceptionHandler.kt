package com.jadenwjh.restdemo.exception

import com.jadenwjh.restdemo.exception.message.ElementNotFoundException
import com.jadenwjh.restdemo.exception.message.InvalidPostException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.Date

@RestController
@ControllerAdvice
class ExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handleUncaughtException(exception: Exception, webRequest: WebRequest): ResponseEntity<Any> =
        getResponseEntity(exception, webRequest, HttpStatus.INTERNAL_SERVER_ERROR)

    @ExceptionHandler(ElementNotFoundException::class)
    fun handleElementNotFoundException(exception: Exception, webRequest: WebRequest): ResponseEntity<Any> =
        getResponseEntity(exception, webRequest, HttpStatus.NOT_FOUND)

    @ExceptionHandler(InvalidPostException::class)
    fun handleInvalidPostException(exception: Exception, webRequest: WebRequest): ResponseEntity<Any> =
        getResponseEntity(exception, webRequest, HttpStatus.BAD_REQUEST)

    private fun getResponseEntity(exception: Exception, webRequest: WebRequest, status: HttpStatus) =
        ResponseEntity<Any>(
            ExceptionResponse(
                timestamp = Date().toString(),
                message = exception.message,
                trace = webRequest.getDescription(false)
            ), status)
}
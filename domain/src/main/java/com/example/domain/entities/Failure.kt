package com.example.domain.entities

sealed class Failure {

    object NetworkError : Failure()
}
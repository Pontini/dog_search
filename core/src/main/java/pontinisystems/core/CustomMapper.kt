package pontinisystems.core

interface CustomMapper<in E, T> {
    fun mapFrom(from: E): T
}
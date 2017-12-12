package com.dlgdev.pokemon

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import javax.sql.DataSource

fun <T> DataSource.use(block: (Connection) -> T): T {
    val connection = connection
    if (connection != null) {
        try {
            return block(connection)
        } finally {
            connection.close()
        }
    } else {
        throw IllegalStateException("No Connection returned from $this")
    }
}

fun <T> PreparedStatement.query(block: (ResultSet) -> T): T {
    val resultSet = executeQuery()
    try {
        return block(resultSet)
    } finally {
        resultSet.close()
    }
}
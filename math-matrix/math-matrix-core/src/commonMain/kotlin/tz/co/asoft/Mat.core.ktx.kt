package tz.co.asoft

fun <N : Number> R(vararg numbers: N) = Mat.Row(numbers.toMutableList())

fun <N : Number> Mat(vararg rows: Pair<N, N>) = Mat(rows.toList().map { Mat.Row(it.toList().toMutableList()) })

fun <N : Number> Mat(vararg rows: Mat.Row<N>) = Mat(rows.toList())

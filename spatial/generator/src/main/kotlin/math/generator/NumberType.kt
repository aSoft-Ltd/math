package math.generator

enum class NumberType(val label: String, val default: String) {
    Short("Short", "0"),
    Byte("Byte", "0"),
    Int("Int", "0"),
    Long("Long", "0"),
    Float("Float", "0f"),
    Double("Double", "0.0");

    internal fun canBeOperatedWith(other: NumberType) = when (this) {
        Short, Byte -> false
        Int -> listOf(Short, Byte, Int).contains(other)
        Long -> other != Double && other != Float
        Float -> other != Double
        else -> true
    }
}
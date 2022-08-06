package math.generator

enum class NumberType(val label: String, val default: String) {
    Short("Short", "0"),
    Byte("Byte", "0"),
    Int("Int", "0"),
    Long("Long", "0"),
    Float("Float", "0f"),
    Double("Double", "0.0");

    fun canHold(type: NumberType) = when (this) {
        Short -> false
        Byte -> false
        Int -> listOf(Short, Byte, Int).contains(type)
        Long -> listOf(Short, Byte, Int, Long).contains(type)
        Float -> type != Double
        Double -> true
    }
}
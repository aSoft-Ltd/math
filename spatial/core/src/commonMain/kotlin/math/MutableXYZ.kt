@file:Suppress("WRONG_EXPORTED_DECLARATION")
package math

interface MutableXYZ<N> : XYZ<N>, MutableXY<N>, MutableZ<N>
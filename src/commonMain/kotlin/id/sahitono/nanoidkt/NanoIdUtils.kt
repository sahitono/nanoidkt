package id.sahitono.nanoidkt

import com.soywiz.krypto.SecureRandom
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.ln
import kotlin.random.Random

/**
 * The default random number generator used by this class.
 * Creates cryptographically strong NanoId Strings.
 */
private val _defaultNumberGenerator = SecureRandom

/**
 * The default alphabet used by this class.
 * Creates url-friendly NanoId Strings using 64 unique symbols.
 */
private val _defaultAlphabet = "_-0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()

/**
 * The default size used by this class.
 * Creates NanoId Strings with slightly more unique values than UUID v4.
 */
private const val DEFAULT_SIZE = 21

fun randomNanoId(
    random: Random = _defaultNumberGenerator,
    alphabet: CharArray = _defaultAlphabet,
    size: Int = DEFAULT_SIZE
): String {
    require(!(alphabet.isEmpty() || alphabet.size >= 256)) { "alphabet must contain between 1 and 255 symbols." }
    require(size > 0) { "size must be greater than zero." }

    val mask = (2 shl floor(ln((alphabet.size - 1).toDouble()) / ln(2.0)).toInt()) - 1
    val step = ceil(1.6 * mask * size / alphabet.size).toInt()
    val idBuilder = StringBuilder(size)
    val bytes = ByteArray(step)

    while (true) {
        random.nextBytes(bytes)
        for (i in 0 until step) {
            val alphabetIndex = bytes[i].toInt() and mask
            if (alphabetIndex < alphabet.size) {
                idBuilder.append(alphabet[alphabetIndex])
                if (idBuilder.length == size) {
                    return idBuilder.toString()
                }
            }
        }
    }
}
import org.json.JSONArray
import org.json.JSONObject

public inline fun JSONArray.forEach(action: (JSONObject) -> Unit): Unit {
    for (element in 0..this.length()) action(this[element] as JSONObject)
}

public inline fun <R> JSONArray.map(transform: (JSONObject) -> R): List<R> {
    var result = ArrayList<R>(length())
    for (element in 0..this.length()) {
        result.add(transform(this[element] as JSONObject))
    }
    return result
}
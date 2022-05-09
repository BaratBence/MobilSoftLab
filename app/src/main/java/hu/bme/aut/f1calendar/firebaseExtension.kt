package hu.bme.aut.f1calendar

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

fun FirebaseAnalytics.createLog(id: String, name: String, location: String,  event: String) {
    val bundle = Bundle()
    bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id)
    bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name)
    bundle.putString(FirebaseAnalytics.Param.LOCATION, location)
    bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "text/plain")
    this.logEvent(event, bundle)
}
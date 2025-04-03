package tech.debuggingmadejoyful.anddaaven

import android.app.Application
import tech.debuggingmadejoyful.anddaaven.data.TefillaRepository
import tech.debuggingmadejoyful.anddaaven.data.TefillaRepositoryImpl

class AndDaavenApplication  : Application(){
    companion object {
        const val ANDDAAVEN_APP_URI = "https://app.debuggingmadejoyful.tech/anddaaven"
    }
    lateinit var tefillaRepository: TefillaRepository

    override fun onCreate() {
        super.onCreate()
        tefillaRepository = TefillaRepositoryImpl(this)
    }
}
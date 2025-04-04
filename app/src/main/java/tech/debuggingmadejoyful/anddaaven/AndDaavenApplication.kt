package tech.debuggingmadejoyful.anddaaven

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import tech.debuggingmadejoyful.anddaaven.data.preferences.PreferencesRepository
import tech.debuggingmadejoyful.anddaaven.data.tefilla.TefillaRepository
import tech.debuggingmadejoyful.anddaaven.data.tefilla.TefillaRepositoryImpl

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "preferences"
)

class AndDaavenApplication  : Application(){
    companion object {
        const val ANDDAAVEN_APP_URI = "https://app.debuggingmadejoyful.tech/anddaaven"
    }
    lateinit var tefillaRepository: TefillaRepository
    lateinit var preferencesRepository: PreferencesRepository
    override fun onCreate() {
        super.onCreate()
        tefillaRepository = TefillaRepositoryImpl(this)
        preferencesRepository = PreferencesRepository(dataStore)
    }
}
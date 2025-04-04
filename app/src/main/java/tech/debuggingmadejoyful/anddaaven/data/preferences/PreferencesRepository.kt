package tech.debuggingmadejoyful.anddaaven.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesRepository(private val dataStore: DataStore<Preferences>) {
    private companion object {
        val DARK_LIGHT_THEME = stringPreferencesKey("dark_light_theme")
    }

    val currentTheme: Flow<String> =
        dataStore.data.map { preferences ->
            preferences[DARK_LIGHT_THEME] ?: "AUTO"
        }

    suspend fun saveTheme(theme: String) {
        dataStore.edit { preferences ->
            preferences[DARK_LIGHT_THEME] = theme
        }
    }
}
package br.com.aline.ia_gemini.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aline.ia_gemini.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private val generativeModel = GenerativeModel(
        // For text-only input, use the gemini-pro model
        modelName = "gemini-pro",
        // Access your API key as a Build Configuration variable (see "Set up your API key" above)
        apiKey = BuildConfig.apiKey
    )

    private val _textGenerationResult = MutableStateFlow<String?>(null)

    val textGenerationResult = _textGenerationResult.asStateFlow()

    fun generateSuperMarketList(
        prompt: String =
            "Escreva uma lista de compras com 10 itens essenciais para uma dieta low carb. Insira emoticons para cada item"
    ) {
        _textGenerationResult.value = "Gerando lista de compras..."

        viewModelScope.launch(Dispatchers.IO) {
            try {

                val result = generativeModel.generateContent(prompt)

                _textGenerationResult.value = result.text

            } catch (e: Exception) {

                _textGenerationResult.value = "Error: ${e.message}"
            }
        }
    }
}
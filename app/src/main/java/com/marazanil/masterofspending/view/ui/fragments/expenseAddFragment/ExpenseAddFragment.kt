package com.marazanil.masterofspending.view.ui.fragments.expenseAddFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marazanil.masterofspending.R
import com.marazanil.masterofspending.data.db.ExpenseDatabase
import com.marazanil.masterofspending.data.db.entity.ExpenseEntity
import com.marazanil.masterofspending.data.db.service.ExchangeRateService
import com.marazanil.masterofspending.data.network.ExchangerateApiConfig
import com.marazanil.masterofspending.databinding.FragmentExpenseAddBinding
import com.marazanil.masterofspending.model.ExchangeRateResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExpenseAddFragment : Fragment() {

    private var _binding: FragmentExpenseAddBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentExpenseAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        fetchCurrencyRates()

        // EditText'e TextWatcher ekleyin.
        binding.editTextExpensePrice.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Bu methodun bu örnekte kullanılmasına gerek yok.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Bu methodun bu örnekte kullanılmasına gerek yok.
            }

            override fun afterTextChanged(s: Editable?) {
                s?.toString()?.toDoubleOrNull()?.let { amount ->
                    if (amount > 0) {
                        updateConvertedValues(amount) // Kullanıcının girdiği miktarla dönüşümü yapın.
                    }
                }
            }
        })
    }

    private var currentRates: Map<String, Double>? = null

    fun fetchCurrencyRates() {
        val apiKey = "c593e141adcac12b2aabb66b" // API anahtarınız.
        val url = "https://v6.exchangerate-api.com/v6/$apiKey/latest/TRY"

        ExchangerateApiConfig.service.getLatestRates(url).enqueue(object : Callback<ExchangeRateResponse> {
            override fun onResponse(call: Call<ExchangeRateResponse>, response: Response<ExchangeRateResponse>) {
                if (response.isSuccessful) {
                    // Log mesajı ekleyin.
                    Log.d("CurrencyRates", "Rates loaded successfully.")
                    currentRates = response.body()?.rates
                    binding.editTextExpensePrice.text.toString().toDoubleOrNull()?.let { amount ->
                        if (amount > 0) {
                            updateConvertedValues(amount)
                        }
                    }
                } else {
                    // Log mesajı ekleyin.
                    Log.e("CurrencyRates", "Error fetching rates: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<ExchangeRateResponse>, t: Throwable) {
                // Log mesajı ekleyin.
                Log.e("CurrencyRates", "Exception fetching rates: ${t.localizedMessage}", t)
            }
        })
    }



    fun updateConvertedValues(amountTl: Double) {
        currentRates?.let { rates ->
            val usdRate = rates["USD"] ?: 1.0
            val eurRate = rates["EUR"] ?: 1.0
            val gbpRate = rates["GBP"] ?: 1.0

            val convertedText = "USD: ${(amountTl / usdRate).format(2)}\n" +
                    "EUR: ${(amountTl / eurRate).format(2)}\n" +
                    "GBP: ${(amountTl / gbpRate).format(2)}"

            activity?.runOnUiThread {
                binding.textViewConvertedPrice.text = convertedText
            }
        } ?: Toast.makeText(context, "Döviz kurları henüz yüklenmedi.", Toast.LENGTH_SHORT).show()
    }

    fun Double.format(digits: Int) = "%.${digits}f".format(this)



    private fun setupListeners() {
        binding.detailsPageAddButton.setOnClickListener {
            saveExpenseToDatabase()
        }

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_expenseAddFragment_to_expenseListFragment)
            Toast.makeText(context, "Harcamalar Ekranına Geri Dönüldü!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveExpenseToDatabase() {
        val expenseName = binding.editTextExpense.text.toString()
        val expenseDetails = binding.editTextDetails.text.toString()
        val expensePriceInput = binding.editTextExpensePrice.text.toString().toDoubleOrNull() ?: 0.0
        val currencyType = getSelectedCurrency()

        val expensePrice = convertCurrency(expensePriceInput, "TRY", currencyType)

        if (expenseName.isBlank() || expensePriceInput <= 0) {
            Toast.makeText(context, "Harcama adı ve fiyatı boş olamaz !", Toast.LENGTH_SHORT).show()
            return
        }

        val expense = ExpenseEntity(
            expenseId = 0,
            expenseName = expenseName,
            expenseDetails = expenseDetails,
            expenseNumber = null,
            expensePrice = expensePrice.toString(),
            currencyType = currencyType
        )

        // Veritabanına kaydettik
        val expenseDao = ExpenseDatabase.getDatabase(requireContext())?.expenseDao()
        expenseDao?.insertExpense(expense)
        Toast.makeText(context, "Harcama başarıyla kaydedildi", Toast.LENGTH_SHORT).show()
        logSavedExpenses()
        findNavController().navigate(R.id.action_expenseAddFragment_to_expenseListFragment)

    }
    private fun convertCurrency(amount: Double, fromCurrency: String, toCurrency: String): Double {
        currentRates?.let { rates ->
            val rate = rates[toCurrency] ?: return amount // Eğer döviz kuru bilgisi yoksa, miktarı dönüştürmeden geri döndür.
            return if (fromCurrency == "TL") {
                amount / rate // TL'den dönüşüm yapılıyorsa, miktarı kura böl.
            } else {
                amount * rate // Diğer para birimlerinden TL'ye dönüşüm yapılıyorsa, miktarı kurla çarp.
            }
        } ?: run {
            Toast.makeText(context, "Döviz kurları henüz yüklenmedi.", Toast.LENGTH_SHORT).show()
            return amount // Döviz kurları yüklenmemişse, dönüşüm yapılmadan miktarı geri döndür.
        }
    }

    private fun getSelectedCurrency(): String {
        return when (binding.chipGroupCurrency.checkedChipId) {
            R.id.dollarChip -> "USD"
            R.id.euroChip -> "EUR"
            R.id.sterlinChip -> "GBP"
            else -> "TL" // Seçilen bir chip olmazsa varsayılan değer olarak TL döndür
        }
    }






    private fun logSavedExpenses() {
        val expenseDao = ExpenseDatabase.getDatabase(requireContext())?.expenseDao()
        val expenses = expenseDao?.getAllExpenses()
        expenses?.forEach { expense ->
            Log.d("Made Expenses", "ID: ${expense.expenseId}, Name: ${expense.expenseName}," +
                    "Details: ${expense.expenseDetails}, Number: ${expense.expenseNumber}, Price: ${expense.expensePrice}, Currency: ${expense.currencyType}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

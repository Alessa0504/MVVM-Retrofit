package com.example.mvvm_retrofit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_retrofit.adapter.PicsAdapter
import com.example.mvvm_retrofit.repository.Repository
import com.example.mvvm_retrofit.util.Resource
import com.example.mvvm_retrofit.viewmodel.PicsViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val repository = Repository()
    private val viewModel = PicsViewModel(repository)
    private val picsAdapter = PicsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_pics.layoutManager = LinearLayoutManager(this@MainActivity)
        rv_pics.adapter = picsAdapter

        viewModel.picLiveData.observe(this@MainActivity) { response ->
            when (response) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { data ->
                        picsAdapter.diff.submitList(data)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun hideProgressBar() {
        progress_circular.visibility = View.GONE
    }

    private fun showProgressBar() {
        progress_circular.visibility = View.VISIBLE
    }
}
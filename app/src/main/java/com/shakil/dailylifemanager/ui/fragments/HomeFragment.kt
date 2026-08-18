package com.shakil.dailylifemanager.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.shakil.dailylifemanager.database.LifeStoreDatabase
import com.shakil.dailylifemanager.database.LifeStoreRepository
import com.shakil.dailylifemanager.databinding.FragmentHomeBinding
import com.shakil.dailylifemanager.utils.DateTimeUtils
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var repository: LifeStoreRepository
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val database = LifeStoreDatabase.getDatabase(requireContext())
        repository = LifeStoreRepository(database)
        
        setupUI()
    }
    
    private fun setupUI() {
        val todayDate = DateTimeUtils.getCurrentDate()
        
        // Display today's date and day
        binding.todayDateText.text = DateTimeUtils.formatDate(todayDate)
        binding.todayDayText.text = DateTimeUtils.getDayOfWeek(todayDate)
        
        lifecycleScope.launch {
            // Get today's tasks
            repository.getTasksByDate(todayDate).collect { tasks ->
                val incompleteTasks = tasks.filter { !it.isCompleted }
                binding.incompleteTasksCount.text = incompleteTasks.size.toString()
                binding.completedTasksCount.text = tasks.filter { it.isCompleted }.size.toString()
            }
        }
        
        lifecycleScope.launch {
            // Get today's expenses
            repository.getTodayExpenseSum(todayDate).collect { sum ->
                binding.todayExpenseAmount.text = String.format("৳%.2f", sum ?: 0.0)
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
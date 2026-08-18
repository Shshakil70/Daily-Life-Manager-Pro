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
import com.shakil.dailylifemanager.databinding.FragmentRemindersBinding
import kotlinx.coroutines.launch

class RemindersFragment : Fragment() {
    
    private var _binding: FragmentRemindersBinding? = null
    private val binding get() = _binding!!
    private lateinit var repository: LifeStoreRepository
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRemindersBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val database = LifeStoreDatabase.getDatabase(requireContext())
        repository = LifeStoreRepository(database)
        
        setupUI()
    }
    
    private fun setupUI() {
        binding.remindersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        
        lifecycleScope.launch {
            repository.getAllReminders().collect { reminders ->
                // TODO: Setup adapter for reminders list
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
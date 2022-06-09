package com.example.adiser.adminMainActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adiser.databinding.DmasterAdminBinding
import com.example.adiser.R
import com.example.adiser.adapter.ListDataAdminAdapter
import com.example.adiser.viewModel.DataMasterViewModel

class DataMasterFragment : Fragment() {

    private var _binding: DmasterAdminBinding? = null
    private val binding get() = _binding!!
    private lateinit var listDataAdminAdapter: ListDataAdminAdapter
    private lateinit var viewModel: DataMasterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DmasterAdminBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[DataMasterViewModel::class.java]

        listDataAdminAdapter = ListDataAdminAdapter()
        with(binding.recyclerDmasterAdmin) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = this@DataMasterFragment.listDataAdminAdapter
        }

        val role = "user"
        observeData(role)

        binding.menuBawah.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.dashboard->findNavController().navigate(R.id.action_dataMasterFragment_to_adminDashboardFragment)
                R.id.data_sosial->findNavController().navigate(R.id.action_dataMasterFragment_to_dataSosialFragment)

            }
            true
        }

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeData(role: String) {
        viewModel.getDataUserAdmin(role).observe(viewLifecycleOwner) {
            with(listDataAdminAdapter) {
                setdata(it)
                notifyDataSetChanged()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
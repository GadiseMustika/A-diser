package com.example.adiser.adminMainActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adiser.Constants
import com.example.adiser.databinding.SuratSelesaiAdminBinding
import com.example.adiser.R
import com.example.adiser.adapter.ListSuratAdminAdapter
import com.example.adiser.viewModel.DataMasterViewModel
import com.example.adiser.viewModel.SuratProsesViewModel

class SuratSelesaiFragment : Fragment() {

    private var _binding: SuratSelesaiAdminBinding? = null
    private val binding get() = _binding!!
    private lateinit var listSuratAdminAdapter: ListSuratAdminAdapter
    private lateinit var viewModel: SuratProsesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SuratSelesaiAdminBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[SuratProsesViewModel::class.java]

        listSuratAdminAdapter = ListSuratAdminAdapter()
        with(binding.recyclerSselesaiAdmin) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = this@SuratSelesaiFragment.listSuratAdminAdapter
        }

        val status = arguments?.getString(Constants.SURAT_STATUS)
        if (status != null) {
            observeData(status)
        }

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeData(status: String) {
        viewModel.getDataSuratProses(status).observe(viewLifecycleOwner) {
            with(listSuratAdminAdapter) {
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
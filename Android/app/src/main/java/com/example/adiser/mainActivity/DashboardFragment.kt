package com.example.adiser.mainActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adiser.adapter.ListSuratUserAdapter
import com.example.adiser.databinding.DashboardUserBinding
import com.example.adiser.viewModel.DashboardViewModel
import com.example.adiser.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class DashboardFragment : Fragment() {

    private var _binding: DashboardUserBinding? = null
    private val binding get() = _binding!!
    private lateinit var listSuratUserAdapter: ListSuratUserAdapter
    private lateinit var viewModel: DashboardViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var user: FirebaseUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DashboardUserBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[DashboardViewModel::class.java]

        listSuratUserAdapter = ListSuratUserAdapter()
        with(binding.recyclerDashboardUser) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = this@DashboardFragment.listSuratUserAdapter
        }
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        val uid = user.uid
        observeData(uid)

        binding.menuBawah.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.layanan->findNavController().navigate(R.id.action_dashboardFragment_to_layananFragment)
                R.id.profile->findNavController().navigate(R.id.action_dashboardFragment_to_profilFragment)

            }
            true
        }

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeData(suratId: String) {
        viewModel.getDataSurat(suratId).observe(viewLifecycleOwner) {
            with(listSuratUserAdapter) {
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
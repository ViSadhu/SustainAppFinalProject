package com.example.vihang.ui.home;

import static android.content.Context.MODE_PRIVATE;
import static com.example.vihang.MainActivity.PREFS_NAME;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.vihang.DBHelper;
import com.example.vihang.R;
import com.example.vihang.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private DBHelper dbHelper;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences preferences = requireActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        dbHelper = new DBHelper(getContext());


        // add testing records if there are no facts available
        if (dbHelper.getFactsCount() == 0) {
            dbHelper.addTestingFacts();
        }


        String randomFact = dbHelper.getRandomFact();

        Log.d("HomeFragment", "Random Fact: " + randomFact);

        binding.tvHometitle.setText(getString(R.string.home_welcome, preferences.getString("displayName", "User")));
        binding.tvFunfactcontent.setText(randomFact);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
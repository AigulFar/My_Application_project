package com.example.myapplication_project.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication_project.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final EditText editTextAge = binding.editTextAge;
        final EditText editTextHight = binding.editTextHight;
        final EditText editTextWeight = binding.editTextWeight;
        final Spinner spinner_s = binding.spinnerSex;
        final TextView result = binding.textDashboard3;



        final Button button = binding.calculate;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int age = Integer.parseInt(editTextAge.getText().toString());
                    int hight = Integer.parseInt(String.valueOf(editTextHight.getText()));
                    int weight = Integer.parseInt(String.valueOf(editTextWeight.getText()));
                    String sex = spinner_s.getSelectedItem().toString();
                    String res = "Ваша суточная норма калорий : ";
                    if (sex.equals("Мужской")){
                        res += String.valueOf((int) (88.36 + (13.4 * weight) + (4.8 * hight) - (5.7 * age)));
                        result.setText(res);
                    }
                    else {
                        res += String.valueOf((int) (447.6 + (9.2 * weight) + (3.1 * hight) - (4.3 * age )));
                        result.setText(res);
                    }
                }
                catch (Exception e){
                    Toast.makeText(getActivity(), "Проверьте заполненные данные, возможно возникла ошибка!",
                            Toast.LENGTH_LONG).show();                }

            }
        });
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
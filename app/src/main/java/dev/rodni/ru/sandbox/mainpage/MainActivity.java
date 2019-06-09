package dev.rodni.ru.sandbox.mainpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dev.rodni.ru.sandbox.R;
import dev.rodni.ru.sandbox.adapter.EmployeeAdapter;
import dev.rodni.ru.sandbox.pojo.Employee;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private RecyclerView recyclerViewEmployees;
    private EmployeeAdapter adapter;

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);

        recyclerViewEmployees = findViewById(R.id.recycler_employees);
        adapter = new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<Employee>());
        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployees.setAdapter(adapter);

        presenter.loadData();
    }

    @Override
    protected void onDestroy() {

        presenter.disposeDisposable();

        super.onDestroy();
    }


    @Override
    public void saveData(List<Employee> employees) {
        adapter.setEmployees(employees);
    }

    @Override
    public void showError(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}

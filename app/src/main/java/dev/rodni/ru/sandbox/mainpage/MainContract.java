package dev.rodni.ru.sandbox.mainpage;

import java.util.List;

import dev.rodni.ru.sandbox.pojo.Employee;

public interface MainContract {

    interface View {

        void saveData(List<Employee> employees);

        void showError(Throwable t);
    }

    interface Presenter {

        void loadData();

        void disposeDisposable();
    }
}

package dev.rodni.ru.sandbox.mainpage;

import dev.rodni.ru.sandbox.api.ApiEmployeeClient;
import dev.rodni.ru.sandbox.api.ApiFactory;
import dev.rodni.ru.sandbox.pojo.EmployeeResponse;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements MainContract.Presenter {

    //using CompositeDisposable to handle requests when activity is gone
    private CompositeDisposable compositeDisposable;
    private Disposable disposable;

    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiEmployeeClient apiEmployeeClient = apiFactory.getApi();

        compositeDisposable = new CompositeDisposable();
        disposable = apiEmployeeClient.getEmployees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EmployeeResponse>() {
                    @Override
                    public void accept(EmployeeResponse employeeResponse) throws Exception {
                        view.saveData(employeeResponse.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError(throwable);
                    }
                });

        //adding our disposable to the composite one
        compositeDisposable.add(disposable);
    }

    @Override
    public void disposeDisposable() {
        //stoping observe everything when activity is gone
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}

package dev.rodni.ru.sandbox.api;

import dev.rodni.ru.sandbox.pojo.EmployeeResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiEmployeeClient {

    //remember that we will get employee response class as a base NOT just simple employee
    @GET("testTask.json")
    Observable<EmployeeResponse> getEmployees();
}

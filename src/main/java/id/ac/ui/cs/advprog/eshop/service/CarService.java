package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.List;

public interface CarService extends CarCreateService, CarFinderService, CarUpdateService, CarDeleteService {
}
interface CarCreateService {
    Car create(Car car);
}
interface CarFinderService {
    List<Car> findAll();
    Car findById(String carId);
}
interface CarUpdateService {
    void update(String carId, Car car);
}
interface CarDeleteService {
    void deleteCarById(String carId);
}
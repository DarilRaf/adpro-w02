package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CarServiceImpl implements CarService{
    @Autowired
    private CarRepository carRepository;
    @Override
    public Car create(Car car) {
        return carRepository.create(car);
    }

    @Override
    public void update(String carId, Car car) {
        carRepository.update(carId, car);
    }
    @Override
    public void deleteCarById(String carId) {
        carRepository.delete(carId);
    }
}

package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@Service
public class CarFinderImpl implements CarFinder{
    @Autowired
    private CarRepository carRepository;
    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> allCar = new ArrayList<>();
        carIterator.forEachRemaining(allCar::add);
        return allCar;
    }
        @Override
    public Car findById(String carId) {
        Car car = carRepository.findById(carId);
        return car;
    }
}

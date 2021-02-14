package racingcar.controller;

import racingcar.domain.*;
import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;
import java.util.Scanner;

public class RacingController {

    private final Scanner scanner;
    private final RacingTimeRegister racingTimeRegister;

    public RacingController(Scanner scanner) {
        this.scanner = scanner;
        this.racingTimeRegister = new RacingTimeRegister();
    }

    public void start() {
        final Cars cars = new Cars(InputView.inputCarName(scanner));
        final int raceTime = applyRaceTime();

        showRaceResult();

        for (int i = 0; i < raceTime; i++) {
            cars.goForwardAllCarsRandomly();
            showRaceState(cars.getCars());
        }

        showRaceWinner(cars.getWinnerNames());
    }

    private int applyRaceTime() {
        String inputTryTime = InputView.inputRaceTime(scanner);

        return racingTimeRegister.registerRacingTime(inputTryTime);
    }

    private void showRaceResult() {
        OutputView.printRaceResult();
    }

    private void showRaceState(List<Car> cars) {
        showAllCarsPosition(cars);
        OutputView.printNewLine();
    }

    private void showAllCarsPosition(final List<Car> cars) {
        cars.forEach(car ->
                OutputView.printRaceResultEachCar(
                        car.getName(), car.getPosition()));
    }

    private void showRaceWinner(final List<String> winnerNames) {
        OutputView.printWinner(winnerNames);
    }
}

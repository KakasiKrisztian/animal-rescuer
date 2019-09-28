package org.fasttrackit;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Game {
    Rescuer rescuer;
    VeterinaryDoctor veterinaryDoctor;
    Animal dog;
    private List<AnimalFood> availableAnimalFood = new ArrayList<>();
    private RecreationalActivity[] availableActivities = new RecreationalActivity[4];

//    public Game(Rescuer rescuer, VeterinaryDoctor veterinaryDoctor, Animal dog) {
//        this.rescuer = rescuer;
//        this.veterinaryDoctor = veterinaryDoctor;
//        this.dog = dog;
//    }

    public void start() {
//        initActivities();
//        foodNames();
//        availableActivities();
        initRescuerAndDifficulty();
        initAnimal();
        requireFeeding();
    }

    public final int initAnimal() {
        int animalChosen = getAnimalChoiceFromUser();
        String name = nameAnimal();
        if (animalChosen <= 1) {
            System.out.println("You have chosen to take care of this cute dog.");
            Animal dog = new Dog(name, "Husky");
            dog.setMoodLevel(4);
            dog.setScaredLevel(8);
            dog.setLoyalty(2);
            dog.setHungerLevel(9);
            dog.setHealthLevel(6);
            dog.setFoodPreferred("Meat");
            dog.setAge(4);
            dog.setEnergyLevel(7);
            dog.setActivityPreferred("play catch");
            dog.setToiletNeed(4);
            System.out.println("The name of your new dog is: " + name);
            System.out.println();
            System.out.println("The stats of " + dog.getName() + " are:");
            System.out.println("Age: " + dog.getAge());
            System.out.println("Preferred food: " + dog.getFoodPreferred());
            System.out.println("Activity preferred: " + dog.getActivityPreferred());
            System.out.println("Mood level: " + dog.getMoodLevel() + "/10");
            System.out.println("Scared level: " + dog.getScaredLevel() + "/10");
            System.out.println("Loyalty level: " + dog.getLoyalty() + "/10");
            System.out.println("Hunger level: " + dog.getHungerLevel() + "/10");
            System.out.println("Health level: " + dog.getHealthLevel() + "/10");
            System.out.println("Energy level: " + dog.getEnergyLevel() + "/10");
            System.out.println("Toilet need level: " + dog.getToiletNeed() + "/10");
            return 1;
        } else {
            System.out.println("You have chosen to take care of this fluffy cat.");
            Animal cat = new Cat(name, "Munchkin");
            cat.setMoodLevel(6);
            cat.setHungerLevel(7);
            cat.setActivityPreferred("play with a ball");
            cat.setLoyalty(4);
            cat.setAge(2);
            cat.setFoodPreferred("Beans");
            cat.setToiletNeed(6);
            cat.setHealthLevel(9);
            cat.setEnergyLevel(8);
            cat.setScaredLevel(5);
            System.out.println("The name of your new cat is: " + name);
            System.out.println();
            System.out.println("The stats bars of " + cat.getName() + " are:");
            System.out.println("Age: " + cat.getAge());
            System.out.println("Activity preferred: " + cat.getActivityPreferred());
            System.out.println("Preferred food: " + cat.getFoodPreferred());
            System.out.println("Mood level: " + cat.getMoodLevel() + "/10");
            System.out.println("Hunger level: " + cat.getHungerLevel() + "/10");
            System.out.println("Loyalty level: " + cat.getLoyalty() + "/10");
            System.out.println("Toilet need: " + cat.getToiletNeed() + "/10");
            System.out.println("Health level: " + cat.getHealthLevel() + "/10");
            System.out.println("Energy level: " + cat.getEnergyLevel() + "/10");
            System.out.println("Scared level: " + cat.getScaredLevel() + "/10");
        }
        return 2;
    }


    private int getAnimalChoiceFromUser() {
        System.out.println("Please select the type of animal would you like to take care of: Option 1 - Dog or Option 2 - Cat.");
        Scanner scanner = new Scanner(System.in);
        int x;
        try {
            x = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("You have entered an invalid option number.");
            return getAnimalChoiceFromUser();
        }
        if (x > 2 | x <= 0) {
            System.out.println("You have entered an invalid option number");
            getAnimalChoiceFromUser();
        } else {
            System.out.println("You have chosen option " + x);
            return x;
        }
        return x;
    }


    public void initRescuerAndDifficulty() {
        String rescuerNameChosen = rescuerNameChosenByUser();
        int budget = chosenDifficultyByUser();
        if (budget == 1) {
            budget = 150;
        } else if (budget == 2) {
            budget = 100;
        } else {
            budget = 75;
        }
        Rescuer rescuer = new Rescuer(rescuerNameChosen, budget);
        System.out.println("Your name is: " + rescuerNameChosen + ". Your budget is: " + budget + ". Be careful how you spend your money.");

    }

    private String rescuerNameChosenByUser() {
        System.out.println("Please enter your name.");
        Scanner scanner = new Scanner(System.in);
        String x;
        try {
            x = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("You have entered an invalid name");
            return rescuerNameChosenByUser();
        }
        return x;

    }

    private int chosenDifficultyByUser() {
        System.out.println("Please choose the difficulty on which you want to play: 1, 2 or 3.");
        Scanner scanner = new Scanner(System.in);
        int y = scanner.nextInt();
        try {
            if (y >= 4 || y <= 0) {
                System.out.println("You have entered an invalid option number.");
                chosenDifficultyByUser();
            } else {
                System.out.println("You have chosen difficulty: " + y);
                return y;
            }
        } catch (InputMismatchException e) {
            System.out.println("You have entered an invalid option number.");
            chosenDifficultyByUser();
        }
        return y;
    }

    private String nameAnimal() {
        System.out.println("Please choose a name for your new pet.");
        Scanner scanner = new Scanner(System.in);
        String name;
        try {
            name = scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("You have entered an invalid name");
            return nameAnimal();
        }
        return name;

    }

    private void requireFeeding() {
        System.out.println();
        System.out.println("Your pet is hungry. Please feed him.");
        initFood();
        foodNames();
        int x = getFeedingOptionFromUser();
        rescuer.feedPet(dog, availableAnimalFood.get(x));


    }

    private int getFeedingOptionFromUser() {
        Scanner scanner = new Scanner(System.in);
        int x;
        try {
            x = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("You have entered an invalid option number.");
            return getFeedingOptionFromUser();
        }
        if (x < 1 || x > 3) {
            System.out.println("You have entered an invalid option number.");
            getFeedingOptionFromUser();
        }
        System.out.println("You have chosen option " + x);
        return x;

    }


    public void initFood() {
        AnimalFood animalFood1 = new AnimalFood("Meat", 10, 1, "Dogs", false);
        availableAnimalFood.add(animalFood1);

        AnimalFood animalFood2 = new AnimalFood("Beans", 7, 1, "Cat", true);
        availableAnimalFood.add(animalFood2);
    }


    public void initActivities() {
        RecreationalActivity recreationalActivity1 = new RecreationalActivity("Walk");
        availableActivities[0] = recreationalActivity1;

        RecreationalActivity recreationalActivity2 = new RecreationalActivity("Exploring new places");
        availableActivities[1] = recreationalActivity2;

        RecreationalActivity recreationalActivity3 = new RecreationalActivity("Pet the animal");
        availableActivities[2] = recreationalActivity3;

        RecreationalActivity recreationalActivity4 = new RecreationalActivity("Talk with the animal");
        availableActivities[3] = recreationalActivity4;

    }

    public void foodNames() {
        System.out.println("The available foods to feed your animal are:");
        for (int i = 0; i <= availableAnimalFood.size(); i++) {
            int x = i;
            if (i == 0) {
                System.out.println("Option " + (x + 1) + " is: " + availableAnimalFood.get(i).getName());
            } else if (i == 1) {
                System.out.println("Option " + (x + 1) + " is: " + availableAnimalFood.get(i).getName());
            } else {
                System.out.println("Option " + (x + 1) + " is: You can choose to not feed the pet right now");
            }
        }
    }

    public void availableActivities() {
        System.out.println("The available activities are: ");
        for (int i = 0; i < availableActivities.length; i++) {
            System.out.println("Option " + (i + 1) + " is: " + availableActivities[i].getName());
        }
    }


}


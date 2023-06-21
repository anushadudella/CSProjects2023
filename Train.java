public class Main
{

    public static void main(String[] args) {

        Train train1 = new Train();
        train1 = train1.insertRear(train1,1,"oil");
        train1 = train1.insertRear(train1,2,"cookies");
        train1 = train1.insertRear(train1,3,"cereals");

        Train.printTrainCars(train1);
        System.out.println("Inserting new car to the front with contents Barley");
        train1 = train1.insertFront(train1,new TrainCar(4,"barley"));
        Train.printTrainCars(train1);
        System.out.println("Removing train car with cookies");
        train1 = train1.remove(train1,"cookies");
        Train.printTrainCars(train1);
        System.out.println("Retrieved car 1 contents: " + train1.retrieve(train1,1));
        Train.printTrainCars(train1);
        
    }

}

class TrainCar
{
    int carNumber;
    String carContents;
    TrainCar nextCar;

    //Constuctor for building a TrainCar
    //takes in a carNumber and carContents
    public TrainCar(int carNumber, String carContents){
        this.carNumber = carNumber;
        this.carContents = carContents;
    }

    //@param: does not take anything (get method)
    //@return: returns the car number of a trainCar
    public int getCarNumber(){
        return carNumber;
    }

    //@param: does not take anything (get method)
    //@return: returns the car contents of a trainCar
    public String getCarContents() {
        return carContents;
    }

    //@param: takes the car contents of a car 
    // in order to set it to a trainCar object
    //@return: is a void method so does not return anything
    public void setCarContents(String carContents) {
       this.carContents = carContents;
    }

    //@param: takes the car number of a car 
    // in order to set it to a trainCar object
    //@return: is a void method so does not return anything
    public void setCarNumber(int carNumber){
        this.carNumber = carNumber;
    }

    //@param: takes nextCar of a car 
    // in order to set it to a trainCar object
    //@return: is a void method so does not return anything
    public void setNextCar(TrainCar nextCar){
        this.nextCar = nextCar;
    }

    //@param: does not take anything (get method)
    //@return: if the next car of a trainCar object is not null
    // returns the next car of the train
    // else it returns null
    public TrainCar getNextCar(){

        if (nextCar != null)
            return nextCar;
        else
            return null;
    }


}

class Train
{
    TrainCar front;

    public Train(){
        //System.out.println("A New Train constructed");
    }

    //@param: takes a train object, car number, and car contents)
    //@return: returns the modified train 
    public Train insertRear(Train train, int carNumber, String carContents) {

        //creates a new trainCar
        TrainCar trainCar = new TrainCar(carNumber, carContents);

        //if the front of a train is = to null
        //the front of the train is set to the trainCar created
        if(front == null) {
            front = trainCar;
        }
        
        //if the front of the train is NOT null
        //loop through the train (keep calling getNextCar)
        // in order to find a car that is NOT null
        // this will be the last car
        else {
                TrainCar last = train.front;
                while(last.getNextCar() != null) {
                    last = last.getNextCar();
                }
                last.nextCar = trainCar;
        }

        return train;

    }
    
    //@param: takes train and trainCar objects
    //to insert a car at the front
    // stores a variable (the old front car) as the 
    // front of the car
    // the car to insert as the front car's NEXT car should be 
    // the old front of the train
    // @return: returns the modified train
    public Train insertFront(Train train, TrainCar carToInsert) {
        
        TrainCar oldfrontcar = train.front;
        carToInsert.setNextCar(oldfrontcar);
        front = carToInsert;
        return train;
    }

    //@param: takes a train object and string carContents
    //@return: returns the modified train
    public Train remove(Train train, String carContents) {

        TrainCar first = train.front, prevCar = null; // this is the first car
        
        // If the front of the car is not null and the contents of the 
        // first car are equal to the contents being passed
        // set the front of the train to the next car
        if(first!= null && first.getCarContents().equals(carContents)) {
            train.front = first.getNextCar();
            
            return train;
        }
        
        // when any other traincar has the matching contents to be deleted.
        // iterating through the train cars looking for the car
        // that would be matching the contents
        // store the previous car to make the next car of that 
        // to its new neighbor
        while(first!= null && !(first.getCarContents().equals(carContents))) {
          prevCar = first;
          first = first.getNextCar();

        }
        
        // FOUND the traincar (first) that matched the contents in the code 
        // before this code and now 
        // set the previous car of this FOUND car 
        // to the next car of this FOUND car
        if(first != null) {
            prevCar.setNextCar(first.getNextCar());
            System.out.println("TrainCar with " + first.getCarContents()+ " found and deleted");
        }
        else if(first == null)
            System.out.println("TrainCar with " + carContents + " not found so not deleted");
            
        return train;
    }

    //@param: takes a train object and a car number
    // loops through the train checking if it equals the 
    // number passed into the method
    // if trainCar does not equal the number --> continues to loop 
    // if trainCar does equal the number --> returns the contents 
    // of the car
    //@return: returns the carContents (string)
    // or null
    public String retrieve(Train train, int carNumber){
        
            TrainCar last = train.front;

            if (last.getCarNumber() == carNumber) {
                return last.getCarContents();
            } else {
                while (last.getNextCar() != null) {
                    last = last.getNextCar();
                    if (last.getCarNumber() == carNumber) {
                        return last.getCarContents();
                    }
                }

            }
            
            return null;
        }


    //@param: takes a train object
    // checks if the train is null --> if yes, prints it is empty
    // if the traincar is not null
    // prints the car number and the contents
    //@return: does not return anything (is a void method)
    public static void printTrainCars(Train train) {
        
        TrainCar trainCar = train.front;
        if(trainCar == null) {
            System.out.println("Train is empty");
        }
        else {

            while(trainCar != null) {
                System.out.println(trainCar.getCarNumber() + "," + trainCar.getCarContents());
                trainCar = trainCar.getNextCar();
                
            }
        }

    }

}

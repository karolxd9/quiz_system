package com.example.quiz_system;

import com.conf.SystemInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class ParallelDataGenerator extends DataGenerator implements Callable<Boolean>{
    private long minUserID;
    private  long maxUserID;

    private int numberOfThreads; //liczba rdzeni procesora;



    public ParallelDataGenerator(SystemInfo system) throws SQLException {
        this.minUserID = DataGenerator.getID(false,"user_id","user");
        this.maxUserID = DataGenerator.getID(true,"user_id","user");
        this.numberOfThreads = system.getNumberOfCore();
    }

    /**
     * Generacja n użytkowników
     * @param n liczba wygenerowanych rekordów
     * @return Łańcuch znaków w liczbą n wygenrowanych rekordów dla tabeli użytkownicy
     */
    public List<String> SingleGeneratingUsers(int n){
        List<String> records = new ArrayList<>();
        for(int i=0; i<n; i++ ){
            records.add(super.generateUser());
        }
        return records;

    }

    /**
     * Równoległe generowanie użytkowników
     * @param n łączna liczba wygenerowanych rekordów
     * @return Łańcuch znaków w liczbą n wygenrowanych rekordów dla tabeli użytkownicy
     */
    public String ParallelGeneratingUsers(int n) {
        int numberOfDataPerThread = n / this.numberOfThreads;
        int leftData = n % this.numberOfThreads;

        ExecutorService executorService = Executors.newFixedThreadPool(this.numberOfThreads);

        List<Future<List<String>>> futures = new ArrayList<>();

        for (int i = 0; i < this.numberOfThreads; i++) {
            Callable<List<String>> dataGenerationTask = () -> SingleGeneratingUsers(numberOfDataPerThread);
            Future<List<String>> future = executorService.submit(dataGenerationTask);
            futures.add(future);
        }

        // Handle the remaining data
        for (int i = 0; i < leftData; i++) {
            Callable<List<String>> dataGenerationTask = () -> SingleGeneratingUsers(1);
            Future<List<String>> future = executorService.submit(dataGenerationTask);
            futures.add(future);
        }

        // Collect the results
        List<String> result = new ArrayList<>();

        for (Future<List<String>> future : futures) {
            try {
                result.addAll(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // Shutdown the executor service
        executorService.shutdown();

        return result.toString();
    }




    @Override
    public Boolean call() throws Exception {
        return true;
    }
}

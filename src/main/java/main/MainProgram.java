package main;

import concurrent_solution.MultiConsumer;
import concurrent_solution.MultiProducer;
import data.StudentVle;
import interfaces.Consumer;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import sequential_solution.SingleConsumer;
import interfaces.Producer;
import sequential_solution.SingleProducer;

/**
 * Entry point of the program.
 * Runs in Single-thread or Multi-thread mode.
 */
public class MainProgram {

  /**
   * Runs in Single-thread mode.
   * @param fileDir the file dir of courses.csv and studentVle.csv
   * @param threshold user specified threshold value
   * @throws IOException IOException
   * @throws InterruptedException InterruptedException
   */
  public static void runSingle(final String fileDir, final double threshold)
      throws IOException, InterruptedException {
    final Consumer consumer = new SingleConsumer(fileDir, threshold);
    final Producer producer = new SingleProducer(consumer);

    producer.processStudentVleFile(fileDir);
  }

  /**
   * Runs in Multi-thread mode.
   * @param fileDir the file dir of courses.csv and studentVle.csv
   * @param threshold user specified threshold value
   * @throws IOException IOException
   * @throws InterruptedException InterruptedException
   */
  public static void runMulti(final String fileDir, final double threshold)
      throws IOException, InterruptedException {
      final BlockingQueue<StudentVle> blockingQueue = new ArrayBlockingQueue<StudentVle>(10000);

      final Thread producerT = new Thread(new MultiProducer(blockingQueue, fileDir));
      final Thread consumerT = new Thread(new MultiConsumer(blockingQueue, fileDir, threshold));
      producerT.start();
      consumerT.start();

      //let main thread wait for two sub-threads.
      //prevent Junit test cases exiting early.
      producerT.join();
      consumerT.join();
  }

  /**
   * main method.
   * @param args user specified args
   */
  public static void main(final String[] args) {
    try {
      if (args.length == 1) {
        runSingle(args[0], 10000);
        //runMulti(args[0], 10000);
      } else if (args.length == 2) {
        //runSingle(args[0], Double.parseDouble(args[1]));
        runMulti(args[0], Double.parseDouble(args[1]));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

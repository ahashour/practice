// import java.util.Scanner;
// import webGraph.*;
import java.util.*;
import java.util.concurrent.*;

public class WebCrawler {

    // private static final Scanner scanner = new Scanner(System.in);
    static ExecutorService executor = Executors.newFixedThreadPool(5);
    static List<Callable<Boolean>> myTasks = new ArrayList();

    public static HashSet<String> webCrawler(WebGraph input, WebNode start) {
        HashSet<String> output = new HashSet();
        // webCrawlerDFS(input, start, output);
        
        // try {
        //     webCrawlerDFSMultiThread(input, start, output);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        // ExecutorService executor = Executors.newCachedThreadPool();
        Semaphore semp = new Semaphore(1);
        webCrawlerDFSMultiThread2(input, start, output, semp);
        try {
            executor.invokeAll(myTasks);
            executor.shutdown();
            executor.awaitTermination(50000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ie) {
            executor.shutdownNow();
        }

        return output;
    }

    public static void webCrawlerDFS(WebGraph input, WebNode start, HashSet output) {
        // System.out.println(start);
        if ( output.contains(start.value)) return;
        output.add(start.value);
        for (WebNode adjNode : start.adjList) {
            webCrawlerDFS(input, adjNode, output);
        }
    }

    public static synchronized void webCrawlerDFSMultiThread(WebGraph input, WebNode start, HashSet output) throws Exception {
        // System.out.println(start);
        if ( output.contains(start.value)) return;
        output.add(start.value);
        List<Thread> myThreads = new ArrayList<Thread>();
        
        for (WebNode adjNode : start.adjList) {
            Thread x = new Thread() {
                public void run() {
                    try {
                        webCrawlerDFSMultiThread(input, adjNode, output);
                    } 
                    catch(Exception e) {
                        System.out.println(e);
                    }
                } 
            };
            myThreads.add(x);
            x.start();
        }
        try {
            for (Thread y : myThreads) {
                y.join();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void webCrawlerDFSMultiThread2(WebGraph input, WebNode start, HashSet output, Semaphore semp) {
        System.out.println(start);
        semp.tryAcquire();
        if ( output.contains(start.value)) { 
            semp.release();
            return;
        }
        output.add(start.value);
        semp.release();
        for (WebNode adjNode : start.adjList) {
            // System.out.println("submitTobe" + adjNode);
            // if (output.contains(adjNode.value))
            // {
                Callable<Boolean> task = () -> {
                    System.out.println("submit" + adjNode);
                    webCrawlerDFSMultiThread2(input, adjNode, output, semp);
                    return true;
                };
                executor.submit(task);
                myTasks.add(task);
            // }
        }
    }

    public static void main(String[] args) {
        // String y = scanner.nextLine();
        // scanner.close();
        WebGraph myWeb = new WebGraph();
        myWeb.addNode("A"); //0
        myWeb.addNode("B"); //1
        myWeb.addNode("C"); //2
        myWeb.addNode("D"); //3
        myWeb.addNode("E"); //4
        myWeb.addNode("F"); //5
        myWeb.connectNodes(myWeb.adjList.get(0), myWeb.adjList.get(1));
        myWeb.connectNodes(myWeb.adjList.get(0), myWeb.adjList.get(3));
        myWeb.connectNodes(myWeb.adjList.get(0), myWeb.adjList.get(4));
        myWeb.connectNodes(myWeb.adjList.get(1), myWeb.adjList.get(2));
        myWeb.connectNodes(myWeb.adjList.get(1), myWeb.adjList.get(5));
        myWeb.connectNodes(myWeb.adjList.get(5), myWeb.adjList.get(0));
        myWeb.connectNodes(myWeb.adjList.get(3), myWeb.adjList.get(0));
        myWeb.connectNodes(myWeb.adjList.get(4), myWeb.adjList.get(3));

        HashSet<String> result = webCrawler(myWeb, myWeb.adjList.get(0));
        System.out.println(result.size());
        System.out.println(result);
    }
}
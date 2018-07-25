// package webGraph;

import java.io.*;
import java.util.*;

public class WebGraph {
    public List<WebNode> adjList;

    public WebGraph() {
        this.adjList = new ArrayList<WebNode>();
    }
    public WebNode addNode(String value) {
        WebNode result = new WebNode(value, this.adjList.size());
        this.adjList.add(result);
        return result;
    }

    public void connectNodes(WebNode s, WebNode d) {
        if ( s == null || d == null ) {
            throw new IllegalArgumentException("input is null for connectNodes");
        }
        if ( s.id >= this.adjList.size() || d.id >= this.adjList.size() ) {
            throw new IllegalArgumentException("input is null for connectNodes");
        }
        this.adjList.get(s.id).adjList.add(d);
        // this.adjList.get(d.id).adjList.add(s);
    }
}

class WebNode {
    String value;
    int id;
    public List<WebNode> adjList;

    public WebNode(String value, int id) {
        this.value = value;
        this.id = id;
        this.adjList = new ArrayList<WebNode>();
    } 

    public String toString() {
        return "Value = " + this.value + "\tid = " + id;
    }
}
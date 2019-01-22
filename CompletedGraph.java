/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

/**
 *
 * @author pittsfirstbeauty
 */
//package Project;

import java.util.LinkedList;
import java.util.Random;

public class CompletedGraph {
	//two-dimension array
		public static int [][] completedTwoDim(int size) {
			int [][] graph = new int [size][size];
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (i == j) {
						graph[i][j] = 0;
					} else {
						graph[i][j] = random(1, 10);
					}
				}
			}
			return graph;
		}
		//Linked List
		public static LinkedList <LinkedList <Integer>> completedLinkedList(int size) {
			LinkedList <LinkedList <Integer>> graph = new LinkedList <> ();
			for (int i = 0; i < size; i++) {
				LinkedList <Integer> list = new LinkedList <> ();
				for (int j = 0; j < size; j++) {
					if (i == j) {
						list.add(0);
					} else {
						list.add(random(1, 10));
					}
				}
				graph.add(list);
			}
			return graph;
		}
		//one-dimension array
		public static int [] completedOneDim(int size) {
			int [] graph = new int [size * (size - 1) / 2];
			for (int i = 0; i < size; i++) {
				graph[i] = random(1, 10);
			}
			return graph;
		}
		//get a random integer between low and high
		public static int random(int low, int high) {
			Random rand = new Random();
			return rand.nextInt(high - low + 1) + low;
		}
}


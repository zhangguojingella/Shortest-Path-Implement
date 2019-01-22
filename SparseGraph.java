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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

class SparseGraph {
	//two-dimension array
	public int [][] sparseTwoDim(int size) {
		int [][] graph = new int [size][size];
		ArrayList <int []> list = new ArrayList <> ();
		for (int i = 0; i < size - 1; i++) {
			int x = random(0, size - 1);
			int y = random(0, size - 1);
			int status = 0;
			int [] edge = new int[2];
			if (x < y) swap(x, y);
			for (int [] one: list) {
				if (x == one[0] && y == one[1]) status = 1;
			}
			edge[0] = x;
			edge[1] = y;
			if (x == y || status == 1) {
				i--;
				continue;
			} else {
				list.add(edge);
			}
		}
		for (int [] one: list) {
			graph[one[0]][one[1]] =random(1, 10);
			graph[one[1]][one[0]] = graph[one[0]][one[1]];
		}
		return graph;
	}
	//Linked List
	public static LinkedList <LinkedList <Integer>> sparseLinkedList(int size) {
		LinkedList <LinkedList <Integer>> graph = new LinkedList <> ();
		for (int i = 0; i < size; i++) {
			LinkedList <Integer> list = new LinkedList <> ();
			for (int j = 0; j < size; j++) {
				list.add(0);
			}
			graph.add(list);
		}
		ArrayList <int []> list = new ArrayList <> ();
		for (int i = 0; i < size - 1; i++) {
			int status = 0;
			int x = random(0, size - 1);
			int y = random(0, size - 1);
			int [] edge = new int [2];
			if (x < y) swap(x, y);
			for (int [] one: list) {
				if (x == one[0] && y == one[1]) status = 1;
			}
			edge[0] = x;
			edge[1] = y;
			if (x == y || status == 1) {
				i--;
				continue;
			} else {
				list.add(edge);
			}
		}
		for (int [] one: list) {
			graph.get(one[0]).set(one[1], random(1, 10));
			graph.get(one[1]).set(one[0], graph.get(one[0]).get(one[1]));
		}
		for (int i = 0; i < graph.size(); i++) {
			for (int j = 0; j < graph.size(); j++) {
				if (graph.get(i).get(j) == null) {
					graph.get(i).set(j, 0);
				}
			}
		}
		return graph;
	}
	public static int [] sparseOneDim(int size) {
		int [] graph = new int [size * (size - 1) / 2];
		ArrayList <int []> list = new ArrayList <> ();
		for (int i = 0; i < size - 1; i++) {
			int status = 0;
			int x = random(0, size - 1);
			int y = random(0, size - 1);
			int [] edge = new int [2];
			if (x < y) swap(x, y);
			for (int [] one: list) {
				if (x == one[0] && y == one[1]) status = 1;
			}
			edge[0] = x;
			edge[1] = y;
			if (x == y || status == 1) {
				i--;
				continue;
			} else {
				list.add(edge);
			}
		}
		for (int [] one: list) {
			graph[one[1] + one[0]*(one[0] - 1) / 2] = random(1, 10);
		}
		return graph;
	}
	//get a random integer between low and high
	public static int random(int low, int high) {
		Random rand = new Random();
		return rand.nextInt(high - low + 1) + low;
	}
	//swap two integers
	public static void swap(int i, int j) {
		int temp = i;
		j = i;
		i = temp;
	}
}

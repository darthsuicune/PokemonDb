package com.dlgdev.pokemon.database;

import java.util.HashMap;
import java.util.Map;

public class Pokemon {
	final int dexNumber;
	final int formNumber;
	public String name;
	public Map<String, String> names = new HashMap<>();
	public int[] baseStats = new int[6];
	public String[] types = new String[3];
	public double[] genderRatio = new double[2];
	public Ability[] abilities = new Ability[3];
	public String[] eggGroups = new String[2];
	public double height;
	public double weight;
	public String color;
	public String[] evos;
	public String prevo;
	public String[] otherForms;
	public String gender;
	public int evoLevel;
	public String baseForm;
	public String formName;
	public String formLetter;


	public Pokemon(int dexNumber, int formNumber) {
		this.dexNumber = dexNumber;
		this.formNumber = formNumber;
	}
}

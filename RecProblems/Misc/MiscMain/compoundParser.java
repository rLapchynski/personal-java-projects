package MiscMain;

import java.util.*;
import utilities.*;
import utilities.Data.Utils;

public class compoundParser {
	
	public static ArrayList<String> elementPropetyStrings = Files.fileList("C:/Users/Ryan Lapchynski/EclipseWorkspace/Resources/Elements.txt");
	//Thread.currentThread().getContextClassLoader().getResource("Elements.txt")
	//this.getClass().getResource("/Elements.txt")
	public static ArrayList<Element> elements = new ArrayList<Element>();
	public static ArrayList<String> symbols = new ArrayList<String>();
	
	public static void main(String[] args) {
		for(String PropertyStr : elementPropetyStrings){
			elements.add(new Element(PropertyStr));
			symbols.add(elements.get(elements.size()-1).symbol);
		}
		
		Compound c = new Compound("Fe6(HO1K3)2(PbPBCFXe)2Be5((RuAg(HeO)2)3BrHg)4(Na2Cl)3");
		//c.Formula = "Be5((RuAg(He)2)3BrHg)4(Na2Cl)3Fe6(HO4K3)2";
		//c.parseFromFormula();
		//c.print();
		c.printBreakdown();
		//System.out.println(c.Formula);
		
	}
	
	public static int countOccurances(ArrayList<Compound> c, Compound d){
		int retInt = 0;
		for(Compound e : c) if(e.Formula.equals(d.Formula)) retInt++;
		return retInt;
	}
}

class Compound{
	public ArrayList<Element> elements;
	public ArrayList<Compound> compounds;
	public String Formula;
	
	public Compound(){
		elements = new ArrayList<Element>();
		compounds = new ArrayList<Compound>();
	}
	public Compound(ArrayList<Compound> c){
		elements = new ArrayList<Element>();
		compounds = c;
	}
	public Compound(ArrayList<Element> e, ArrayList<Compound> c){
		elements = e;
		compounds = c;
	}
	public Compound(String in){
		Compound c = parseCompound(in);
		elements = c.elements;
		compounds = c.compounds;
		Formula = c.Formula;
	}

	public void parseFromFormula(){
		Compound parsed = parseCompound(this.Formula);
		this.elements = parsed.elements;
		this.compounds = parsed.compounds;
	}
	public static ArrayList<Element> parseElements(String in){
		ArrayList<Element> retList = new ArrayList<Element>();
		
		for(int i = 0; i < in.length(); i++){
			for(int a = i+1; a <= in.length() && a < i+3; a++){
				
				if(compoundParser.symbols.contains(in.substring(i, a)) && (a==in.length() || !compoundParser.symbols.contains(in.substring(i, a+1))) ){
					int x = 1;
					
					if(a!=in.length() && a+1!=in.length() && in.substring(a, a+2).matches("[0-9]{2}")) x = Integer.parseInt(in.substring(a, a+2));
					else if(a!=in.length() && in.substring(a, a+1).matches("[0-9]{1}")) x = Integer.parseInt(in.substring(a, a+1));
					
					for(int b = 0; b < x; b++) retList.add(compoundParser.elements.get(compoundParser.symbols.indexOf(in.substring(i, a).trim())));
				}
				
			}

			
		}
		
		return retList;
	}
	public static Compound parseCompound(String in){
		Compound retComp = new Compound();
		retComp.Formula = in;
		
		for(String s : splitSubCompounds(in, 0)){
			if(s.contains("(") || s.contains(")")){
				retComp.compounds.add(parseCompound(s));
			}else{
				retComp.elements.addAll(parseElements(s));
			}
		}
		
		return retComp;
	}
	public static ArrayList<String> splitSubCompounds(String in, int iteration){
		ArrayList<String> retArr = new ArrayList<String>();
		int layer = 0, firstInd = -1;
		boolean addIn = true;
		for(int i = 0; i<in.length(); i++){
			if(in.substring(i, i+1).equals("(")){
				if(firstInd == -1) firstInd = i;
				layer++;
			}else if(in.substring(i, i+1).equals(")")){
				layer--;
			}
			if(firstInd != -1 && layer == 0){
				//Num.printCol(new int[]{45, 10}, Num.repeat("   ", iteration)+in, ""+firstInd);
				int x = 1;
				if(i < in.length()-2 && in.substring(i+2, i+3).matches("[0-9]{2}") ) x = Integer.parseInt(in.substring(i+2, i+3));
				else if(i < in.length()-1 && in.substring(i+1, i+2).matches("[0-9]{1}") ) x = Integer.parseInt(in.substring(i+1, i+2));
				for(int a = 0; a < x; a++){
					retArr.add(in.substring(firstInd+1, i));
				}
				in = in.substring(0, firstInd) + in.substring(i+1+(x==1 ? 0 : Utils.length(x)));
				//Num.printlnCol(new int[]{5, 30, 45}, x+"", retArr.get(retArr.size()-1), in);
				retArr.addAll(splitSubCompounds(in, iteration +1));
				addIn = false;
				break;
			}
		}
		if(addIn) retArr.add(in);
		
		return retArr;
	}
	private static void print(Compound c, int iteration){
		//System.out.print("\n");
		//for(Compound d : c.compounds) System.out.print(d.Formula + " ");
		ArrayList<String> printed = new ArrayList<String>();
		for(String s : Compound.splitSubCompounds(c.Formula, 0)){
			if((s.contains("(") || s.contains(")")) && !printed.contains(s)){
				System.out.println(Utils.repeat("	", iteration) + s);
				printed.add(s);
				print(new Compound(s), iteration +1);
				System.out.println();
			} else if(!printed.contains(s)){
				System.out.println(Utils.repeat("	", iteration) + s);
				System.out.print(Utils.repeat("	", iteration+1));
				ArrayList<String> printed2 = new ArrayList<String>();
				for(Element e : parseElements(s)){
					if(!printed2.contains(e.name)){
						System.out.print( e.name + " ");
						printed2.add(e.name);
					}
				}
				printed.add(s);
				System.out.println();
			}
		}
		
	}
	private static void printBreakdown(Compound c, int iteration){
		//System.out.print("\n");
		//for(Compound d : c.compounds) System.out.print(d.Formula + " ");
		for(String s : Compound.splitSubCompounds(c.Formula, 0)){
			if((s.contains("(") || s.contains(")"))){
				System.out.println(Utils.repeat("	", iteration) + s);
				print(new Compound(s), iteration +1);
				System.out.println();
			} else{
				System.out.println(Utils.repeat("	", iteration) + s);
				System.out.print(Utils.repeat("	", iteration+1));
				for(Element e : parseElements(s)){
					System.out.print( e.name + " ");
				}
				System.out.println();
			}
		}
		
	}
	public void print(){
		//Num.printlnCol(new int[]{45,  10, 5, 30, 45}, "in1", "firstInd", "x", "Last added", "in2");
		System.out.println(Formula + ":");
		System.out.println(splitSubCompounds(this.Formula, 0) + "\n");
		print(this, 0); 
	}
	public void printBreakdown(){
		//Num.printlnCol(new int[]{45,  10, 5, 30, 45}, "in1", "firstInd", "x", "Last added", "in2");
		System.out.println(splitSubCompounds(this.Formula, 0) + "\n");
		System.out.println(Formula + ":\n");
		printBreakdown(this, 1); 
	}
}

class Element {
	public int[] 			oxidationStates;
	public boolean 			metal;
	public ElectronConfig 	electronicConfiguration;
	public state			standardState;
	public bond				bondingType;
	public int  			atomicNumber,
							yearDiscovered;
	public String 			symbol,
							CPKColor,
							name;
	public double 			electronegativity,
							atomicRadius,
							ionicRadius, 
							vanDerWaalsRadius,
							IE1,
							EA,
							meltingPoint,
							boilingPoint,
							density,
							atomicMass;
	
	public Element(String in){
		String[] prp = combineOxiStates(in);
		for(int i = 0; i < prp.length; i++){
			if(prp[i].equals(" ")) prp[i] = "";
		}
		
		if(!prp[ 0].trim().equals("")) atomicNumber = 			Integer.parseInt(prp[0]);
		if(!prp[ 1].trim().equals("")) symbol = 				prp[1].trim();
		if(!prp[ 2].trim().equals("")) name = 					prp[2].trim();
		if(!prp[ 3].trim().equals("")) atomicMass = 			Double.parseDouble(prp[3].endsWith(")") ? prp[3].substring(0, prp[3].indexOf("(")) : prp[3]);
		if(!prp[ 4].trim().equals("")) CPKColor = 				"0x" + prp[4].trim();
		if(!prp[ 5].trim().equals("")) electronicConfiguration = new ElectronConfig(prp[5]);
		if(!prp[ 6].trim().equals("")) electronegativity = 		Double.parseDouble(prp[6]);
		if(!prp[ 7].trim().equals("")) atomicRadius = 			Double.parseDouble(prp[7]);
		if(!prp[ 8].trim().equals("")) ionicRadius = 			Double.parseDouble(prp[8].endsWith(")") ? prp[8].substring(0, prp[8].indexOf("(")) : prp[8]);
		if(!prp[ 9].trim().equals("")) vanDerWaalsRadius = 		Double.parseDouble(prp[9]);
		if(!prp[10].trim().equals("")) IE1 = 					Double.parseDouble(prp[10]);
		if(!prp[11].trim().equals("")) EA = 					Double.parseDouble(prp[11]);
		if(!prp[12].trim().equals("")) if(in.contains("{")) oxidationStates =		parseOxiStates(prp[12].substring(1, prp[12].length()-1 < 1 ? 1 : prp[12].length()-1).split(","));
		if(!prp[13].trim().equals("")) standardState = 			prp[13].equalsIgnoreCase("solid") ? state.Solid : 
																prp[13].equalsIgnoreCase("liquid") ? state.Liquid : 
																									state.Gas;
		if(!prp[14].trim().equals("")) bondingType =			prp[14].equalsIgnoreCase("diatomic") ? bond.Diatomic : 
																prp[14].equalsIgnoreCase("atomic") ? bond.Atomic : 
																prp[14].equalsIgnoreCase("metallic") ? bond.Metallic : 
																									bond.CovalentNetwork;
		if(!prp[15].trim().equals("")) meltingPoint = 			Double.parseDouble(prp[15]);
		if(!prp[16].trim().equals("")) boilingPoint = 			Double.parseDouble(prp[16]);
		if(!prp[17].trim().equals("")) density = 				Double.parseDouble(prp[17]);
		if(!prp[18].trim().equals("")) metal = 					prp[18].trim().equalsIgnoreCase("metal");
		if(!prp[19].trim().equals("")) yearDiscovered = 		prp[19].equalsIgnoreCase("Ancient") ? 0 : Integer.parseInt(prp[19]);
		
	}
	private static int[] parseOxiStates(String[] in){
		int[] retArr = new int[in.length];
		for(int i = 0; i<in.length; i++){
			retArr[i] = Integer.parseInt(in[i].trim());
		}
		return retArr;
	}
	private static String[] combineOxiStates(String in){
		if(!in.contains("{")) return in.split(",");
		String[] a = in.substring(0, in.indexOf("{")).split(",");
		String b = in.substring(in.indexOf("{"), in.indexOf("}")+1);
		String[] c = in.substring(in.indexOf("}")+2).split(",");
		
		if(in.substring(0, in.indexOf("{")).endsWith(",,")){
			String[] d = new String[a.length+1];
			for(int i = 0; i < a.length; i++) d[i] = a[i];
			d[a.length] = "";
			a = d;
		}
		
		String[] retStr = new String[a.length + 1 + c.length];
		
		for(int i = 0; i<a.length; i++){
			retStr[i] = a[i];
		}
		retStr[a.length] = b;
		for(int i = a.length+1; i<retStr.length; i++){
			retStr[i] = c[i-a.length-1];
		}
		
		return retStr;
	}
}
enum state{ Solid, Liquid, Gas; }
enum bond{ Diatomic, Atomic, Metallic, CovalentNetwork; }

class ElectronConfig{
	public int 	shell_1s, shell_2s, shell_2p, shell_3s, shell_3p, 
				shell_4s, shell_3d, shell_4p, shell_5s, shell_4d, 
				shell_5p, shell_6s, shell_4f, shell_5d, shell_6p, 
				shell_7s, shell_5f, shell_6d, shell_7p, shell_8s, 
				shell_5g, shell_6f, shell_7d, shell_8p, shell_9s;
	public ArrayList<Integer> shells = new ArrayList<Integer>();
	public String fullConfig, shortConfig;
	
	final private static String[] a = {"[He]", "[Ne]", "[Ar]", "[Kr]", "[Xe]", "[Rn]"};
	final private static String[] b = {	"1s2", 							//He
										"[He] 2s2 2p6",					//Ne
										"[Ne] 3s2 3p6",					//Ar
										"[Ar] 3d10 4s2 4p6",			//Kr
										"[Kr] 4d10 5s2 5p6",			//Xe
										"[Xe] 4f14 5d10 6s2 6p6"};		//Rn
	final private static String[] c = {	"1s2", 							//He
										"1s2 2s2 2p6",															//Ne
										"1s2 2s2 2p6 3s2 3p6",													//Ar
										"1s2 2s2 2p6 3s2 3p6 3d10 4s2 4p6",										//Kr
										"1s2 2s2 2p6 3s2 3p6 3d10 4s2 4p6 4d10 5s2 5p6",						//Xe
										"1s2 2s2 2p6 3s2 3p6 3d10 4s2 4p6 4d10 5s2 5p6 4f14 5d10 6s2 6p6"};		//Rn
	
	final private static List<String> nobleGasses = Arrays.asList(a);
	final private static List<String> NGConfig = Arrays.asList(b);
	final private static List<String> NGLongConfig = Arrays.asList(c);
	
	ElectronConfig(String shortConfiguration){
		fullConfig = shortToLongConfig(shortConfiguration);
		shortConfig = shortConfiguration;
		
		for(String a : fullConfig.split(" ")){
			a = a.trim();
			shells.add(Integer.parseInt(a.substring(2)));
		}
		while(shells.size() <= 24){
			shells.add(0);
		}
		
		shell_1s = shells.get(0 );
		shell_2s = shells.get(1 );
		shell_2p = shells.get(2 );
		shell_3s = shells.get(3 );
		shell_3p = shells.get(4 );
		shell_4s = shells.get(5 );
		shell_3d = shells.get(6 );
		shell_4p = shells.get(7 );
		shell_5s = shells.get(8 );
		shell_4d = shells.get(9 );
		shell_5p = shells.get(10);
		shell_6s = shells.get(11);
		shell_4f = shells.get(12);
		shell_5d = shells.get(13);
		shell_6p = shells.get(14);
		shell_7s = shells.get(15);
		shell_5f = shells.get(16);
		shell_6d = shells.get(17);
		shell_7p = shells.get(18);
		shell_8s = shells.get(19);
		shell_5g = shells.get(20);
		shell_6f = shells.get(21);
		shell_7d = shells.get(22);
		shell_8p = shells.get(23);
		shell_9s = shells.get(24);
	}
	
	public static String shortToLongConfig(String in){
		if(	nobleGasses.contains(in.substring(0, 4).trim()) ){
				return shortToLongConfig(NGConfig.get(nobleGasses.indexOf(in.substring(0,4))) + " " + in.substring(4).trim());
		}else{
			return in;
		}
	}
	public static String longToShortConfig(String in){
		int i = -1;
		for(String pre : c)	if(in.startsWith(pre)) i++;
		return in.replaceFirst(NGLongConfig.get(i), nobleGasses.get(i));
	}
}
/*

if(in.substring(i, i+1).equals("(")){
	int x = 1;
	
	if(in.lastIndexOf(")") < in.length()-2 && in.substring(in.lastIndexOf(")")+2, in.lastIndexOf(")")+3).matches("[0-9]{2}") ) 
		x = Integer.parseInt(in.substring(in.lastIndexOf(")")+2, in.lastIndexOf(")")+3));
	else if(in.lastIndexOf(")") < in.length()-1 && in.substring(in.lastIndexOf(")")+1, in.lastIndexOf(")")+2).matches("[0-9]{1}") ) 
		x = Integer.parseInt(in.substring(in.lastIndexOf(")")+1, in.lastIndexOf(")")+2));
	for(int a = 0; a < x; a++){
		retComp.compounds.add(parseCompound(in.substring(i+1, in.lastIndexOf(")"))));
	}
	in = in.substring(0, in.indexOf("(")) + in.substring(in.lastIndexOf(")")+Num.length(x));
}





		ArrayList<String> printed = new ArrayList<String>();
		for(Element e : c.elements){
			if(!printed.contains(e.name)) {
				System.out.println(Num.forPrintCol(new int[]{20, 5}, Num.repeat("   ", iteration) + e.name, ""+Num.countOccurance(c.elements, e)));
				printed.add(e.name);
			}
		}
		printed.clear();
		for(Compound d : c.compounds){
			if(!printed.contains(d.Formula)){
				System.out.println(Num.forPrintCol(new int[]{20, 5}, Num.repeat("   ", iteration) + "(" + d.Formula + ")", ""+compoundParser.countOccurances(c.compounds, d)));
				print(d, iteration+1);
				printed.add(d.Formula);
			}
		}

*/
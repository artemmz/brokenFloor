import java.util.*;

/**
* FixFloor class calculates minimum expenses to replace broken floor pieces
* given the choice between 2x1 & 1x1 pieces and their price.
*/
public class FixFloor{
	
	static final int[] leftRight 	= 	{ 1, 0, 0, -1 };
	static final int[] upDown 		= 	{ 0, 1, -1, 0 };
	private static List<String> floorList = new ArrayList<String>();
	private static List<List<Integer>> brokenGraph = new ArrayList<List<Integer>>();
	private static int[] matching;
	private static int n;
	private static int m;
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		int a = scan.nextInt();
		int b = scan.nextInt();
		scan.nextLine();
		for (int i = 0; i < n; i++){
			floorList.add(scan.nextLine().trim());
		}
		
		int brokenNumb = 0;
		for (int i = 0; i < n; i++){
			for (int j = 0; j < m; j++){
				brokenGraph.add(new ArrayList<Integer>());
				if ((i+j)%2 != 0 && isBroken(i,j)){ 
					for (int k = 0; k < leftRight.length; k++){
						int iNear = i + leftRight[k];
						int jNear = j + upDown[k];
						if (isBroken(iNear,jNear)){
							addEdge(floorNumb(i, j), floorNumb(iNear, jNear)); 
						}
					}
				}
				if (isBroken(i,j)){
					brokenNumb++;
				}
			}
		}
		
		if (2 * b <= a){
			System.out.println(b*brokenNumb);
		}else{
			int maxMatches = maxMatching();
			System.out.println(maxMatches * a + (brokenNumb - 2 * maxMatches)*b);
		}
	}
	
	private static int maxMatching (){
		matching = new int[n * m];
		Arrays.fill(matching, -1);
		for (int v = 0; v < n * m; v++){
			findPath(v, new boolean[n*m]);
		}
		
		int pairs = 0;
		for (int to = 0; to < n * m; to++){
			if (matching[to] != -1){
				pairs++;
			}
		}
		return pairs;
	}
	
	private static boolean findPath(int v, boolean[] used){
		if (used[v]) return false;
		used[v] = true;
		for (Integer to : brokenGraph.get(v)) {
			if (matching[to] == -1 || findPath(matching[to], used)) {
				matching[to] = v;
				return true;
			}
		}
		return false;
	}
	
	private static boolean isBroken(int i,int j){
		return (0 <= i) && (i < n) && (0 <= j) && (j < m) && (floorList.get(i).charAt(j) == '*'); 
	}
	
	private static int floorNumb(int i, int j){
		return i * m +j;
	}
	
	private static void addEdge(int v, int to){
		brokenGraph.get(v).add(to);
	}
}

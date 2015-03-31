import java.util.*;

public class FixFloor{
	
	// исп обход в глубину
	// нужно брать меньшую долю?? а есть меньшая?
	// оптимизация эвристикой
	
	static final int[] leftRight 	= 	{ 1, 0, 0, -1 };
	static final int[] upDown 		= 	{ 0, 1, -1, 0 };
	private static List<String> floorList = new ArrayList<String>();
	private static List<List<Integer>> brokenGraph = new ArrayList<List<Integer>>();
	private static List<Integer> used = new ArrayList<Integer>();
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
				if ((i+j)%2 != 0 && isBroken(i,j)){
					for (int k = 0; k < leftRight.length; k++){
						int iNear = i + leftRight[k];
						int jNear = j + upDown[k];
						if (isBroken(iNear,jNear)){
							addEdge(floorNumb(i, j), floorNumb(iNear, jNear)); // написать addEdge!
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
			
		}
		
		System.out.println();
		System.out.println(brokenNumb);
		System.out.println(floorList);
	}
	
	private static boolean isBroken(int i,int j){
		return (0 <= i) && (i < n) && (0 <= j) && (j < m) && (floorList.get(i).charAt(j) == '*'); // mb equals?????
	}
	
	private static int floorNumb(int i, int j){
		return i * m +j;
	}
	
	private static void addEdge(int v, int to){
		brokenGraph.get(v).add(to);
	}
}

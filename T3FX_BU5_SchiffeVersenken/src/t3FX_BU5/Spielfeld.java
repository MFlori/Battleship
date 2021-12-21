package t3FX_BU5;

public class Spielfeld {

	private int [][] coordinates;

	public Spielfeld() {
		super();
		int [][] coord = new int [9][9]; //erweitertes Spielfeld (7x7 + Rahmen wg Prüfung)
		int x = 0;
		int y = 0;
		int hv = 0; // Variable für horizontal 0 / vertikal 1
		int count = 0; //prüft ob bereits 3 Schiffe vorhanden sind
				
		//Felder mit 0 initialisieren
		for(int i=0;i<coord.length;i++) {
			for(int j=0;j<coord.length;j++) {
				coord[i][j]=0;
			}
		}
		//Schleife läuft bis 3 Schiffe erzeugt wurden
		while(count <3) {
			hv = (int) (Math.random()*2);
			//wenn horizontal:
			if(hv == 0) { 
				x= (int) (Math.random()*5+2);//Schiffsmitte x zwischen 2 und 6
				y= (int) (Math.random()*7+1);//Schiffsmitte y zwischen 1 und 7
				//prüft, ob alle Felder (inkl angrenzende) leer sind:
				if(coord[x][y]!=1 && coord[x-1][y]!=1 && coord[x+1][y]!=1 && 
				   coord[x-2][y]!=1 && coord[x+2][y]!=1 && coord[x-1][y-1]!=1 && 
				   coord[x][y-1]!=1 && coord[x+1][y-1]!=1 && coord[x-1][y+1]!=1 && 
				   coord[x][y+1]!=1 && coord[x+1][y+1]!=1) { 
						
					coord[x][y] = 1;
					coord[x-1][y] = 1;
					coord[x+1][y] = 1;
					count++;	
				}
			}
			//wenn vertikal:
			else if(hv == 1) {
				x= (int) (Math.random()*7+1);//Schiffsmitte x zwischen 1 und 7
				y= (int) (Math.random()*5+2);//Schiffsmitte y zwischen 2 und 6
				//prüft, ob alle Felder (inkl angrenzende) leer sind:
				if(coord[x][y]!=1 && coord[x][y-1]!=1 && coord[x][y+1]!=1 && 
						   coord[x][y-2]!=1 && coord[x][y+2]!=1 && coord[x-1][y-1]!=1 && 
						   coord[x-1][y]!=1 && coord[x-1][y+1]!=1 && coord[x+1][y-1]!=1 && 
						   coord[x+1][y]!=1 && coord[x+1][y+1]!=1) { 
					
					coord[x][y] = 1;
					coord[x][y-1] = 1;
					coord[x][y+1] = 1;
					count++;
				}
			}
		}
		//Umwandlung in 7x7 Spielfeld (äußerer Felder werden entfernt):
		int [][]coordX = new int [7][7];
		for(int i=0;i<coordX.length;i++) {
			for(int j=0;j<coordX.length;j++) {
				coordX[i][j] = coord[i+1][j+1];
			}
		}
		this.coordinates = coordX;
	}
	public int[][] getCoordinates() {
		return coordinates;
	}
}

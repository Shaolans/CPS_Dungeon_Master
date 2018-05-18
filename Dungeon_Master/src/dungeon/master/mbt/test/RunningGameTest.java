package dungeon.master.mbt.test;

import java.util.Scanner;

import dungeon.master.components.Cow;
import dungeon.master.components.Engine;
import dungeon.master.components.Environment;
import dungeon.master.components.Player;
import dungeon.master.enumerations.Command;
import dungeon.master.enumerations.Dir;

public class RunningGameTest {

	public static void main(String[] args) {
		Environment env = new Environment();
		env.init(15, 20);
		Engine e = new Engine();
		e.init(env);
		Cow cow = new Cow();
		cow.init(env, 6, 7, Dir.N, 3);
		Player player = new Player();
		player.init(env, 5, 4, Dir.N);
		e.addEntity(cow);
		e.addEntity(player);
		
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i < 10; i++){
			String cmd = sc.nextLine();
			switch(cmd){
			case "FF":
				player.setLastCom(Command.FF);
				break;
			case "BB":
				player.setLastCom(Command.BB);
				break;
			case "RR":
				player.setLastCom(Command.RR);
				break;
			case "LL":
				player.setLastCom(Command.LL);
				break;
			case "TR":
				player.setLastCom(Command.TR);
				break;
			case "TL":
				player.setLastCom(Command.TL);
				break;
				
			}
			
			e.step();
			
			for(int k = 0; k < 15; k++){
				for(int j = 0; j < 20; j++){
					if(player.getCol() == k && player.getRow() == j){
						System.out.print("P  "+"  ");
					}else if(cow.getCol() == k && cow.getRow() == j){
						System.out.print("C  "+"  ");
					}else{
						System.out.print(env.getCellNature(k, j)+"  ");
					}
				}
				System.out.println();
			}
		}
		

	}

}

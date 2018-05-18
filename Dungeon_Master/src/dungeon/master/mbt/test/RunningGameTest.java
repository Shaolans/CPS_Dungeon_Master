package dungeon.master.mbt.test;

import java.util.Scanner;

import dungeon.master.components.Cow;
import dungeon.master.components.Engine;
import dungeon.master.components.Environment;
import dungeon.master.components.Player;
import dungeon.master.contracts.CowServiceContract;
import dungeon.master.contracts.EngineServiceContract;
import dungeon.master.contracts.EnvironmentServiceContract;
import dungeon.master.contracts.PlayerServiceContract;
import dungeon.master.enumerations.Command;
import dungeon.master.enumerations.Dir;

public class RunningGameTest {

	public static void main(String[] args) {
		EnvironmentServiceContract env = new EnvironmentServiceContract(new Environment());
		env.init(15, 20);
		EngineServiceContract e = new EngineServiceContract(new Engine());
		e.init(env);
		CowServiceContract cow = new CowServiceContract(new Cow());
		cow.init(env, 6, 7, Dir.N, 3);
		PlayerServiceContract player = new PlayerServiceContract(new Player());
		player.init(env, 5, 7, Dir.N, 100);
		e.addEntity(cow);
		e.addEntity(player);
		
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i < 100; i++){
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
			
			for(int k = 0; k < 20; k++){
				for(int j = 0; j < 15; j++){
					if(player.getCol() == j && player.getRow() == k){
						System.out.print("P  "+"  ");
					}else if(cow.getCol() == j && cow.getRow() == k){
						System.out.print("C  "+"  ");
					}else{
						System.out.print(env.getCellNature(j, k)+"  ");
					}
				}
				System.out.println();
			}
		}
		

	}

}

package dungeon.master.mbt.test;

import java.util.Scanner;

import dungeon.master.components.Cow;
import dungeon.master.components.Engine;
import dungeon.master.components.Environment;
import dungeon.master.components.Monster;
import dungeon.master.components.Player;
import dungeon.master.contracts.CowServiceContract;
import dungeon.master.contracts.EngineServiceContract;
import dungeon.master.contracts.EnvironmentServiceContract;
import dungeon.master.contracts.MonsterServiceContract;
import dungeon.master.contracts.PlayerServiceContract;
import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Command;
import dungeon.master.enumerations.Dir;

public class RunningGameTest {

	public static void main(String[] args) {
		EnvironmentServiceContract env = new EnvironmentServiceContract(new Environment());
		env.init(15, 20);
		EngineServiceContract e = new EngineServiceContract(new Engine());
		PlayerServiceContract player = new PlayerServiceContract(new Player());
		player.init(env, 5, 7, Dir.N, 1000, 100);
		e.init(env, player);
		CowServiceContract cow = new CowServiceContract(new Cow());
		cow.init(env, 6, 7, Dir.N, 3, 10);
		
		MonsterServiceContract monster = new MonsterServiceContract(new Monster());
		monster.init(env, 10, 10, Dir.N, 50, 10);
		
		e.addEntity(monster);
		e.addEntity(cow);
		e.addEntity(player);
		env.setNature(5, 10, Cell.DWO);
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
			case "OP":
				player.openDoor();
				break;
			case "CL":
				player.closeDoor();
				break;
			case "ATK":
				player.attack();
				break;
			case "PCK":
				player.pickItem();
			}
			
			e.clean();
			e.step();
			
			for(int k = 0; k < 15; k++){
				for(int j = 0; j < 20; j++){
					if(player.getCol() == k && player.getRow() == j){
						System.out.print("P  "+"  ");
					}else if(cow.getCol() == k && cow.getRow() == j){
						System.out.print("C  "+"  ");
					}else if(monster.getCol() == k && monster.getRow() == j){
						System.out.print("M  "+"  ");
					}else {
						System.out.print(env.getCellNature(k, j)+"  ");
					}
				}
				System.out.println();
			}
			System.out.println("TREASURE: "+player.foundTreasure());
			System.out.println("FINISHED: "+e.isFinished());
		}
		sc.close();
		

	}

}

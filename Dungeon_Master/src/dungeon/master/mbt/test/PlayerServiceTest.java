package dungeon.master.mbt.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dungeon.master.components.Player;
import dungeon.master.contracts.PlayerServiceContract;
import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Command;
import dungeon.master.enumerations.Dir;
import dungeon.master.exceptions.InvariantError;
import dungeon.master.exceptions.PostconditionError;
import dungeon.master.exceptions.PreconditionError;
import dungeon.master.services.PlayerService;

public class PlayerServiceTest extends MobServiceTest {
	private PlayerService player;
	
	@Before
	public void beforeTests(){
		super.beforeTests();
		player = new PlayerServiceContract(new Player());
		mob = player;
		player.init(env, 5, 7, Dir.N, 10);
	}
	
	@After
	public void afterTests(){
		super.afterTests();
		player = null;
	}
	
	
	@Test
	public void closeDoorTransitionTest_1() {
		try {
			
			player.setLastCom(Command.BB);
			env.setNature(5, 8, Cell.DNO);
			player.closeDoor();
			
			assertTrue(player.getCol() == 5 && player.getRow() == 7 && env.getCellNature(5, 8) == Cell.DNC);
		}catch(PostconditionError pe) {
			fail();
		}catch(InvariantError ie) {
			ie.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void closeDoorTransitionTest_2() {
		try {
			
			player.init(env, 5, 7, Dir.S, 10);
			player.setLastCom(Command.BB);
			env.setNature(5, 6, Cell.DWO);
			player.closeDoor();
			
			assertTrue(player.getCol() == 5 && player.getRow() == 7 && env.getCellNature(5, 6) == Cell.DWC);
		}catch(PostconditionError pe) {
			fail();
		}catch(InvariantError ie) {
			ie.printStackTrace();
			fail();
		}
	}
	
	
	@Test
	public void openDoorTransitionTest_1() {
		try {
			
			player.setLastCom(Command.BB);
			env.setNature(5, 8, Cell.DNC);
			player.openDoor();
			
			assertTrue(player.getCol() == 5 && player.getRow() == 7 && env.getCellNature(5, 8) == Cell.DNO);
		}catch(PostconditionError pe) {
			fail();
		}catch(InvariantError ie) {
			ie.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void openDoorTransitionTest_2() {
		try {
			
			player.init(env, 5, 7, Dir.S, 10);
			player.setLastCom(Command.BB);
			env.setNature(5, 6, Cell.DWC);
			player.openDoor();
			
			assertTrue(player.getCol() == 5 && player.getRow() == 7 && env.getCellNature(5, 6) == Cell.DWO);
		}catch(PostconditionError pe) {
			fail();
		}catch(InvariantError ie) {
			ie.printStackTrace();
			fail();
		}
	}
	
	
	@Test
	public void setLastComTransitionTest_1(){
		try{
			player.setLastCom(Command.FF);
			assertTrue(player.getLastCom().getValue() == Command.FF);
		}catch(InvariantError ie){
			fail();
		}
	}
	
	@Test
	public void getContentPreTest_1(){
		//cas positif
		try{
			player.getContent(1, 2);
		}catch(PreconditionError pe){
			fail();
		}
	}
	
	@Test
	public void getContentPreTest_2(){
		//cas negatif
		try{
			player.getContent(15, 20);
			fail();
		}catch(PreconditionError pe){
		}
	}
	
	@Test
	public void getNaturePreTest_1(){
		//cas positif
		try{
			player.getNature(1, -1);
		}catch(PreconditionError pe){
			fail();
		}
	}
	
	
	@Test
	public void getNaturePreTest_2(){
		//cas negatif
		try{
			player.getNature(-15, -1);
			fail();
		}catch(PreconditionError pe){
		}
	}
	
	@Test
	public void isViewablePreTest_1(){
		//cas positif
		try{
			player.isViewable(1, -1);
		}catch(PreconditionError pe){
			fail();
		}
	}
	
	
	@Test
	public void isViewablePreTest_2(){
		//cas positif
		try{
			player.isViewable(1, -10);
			fail();
		}catch(PreconditionError pe){
		}
	}
	
	@Test
	public void stepTransitionTest_1(){
		try{
			player.setLastCom(Command.FF);
			player.step();
			assertTrue(player.getCol() == 5 && player.getRow() == 8);
		}catch(PostconditionError ie){
			fail();
		}
	}
	
	@Test
	public void stepTransitionTest_2(){
		try{
			player.setLastCom(Command.BB);
			player.step();
			assertTrue(player.getCol() == 5 && player.getRow() == 6);
		}catch(PostconditionError ie){
			fail();
		}
	}
	
	@Test
	public void stepTransitionTest_3(){
		try{
			player.setLastCom(Command.RR);
			player.step();
			assertTrue(player.getCol() == 6 && player.getRow() == 7);
		}catch(PostconditionError ie){
			fail();
		}
	}
	
	@Test
	public void stepTransitionTest_4(){
		try{
			player.setLastCom(Command.LL);
			player.step();
			assertTrue(player.getCol() == 4 && player.getRow() == 7);
		}catch(PostconditionError ie){
			fail();
		}
	}
	
	
}

package dungeon.master.components;

import java.util.ArrayList;
import java.util.List;

import dungeon.master.enumerations.Cell;
import dungeon.master.enumerations.Pair;
import dungeon.master.services.EditMapService;

public class EditMap extends Map implements EditMapService {
	
	
	@Override
	public void init(int w, int h) {
		super.init(w, h);
		cellmap[0][0] = Cell.IN;
		cellmap[w-1][h-1] = Cell.OUT;
	}
	
	
	@Override
	public boolean isReachable(int x1, int y1, int x2, int y2) {
		Cell [][]tab = new Cell[getWidth()][getHeight()];
		for(int i = 0; i < getWidth(); i++){
			for(int j = 0; j < getHeight(); j++){
				tab[i][j] = getCellNature(i, j);
			}
		}
		List<Pair<Integer, Integer>> track = new ArrayList<>();
		if(!backtracking(x1, y1, x2, y2, track, tab)) return false;
		if(!(track.get(0).getValue1() == x1 && track.get(0).getValue2() == y1)) return false;
		if(!(track.get(track.size()-1).getValue1() == x2 && track.get(track.size()-1).getValue2() == y2)) return false;
		
		for(int i = 1; i < track.size(); i++){
			Pair<Integer, Integer> uv = track.get(i-1);
			Pair<Integer, Integer> st = track.get(i);
			if((uv.getValue1().intValue()-st.getValue1().intValue())*(uv.getValue1().intValue()-st.getValue1().intValue())+
					(uv.getValue2().intValue()-st.getValue2().intValue())*(uv.getValue2().intValue()-st.getValue2().intValue()) != 1){
				return false;
			}
		}
		
		for(int i = 1; i < track.size()-1; i++){
			if(getCellNature(track.get(i).getValue1(), track.get(i).getValue2()) == Cell.WLL) return false;
		}
		
		return true;
	}
	
	
	
	public boolean backtracking(int x, int y, int xend, int yend, List<Pair<Integer, Integer>> track, Cell[][] tab){
		boolean res;
		
		if(x == xend && y == yend && tab[x][y] != Cell.WLL){
			track.add(0, new Pair<Integer, Integer>(x,y));
			return true;
		}
		
		tab[x][y] = Cell.WLL;
		
		if(x+1 < tab.length && tab[x+1][y] != Cell.WLL){
			res = backtracking(x+1, y, xend, yend, track, tab);
			if(res){
				track.add(0, new Pair<Integer, Integer>(x,y));
				return true;
			}
		}
		
		if(x-1 > 0 && tab[x-1][y] != Cell.WLL){
			res = backtracking(x-1, y, xend, yend, track, tab);
			if(res){
				track.add(0, new Pair<Integer, Integer>(x,y));
				return true;
			}
		}
		
		if(y+1 < tab[0].length && tab[x][y+1] != Cell.WLL){
			res = backtracking(x, y+1, xend, yend, track, tab);
			if(res){
				track.add(0, new Pair<Integer, Integer>(x,y));
				return true;
			}
		}
		
		if(y-1 > 0 && tab[x][y-1] != Cell.WLL){
			res = backtracking(x, y-1, xend, yend, track, tab);
			if(res){
				track.add(0, new Pair<Integer, Integer>(x,y));
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean isReady() {
		Pair<Integer, Integer> in = null, out = null;
		for(int i = 0; i < getWidth(); i++){
			for(int j = 0; j < getHeight(); j++){
				if(getCellNature(i,j) == Cell.IN){
					in = new Pair<Integer, Integer>(i,j);
				}
				if(getCellNature(i,j) == Cell.OUT){
					out = new Pair<Integer, Integer>(i,j);
				}
			}
		}
		
		int xi = in.getValue1();
		int yi = in.getValue2();
		
		int xo = out.getValue1();
		int yo = out.getValue2();
		
		boolean res = (getCellNature(xi, yi) == Cell.IN && getCellNature(xo, yo) == Cell.OUT);
		res = res & isReachable(xi, yi, xo, yo);
		
		for(int i = 0; i < getWidth(); i++){
			for(int j = 0; j < getHeight(); j++){
				if(i != xi || j != yi){
					if(getCellNature(i, j) == Cell.IN){
						return false;
					}
				}
				if(i != xo || j != yo){
					if(getCellNature(i, j) == Cell.OUT){
						return false;
					}
				}
			}
		}
		
		return res;
	}

	@Override
	public void setNature(int col, int row, Cell cell) {
		cellmap[col][row] = cell;
	}

}

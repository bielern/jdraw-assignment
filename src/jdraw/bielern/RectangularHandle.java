package jdraw.bielern;

import jdraw.framework.Figure;

public class RectangularHandle extends AbstractHandle {

	public final static int N  = 0;
	public final static int NE = 1;
	public final static int E  = 2;
	public final static int SE = 3;
	public final static int S  = 4;
	public final static int SW = 5;
	public final static int W  = 6;
	public final static int NW = 7;
		
	public RectangularHandle(Figure figure, int stateID) {
		super(figure);
		switch (stateID){
			case N : {
				this.state = new ASideHandleState.NState(stateID);
				break;
			}
			case E : {
				this.state = new ASideHandleState.EState(stateID);
				break;
			}
			case W : {
				this.state = new ASideHandleState.WState(stateID);
				break;
			}
			case S : {
				this.state = new ASideHandleState.SState(stateID);
				break;
			}
			case NW : {
				this.state = new ACornerHandleState.NWState(stateID);
				break;
			}
			case NE : {
				this.state = new ACornerHandleState.NEState(stateID);
				break;
			}
			case SW : {
				this.state = new ACornerHandleState.SWState(stateID);
				break;
			}
			case SE : {
				this.state = new ACornerHandleState.SEState(stateID);
				break;
			}
		}
	}
}

package Game2048;

public class OriginalMode extends Game2048 {
	
	@Override
	public int addScore(int value) {
		return score += value;
	}
}


public class FarmGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameEnvironment game = new GameEnvironment();
		game.Run();
		
		//close the scanner once game ends.
		game.getAskPlayer().close();
		System.out.println("Thank you for playing!");
	}

}

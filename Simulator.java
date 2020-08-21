import java.io.*;

public class Simulator {
	public static void main(String[] args){
		int i = 0;
		Integer top = null;
		
		if (args.length != 1) {
			System.out.println("usage : java Simulator [simularion file]");
			return ;
		}

		WeatherTower t = new WeatherTower();

		try {
			FileInputStream fstream = new FileInputStream(args[0]);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String buff;
			while ((buff = br.readLine()) != null) {
				if (buff.length() == 0)
					break ;
				if (top == null) {
					if (!buff.matches("^[+-]?\\d+$"))
						throw new ParsingException("First line must be a single integer");
					top = Integer.parseInt(buff);
				}
				else {
					String[] tokens = buff.split(" ");
					if (tokens.length != 5)
						throw new ParsingException("Each line must contains 5 tokens");
					else if (!tokens[2].matches("^[+-]?\\d+$") || !tokens[3].matches("^[+-]?\\d+$") || !tokens[4].matches("^[+-]?\\d+$"))
						throw new ParsingException("Coordinates must be integers");
					else
						t.register(AircraftFactory.newAircraft(tokens[0], tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4])));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace(System.out);
			throw new ParsingException("Parsing failed");
		}
		
		if (top == null) {
			throw new ParsingException("No weather change count value defined");
		}
		
		while (i < top.intValue()) {
			t.changeWeather();
			i++;
		}
	}
}
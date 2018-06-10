package ppj_8.ppj_wyklad_8;
import java.util.Arrays;

public
	class Main{
		


	public static void main(String[] args){
		int[] dane = {
			(int)(Math.random()*100),
			(int)(Math.random()*100),
			(int)(Math.random()*100),
			(int)(Math.random()*100),
			(int)(Math.random()*100),
			(int)(Math.random()*100),
			(int)(Math.random()*100)
		};	

		
		Arrays.sort(dane);
		MTNarzedzia.wyswietl(dane);
		MTNarzedzia.sortuj(dane);
		MTNarzedzia.wyswietl(dane);
		
		// ================================
		/*
		Owoc owoc1 = new Owoc();
		
		owoc1.nazwa = "Gruszka";
		owoc1.iloscPestek = 3;
		owoc1.cos = 10;
		*/
		Owoc owoc1 = new Owoc("Gruszka", 3);
		/*
		Owoc owoc2 = new Owoc();
		owoc2.nazwa = "Sliwka";
		owoc2.iloscPestek = 1;
		owoc2.cos = 20;
		*/
		Owoc owoc2 = new Owoc("Sliwka", 1);
		
		/*
		System.out.println(
			owoc1.nazwa +" iloscPestek: "+owoc1.iloscPestek + " " + owoc1.cos
		);
		System.out.println(
			owoc2.nazwa +" iloscPestek: "+owoc2.iloscPestek + " " + owoc2.cos
		);
		*/
		
		owoc1.przedstaw();
		owoc2.przedstaw();
		
		//System.out.println(
			new Owoc("Wisnia", 1).przedstaw();
		//);
		
		System.out.println(
			new Owoc("Wisnia", 1)
		);
		

	}
}






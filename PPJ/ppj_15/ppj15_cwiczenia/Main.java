package ppj15_cwiczenia;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Main {
    public static void main(String[] args) {
        System.out.println("//Zad1");
        int slowa = 0, data = 0, rok = 0, duzaLitera = 0, kropka = 0;
        //wynik 36 slow       
        String str = "Jan III Sobieski herbu Janina (ur. 17 sierpnia 1629 w Olesku, zm. 17 czerwca 1696 w Wilanowie) – król Polski i wielki książę litewski od 1674, hetman wielki koronny od 1668, hetman polny koronny od 1666, marszałek wielki koronny od 1665, chorąży wielki koronny od 1656";

        
        String regex = "\\.";
        
        Pattern pp = Pattern.compile(regex);
        Matcher mm = pp.matcher(str);
        
        while (mm.find()){
            kropka++;
        }
        
        Pattern pData = Pattern.compile("\\d{2} \\p{L}+ \\d{4}");
        Matcher mData = pData.matcher(str);
        
        while (mData.find()) {
            data++;
        }
        
        String[] split = str.split("\\s|\\p{Punct}");
       
        for(int i=0;i<split.length;i++) {
            Pattern p = Pattern.compile("\\p{L}+|\\p{Lu}+");
            Matcher mat = p.matcher(split[i]);
            boolean checkWord = mat.matches();
            
            if (checkWord) {
                slowa++;
            }
            
            
            Pattern prok = Pattern.compile("\\d{4}?");
            Matcher mrok = prok.matcher(split[i]);
            boolean checkRok = mrok.matches();
            
            if (checkRok) {
                rok++;
            }
            
            Pattern pduzaLit = Pattern.compile("\\p{Lu}{1}\\p{L}*");
            Matcher mduzaLit = pduzaLit.matcher(split[i]);
            boolean checkduzaLit = mduzaLit.matches();
            
            if (checkduzaLit) {
                duzaLitera++;
            }
            
        }
        System.out.println("Słowa: "+slowa);
        System.out.println("Ilość dat: "+data);
        System.out.println("Lata: "+rok);
        System.out.println("Słowo z dużej litery: "+duzaLitera);
        System.out.println("Ilość kropek: "+kropka);
        
        //Zad2
        System.out.println("");
        System.out.println("//Zad2");
        
        StringBuffer str2 = new StringBuffer();
        int wrt;
        int count=0;
        try {
            FileInputStream fr = new FileInputStream("C:\\Users\\Lucas\\Downloads\\telFormat.txt");
            while((wrt = fr.read()) != -1) {
                str2.append((char)wrt);
            }
            System.out.println(str2);
            String regex2 = "\\d{4}-\\d{3}|"
                          + "\\d{1} \\d{3}-\\d{3}|"
                          + "(\\+\\d{2}?\\((\\.{1}|\\d{2})\\))?(\\d{3}( |-|_)?\\d{3}( |-|_)?\\d{3}|\\d{7})";
            
            Pattern pTel = Pattern.compile(regex2);
            Matcher mTel = pTel.matcher(str2);
            System.out.println();
            while (mTel.find()) {
                count++;
                System.out.println(count+" "+mTel.group());
            }
            
            //System.out.println(count);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        //Zad3
        System.out.println("");
        System.out.println("//Zad3");
        
        String[] serverLogs[] = new String[100][3];
        StringBuffer str5 = new StringBuffer();
        
        try {
        FileReader frstr = new FileReader("C:\\Users\\Lucas\\Downloads\\serverLog.txt");
        
        while ((wrt = frstr.read()) != -1) {
            str5.append((char)wrt);
        }
        } catch  (Exception ex) {
            System.out.println(ex);
        }
        
        String regex_ip = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
        Pattern sLp = Pattern.compile(regex_ip); 
        Matcher sLm = sLp.matcher(str5);
        
        int count3 = 0;
        while(sLm.find()) {
            if(count3 != 100) {
                serverLogs[count3][0] = sLm.group();
                count3++;
            }
        }
        
        String regex_data = "\\d{1,2}\\/\\d{1,2}\\/\\d{4} \\d{1,2}\\:\\d{1,2}\\:\\d{1,2} (AM|PM)";
        Pattern sLpp = Pattern.compile(regex_data); 
        Matcher sLmm = sLpp.matcher(str5);
        
        int count4 = 0;
        while(sLmm.find()) {
            if(count4 != 100) {
                serverLogs[count4][1] = sLmm.group();
                count4++;
            }
        }
        
        String regex_mess = "> .+";
        Pattern sLppp = Pattern.compile(regex_mess); 
        Matcher sLmmm = sLppp.matcher(str5);
        
        int count5 = 0;
        while(sLmmm.find()) {
            if(count5 != 100) {
                serverLogs[count5][2] = sLmmm.group();
                count5++;
            }
        }
        
        for(int i=0;i<serverLogs.length;i++) {
            System.out.println(i+": "+" "+serverLogs[i][0]+" | "+serverLogs[i][1]+" | "+serverLogs[i][2]);
        }
    }
    
}

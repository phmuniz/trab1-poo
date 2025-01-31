package date;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class DateFunctions {
    
    public static Date criaData(String dataString) throws ParseException{
		
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatada = formato.parse(dataString);
        return dataFormatada;
	}
	public static void printData(Date data){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(formato.format(data));
	}
}

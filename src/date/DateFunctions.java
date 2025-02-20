package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFunctions {

	public static Date criaData(String dataString) throws ParseException {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatada = formato.parse(dataString);
		return dataFormatada;
	}

	public static void printData(Date data) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(formato.format(data));
	}

	public static String dataFormataMesAno(Date data) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int mesCal = cal.get(Calendar.MONTH);
		int mes = mesCal + 1;
		int ano = cal.get(Calendar.YEAR);

		String dataFormatada = (mes < 10 ? "0" : "") + mes + "/" + ano;

		return dataFormatada;
	}

	public static Date somaMeses(Date data, int qtdMeses) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.add(Calendar.MONTH, qtdMeses);

		Date dataAtualizada = cal.getTime();

		return dataAtualizada;
	}

	public static Date vaiProProximoMes(Date data) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.add(Calendar.MONTH, 1);

		Date dataAtualizada = cal.getTime();

		return dataAtualizada;
	}

	public static boolean ehMesmoMes(Date data1, Date data2) {

		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(data1);
		int mesData1 = cal1.get(Calendar.MONTH);
		int anoData1 = cal1.get(Calendar.YEAR);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(data2);
		int mesData2 = cal2.get(Calendar.MONTH);
		int anoData2 = cal2.get(Calendar.YEAR);
		if (mesData1 == mesData2 && anoData1 == anoData2)
			return true;

		return false;
	}

	public static boolean ehDezembro(Date data) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		int mes = cal.get(Calendar.MONTH);

		if (mes == 11)
			return true;

		return false;
	}
}

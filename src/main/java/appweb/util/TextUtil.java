package appweb.util;

import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class TextUtil {

	private static String[][] acentos = { { "a", "á" }, { "a", "ã" }, { "a", "à" }, { "a", "â" }, { "a", "ä" },
			{ "e", "é" }, { "e", "ẽ" }, { "e", "è" }, { "e", "ê" }, { "e", "ë" }, { "i", "í" }, { "i", "ĩ" },
			{ "i", "ì" }, { "i", "î" }, { "i", "ï" }, { "o", "ó" }, { "o", "õ" }, { "o", "ò" }, { "o", "ô" },
			{ "o", "ö" }, { "u", "ú" }, { "u", "ũ" }, { "u", "ù" }, { "u", "û" }, { "u", "ü" }, { "c", "ç" } };

	private static String[] notCaptalise = { "dos", "do", "das", "da", "de", "e", "a", "o" };
	private static String[] romanos = { "I", "II", "III", "IV", "V", "VI", "VII", "VII", "VIII", "IX", "X", "XXII",
			"XXIII", "PMVC", "CEI", "CE", "CMEI", "NTI", "PIS", "PASEP", "FGTS", "EPI" };
	private static String[] turnos = { "", "Matutino", "Vespertino", "Noturno", "Integral" };
	private static String[] situacaoMatricula = { "", "Matriculado", "Transferido", "Evadido", "Cancelado", "Falecido",
			"Não se aplica" };
	private static DecimalFormat df = (DecimalFormat) NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
	private static DecimalFormat dfTwo = new DecimalFormat("00");
	private static DecimalFormat dfThree = new DecimalFormat("000");

	public static String geraBrancos(int quantidade) {
		String result = "";
		for (int i = 0; i < quantidade; i++)
			result += " ";
		return result;
	}

	// Prepara o campo para o layout, alinhado a esquerda com brancos a direita
	public static String preparaCampo(String campo, int campoLen) {
		if (campo.length() >= campoLen)
			return campo.substring(0, campoLen);

		return campo + geraBrancos(campoLen - campo.length());
	}

	public static String checkLength(String campo, int campoLen) {
		if (campo != null)
			if (campo.length() > campoLen)
				return campo.substring(0, campoLen);
		return campo;
	}

	public static String retiraAcentos(String str) {
		String palavra = new String(str.toLowerCase());

		for (int i = 0; i < acentos.length - 1; i++) {
			palavra = palavra.replaceAll(acentos[i][1], acentos[i][0]);
		}

		palavra = palavra.replaceAll("\\s+", " ");
		return palavra.toUpperCase();
	}

	// Retorna a versão da string sem acentos, sem c cedilha, sem espaços duplos,
	// sem espaço no início da palavra e sem espaço no final da palavra.
	public static String retiraAcentosCedil(String str) {
		if (str != null) {
			String palavra = String.valueOf(str.toLowerCase());

			for (int i = 0; i < acentos.length; i++) {
				palavra = palavra.replaceAll(acentos[i][1], acentos[i][0]);
			}
			palavra = palavra.replaceAll("\\s+", " ");
			palavra = palavra.trim();
			return palavra.toLowerCase();
			// return palavra;
		} else {
			return null;
		}
	}

	public static String capitalise(String str) {
		if (!isEmpty(str)) {
			String palavra = new String(str.toLowerCase());
			palavra = palavra.trim();
			String[] tokens = palavra.split("\\s+");

			for (int i = 0; i < tokens.length; i++) {
				if (isRomanos(tokens[i])) {
					tokens[i] = new String((tokens[i]).trim()).toUpperCase();
				} else if (isCapitalizable(tokens[i])) {
					tokens[i] = new String(capitaliseWord(tokens[i]).trim());
				}
			}

			String result = "";
			for (int i = 0; i < tokens.length - 1; i++)
				result += tokens[i] + " ";
			return result + tokens[tokens.length - 1];
		}
		return null;
	}

	public static String capitaliseWord(String str) {
		if (!isEmpty(str))
			return str.substring(0, 1).toUpperCase() + (str.length() > 1 ? str.substring(1) : "");
		return str;
	}

	public static boolean isCapitalizable(String str) {
		for (int i = 0; i < notCaptalise.length; i++)
			if (str.equals(notCaptalise[i]))
				return false;
		return true;
	}

	public static boolean isRomanos(String str) {
		for (int i = 0; i < romanos.length; i++)
			if (str.equalsIgnoreCase(romanos[i]))
				return true;
		return false;
	}

	public static boolean isEmpty(Object obj) {
		if (obj == null)
			return true;
		if (obj instanceof String) {
			String str = (String) obj;
			if (str.trim() == null)
				return true;
			if (str.trim().equals(""))
				return true;
		}
		return false;
	}

	public static String cast(Object obj) {
		if (isEmpty(obj))
			return "";
		return obj.toString();
	}

	public static int nBool(int number) {
		if (number == 1)
			return 1;
		return 2;
	}

	public static Integer nBool(Integer number) {
		if (!isEmpty(number))
			if (number.intValue() == 1)
				return new Integer(1);
		return new Integer(2);
	}

	public static short nBool(short number) {
		if (number == 1)
			return (short) 1;
		return (short) 2;
	}

	public static Short nBool(Short number) {
		if (number.shortValue() == 1)
			return new Short((short) 1);
		return new Short((short) 2);
	}

	public static String nString(short number) {
		// System.out.println( "--:--: " + number );
		if (number <= 0)
			return "";
		return "" + number;
	}

	public static String nString(Integer number) {
		// System.out.println( "--:--: " + number );
		if (!isEmpty(number))
			if (number.intValue() <= 0)
				return "";
		return "" + number;
	}

	public static String nClear(String text, int size) {
		// altera caixa do texto.
		String result = "";
		result = nClear(text);
		if (result.length() > size)
			return result.substring(0, size);
		return result;
	}

	public static String nClear(String text) {
		if (text == null) {
			return "";
		}
		// altera caixa do texto.
		String result = "";
		result = text.replaceAll("\\p{Punct}", "");
		// result = text.replaceAll( ",", "" );
		// result = text.replaceAll( "-", "" );
		// result = text.replaceAll( "/", "" );
		// result = text.replaceAll( ":", "" );
		// result = text.replaceAll( ";", "" );
		return result.toUpperCase();
	}

	public static String clear(String text) {
		String result = null;

		if (text != null) {
			result = text.replaceAll("\\p{Punct}", "");
		}
		return result;
	}

	public static java.util.Date parseDate(String text) {
		try {
			if (text == null)
				return null;
			return new SimpleDateFormat("dd/MM/yyyy").parse(text);
		} catch (java.text.ParseException e) {
			return null;
		}
	}

	public static String formatMonetary(Integer valor) {
		try {
			if (isEmpty(valor))
				return "";
			double valorM = (double) (((double) valor.intValue()) / 100.00);
			df.applyPattern("###,###.00");
			return df.format(valorM);
		} catch (Exception e) {
			return "";
		}
	}

	public static String formatMonetary1(Integer valor) {
		try {
			if (isEmpty(valor))
				return "";
			return formatMonetary(valor);
		} catch (Exception e) {
			return "";
		}
	}

	public static Integer monetaryToInteger(String text) {
		try {
			if (isEmpty(text))
				return null;
			String word = text.replaceAll("\\p{Punct}", "");
			return new Integer(Integer.parseInt(word));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String maskCertidao(String text) {
		try {
			if (isEmpty(text) || text.length() != 32)
				return null;
			String word = text.substring(0, 6);
			word += "-" + text.substring(6, 8);
			word += "-" + text.substring(8, 10);
			word += "-" + text.substring(10, 14);
			word += "-" + text.substring(14, 15);
			word += "-" + text.substring(15, 20);
			word += "-" + text.substring(20, 23);
			word += "-" + text.substring(23, 30);
			word += "-" + text.substring(30, 32);
			return word;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String maskCEP(String text) {
		try {
			if (isEmpty(text) || text.length() != 8)
				return null;
			String word = text.substring(0, 5);
			word += "-" + text.substring(5, 8);
			return word;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String maskCPF(String text) {
		try {
			if (isEmpty(text) || text.length() != 11)
				return null;
			String word = text.substring(0, 3) + "." + text.substring(3, 6) + "." + text.substring(6, 9) + "-"
					+ text.substring(9, 11);
			return word;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String millisToHora(long tempo) {
		String hora = "";
		int secs = (int) tempo / 1000;
		if (tempo == 0) {
			hora = "0";
		} else {
			int[] ret = new int[4];
			// calcula nmero de horas, minutos e segundos
			ret[0] = secs / 3600;
			secs = secs % 3600;
			ret[1] = secs / 60;
			secs = secs % 60;
			ret[2] = secs;
			ret[3] = (int) tempo % 1000;

			DecimalFormat formatter = new DecimalFormat("00");

			hora += formatter.format(ret[0]) + ":";
			hora += formatter.format(ret[1]) + ":";
			hora += formatter.format(ret[2]) + ".";
			hora += formatter.format(ret[3]);

		} // fim do if (tempo == 0)
		return hora;
	}

	/*
	 * public static String getNecessidadeEspecialText( Collection c ) { String
	 * result = ""; try { Iterator it = c.iterator();
	 * 
	 * int i = 1;
	 * 
	 * while ( it.hasNext() ) { NecessidadeEspecial ne = (NecessidadeEspecial)
	 * it.next();
	 * 
	 * if ( i == 1 ) result += ne.getNome(); else result += ", " + ne.getNome();
	 * 
	 * i++; } } catch ( Exception e ) { e.printStackTrace(); } return result; }
	 */

	public static String formatSegundos(long segundos) {
		String result = "";
		try {
			long dias = segundos / (86400);
			long restoDias = segundos % (86400);
			long horas = restoDias / 3600;
			long restoHoras = restoDias % 3600;
			long minutos = restoHoras / 60;
			long segundosI = restoHoras % 60;

			result = String.format("%s dia(s) %s:%s:%s", dfTwo.format(dias), dfTwo.format(horas), dfTwo.format(minutos),
					dfTwo.format(segundosI));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String formatMillis(long time) {
		String result = "";
		try {
			long dias = time / (86400000);
			long restoDias = time % (86400000);
			long horas = restoDias / 3600000;
			long restoHoras = restoDias % 3600000;
			long minutos = restoHoras / 60000;
			long restoMinutos = restoHoras % 60000;
			long segundos = restoMinutos / 1000;
			long restoSegundos = restoMinutos % 1000;

			if (dias > 0) {
				result = String.format("%s dia(s) %s:%s:%s.%s", dfTwo.format(dias), dfTwo.format(horas),
						dfTwo.format(minutos), dfTwo.format(segundos), dfThree.format(restoSegundos));
			} else {
				result = String.format("%s:%s:%s.%s", dfTwo.format(horas), dfTwo.format(minutos),
						dfTwo.format(segundos), dfThree.format(restoSegundos));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getTurno(int i) {
		return turnos[i];
	}

	public static String getSituacaoMatricula(int i) {
		return situacaoMatricula[i];
	}
}

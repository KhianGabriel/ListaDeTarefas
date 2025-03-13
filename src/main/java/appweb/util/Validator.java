package appweb.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

// Classe para validação de campos em formulários.
public class Validator {
	private static String[] nomesNaoPermitidos = { "nao", "nome", "possivel", "impossivel", "invalido", "consta",
			"inlegivel", "ilegivel" };

	// Palavras que não podem conter no campo nome.
	public static boolean validaNomeNaoPermitdo(String word) {
		for (int i = 0; i < nomesNaoPermitidos.length; i++) {
			if (word.equalsIgnoreCase(nomesNaoPermitidos[i]))
				return false;
		}
		return true;
	}

	// Valida o campo nome( O campo não pode conter caracteres especiais, nomes
	// abreviados, mais de dois espaços entre as palavras e nem nome com uma
	// única palavra). E não haver 4 caracteres repetidos.
	public static boolean validaNome(String word) {
		if (word != null) {
			if (Pattern.matches("^.*(\\d+).*$", word))
				return false;
			if (Pattern.matches("^.*(\\p{Punct}+).*$", word))
				return false;
			if (Pattern.matches("^.*(\\s{2,}).*$", word))
				return false;

			String[] tokens = word.split("\\s+");

			if (tokens.length <= 1)
				return false;

			for (int i = 0; i < tokens.length; i++) {
				if (tokens[i].length() == 1)
					if (!tokens[i].equalsIgnoreCase("E"))
						return false;

				if (!validaNomeNaoPermitdo(tokens[i]))
					return false;
			}

			char ch = word.charAt(0);
			for (int i = 1, count = 1; i < word.length(); i++) {
				if (ch == word.charAt(i))
					count++;
				else {
					count = 1;
					ch = word.charAt(i);
				}

				if (count > 4)
					return false;
			}

			return true;
		}
		return false;
	}

	// Valida o campo login( O campo não pode conter caracteres especiais,
	// espaços).
	public static boolean validaLogin(String word) {
		if (word != null) {

			//return (Pattern.matches("^(\\p{Lower}{5,})+\\d*\\p{Lower}*$", word));
			return (Pattern.matches("^(\\p{Lower}{4,})+[\\d-\\p{Lower}]+$", word));
		}
		return false;
	}

	public static boolean validaCEP(String word) {		
		if (word != null) {
			if (!validaDigitNoZero(TextUtil.clear(word)))
				return false;
			//System.err.format("[%s][%s]%n","^\\d{5}-\\d{3}$", word);
			return Pattern.matches("^\\d{5}-\\d{3}$", word);
		}
		return false;
	}

	public static boolean validaCelular(String word) {
		if (word != null && word.length() >= 12) {
			// if ( !validaDigitNoZero( TextCast.clear( word ) ) )
			// return false;
			return (word.substring(4).startsWith("9") || word.substring(4).startsWith("8"));
		}
		return false;
	}

	// Valida endereço
	// Somente aceita os seguintes caracteres: A a Z
	// (maiúsculas), 0 a 9, ., /, -, ª, ° e ,.
	public static boolean validaEndereco(String word) {
		if (word != null) {
			word = word.replaceAll("\\.", "");
			word = word.replaceAll("/", "");
			word = word.replaceAll("-", "");
			word = word.replaceAll("ª", "");
			word = word.replaceAll("º", "");
			word = word.replaceAll("°", "");
			word = word.replaceAll(",", "");
			word = word.replaceAll(" ", "");
			return validaAlphaNumericNoZero(word);
		}
		return false;
	}

	public static boolean validaEnderecoLogradouro(String word) {
		if (word != null) {
			String[] words = word.split("\\s+");

			if (words.length < 2)
				return false;
			return validaEndereco(word);
		}
		return false;
	}

	public static boolean validaNumeroTelefone(String word) {
		if (word != null) {
			String wordTemp = word.replaceAll("-", "");
			wordTemp = wordTemp.replaceAll("\\(", "");
			wordTemp = wordTemp.replaceAll("\\)", "");

			if (!validaDigitNoZero(wordTemp))
				return false;
			return Pattern.matches("^\\(\\d{2}\\)\\d{4}-\\d{4}$", word);
		}
		return false;
	}

	// Valida caracteres iguais.
	public static boolean validaEqualsCaracters(String word) {
		if (word != null) {
			char ch = word.charAt(0);
			for (int i = 1; i < word.length(); i++)
				if (ch != word.charAt(i))
					return false;
			return true;
		}
		return false;
	}

	// Valida alfanumerico.
	public static boolean validaAlphaNumeric(String word) {
		if (word != null) {
			return Pattern.matches("^\\w+$", word);
		}
		return false;
	}

	// Valida alfanumerico, sem valores ex: 000000000000, 00, 0.
	public static boolean validaAlphaNumericNoZero(String word) {
		if (word != null) {
			if (!validaNoZero(word))
				return false;

			word = word.replaceAll("\\s+", "");

			return Pattern.matches("^\\w+$", word);

		}
		return false;
	}

	public static boolean validaAlphaNumericComEspaco(String word) {
		if (word != null) {
			word = word.replaceAll(" ", "");
			return Pattern.matches("^\\w+$", word);
		}
		return false;
	}

	// Valida digito.
	public static boolean validaDigit(String word) {
		if (word != null) {
			return (Pattern.matches("^\\d+$", word));
		}
		return false;
	}

	// Valida digito não zero.
	public static boolean validaDigitNoZero(String word) {
		if (word != null) {
			if (validaDigit(word))
				return (!Pattern.matches("^0+$", word));
			return false;
		}
		return false;
	}

	// Valida não zero(Quando todos caracteres são zero retorna falso).
	public static boolean validaNoZero(String word) {
		if (word != null) {
			return (!Pattern.matches("^0+$", word));
		}
		return false;
	}

	public static boolean validaSenha(String word) {
		if (word != null) {
			//return word.length() >= 8 && word.length() >= 25;
			return word.length() >= 8;
		}
		return false;
	}

	// Valida data.
	public static boolean validaData(String word) {
		if (word != null) {
			if (Pattern.matches(
					"^((0[1-9]|[12]\\d)\\/(0[1-9]|1[0-2])|30\\/(0[13-9]|1[0-2])|31\\/(0[13578]|1[02]))\\/\\d{4}$",
					word)) {
				int ano = Integer.parseInt(word.substring(6, 10));
				int mes = Integer.parseInt(word.substring(3, 5));
				int dia = Integer.parseInt(word.substring(0, 2));

				if ((ano % 4) == 0 && ((ano % 400) == 0 || (ano % 100) != 0)) {
					return true;
				} else {
					if (mes == 2 && dia == 29)
						return false;
					return true;
				}
			}
			return false;
		}
		return false;
	}

	// Valida data.
	public static boolean validaHora(String word) {
		// ^([0-1][0-9]|[2][0-3]):[0-5][0-9]$
		if (word != null) {
			return (Pattern.matches("^([0-9]|[0-1][0-9]|[2][0-3]):([0-9]|[0-5][0-9])($|:([0-9]|[0-5][0-9])$)", word));
		}
		return false;
	}

	// Valida numero PIS/PASEP
	public static boolean validaNumeroPIS(String word) {
		if (word != null) {
			if (word.length() != 11 || !validaDigitNoZero(word))
				return false;
			String ftap = "3298765432";
			int total = 0;

			for (int i = 0; i < word.length() - 1; i++) {
				total += Integer.parseInt(word.substring(i, i + 1)) * Integer.parseInt(ftap.substring(i, i + 1));

			}

			int resto = total % 11;
			// System.out.println( "Resto PIS: " + resto );
			// System.out.println( "PIS: " + word );

			if (resto != 0)
				resto = 11 - resto;
			if (resto == Integer.parseInt(word.substring(10, 11)))
				return true;
			else
				return false;
		}
		return false;
	}

	// Valida numero título de eleitor
	public static boolean validaNumeroTituloEleitor(String word) {
		if (word != null) {
			if (word.length() != 12 || !validaDigitNoZero(word))
				return false;
			// Testa a unidade da federação
			int codUF = Integer.parseInt(word.substring(8, 10));
			if (codUF < 1 || codUF > 28)
				return false;

			int total = 0, digito1 = -1, digito2 = -1;

			// Calcula primeiro digito.

			for (int i = 0; i < 8; i++) {
				total += Integer.parseInt(word.substring(i, i + 1)) * (10 - (i + 1));
			}

			int resto = total % 11;

			if (resto == 1) {
				digito1 = 0;
			} else {
				if (resto == 0) {
					if (codUF == 1 || codUF == 2)
						digito1 = 1;
					else
						digito1 = 0;
				} else {
					digito1 = 11 - resto;
				}
			}
			//
			// Calcula segundo digito.
			total = 0;
			for (int i = 8; i < 10; i++) {
				total += Integer.parseInt(word.substring(i, i + 1)) * (13 - (i + 1));
			}
			total += digito1 * 2;
			resto = total % 11;

			if (resto == 1) {
				digito2 = 0;
			} else {
				if (resto == 0) {
					if (codUF == 1 || codUF == 2)
						digito2 = 1;
					else
						digito2 = 0;
				} else {
					digito2 = 11 - resto;
				}
			}
			return (digito1 == Integer.parseInt(word.substring(10, 11))
					&& digito2 == Integer.parseInt(word.substring(11)));
		}
		return false;
	}

	// Valida numero CPF/CNPJ
	public static String calcDVMod11aInf(String word) {
		String dv = "";
		String valor = new String(word);

		if (word != null) {
			for (int k = 1; k <= 2; k++) {
				int len = valor.length();
				int soma = 0;
				int multi = 1;

				for (int i = len - 1; i >= 0; i--) {
					multi++;
					soma += Integer.parseInt(valor.substring(i, i + 1)) * multi;
				}
				int resto = soma % 11;
				int novoDV = 0;

				if (resto > 1) {
					novoDV = 11 - resto;
				}
				dv += novoDV;
				valor += novoDV;
			}
			return dv;
		}
		return "";
	}

	public static String calcDVMod11(String word) {
		String dv = "";
		String valor = new String(word);

		if (word != null) {
			for (int k = 1; k <= 2; k++) {
				int len = valor.length();
				int soma = 0;
				int multi = 1;

				for (int i = len - 1; i >= 0; i--) {
					multi++;
					soma += Integer.parseInt(valor.substring(i, i + 1)) * multi;
					if (multi == 9)
						multi = 1;
				}
				int resto = soma % 11;
				int novoDV = 0;

				if (resto > 1) {
					novoDV = 11 - resto;
				}
				dv += novoDV;
				valor += novoDV;
			}
			return dv;
		}
		return "";
	}

	public static boolean validaNumeroCPF_CNPJ(String word) {
		if (word != null) {
			if (!validaDigitNoZero(word) || validaEqualsCaracters(word))
				return false;

			String dvDigito = "";
			String dvDigitoNovo = "";

			if (word.length() == 11) {
				dvDigito = word.substring(9, 11);
				dvDigitoNovo = calcDVMod11aInf(word.substring(0, 9));
			} else if (word.length() == 14) {
				dvDigito = word.substring(12, 14);
				dvDigitoNovo = calcDVMod11(word.substring(0, 12));
			} else {
				return false;
			}

			// System.out.println( "CPF: " + word );
			// System.out.println( "DV: " + dvDigitoNovo );
			return (dvDigito.equals(dvDigitoNovo));
		}
		return false;
	}

	public static boolean validaNumeroCertidaoNascimento(String word) {
		if (word != null) {
			if (!Pattern.matches("^\\d{30}[Xx0-9][Xx0-9]$", word))
				return false;
			if (!validaDigitNoZero(word.substring(0, 30)) || validaEqualsCaracters(word))
				return false;

			String serventia = word.substring(0, 6);
			String serventiaDv = word.substring(0, 5);
			serventiaDv = mod10(serventiaDv);

			if (!serventiaDv.equals(serventia))
				return false;
			// System.out.format( "Original: %s, Mod11: %s%n", serventia, mod11( serventiaDv
			// ) );
			// System.out.format( "Original: %s, Mod11a: %s%n", serventia, mod11a(
			// serventiaDv ) );
			// System.out.format( "Original: %s, Mod10: %s%n", serventia, serventiaDv );

			// Desabilida o dígito verificador caso no numero da certidão termine com
			// XX(Cartório sem computador)
			if (word.toUpperCase().endsWith("XX"))
				return true;

			String calcDv = word.substring(0, 30);

			calcDv = mod11a(calcDv);
			calcDv = mod11a(calcDv);
			// System.out.format( "N. Original: %s, certDv: %s%n", word, calcDv );
			return calcDv.equals(word);

			// System.out.println( "CPF: " + word );
			// System.out.println( "DV: " + dvDigitoNovo );

		}
		return false;
	}

	// Valida numero Cartão SUS
	public static boolean validaNumeroCSUS(String word) {
		if (word != null) {
			if (word.length() < 15)
				return false;

			int len = 15;
			int soma = 0;

			for (int i = 0; i < len; i++) {
				soma += Integer.parseInt(word.substring(i, i + 1)) * (15 - i);
			}
			int resto = soma % 11;

			if (resto == 0) {
				return true;
			}
		}
		return false;
	}

	// Valida Endereço de e-mail.
	public static boolean validaEmail(String word) {
		if (word != null) {
			return (Pattern.matches(
					"^[\\w-]+(\\.[\\w-]+)*@(([A-Za-z\\d][A-Za-z\\d-]{0,61}[A-Za-z\\d]\\.)+[A-Za-z]{2,6}|\\[\\d{1,3}(\\.\\d{1,3}){3}\\])$",
					word));
		}
		return false;
	}

	// Valida valor financeiro.
	public static boolean validaValorFinanceiro(String word) {
		if (word != null) {
			return (Pattern.matches("^\\d{1,3}(\\.\\d{3})*\\,\\d{2}$", word));
		}
		return false;
	}

	// Retorna a idade em anos.
	public static int getIdade(Date now, Date data) {
		int idade = -1;
		if (now != null && data != null) {
			Calendar c = new GregorianCalendar();
			c.setTime(now);

			int diaNow = c.get(Calendar.DAY_OF_MONTH);
			int mesNow = c.get(Calendar.MONTH) + 1;
			int anoNow = c.get(Calendar.YEAR);

			c.setTime(data);
			int diaData = c.get(Calendar.DAY_OF_MONTH);
			int mesData = c.get(Calendar.MONTH) + 1;
			int anoData = c.get(Calendar.YEAR);

			if (mesNow < mesData) {
				anoData++;
			} else if (mesNow == mesData) {
				if (diaNow < diaData)
					anoData++;
			}

			return anoNow - anoData;
		}
		return idade;
	}

	public static String mod10(String number) {
		String result = "";
		try {
			if (number != null) {
				int sum = 0;
				boolean val = true;
				for (int i = number.length() - 1, j = 2; i >= 0; i--, val = !val) {
					int idx = (val ? 2 : 1);
					sum += sumDigit(
							Integer.toString(idx * Integer.parseInt(new Character(number.charAt(i)).toString())));
					// String sumStr = Integer.toString(sum);
					// System.out.println("Sum: " + sum + ", j: " + j +
					// ", Digit: " + Integer.parseInt(new
					// Character(number.charAt(i)).toString()));
					// System.out.println("Number: " + sumStr + ", SumDigit: " +
					// this.sumDigit(sumStr));
				}
				int resto = sum % 10;
				int digit = 0;
				if (resto != 0)
					digit = 10 - resto;

				result = number + digit;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String mod11a(String number) {
		String result = "";
		try {
			if (number != null) {
				int sum = 0;
				for (int i = number.length() - 1, j = 2; i >= 0; i--, j++) {
					sum += j * Integer.parseInt(new Character(number.charAt(i)).toString());
					// System.out.println("Sum: " + sum + ", j: " + j +
					// ", Digit: " + Integer.parseInt(new
					// Character(number.charAt(i)).toString()));
				}
				int digit = (sum * 10) % 11;

				if (digit != 10)
					result = number + digit;
				else
					// Para CPF retorna 0, para Certidão de nascimento retorna 1.
					result = number + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String mod11(String number) {
		String result = "";
		try {
			if (number != null) {
				int sum = 0;
				for (int i = number.length() - 1, j = 2; i >= 0; i--, j++) {
					sum += j * Integer.parseInt(new Character(number.charAt(i)).toString());
					// System.out.println("Sum: " + sum + ", j: " + j +
					// ", Digit: " + Integer.parseInt(new
					// Character(number.charAt(i)).toString()));
				}
				int digit = (sum * 10) % 11;

				if (digit != 10)
					result = number + digit;
				else
					// Para CPF retorna 0, para Certidão de nascimento retorna 1.
					result = number + 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static int sumDigit(String number) {
		int result = 0;
		try {
			if (number != null) {
				int sum = 0;
				for (int i = 0; i < number.length(); i++) {
					sum += Integer.parseInt(new Character(number.charAt(i)).toString());
				}
				result = sum;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
